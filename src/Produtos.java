public class Produtos {
	private int codigo;
	private String nomeproduto, descricao;
	private float preco;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Produtos(int codigo, String nomeproduto, String descricao, float preco) {
		this.codigo = codigo;
		this.nomeproduto = nomeproduto;
		this.descricao = descricao;
		this.preco = preco;
	}
	public String paraString() {
		String info="";
		info += "\n"+ codigo + "\n";
		info += nomeproduto + "\n";
		info+= descricao + "\n";
		info += preco + "\n";
		return info;
	}
}

