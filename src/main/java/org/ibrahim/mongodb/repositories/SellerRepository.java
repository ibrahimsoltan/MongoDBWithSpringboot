package org.ibrahim.mongodb.repositories;

import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, ObjectId> {
}
