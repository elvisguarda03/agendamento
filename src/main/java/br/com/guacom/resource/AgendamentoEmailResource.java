package br.com.guacom.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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
	public Response saveAgendamentoEmail(AgendamentoEmail agendamentoEmail) throws BusinessException {
		business.save(agendamentoEmail);
		
		URI location = UriBuilder.fromResource(getClass()).path("/{id}")
				.build(agendamentoEmail.getId());
		
		return Response
				.created(location)
				.entity(agendamentoEmail)
				.build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(APPLICATION_JSON)
	public Response findById(@PathParam("id") Integer id) {
		return Response.ok(business.findById(id))
				.build();
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	public Response update(AgendamentoEmail agendamentoEmail) {
		business.update(agendamentoEmail);
		
		return Response.ok()
				.entity(agendamentoEmail)
				.build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		business.deleteById(id);
		
		return Response.noContent()
				.build();
	}
}