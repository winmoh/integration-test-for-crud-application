package com.integrationtest.first.integrationtest.Controller;

import com.integrationtest.first.integrationtest.Model.Product;
import com.integrationtest.first.integrationtest.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        product = service.saveProduct(product);
//        //Create resource location
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(product.getId())
//                .toUri();
//
//        //Send location in response
//        return ResponseEntity.created(location).build();
        return product;
    }


    @GetMapping("/getAll")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/get/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }


    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }}
