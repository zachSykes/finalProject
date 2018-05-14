/**
 * 
 */
package csci4380.finals.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import csci4380.finals.cats.jpa.model.Cat;
import csci4380.finals.cats.jpa.repo.CatRepository;

/**
 * @author zacharysykes
 *
 */
@Component
public class InitCatDb implements CommandLineRunner
{
	private CatRepository catRepository;
	
	@Autowired
	public InitCatDb(CatRepository catRepository)
	{
		super();
		this.catRepository = catRepository;
}
@Override
public void run(String... args) throws Exception
{
	Cat catOne = new Cat("Gibbs", "Short Hair", "Jen", "12 Country Acres Drive", 2, true, "4/20/2016");
	Cat savedCatOne = catRepository.save(catOne);
	System.out.println("-----> GIBBS -----> Saved catOne");
	
	Cat catTwo = new Cat("Ziva", "Short Hair", "Joe", "12 Country Acres Drive", 1, false, "12/24/2017");
	Cat savedCatTwo = catRepository.save(catTwo);
	System.out.println("-----> ZIVA -----> Saved catTwo");

	
}
}
