package org.ryanchoi.catalog_service.service;

import org.ryanchoi.catalog_service.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
