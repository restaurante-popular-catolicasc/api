package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.GetCategoryByIdUseCase;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class GetCategoryByIdUseCaseTest {

    @Mock
    private ICategoryRepository categoryRepository;

    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getCategoryByIdUseCase = new GetCategoryByIdUseCase(categoryRepository);
    }

    @Test(expected = NotFoundException.class)
    public void shouldReturnNotFoundException_whenTryingGetCategoryByEmptyId() {
        var id = "";
        doThrow(new NotFoundException("")).when(categoryRepository).findById(id);

        getCategoryByIdUseCase.execute(id);
    }


    @Test
    public void shouldReturnOptionalCategory_wheGetCategoryById() {
        var category = createCategory();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        var getCategoryReturn = getCategoryByIdUseCase.execute(category.getId());

        assertEquals(getCategoryReturn, Optional.of(category));
    }

    private Category createCategory() {
        return new Category.CategoryBuilder()
                .id(UUID.randomUUID().toString())
                .name("Arroz")
                .description("teste")
                .build();
    }

}
