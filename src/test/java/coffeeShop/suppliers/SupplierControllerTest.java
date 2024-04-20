package coffeeShop.suppliers;

//import coffeeShop.authentication.SecurityConfig;
import coffeeShop.entities.Supplier;
import coffeeShop.repositories.SupplierRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@ActiveProfiles("test")
//@ContextConfiguration(classes = SecurityConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SupplierControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void statusOk() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/suppliers"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void modelResponseHasParameters() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/suppliers"))
                .andReturn();
        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("suppliers"));
    }

    @Test
    public void createSupplier() throws Exception{
        String name = "Delivery";
        String contactPerson = "Anna";
        String personEmail = "email@gmail.com";

        mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
                .param("name", name)
                .param("contactPerson", contactPerson)
                .param("contactPersonEmail", personEmail))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/suppliers"));

        List<Supplier> suppliers = this.supplierRepository.findAll();
        boolean supplierFound = false;
        for (Supplier supplier: suppliers){
            if(supplier.getName().equals(name)){
                supplierFound = true;
            }
        }
        Assert.assertTrue(supplierFound);
    }

    @Test
    public void cannotCreateEmptySupplier() throws Exception{
        String name = "";
        String contactPerson = "Anna";
        String personEmail = "email@gmail.com";

        mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
                        .param("name", name)
                        .param("contactPerson", contactPerson)
                        .param("contactPersonEmail", personEmail))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/suppliers"));

        List<Supplier> suppliers = this.supplierRepository.findAll();
        boolean supplierFound = false;
        for (Supplier supplier: suppliers){
            if(supplier.getName().equals(name)){
                supplierFound = true;
            }
        }
        Assert.assertFalse(supplierFound);
    }

}
