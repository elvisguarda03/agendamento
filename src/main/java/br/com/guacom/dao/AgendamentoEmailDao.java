package br.com.guacom.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guacom.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {
	
// 1 - ADICIONAR O JAR A PASTA MODULES - module add --name=com.mysql --resources=/path/to/mysql-connector-java-8.0.15.jar --dependencies=javax.api,javax.transaction.api
// 2 - DEFININDO O MODULE COMO DRIVER - /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
// 3 - DEFININDO DATASOURCE - data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=SEU-USUARIO --password=SUA-SENHA --min-pool-size=10 --max-pool-size=20
	
	//Indicando para o CDI o ponto de injeção
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> findAll() {
		return entityManager
				.createQuery("select * from agendamentoemail", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void save(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
}