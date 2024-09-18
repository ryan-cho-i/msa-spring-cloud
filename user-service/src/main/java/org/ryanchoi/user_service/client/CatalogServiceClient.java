package org.ryanchoi.user_service.client;

import org.ryanchoi.user_service.error.FeignErrorDecoder2;
import org.ryanchoi.user_service.vo.ResponseCatalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "catalog-service", configuration = FeignErrorDecoder2.class)
public interface CatalogServiceClient {
    @GetMapping("/catalog-service/getCatalogs_wrong")
    List<ResponseCatalog> getCatalogs();
}
