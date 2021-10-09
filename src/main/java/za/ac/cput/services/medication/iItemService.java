package za.ac.cput.services.medication;

//Na'eem Mahoney
//218190751
//ADP3
//Capstone - Back-End
//Service Class that extends iService for Item Entity

import za.ac.cput.entity.medication.Item;
import za.ac.cput.services.iService;
import java.util.Set;

public interface iItemService extends iService<Item, String>{
    public Set<Item> getAll();
}
