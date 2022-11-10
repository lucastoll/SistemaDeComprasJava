import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciaArquivo {

	public String retornaConteudoDaLinhaNoArquivo(String caminhoArquivo, int linha) throws IOException{
		// Função que obtem um arquivo e retorna o conteudo de uma determinada linha
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
		// Recebe o arquivo original e cria um arquivo temporario
		File arquivoOriginal = new File(arquivo);
		File arquivoTemporario = new File("./baseDados/clientesTemp.txt");
		arquivoTemporario.createNewFile();
		int iterador = 1; // Arquivo ja começa na linha 1
		// Declara um reader para o arquivo original e um writer para o temporario
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoOriginal));
		FileWriter fileWriter = new FileWriter(arquivoTemporario, true);
		// Percorre o arquivo original e verifica se a linha atual é a linha que precisa ser apagada, caso verdadeiro não escreve nada, caso falso escreve a linha
		while(bufferedReader.ready()) {
			if(iterador == linhaParaApagar) {	
				bufferedReader.readLine();
				iterador++;
				continue;
			}
			else { // Apenas as linhas diferentes da linha para apagar são escritas no arquivo temporario
				fileWriter.write(bufferedReader.readLine() + "\n");	
			}
			iterador++;
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
	
	public void alteraTotalPagoNoArquivo(String id, String pagamento, float valorTotalPago) throws IOException{
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
				fileWriter.write((Float.parseFloat(pagamento) + valorTotalPago) + "\n");
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
	
}
