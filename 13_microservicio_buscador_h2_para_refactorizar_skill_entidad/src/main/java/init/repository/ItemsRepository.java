package init.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Item;

public interface ItemsRepository extends JpaRepository<Item,Integer>{
	List<Item> findByTematica(String tematica);
	Optional<Item> findFirstByUrl(String url);
	@Transactional
	@Modifying
	void deleteByTematica(String tematica);

	
	@Query(value = "select count(*) from items where tematica=?1",nativeQuery = true)
	int countByTematica(String tematica);
}
