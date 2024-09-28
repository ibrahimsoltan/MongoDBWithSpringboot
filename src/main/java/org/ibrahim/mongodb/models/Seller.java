package org.ibrahim.mongodb.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sellers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    private ObjectId id;
    private String name;
    private String email;

    // Getters and Setters
}
