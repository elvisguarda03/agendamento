package br.com.guacom.dao;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.guacom.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {
// 1 - ADICIONAR O JAR A PASTA MODULES - module add --name=com.mysql --resources=/path/to/mysql-connector-java-8.0.15.jar --dependencies=javax.api,javax.transaction.api
// 2 - DEFININDO O MODULE COMO DRIVER - /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
// 3 - DEFININDO DATASOURCE - data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=SEU-USUARIO --password=SUA-SENHA --min-pool-size=10 --max-pool-size=20
	
	//Indicando para o CDI o ponto de injeção
	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	public List<AgendamentoEmail> findAll() {
		List<AgendamentoEmail> agendamentos =  entityManager
				.createQuery("FROM AgendamentoEmail", AgendamentoEmail.class)
				.getResultList();
		
		logger.info("Agendamentos encontrados: " + agendamentos.size());
		
		return agendamentos;
	}
	
	public Boolean existsByEmail(String email) {
		Query query = entityManager.createQuery("FROM AgendamentoEmail a where a.email =: email AND a.isSent = false");
		query.setParameter("email", email);
		
		return query.getResultList()
				.isEmpty() == false;
	}
	
	public List<AgendamentoEmail> findByIsSent() {
		return entityManager.createQuery("FROM AgendamentoEmail", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void save(AgendamentoEmail agendamentoEmail) {
		if (Objects.isNull(agendamentoEmail.getId())) {
			entityManager.persist(agendamentoEmail);
		
			return;
		}
		
		entityManager.merge(agendamentoEmail);
	}
}