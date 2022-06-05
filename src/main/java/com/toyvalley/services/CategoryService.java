package com.toyvalley.services;

import com.toyvalley.models.data.category.CategoryResponse;
import com.toyvalley.models.data.category.CreateCategoryRequest;
import com.toyvalley.models.data.category.SearchCategoryResponse;
import com.toyvalley.models.data.category.UpdateCategoryRequest;
import com.toyvalley.models.entities.Category;
import com.toyvalley.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        List<Category> categoriesList = new ArrayList<>();

        categoriesList.add(generateVehicleCategory());
        categoriesList.add(generateActionFiguresCategory());
        categoriesList.add(generateEducationalCategory());
        categoriesList.add(generateDollsCategory());
        categoriesList.add(generateOutdoorSeasonalToysCategory());
        categoriesList.add(generateArtsAndCraftsCategory());
        categoriesList.add(generateBuildingAndConstructionCategory());
        categoriesList.add(generateElectronicCategory());
        categoriesList.add(generateGamesAndPuzzlesCategory());
        categoriesList.add(generateInfantToysCategory());
        categoriesList.add(generateOtherCategory());
        categoriesList.add(generateMusicalInstrumentsCategory());
    }

        public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        ArrayList<CategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            responseList.add(new CategoryResponse(category.getId(), category.getName(), category.getDescription()));
        }

        return responseList;
    }


    public CategoryResponse getCategory(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
        }

        throw new RuntimeException("Category not found provided id:" + id);
    }


    public CategoryResponse createCategory(CreateCategoryRequest category) {
        Category categoryEntity = new Category(category.getName(), category.getDescription());
        Category newCategory = categoryRepository.save(categoryEntity);
        return new CategoryResponse(newCategory.getId(), newCategory.getName(), newCategory.getDescription());
    }

    public CategoryResponse updateCategory(long id, UpdateCategoryRequest category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category categoryEntity = categoryOptional.get();
            categoryEntity.update(category.getName(), category.getDescription());
            categoryRepository.save(categoryEntity);
            return new CategoryResponse(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
        }


        throw new RuntimeException("Category with id " + id + " not found.");
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    private Category generateVehicleCategory() {
        Category category = new Category();
        category.setId(1);

        category.setName("Vehicles");

        category.setDescription("Vehicles are one of the most popular categories for boys.");
        return category;
    }

    private Category generateActionFiguresCategory() {
        Category category = new Category();
        category.setId(2);

        category.setName("Action Figures");

        category.setDescription("Ready for little action?");
        return category;
    }

    private Category generateGamesAndPuzzlesCategory() {
        Category category = new Category();
        category.setId(3);

        category.setName("Games and Puzzles");

        category.setDescription("Never-getting-old.");
        return category;
    }

    private Category generateArtsAndCraftsCategory() {
        Category category = new Category();
        category.setId(4);

        category.setName("Arts and crafts");

        category.setDescription("Perfect place for small artists.");
        return category;
    }

    private Category generateBuildingAndConstructionCategory() {
        Category category = new Category();
        category.setId(5);

        category.setName("Building and construction");

        category.setDescription("Bob the builder has never seemed so close.");
        return category;
    }

    private Category generateDollsCategory() {
        Category category = new Category();
        category.setId(6);

        category.setName("Dolls");

        category.setDescription("The most popular category for girls.");
        return category;
    }

    private Category generateEducationalCategory() {
        Category category = new Category();
        category.setId(7);

        category.setName("Educational");

        category.setDescription("Start education with small steps.");
        return category;
    }

    private Category generateElectronicCategory() {
        Category category = new Category();
        category.setId(8);

        category.setName("Electronic");

        category.setDescription("Into rock'n'roll? Here we are.");
        return category;
    }

    private Category generateInfantToysCategory() {
        Category category = new Category();
        category.setId(9);

        category.setName("Infrant toys");

        category.setDescription("Don't get rid of your infant's smile.");
        return category;
    }

    private Category generateMusicalInstrumentsCategory() {
        Category category = new Category();
        category.setId(10);

        category.setName("Musical Instruments");

        category.setDescription("Real place for small musicians.");
        return category;
    }

    private Category generateOtherCategory() {
        Category category = new Category();
        category.setId(11);

        category.setName("Other");

        category.setDescription("Didn't find anything yet? Check other.");
        return category;
    }

    private Category generateOutdoorSeasonalToysCategory() {
        Category category = new Category();
        category.setId(12);

        category.setName("Outdoor seasonal toys");

        category.setDescription("Outdoor Seasonal Toys might cheer up your kid during any season.");
        return category;
    }

    public List<SearchCategoryResponse> getCategoryByName(String name) {
        List<SearchCategoryResponse> categoryResponseList = categoryRepository.getCategoriesByName(name);
        return categoryResponseList;
    }
}
