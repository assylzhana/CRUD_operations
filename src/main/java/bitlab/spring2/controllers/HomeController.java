package bitlab.spring2.controllers;

import bitlab.spring2.DB;
import bitlab.spring2.models.Item;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model){
        List<Item> item =  DB.getItems();
        model.addAttribute("laptop",item);
        return "home";
    }
    @PostMapping("/add-item")
    public String add(Item item){
        DB.addItem(item);
        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id,Model model){
        Item item = DB.getItemById(id);
        model.addAttribute("zat",item);
        return "details";
    }
    @PostMapping("/edit-item/{id}")
    public String edit(@PathVariable Long id, @RequestParam(name = "name") String name,
                       @RequestParam(name = "description") String description,
                       @RequestParam(name="price") double price){
        Item item = DB.getItemById(id);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        return "redirect:/details/"+id;
    }
    @PostMapping("/delete-item/{id}")
    public String delete(@PathVariable Long id){
        DB.deleteItem(id);
        return "redirect:/";
    }
}
