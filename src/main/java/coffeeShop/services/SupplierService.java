package coffeeShop.services;

import coffeeShop.models.Supplier;
import coffeeShop.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Getter
    private String errorMessage;

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

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

    public void setErrorMessage(String err){
        this.errorMessage = err;
    }

    public void clearErrorMessage() {
        this.errorMessage = null;
    }
}
