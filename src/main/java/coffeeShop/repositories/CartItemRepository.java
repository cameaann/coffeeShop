package coffeeShop.repositories;

import coffeeShop.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserIdOrderByCreatedDateDesc(Long userId);
}
