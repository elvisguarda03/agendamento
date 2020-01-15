package br.com.guacom.dto;

import java.util.Date;
import java.util.List;

public class MensagemErroDto {
	private List<String> mensagens;
	private Date dataErro;
	
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	public Date getDataErro() {
		return dataErro;
	}
	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}
	
	public final static class Builder {
		private List<String> mensagens;
		private Date dataErro;
		
		public static Builder builder() {
			return new Builder();
		}
		
		public Builder mensagens(List<String> mensagens) {
			this.mensagens = mensagens;
			return this;
		}
		
		public Builder dataErro() {
			this.dataErro = new Date();
			return this;
		}
		
		public MensagemErroDto build() {
			MensagemErroDto mensagemErro = new MensagemErroDto();
			mensagemErro.setMensagens(mensagens);
			mensagemErro.setDataErro(new Date());
		
			return mensagemErro;
		}
	}
	
//	public static MensagemErroDto build(List<String> mensagens) {
//		MensagemErroDto mensagemErro = new MensagemErroDto();
//		mensagemErro.setMensagens(mensagens);
//		mensagemErro.setDataErro(new Date());
//	
//		return mensagemErro;
//	}
}