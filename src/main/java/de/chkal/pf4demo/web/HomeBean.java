package de.chkal.pf4demo.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.prettyfaces.annotation.Join;
import org.ocpsoft.prettyfaces.annotation.URLAction;

import de.chkal.pf4demo.dao.CategoryDao;
import de.chkal.pf4demo.model.Category;

@Named
@RequestScoped
@Join(path = "/home", to = "/faces/home.xhtml")
public class HomeBean {

    @Inject
    private CategoryDao categoryDao;

    private List<Category> categories;

    @URLAction
    public void loadData() {
        categories = categoryDao.list();
    }

    public List<Category> getCategories() {
        return categories;
    }

}
