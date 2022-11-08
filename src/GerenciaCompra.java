
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciaCompra {
private ArrayList <Compras> vecCompra;
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

public void efetuaPagamento(String id) throws IOException {
	String infos = "";
	String pagamento = "";
	
	for(Compras compra: this.vecCompra) {
		if(compra.getIdentificador() ==  Integer.parseInt(id)) {
			infos = "Quanto você deseja pagar?\n" + "Valor total compra: " + compra.getValorTotalCompra() + " | " + "Valor total pago: " + compra.getValorTotalPago();
			
			do {
				pagamento = JOptionPane.showInputDialog(null, infos, JOptionPane.QUESTION_MESSAGE);
			}while(Double.parseDouble(pagamento) <= 0 || Double.parseDouble(pagamento) > compra.getValorTotalCompra() - compra.getValorTotalPago());
			
			alteraTotalPagoNoArquivo(id, pagamento, compra.getValorTotalPago());
		}
	}
}

public void alteraTotalPagoNoArquivo(String id, String pagamento, double valorTotalPago) throws IOException{
	// Abre o arquivo original e cria um arquivo temporario
	File arquivoOriginal = new File("./baseDados/compras.txt");
	File arquivoTemporario = new File("./baseDados/comprasTemp.txt");
	arquivoTemporario.createNewFile();
	// Declara um reader para o arquivo original e um writer para o temporario
    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));
	FileWriter fileWriter = new FileWriter(arquivoTemporario, false);
	
	String linha = "";
	
	
	// este while tem como intuito reescrever todas as linhas do arquivo original no temporario
	// com exceção da linha do valorTotalPago da compra que está sendo alterada
	while(bufferedReader.ready()) {
		linha = bufferedReader.readLine();
		if(linha.equals(id)) {	
			// Se a linha for igual ao ID, ler informações até chegar no Total pago
			// pois abaixo do total pago esta a informação que queremos substituir com esta função
			while(!linha.equals("Total pago")) {
				fileWriter.write(linha + "\n");	
				linha = bufferedReader.readLine();
			}
			//Escreve o valor novo e pula uma linha, para o arquivo continuar sendo reescrito porém agora com a linha nova que foi definida no lugar do antigo valorTotalPago
			fileWriter.write("Total pago\n");
			fileWriter.write((Double.parseDouble(pagamento) + valorTotalPago) + "\n");
			bufferedReader.readLine();

			continue;
		}
		else { 
			fileWriter.write(linha + "\n");	
		}
	}
	fileWriter.close(); 
	bufferedReader.close(); 
	
  //Deleta o arquivo original
  if (!arquivoOriginal.delete()) {
    System.out.println("Não foi possível deletar o arquivo");
    return;
  }
  //Renomeia o arquivo temporario para o nome do arquivo original
  if (!arquivoTemporario.renameTo(arquivoOriginal))
    System.out.println("Não foi possível renomear o arquivo");
}


public void leCompra() throws IOException {
	this.vecCompra.clear();
	String nomeCliente="", identidade="", nomeProduto="",linha2="";
	int identificador=0,quantidade=0;
	float valorTotalCompra=0, valorUnitario=0, valorTotalProduto=0, valorTotalPago=0;
	
	ArrayList<ItensCompra>vecItensCompra= new ArrayList<ItensCompra>();
	LocalDate data = null;
	 File arquivoCompra = new File("./baseDados/compras.txt");
	if(arquivoCompra.createNewFile()) {
		  System.out.println("Arquivo criado: " + arquivoCompra.getName());
	} 
	else {
		  System.out.println("Arquivo já existe. (Compras)");
	}
		FileReader fr = new FileReader(arquivoCompra);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String linha1 = br.readLine();		
			if(linha1.equals("InicioCompra")) {
				nomeCliente = br.readLine();
				identidade = br.readLine();
				identificador = Integer.parseInt(br.readLine());
				valorTotalCompra = Float.parseFloat(br.readLine());
				data = LocalDate.parse(br.readLine());
				linha2=br.readLine();
				vecItensCompra= new ArrayList<ItensCompra>();
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
				
				valorTotalPago = Float.parseFloat(br.readLine());
				Compras compra = new Compras(nomeCliente, identidade, identificador, valorTotalCompra, valorTotalPago, data, vecItensCompra);
				getVecCompra().add(compra);
			}
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
	if(controlador == 1){	
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
			 if(permissaoParaContinuar==true){
				 JOptionPane.showMessageDialog(null, "Nome não encontrado no sistema, por favor digite novamente: ", "Erro", JOptionPane.ERROR_MESSAGE);
			 }	
		}while (permissaoParaContinuar==true);

	}else if(controlador ==2){
		for(PessoaJuridica pessoa: vecPessoaJuridica) {	
			nomesCadastrados += pessoa.getNome() + "\n";
		}
		do{
			 nomeEscolhidoCliente = JOptionPane.showInputDialog(null, nomesCadastrados,"Qual cliente esta comprando? ", JOptionPane.INFORMATION_MESSAGE);
			 for (PessoaJuridica pessoa1: vecPessoaJuridica) {
				 if(pessoa1.getNome().equals(nomeEscolhidoCliente)) {
						permissaoParaContinuar = false;
					}
			 }
			 if(permissaoParaContinuar==true){
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
	do{
		proximaCompra = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 - Sim\n"
				+ "2 - Não",
		        "Detalhes Compra - deseja continuar?",
		        JOptionPane.INFORMATION_MESSAGE));
	}while (proximaCompra <1 || proximaCompra > 2);
	}while(proximaCompra==1);
	boolean mesmoIdentificador = false;
	do {
		mesmoIdentificador = false;
		identificador = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o identificador da compra: ", JOptionPane.INFORMATION_MESSAGE));
		 for (Compras compra: this.vecCompra) {
			 if(compra.getIdentificador()==identificador) {
					mesmoIdentificador = true;
				}
		 }
		 if(mesmoIdentificador==true){
			 JOptionPane.showMessageDialog(null, "Identificador ja existente, por favor, digite um novo: ", "Erro", JOptionPane.ERROR_MESSAGE);
		 }
	}while(mesmoIdentificador);
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
		fileWriter.write("Total pago\n");
		fileWriter.write("0.0\n\n");
		fileWriter.close();
}

	public void mostraTodasAsCompras() {
		String infos = "";
		int i = 0;
		for (Compras compra: this.vecCompra) {
			infos = "";
			infos += compra.paraString();
			i++;
			JOptionPane.showMessageDialog(null, infos, "Mostrando todas as compras - " + i + "/" + this.vecCompra.size(), JOptionPane.INFORMATION_MESSAGE);
		}
	}//case 5
	public void buscaCompraPeloNumero() {
		String infos = "IDS disponíveis: ";
		
		for(Compras compra: this.vecCompra) {
			infos += "\n" + compra.getIdentificador();
		}
		
		String idCompra = JOptionPane.showInputDialog(null, infos
				, "Buscar Compra pelo número",
				JOptionPane.QUESTION_MESSAGE);
		
		infos = "";
		for(Compras compra: this.vecCompra) {
			if(compra.getIdentificador() == Integer.parseInt(idCompra)){
				infos += compra.paraString();
			}			
		}
		JOptionPane.showMessageDialog(null, infos, "Compra com ID: "+ idCompra, JOptionPane.INFORMATION_MESSAGE);
	}//case 6
	
	public void relacaoComprasNaoPagas() {
		String infos = "";
		
		ArrayList<Compras> ArrayCompra = new ArrayList();
		
		for(Compras compra: this.vecCompra)
			if(compra.getValorTotalPago() < compra.getValorTotalCompra()){
				ArrayCompra.add(compra);
			}
		infos += "Compras pelo id: \n";
		for(Compras compra: ArrayCompra) {
			infos += compra.paraString();
		}
		JOptionPane.showMessageDialog(null, infos, "Mostrando todas as Compras não pagas", JOptionPane.INFORMATION_MESSAGE);
	}//case 7
	public void ultimasDezCompras() {
		String infos = "";
		int i = 0, j = 0;
		ArrayList<Compras> ArrayCompra = new ArrayList();
		
		for (Compras compra: this.vecCompra) {
				if(compra.getValorTotalPago() == compra.getValorTotalCompra() && j<10){
					ArrayCompra.add(compra);
					infos = "";
					infos += compra.paraString();
					JOptionPane.showMessageDialog(null, infos, "Mostrando todas as ultimas 10 compras", JOptionPane.INFORMATION_MESSAGE);
					i++;
					j++;
				}

		}
	}//case 8
	public void compraMaisCara() {
		String infos = "";
		if(this.vecCompra.size()<=0) {
			JOptionPane.showMessageDialog(null, "Não existem compras registradas", "ERRO", JOptionPane.ERROR_MESSAGE);
		}else {
			double compraMaisCara = this.vecCompra.get(0).getValorTotalCompra();
			infos = this.getVecCompra().get(0).paraString()+"\n";
	        for (int i=0; i<this.vecCompra.size();i++) {
	        if(compraMaisCara<this.vecCompra.get(i).getValorTotalCompra()){ 
	        	infos = this.getVecCompra().get(i).paraString() + "\n";
	        }
	        }
	        JOptionPane.showMessageDialog(null, infos, "Compra mais cara: ", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}//case 9
	public void compraMaisBarata() {
		String infos = "";
		if(this.vecCompra.size()<=0) {
			JOptionPane.showMessageDialog(null, "Não existem compras registradas", "ERRO", JOptionPane.ERROR_MESSAGE);
		}else {
			double compraMaisBarata = this.vecCompra.get(0).getValorTotalCompra();
			infos = this.getVecCompra().get(0).paraString()+"\n";
	        for (int i=0; i<this.vecCompra.size();i++) {
	        if(compraMaisBarata>this.vecCompra.get(i).getValorTotalCompra()){ 
	        	infos = this.getVecCompra().get(i).paraString() + "\n";
	        }
	        }
	        JOptionPane.showMessageDialog(null, infos, "Compra mais barata:", JOptionPane.INFORMATION_MESSAGE);
		}
	}//case 10
}//class



