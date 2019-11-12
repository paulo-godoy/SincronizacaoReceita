/*
Teste admissÃ£o Sicredi
CenÃ¡rio atual de negÃ³cio: (Banco -- SincronizaÃ§Ã£o da Receita Federal)
Todo dia Ãºtil por volta das 8 horas da manhÃ£ um colaborador do Banco recebe e compila as informaÃ§Ãµes de todas agencias em um arquivos excel. Hoje o Sicredi jÃ¡ possiu mais de 4 milhÃµes de contas ativas.
Esse usuÃ¡rio exporta os dados em um arquivo CSV para ser enviada para a Receita Federal, antes as 10:00 da manhÃ£ na abertura das
agÃªncias.

-- Requisito:
Usar o serviÃ§o da receita (ReceitaService) para processamento automÃ¡tico do arquivo.

Funcionalidade:
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualizaÃ§Ã£o para a Receita atravÃ©s do serviÃ§o (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualizaÃ§Ã£o da Receita (true ou false, sucesso ou falha) em uma nova coluna.


Regras do atualizarConta
1. atualizarConta(String agencia, String conta, double saldo, String status)
    1.1 - agencia bancaria deve ter 4 digitos, sendo eles sempre numÃ©ricos
    1.2 - conta deve ter 6 digitos, todos numÃ©ricos, podendo ter apenas o caractere X em qualquer posiÃ§Ã£o da String
        1.2.1 - ATENÃ‡ÃƒO! No arquivo CSV as contas vÃªm com um hÃ­fen (-) antes do dÃ­gito verificador, porÃ©m a API Simulada
                nÃ£o aceita este caractere.
    1.3 - Saldo deve ser um double, podendo ser negativo. FaÃ§a as alteraÃ§Ãµes necessÃ¡rias para que isso possa ocorrer.
    1.4 - Status Ã© um string, cujos valores vÃ¡lidos estÃ£o definidos na API Simulada, valores invÃ¡lidos (erro humano) devem
          assumir um valor default de A.

2. Os erros devem ser gravados na nova coluna. O serviÃ§o deve ser resiliente a erros, pois existe uma taxa conhecida de 0,1%
    de erro.

3. O tempo de execuÃ§Ã£o tem que ser rÃ¡pido e acontecer antes da agÃªncia abrir.

Exemplo de CSV:
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

NÃƒO SERÃ� FORNECIDO UM ARQUIVO CSV, MAS DEVE-SE PARTIR DA PREMISSA DO EXEMPLO ACIMA.
O CÃ“DIGO DEVE SER ORGANIZADO E SALVAR O ARQUIVO NA MESMA PASTA QUE O PROJETO ESTÃ�.
*/
package sincronizacaoreceita;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SincronizacaoReceita {

	public static void main(String[] args) throws RuntimeException, InterruptedException, IOException {

		boolean contaValida = true;
		//Limpa o arquivo certo.csv
		try {
			Files.write(Paths.get("certo.csv"), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

		// Exemplo -- ApÃ³s ler do arquivo CSV
		ReceitaService receitaService = new ReceitaService();
		// receitaService.atualizarConta("0101", "123456", 100.50, "A");

		// Fluxo de Entrada com Arquivo
		InputStream fis = new FileInputStream("arquivo.csv");
		Reader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		// Conferir agencia
		String linha = br.readLine();
// aqui enquanto a linha não for nula continua executando
		while (linha != null) {

			String[] linhas = linha.split(";");

			Conta conta = new Conta(linhas[0], linhas[1], linhas[2], linhas[3]);

			receitaService.atualizarConta(conta);

			linha = br.readLine();

		}

		br.close();
	}

}
