package org.ibrahim.mongodb.controllers;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.ibrahim.mongodb.models.Seller;
import org.ibrahim.mongodb.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable String id) {
        ObjectId sellerObjectId = new ObjectId(id);
        return sellerService.getSellerById(sellerObjectId);
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerService.createSeller(seller);
    }

    @PutMapping("/{id}")
    public Seller updateSeller(@PathVariable String id, @RequestBody Seller seller) {
        ObjectId sellerObjectId = new ObjectId(id);
        return sellerService.updateSeller(sellerObjectId, seller);
    }

    @DeleteMapping("/{id}")
    public void deleteSeller(@PathVariable String id) {
        ObjectId sellerObjectId = new ObjectId(id);
        sellerService.deleteSeller(sellerObjectId);
    }
}
