package za.ac.cput.services.medication;

//Na'eem Mahoney
//218190751
//ADP3
//Capstone - Back-End
//Service Class for Item Entity

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.medication.Item;
import za.ac.cput.repository.medication.ItemRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService implements iItemService{

    private static ItemService itemService = null;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item create(Item item){
        return this.itemRepository.save(item);
    }

    @Override
    public Item read(String itemID){
        return this.itemRepository.findById(itemID).orElse(null);
    }

    @Override
    public Item update(Item item){
        if (this.itemRepository.existsById(item.getItemID())){
        return this.itemRepository.save(item);}
        return null;
    }

    @Override
    public boolean delete(String itemID){
        this.itemRepository.deleteById(itemID);
        if(this.itemRepository.existsById(itemID)){
            System.out.println("Item: "+itemID+" not Deleted");
            return false;
        }
        else{
            System.out.println("Item Deleted");
            return true;
        }
    }

    @Override
    public Set<Item> getAll(){return this.itemRepository.findAll().stream().collect(Collectors.toSet());}
}
