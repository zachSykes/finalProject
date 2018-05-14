/**
 * 
 */
package csci4380.finals.cats.mvc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import csci4380.finals.cats.jpa.model.Cat;
import csci4380.finals.cats.jpa.repo.CatRepository;


/**
 * @author zacharysykes
 *
 */
@Controller
@RequestMapping("/mvc/cat")
public class CatController 
{
		@Autowired
		private CatRepository catRepository;
		
//		@RequestMapping(value="/echoMessage", method=RequestMethod.GET)
		@RequestMapping("/echoMessage") // NOTE ilker, equivalent to above line since default is GET
//		@GetMapping("/echoMessage")		// NOTE ilker, equivalent to above line as well
		@ResponseBody					// NOTE ilker, means this method is not returning name of "view", just pass whatever it returns to Http Response
		/**
		 * http://localhost:8888/mvc/cat/echoMessage?msg=Hi
		 */
		public String echoMessage(@RequestParam(value="msg", defaultValue="Zach") String message) {
			return "echoMessage echoes: " + message;
		}
	
		
		@GetMapping("/list")
		public String showCatList(Model model, @RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="3") int size) {
			Page<Cat> catsPage = catRepository.findAll(new PageRequest(page, size));
			model.addAttribute("catsData", catsPage);
			model.addAttribute("currentPage", page);	// for UI nav-pills to high light "active" page
			model.addAttribute("rowsPerPage", size);	// example of passing 2 uri params
			return "catList";
		}
		
		// /mvc/cat/save    POST
		@PostMapping("/save")
		public String saveCat(Cat cat) {
			catRepository.save(cat);
			return "redirect:/mvc/cat/list";	
		}
		
		@GetMapping("/delete")
		public String deleteCat(Integer petId) {
			catRepository.deleteById(petId);
			return "redirect:/mvc/cat/list";	
		}
		
		
		// NOTE ilker this is equivalent to findOne or findById
		@GetMapping("/detail")	// /mvc/cat/detail
		@ResponseBody			// NOTE ilker since will be using jQuery for this, let it return the StudentEntity
		public Cat showCatDetail(Integer petId) {
			Optional<Cat> cat = catRepository.findById(petId);
			return cat.isPresent() ? cat.get() : null;
		}
	}

