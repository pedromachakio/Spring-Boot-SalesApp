package pedromachakio.com.github.rest.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pedromachakio.com.github.domain.entity.Product;
import pedromachakio.com.github.domain.repository.ProductsDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductsDAO productsDAO;

    public ProductController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productsDAO
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product product) {
        return productsDAO.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productsDAO
                .findById(id)
                .map(productToBeDeleted -> {
                    productsDAO.delete(productToBeDeleted);
                    return productToBeDeleted;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Integer id, @RequestBody @Valid Product providedProduct) {
        productsDAO
                .findById(id)
                .map(alreadyExistingProduct -> {
                    providedProduct.setId(alreadyExistingProduct.getId());
                    // o providedProduct vem com as novas infos atualizadas menos o id
                    // então dá-se set do id igual ao que é para ser overwritten
                    // e depois save e depois retorna-se o atualizado
                    productsDAO.save(providedProduct);
                    return alreadyExistingProduct;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping
    public List<Product> findProduct(Product filterProduct) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Product> exampleProduct = Example.of(filterProduct, exampleMatcher);

        return productsDAO.findAll(exampleProduct);
    }

}
