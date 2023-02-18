package com.example.billingservice.entities;

import com.example.billingservice.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Date billingDate;
    @OneToMany(mappedBy = "bill")
    public Collection<ProductItem> productItems;
    private Long customerID;
    @Transient
    private Customer customer;

    public int gettotal() {
        int somme = 0;
        for (ProductItem pi : productItems) {
            somme += pi.getPrice();
        }
        return somme;
    }
}
