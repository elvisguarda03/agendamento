package br.com.guacom;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.guacom.business.AgendamentoEmailBusiness;
import br.com.guacom.entity.AgendamentoEmail;
import br.com.guacom.interception.Logger;


@Logger
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", 
				propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", 
		propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Override
	public void onMessage(Message message) {
		try {
			AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			agendamentoEmailBusiness.enviarEmail(agendamentoEmail);
		} catch (JMSException jmse) {
			throw new RuntimeException(jmse);
		}
	}
}
