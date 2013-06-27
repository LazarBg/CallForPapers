package rs.fon.is.cfp.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import rs.fon.is.cfp.domain.Thing;

public class URIGenerator {

	public static <T extends Thing> URI generate(T resource) throws URISyntaxException{
		String uri = Constants.NS + 
				resource.getClass().getSimpleName() + 
				"/" + UUID.randomUUID();
		return new URI(uri);
	}
}
