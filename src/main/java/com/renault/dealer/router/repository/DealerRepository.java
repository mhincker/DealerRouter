package com.renault.dealer.router.repository;

import com.renault.dealer.router.model.Dealer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DealerRepository extends CrudRepository<Dealer, Long> {

    List<Dealer> findByUid(String uid);

}
