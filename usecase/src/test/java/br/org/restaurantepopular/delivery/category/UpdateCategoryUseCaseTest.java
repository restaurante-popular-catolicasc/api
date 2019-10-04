package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.category.UpdateCategoryUseCase;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class UpdateCategoryUseCaseTest {

    @Mock
    private ICategoryRepository categoryRepository;

    private UpdateCategoryUseCase updateCategoryUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryRepository);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnValidationException_whenUpdatingCategoryWithoutId() {
        var category = new Category.CategoryBuilder()
                .name("Arroz branco")
                .description("Arroz")
                .build();

        updateCategoryUseCase.execute(category);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnValidationException_whenUpdatingCategoryWithoutName() {
        var category = new Category.CategoryBuilder()
                .id(UUID.randomUUID().toString())
                .description("Arroz")
                .build();

        updateCategoryUseCase.execute(category);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnValidationException_whenUpdatingCategoryWithoutDescription() {
        var category = new Category.CategoryBuilder()
                .id(UUID.randomUUID().toString())
                .name("Arroz")
                .build();

        updateCategoryUseCase.execute(category);
    }

    @Test(expected = NotFoundException.class)
    public void shouldReturnNotFoundException_whenUpdatingCategoryWithWrongId() {
        var category = createCategory();

        doThrow(new NotFoundException("")).when(categoryRepository).update(category);

        updateCategoryUseCase.execute(category);
    }

    @Test
    public void shouldReturnCategory_whenCreatingCategory() {
        var category = createCategory();
        doNothing().when(categoryRepository).update(category);

        var updateReturn = updateCategoryUseCase.execute(category);

        assertEquals(updateReturn, Optional.empty());
    }

    private Category createCategory() {
        return new Category.CategoryBuilder()
                .id(UUID.randomUUID().toString())
                .name("Arroz")
                .description("teste")
                .build();
    }
}
