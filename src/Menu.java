import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class Menu {
	public static void main(String[] args) throws IOException {
		int auxMenu = 0;
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;

		do{
			auxMenu = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite uma opção: \n"
                    + "1 - Cadastro de clientes\n"
                    + "2 - Deletar Cliente pelo CPF\n"
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
				cadastraCliente();
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
	
	public static void cadastraCliente() throws IOException {
		File arquivoClientes = new File("./baseDados/clientes.txt");

		if(arquivoClientes.createNewFile()) {
		  System.out.println("File created: " + arquivoClientes.getName());
		} 
		else {
		  System.out.println("File already exists.");
		}
			
		FileWriter fileWriter = new FileWriter(arquivoClientes, true);
		
		
		String rua = JOptionPane.showInputDialog(null,
                "Rua",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);

		int numero = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Numero: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE));
		
		String bairro = JOptionPane.showInputDialog(null,
                "Bairro: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		
		String cep = JOptionPane.showInputDialog(null,
				"CEP: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		
		String cidade = JOptionPane.showInputDialog(null,
				"Cidade: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
		
		String estado = JOptionPane.showInputDialog(null,
				"Estado: ",
                "Cadastro de clientes - Endereço",
                JOptionPane.INFORMATION_MESSAGE);
	
		int escolhaTipoDeCliente;
		
		do {
			escolhaTipoDeCliente = Integer.parseInt(JOptionPane.showInputDialog(null,
					"1 - Pessoa física\n"
					+ "2 - Pessoa juridica",
	                "Cadastro de clientes - tipo do cliente",
	                JOptionPane.INFORMATION_MESSAGE));
			
			if(escolhaTipoDeCliente == 1) {
				String cpf = JOptionPane.showInputDialog(null,
						"CPF: ",
		                "Cadastro de clientes - Pessoa física",
		                JOptionPane.INFORMATION_MESSAGE);
				
				int numeroMaximoDeParcelas = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Número máximo de parcelas na compra: ",
		                "Cadastro de clientes - Pessoa física",
		                JOptionPane.INFORMATION_MESSAGE));
				
				fileWriter.write("Separador\n\n" +
						"fisica\n" 
						+ rua + "\n"
						+ numero + "\n" 
						+ bairro + "\n" 
						+ cep + "\n" 
						+ cidade + "\n" 
						+ estado + "\n"
						+ cpf + "\n"
						+ numeroMaximoDeParcelas + "\n\n");
			}
			else if(escolhaTipoDeCliente == 2) {
				String cnpj = JOptionPane.showInputDialog(null,
						"CNPJ: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				
				String razaoSocial = JOptionPane.showInputDialog(null,
						"Razão Social: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				
				String prazoMaximo = JOptionPane.showInputDialog(null,
						"Prazo máximo em dias para o pagamento da compra: ",
		                "Cadastro de clientes - Pessoa jurídica",
		                JOptionPane.INFORMATION_MESSAGE);
				
				fileWriter.write("Separador\n\n" +
						"juridica\n" 
						+ rua + "\n"
						+ numero + "\n" 
						+ bairro + "\n" 
						+ cep + "\n" 
						+ cidade + "\n" 
						+ estado + "\n"
						+ cnpj + "\n"
						+ razaoSocial + "\n"
						+ prazoMaximo + "\n\n");
			}
			else {
				JOptionPane.showMessageDialog(null, "Opção invalida!, verifique a digitação e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(escolhaTipoDeCliente);
			System.out.println(escolhaTipoDeCliente > 0 || escolhaTipoDeCliente < 3);
		}while(escolhaTipoDeCliente < 1 || escolhaTipoDeCliente > 2);
		
		fileWriter.close();
	}
}
