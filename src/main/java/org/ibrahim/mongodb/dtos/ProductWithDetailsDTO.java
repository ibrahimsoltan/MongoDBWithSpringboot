package org.ibrahim.mongodb.dtos;

import lombok.*;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Category;
import org.ibrahim.mongodb.models.Seller;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithDetailsDTO {

    private ObjectId productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;

    private Category category;
    private Seller seller;
}
