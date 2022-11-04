import java.time.LocalDate;

public class PessoaFisica extends Cliente{
	private String CPF;
	private int maximoDeParcelas;
	public PessoaFisica(LocalDate dataCadastro, Endereço endereço, String nome, String cpf, int maximoDeParcelas) {
		super(dataCadastro, endereço, nome);
		this.setCPF(cpf);
		this.setMaximoDeParcelas(maximoDeParcelas);
	}
	@Override
	public String paraString() {
		String infos = "\n\n";
		infos += this.getEndereço().paraString();
		infos += "\nNome: " + this.getNome();
		infos += "\nData de cadastro: " + this.getDataCadastro();
		infos += "\nCPF: " + this.getCPF();
		infos += "\nMaximo de parcelas permitidas na compra: " + this.getMaximoDeParcelas();
		
		return infos;
	}
	//maximoDeParcelas
	public int getMaximoDeParcelas() {
		return maximoDeParcelas;
	}
	public void setMaximoDeParcelas(int maximoDeParcelas) {
		this.maximoDeParcelas = maximoDeParcelas;
	}
	//CPF
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cpf) {
		CPF = cpf;
	}

}
