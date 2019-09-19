package br.org.restaurantepopular.delivery.category;

import br.org.restaurantepopular.usecase.category.DeleteCategoryUseCase;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static junit.framework.TestCase.*;

public class DeleteCategoryUseCaseTest {

    @Mock
    private ICategoryRepository categoryRepository;

    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryRepository);
    }

    @Test(expected = NotFoundException.class)
    public void shouldReturnNotFoundException_whenTryingDeleteCategoryWithEmptyId() {
        var id = "";
        doThrow(new NotFoundException("")).when(categoryRepository).delete(id);

        deleteCategoryUseCase.execute(id);
    }

    @Test
    public void shouldReturnEmptyOptional_whenDeleteCategoryWithCorrectId() {
        var id = "";
        doNothing().when(categoryRepository).delete(id);

        var deleteReturn = deleteCategoryUseCase.execute(id);

        assertEquals(deleteReturn, Optional.empty());
    }
}
