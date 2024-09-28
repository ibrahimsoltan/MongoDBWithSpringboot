package org.ibrahim.mongodb.repositories;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {
}
