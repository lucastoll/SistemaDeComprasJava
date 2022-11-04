import java.util.ArrayList;
import javax.swing.JOptionPane;

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


	/*public void setVecCompras(ArrayList<Compras> vecCompras) {
		this.vecCompras = vecCompras;
	}

	public void cadastraCompra (ArrayList<PessoaFisica>vecPessoaFisica, ArrayList<PessoaJuridica>vecPessoaJuridica,ArrayList<Produtos>vecProdutos, ArrayList<Pereciveis>vecPereciveis) {
	int controlador =0;
	do {
		controlador = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 - Pessoa fisíca\n"
				+ "2 - Pessoa jurídica",
	            "Detalhes Compra - tipo pessoa",
	            JOptionPane.INFORMATION_MESSAGE));
	}while (controlador <1 || controlador > 2);

	String nomeEscolhidoCliente="", nomeProduto="", cpf="", cnpj="",nomesCadastrados="", produtosCadastrados="";
	int identificador=0, quantidade=0;
	float precoUnidade=0, precoTotal=0;

	if (controlador == 1) {	
		
		for(PessoaFisica pessoa: vecPessoaFisica) {	
			nomesCadastrados += pessoa.getNome() + "\n";	
		}
		nomeEscolhidoCliente = JOptionPane.showInputDialog(null, nomesCadastrados,"Qual cliente esta comprando? ", JOptionPane.INFORMATION_MESSAGE);
		identificador = Integer.parseInt(JOptionPane.showInputDialog("Digite o identificador: "));
		produtosCadastrados += "NÃO PERECIVEIS \n"; 
		for(Produtos produto: vecProdutos) {	
			produtosCadastrados += produto.getNomeproduto() + "\n";	
		}
		produtosCadastrados += "PERECIVEIS \n"; 
		for(Pereciveis produto: vecPereciveis) {	
			produtosCadastrados += produto.getNomeproduto()+ "\n";	
		}
		nomeProduto = JOptionPane.showInputDialog(null, produtosCadastrados,"Qual produto deseja? ", JOptionPane.INFORMATION_MESSAGE);
		quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade: "));
		}else if(controlador ==2) {
			for(PessoaJuridica pessoa: vecPessoaJuridica) {	
			nomesCadastrados += pessoa.getNome() + "\n";	
			}
			nomeEscolhidoCliente = JOptionPane.showInputDialog(null, nomesCadastrados,"Qual cliente esta comprando? ", JOptionPane.INFORMATION_MESSAGE);
		}

	}*/
	}


