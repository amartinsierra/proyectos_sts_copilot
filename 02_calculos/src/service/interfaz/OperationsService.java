package service.interfaz;

import java.util.List;

public interface OperationsService {
	int verificar(int n, List<Integer> lista);
	List<Integer> buscarMayores(int n, List<Integer> lista);
}
