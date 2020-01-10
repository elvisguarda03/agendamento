package br.com.guacom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class AgendamentoEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email
	@NotBlank
	@Column
	private String email;
	
	@Column
	@NotBlank
	private String assunto;
	
	@Column
	@NotBlank
	private String mensagem;
	
	@Column
	private Boolean isSent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}
}
