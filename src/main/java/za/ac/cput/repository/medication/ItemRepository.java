package za.ac.cput.repository.medication;

//Na'eem Mahoney
//218190751
//ADP3
//Capstone - Back-End
//Repository for Item Entity

import java.util.Set;
import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.medication.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{

}
