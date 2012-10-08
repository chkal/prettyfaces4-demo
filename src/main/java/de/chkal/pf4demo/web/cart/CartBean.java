package de.chkal.pf4demo.web.cart;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;

import de.chkal.pf4demo.model.Book;

@Named
@RequestScoped
@Join(path = "/cart", to = "/faces/cart.xhtml")
public class CartBean
{

   @Inject
   private Cart cart;

   private List<Book> books;

   private float sum;

   @RequestAction
   @Deferred
   public void init()
   {

      books = cart.getBooks();

      sum = 0.0f;
      for (Book book : books) {
         sum += book.getPrice();
      }

   }

   public List<Book> getBooks()
   {
      return books;
   }

   public float getSum()
   {
      return sum;
   }

}
