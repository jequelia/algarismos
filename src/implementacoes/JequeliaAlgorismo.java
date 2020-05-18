package implementacoes;

import org.junit.platform.commons.util.StringUtils;

import exceptions.InvalidRomanException;
import interfaces.IAlgarismo;

/**
 * Implementação do conversor de números romanos;
 * 
 * Base para construção do algoritmo do Professor Francisco Edmundo;
 * 
 * @author Jequelia Santana
 */
public class JequeliaAlgorismo implements IAlgarismo {

	// Constantes para tradução do número romano
	private final static Integer BASE_ROMANO = 10;
	private final static Integer MULT_GRUPO_ROMANO_DE_5 = 5;
	private final static String GRUPO_ROMANOS_DE_1 = "IXCM";
	private final static String GRUPO_ROMANOS_DE_5 = "VLD";
	// Valor dos números romanos
	private final static char ROMANO_MIL = 'M';
	private final static char ROMANO_CEM = 'C';
	private final static char ROMANO_DEZ = 'X';
	private final static char ROMANO_UM = 'I';
	// Validação dos números
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
	 * se sim, lança uma exception. Verifica também se a entrada é nula. 
	 * @param numeroRomano - número de entrada;
	 */
	private void validarRomano(String numeroRomano) {
		if (StringUtils.isBlank(numeroRomano)) {
			throw new InvalidRomanException();
		}
		char[] caracteres = numeroRomano.toCharArray();

		int letra_m = 0;
		int letra_x = 0;
		int letra_i = 0;
		int letra_c = 0;

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
		if (letra_c >= LIMITE_MAX_DE_REP || 
		    letra_x >= LIMITE_MAX_DE_REP || 
		    letra_i >= LIMITE_MAX_DE_REP || 
		    letra_m >= LIMITE_MAX_DE_REP) {
			throw new InvalidRomanException();
		}
	}

	/**
	 * Ele traduz o caractere pelos dois grupos para chegar ao valor indo-arábicos
	 * @param caractere - letra separada da string de entrada;
	 * @return int - retorna o caractere romano traduzido para indo-arábicos.
	 */
	private int traduzirNumeralRomano(char caractere) {
		return traduzirRomanoPorGrupo(caractere, GRUPO_ROMANOS_DE_1)
				+ (MULT_GRUPO_ROMANO_DE_5 * traduzirRomanoPorGrupo(caractere, GRUPO_ROMANOS_DE_5));
	}
	
	
	/**
	 * Ele pega o caractere romano e retorna a posição dele
	 * no grupo de romanos(GRUPO_ROMANOS_DE_1 ou GRUPO_ROMANOS_DE_5)
	 * depois eleva o numero da BASE_ROMANO(10). 
	 * Ex: romano = V(5)
	 * grupo romano = 5
	 * base romano = 10
	 * o V ta na posição 0
	 * resultado = 10^0 
	 * 
	 * @param romano - caractere romano ;
	 * @param grupo - base de 1 ou de 5 ;
	 * @return int - retorna o valor 
	 */
	private int traduzirRomanoPorGrupo(char romano, String grupo) {
		return (int) Math.floor(Math.pow(BASE_ROMANO, grupo.indexOf(romano)));
	}

	/**
	 * Verifica se o número atual é menor ou maior que o número anterior, se o
	 * número atual for menor que o número anterior retorna -1, caso contrário +1
	 * 
	 * Quando +1 o número está a direita; Quando -1 o número está a esquerda;
	 * 
	 * @param numeroAtual    - número da sequência;
	 * @param numeroAnterior - número anterior da sequência;
	 * @return int - sendo -1 quando negativo ou +1 quando positivo.
	 */
	private int verificarPosicaoDoNumero(int numeroAtual, int numeroAnterior) {
		return numeroAtual >= numeroAnterior ? 1 : -1;
	}

}
