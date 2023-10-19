package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN


    List<Product> findByPriceBetweenOrderByPrice(int min, int max);
    List<Product> findByPriceGreaterThanOrderByPrice(int min);
    List<Product> findByPriceLessThanOrderByPrice(int max);


    // END
}
