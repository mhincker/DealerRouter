package com.renault.dealer.router.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dealer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String uid;
    private String name;
    private Set<Service> services;

    public Dealer() {}

    public Dealer(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Dealer[id=%d, uid='%s', name='%s']",
                id, uid, name);
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
    public Set<Service> getServices() {
        return services;
    }

    public void SetService(Set<Service> services) {
        this.services = services;
    }
}
