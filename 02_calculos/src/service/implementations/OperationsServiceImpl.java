package service.implementations;

import java.util.List;

import service.interfaz.OperationsService;

public class OperationsServiceImpl implements OperationsService {

	@Override
	public int verificar(int n, List<Integer> lista) {
		//Crear el código necesario para devolver el total de números mayores a n en la lista
		//utiliza programación funcional con streams para contar los números mayores a n en la lista
		return (int) lista.stream()
				.filter(x -> x > n)
				.count();
	}

	@Override
	public List<Integer> buscarMayores(int n, List<Integer> lista) {
		//Crear el código necesario para devolver una lista con los números mayores a n
		//utiliza programación funcional con streams para filtrar los números mayores a n en la lista
		return lista.stream()
				.filter(x -> x > n)
				.toList();
	}

}
