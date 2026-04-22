package init.service;

import java.util.List;

import init.model.Item;

public interface BuscadorService {
	List<Item> buscarPorTematica(String tematica);
	boolean nuevoItem(Item item);
}
