package za.ac.cput.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.entity.general.Address;


public interface AddressRepository extends JpaRepository<Address,String>
{

}
