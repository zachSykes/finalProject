/**
 * 
 */
package csci4380.finals.cats.jpa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import csci4380.finals.cats.jpa.model.Cat;

/**
 * @author zacharysykes
 *
 */
public interface CatRepository extends JpaRepository<Cat, Integer> 
{
	public Optional<List<Cat>> findByNameOrOwnerName(String name, String ownerName);
	@Query
	public Cat findByTypeIgnoreCase(@Param("type") String type);
	public void deleteByPetId(Integer petId);

}
