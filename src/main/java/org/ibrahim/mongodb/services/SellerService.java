package org.ibrahim.mongodb.services;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Seller;
import org.ibrahim.mongodb.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerService {


    private final SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(ObjectId id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(ObjectId id, Seller seller) {
        if (sellerRepository.existsById(id)) {

            seller.setId(id);
            return sellerRepository.save(seller);
        }
        return null;
    }

    public void deleteSeller(ObjectId id) {
        sellerRepository.deleteById(id);
    }
}
