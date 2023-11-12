package exercise.specification;

import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component // для возможности автоматической инъекции
public class ProductSpecification {

    @Autowired
    private CategoryRepository categoryRepository;
    // Генерация спецификации на основе параметров внутри DTO
    // Для удобства каждый фильтр вынесен в свой метод
    public Specification<Product> build(ProductParamsDTO parameters) {
        return withCategory(               parameters.getCategoryId())
                .and(withPriceGreaterThan( parameters.getPriceGt()))
                .and(withPriceLessThan(    parameters.getPriceLt()))
                .and(withRatingGreaterThan(parameters.getRatingGt()))
                .and(nameContainsString(   parameters.getTitleCont()))
                ;
    }

    private Specification<Product> withCategory(Long categoryID){
        return (root, query, cb) -> categoryID == null ? cb.conjunction()
                : cb.equal(root.get("category").get("id"), categoryID);
    }

    private Specification<Product> withPriceGreaterThan(Integer priceLowerBound){
        return (root, query, criteriaBuilder) -> priceLowerBound == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("price"), priceLowerBound);
    }

    private Specification<Product> withPriceLessThan(Integer priceUpperBound){
        return (root, query, criteriaBuilder) -> priceUpperBound == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.lessThan(root.get("price"), priceUpperBound);
    }

    private Specification<Product> withRatingGreaterThan(Double ratingLowerBound){
        return (root, query, criteriaBuilder) -> ratingLowerBound == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("rating"), ratingLowerBound);
    }

    private Specification<Product> nameContainsString(String keyWord){
        return (root, query, criteriaBuilder) -> keyWord == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("title"), keyWord);
    }
}
// END
