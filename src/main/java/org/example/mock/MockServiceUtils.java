package org.example.mock;

import org.example.Domain.ServiceModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с моковыми данными косметических услуг
 */
public class MockServiceUtils {
    
    /**
     * Получить услуги по категории
     * @param category категория услуг
     * @return список услуг указанной категории
     */
    public static List<ServiceModel> getServicesByCategory(String category) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getCategory().equals(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Получить услуги в указанном диапазоне цен
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список услуг в указанном диапазоне цен
     */
    public static List<ServiceModel> getServicesByPriceRange(Double minPrice, Double maxPrice) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getPrice() >= minPrice && service.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    /**
     * Получить услуги с длительностью не более указанного времени
     * @param maxDuration максимальная длительность в минутах
     * @return список услуг с длительностью не более указанного времени
     */
    public static List<ServiceModel> getServicesByMaxDuration(Integer maxDuration) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }
    
    /**
     * Получить услугу по имени
     * @param name название услуги
     * @return услуга с указанным названием или null
     */
    public static ServiceModel getServiceByName(String name) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Получить список всех доступных категорий
     * @return список уникальных категорий
     */
    public static List<String> getAllCategories() {
        return MockServiceData.getMockServices().stream()
                .map(ServiceModel::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
    
    /**
     * Получить услуги с ценой не выше указанной
     * @param maxPrice максимальная цена
     * @return список доступных услуг
     */
    public static List<ServiceModel> getAffordableServices(Double maxPrice) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    /**
     * Получить услуги с длительностью в указанном диапазоне
     * @param minDuration минимальная длительность в минутах
     * @param maxDuration максимальная длительность в минутах
     * @return список услуг с длительностью в указанном диапазоне
     */
    public static List<ServiceModel> getServicesByDurationRange(Integer minDuration, Integer maxDuration) {
        return MockServiceData.getMockServices().stream()
                .filter(service -> service.getDuration() >= minDuration && service.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }
}

