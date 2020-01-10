package br.com.guacom.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.guacom.dao.AgendamentoEmailDao;
import br.com.guacom.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailBusiness {
	//Indicando para o CDI o ponto de injeção
	
	@Inject
	private AgendamentoEmailDao dao;
	
	public List<AgendamentoEmail> findAll() {
		return dao.findAll();
	}
	
	public void save(@Valid AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setIsSent(false);
		dao.save(agendamentoEmail);
	}
}
