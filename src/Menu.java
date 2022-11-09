import javax.swing.JOptionPane;
import java.io.*;  // Import all the java.io library
import java.util.ArrayList;
import java.time.LocalDate;

public class Menu {
	public static void main(String[] args) throws IOException {
		int auxMenu = 0;


		GerenciaCliente GerenciadorClientes = new GerenciaCliente();
		GerenciaProdutos GerenciadorProdutos = new GerenciaProdutos();
		GerenciaCompra GerenciadorCompras = new GerenciaCompra();
		
		do{
			GerenciadorClientes.leClientes();
			GerenciadorProdutos.leProdutos();
			GerenciadorCompras.leCompra();
			
			auxMenu = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite uma opção: \n"
                    + "1 - Cadastro de clientes\n"
                    + "2 - Deletar cliente pelo CPF ou CNPJ\n"
                    + "3 - Deletar cliente pelo nome\n"
                    + "4 - Cadastro de produtos\n"
                    + "5 - Efetuação de uma compra\n"
                    + "6 - Atualização da situação de pagamento de uma conta\n"
                    + "7 - Relatórios\n"
                    + "0 - Encerrar sistema",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE));
			
			switch(auxMenu){
			case 1:
				ArrayList<String> ArrayInfosCliente = new ArrayList<String>(obtemInformaçõesCliente(GerenciadorClientes));				
				GerenciadorClientes.cadastraCliente(ArrayInfosCliente);
				break;
			case 2:
				int escolha; //Pergunta para o usuário se quer excluir por CPF ou CNPJ (lógicas diferentes);
				do{			
				escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Como desejar deletar um cliente?\n1 - CPF (Pessoa física)\n2 - CNPJ (Pessoa jurídica)"
							, "Deletar cliente por CPF/CNPJ",
							JOptionPane.QUESTION_MESSAGE));
				}while(escolha < 1 || escolha > 2);
				if(escolha == 1) {
					GerenciadorClientes.excluiClientePorCPF();
				} 
				else{	
					GerenciadorClientes.excluiClientePorCNPJ();
				}
				break;
			case 3:
				GerenciadorClientes.excluirClientePorNome();
				break;
			case 4:
				ArrayList<String> ArrayInfosProduto = new ArrayList<String>(obtemInformacoesProduto());
				GerenciadorProdutos.cadastraProdutos(ArrayInfosProduto);
				break;
			case 5: 
				GerenciadorCompras.cadastraCompra(GerenciadorClientes.getVecPessoaFisica(), GerenciadorClientes.getVecPessoaJuridica(), GerenciadorProdutos.getVecProdutos(), GerenciadorProdutos.getVecPereciveis());
				break;
			case 6:
				String idBuscado = obtemIDCompraNaoPaga(GerenciadorCompras);
				GerenciadorCompras.efetuaPagamento(idBuscado);
				break;
			case 7:
				subMenuRelatorios(GerenciadorClientes, GerenciadorProdutos, GerenciadorCompras);
				break;
			case 0: 
				JOptionPane.showMessageDialog(null, "...", "Encerrando sistema!", JOptionPane.INFORMATION_MESSAGE);
				break;
			default: 
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while(auxMenu != 0);
	}
	
	public static ArrayList<String> obtemInformacoesProduto() {
		   int controlador = 0;
		   String codigo="", preco="";
		   ArrayList<String> ArrayInfosProduto = new ArrayList<String>();

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
			
	       boolean permissaoParaContinuar;
			do {
				permissaoParaContinuar = false;
				codigo = JOptionPane.showInputDialog("Digite o código do produto: ");
				try {
					Integer.parseInt(codigo);
					permissaoParaContinuar = true;
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}while(!permissaoParaContinuar);
	       
			String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto: ");
			String descricao = JOptionPane.showInputDialog("Digite a descrição: ");
			
			do {
				permissaoParaContinuar = false;
				preco = JOptionPane.showInputDialog("Digite o preço: ");
				try {
					Float.parseFloat(preco);
					permissaoParaContinuar = true;
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}while(!permissaoParaContinuar);
			
			
			if(controlador == 1) {
				ArrayInfosProduto.add("Produto");
				ArrayInfosProduto.add(codigo);
				ArrayInfosProduto.add(nomeProduto);
				ArrayInfosProduto.add(descricao);
				ArrayInfosProduto.add(preco);

			}
			else if(controlador==2) {
				int dia;
				int mes;
				
				ArrayInfosProduto.add("Perecivel");
				ArrayInfosProduto.add(codigo);
				ArrayInfosProduto.add(nomeProduto);
				ArrayInfosProduto.add(descricao);
				ArrayInfosProduto.add(preco);
							
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
					if(dia < 10 && mes<10) {
						ArrayInfosProduto.add(ano+"-"+"0"+mes+"-"+"0"+dia+"\n");
					}
					else if (mes <10){
						ArrayInfosProduto.add(ano+"-"+"0"+mes+"-"+dia+"\n");
					}
					else if (dia <10) {
						ArrayInfosProduto.add(ano+"-"+mes+"-"+"0"+dia+"\n");
					}
					else {
						ArrayInfosProduto.add(ano+"-"+mes+"-"+dia+"\n");
					}
			}
			
			return ArrayInfosProduto;
	}
	
	public static String obtemIDCompraNaoPaga(GerenciaCompra GerenciadorCompras) {
		String infos = "ID's de compras cadastradas no sistema: \n", id = "";
		boolean permissaoPraContinuar = false;
		
		for(Compras compra: GerenciadorCompras.getVecCompra()) {
			if(compra.getValorTotalCompra() - compra.getValorTotalPago() != 0.0) {				
				infos += compra.getIdentificador() + "\n";
			}
		}
		
		do {
			id = JOptionPane.showInputDialog(null, infos, JOptionPane.QUESTION_MESSAGE);
			for(Compras compra: GerenciadorCompras.getVecCompra()) {
				if(compra.getIdentificador() ==  Integer.parseInt(id)  && compra.getValorTotalCompra() - compra.getValorTotalPago() != 0) {
					permissaoPraContinuar = true;
				}
			}
		}while(permissaoPraContinuar == false);
		
		return id;
	}
	
	public static void subMenuRelatorios(GerenciaCliente GerenciadorClientes, GerenciaProdutos GerenciadorProdutos, GerenciaCompra GerenciadorCompras) throws IOException {
		int auxSubmenuRelatorios = 0;
		do {
			auxSubmenuRelatorios = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite uma opção: \n"
                    + "1 - Relação de todos os Clientes que possuem o nome iniciado por uma determinada\r\n"
                    + "sequência de caracteres\n"
                    + "2 - Relação de todos os Produtos\n"
                    + "3 - Busca de um produto pelo nome\n"
                    + "4 - Relação de produtos que são perecíveis e que estão com a data de validade vencida\n"
                    + "5 - Relação de todas as compras\n"
                    + "6 - Busca de uma compra pelo número\n"
                    + "7 - Relação de todas as compras que não foram pagas ainda\n"
                    + "8 - Relação das 10 últimas compras pagas\n"
                    + "9 - Apresentação das informações da compra mais cara\n"
                    + "10 - Apresentação das informações da compra mais barata\n"
                    + "11 - Relação do valor total de compras feitas em cada mês nos últimos 12 meses\n"
                    + "0 - Voltar ao menu principal",
                    "Menu relatórios",
                    JOptionPane.QUESTION_MESSAGE));
			switch(auxSubmenuRelatorios) {
			case 1: 
				GerenciadorClientes.mostraClientesQueComecamPorUmaSequenciaDeCaracteres();
				break;
			case 2:
				GerenciadorProdutos.relacaoProdutos();
				break;
			case 3:
				GerenciadorProdutos.buscaProdutoPeloNome();
				break;
			case 4:
				GerenciadorProdutos.relacaoProdutosVencidos();
				break;
			case 5:
				GerenciadorCompras.mostraTodasAsCompras();
				break;
			case 6:
				GerenciadorCompras.buscaCompraPeloNumero();
				break;
			case 7:
				GerenciadorCompras.relacaoComprasNaoPagas();
				break;
			case 8:
				GerenciadorCompras.ultimasDezCompras();
				break;
			case 9:
				GerenciadorCompras.compraMaisCara();
				break;
			case 10:
				GerenciadorCompras.compraMaisBarata();
				break;
			case 11:
				GerenciadorCompras.comprasFeitasUltimosDozeMeses();
				break;
			case 0:
				auxSubmenuRelatorios = 0;
				break;
			default: 
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while(auxSubmenuRelatorios != 0);
	}
	
	public static String obtemInformacaoeVerificaRepeticaoCliente(String mensagem, GerenciaCliente GerenciadorClientes) {
		String atributo = "";
		boolean permissaoParaContinuar = true;
		do{
			permissaoParaContinuar = true;
			atributo = JOptionPane.showInputDialog(null,
					mensagem,
	                "Cadastro de clientes",
	                JOptionPane.INFORMATION_MESSAGE);
			// Verifica se ja existe outro cliente com a mesma informação cadastrada no Nome, CPF ou CNPJ de algum cliente 
			// (Se existir seria um problema para a manipulação do arquivo, principalmente na parte de deletar)
			for(PessoaJuridica pessoaJuridica: GerenciadorClientes.getVecPessoaJuridica()) {
				if(pessoaJuridica.getNome().equals(atributo) || pessoaJuridica.getCNPJ().equals(atributo)) {
					permissaoParaContinuar = false;
				}
			}	
			for(PessoaFisica pessoaFisica: GerenciadorClientes.getVecPessoaFisica()) {
				if(pessoaFisica.getNome().equals(atributo) || pessoaFisica.getCPF().equals(atributo)) {
					permissaoParaContinuar = false;
				}
			}
			if(!permissaoParaContinuar) {
				JOptionPane.showMessageDialog(null, "Informação ja cadastrada no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			else if(atributo.trim().equals("")) { // Se o usuario não digitar nada, permissao = false
				permissaoParaContinuar = false;
			}
		}while(!permissaoParaContinuar);
		
		return atributo;
	}
	
	public static ArrayList<String> obtemInformaçõesCliente(GerenciaCliente GerenciadorClientes) {
		//Função que pergunta as informações pro cliente e retorna em uma array
		ArrayList<String> ArrayInfosCliente = new ArrayList<String>();
		boolean permissaoParaContinuar;
		String nome = obtemInformacaoeVerificaRepeticaoCliente("Nome: ", GerenciadorClientes),
				rua = obtemInformacaoeVerificaRepeticaoCliente("Rua: ", GerenciadorClientes),
				bairro = obtemInformacaoeVerificaRepeticaoCliente("Bairro: ", GerenciadorClientes),
				cep = obtemInformacaoeVerificaRepeticaoCliente("CEP: ", GerenciadorClientes),
				cidade = obtemInformacaoeVerificaRepeticaoCliente("Cidade: ", GerenciadorClientes),
				estado = obtemInformacaoeVerificaRepeticaoCliente("Estado: ", GerenciadorClientes),
				numero = "";

		// Verificação se o usuario digitou um valor numerico
		do {
			permissaoParaContinuar = false;
			numero = obtemInformacaoeVerificaRepeticaoCliente("Numero: ", GerenciadorClientes);
			try {
				Integer.parseInt(numero);
				permissaoParaContinuar = true;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}while(!permissaoParaContinuar);
			

		int escolhaTipoDeCliente;
		do {
			escolhaTipoDeCliente = Integer.parseInt(JOptionPane.showInputDialog(null,
					"1 - Pessoa física\n"
					+ "2 - Pessoa juridica",
	                "Cadastro de clientes - tipo do cliente",
	                JOptionPane.INFORMATION_MESSAGE));
			
			if(escolhaTipoDeCliente == 1) {
				String cpf = obtemInformacaoeVerificaRepeticaoCliente("CPF: ", GerenciadorClientes), 
					numeroMaximoDeParcelas = "";

				// Verificação se o usuario digitou um valor numerico
				do {
					permissaoParaContinuar = false;
					numeroMaximoDeParcelas = obtemInformacaoeVerificaRepeticaoCliente("Numero maximo de parcelas permitidas em uma compra do cliente: ", GerenciadorClientes);
					try {
						Integer.parseInt(numeroMaximoDeParcelas);
						permissaoParaContinuar = true;
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}while(!permissaoParaContinuar);
				
				ArrayInfosCliente.add(0, "fisica"); //adiciona na primeira posicao da array (pra linha servir como identificador no arquivo)
				ArrayInfosCliente.add(nome);
				ArrayInfosCliente.add(rua);
				ArrayInfosCliente.add(numero);
				ArrayInfosCliente.add(bairro);
				ArrayInfosCliente.add(cep);
				ArrayInfosCliente.add(cidade);
				ArrayInfosCliente.add(estado);
				ArrayInfosCliente.add(cpf);
				ArrayInfosCliente.add(numeroMaximoDeParcelas);
			}
			else if(escolhaTipoDeCliente == 2) {
				String cnpj = obtemInformacaoeVerificaRepeticaoCliente("CNPJ: ", GerenciadorClientes),
						razaoSocial = obtemInformacaoeVerificaRepeticaoCliente("Razão social: ", GerenciadorClientes),
					    prazoMaximo = "";
				
				// Verificação se o usuario digitou um valor numerico
				do {
					permissaoParaContinuar = false;
					prazoMaximo = obtemInformacaoeVerificaRepeticaoCliente("Prazo maximo: ", GerenciadorClientes);
					try {
						Integer.parseInt(prazoMaximo);
						permissaoParaContinuar = true;
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}while(!permissaoParaContinuar);
				
				ArrayInfosCliente.add(0, "juridica"); //adiciona na primeira posicao da array (pra linha servir como identificador no arquivo)
				ArrayInfosCliente.add(nome);
				ArrayInfosCliente.add(rua);
				ArrayInfosCliente.add(numero);
				ArrayInfosCliente.add(bairro);
				ArrayInfosCliente.add(cep);
				ArrayInfosCliente.add(cidade);
				ArrayInfosCliente.add(estado);
				ArrayInfosCliente.add(cnpj);
				ArrayInfosCliente.add(razaoSocial);
				ArrayInfosCliente.add(prazoMaximo);
			}
			else {
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}while(escolhaTipoDeCliente < 1 || escolhaTipoDeCliente > 2);	
		return ArrayInfosCliente;
	}
}
