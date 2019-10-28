package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.delivery.MainApplication
import br.org.restaurantepopular.delivery.rest.api.category.CategoryDTO
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
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryIntegrationTest {

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
    fun shouldReturn201_whenCreatingANewCategory() {
        val response = createCategory("Arroz", "Arroz Branco", Unit::class.java)

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun shouldReturn422_whenCreatingANewCategoryWithoutName() {
        val response = createCategory(null, "Arroz Branco", Unit::class.java)

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }

    @Test
    fun shouldReturn422_whenCreatingANewCategoryWithoutDescription() {
        val response = createCategory("Arroz", null, Unit::class.java)

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }

    @Test
    fun shouldReturn200AndTheCategory_whenRetrievingACategoryThatExists() {
        val name = "Arroz"
        val description = "Arroz Branco"
        val createResponse = createCategory(name, description, CategoryDTO::class.java)

        val retrieveResponse = retrieveCategory(createResponse.body!!.id, CategoryDTO::class.java)
        val responseDto = retrieveResponse.body

        assertEquals(HttpStatus.OK, retrieveResponse.statusCode)
        assertEquals(name, responseDto!!.name)
        assertEquals(description, responseDto.description)
    }

    @Test
    fun shouldReturn404_whenTryingRetrieveACategoryThatDoesNotExist_byID() {
        val retrieveResponse = retrieveCategory("23234234234", CategoryDTO::class.java)
        assertEquals(HttpStatus.NOT_FOUND, retrieveResponse.statusCode)
    }

    @Test
    fun shouldReturn200AndCategoryList_whenRetrievingCategoriesWith2Registers() {
        createCategory("Arroz", "Arroz Branco", CategoryDTO::class.java)
        createCategory("Feijão", "Feijão carioca", CategoryDTO::class.java)

        val retrieveResponse = retrieveCategory("", List::class.java)
        val responseDTO = retrieveResponse.body

        assertEquals(HttpStatus.OK, retrieveResponse.statusCode)
        assertNotNull(responseDTO)
        assertEquals(2, responseDTO!!.size)
    }

    @Test
    fun shouldReturn204_whenTryingToDeleteExistentCategory_byID() {
        val createResponse = createCategory("Feijão", "Feijão carioca", CategoryDTO::class.java)
        val categoryID = createResponse.body!!.id

        val deleteResponse = deleteCategory(categoryID, Unit::class.java)

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.statusCode)
    }

    @Test
    fun shouldReturn404_whenTryingToDeleteNoneExistentCategory_byID() {
        val deleteResponse = deleteCategory("INVALID ID", Unit::class.java)

        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.statusCode)
    }

    private fun <T> deleteCategory(id: String?, typeKey: Class<T>): ResponseEntity<T> {
        return restTemplate.exchange("$serverURL/categories/$id", HttpMethod.DELETE, requestEntity, typeKey)
    }

    private fun <T> retrieveCategory(id: String?, typeKey: Class<T>): ResponseEntity<T> {
        return restTemplate.exchange("$serverURL/categories/$id", HttpMethod.GET, requestEntity, typeKey)
    }

    private fun <T> createCategory(
        name: String?,
        description: String?,
        typeKey: Class<T>
    ): ResponseEntity<T> {

        val category = CategoryDTO(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description
        )

        val entity = HttpEntity(category)
        return restTemplate.exchange(serverURL!! + "/categories", HttpMethod.POST, entity, typeKey)
    }
}