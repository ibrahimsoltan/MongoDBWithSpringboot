package org.ibrahim.mongodb.repositories;

import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
}
