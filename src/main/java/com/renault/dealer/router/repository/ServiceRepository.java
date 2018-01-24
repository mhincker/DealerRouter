package com.renault.dealer.router.repository;


import com.renault.dealer.router.model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {

    List<Service> findByName(String name);

}
