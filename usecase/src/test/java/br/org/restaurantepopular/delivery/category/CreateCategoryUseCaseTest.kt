package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.CreateCategoryUseCase
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.core.exception.ValidationException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Optional
import org.mockito.Mockito.`when`
import junit.framework.TestCase.*

class CreateCategoryUseCaseTest {

    @Mock
    private lateinit var categoryRepository: ICategoryRepository
    private lateinit var createCategoryUseCase: CreateCategoryUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        createCategoryUseCase = CreateCategoryUseCase(categoryRepository)
    }

    @Test(expected = ValidationException::class)
    fun shouldReturnValidationException_whenCreatingCategoryWithoutName() {
        val category = Category(description = "arroz", name = "")

        createCategoryUseCase.execute(category)
    }

    @Test(expected = ValidationException::class)
    fun shouldReturnValidationException_whenCreatingCategoryWithoutDescription() {
        val category = Category(name = "Arroz branco", description = "")

        createCategoryUseCase.execute(category)
    }

    @Test
    fun shouldReturnCategory_whenCreatingCategory() {
        val category = createCategory()
        `when`(categoryRepository.save(category)).thenReturn(category)

        val executeReturn = createCategoryUseCase.execute(category)

        assertEquals(executeReturn, category)
    }

    private fun createCategory() = Category(name = "Arroz", description = "Arroz branco")
}
