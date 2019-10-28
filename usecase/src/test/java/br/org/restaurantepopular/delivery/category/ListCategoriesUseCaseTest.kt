package br.org.restaurantepopular.delivery.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.category.ListCategoriesUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.UUID
import junit.framework.TestCase.assertEquals
import org.mockito.Mockito.`when`

class ListCategoriesUseCaseTest {

    @Mock
    private lateinit var categoryRepository: ICategoryRepository

    private lateinit var listCategoriesUseCase: ListCategoriesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        listCategoriesUseCase = ListCategoriesUseCase(categoryRepository)
    }

    @Test
    fun shouldReturnEmptyList_whenRetrievingCategoriesWithoutRegisters() {
        val emptyList = emptyList<Category>()
        `when`(categoryRepository.listCategories()).thenReturn(emptyList)

        val listCategoriesReturn = listCategoriesUseCase.execute(Unit)

        assertEquals(listCategoriesReturn, emptyList)
        assertEquals(listCategoriesReturn.size, emptyList.size)
    }

    @Test
    fun shouldReturnCategoryList_whenRetrievingCategoriesWith2Registers() {
        val category1 = createCategory()
        val category2 = createCategory()
        val categories = listOf(category1, category2)
        `when`(categoryRepository.listCategories()).thenReturn(categories)

        val listCategoriesReturn = listCategoriesUseCase.execute(Unit)

        assertEquals(listCategoriesReturn, categories)
        assertEquals(listCategoriesReturn.size, categories.size)
        assertEquals(listCategoriesReturn.size, 2)
    }

    private fun createCategory() =
        Category(id = UUID.randomUUID().toString(), name = "Arroz", description = "Arroz branco")
}
