package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.usecase.category.DeleteCategoryUseCase
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doThrow
import junit.framework.TestCase.*

class DeleteCategoryUseCaseTest {

    @Mock
    private lateinit var categoryRepository: ICategoryRepository

    private lateinit var deleteCategoryUseCase: DeleteCategoryUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)
    }

    @Test(expected = NotFoundException::class)
    fun shouldReturnNotFoundException_whenTryingDeleteCategoryWithEmptyId() {
        val id = ""
        doThrow(NotFoundException("")).`when`(categoryRepository).delete(id)

        deleteCategoryUseCase.execute(id)
    }

    @Test
    fun shouldReturnEmptyOptional_whenDeleteCategoryWithCorrectId() {
        val id = ""
        doNothing().`when`(categoryRepository).delete(id)

        val deleteReturn = deleteCategoryUseCase.execute(id)

        assertEquals(deleteReturn, Unit)
    }
}
