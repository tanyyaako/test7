package org.example.Service;

import org.example.DTO.ServiceDTO;
import org.example.Domain.ServiceModel;
import org.example.Repository.ServiceRepositoryImpl;
import org.example.mock.MockServiceData;
import org.example.mock.MockServiceUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements ServiceInterface {
    private final ServiceRepositoryImpl repository;
    private final ModelMapper modelMapper;
    public ServiceImpl(ServiceRepositoryImpl repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceDTO createProduct(ServiceDTO serviceDto) {
        System.out.println(serviceDto.getName());
        ServiceModel serviceEntity = new ServiceModel(
                serviceDto.getName(),
                serviceDto.getDescription(),
                serviceDto.getPrice(),
                serviceDto.getDuration(),
                serviceDto.getCategory()
        );
        ServiceModel savedServiceEntity = repository.save(serviceEntity);
        ServiceDTO savedServiceDto = modelMapper.map(savedServiceEntity, ServiceDTO.class);
        System.out.println(savedServiceDto.getName());
        return savedServiceDto;
    }

    @Override
    public ServiceDTO getById(String id) {
        try {
            Optional<ServiceModel> serviceEntity = repository.findById(id);
            ServiceModel entity = serviceEntity.orElseThrow(() ->
                    new RuntimeException("Услуга с id=" + id + " не найдена")
            );
            return modelMapper.map(entity, ServiceDTO.class);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(String id) {
        if (!repository.findById(id).isPresent()) {
            throw new RuntimeException("Невозможно удалить: услуга с id=" + id + " не найдена");
        }
        repository.deleteById(id);

    }

    @Override
    public void changeActive(String id) {
        ServiceModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга с id=" + id + " не найдена"));
        Boolean current = entity.isActive();
        entity.setActive(current == true ? Boolean.FALSE : Boolean.TRUE);
        repository.save(entity);

    }

    @Override
    public List<ServiceDTO> getAll() {
        List<ServiceModel> allServices = repository.findAll();
        return allServices.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }

    @Override
    public List<ServiceDTO> getAllNoActive() {
        List<ServiceModel> noActive = repository.findByActive(false);
        return noActive.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }

    @Override
    public List<ServiceDTO> getAllActive() {
        List<ServiceModel> noActive = repository.findByActive(true);
        return noActive.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }
    
    // Методы для работы с моковыми данными
    @Override
    public List<ServiceDTO> getAllMockServices() {
        List<ServiceModel> mockServices = MockServiceData.getMockServices();
        return mockServices.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }
    
    @Override
    public List<ServiceDTO> getMockServicesByCategory(String category) {
        List<ServiceModel> services = MockServiceUtils.getServicesByCategory(category);
        return services.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }
    
    @Override
    public List<ServiceDTO> getMockServicesByPriceRange(Double minPrice, Double maxPrice) {
        List<ServiceModel> services = MockServiceUtils.getServicesByPriceRange(minPrice, maxPrice);
        return services.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }
    
    @Override
    public List<ServiceDTO> getMockServicesByMaxDuration(Integer maxDuration) {
        List<ServiceModel> services = MockServiceUtils.getServicesByMaxDuration(maxDuration);
        return services.stream()
                .map(model -> modelMapper.map(model, ServiceDTO.class))
                .toList();
    }
    
    @Override
    public List<String> getAllMockCategories() {
        return MockServiceUtils.getAllCategories();
    }
    
    @Override
    public ServiceDTO getMockServiceByName(String name) {
        ServiceModel service = MockServiceUtils.getServiceByName(name);
        if (service != null) {
            return modelMapper.map(service, ServiceDTO.class);
        }
        return null;
    }
}
