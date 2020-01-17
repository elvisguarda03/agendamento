package br.com.guacom.interception;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

//Marcando a classe como interceptadora e definindo a ordem de execução.
@Interceptor
@Priority(1)
@Logger
public class LoggerInterceptor {

	@AroundInvoke
	public Object process(InvocationContext context) throws Exception {
		final java.util.logging.Logger logger = java.util.logging.Logger
				.getLogger(context.getTarget().getClass().getName());

		try {
			logger.info("Invocando: " + context.getMethod());

			return context.proceed();
		} catch (Exception e) {
			treatException(logger, e);
			throw e;
		}
	}

	public void treatException(java.util.logging.Logger logger, Exception e) {
		if (e instanceof ConstraintViolationException) {
			logger.info(e.getMessage());
		} else {
			logger.severe(e.getMessage());
		}
	}
}