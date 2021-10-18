package za.ac.cput.services.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.general.Address;
import za.ac.cput.repository.general.AddressRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service

    public class AddressService implements iAddressServices {
        private static AddressService addressService = null;

        @Autowired
        private AddressRepository addressRepository;

        @Override
        public Address create(Address address)
        {
            return this.addressRepository.save(address);
        }

        @Override
        public Address read(String addressNumber) {
            return this.addressRepository.findById(addressNumber).orElse(null);
        }

        @Override
        public Address update(Address address) {
            if (this.addressRepository.existsById(address.getAddressNumber())) {
                return this.addressRepository.save(address);
            }
            return null;
        }

        @Override
        public boolean delete(String addressNumber) {
            this.addressRepository.deleteById(addressNumber);
            if (this.addressRepository.existsById(addressNumber)) {
                System.out.println("Address: " + addressNumber + " was not deleted successfully");
                return false;
            } else {
                System.out.println("Address: " + addressNumber + " successfully deleted");
                return true;
            }
        }

        @Override
        public Set<Address> getAll() {
            return this.addressRepository.findAll().stream().collect(Collectors.toSet());
        }
    }


