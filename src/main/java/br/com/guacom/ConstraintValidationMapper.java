package br.com.guacom;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.guacom.dto.MensagemErroDto;

@Provider
public class ConstraintValidationMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		return Response
				.status(Status.BAD_REQUEST)
				.entity(MensagemErroDto
							.build(exception
								.getConstraintViolations()
								.stream()
								.map(c -> c.getMessage())
								.collect(Collectors.toList())))
				.build();
	}

}
