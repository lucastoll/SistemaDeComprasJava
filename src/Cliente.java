import java.time.LocalDate;

public abstract class Cliente {
	private LocalDate DataCadastro = LocalDate.now();
	private Endereço Endereço;
	
	public Cliente(LocalDate dataCadastro, Endereço endereço) {
		this.setDataCadastro(dataCadastro);
		this.setEndereço(endereço);
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
	
	
}
