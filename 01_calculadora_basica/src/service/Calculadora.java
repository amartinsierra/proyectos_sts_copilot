package service;

public class Calculadora {
	//Crea un método para sumar dos números enteros y retornar el resultado
	public int sumar(int a, int b) {
		return a + b;
	}
	//Crea un método para restar dos números enteros y retornar el resultado
	public int restar(int a, int b) {
		return a - b;
	}
	//Crea un método para multiplicar dos números enteros y retornar el resultado
	public int multiplicar(int a, int b) {
		return a * b;
	}
	//Crea un método para dividir dos números enteros y retornar el resultado
	public int dividir(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("No se puede dividir por cero");
		}
		return a / b;
	}
	//crear un método que calcule el factorial de un número entero y retorne el resultado
	//utilice programación funcional con streams para calcular el factorial
	public int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("No se puede calcular el factorial de un número negativo");
		}
		if (n == 0) {
			return 1;
		}
		return java.util.stream.IntStream.rangeClosed(1, n)
				.reduce(1, (x, y) -> x * y);
	}
	
}
