
/**
 * 
 */
package csci4380.finals.dogs.mongodb.repo;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csci4380.finals.dogs.mongodb.model.Dog;


/**
 * @author zacharysykes
 *
 */
@Repository
public interface DogRepository extends MongoRepository<Dog, String> 
{
	public Optional<List<Dog>> findByPetId(Integer petId);
	public Optional<List<Dog>> findByNameOrOwnerName(String name, String ownerName);
	public void deleteByPetId(Integer petId);

	
	@Query
	public Dog findByTypeIgnoreCase(@Param("type") String type);
	@Query("{'type': ?0}")
	public List<Dog> findMyByType(String type);

	

	
	

}
