package com.store.storevmware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.storevmware.Entity.Catalog;
import com.store.storevmware.repository.CatalogRepository;

@Service
public class CatalogService {

	@Autowired
	CatalogRepository catalogRepository;
	
	public Catalog addCatalog(Catalog catalog) {
		return catalogRepository.save(catalog);
	}
	
	public void removeCatalog(Catalog catalog) {
		catalogRepository.delete(catalog);
	}
	
	public Catalog updateCatalog(Catalog catalog) {
		Optional<Catalog> checkExistCatalog = catalogRepository.findById(catalog.getId());
		if(checkExistCatalog.isPresent()) {
			Catalog oldCatalog = checkExistCatalog.get();
			oldCatalog.setItems(catalog.getItems());
			oldCatalog.setDiscription(catalog.getDiscription());
			catalogRepository.save(oldCatalog);
			return oldCatalog;
		}else {
			return new Catalog();
		}
	}

	public Catalog findById(int catalogId) {
		return catalogRepository.findById(catalogId).get();
		
	}
	
	public List<Catalog> getCatalogs(){
		return catalogRepository.findAll();
	}
}
