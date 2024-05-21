package coffeeShop.services;

import coffeeShop.models.Manufacturer;
import coffeeShop.repositories.ManufacturerRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
    @Getter
    private String errorMessage;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Iterable<Manufacturer> list(){
        return manufacturerRepository.findAll();
    }

    @Transactional
    public void add(String name, String url){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setUrl(url);
        manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public Manufacturer getOne(long id){
        return manufacturerRepository.getReferenceById(id);
    }

    @Transactional
    public void change(String name, String url, Long id){
        Manufacturer manufacturer = manufacturerRepository.getReferenceById(id);
        manufacturer.setName(name);
        manufacturer.setUrl(url);
        manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public void remove(long id){
        if(manufacturerRepository.existsById(id)){
            manufacturerRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Manufacturer with ID " + id + " does not exist");
        }
    }

    public void setErrorMessage(String err){
        this.errorMessage = err;
    }

    public void clearErrorMessage() {
        this.errorMessage = null;
    }
}
