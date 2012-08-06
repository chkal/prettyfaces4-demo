package de.chkal.pf4demo.web.details;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.prettyfaces.annotation.Join;
import org.ocpsoft.prettyfaces.annotation.MappingId;
import org.ocpsoft.prettyfaces.annotation.ParameterBinding;
import org.ocpsoft.prettyfaces.annotation.URLAction;

import de.chkal.pf4demo.dao.BookDao;
import de.chkal.pf4demo.model.Book;
import de.chkal.pf4demo.web.cart.Cart;
import de.chkal.pf4demo.web.utils.ResponseUtils;

@Named
@RequestScoped
@MappingId("book")
@Join(path = "/buch/{isbn}", to = "/faces/book.xhtml")
public class BookBean {

    @ParameterBinding
    private Long isbn;

    private Book book;

    @Inject
    private BookDao bookDao;

    @Inject
    private Cart cartBean;

    @URLAction
    public String loadData() {

        book = bookDao.getByIsbn(isbn);

        if (book == null) {
            ResponseUtils.sendError(404);
            return null;
        }

        return null;

    }

    public String addToCart() {

        cartBean.addBook(book);

        return "rewrite:";

    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Book getBook() {
        return book;
    }

}
