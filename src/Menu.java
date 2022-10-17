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
				ArrayList<String> ArrayInfosCliente = new ArrayList<String>(GerenciadorClientes.obtemInformaçõesCliente());				
				GerenciadorClientes.cadastraCliente(ArrayInfosCliente);
				break;
			case 2:
				GerenciadorClientes.excluiClientePorCPF();
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
}
