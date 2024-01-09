package com.example.hw_currency_mvc_10;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, Long> {

}
