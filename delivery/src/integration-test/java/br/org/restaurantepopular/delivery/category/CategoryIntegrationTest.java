package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.delivery.MainApplication;
import br.org.restaurantepopular.delivery.rest.api.category.CategoryDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

    @Autowired
    Environment environment;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private String serverURL;
    private HttpEntity<?> requestEntity;

    @Before
    public void setup() {
        var port = environment.getProperty("local.server.port");
        serverURL = "http://localhost:" + port;
        var requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestEntity = new HttpEntity<>(requestHeaders);
    }

    @Test
    public void shouldReturn201_whenCreatingANewCategory() {
        var response = createCategory(null, "Arroz", "Arroz Branco", Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturn422_whenCreatingANewCategoryWithoutName() {
        var response = createCategory(null, null, "Arroz Branco", Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void shouldReturn422_whenCreatingANewCategoryWithoutDescription() {
        var response = createCategory(null, "Arroz", null, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void shouldReturn200AndTheCategory_whenRetrievingACategoryThatExists() {
        var name = "Arroz";
        var description = "Arroz Branco";
        var createResponse = createCategory(null, name, description, CategoryDTO.class);

        var retrieveResponse = retrieveCategory(createResponse.getBody().getId(), CategoryDTO.class);
        var responseDto = retrieveResponse.getBody();

        assertEquals(HttpStatus.OK, retrieveResponse.getStatusCode());
        assertEquals(name, responseDto.getName());
        assertEquals(description, responseDto.getDescription());
    }

    @Test
    public void shouldReturn404_whenTryingRetrieveACategoryThatDoesNotExist_byID() {
        var retrieveResponse = retrieveCategory("23234234234", CategoryDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, retrieveResponse.getStatusCode());
    }

    @Test
    public void shouldReturn200AndCategoryList_whenRetrievingCategoriesWith2Registers() {
        createCategory(null, "Arroz", "Arroz Branco", CategoryDTO.class);
        createCategory(null, "Feijão", "Feijão carioca", CategoryDTO.class);

        var retrieveResponse = retrieveCategory("", List.class);
        var responseDTO = retrieveResponse.getBody();

        assertEquals(HttpStatus.OK, retrieveResponse.getStatusCode());
        assertNotNull(responseDTO);
        assertEquals(2, responseDTO.size());
    }

    private <T> ResponseEntity<T> retrieveCategory(String id, Class<T> typeKey) {
        return restTemplate.exchange(serverURL + "/categories/" + id, HttpMethod.GET, requestEntity, typeKey);
    }

    private <T> ResponseEntity<T> createCategory(
            String id,
            String name,
            String description,
            Class<T> typeKey) {

        var category = new CategoryDTO();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        var entity = new HttpEntity<>(category);
        return restTemplate.exchange(serverURL + "/categories", HttpMethod.POST, entity, typeKey);
    }
}
