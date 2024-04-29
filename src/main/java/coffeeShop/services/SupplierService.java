package coffeeShop.services;

import coffeeShop.entities.Supplier;
import coffeeShop.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Iterable<Supplier> list(){
        return supplierRepository.findAll();
    }

    @Transactional
    public void add(String name, String contactPerson, String contactPersonEmail){
        Supplier supplier = new Supplier();
        supplierRepository.save(setSupplierSettings(name, contactPerson,
                contactPersonEmail, supplier));
    }

    @Transactional
    public Supplier getOne(long id){
        return supplierRepository.getReferenceById(id);
    }

    @Transactional
    public void change(String name, String contactPerson, String contactPersonEmail, Long id){
        Supplier supplier = supplierRepository.getReferenceById(id);
        supplierRepository.save(setSupplierSettings(name, contactPerson,
                contactPersonEmail, supplier));
    }

    @Transactional
    public void remove(long id){
        if(supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Supplier with ID " + id + " does not exist");
        }
    }

    private Supplier setSupplierSettings(String name, String contactPerson,
                                         String contactPersonEmail, Supplier supplier){
        supplier.setName(name);
        supplier.setContactPerson(contactPerson);
        supplier.setContactPersonEmail(contactPersonEmail);
        return supplier;
    }
}
