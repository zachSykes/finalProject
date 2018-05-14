/**
 * 
 */
package csci4380.finals.dogs.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import csci4380.finals.dogs.mongodb.model.Dog;
import csci4380.finals.dogs.mongodb.repo.DogRepository;

/**
 * @author zacharysykes
 *
 */
@RestController
@RequestMapping("/rest/v1/dogs")
public class DogRestController 
{
	private DogRepository dogRepository;
	public DogRestController(DogRepository dogRepository)
	{
		this.dogRepository = dogRepository;
	}
	
	@GetMapping("/echoMessage")
	public String echoMessage(@RequestParam(value="msg", defaultValue="Zach") String message)
	{
		return "echoMessage echoes: " + message;
	}
	
	@GetMapping("")
	public Page<Dog> finalAll(@RequestParam(defaultValue = "0") int page, @RequestParam(value = "rowPerPage", defaultValue = "3") int size)
{
	Page<Dog> dogsPage = dogRepository.findAll(new PageRequest(page, size));
	return dogsPage;
}

@GetMapping("/all")
public List<Dog> findAll()
{
	List<Dog> dogs = dogRepository.findAll();
	return dogs;
}

@PostMapping("")
public Optional<Dog> save(@RequestBody final Dog dog)
{
	Dog savedDog = dogRepository.save(dog);
	return dogRepository.findById(savedDog.getId());
}

@PutMapping("")
public Dog insert(@RequestBody final Dog dog)
{
	Dog insertedDog = dogRepository.insert(dog);
	return insertedDog;
}

@GetMapping("/{id}")
public Optional<Dog> findbyId(@PathVariable String id)
{
	Optional<Dog> dog = dogRepository.findById(id);
	return dog;
}

@GetMapping("/petId/{petId}")
	public  Optional<List<Dog>> findByPetId(@PathVariable Integer petId)
{
		Optional<List<Dog>> dogs = dogRepository.findByPetId(petId);
		return dogs;
}

@DeleteMapping("/{id}")
public void delete(@PathVariable("id") String id)
{
	dogRepository.deleteById(id);
}

@DeleteMapping("/petId/{petId}")
public void delete(@PathVariable("petId") Integer petId)
{
	dogRepository.deleteByPetId(petId);
}

@GetMapping("/byNameorOwnerName/{name}/{ownerName}")
public Optional<List<Dog>> findByNameOrOwnerName(@PathVariable String name, @PathVariable String ownerName)
{
	Optional<List<Dog>> dogs = dogRepository.findByNameOrOwnerName(name, ownerName);
	return dogs;
}

@GetMapping("findByTypeIgnoreCase/{type}")
public Dog findByTypeIgnoreCase(@PathVariable String type)
{
	Dog dog = dogRepository.findByTypeIgnoreCase(type);
	return dog;
}
@GetMapping("/byType/{type}")
public List <Dog> findMyByType(@PathVariable String type)
{
	List<Dog> dogs = dogRepository.findMyByType(type);
	return dogs;
}


}
