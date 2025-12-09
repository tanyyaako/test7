package org.example.Service;

import org.example.DTO.ServiceDTO;

import java.util.List;

public interface ServiceInterface {
    ServiceDTO createProduct(ServiceDTO serviceDto);
    ServiceDTO getById(String id);
    void delete(String id);
    void changeActive(String id );
    List<ServiceDTO> getAll();
    List<ServiceDTO> getAllNoActive();
    List<ServiceDTO> getAllActive();
    
    // Методы для работы с моковыми данными
    List<ServiceDTO> getAllMockServices();
    List<ServiceDTO> getMockServicesByCategory(String category);
    List<ServiceDTO> getMockServicesByPriceRange(Double minPrice, Double maxPrice);
    List<ServiceDTO> getMockServicesByMaxDuration(Integer maxDuration);
    List<String> getAllMockCategories();
    ServiceDTO getMockServiceByName(String name);

}