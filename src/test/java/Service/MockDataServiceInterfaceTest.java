package Service;

import org.example.DTO.ServiceDTO;
import org.example.Repository.ServiceRepositoryImpl;
import org.example.Service.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class MockDataServiceInterfaceTest {

    @MockBean
    private ServiceRepositoryImpl repository;
    
    @Autowired
    private ServiceInterface serviceInterface;

    @Test
    void getAllMockServices_ShouldReturnMockServices() {
        // Act
        List<ServiceDTO> mockServices = serviceInterface.getAllMockServices();

        // Assert
        assertThat(mockServices).isNotEmpty();
        assertThat(mockServices.size()).isGreaterThan(10); // У вас много моковых данных

        // Проверяем, что есть услуги из разных категорий
        long faceCareCount = mockServices.stream()
                .filter(s -> s.getCategory().equals("Уход за лицом"))
                .count();
        assertThat(faceCareCount).isGreaterThan(0);

        long manicureCount = mockServices.stream()
                .filter(s -> s.getCategory().equals("Маникюр и педикюр"))
                .count();
        assertThat(manicureCount).isGreaterThan(0);

        System.out.println("Found " + mockServices.size() + " mock services");
        System.out.println("Face care services: " + faceCareCount);
        System.out.println("Manicure services: " + manicureCount);
    }

    @Test
    void getMockServicesByCategory_ShouldFilterCorrectly() {
        // Act
        List<ServiceDTO> faceCareServices = serviceInterface.getMockServicesByCategory("Уход за лицом");
        List<ServiceDTO> bodyCareServices = serviceInterface.getMockServicesByCategory("Уход за телом");

        // Assert
        assertThat(faceCareServices).isNotEmpty();
        assertThat(bodyCareServices).isNotEmpty();

        // Все услуги должны быть из указанной категории
        assertThat(faceCareServices)
                .allMatch(s -> s.getCategory().equals("Уход за лицом"));
        assertThat(bodyCareServices)
                .allMatch(s -> s.getCategory().equals("Уход за телом"));

        System.out.println("Face care services count: " + faceCareServices.size());
        System.out.println("Body care services count: " + bodyCareServices.size());
    }

    @Test
    void getMockServicesByPriceRange_ShouldFilterByPrice() {
        // Act
        List<ServiceDTO> affordableServices = serviceInterface.getMockServicesByPriceRange(0.0, 2000.0);
        List<ServiceDTO> expensiveServices = serviceInterface.getMockServicesByPriceRange(4000.0, 10000.0);

        // Assert
        assertThat(affordableServices).isNotEmpty();
        assertThat(expensiveServices).isNotEmpty();

        // Проверяем диапазон цен
        assertThat(affordableServices)
                .allMatch(s -> s.getPrice() >= 0.0 && s.getPrice() <= 2000.0);
        assertThat(expensiveServices)
                .allMatch(s -> s.getPrice() >= 4000.0 && s.getPrice() <= 10000.0);

        System.out.println("Affordable services (<= 2000): " + affordableServices.size());
        System.out.println("Expensive services (>= 4000): " + expensiveServices.size());
    }

    @Test
    void getAllMockCategories_ShouldReturnAllCategories() {
        // Act
        List<String> categories = serviceInterface.getAllMockCategories();

        // Assert
        assertThat(categories).isNotEmpty();
        assertThat(categories).contains("Уход за лицом", "Уход за телом", "Маникюр и педикюр");

        System.out.println("Available categories: " + categories);
    }

    @Test
    void getMockServiceByName_ShouldFindService() {
        // Act
        ServiceDTO facialCleaning = serviceInterface.getMockServiceByName("Чистка лица");
        ServiceDTO yogaClass = serviceInterface.getMockServiceByName("Йога"); // Не существует

        // Assert
        assertThat(facialCleaning).isNotNull();
        assertThat(facialCleaning.getName()).isEqualTo("Чистка лица");
        assertThat(facialCleaning.getPrice()).isEqualTo(2500.0);
        assertThat(facialCleaning.getDuration()).isEqualTo(60);

        assertThat(yogaClass).isNull(); // Не должен найти

        System.out.println("Found service: " + facialCleaning.getName() +
                ", Price: " + facialCleaning.getPrice());
    }
}