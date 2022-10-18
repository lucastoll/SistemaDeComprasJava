import java.awt.HeadlessException;
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
		
		String nome = "", rua = "", bairro = "", cep = "", cidade = "", estado = "";
		int numero = 0;
		
		while(br.ready()) {
			String linha = br.readLine();
			if(linha.equals("fisica")) {
				String cpf = "";
				int maximoDeParcelas = 0;
				nome = br.readLine();
				rua = br.readLine();
				numero = Integer.parseInt(br.readLine());
				bairro = br.readLine();
				cep = br.readLine();
				cidade = br.readLine();
				estado = br.readLine();
				cpf = br.readLine();
				maximoDeParcelas = Integer.parseInt(br.readLine());
				
				Endereço endereço1 = new Endereço(rua, numero, bairro, cep, cidade, estado);
				PessoaFisica pessoaFisica1 = new PessoaFisica(LocalDate.now(), endereço1, nome, cpf, maximoDeParcelas);
				getVecPessoaFisica().add(pessoaFisica1);
			}
			else if(linha.equals("juridica")) {
				String cnpj = "", razaoSocial = "";
				int prazoMaximoEmDias = 0;
				
				nome = br.readLine();
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
				PessoaJuridica pessoaJuridica1 = new PessoaJuridica(LocalDate.now(), endereço1, nome, cnpj, razaoSocial, prazoMaximoEmDias);
				getVecPessoaJuridica().add(pessoaJuridica1);
			}
		}
		
		br.close();
		fr.close();
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
		fileWriter.write("\n");

		fileWriter.close();
	}
	
	public void excluiClientePorCPF() throws IOException {
		String cpfsCadastradosNoSistema = "";
		boolean cpfEncontrado = false;
		//Pega todos os cpfs encontrados no sistema e exibe na tela para o usuário ver
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			cpfsCadastradosNoSistema += pessoaFisica.getCPF() + "\n";
		}
		//Pergunta qual CPF o usuário quer excluir
		String cpf = JOptionPane.showInputDialog(null, "CPFs cadastrados no sistema:\n" + cpfsCadastradosNoSistema,
				"Deletar cliente por CPF/CNPJ",
				JOptionPane.QUESTION_MESSAGE);
		//Verifica se o CPF que o usuário digitou existe no sistema
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			if(cpf.equals(pessoaFisica.getCPF())) {
				cpfEncontrado = true;		
				break;
			}
		}
		//Verifica se o CPF foi encontrado, se sim ele é apagado, caso contrário, diz ao usuário que o cpf não foi encontrado
		if(!cpfEncontrado){
			JOptionPane.showMessageDialog(null, "CPF não encontrado no sistema, verifique a digitação e tente novamente", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
		else{
			int iterador = 0;
			File arquivoOriginal = new File("./baseDados/clientes.txt");
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));

		    while(bufferedReader.ready()) {
		    	iterador++;
				if(cpf.equals(bufferedReader.readLine())) {	
					bufferedReader.close();
					for(int j = iterador + 2; j >= iterador - 8; j--) { // Verifica todas as linhas do arquivo procurando o CPF	
						removeLinhaPorArquivo("./baseDados/clientes.txt", j); // Apaga as informações do cliente fisico partindo da ultima linha para a primeira
					}
					JOptionPane.showMessageDialog(null, "Cliente com CPF " + cpf + " deletado com sucesso", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
		    } // while
		} // else
	}
	
	public void excluiClientePorCNPJ() throws IOException{
		String cnpjsCadastradosNoSistema = "";
		boolean cnpjEncontrado = false;
		//Pega todos os CNPJ encontrados no sistema e exibe na tela para o usuário ver
		for(PessoaJuridica pessoaJuridica: this.vecPessoaJuridica) {
			cnpjsCadastradosNoSistema += pessoaJuridica.getCNPJ() + "\n";
		}
		//Pergunta qual CNPJ o usuário quer excluir
		String cnpj = JOptionPane.showInputDialog(null, "CNPJs cadastrados no sistema:\n" + cnpjsCadastradosNoSistema,
				"Deletar cliente por CNPJ",
				JOptionPane.QUESTION_MESSAGE);
		//Verifica se o CNPJ que o usuário digitou existe no sistema
		for(PessoaJuridica pessoaJuridica: this.vecPessoaJuridica) {
			if(cnpj.equals(pessoaJuridica.getCNPJ())) {
				cnpjEncontrado = true;		
				break;
			}
		}
		//Verifica se o CNPJ foi encontrado, se sim ele é apagado, caso contrário, diz ao usuário que o cpf não foi encontrado
		if(!cnpjEncontrado){
			JOptionPane.showMessageDialog(null, "CNPJ não encontrado no sistema, verifique a digitação e tente novamente", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
		else{
			int iterador = 0;
			File arquivoOriginal = new File("./baseDados/clientes.txt");
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));

		    while(bufferedReader.ready()) {
		    	iterador++;
				if(cnpj.equals(bufferedReader.readLine())) { // Verifica todas as linhas do arquivo procurando o CNPJ	
					bufferedReader.close();
					for(int j = iterador + 3; j >= iterador - 8; j--) { // Apaga as informações do cliente juridico partindo da ultima linha para a primeira
						removeLinhaPorArquivo("./baseDados/clientes.txt", j);
					}
					JOptionPane.showMessageDialog(null, "Cliente com CNPJ " + cnpj + " deletado com sucesso", "Operação concluída",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
		    } // while
		} // else
	}
	
	public void excluirClientePorNome() throws IOException{
		String nomesCadastradosNoSistema = "";

		for(PessoaJuridica pessoaJuridica: this.vecPessoaJuridica) {
			nomesCadastradosNoSistema += pessoaJuridica.getNome() + "\n";
		}
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			nomesCadastradosNoSistema += pessoaFisica.getNome() + "\n";
		}
		
		String nome = JOptionPane.showInputDialog(null, "Nomes cadastrados no sistema:\n" + nomesCadastradosNoSistema,
				"Deletar cliente por nome",
				JOptionPane.QUESTION_MESSAGE);
		
		boolean nomeEncontrado = false;
		
		for(PessoaJuridica pessoaJuridica: this.vecPessoaJuridica) {
			if(nome.equals(pessoaJuridica.getNome())) {
				nomeEncontrado = true;		
				break;
			}
		}
		for(PessoaFisica pessoaFisica: this.vecPessoaFisica) {
			if(nome.equals(pessoaFisica.getNome())) {
				nomeEncontrado = true;		
				break;
			}
		}
		
		if(!nomeEncontrado) {
			JOptionPane.showMessageDialog(null, "Nome não encontrado no sistema, verifique a digitação e tente novamente", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			int iterador = 0;
			File arquivoOriginal = new File("./baseDados/clientes.txt");
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));

		    while(bufferedReader.ready()) {
		    	iterador++;
		    	
				if(nome.equals(bufferedReader.readLine())) { // Verifica todas as linhas do arquivo procurando o CNPJ	
			    	bufferedReader.close();
					if(retornaConteudoDaLinhaNoArquivo("./baseDados/clientes.txt", iterador - 1).equals("fisica")){
						for(int j = iterador + 9; j >= iterador - 1; j--) { // Apaga as informações do cliente juridico partindo da ultima linha para a primeira
							removeLinhaPorArquivo("./baseDados/clientes.txt", j);
						}
					}
					else if(retornaConteudoDaLinhaNoArquivo("./baseDados/clientes.txt", iterador - 1).equals("juridica")) {
						for(int j = iterador + 10; j >= iterador - 1; j--) { // Apaga as informações do cliente juridico partindo da ultima linha para a primeira
							removeLinhaPorArquivo("./baseDados/clientes.txt", j);
						}
					}
							
					JOptionPane.showMessageDialog(null, "Cliente com nome " + nome + " deletado com sucesso", "Operação concluída",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
		    	
		    } // while
		}
		
	}
	
	public String retornaConteudoDaLinhaNoArquivo(String caminhoArquivo, int linha) throws IOException{
		File arquivo = new File(caminhoArquivo);
		int iterador = 1;

	    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
	    while(bufferedReader.ready()) {
	    	String conteudoLinha = bufferedReader.readLine();
			if(iterador == linha) {	
				bufferedReader.close();
				return conteudoLinha;
			}
			iterador++;
		}
	    
		bufferedReader.close();
	    System.out.println("Linha não encontrada no arquivo, retornando vazio");
	    return "";
	}
	
	public void removeLinhaPorArquivo(String arquivo, int linhaParaApagar) throws IOException {
		File arquivoOriginal = new File(arquivo);
		File arquivoTemporario = new File("./baseDados/clientesTemp.txt");
		arquivoTemporario.createNewFile();
		int iterador = 1;

	    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));
		FileWriter fileWriter = new FileWriter(arquivoTemporario, true);
		

		while(bufferedReader.ready()) {
			if(iterador == linhaParaApagar) {	
				bufferedReader.readLine();
				iterador++;
				continue;
			}
			else {
				fileWriter.write(bufferedReader.readLine() + "\n");	
			}
			iterador++;
		}
		fileWriter.close(); 
		bufferedReader.close(); 
		
	  //Delete the original file
      if (!arquivoOriginal.delete()) {
        System.out.println("Could not delete file");
        return;
      }

      //Rename the new file to the filename the original file had.
      if (!arquivoTemporario.renameTo(arquivoOriginal))
        System.out.println("Could not rename file");

    }
}

 