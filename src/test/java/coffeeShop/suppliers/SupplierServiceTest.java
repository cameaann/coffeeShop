package coffeeShop.suppliers;

import coffeeShop.repositories.SupplierRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SupplierServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void testList(){

    }
    @Test
    public void testAdd(){

    }

    @Test
    public void testGetOne(){

    }


    @Test
    public  void testChange(){

    }

    public void testRemove(){}

}
