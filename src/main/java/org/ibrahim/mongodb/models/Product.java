package org.ibrahim.mongodb.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private double price;
    private ObjectId categoryId;
    private ObjectId sellerId;
    private int quantity;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;  // Automatically populated when the product is updated


}
