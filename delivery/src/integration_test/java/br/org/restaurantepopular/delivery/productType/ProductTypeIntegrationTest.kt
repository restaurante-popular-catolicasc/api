package br.org.restaurantepopular.delivery.productType

import br.org.restaurantepopular.delivery.MainApplication
import br.org.restaurantepopular.delivery.rest.api.product_type.ProductTypeDTO
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.test.context.junit4.SpringRunner
import junit.framework.TestCase.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductTypeIntegrationTest {

    @Autowired
    internal var environment: Environment? = null

    private val restTemplate = TestRestTemplate()
    private var serverURL: String? = null
    private var requestEntity: HttpEntity<*>? = null

    @Before
    fun setup() {
        val port = environment!!.getProperty("local.server.port")
        serverURL = "http://localhost:" + port!!
        val requestHeaders = HttpHeaders()
        requestHeaders.contentType = MediaType.APPLICATION_JSON
        requestEntity = HttpEntity<Any>(requestHeaders)
    }

    @Test
    fun shouldReturn201_whenCreatingANewProductType() {
        val response = createProductType("Integral", Void::class.java)

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun shouldReturn422_whenCreatingANewProductTypeWithoutName() {
        val response = createProductType(null, Void::class.java)

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }

    @Test
    fun shouldReturn200AndTheProductType_whenRetrievingAProductTypeThatExists() {
        val name = "Integral"
        val createResponse = createProductType(name, ProductTypeDTO::class.java)

        val retrieveResponse = retrieveProductType(createResponse.body!!.id, ProductTypeDTO::class.java)
        val responseDto = retrieveResponse.body

        assertEquals(HttpStatus.OK, retrieveResponse.statusCode)
        assertEquals(name, responseDto!!.name)
        assertEquals(createResponse.body!!.id, responseDto.id)
    }

    @Test
    fun shouldReturn404_whenTryingRetrieveAProductTypeThatDoesNotExist_byID() {
        val retrieveResponse = retrieveProductType("23234234234", ProductTypeDTO::class.java)
        assertEquals(HttpStatus.NOT_FOUND, retrieveResponse.statusCode)
    }

    @Test
    fun shouldReturn204_whenTryingToDeleteExistentProductType_byID() {
        val createResponse = createProductType("Laticinio", ProductTypeDTO::class.java)
        val productTypeID = createResponse.body!!.id

        val deleteResponse = deleteProductType(productTypeID, Void::class.java)

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.statusCode)
    }

    @Test
    fun shouldReturn404_whenTryingToDeleteNonexistentProductType_byID() {
        val deleteResponse = deleteProductType("INVALID ID", Void::class.java)

        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.statusCode)
    }

    private fun <T> deleteProductType(id: String?, typeKey: Class<T>): ResponseEntity<T> {
        return restTemplate.exchange("$serverURL/productType/$id", HttpMethod.DELETE, requestEntity, typeKey)
    }

    private fun <T> retrieveProductType(id: String?, typeKey: Class<T>): ResponseEntity<T> {
        return restTemplate.exchange("$serverURL/productType/$id", HttpMethod.GET, requestEntity, typeKey)
    }

    private fun <T> createProductType(
        name: String?,
        typeKey: Class<T>
    ): ResponseEntity<T> {

        val productTypeDTO = ProductTypeDTO(
            name = name
        )

        val entity = HttpEntity(productTypeDTO)
        return restTemplate.exchange(serverURL!! + "/productType", HttpMethod.POST, entity, typeKey)
    }
}