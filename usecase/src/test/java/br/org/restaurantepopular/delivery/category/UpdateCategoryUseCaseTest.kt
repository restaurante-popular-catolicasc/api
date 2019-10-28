package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.category.UpdateCategoryUseCase
import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import br.org.restaurantepopular.usecase.core.exception.ValidationException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Optional
import java.util.UUID
import junit.framework.TestCase.assertEquals
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doThrow

class UpdateCategoryUseCaseTest {

    @Mock
    private lateinit var categoryRepository: ICategoryRepository

    private lateinit var updateCategoryUseCase: UpdateCategoryUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        updateCategoryUseCase = UpdateCategoryUseCase(categoryRepository)
    }

    @Test(expected = ValidationException::class)
    fun shouldReturnValidationException_whenUpdatingCategoryWithoutId() {
        val category = Category(name = "Arroz", description = "Arroz branco")

        updateCategoryUseCase.execute(category)
    }

    @Test(expected = ValidationException::class)
    fun shouldReturnValidationException_whenUpdatingCategoryWithoutName() {
        val category = Category(id = UUID.randomUUID().toString(), name = "", description = "Arroz branco")

        updateCategoryUseCase.execute(category)
    }

    @Test(expected = ValidationException::class)
    fun shouldReturnValidationException_whenUpdatingCategoryWithoutDescription() {
        val category = Category(id = UUID.randomUUID().toString(), name = "Arroz", description = "")

        updateCategoryUseCase.execute(category)
    }

    @Test(expected = NotFoundException::class)
    fun shouldReturnNotFoundException_whenUpdatingCategoryWithWrongId() {
        val category = createCategory()

        doThrow(NotFoundException("")).`when`(categoryRepository).update(category)

        updateCategoryUseCase.execute(category)
    }

    @Test
    fun shouldReturnCategory_whenCreatingCategory() {
        val category = createCategory()
        doNothing().`when`(categoryRepository).update(category)

        val updateReturn = updateCategoryUseCase.execute(category)

        assertEquals(updateReturn, Unit)
    }

    private fun createCategory() =
        Category(id = UUID.randomUUID().toString(), name = "Arroz", description = "Arroz branco")
}
