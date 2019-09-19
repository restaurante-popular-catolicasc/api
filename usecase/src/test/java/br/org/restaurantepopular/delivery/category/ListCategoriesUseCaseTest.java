package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.category.ListCategoriesUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ListCategoriesUseCaseTest {

    @Mock
    private ICategoryRepository categoryRepository;

    private ListCategoriesUseCase listCategoriesUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        listCategoriesUseCase = new ListCategoriesUseCase(categoryRepository);
    }

    @Test
    public void shouldReturnEmptyList_whenRetrievingCategoriesWithoutRegisters() {
        List<Category> emptyList = Collections.emptyList();
        when(categoryRepository.listCategories()).thenReturn(emptyList);

        var listCategoriesReturn = listCategoriesUseCase.execute(Optional.empty());

        assertEquals(listCategoriesReturn, emptyList);
        assertEquals(listCategoriesReturn.size(), emptyList.size());
    }

    @Test
    public void shouldReturnCategoryList_whenRetrievingCategoriesWith2Registers() {
        var category1 = createCategory();
        var category2 = createCategory();
        var categories = List.of(category1, category2);
        when(categoryRepository.listCategories()).thenReturn(categories);

        var listCategoriesReturn = listCategoriesUseCase.execute(Optional.empty());

        assertEquals(listCategoriesReturn, categories);
        assertEquals(listCategoriesReturn.size(), categories.size());
        assertEquals(listCategoriesReturn.size(), 2);
    }

    private Category createCategory() {
        return new Category.CategoryBuilder()
                .id(UUID.randomUUID().toString())
                .name("Arroz")
                .description("teste")
                .build();
    }
}
