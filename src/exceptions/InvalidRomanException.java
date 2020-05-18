package exceptions;

public class InvalidRomanException extends RuntimeException  {
	
	public InvalidRomanException() {
		super("Número de letras(X,I,M ou C) inválido");
	}
	

}
