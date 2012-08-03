package de.chkal.pf4demo.web.list;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.prettyfaces.annotation.Join;
import org.ocpsoft.prettyfaces.annotation.ParameterBinding;
import org.ocpsoft.prettyfaces.annotation.URLAction;

import de.chkal.pf4demo.dao.BookDao;
import de.chkal.pf4demo.dao.CategoryDao;
import de.chkal.pf4demo.model.Book;
import de.chkal.pf4demo.model.Category;
import de.chkal.pf4demo.web.utils.ResponseUtils;

@Named
@RequestScoped
@Join(path = "/category/{seoKey}", to = "/faces/category.xhtml")
public class CategoryBean {

    @ParameterBinding
    private String seoKey;

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private BookDao bookDao;

    private List<Book> books;

    @URLAction
    public String loadData() {

        Category category = categoryDao.getBySeoKey(seoKey);

        if (category == null) {
            ResponseUtils.sendError(404);
            return null;
        }

        books = bookDao.findByCategory(category);

        return null;

    }

    public List<Book> getBooks() {
        return books;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

}
