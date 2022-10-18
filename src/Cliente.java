import java.time.LocalDate;

public abstract class Cliente {
	private String Nome;
	private LocalDate DataCadastro = LocalDate.now();
	private Endereço Endereço;
	
	public Cliente(LocalDate dataCadastro, Endereço endereço, String nome) {
		this.setDataCadastro(dataCadastro);
		this.setEndereço(endereço);
		this.setNome(nome);
	}

	public abstract String paraString();
	
	public LocalDate getDataCadastro() {
		return DataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.DataCadastro = dataCadastro;
	}
	public Endereço getEndereço() {
		return Endereço;
	}
	public void setEndereço(Endereço endereço) {
		Endereço = endereço;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
}
