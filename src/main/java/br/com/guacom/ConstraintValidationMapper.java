package br.com.guacom;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.guacom.dto.MensagemErroDto;

@Provider
public class ConstraintValidationMapper implements ExceptionMapper<ConstraintViolationException> {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		logger.info(exception.getMessage());
		
		return Response
				.status(Status.BAD_REQUEST)
				.entity(MensagemErroDto.Builder.builder()
							.mensagens(exception
									.getConstraintViolations()
									.stream()
									.map(ConstraintViolation::getMessage)
									.collect(Collectors.toList()))
							.build())
				.build();
	}
}
