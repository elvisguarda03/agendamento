package br.com.guacom.business;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import br.com.guacom.dao.AgendamentoEmailDao;
import br.com.guacom.entity.AgendamentoEmail;
import br.com.guacom.exception.BusinessException;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class AgendamentoEmailBusiness {
//	private Logger logger = Logger.getLogger(AgendamentoEmailBusiness.class.getName());
	
	//Indicando para o CDI o ponto de injeção
	@Inject
	private AgendamentoEmailDao dao;
	
	//O JNDI é utilizado para referenciar recursos dentro do contexto Java EE.
	
	//Injentando o recurso
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session session;
	
	private final static String EMAIL_FROM = "mail.address";
	private final static String EMAIL_USER = "mail.smtp.user";
	private final static String EMAIL_PASSWORD = "mail.smtp.pass";
	
//	1 - /subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)
//	2 - /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
//	3 - /subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=bc82647d48b758, password=6320632ea13bd1, tls=true)
	
	public List<AgendamentoEmail> findAll() {
		List<AgendamentoEmail> agendamentos = dao.findAll();
		
//		logger.info("Listagem de agendamentos");
		
		return agendamentos;
	}
	
	public List<AgendamentoEmail> findByIsSent() {
		return dao.findByIsSent();
	}
	
	//Abrindo uma nova transação para essa operação.
	
//	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		if (dao.existsByEmail(agendamentoEmail.getEmail())) {
			throw new BusinessException("E-mail existente na base de dados.");
		}
		
		agendamentoEmail.setIsSent(false);
		dao.save(agendamentoEmail);
		
//		logger.info("Agendamento para o e-mail " + agendamentoEmail.getEmail() + " salvo");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void update(@Valid AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setIsSent(true);
		dao.save(agendamentoEmail);
	}
	
	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(session.getProperty(EMAIL_FROM));
			message.setRecipients(RecipientType.TO, agendamentoEmail.getEmail());
			message.setSubject(agendamentoEmail.getAssunto());
			message.setText(Optional.ofNullable(agendamentoEmail.getMensagem())
					.orElse(""));
		
			Transport.send(message, 
					session.getProperty(EMAIL_USER), 
					session.getProperty(EMAIL_PASSWORD));
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}

	public AgendamentoEmail findById(Integer id) {
		return dao.findById(id);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}