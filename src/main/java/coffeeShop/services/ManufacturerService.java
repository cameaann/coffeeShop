package coffeeShop.services;

import coffeeShop.models.Manufacturer;
import coffeeShop.repositories.ManufacturerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
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
//        manufacturer.setProducts(new ArrayList<Product>());
//        manufacturer.getProducts().add(product);
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
//        manufacturer.setProducts(products);
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
}
