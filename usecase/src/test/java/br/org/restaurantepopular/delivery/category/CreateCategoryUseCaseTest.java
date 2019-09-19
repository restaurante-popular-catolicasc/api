package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.CreateCategoryUseCase;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static junit.framework.TestCase.*;

public class CreateCategoryUseCaseTest {

    @Mock
    private ICategoryRepository categoryRepository;

    private CreateCategoryUseCase createCategoryUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        createCategoryUseCase = new CreateCategoryUseCase(categoryRepository);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnValidationException_whenCreatingCategoryWithoutName() {
        var category = new Category.CategoryBuilder()
                .description("Arroz")
                .build();

        createCategoryUseCase.execute(category);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnValidationException_whenCreatingCategoryWithoutDescription() {
        var category = new Category.CategoryBuilder()
                .name("Arroz")
                .build();

        createCategoryUseCase.execute(category);
    }

    @Test
    public void shouldReturnCategory_whenCreatingCategory() {
        var category = createCategory();
        when(categoryRepository.save(category)).thenReturn(Optional.of(category));

        var executeReturn = createCategoryUseCase.execute(category);

        assertEquals(executeReturn, Optional.of(category));
    }

    private Category createCategory() {
        return new Category.CategoryBuilder()
                .name("Arroz")
                .description("teste")
                .build();
    }
}
