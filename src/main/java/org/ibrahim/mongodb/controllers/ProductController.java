package org.ibrahim.mongodb.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.dtos.ProductWithDetailsDTO;
import org.ibrahim.mongodb.models.Product;
import org.ibrahim.mongodb.services.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    private ObjectMapper objectMapper;

    @GetMapping
    public List<ProductWithDetailsDTO> getProducts(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String sellerId

    ) {
        System.out.println("Category id: " + categoryId);
        System.out.println("Seller id: " + sellerId);
        if ((categoryId != null && !categoryId.isEmpty()) || (sellerId != null && !sellerId.isEmpty())) {
            ObjectId categoryObjectId = categoryId != null && !categoryId.isEmpty() ? new ObjectId(categoryId) : null;
            ObjectId sellerObjectId = sellerId != null && !sellerId.isEmpty() ? new ObjectId(sellerId) : null;

            return productService.getProductsByCategoryAndSeller(categoryObjectId, sellerObjectId);
        }
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable ObjectId id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseBody
    public Map<String,Object> createProduct( HttpEntity<String> product) throws IOException {
//        Product product = objectMapper.readValue(product.getBody(), Product.class);
        System.out.println("Product: " + product.getBody());
//        System.out.println("Product: " + product.());
        Product productBody = objectMapper.readValue( product.getBody(), Product.class);
        Product reponseProduct = productService.addProduct(productBody);

        HashMap<String,Object> tempMap =  objectMapper.readValue(product.getBody(),HashMap.class);
        return tempMap;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable ObjectId id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable ObjectId id) {
        productService.deleteProduct(id);
    }
}
