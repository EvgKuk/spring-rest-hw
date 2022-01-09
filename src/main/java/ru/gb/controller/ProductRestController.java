package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.dto.ProductDto;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public ProductRestController (ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/rest/v1/all GET
    @GetMapping("/all")
    public List<ProductDto> getAllProducts(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setHeader("test", "test1");
        return productService.getAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    // http://localhost:8080/app/rest/v1/info/3 GET
    @GetMapping("/info/{id}")
    public ResponseEntity<ProductDto> getProductInfo(@PathVariable Long id) {
        ProductDto productDto = new ProductDto(productService.findById(id));
        return new ResponseEntity<>(productDto, HttpStatus.FOUND);
    }

    // http://localhost:8080/app/rest/v1/add POST
    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    // http://localhost:8080/app/rest/v1/del GET
    @DeleteMapping("/del/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);

    }

}
