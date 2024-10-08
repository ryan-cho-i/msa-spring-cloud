package org.ryanchoi.catalog_service.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ryanchoi.catalog_service.jpa.CatalogEntity;
import org.ryanchoi.catalog_service.jpa.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {
    CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }

}
