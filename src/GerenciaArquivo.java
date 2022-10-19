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
	
}
