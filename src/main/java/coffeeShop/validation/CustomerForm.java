package coffeeShop.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CustomerForm {

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    private String email;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Email: " + this.email + ")";
    }
}
