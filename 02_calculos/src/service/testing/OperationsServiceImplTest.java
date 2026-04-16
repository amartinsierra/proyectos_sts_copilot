package service.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import service.implementations.OperationsServiceImpl;

class OperationsServiceImplTest {

	//Crear un test para el método verificar de la clase OperationsServiceImpl
	
	@Test
	void testVerificar() {
		OperationsServiceImpl service = new OperationsServiceImpl();
		List<Integer> lista = List.of(1, 2, 3, 4, 5);
		int n = 3;
		int resultado = service.verificar(n, lista);
		assertEquals(2, resultado);
	}
	
	//Crear un test para el método buscarMayores de la clase OperationsServiceImpl
	//no utilices nombres cualificados para las clases, solo el nombre de la clase sin el paquete
	@Test
	void testBuscarMayores() {
		OperationsServiceImpl service = new OperationsServiceImpl();
		List<Integer> lista = List.of(1, 2, 3, 4, 5);
		int n = 3;
		List<Integer> resultado = service.buscarMayores(n, lista);
		assertEquals(List.of(4, 5), resultado);
	}
	

}
