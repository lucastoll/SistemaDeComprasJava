import javax.swing.JOptionPane;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.time.LocalDate;

public class Menu {
	public static void main(String[] args) throws IOException {
		int auxMenu = 0;
		GerenciaCliente GerenciadorClientes = new GerenciaCliente();
		GerenciaProdutos GerenciadorProdutos = new GerenciaProdutos();
		do{
			GerenciadorClientes.leClientes();//Le as informações do arquivo e atualiza os vetores cleintes apos qualquer operação do menu
			GerenciadorProdutos.leProdutos();
			auxMenu = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite uma opção: \n"
                    + "1 - Cadastro de clientes\n"
                    + "2 - Deletar cliente pelo CPF\n"
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
				GerenciadorProdutos.cadastraProdutos();
				break;
			case 5: 
				GerenciadorProdutos.MostraProdutos();
				break;
			case 7:
				subMenuRelatorios(GerenciadorClientes.getVecPessoaFisica(), GerenciadorClientes.getVecPessoaJuridica());
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
	
	public static void subMenuRelatorios(ArrayList<PessoaFisica> vecPessoaFisica, ArrayList<PessoaJuridica> vecPessoaJuridica) {
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
				mostraClientesQueComecamPorUmaSequenciaDeCaracteres(vecPessoaFisica, vecPessoaJuridica);
				break;
			case 0:
				break;
			default: 
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while(auxSubmenuRelatorios != 0);
	}
	
	public static void mostraClientesQueComecamPorUmaSequenciaDeCaracteres(ArrayList<PessoaFisica> arrayOriginalPessoaFisica, ArrayList<PessoaJuridica> arrayOriginalPessoaJuridica) {
		// Pergunta a sequencia de caracteres para o usuario
		String sequenciaCaracteres = JOptionPane.showInputDialog(null, "Qual sequência de caracteres você deseja buscar?"
				, "Clientes que possuem o nome iniciado por uma determinada sequência de caracteres",
				JOptionPane.QUESTION_MESSAGE);
		
		ArrayList<PessoaFisica> ArrayPessoasFisicas = new ArrayList();
		ArrayList<PessoaJuridica> ArrayPessoasJuridicas = new ArrayList();
		// Procura ocorrencias de pessoas fisicas com nomes começando a partir da sequencia definida pelo usuario e coloca em uma array;
		for(PessoaFisica pessoaFisica: arrayOriginalPessoaFisica) {
			System.out.println(pessoaFisica.getNome().indexOf(sequenciaCaracteres) + pessoaFisica.getNome());
			if(pessoaFisica.getNome().indexOf(sequenciaCaracteres) == 0){
				ArrayPessoasFisicas.add(pessoaFisica);
			}
		}
		// Procura ocorrencias de pessoas juridicas com nomes começando a partir da sequencia definida pelo usuario e coloca em uma array;
		for(PessoaJuridica pessoaJuridica: arrayOriginalPessoaJuridica) {
			if(pessoaJuridica.getNome().indexOf(sequenciaCaracteres) == 0){
				ArrayPessoasJuridicas.add(pessoaJuridica);
			}
		}
		// Mostra as informações que foram coletadas na array.
		String infos = "Pessoas fisicas: ";
		for(PessoaFisica pessoaFisica: ArrayPessoasFisicas) {
			infos += pessoaFisica.paraString();
		}
		infos += "\nPessoas juridicas: ";
		for(PessoaJuridica pessoaJuridica: ArrayPessoasJuridicas) {
			infos += pessoaJuridica.paraString();
		}
		
		
		JOptionPane.showMessageDialog(null, infos, "Relação de todos os clientes iniciados pela sequencia de caracteres: " + sequenciaCaracteres, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String obtemInformacaoeVerificaRepeticaoCliente(String atributo, String mensagem, GerenciaCliente GerenciadorClientes) {
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
		String nome = "", rua = "", numero = "", bairro = "", cep = "", cidade = "", estado = "";

		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(nome, "Nome: ", GerenciadorClientes));
		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(rua, "Rua: ", GerenciadorClientes));
		
		// Verificação se o usuario digitou um valor numerico
		do {
			permissaoParaContinuar = false;
			numero = obtemInformacaoeVerificaRepeticaoCliente(numero, "Numero: ", GerenciadorClientes);
			try {
				Integer.parseInt(numero);
				permissaoParaContinuar = true;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}while(!permissaoParaContinuar);
		ArrayInfosCliente.add(numero);
			
		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(bairro, "Bairro: ", GerenciadorClientes));
		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(cep, "CEP: ", GerenciadorClientes));
		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(cidade, "Cidade: ", GerenciadorClientes));
		ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(estado, "Estado: ", GerenciadorClientes));

		int escolhaTipoDeCliente;
		do {
			escolhaTipoDeCliente = Integer.parseInt(JOptionPane.showInputDialog(null,
					"1 - Pessoa física\n"
					+ "2 - Pessoa juridica",
	                "Cadastro de clientes - tipo do cliente",
	                JOptionPane.INFORMATION_MESSAGE));
			
			if(escolhaTipoDeCliente == 1) {
				String cpf = "", numeroMaximoDeParcelas = "";

				ArrayInfosCliente.add(0, "fisica"); //adiciona na primeira posicao da array (pra linha servir como identificador no arquivo)
				ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(cpf, "CPF: ", GerenciadorClientes));
				// Verificação se o usuario digitou um valor numerico
				do {
					permissaoParaContinuar = false;
					numeroMaximoDeParcelas = obtemInformacaoeVerificaRepeticaoCliente(numeroMaximoDeParcelas, "Numero maximo de parcelas permitidas em uma compra do cliente: ", GerenciadorClientes);
					try {
						Integer.parseInt(numeroMaximoDeParcelas);
						permissaoParaContinuar = true;
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}while(!permissaoParaContinuar);
				ArrayInfosCliente.add(numeroMaximoDeParcelas);
			}
			else if(escolhaTipoDeCliente == 2) {
				String cnpj = "", razaoSocial = "", prazoMaximo = "";
				
				ArrayInfosCliente.add(0, "juridica"); //adiciona na primeira posicao da array (pra linha servir como identificador no arquivo)
				ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(cnpj, "CNPJ: ", GerenciadorClientes));
				ArrayInfosCliente.add(obtemInformacaoeVerificaRepeticaoCliente(razaoSocial, "Razão social: ", GerenciadorClientes));
				
				// Verificação se o usuario digitou um valor numerico
				do {
					permissaoParaContinuar = false;
					prazoMaximo = obtemInformacaoeVerificaRepeticaoCliente(prazoMaximo, "Prazo maximo: ", GerenciadorClientes);
					try {
						Integer.parseInt(prazoMaximo);
						permissaoParaContinuar = true;
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Digite um valor numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}while(!permissaoParaContinuar);
				ArrayInfosCliente.add(prazoMaximo);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}while(escolhaTipoDeCliente < 1 || escolhaTipoDeCliente > 2);
		
		return ArrayInfosCliente;
	}
	/*public static ArrayList<String> obtemInformaçõesProdutos(GerenciaProdutos GerenciadorProdutos){
		
	}*/
}
