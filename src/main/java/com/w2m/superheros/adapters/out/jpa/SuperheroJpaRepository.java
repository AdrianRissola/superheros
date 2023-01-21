package com.w2m.superheros.adapters.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;

//@Repository
public interface SuperheroJpaRepository extends JpaRepository<SuperheroJpaEntity, Integer> {

}
