package ch.bbw.personenverwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class MainController {
    private PersonRepository personRepository;

    @Autowired
    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        System.out.println(personRepository.findAll());
        return "index";
    }

    @GetMapping("/person/{id}")
    public String article(@PathVariable long id, Model model) {
        model.addAttribute("person", personRepository.findById(id));
        return "person";
    }

    @PostMapping("/person/{id}")
    public String submitIndex(@ModelAttribute Person person, @PathVariable long id) {
        return "person";
    }
}
