package br.com.guacom;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class AgendamentoEmailTimer {
	private final static Logger logger = Logger.getLogger(AgendamentoEmailTimer.class.getName());
	
	@Schedule(hour="*", minute="*")
	public void enviarEmailAgendados() {
		logger.info("Enviou o email");
	}
}
