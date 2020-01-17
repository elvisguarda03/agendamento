package br.com.guacom.timer;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.guacom.business.AgendamentoEmailBusiness;
import br.com.guacom.entity.AgendamentoEmail;
import br.com.guacom.interception.Logger;

//Ao anotar uma classe que é um EJB Timer, o contexto 
//Java EE controla o processamento para que não haja dois processamentos em paralelo.

//Criando um EJB
@Singleton
@Logger
public class AgendamentoEmailTimer {
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Schedule(hour = "*", minute = "*")
	public void enviarEmailAgendados() {
		List<AgendamentoEmail> agendamentosEmail = agendamentoEmailBusiness.findByIsSent();
		agendamentosEmail.forEach(a -> {
			context.createProducer().send(queue, a);
			agendamentoEmailBusiness.update(a);
		});
	}
}
