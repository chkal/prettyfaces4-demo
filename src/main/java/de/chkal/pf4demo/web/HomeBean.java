package de.chkal.pf4demo.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;

import de.chkal.pf4demo.dao.CategoryDao;
import de.chkal.pf4demo.model.Category;

@Named
@RequestScoped
@Join(path = "/home", to = "/faces/home.xhtml")
public class HomeBean
{

   @Inject
   private CategoryDao categoryDao;

   private List<Category> categories;

   @RequestAction
   @Deferred
   public void loadData()
   {
      categories = categoryDao.list();
   }

   public List<Category> getCategories()
   {
      return categories;
   }

}
