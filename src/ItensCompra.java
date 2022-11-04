public class ItensCompra {
	private int quantidade=0;
	private String nomeProduto="";
	private float precoUnitario=0, valorTotal=0;
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public ItensCompra (String nomeProduto, int quantidade, float precoUnitario, float valorTotal) {
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.valorTotal = valorTotal;
	}
}


