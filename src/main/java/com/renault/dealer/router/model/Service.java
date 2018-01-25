package com.renault.dealer.router.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @Column
    @ElementCollection(targetClass=Dealer.class)
    private Set<Dealer> dealers;

    public Service() {}

    public Service(String name, Set<Dealer> dealers) {
        this.name = name;
        this.dealers = dealers;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s']",
                id, name);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dealer_service", joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"))
    public Set<Dealer> getDealers() {
        return dealers;
    }


    public void setDealers(Set<Dealer> dealers) {
        this.dealers = dealers;
    }}

