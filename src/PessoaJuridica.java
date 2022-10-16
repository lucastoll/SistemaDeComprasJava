import java.time.LocalDate;

public class PessoaJuridica extends Cliente{
	private String CNPJ;
	private String RazaoSocial;
	private int PrazoMaximoPagamentoEmDias;
	
	public PessoaJuridica(LocalDate dataCadastro, Endereço endereço, String cnpj, String razaoSocial, int prazoMaximoPagamentoEmDias){
		super(dataCadastro, endereço);
		this.setCNPJ(cnpj);
		this.setRazaoSocial(razaoSocial);
		this.setPrazoMaximoPagamentoEmDias(prazoMaximoPagamentoEmDias);
	}
	
	public String paraString() {
		String infos = "";
		infos += this.getEndereço().paraString();
		infos += "\nData de cadastro: " + this.getDataCadastro();
		infos += "\nCNPJ: " + this.getCNPJ();
		infos += "\nRazão Social: " + this.getRazaoSocial();
		infos += "\nPrazo maximo em dias para o pagamento da compra: " + this.getPrazoMaximoPagamentoEmDias();
		
		return infos;
	}

	//CNPJ
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	//Razao Social
	public String getRazaoSocial() {
		return RazaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}
	//Prazo Maximo Pagamento em dias
	public int getPrazoMaximoPagamentoEmDias() {
		return PrazoMaximoPagamentoEmDias;
	}
	public void setPrazoMaximoPagamentoEmDias(int prazoMaximoPagamentoEmDias) {
		PrazoMaximoPagamentoEmDias = prazoMaximoPagamentoEmDias;
	}

}
