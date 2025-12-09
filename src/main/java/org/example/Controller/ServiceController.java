package org.example.Controller;

import org.example.DTO.ServiceDTO;
import org.example.Service.ServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {
    private final ServiceImpl service;
    public ServiceController(ServiceImpl service) {
        this.service = service;

    }

    @PostMapping ("/create")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO serviceDTO1 = service.createProduct(serviceDTO);
        return ResponseEntity.ok(serviceDTO1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getById(@PathVariable String id) {
        ServiceDTO dto = service.getById(id);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> changeActive(@PathVariable String id) {
        service.changeActive(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceDTO>> getAll() {
        List<ServiceDTO> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<ServiceDTO>> getAllNoActive() {
        List<ServiceDTO> noActive = service.getAllNoActive();
        return ResponseEntity.ok(noActive);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceDTO>> getAllActive() {
        List<ServiceDTO> active = service.getAllActive();
        return ResponseEntity.ok(active);
    }
    
    // Эндпоинты для работы с моковыми данными
    @GetMapping("/mock/all")
    public ResponseEntity<List<ServiceDTO>> getAllMockServices() {
        List<ServiceDTO> mockServices = service.getAllMockServices();
        return ResponseEntity.ok(mockServices);
    }
    
    @GetMapping("/mock/category/{category}")
    public ResponseEntity<List<ServiceDTO>> getMockServicesByCategory(@PathVariable String category) {
        List<ServiceDTO> services = service.getMockServicesByCategory(category);
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/mock/price")
    public ResponseEntity<List<ServiceDTO>> getMockServicesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<ServiceDTO> services = service.getMockServicesByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/mock/duration")
    public ResponseEntity<List<ServiceDTO>> getMockServicesByMaxDuration(
            @RequestParam Integer maxDuration) {
        List<ServiceDTO> services = service.getMockServicesByMaxDuration(maxDuration);
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/mock/categories")
    public ResponseEntity<List<String>> getAllMockCategories() {
        List<String> categories = service.getAllMockCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/mock/name/{name}")
    public ResponseEntity<ServiceDTO> getMockServiceByName(@PathVariable String name) {
        ServiceDTO serviceDTO = service.getMockServiceByName(name);
        if (serviceDTO != null) {
            return ResponseEntity.ok(serviceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
