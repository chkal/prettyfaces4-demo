package de.chkal.pf4demo.web.details;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.Parameter;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.annotation.Rule;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.urlbuilder.Address;

import de.chkal.pf4demo.dao.BookDao;
import de.chkal.pf4demo.model.Book;
import de.chkal.pf4demo.web.cart.Cart;
import de.chkal.pf4demo.web.utils.ResponseUtils;

@Named
@RequestScoped
@Rule("book")
@Join(path = "/buch/{isbn}", to = "/faces/book.xhtml")
public class BookBean
{

   @Parameter
   private Long isbn;

   private Book book;

   @Inject
   private BookDao bookDao;

   @Inject
   private Cart cartBean;

   @RequestAction
   @Deferred
   public String loadData()
   {

      book = bookDao.getByIsbn(isbn);

      if (book == null) {
         ResponseUtils.sendError(404);
         return null;
      }

      return null;

   }

   public String addToCart()
   {

      cartBean.addBook(book);

      return Address.begin()
               .path("/faces/book.xhtml")
               .query("faces-redirect", true)
               .query("isbn", isbn)
               .toString();

   }

   public Long getIsbn()
   {
      return isbn;
   }

   public void setIsbn(Long isbn)
   {
      this.isbn = isbn;
   }

   public Book getBook()
   {
      return book;
   }

}
