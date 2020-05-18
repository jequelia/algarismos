package testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.InvalidRomanException;
import implementacoes.JequeliaAlgorismo;
import interfaces.IAlgarismo;

public class TesteAlgarismo {
	
	private static IAlgarismo teste;
	
	@BeforeAll
	public static void injetarTeste() {
		teste = new JequeliaAlgorismo();
	}

	@Test
	void conversorRomanoTeste() {
		Assertions.assertEquals(25, teste.conversor("XXV"));
	}
	
	@Test
	void conversorRomanoSomatorioTeste() {
		Assertions.assertEquals(8, teste.conversor("VIII"));
	}
	
	@Test
	void conversorRomanoSomatorio2Teste() {
		Assertions.assertEquals(62, teste.conversor("LXII"));
	}
	
	@Test
	void conversorRomanoSomatorio3Teste() {
		Assertions.assertEquals(158, teste.conversor("CLVIII"));
	}
	
	@Test
	void conversorRomanoSomatorio4Teste() {
		Assertions.assertEquals(1120, teste.conversor("MCXX"));
	}
	
	@Test
	void conversorRomanoSubtracaoTeste() {
		Assertions.assertEquals(90, teste.conversor("XC"));
	}
	
	@Test
	void conversorRomanoSubtracaoTeste2() {
		Assertions.assertEquals(9, teste.conversor("IX"));
	}
	
	@Test
	void conversorRomanoSubtracaoTeste3() {
		Assertions.assertEquals(4, teste.conversor("IV"));
	}

	@Test
	void regraNumeroRepetido() {
		Assertions.assertThrows(InvalidRomanException.class, () -> {
			teste.conversor("MMMM");
		});
	}
	
	@Test
	void regraNumeroVazio() {
		Assertions.assertThrows(InvalidRomanException.class, () -> {
			teste.conversor("");
		});
	}
	
	@Test
	void regraNumeroNulo() {
		Assertions.assertThrows(InvalidRomanException.class, () -> {
			teste.conversor(null);
		});
	}
}
