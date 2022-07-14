package ch.bbw.personenverwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.regex.Pattern;

@Controller
public class MainController {
    private final PersonRepository personRepository;

    public static boolean testEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }


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
    public String submitPerson(@Valid Person person, BindingResult bindingResult, @PathVariable long id, Model model) {
        if(!testEmail(person.getEmail())) {
            model.addAttribute("error", "Email falsch");
        }
        if (bindingResult.hasErrors()) {
            return "person";
        }

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
    public String addsubmitPerson(@Valid Person person, BindingResult bindingResult, Model model) {
        if(!testEmail(person.getEmail())) {
            model.addAttribute("error", "Email falsch");
        }
        if (bindingResult.hasErrors()) {
            return "add";
        }

        personRepository.save(person);
        return "add";
    }

}
