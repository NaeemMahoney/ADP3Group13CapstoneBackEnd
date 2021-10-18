package za.ac.cput.services.general;

import za.ac.cput.entity.general.Address;
import za.ac.cput.services.iService;

import java.util.Set;

public interface iAddressServices extends iService<Address,String>
{
    public Set<Address> getAll();
}

