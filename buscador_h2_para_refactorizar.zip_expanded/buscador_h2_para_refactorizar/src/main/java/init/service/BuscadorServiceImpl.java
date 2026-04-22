package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Item;
import init.repository.ItemsRepository;
@Service
public class BuscadorServiceImpl implements BuscadorService {
	@Autowired
	ItemsRepository itemsRepository;	
	
	@Override
	public List<Item> buscarPorTematica(String tematica) {
		return itemsRepository.findByTematica(tematica);
	}
	@Override
	public boolean nuevoItem(Item item) {
		if(itemsRepository.findFirstByUrl(item.getUrl())==null) {
			itemsRepository.save(item);
			return true;
		}
		return false;
	}

}
