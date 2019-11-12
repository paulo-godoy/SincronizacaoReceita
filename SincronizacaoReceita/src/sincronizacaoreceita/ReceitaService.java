package sincronizacaoreceita;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 * @author felipe.correa<felipe.correa@compasso.com.br>
 */
public class ReceitaService {

	public boolean atualizarConta(Conta conta) throws RuntimeException, InterruptedException, IOException {
//se a conta for valida retorna o try se n�o o catch
		if (conta.contaValida()) {
			// Simula tempo de resposta do serviço (entre 1 e 5 segundos)

			long wait = Math.round(Math.random() * 4000) + 1000;
			Thread.sleep(wait);

			String linha = conta.getAgencia() + ";" + conta.getNumeroConta() + ";" + conta.getSaldo() + ";"
					+ conta.getStatus() + "\n";

			try {
				Files.write(Paths.get("certo.csv"), linha.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}

		} else {

			String linha = conta.getAgencia() + ";" + conta.getNumeroConta() + ";" + conta.getSaldo() + ";"
					+ conta.getStatus() + "\n";

			try {
				Files.write(Paths.get("error.csv"), linha.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}

			

		}
		// Simula cenario de erro no serviço (0,1% de erro do total de chamadas)

		long randomError = Math.round(Math.random() * 1000);
		if (randomError == 500) {
			throw new RuntimeException("Error");

		}
		return true;
	}

}
