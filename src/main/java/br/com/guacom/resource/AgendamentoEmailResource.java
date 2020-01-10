package br.com.guacom.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.guacom.business.AgendamentoEmailBusiness;
import br.com.guacom.entity.AgendamentoEmail;

@Path("/agendamentoemail")
public class AgendamentoEmailResource {
	@Inject
	private AgendamentoEmailBusiness business; 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentosEmail() {
		return Response
				.ok(business.findAll())
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveEmail(AgendamentoEmail agendamentoEmail) {
		business.save(agendamentoEmail);
	
		return Response
				.status(201)
				.build();
	}
}
