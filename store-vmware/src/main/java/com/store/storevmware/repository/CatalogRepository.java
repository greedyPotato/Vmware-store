package com.store.storevmware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.storevmware.Entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Integer>{

}
