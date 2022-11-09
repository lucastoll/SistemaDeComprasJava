import java.io.*;  // Import all the java.io library
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GerenciaProdutos {
	private ArrayList<Produtos> vecProdutos;
	private ArrayList<Pereciveis>vecPereciveis;

	
	public GerenciaProdutos() {
		ArrayList<Produtos>vecProdutos = new ArrayList<Produtos>();
		setVecProdutos(vecProdutos);
		ArrayList<Pereciveis>vecPereciveis = new ArrayList<Pereciveis>();
		setVecPereciveis(vecPereciveis);
	}
	public ArrayList<Produtos> getVecProdutos() {
		return vecProdutos;
	}
	public void setVecProdutos(ArrayList<Produtos> vecProdutos) {
		this.vecProdutos = vecProdutos;
	}
	public ArrayList<Pereciveis> getVecPereciveis() {
		return vecPereciveis;
	}
	public void setVecPereciveis(ArrayList<Pereciveis> vecPereciveis) {
		this.vecPereciveis = vecPereciveis;
	}
		public void leProdutos() throws IOException{	
		this.vecProdutos.clear();
		this.vecPereciveis.clear();
		File arquivoProduto = new File("./baseDados/produtos.txt");
		if(arquivoProduto.createNewFile()) {
			  System.out.println("Arquivo criado: " + arquivoProduto.getName());
			} 
			else {
			  System.out.println("Arquivo já existe. (Produtos)");
			}
		FileReader fr = new FileReader(arquivoProduto);
		BufferedReader br = new BufferedReader(fr);
		int codigo=0;
		String nomeProduto= "", descricao= "";
		LocalDate datadevalidade;
		float preco=0;
		while(br.ready()) {
		String linha1 = br.readLine();
		if(linha1.equals("Produto")){
			codigo=Integer.parseInt(br.readLine());
			nomeProduto = br.readLine();
			descricao=br.readLine();
			preco=Float.parseFloat(br.readLine());
			Produtos produto = new Produtos (codigo, nomeProduto, descricao, preco);
			getVecProdutos().add(produto);
		}
		if(linha1.equals("Perecivel")){
			codigo=Integer.parseInt(br.readLine());
			nomeProduto = br.readLine();
			descricao=br.readLine();
			preco=Float.parseFloat(br.readLine());
			datadevalidade= LocalDate.parse(br.readLine());
			Pereciveis perecivel = new Pereciveis (codigo, nomeProduto, descricao, preco, datadevalidade);
			getVecPereciveis().add(perecivel);
			
		}	
		}
		br.close();
		fr.close();
	}
		
	public void cadastraProdutos(ArrayList<String> ArrayInfoProdutos) throws IOException {
		// Função que recebe uma array de dados e cadastra no final do arquivo
		File arquivoProdutos = new File("./baseDados/produtos.txt");

		if(arquivoProdutos.createNewFile()) {
		  System.out.println("Arquivo criado: " + arquivoProdutos.getName());
		} 
		else {
		  System.out.println("Arquivo ja existe.");
		}
			
		FileWriter fileWriter = new FileWriter(arquivoProdutos, true);
		
		for(String info: ArrayInfoProdutos) {
			fileWriter.write(info + "\n");
		}
		fileWriter.write("\n");

		fileWriter.close();
	}
	
	public void relacaoProdutos () {
		ArrayList<Produtos> ArrayProdutos = new ArrayList();
		ArrayList<Pereciveis> ArrayPereciveis = new ArrayList();
		// Procura ocorrencias de pessoas fisicas com nomes começando a partir da sequencia definida pelo usuario e coloca em uma array;
		for(Produtos produtos: this.vecProdutos) {
			ArrayProdutos.add(produtos);
		}
		// Procura ocorrências de pessoas jurídicas com nomes começando a partir da sequência definida pelo usuário e coloca em uma array;
		for(Pereciveis pereciveis: this.vecPereciveis) {
			ArrayPereciveis.add(pereciveis);
		}
		// Mostra as informações que foram coletadas na array.
		String infos = "";
		for(Produtos produtos: ArrayProdutos) {
			infos = "Não perecivel\n\n";
			infos += produtos.paraString();
			JOptionPane.showMessageDialog(null, infos, "Relação de todos os produtos: ", JOptionPane.INFORMATION_MESSAGE);
		}
		infos = "";
		for(Pereciveis pereciveis: ArrayPereciveis) {
			infos = "Perecivel\n\n";
			infos += pereciveis.paraString();
			JOptionPane.showMessageDialog(null, infos, "Relação de todos os produtos: ", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}//case 2
	public void buscaProdutoPeloNome(){
		String infos="\nProdutos disponíveis: ";
		for(Produtos produtos: this.vecProdutos) {
			infos += "\n" + produtos.getNomeproduto();
		}
		for(Pereciveis pereciveis: this.vecPereciveis) {
			infos += "\n"+ pereciveis.getNomeproduto();
		}
		// Pergunta o nome do produto para o usuario
		String nomeProduto = JOptionPane.showInputDialog(null, "Qual o nome do produto que você deseja buscar?" + infos,"Busca de produto pelo nome",
				JOptionPane.QUESTION_MESSAGE);
		
		ArrayList<Produtos> ArrayProdutos = new ArrayList();
		ArrayList<Pereciveis> ArrayPereciveis = new ArrayList();
		
		for(Produtos produtos: this.vecProdutos) {
			if(produtos.getNomeproduto().indexOf(nomeProduto) == 0){
				ArrayProdutos.add(produtos);
			}
		}
		for(Pereciveis pereciveis: this.vecPereciveis) {
			if(pereciveis.getNomeproduto().indexOf(nomeProduto) == 0){
				ArrayPereciveis.add(pereciveis);	
			}
		}
		if(ArrayProdutos.size()!=0) {
			infos="";
			infos += "Não pereciveis: \n";
			for(Produtos produtos: ArrayProdutos) {
				infos += produtos.paraString();
			}
		}
		else if(ArrayPereciveis.size()!=0) {
			infos = "";
			infos += "Pereciveis: \n";
			for(Pereciveis pereciveis: ArrayPereciveis) {
				infos += pereciveis.paraString();
			}
		}
		JOptionPane.showMessageDialog(null, infos, "Busca de Produtos pelo nome: " + nomeProduto, JOptionPane.INFORMATION_MESSAGE);
		

		
	}//case 3
	public void relacaoProdutosVencidos() {
		String infos="";
		
		ArrayList<Pereciveis> ArrayPereciveis = new ArrayList();
		// Procura ocorrências de pessoas jurídicas com nomes começando a partir da sequência definida pelo usuário e coloca em uma array;
		for(Pereciveis pereciveis: this.vecPereciveis) {
			if(pereciveis.vencimento() == true)
			ArrayPereciveis.add(pereciveis);
		}
		infos += "Produtos vencidos\n\n";
		for(Pereciveis pereciveis: ArrayPereciveis) {
			infos += pereciveis.paraString();
		}
		
		JOptionPane.showMessageDialog(null, infos, "Relação de todos os clientes iniciados pela sequencia de caracteres: ", JOptionPane.INFORMATION_MESSAGE);
	}//case 4
}//class


	
	