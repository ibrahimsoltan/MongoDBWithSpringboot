package org.ibrahim.mongodb.repositories;

import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product , ObjectId> {

    List<Product> findAllByCategoryId(ObjectId categoryObjectId);
}
