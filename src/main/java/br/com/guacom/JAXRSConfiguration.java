package br.com.guacom;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * @author rhuanrocha
 */

@ApplicationPath("/resources")
public class JAXRSConfiguration extends Application {

//	@Override
//	public Set<Class<?>> getClasses() {
//		Set<Class<?>> classes = new HashSet<Class<?>>();
//		classes.add(AgendamentoEmailResource.class);
//		
//		return classes;
//	}
}