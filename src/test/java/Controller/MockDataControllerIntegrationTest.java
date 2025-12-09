//package Controller;
//
//import org.example.FrontCourseApplication;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(classes = FrontCourseApplication.class)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class MockDataControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getAllMockServices_ShouldReturnAllMockData() throws Exception {
//        mockMvc.perform(get("/service/mock/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(10))))
//                .andExpect(jsonPath("$[?(@.name == 'Чистка лица')]", hasSize(1)))
//                .andExpect(jsonPath("$[?(@.name == 'Классический маникюр')]", hasSize(1)))
//                .andExpect(jsonPath("$[?(@.category == 'Уход за лицом')]", hasSize(greaterThan(0))));
//    }
//
//    @Test
//    void getMockServicesByCategory_ShouldFilterServices() throws Exception {
//        mockMvc.perform(get("/service/mock/category/Уход за лицом"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
//                .andExpect(jsonPath("$[*].category", everyItem(is("Уход за лицом"))))
//                .andExpect(jsonPath("$[?(@.name == 'Чистка лица')]", hasSize(1)))
//                .andExpect(jsonPath("$[?(@.name == 'Пилинг лица')]", hasSize(1)));
//    }
//
//    @Test
//    void getMockServicesByPriceRange_ShouldReturnServicesInRange() throws Exception {
//        mockMvc.perform(get("/service/mock/price")
//                        .param("minPrice", "0")
//                        .param("maxPrice", "2000"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
//                .andExpect(jsonPath("$[*].price", everyItem(lessThanOrEqualTo(2000.0))));
//
//        mockMvc.perform(get("/service/mock/price")
//                        .param("minPrice", "4000")
//                        .param("maxPrice", "10000"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
//                .andExpect(jsonPath("$[*].price", everyItem(greaterThanOrEqualTo(4000.0))));
//    }
//
//    @Test
//    void getMockServicesByMaxDuration_ShouldReturnQuickServices() throws Exception {
//        mockMvc.perform(get("/service/mock/duration")
//                        .param("maxDuration", "30"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
//                .andExpect(jsonPath("$[*].duration", everyItem(lessThanOrEqualTo(30))));
//    }
//
//    @Test
//    void getAllMockCategories_ShouldReturnCategoriesList() throws Exception {
//        mockMvc.perform(get("/service/mock/categories"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(5))))
//                .andExpect(jsonPath("$", hasItem("Уход за лицом")))
//                .andExpect(jsonPath("$", hasItem("Маникюр и педикюр")))
//                .andExpect(jsonPath("$", hasItem("Депиляция")));
//    }
//
//    @Test
//    void getMockServiceByName_ShouldFindSpecificService() throws Exception {
//        mockMvc.perform(get("/service/mock/name/Чистка лица"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Чистка лица")))
//                .andExpect(jsonPath("$.price", is(2500.0)))
//                .andExpect(jsonPath("$.duration", is(60)))
//                .andExpect(jsonPath("$.category", is("Уход за лицом")));
//
//        // Несуществующая услуга
//        mockMvc.perform(get("/service/mock/name/Несуществующая услуга"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void integrationTest_CompleteMockDataWorkflow() throws Exception {
//        // 1. Получаем все категории
//        mockMvc.perform(get("/service/mock/categories"))
//                .andExpect(status().isOk());
//
//        // 2. Получаем услуги по первой категории
//        mockMvc.perform(get("/service/mock/category/Уход за лицом"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
//
//        // 3. Ищем конкретную услугу
//        mockMvc.perform(get("/service/mock/name/Чистка лица"))
//                .andExpect(status().isOk());
//
//        // 4. Фильтруем по цене
//        mockMvc.perform(get("/service/mock/price")
//                        .param("minPrice", "1000")
//                        .param("maxPrice", "3000"))
//                .andExpect(status().isOk());
//
//        System.out.println("Complete mock data integration test passed!");
//    }
//}