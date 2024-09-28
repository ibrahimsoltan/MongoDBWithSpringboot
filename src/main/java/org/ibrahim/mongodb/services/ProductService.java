package org.ibrahim.mongodb.services;


import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.dtos.ProductWithDetailsDTO;
import org.ibrahim.mongodb.models.Category;
import org.ibrahim.mongodb.models.Product;
import org.ibrahim.mongodb.models.Seller;
import org.ibrahim.mongodb.repositories.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;

    private final CategoryService categoryService;

    private final SellerService sellerService;

    public List<ProductWithDetailsDTO> getAllProducts() {
        List<Product> products =  productRepository.findAll();
        return getProductWithDetailsDTOS(products);
    }

    public List<ProductWithDetailsDTO> getProductsByCategoryAndSeller(ObjectId categoryObjectId, ObjectId sellerObjectId) {
        Query query = new Query();
        if (categoryObjectId != null) {
            System.out.println("Category id: " + categoryObjectId);
            query.addCriteria(Criteria.where("categoryId").is(categoryObjectId));
        }
        if (sellerObjectId != null) {
            System.out.println("Seller id: " + sellerObjectId);
            query.addCriteria(Criteria.where("sellerId").is(sellerObjectId));
        }
        List<Product> products = mongoTemplate.find(query, Product.class);
        System.out.println("List of products of category size: " + products.size());
        return getProductWithDetailsDTOS(products);

    }

    public Product getProductById(ObjectId id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(ObjectId id, Product product) {
        if (productRepository.existsById(id))
        {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;

    }

    public void deleteProduct(ObjectId id) {
        productRepository.deleteById(id);
    }

    private List<ProductWithDetailsDTO> getProductWithDetailsDTOS(List<Product> products) {
        List<ProductWithDetailsDTO> productWithDetailsDTOS  = new ArrayList<>();
        for (Product product : products) {
            Category category = categoryService.getCategoryById(product.getCategoryId());
            Seller seller = sellerService.getSellerById(product.getSellerId());
            ProductWithDetailsDTO productWithDetailsDTO = ProductWithDetailsDTO
                    .builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .description(product.getDescription())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .category(category)
                    .seller(seller)
                    .build();
            productWithDetailsDTOS.add(productWithDetailsDTO);
        }
        return productWithDetailsDTOS;
    }
}
