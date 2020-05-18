package implementacoes;

import org.junit.platform.commons.util.StringUtils;

import exceptions.InvalidRomanException;
import interfaces.IAlgarismo;

/**
 * Implementa��o do conversor de n�meros romanos;
 * 
 * Base para constru��o do algoritmo do Professor Francisco Edmundo;
 * 
 * @author Jequelia Santana
 */
public class JequeliaAlgorismo implements IAlgarismo {

	// Constantes para tradu��o do n�mero romano
	private final static Integer BASE_ROMANO = 10;
	private final static Integer MULT_GRUPO_ROMANO_DE_5 = 5;
	private final static String GRUPO_ROMANOS_DE_1 = "IXCM";
	private final static String GRUPO_ROMANOS_DE_5 = "VLD";
	// Valor dos n�meros romanos
	private final static char ROMANO_MIL = 'M';
	private final static char ROMANO_CEM = 'C';
	private final static char ROMANO_DEZ = 'X';
	private final static char ROMANO_UM = 'I';
	// Valida��o dos n�meros
	private final static char LIMITE_MAX_DE_REP = 4;

	@Override
	public int conversor(String romano) {
		int valorFinal = 0;
		int numeroAnterior = 0;

		validarRomano(romano);

		for (int i = romano.length() - 1; i >= 0; i--) {
			char romanoDaSequencia = romano.charAt(i);
			int numeroAtual = traduzirNumeralRomano(romanoDaSequencia);

			valorFinal += numeroAtual * verificarPosicaoDoNumero(numeroAtual, numeroAnterior);
			numeroAnterior = numeroAtual;

		}

		return valorFinal;
	}
	
	/**
	 * Verifica se existe algum caractere no numero romamo que se repta mais de 3 vezes
	 * se sim, lan�a uma exception. Verifica tamb�m se a entrada � nula. 
	 * @param numeroRomano - n�mero de entrada;
	 */
	private void validarRomano(String numeroRomano) {
		if (StringUtils.isBlank(numeroRomano)) {
			throw new InvalidRomanException();
		}
		char[] caracteres = numeroRomano.toCharArray();

		int m = 0;
		int x = 0;
		int i = 0;
		int c = 0;

		for (int y = 0; y < caracteres.length; y++) {
			if (caracteres[y] == ROMANO_MIL) {
				m++;
			} else if (caracteres[y] == ROMANO_DEZ) {
				x++;
			} else if (caracteres[y] == ROMANO_UM) {
				i++;
			} else if (caracteres[y] == ROMANO_CEM) {
				c++;
			}
		}
		if (c >= LIMITE_MAX_DE_REP || x >= LIMITE_MAX_DE_REP || i >= LIMITE_MAX_DE_REP || m >= LIMITE_MAX_DE_REP) {
			throw new InvalidRomanException();
		}
	}

	/**
	 * Ele soma o valor do metodo
	 * @param caractere - letra separada da string de entrada;
	 */
	private int traduzirNumeralRomano(char caractere) {
		return traduzirRomanoPorGrupo(caractere, GRUPO_ROMANOS_DE_1)
				+ (MULT_GRUPO_ROMANO_DE_5 * traduzirRomanoPorGrupo(caractere, GRUPO_ROMANOS_DE_5));
	}
	
	
	/**
	 * Ele faz o calculo d
	 * @param romano - caractere romano ;
	 * @param grupo - base de 1 ou de 5 ;
	 * @return int - retorna o valor da base.
	 */
	private int traduzirRomanoPorGrupo(char romano, String grupo) {
		return (int) Math.floor(Math.pow(BASE_ROMANO, grupo.indexOf(romano)));
	}

	/**
	 * Verifica se o n�mero atual � menor ou maior que o n�mero anterior, se o
	 * n�mero atual for menor que o n�mero anterior retorna -1, caso contr�rio +1
	 * 
	 * Quando +1 o n�mero est� a direita; Quando -1 o n�mero est� a esquerda;
	 * 
	 * @param numeroAtual    - n�mero da sequ�ncia;
	 * @param numeroAnterior - n�mero anterior da sequ�ncia;
	 * @return int - sendo -1 quando negativo ou +1 quando positivo.
	 */
	private int verificarPosicaoDoNumero(int numeroAtual, int numeroAnterior) {
		return numeroAtual >= numeroAnterior ? 1 : -1;
	}

}
