package za.ac.cput.controller.general;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.general.Address;
import za.ac.cput.factory.general.AddressFactory;
import za.ac.cput.services.general.AddressService;

import java.util.Set;

@RestController
@RequestMapping("/address")
public class AddressController
{
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Address create(@RequestBody Address address)
    {
        Address newAddress = AddressFactory.build(address.getHouseNumber(),address.getStreetName(),address.getSuburb(),address.getPostalCode());
        return addressService.create(newAddress);
    }

    @RequestMapping("/read/{id}")
    public Address read(@PathVariable String id){
        return addressService.read(id);
    }

    @PostMapping("/update")
    public Address update(@RequestBody Address address){
        return addressService.update(address);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        return addressService.delete(id);
    }

    @GetMapping("/getall")
    public Set<Address> getAddress(){
        return addressService.getAll();
    }
}
