package za.ac.cput.controller.medication;

//Na'eem Mahoney
//218190751
//ADP3
//Capstone - Back-End
//Controller Class for Item Entity

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.medication.Item;
import za.ac.cput.factory.medication.ItemFactory;
import za.ac.cput.services.medication.ItemService;

import java.util.Set;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Item create(@RequestBody Item item){
        Item newItem = ItemFactory.build(item.getItemName(), item.getItemType(), item.getItemPrice(), item.getItemStock());
        return itemService.create(newItem);
    }

    @RequestMapping("/read/{id}")
    public Item read(@PathVariable String id){
        return itemService.read(id);
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item){
        return itemService.update(item);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        return itemService.delete(id);
    }

    @GetMapping("/getall")
    public Set<Item> getItems(){
        return itemService.getAll();
    }
}
