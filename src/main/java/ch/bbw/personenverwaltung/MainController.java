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

    @PostMapping("/")
    public String submitIndex(@ModelAttribute Person person, @PathVariable long id) {
        personRepository.delete(person);
        return "index";
    }

    @GetMapping("/person/{id}")
    public String article(@PathVariable long id, Model model) {
        model.addAttribute("person", personRepository.findById(id));
        return "person";
    }

    @PostMapping("/person/{id}")
    public String submitPerson(@ModelAttribute Person person, @PathVariable long id) {
        personRepository.save(person);
        return "person";
    }
}
