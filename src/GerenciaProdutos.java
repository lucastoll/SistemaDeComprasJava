import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GerenciaProdutos {
	@SuppressWarnings("unused")
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
			  System.out.println("Arquivo já existe.");
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
	public void cadastraProdutos() throws IOException {
		int controlador = 0;
       do {  
		controlador = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 - Não perecível\n"
				+ "2 - Perecível",
                "Cadastro de produtos - tipo produto",
                JOptionPane.INFORMATION_MESSAGE));
		if (controlador < 1 || controlador > 2) {
			JOptionPane.showMessageDialog(null, "Digite um valor correto.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
       }while (controlador < 1 || controlador > 2);
		
		int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto: "));
		String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto: ");
		String descricao = JOptionPane.showInputDialog("Digite a descrição: ");
		float preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o preço: "));
		File arquivoProduto = new File("./baseDados/produtos.txt");
		FileWriter fileWriter = new FileWriter(arquivoProduto, true);
		if (controlador ==1) {
		fileWriter.write("Produto\n");
		fileWriter.write(codigo + "\n");
		fileWriter.write(nomeProduto + "\n");
		fileWriter.write(descricao + "\n");
		fileWriter.write(preco + "\n");
		fileWriter.close();
		}
		else if(controlador==2) {
			int dia;
			int mes;
			do {
				dia = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Digite o dia","Data de validade"
		                ,
		                JOptionPane.INFORMATION_MESSAGE));
				if (dia <=0 || dia >31) {
					JOptionPane.showMessageDialog(null, "Dia inválido", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			}while (dia <=0 || dia >31);
			do {
				 mes = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Digite o mes","Data de validade"
		                ,
		                JOptionPane.INFORMATION_MESSAGE));
				if (mes <=0 || mes>12) {
					JOptionPane.showMessageDialog(null, "Mês inválido", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			}while (mes <=0 || mes >12);
			
			int ano = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Digite o ano","Data de validade"
	                ,
	                JOptionPane.INFORMATION_MESSAGE));
				fileWriter.write("Perecivel\n");
				fileWriter.write(codigo + "\n");
				fileWriter.write(nomeProduto + "\n");
				fileWriter.write(descricao + "\n");
				fileWriter.write(preco + "\n");
				if(dia < 10 && mes<10) {
					fileWriter.write(ano+"-"+"0"+mes+"-"+"0"+dia+"\n");
				}
				else if (mes <10){
					fileWriter.write(ano+"-"+"0"+mes+"-"+dia+"\n");
				}
				else if (dia <10) {
					fileWriter.write(ano+"-"+mes+"-"+"0"+dia+"\n");
				}
				else {
					fileWriter.write(ano+"-"+mes+"-"+dia+"\n");
				}
				fileWriter.close();
		}
		
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
		String infos = "Não pereciveis: \n";
		for(Produtos produtos: ArrayProdutos) {
			infos += produtos.paraString();
		}
		infos += "\nPereciveis: ";
		for(Pereciveis pereciveis: ArrayPereciveis) {
			infos += pereciveis.paraString();
		}
		
		
		JOptionPane.showMessageDialog(null, infos, "Relação de todos os produtos: ", JOptionPane.INFORMATION_MESSAGE);
	}//case 2
	public void buscaProdutoPeloNome(){
		String infos="";
		// Pergunta o nome do produto para o usuario
		String nomeProduto = JOptionPane.showInputDialog(null, "Qual o nome do produto que você deseja buscar?","Busca de produto pelo nome",
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
		// Mostra as informações que foram coletadas na array.
				infos += "Não pereciveis: \n";
				for(Produtos produtos: ArrayProdutos) {
					infos += produtos.paraString();
				}
				infos += "\nPereciveis: ";
				for(Pereciveis pereciveis: ArrayPereciveis) {
					infos += pereciveis.paraString();
				}

		JOptionPane.showMessageDialog(null, infos, "Busca de Produtos pelo nome" + nomeProduto, JOptionPane.INFORMATION_MESSAGE);
	}//case 3
	public void relacaoProdutosVencidos() {
		String infos="";
		
		ArrayList<Pereciveis> ArrayPereciveis = new ArrayList();
		// Procura ocorrências de pessoas jurídicas com nomes começando a partir da sequência definida pelo usuário e coloca em uma array;
		for(Pereciveis pereciveis: this.vecPereciveis) {
			if(pereciveis.vencimento() == true)
			ArrayPereciveis.add(pereciveis);
		}
		infos += "\nVencidos: ";
		for(Pereciveis pereciveis: ArrayPereciveis) {
			infos += pereciveis.paraString();
		}
		
		JOptionPane.showMessageDialog(null, infos, "Relação de todos os clientes iniciados pela sequencia de caracteres: ", JOptionPane.INFORMATION_MESSAGE);
	}//case 4
}//class


	
	