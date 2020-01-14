package br.com.guacom.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.guacom.business.AgendamentoEmailBusiness;
import br.com.guacom.entity.AgendamentoEmail;
import br.com.guacom.exception.BusinessException;

@Path("/agendamentoemail")
public class AgendamentoEmailResource {
	@Inject
	private AgendamentoEmailBusiness business;

	@GET
	@Produces(APPLICATION_JSON)
	public Response listarAgendamentosEmail() throws BusinessException {
		return Response
				.ok(business.findAll())
				.build();
	}

	@POST
	@Produces(APPLICATION_JSON)
	public Response saveEmail(AgendamentoEmail agendamentoEmail) throws BusinessException {
		business.save(agendamentoEmail);
		
		return Response
				.status(201)
				.build();
	}
}