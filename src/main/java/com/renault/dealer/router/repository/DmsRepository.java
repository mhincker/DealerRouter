package com.renault.dealer.router.repository;

import com.renault.dealer.router.model.Dealer;
import com.renault.dealer.router.model.DealerManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DmsRepository extends CrudRepository<Dealer, Long> {

    List<DealerManager> findByUid(String uid);

}