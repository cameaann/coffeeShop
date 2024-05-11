package coffeeShop.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CustomerForm {
    @NotNull
    @Size(min = 2, message = "Name should contain at least 2 character.")
    private String name;

    @NotNull
    @NotEmpty(message = "The email is required")
    @Email(message = "Email address should be in the right format")
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
