
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciaCompra {
private ArrayList <Compras>vecCompra;
public ArrayList<Compras> getVecCompra() {
	return vecCompra;
}
public void setVecCompra(ArrayList<Compras> vecCompra) {
	this.vecCompra = vecCompra;
}
public GerenciaCompra() {
	ArrayList<Compras>vecCompra = new ArrayList<Compras>();
	setVecCompra(vecCompra);
}

public void leCompra() throws IOException {
	this.vecCompra.clear();
	String nomeCliente="", identidade="", nomeProduto="",linha2="";
	int identificador=0,quantidade=0;
	float valorTotalCompra=0, valorUnitario=0, valorTotalProduto=0;
	
	ArrayList<ItensCompra>vecItensCompra= new ArrayList<ItensCompra>();
	LocalDate data = null;
	 File arquivoCompra = new File("./baseDados/compras.txt");
	if(arquivoCompra.createNewFile()) {
		  System.out.println("Arquivo criado: " + arquivoCompra.getName());
	} 
	else {
		  System.out.println("Arquivo já existe.");
	}
		FileReader fr = new FileReader(arquivoCompra);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String linha1 = br.readLine();		
			if(linha1.equals("InicioCompra")) {
			System.out.println("To aqui");
			nomeCliente = br.readLine();
			identidade = br.readLine();
			identificador = Integer.parseInt(br.readLine());
			valorTotalCompra = Float.parseFloat(br.readLine());
			data = LocalDate.parse(br.readLine());
			linha2=br.readLine();
			do {
				nomeProduto= br.readLine();
				quantidade=Integer.parseInt(br.readLine());
				valorUnitario = Float.parseFloat(br.readLine());
				valorTotalProduto = Float.parseFloat(br.readLine());
				ItensCompra itemCompra = new ItensCompra(nomeProduto, quantidade, valorUnitario, valorTotalProduto);
				vecItensCompra.add(itemCompra);
				linha2=br.readLine();
				if(linha2 == null) {
					linha2 = "";
				}
			}while(linha2.equals("ItemCompra"));
			
			}
		Compras compra = new Compras(nomeCliente, identidade, identificador,valorTotalCompra,data, vecItensCompra);
		getVecCompra().add(compra);
		}
		br.close();
		fr.close();		
}

public void cadastraCompra(ArrayList<PessoaFisica>vecPessoaFisica, ArrayList<PessoaJuridica>vecPessoaJuridica, ArrayList<Produtos>vecProdutos,ArrayList<Pereciveis>vecPereciveis) throws IOException{
	int controlador =0, quantidade=0, proximaCompra=0, identificador =0;
	String nomesCadastrados = "", nomeEscolhidoCliente="", nomeEscolhidoProduto="", produtosCadastrados ="", cpf="", cnpj="";
	boolean permissaoParaContinuar=true;
	float precoUnitario=0, precoTotal =0, precoTotalCompra=0;
	LocalDate data = LocalDate.now();
	ArrayList <ItensCompra>vecItensCompra= new ArrayList<ItensCompra>();
	do {
		controlador = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 - Pessoa fisíca\n"
				+ "2 - Pessoa jurídica",
	            "Detalhes Compra - tipo pessoa",
	            JOptionPane.INFORMATION_MESSAGE));
	}while (controlador <1 || controlador > 2);
if (controlador == 1) {	
		for(PessoaFisica pessoa: vecPessoaFisica) {	
			nomesCadastrados += pessoa.getNome() + "\n";
		}
			do {
				 nomeEscolhidoCliente = JOptionPane.showInputDialog(null, nomesCadastrados,"Qual cliente esta comprando? ", JOptionPane.INFORMATION_MESSAGE);
				 for (PessoaFisica pessoa1: vecPessoaFisica) {
					 if(pessoa1.getNome().equals(nomeEscolhidoCliente)) {
							permissaoParaContinuar = false;
						}
				 }
				 if (permissaoParaContinuar==true) {
				JOptionPane.showMessageDialog(null, "Nome não encontrado no sistema, por favor digite novamente: ", "Erro", JOptionPane.ERROR_MESSAGE);
				 }
			}while (permissaoParaContinuar==true);

} else if (controlador ==2) {
	for(PessoaJuridica pessoa: vecPessoaJuridica) {	
		nomesCadastrados += pessoa.getNome() + "\n";
	}
		do {
			 nomeEscolhidoCliente = JOptionPane.showInputDialog(null, nomesCadastrados,"Qual cliente esta comprando? ", JOptionPane.INFORMATION_MESSAGE);
			 for (PessoaJuridica pessoa1: vecPessoaJuridica) {
				 if(pessoa1.getNome().equals(nomeEscolhidoCliente)) {
						permissaoParaContinuar = false;
					}
			 }
			 if (permissaoParaContinuar==true) {
			JOptionPane.showMessageDialog(null, "Nome não encontrado no sistema, por favor digite novamente: ", "Erro", JOptionPane.ERROR_MESSAGE);
			 }
		}while (permissaoParaContinuar==true);

}
		do {
		do {
			permissaoParaContinuar=true;
			produtosCadastrados += "NÃO PERECIVEIS \n"; 
			for(Produtos produto: vecProdutos) {	
				produtosCadastrados += produto.getNomeproduto() + "\n";	
			}
			produtosCadastrados += "PERECIVEIS \n"; 
			for(Pereciveis produto: vecPereciveis) {	
				produtosCadastrados += produto.getNomeproduto()+ "\n";	
			}
			 nomeEscolhidoProduto = JOptionPane.showInputDialog(null, produtosCadastrados,"Qual produto deseja comprar? ", JOptionPane.INFORMATION_MESSAGE);
			 for (Produtos produto: vecProdutos) {
				 if(produto.getNomeproduto().equals(nomeEscolhidoProduto)) {
						permissaoParaContinuar = false;
					}
			 }
			 for (Pereciveis produto: vecPereciveis) {
				 if(produto.getNomeproduto().equals(nomeEscolhidoProduto)) {
						permissaoParaContinuar = false;
					}
			 }
			 if (permissaoParaContinuar==true) {
				 JOptionPane.showMessageDialog(null, "Produto não encontrado no sistema, por favor digite novamente: ", "Erro", JOptionPane.ERROR_MESSAGE);
			 }
			 produtosCadastrados="";
		}while (permissaoParaContinuar==true);

		quantidade = Integer.parseInt(JOptionPane.showInputDialog(null,"Quantidade desejada? ", JOptionPane.INFORMATION_MESSAGE));
		for (Produtos produto: vecProdutos) {
			 if(produto.getNomeproduto().equals(nomeEscolhidoProduto)) {
					precoUnitario = produto.getPreco();
				}
		}
		for (Pereciveis produto: vecPereciveis) {
			 if(produto.getNomeproduto().equals(nomeEscolhidoProduto)) {
					precoUnitario = produto.getPreco();
				}
		}
		precoTotal = precoUnitario*quantidade;
		ItensCompra itemCompra = new ItensCompra(nomeEscolhidoProduto,quantidade,precoUnitario,precoTotal);
		vecItensCompra.add(itemCompra);
		do {
			proximaCompra = Integer.parseInt(JOptionPane.showInputDialog(null,
					"1 - Sim\n"
					+ "2 - Não",
			        "Detalhes Compra - deseja continuar?",
			        JOptionPane.INFORMATION_MESSAGE));
		}while (proximaCompra <1 || proximaCompra > 2);
		}while(proximaCompra==1);
		boolean mesmoIdentificador = false;
		do {
			identificador = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o identificador da compra: ", JOptionPane.INFORMATION_MESSAGE));
			 for (Compras compra: this.vecCompra) {
				 if(compra.getIdentificador()==identificador) {
						mesmoIdentificador = false;
					}
			 }
			 if (mesmoIdentificador==true) {
				 JOptionPane.showMessageDialog(null, "Identificador ja existente, por favor, digite um novo: ", "Erro", JOptionPane.ERROR_MESSAGE);
			 }
		}while(mesmoIdentificador==true);
		if(controlador==1) {
			 for (PessoaFisica pessoa: vecPessoaFisica) {
				 if(pessoa.getNome().equals(nomeEscolhidoCliente)) {
						cpf = pessoa.getCPF();
					}
			}
		}else if (controlador==2) {
			 for (PessoaJuridica pessoa: vecPessoaJuridica) {
				 if(pessoa.getNome().equals(nomeEscolhidoCliente)) {
						cnpj = pessoa.getCNPJ();
					}
			}
		}
			 for (ItensCompra compra: vecItensCompra) {
				 precoTotalCompra += compra.getValorTotal();
			 }
			 File arquivoCompra = new File("./baseDados/compras.txt");
				if(arquivoCompra.createNewFile()) {
					  System.out.println("Arquivo criado: " + arquivoCompra.getName());
					} 
					else {
					  System.out.println("Arquivo já existe.");
					}
			FileWriter fileWriter = new FileWriter(arquivoCompra, true);
			if(controlador==1) {
				fileWriter.write("InicioCompra\n");
				fileWriter.write(nomeEscolhidoCliente +"\n");
				fileWriter.write(cpf +"\n");
				fileWriter.write(identificador +"\n");
				fileWriter.write(precoTotalCompra +"\n");
				fileWriter.write(data +"\n");
				for (ItensCompra compra: vecItensCompra) {
					fileWriter.write("ItemCompra\n");
					fileWriter.write(compra.getNomeProduto() +"\n");
					fileWriter.write(compra.getQuantidade() +"\n");
					fileWriter.write(compra.getPrecoUnitario()+ "\n");
					fileWriter.write(compra.getValorTotal()+ "\n");
				}				
			}
			else if (controlador ==2) {
				fileWriter.write("InicioCompra\n");
				fileWriter.write(nomeEscolhidoCliente +"\n");
				fileWriter.write(cnpj +"\n");
				fileWriter.write(identificador +"\n");
				fileWriter.write(precoTotalCompra +"\n");
				fileWriter.write(data +"\n");
				for (ItensCompra compra: vecItensCompra) {
					fileWriter.write("ItemCompra\n");
					fileWriter.write(compra.getNomeProduto() +"\n");
					fileWriter.write(compra.getQuantidade() +"\n");
					fileWriter.write(compra.getPrecoUnitario()+ "\n");
					fileWriter.write(compra.getValorTotal()+ "\n");
				}
			}
			fileWriter.write("\n");
			fileWriter.close();
}

public void mostraCompra() {
	String nomes="";
	for (Compras compra: this.vecCompra) {
		nomes += compra.getNomeCliente() + "\n";
	}
	
	JOptionPane.showMessageDialog(null, nomes, "TODOS OS NOMES DOS CLIENTES", JOptionPane.ERROR_MESSAGE);
}
}



