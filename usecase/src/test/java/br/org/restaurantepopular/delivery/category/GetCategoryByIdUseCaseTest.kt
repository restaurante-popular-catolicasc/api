package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.GetCategoryByIdUseCase
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Optional
import java.util.UUID
import junit.framework.TestCase.assertEquals
import org.mockito.Mockito.*

class GetCategoryByIdUseCaseTest {

    @Mock
    private lateinit var categoryRepository: ICategoryRepository
    private lateinit var getCategoryByIdUseCase: GetCategoryByIdUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategoryByIdUseCase = GetCategoryByIdUseCase(categoryRepository)
    }

    @Test(expected = NotFoundException::class)
    fun shouldReturnNotFoundException_whenTryingGetCategoryByEmptyId() {
        val id = ""
        doThrow(NotFoundException("")).`when`(categoryRepository).findById(id)

        getCategoryByIdUseCase.execute(id)
    }

    @Test
    fun shouldReturnOptionalCategory_wheGetCategoryById() {
        val category = createCategory()
        `when`(categoryRepository.findById(category.id!!)).thenReturn(category)

        val getCategoryReturn = getCategoryByIdUseCase.execute(category.id!!)

        assertEquals(getCategoryReturn, category)
    }

    private fun createCategory() =
        Category(id = UUID.randomUUID().toString(), name = "Arroz", description = "Arroz branco")
}
