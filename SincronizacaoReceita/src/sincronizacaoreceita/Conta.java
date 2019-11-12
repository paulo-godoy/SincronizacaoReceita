package sincronizacaoreceita;

import java.util.ArrayList;
import java.util.List;

public class Conta {

	private String agencia;
	private String numeroConta;
	private double saldo;
	private String status;
	private boolean saldoValido;

	public Conta(String agencia, String numeroConta, String saldo, String status) {
		System.out.println(agencia);
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.status = pegarStatusValidado(status);
		this.saldoValido = validaStrDouble(saldo);

		if (saldoValido) {
			this.saldo = convertStringDouble(saldo);
		} else {
			this.saldo = 0.0;
		}

	}

	String pegarStatusValidado(String s) {
		List<String> tipos = new ArrayList<String>();
		tipos.add("A");
		tipos.add("I");
		tipos.add("B");
		tipos.add("P");

		String s2 = s;
		boolean validado = false;
		for (String status : tipos) {
			// se ele passar por aqui está tudo certo
			if (s2.equals(status)) {
				s2 = s;
				validado = true;
				break;
			}
		}
		// se não passar acima cai aqui é muda pra "A"
		if (!validado) {

			s2 = "A";
		}

		return s2;
	}

	//valida se foi substituido  virgula saldo por ponto
	public boolean validaStrDouble(String s) {
		String str = s.replaceAll(",", ".");
		try {
			Double minhaVariavelString = Double.parseDouble(str);
			System.out.println("Linha executada com sucesso!");
			return true;

		} catch (NumberFormatException e) {
			System.out.println("Dados inválidos!");
			return false;
		}

	}
//aqui ele converte a a virgula do do saldo por ponto
	static Double convertStringDouble(String s) {
		String str = s.replaceAll(",", ".");
		return Double.parseDouble(str);
	}

	public boolean contaValida() {
		return agenciaValida() && numeroContaValida() && saldoValido;
	}
// aqui ele compara se a agencia é igual a str e tamanho da agencia e == 4
	public boolean agenciaValida() {
		String str = this.agencia.replaceAll("\\D+", "");

		if (this.agencia.equals(str) && this.agencia.length() == 4) {
			return true;
		}
		return false;
	}

	public boolean numeroContaValida() {

		String str = numeroConta.replaceAll("xX", "0");
		String strTraco = str.replaceAll("-", "");
		String strNumber = str.replaceAll("\\D+", "");

		int compara = str.length() - strTraco.length();

		if (compara <= 1 && strNumber.length() == strTraco.length() && strNumber.length() == 6) {
			return true;
		} else {
			return false;
		}

	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
