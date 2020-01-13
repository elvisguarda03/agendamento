package br.com.guacom.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.guacom.dao.AgendamentoEmailDao;
import br.com.guacom.entity.AgendamentoEmail;
import br.com.guacom.exception.BusinessException;

@Stateless
public class AgendamentoEmailBusiness {
//	private Logger logger = Logger.getLogger(AgendamentoEmailBusiness.class.getName());
	
	//Indicando para o CDI o ponto de injeção
	@Inject
	private AgendamentoEmailDao dao;
	
	public List<AgendamentoEmail> findAll() {
		List<AgendamentoEmail> agendamentos = dao.findAll();
		
//		logger.info("Listagem de agendamentos");
		
		return agendamentos;
	}
	
	public void save(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		if (dao.existsByEmail(agendamentoEmail.getEmail())) {
			throw new BusinessException("E-mail existente na base de dados.");
		}
		
		agendamentoEmail.setIsSent(false);
		dao.save(agendamentoEmail);
		
//		logger.info("Agendamento para o e-mail " + agendamentoEmail.getEmail() + " salvo");
	}
}
