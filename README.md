# 1. Opis problema

Cilj ovog rada je kreiranje aplikacije koja transformiše u RDF format podatke o pozivima za publikovanje radova (eng. [Call for Papers - CfP](http://en.wikipedia.org/wiki/Call_for_papers)). Ovi podaci su raspoloživi sa različitih izvora, s tim što su se za potrebe ovog projektnog zadatka koristili CfP podaci raspoloživi sa [WikiCfP sajta](http://www.wikicfp.com/cfp/). Konkretno, potrebno je iskoristiti podatke o (preko 5000) CfPs koje ovaj sajt čini dostupnim u [XML formatu](http://www.wikicfp.com/cfp/data.jsp). Transformisani podaci bi trebalo da budu predstavljeni u formi RDF tripleta, a na osnovu RDF vokabulara Call Ontology. Za ovaj vokabular je raspoloživ [dijagram](https://dl.dropbox.com/u/7030190/donotdelete/inteligentnisistemi/seminarski/Call_ontology_v.2.0.jpg) i [owl fajl](https://dl.dropbox.com/u/7030190/donotdelete/inteligentnisistemi/seminarski/call_v.2.0.owl) u kome je vokabular definisan.

Radi kreiranja aplikacije, potrebno je bilo ispuniti sledeće zahteve:
- analizirati oblik (model) u kome su podaci raspoloživi;
- kreirati mapiranja (grafički predstavljena) između modela/oblika u kojem su podaci izvorno raspoloživi i RDF vokabulara Call Ontology;
- na osnovu definisanih mapiranja, izvršiti transformaciju raspoloživih podataka u odgovarajući RDF oblik i tako transformisane podatke smestiti u RDF bazu;- omogućiti pristup podacima u bazi preko odgovarajućih REST servisa i SPARQL Endpoint-a.

# 2. Domenski model

Podaci o pozivima za publikovanje radova izvorno su raspoloživi u XML formatu. Primer izgleda jednog takvog XML fajla dat je na slici 1.

![Slika 1 - Primer CfP XML elementa](/docs/images/CfP item example in XML.png)
-> Slika 1 - Primer CfP XML elementa <-

Podatke u izvornom obliku potrebno je predstaviti, odnosno mapirati u odgovarajuće elemente RDF vokabulara Call Ontology. Grafički prikaz vokabulara kao i mapiranja između njega i podataka u izvornom obliku prikazani su na slici 2. Crvenom bojom su označeni atributi iz izvornog XML fajla, dok crvene strelice ukazuju na mapiranja.

![Slika 2 - Mapiranja izmedju CfP vokabulara i WikiCfP modela](/docs/images/CfP-model-mapping.jpg)
-> Slika 2 - Mapiranja izmedju CfP vokabulara i WikiCfP modela <-

Klasa *Event* odnosi se na događaj (npr. konferenciju, kongres itd.) koji prati konkretan poziv za publikovanje radova. Poseduje atribute kao što su datum početka, datum završetka. naziv, opis, mesto održavanja, kao i referencu ka samom CfP-u. Ostali atributi nisu značajni sa aspekta ovog rada.
Klasa *Place* opisuje lokaciju na kojoj se održava događaj i ima atribut ime.
Klasa *Call* opisuje poziv za publikovanje radova atributima datum abstrakta, datum predaje konačne verzije rada, datum donošenja konačne odluke i datum štampanja objavljenih radova. Poseduje i referencu na događaj koji prati CfP.
Vokabular Call Ontology svojim sadržajem u velikoj meri odgovara postavljenom problemu mapiranja. Međutim, pojedini podaci nisu adekvatno predstavljeni istim. Stoga, potrebno je bilo iskoristiti neke od drugih postojećih vokabulara i predstaviti problematične podatke njihovim elementima. Tako je atribut *createdate* predstavljen pomoću elementa *dc:created* vokabulara [Dublin Core - DC](http://dublincore.org/). Iz istog vokabulara su iskorišćeni elementi *dc:alternative*, date za mapiranje atributa handle i year, respektivno. Atribut weblink je predstavljen elementom *foaf:homepage* vokabulara [FOAF -Friend Of A Friend](http://www.foaf-project.org/).
# 3. Rešenje

U skladu sa postavljenim problemom i specifičnim zahtevima, kreirana je aplikacija koja omogućava kreiranje repozitorijuma transformacijom postojećih podataka. 
Prvi korak je transformisanje podataka iz XML formata u odgovarajuće RDF triplete, a na osnovu RDF vokabulara i modela sa slike 2. Podaci se potom smeštaju u lokalnu RDF bazu.Aplikacija omogućava pristup sačuvanim podacima korišćenjem RESTful servisa. Podržana su dva servisa, jedan koji prikazuje sve podatke vezane za određene CfP-ove i drugi koji prikazuje podatke samo o događajima koji prate CfP-ove. Specifikacija REST servisa data je u nastavku.

* **GET /api/cfp** - servis koji vraća podatke o pozivima za publikovanje radova	(CfP). Opcioni parametri su:
 * *abstractFrom* - donja granica datuma (roka) za predaju abstrakta
 * *abstractTo* - gornja granica datuma (roka) za predaju abstrakta
 * *fullSubmissionFrom* - donja granica datuma (roka) za predaju kompletnih 			  radova
 * *fullSubmissionTo* - gornja granica datuma (roka) za predaju kompletnih 			  radova * *finalDecisionFrom* - donja granica datuma (roka) za donošenje odluke o 			  prihvatanju rada * *finalDecisionTo* - gornja granica datuma (roka) za donošenje odluke o 			  prihvatanju rada * *startDateFrom* - donja granica datuma početka događaja * *startDateTo* - gornja granica datuma početka događaja	 * *endDateFrom* - donja granica datuma završetka događaja * *endDateTo* - gornja granica datuma završetka događaja * *location* - lokacija održavanja događaja * *name* - naziv događaja * *limit* - ograničenje broja vraćenih rezultata * *offset* - pomeraj

Primer poziva REST servisa:

> GET /api/cfp?abstractFrom=2010-02-01&abstractTo=2010-09-30 &location=Italy&startDateFrom=2010-09-01&limit=20

* **GET /api/cfp/events** - servis koji vraća podatke o događajima koji prate pozive za publikovanje radova. Opcioni parametri su: * *startDateFrom* - donja granica datuma početka događaja * *startDateTo* - gornja granica datuma početka događaja	 * *endDateFrom* - donja granica datuma završetka događaja * *endDateTo* - gornja granica datuma završetka događaja * *location* - lokacija održavanja događaja * *name* - naziv događaja * *limit* - ograničenje broja vraćenih rezultata * *offset* - pomeraj

Primer poziva REST servisa:> GET /api/cfp/events?location=Russia&startDateFrom=2010-06-01& startDateTo=2010-09-01&name=computer&limit=20

# 4. Tehnička realizacija
Aplikacija je rađena u programskom jeziku Java, u razvojnom okruženju Eclipse. Radi transformisanja izvornih podataka izvršeno je parsiranje XML dokumenta korišćenjem [dom4j  biblioteke](http://dom4j.sourceforge.net/). Ona omogućava laku manipulaciju XML, XPath i XSLT dokumentima i pruža punu podršku za DOM, SAX (korišćen u aplikaciji) i JAXP. Parsiranje se vrši tako što se iterira kroz dokument i za svaki element unutar korenog elementa proveravaju se vrednosti atributa njegove dece. Na osnovu tih atributa, postavljaju se vrednosti odgovorajaćih polja klasa.
U daljem radu korišćen je [Jena framework](http://jena.apache.org/), Java framework koji pruža skup alata i biblioteka za izradu aplikacija Semantičkog veba. U okviru tog skupa nalazi se API za čitanje, obradu i upisivanje RDF podataka u XML, N-triplet i Turtle formatu; mehanizme koji omogućavaju da se veliki broj RDF tripleta efikasno sačuva na disku kao i query engine koji je u skladu sa najnovijom SPARQL specifikacijom.
Za čitanje i upisivanje RDF podataka korišćena je [Jenabean  biblioteka](https://code.google.com/p/jenabean/). Ona rešava problem prevođenja RDF tripleta u Java objekte i obrnuto. Prevođenje se zasniva na anotacijama, koji mapiraju i povezuju polja Java klase i RDF propertije.
Za skladištenje podataka u RDF repozitorijum korišćena je [TDB](http://jena.apache.org/documentation/tdb/) komponenta Jena framework-a, koja, pored skladištenja, omogućava i izvršavanje različitih vrsta SPARQL upita nad podacima u RDF formatu. TDB omogućava i pojedine napredne mehanizme, poput transakcija, koji se mogu koristiti prilikom manipulacije podacima.
Pristup podacima je omogućen preko RESTful servisa, kao što je opisano u prethodnom poglavlju. Za rad sa servisima iz Java-e korišćen je [Jersey framework](https://jersey.java.net/) koji predstavlja implementaciju [JAX-RS](https://jax-rs-spec.java.net/), Java API-a za RESTful veb servise. JAX-RS koristi anotacije radi pojednostavljenja razvoja i isporuke servisa i endpoint-a. Na osnovu anotacija, određene klase su predstavljene kao resursi, koji sadrže anotacijama označene metode koje prihvataju i obrađuju HTTP zahteve (GET, POST...). Aplikacija na ovaj način isporučuje podatke (u JSON formatu) klijentima, na osnovu različitih parametara koje oni proslede preko HTTP zahteva, a u skladu sa specifikacijom servisa datom u prethodnom poglavlju.