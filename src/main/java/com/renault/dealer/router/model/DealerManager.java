package com.renault.dealer.router.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class DealerManager {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String uid;
    private String name;
    @Column
    @ElementCollection(targetClass=Dealer.class)
    private Set<Dealer> services;

    public DealerManager() {}

    public DealerManager(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Dealer[id=%d, uid='%s', name='%s']",
                id, uid, name);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Service> comments = new ArrayList<>();

}