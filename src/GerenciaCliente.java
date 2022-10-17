import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GerenciaCliente {
	private ArrayList<PessoaFisica> vecPessoaFisica;
	private ArrayList<PessoaJuridica> vecPessoaJuridica;
	
	public GerenciaCliente(ArrayList<PessoaFisica> vecPessoaFisica, ArrayList<PessoaJuridica> vecPessoaJuridica) {
		setVecPessoaFisica(vecPessoaFisica);
		setVecPessoaJuridica(vecPessoaJuridica);
	}
	
	//Pessoa Fisica
	public ArrayList<PessoaFisica> getVecPessoaFisica() {
		return vecPessoaFisica;
	}
	public void setVecPessoaFisica(ArrayList<PessoaFisica> vecPessoaFisica) {
		this.vecPessoaFisica = vecPessoaFisica;
	}
	//Pessoa Juridica
	public ArrayList<PessoaJuridica> getVecPessoaJuridica() {
		return vecPessoaJuridica;
	}
	public void setVecPessoaJuridica(ArrayList<PessoaJuridica> vecPessoaJuridica) {
		this.vecPessoaJuridica = vecPessoaJuridica;
	}	
	
	public void leClientes() throws IOException {
		this.vecPessoaFisica.clear();
		this.vecPessoaJuridica.clear();;
		File arquivoClientes = new File("./baseDados/clientes.txt");
		
		if(arquivoClientes.createNewFile()) {
		  System.out.println("File created: " + arquivoClientes.getName());
		} 
		else {
		  System.out.println("File already exists.");
		}
		
		FileReader fr = new FileReader(arquivoClientes);
		BufferedReader br = new BufferedReader(fr);
		
		String rua = "", bairro = "", cep = "", cidade = "", estado = "";
		int numero = 0;
		
		while(br.ready()) {
			String linha = br.readLine();
			if(linha.equals("fisica")) {
				String cpf = "";
				int maximoDeParcelas = 0;
				rua = br.readLine();
				numero = Integer.parseInt(br.readLine());
				bairro = br.readLine();
				cep = br.readLine();
				cidade = br.readLine();
				estado = br.readLine();
				cpf = br.readLine();
				maximoDeParcelas = Integer.parseInt(br.readLine());
				
				Endereço endereço1 = new Endereço(rua, numero, bairro, cep, cidade, estado);
				PessoaFisica pessoaFisica1 = new PessoaFisica(LocalDate.now(), endereço1, cpf, maximoDeParcelas);
				getVecPessoaFisica().add(pessoaFisica1);
			}
			else if(linha.equals("juridica")) {
				String cnpj = "", razaoSocial = "";
				int prazoMaximoEmDias = 0;
				
				rua = br.readLine();
				numero = Integer.parseInt(br.readLine());
				bairro = br.readLine();
				cep = br.readLine();
				cidade = br.readLine();
				estado = br.readLine();
				cnpj = br.readLine();
				razaoSocial = br.readLine();
				prazoMaximoEmDias = Integer.parseInt(br.readLine());
				
				Endereço endereço1 = new Endereço(rua, numero, bairro, cep, cidade, estado);
				PessoaJuridica pessoaJuridica1 = new PessoaJuridica(LocalDate.now(), endereço1, cnpj, razaoSocial, prazoMaximoEmDias);
				getVecPessoaJuridica().add(pessoaJuridica1);
			}
		}
		
		br.close();
		fr.close();
	}
	
	public ArrayList<String> obtemInformaçõesCliente() {
		ArrayList<String> ArrayInfosCliente = new ArrayList<String>();
		
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
	
	public void cadastraCliente(ArrayList<String> ArrayInfosCliente) throws IOException {
		File arquivoClientes = new File("./baseDados/clientes.txt");

		if(arquivoClientes.createNewFile()) {
		  System.out.println("Arquivo criado: " + arquivoClientes.getName());
		} 
		else {
		  System.out.println("Arquivo ja existe.");
		}
			
		FileWriter fileWriter = new FileWriter(arquivoClientes, true);
		
		for(String info: ArrayInfosCliente) {
			fileWriter.write(info + "\n");
		}
		
		fileWriter.close();
	}
	
	public void excluiClientePorCPF() throws IOException{
		String cpfsCadastradosNoSistema = "";
		boolean cpfEncontrado = false;
		
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			cpfsCadastradosNoSistema += pessoaFisica.getCPF() + "\n";
		}
		
		String cpf = JOptionPane.showInputDialog(null, "CPFs cadastrados no sistema:\n"
				+ cpfsCadastradosNoSistema, "Deletar cliente por CPF",
				JOptionPane.QUESTION_MESSAGE);
		
		int iterador = 0;
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			if(cpf.equals(pessoaFisica.getCPF())) {
				cpfEncontrado = true;
				
				JOptionPane.showMessageDialog(null, "Cliente com o CPF " + cpf + " deletado com sucesso", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			iterador++;
		}
		
		removeLinhaPorArquivo("./baseDados/clientes.txt", 3);
		
		if(!cpfEncontrado) {
			JOptionPane.showMessageDialog(null, "CPF não encontrado, verifique a digitação e tente novamente", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void removeLinhaPorArquivo(String arquivo, int linhaParaApagar) throws IOException {
		File arquivoOriginal = new File(arquivo);
		File arquivoTemporario = new File("./baseDados/clientesTemp.txt");
		arquivoTemporario.createNewFile();
		int iterador = 1;

		//certo
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));
		FileWriter fileWriter = new FileWriter(arquivoTemporario, true);
		

		while(bufferedReader.ready()) {
			
			System.out.println("\n\nIteração " + iterador);
			System.out.println("Iterador == linhaParaApagar?" + (iterador == linhaParaApagar));
			if(iterador == linhaParaApagar) {	
				System.out.println("Entrei no primeiro if");
				bufferedReader.readLine();
				iterador++;
				continue;
			}
			else {
				String fodase = bufferedReader.readLine();
				fileWriter.write(fodase + "\n");	
				System.out.println("Entrei no segundo if e escrevi" + fodase);
			}
			iterador++;
		}
		
		fileWriter.close(); 
		bufferedReader.close(); 
		
	}

}
