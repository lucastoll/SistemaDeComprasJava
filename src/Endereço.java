public class Endereço {
	private String Rua;
	private int Numero;
	private String Bairro;
	private String CEP;
	private String Cidade;
	private String Estado;
	
	public Endereço(String rua, int numero, String bairro, String cep, String cidade, String estado) {
		this.setRua(rua);
		this.setNumero(numero);
		this.setBairro(bairro);
		this.setCEP(cep);
		this.setCidade(cidade);
		this.setEstado(estado);
	}
	
	public String paraString() {
		String infos = "";
		infos += "\nRua: " + this.getRua();
		infos += "\nNumero: " + this.getNumero();
		infos += "\nBairro: " + this.getBairro();
		infos += "\nCEP: " + this.getCEP();
		infos += "\nCidade: " + this.getCidade();
		infos += "\nEstado: " + this.getEstado();

		return infos;
	}
	//rua
	public String getRua() {
		return Rua;
	}
	public void setRua(String rua) {
		Rua = rua;
	}
	//numero
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	//bairro
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	//cep
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cep) {
		CEP = cep;
	}
	//cidade
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	//estado
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
}
