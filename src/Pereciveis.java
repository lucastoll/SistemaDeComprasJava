import java.time.LocalDate;
import java.time.*;

public class Pereciveis extends Produtos {
	private LocalDate datadevalidade;
	
	public Pereciveis(int codigo, String nomeproduto, String descricao, float preco, LocalDate datadevalidade) {
		super(codigo, nomeproduto, descricao, preco);
        this.datadevalidade = datadevalidade;
	}
	
	public LocalDate getDatadevalidade() {
		return datadevalidade;
	}
	public void setDatadevalidade(LocalDate datadevalidade) {
		this.datadevalidade = datadevalidade;
	}
	
	public boolean vencimento(){
		LocalDate hoje = LocalDate.now();
		boolean estavencido;
		if (hoje.isBefore(datadevalidade)){
			estavencido = false;
		}else{
			estavencido = true;
		}
		return estavencido;
	}
	public String paraString() {
		String info="";
		info += "Codigo: " + getCodigo() + "\n";
		info += "Nome do produto: " +  getNomeproduto() + "\n";
		info += "Descrição do produto: " + getDescricao() + "\n";
		info += "Preço R$: " + getPreco() + "\n";
		info +=  "Data de validade: "+ datadevalidade + "\n" ;
		return info;
	}
}

	

