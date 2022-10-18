import javax.swing.JOptionPane;


import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class Menu {
	public static void main(String[] args) throws IOException {
		int auxMenu = 0;

		ArrayList<PessoaFisica> vecPessoaFisica = new ArrayList<PessoaFisica>();
		ArrayList<PessoaJuridica> vecPessoaJuridica = new ArrayList<PessoaJuridica>();
		GerenciaCliente GerenciadorClientes = new GerenciaCliente(vecPessoaFisica, vecPessoaJuridica);
		
		do{
			GerenciadorClientes.leClientes();
			
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
				ArrayList<String> ArrayInfosCliente = new ArrayList<String>(obtemInformaçõesCliente());				
				GerenciadorClientes.cadastraCliente(ArrayInfosCliente);
				break;
			case 2:
				int escolha;
				//Pergunta para o usuário se quer excluir por CPF ou CNPJ (lógicas diferentes);
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
				String infos = "Pessoa física";
				for(PessoaFisica pessoaFisica: vecPessoaFisica) {
					infos += pessoaFisica.paraString();
				}
				infos += "\n\nPessoa Juridíca";
				for(PessoaJuridica pessoaJuridica: vecPessoaJuridica) {
					infos += pessoaJuridica.paraString();
				}
				
				JOptionPane.showMessageDialog(null, infos, "Relação de todos os clientes iniciados por uma determinada sequencia de caracteres", JOptionPane.INFORMATION_MESSAGE);
				break;
			case 0:
				break;
			default: 
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while(auxSubmenuRelatorios != 0);
	}
	
	public static ArrayList<String> obtemInformaçõesCliente() {
		ArrayList<String> ArrayInfosCliente = new ArrayList<String>();
		
		String nome = JOptionPane.showInputDialog(null,
                "Nome",
                "Cadastro de clientes",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(nome);
		
		String rua = JOptionPane.showInputDialog(null,
                "Rua",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(rua);

		String numero = JOptionPane.showInputDialog(null,
				"Numero: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(numero);

		String bairro = JOptionPane.showInputDialog(null,
                "Bairro: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(bairro);

		String cep = JOptionPane.showInputDialog(null,
				"CEP: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(cep);
		
		String cidade = JOptionPane.showInputDialog(null,
				"Cidade: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(cidade);
		
		String estado = JOptionPane.showInputDialog(null,
				"Estado: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		ArrayInfosCliente.add(estado);

		int escolhaTipoDeCliente;
		do {
			escolhaTipoDeCliente = Integer.parseInt(JOptionPane.showInputDialog(null,
					"1 - Pessoa física\n"
					+ "2 - Pessoa juridica",
	                "Cadastro de clientes - tipo do cliente",
	                JOptionPane.INFORMATION_MESSAGE));
			
			if(escolhaTipoDeCliente == 1) {
				ArrayInfosCliente.add(0, "fisica");
				
				String cpf = JOptionPane.showInputDialog(null,
						"CPF: ",
		                "Cadastro de clientes - Pessoa física",
		                JOptionPane.INFORMATION_MESSAGE);
				ArrayInfosCliente.add(cpf);
				
				String numeroMaximoDeParcelas = JOptionPane.showInputDialog(null,
						"Número máximo de parcelas na compra: ",
		                "Cadastro de clientes - Pessoa física",
		                JOptionPane.INFORMATION_MESSAGE);
				ArrayInfosCliente.add(numeroMaximoDeParcelas);
			}
			else if(escolhaTipoDeCliente == 2) {
				ArrayInfosCliente.add(0, "juridica");
				
				String cnpj = JOptionPane.showInputDialog(null,
						"CNPJ: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				ArrayInfosCliente.add(cnpj);
				
				String razaoSocial = JOptionPane.showInputDialog(null,
						"Razão Social: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				ArrayInfosCliente.add(razaoSocial);
				
				String prazoMaximo = JOptionPane.showInputDialog(null,
						"Prazo máximo em dias para o pagamento da compra: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				ArrayInfosCliente.add(prazoMaximo);
			}
			else {
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}while(escolhaTipoDeCliente < 1 || escolhaTipoDeCliente > 2);
		
		return ArrayInfosCliente;
	}
}
