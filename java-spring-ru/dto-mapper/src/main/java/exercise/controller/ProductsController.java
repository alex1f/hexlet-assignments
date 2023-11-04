package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> showAllProducts(){
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable Long id){
        Product result = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        return productMapper.map(result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        Product newProduct = productMapper.map(productCreateDTO);
        newProduct = productRepository.save(newProduct);
        return productMapper.map(newProduct);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO productUpdateDTO){
        Product target = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        productMapper.update(productUpdateDTO, target);
        productRepository.save(target);
        return productMapper.map(target);
    }
    // END
}
