package coffeeShop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class Cart{


    private List<CartItem> items = new ArrayList<>();

    private Double totalCost;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    public Cart(List<CartItem> items, LocalDate dateCreated) {
        this.items = items;
        this.dateCreated = dateCreated;
    }

    @Transient
    public Double getTotalCost() {
        double sum = 0D;
        for (CartItem item : this.getItems()) {
            sum += item.getPrice().doubleValue()*item.getQuantity();
        }
        totalCost = (double) Math.round(sum * 100) /100;

        return totalCost;
    }

    @Transient
    public int getNumberOfProducts() {
        int sum = 0;
        for (CartItem item : this.getItems()) {
            sum += item.getQuantity();
        }
        return sum;
    }
}