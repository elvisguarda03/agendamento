package br.com.guacom;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.guacom.dto.MensagemErroDto;
import br.com.guacom.exception.BusinessException;
import br.com.guacom.interception.Logger;

@Logger
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
//	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public Response toResponse(BusinessException exception) {
//		logger.info(exception.getMessage());
		
		return Response.status(BAD_REQUEST)
				.entity(MensagemErroDto.Builder.builder()
						.mensagens(exception.getMessages())
						.build())
				.build();
	}
}