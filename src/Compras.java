import java.time.LocalDate;
import java.util.ArrayList;

public class Compras {
	private String nomeCliente, identidade;
	private int identificador;
	private LocalDate dataCompra;
	private double valorTotalCompra, valorTotalPago;
	private ArrayList <ItensCompra>vecItensCompra;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	public double getValorTotalPago() {
		return valorTotalPago;
	}
	public void setValorTotalPago(double valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}
	public double getValorTotalCompra() {
		return valorTotalCompra;
	}
	public void setValorTotalCompra(double valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public ArrayList <ItensCompra> getVecItensCompra() {
		return vecItensCompra;
	}
	public void setVecItensCompra(ArrayList <ItensCompra> vecItensCompra) {
		this.vecItensCompra = vecItensCompra;
	}

	public Compras(String nomeCliente,String identidade ,int identificador, double valorTotalCompra, double valorTotalPago, LocalDate dataCompra, ArrayList<ItensCompra>vecItensCompra) {
		this.nomeCliente = nomeCliente;
		this.identidade = identidade;
		this.identificador = identificador;
		this.valorTotalCompra = valorTotalCompra;
		this.valorTotalPago = valorTotalPago;
		this.setDataCompra(dataCompra);
		this.setVecItensCompra(vecItensCompra);
	}
	
	public String paraString() {
		String infos = "";
		
		infos += "Nome do cliente: " + this.getNomeCliente() + "\n";
		infos += "ID da compra: " + this.getIdentificador() + "\n";
		infos += "Documento de identidade do cliente: " + this.getIdentidade() + "\n";
		infos += "Valor total da compra: " + this.getValorTotalCompra() + "\n";
		infos += "Valor total pago: " + this.getValorTotalPago() + "\n";
		infos += "Data da compra: " + this.getDataCompra() + "\n";
		infos += "\nItens da compra\n";
		for(int i = 0; i < this.getVecItensCompra().size(); i++) {
			infos += this.getVecItensCompra().get(i).paraString();
		}
		
		return infos;
	}
}