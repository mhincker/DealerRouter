package com.renault.dealer.router.model;

import javax.persistence.*;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Dealer dealer;


    public Service() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service(String name, Dealer dealer) {
        this.name = name;
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s']",
                id, name);
    }

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }}
