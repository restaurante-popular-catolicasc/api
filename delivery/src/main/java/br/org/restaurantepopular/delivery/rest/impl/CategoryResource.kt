package br.org.restaurantepopular.delivery.rest.impl

import br.org.restaurantepopular.delivery.rest.api.category.CategoryDTO
import br.org.restaurantepopular.delivery.rest.api.category.ICategoryResource
import br.org.restaurantepopular.delivery.rest.api.category.toCategory
import br.org.restaurantepopular.delivery.rest.api.category.toCategoryDto
import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.*
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryResource @Autowired
constructor(
    private val useCaseExecutor: IUseCaseExecutor,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val listCategoriesUseCase: ListCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ICategoryResource {

    override fun getCategoryById(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = getCategoryByIdUseCase,
        requestDto = id,
        requestConverter = { it },
        responseConverter = { it.toCategoryDto() }
    )


    override fun createCategory(
        @RequestBody categoryDTO: CategoryDTO
    ) = useCaseExecutor(
        useCase = createCategoryUseCase,
        requestDto = categoryDTO,
        requestConverter = { it.toCategory() },
        responseConverter = { ResponseEntity(it.toCategoryDto(), HttpStatus.CREATED) }
    )

    override fun updateCategory(
        @PathVariable("id") id: String,
        @RequestBody categoryDTO: CategoryDTO
    ) = useCaseExecutor(
        useCase = updateCategoryUseCase,
        requestDto = categoryDTO.apply { this.id = id },
        requestConverter = { it.toCategory() },
        responseConverter = { ResponseEntity<Unit>(HttpStatus.OK) }
    )


    override fun listCategories() = useCaseExecutor(
        useCase = listCategoriesUseCase,
        responseConverter = { it.map(Category::toCategoryDto) })

    override fun deleteCategory(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = deleteCategoryUseCase,
        requestDto = id,
        requestConverter = { it },
        responseConverter = { ResponseEntity<Unit>(HttpStatus.NO_CONTENT) }
    )

}


