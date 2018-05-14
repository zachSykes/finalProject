/**
 * 
 */
package csci4380.finals.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import csci4380.finals.dogs.mongodb.model.Dog;
import csci4380.finals.dogs.mongodb.repo.DogRepository;

/**
 * @author zacharysykes
 *
 */
@Component
public class InitDogDb implements CommandLineRunner
{
	private DogRepository dogRepository;
	
	public InitDogDb(DogRepository dogRepository)
	{
		this.dogRepository = dogRepository;
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		Dog dogOne = new Dog (101, "Barnabas", "Mix", "Zach", "12 Country Acres Drive", 1, false, "12/16/2017");
		Dog dogTwo = new Dog (201, "Ranger", "Beagle", "Becca", "206 Clover Lane", 6, true, "11/10/2012");
		
		List<Dog> dogs = Arrays.asList(dogOne, dogTwo);
		dogRepository.saveAll(dogs);
		
	}
}
