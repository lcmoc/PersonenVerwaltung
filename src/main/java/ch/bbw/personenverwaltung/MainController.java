package ch.bbw.personenverwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final PersonRepository personRepository;

    @Autowired
    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "index";
    }

    @GetMapping("/person/{id}")
    public String person(@PathVariable long id, Model model) {
        model.addAttribute("person", personRepository.findById(id));
        return "person";
    }

    @PostMapping("/person/{id}")
    public String submitPerson(@ModelAttribute Person person, @PathVariable long id) {
        personRepository.save(person);
        return "person";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("person", personRepository.findById(id));
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@ModelAttribute Person person, @PathVariable long id) {
        personRepository.delete(person);
        return "delete";
    }
    @GetMapping("/add")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "add";
    }

    @PostMapping("/add")
    public String addsubmitPerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "add";
    }

}
