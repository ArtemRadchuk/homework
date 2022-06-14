package controller;

import model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.impl.AuthorServiceImpl;

@Controller
public class AuthorController {
    private AuthorServiceImpl authorService;

    @PostMapping(value = "/author/add")
    public String add(@RequestParam String name, Model model) {
        model.addAttribute("author", authorService.createAuthor(name));
        return "addAuthor";
    }

    @GetMapping(value = "/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.authorList());
        return "authorIndex";
    }

    @GetMapping(value = "/author/delete/{id}")
    public void deleteAuthorById(@PathVariable("id") int id) {
        authorService.deleteAuthorById(id);
    }

/*    @GetMapping(value = "/author/delete/{author}")
    public void deleteAuthor(@PathVariable("author") Author author) {
        authorService.deleteAuthor(author);
    }*/

}
