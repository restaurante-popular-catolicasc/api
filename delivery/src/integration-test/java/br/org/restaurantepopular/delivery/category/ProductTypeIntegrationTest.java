package br.org.restaurantepopular.delivery.product_type;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.restaurantepopular.delivery.MainApplication;
import br.org.restaurantepopular.delivery.rest.api.product_type.ProductTypeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTypeIntegrationTest {

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
    public void shouldReturn201_whenCreatingANewProductType() {
        var response = createProductType(null, "Integral", "Integral", Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturn422_whenCreatingANewProductTypeWithoutName() {
        var response = createProductType(null, null, "Integral", Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void shouldReturn422_whenCreatingANewProductTypeWithoutDescription() {
        var response = createProductType(null, "Integral", null, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void shouldReturn200AndTheProductType_whenRetrievingAProductTypeThatExists() {
        var name = "Integral";
        var description = "Integral";
        var createResponse = createProductType(null, name, description, ProductTypeDTO.class);

        var retrieveResponse = retrieveProductType(createResponse.getBody().getId(), ProductTypeDTO.class);
        var responseDto = retrieveResponse.getBody();

        assertEquals(HttpStatus.OK, retrieveResponse.getStatusCode());
        assertEquals(name, responseDto.getName());
        assertEquals(description, responseDto.getDescription());
    }

    @Test
    public void shouldReturn404_whenTryingRetrieveAProductTypeThatDoesNotExist_byID() {
        var retrieveResponse = retrieveProductType("23234234234", ProductTypeDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, retrieveResponse.getStatusCode());
    }

    @Test
    public void shouldReturn200AndProductTypeList_whenRetrievingCategoriesWith2Registers() {
        createProductType(null, "Integral", "Integral", ProductTypeDTO.class);
        createProductType(null, "Laticinio", "Laticinio", ProductTypeDTO.class);

        var retrieveResponse = retrieveProductType("", List.class);
        var responseDTO = retrieveResponse.getBody();

        assertEquals(HttpStatus.OK, retrieveResponse.getStatusCode());
        assertNotNull(responseDTO);
        assertEquals(2, responseDTO.size());
    }

    @Test
    public void shouldReturn204_whenTryingToDeleteExistentProductType_byID() {
        var createResponse = createProductType(null, "Laticinio", "Laticinio", ProductTypeDTO.class);
        var categoryID = createResponse.getBody().getId();

        var deleteResponse = deleteProductType(categoryID, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }

    @Test
    public void shouldReturn404_whenTryingToDeleteNonexistentProductType_byID() {
        var deleteResponse = deleteProductType("INVALID ID", Void.class);

        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }

    private <T> ResponseEntity<T> deleteProductType(String id, Class<T> typeKey) {
        return restTemplate.exchange(serverURL + "/productType/" + id, HttpMethod.DELETE, requestEntity, typeKey);
    }

    private <T> ResponseEntity<T> retrieveProductType(String id, Class<T> typeKey) {
        return restTemplate.exchange(serverURL + "/productType/" + id, HttpMethod.GET, requestEntity, typeKey);
    }

    private <T> ResponseEntity<T> createProductType(
            String id,
            String name,
            String description,
            Class<T> typeKey) {

        var category = new ProductTypeDTO();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        var entity = new HttpEntity<>(category);
        return restTemplate.exchange(serverURL + "/productType", HttpMethod.POST, entity, typeKey);
    }
}
