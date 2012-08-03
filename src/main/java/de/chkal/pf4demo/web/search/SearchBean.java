package de.chkal.pf4demo.web.search;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ocpsoft.prettyfaces.annotation.Join;
import org.ocpsoft.prettyfaces.annotation.QueryParameterBinding;
import org.ocpsoft.prettyfaces.annotation.URLAction;

import de.chkal.pf4demo.dao.BookDao;
import de.chkal.pf4demo.model.Book;

@Named
@RequestScoped
@Join(path = "/search", to = "/faces/search.xhtml")
public class SearchBean {

    @QueryParameterBinding("q")
    private String query;

    private List<Book> books;

    @Inject
    private BookDao bookDao;

    @URLAction
    public void search() {
        if (query != null && query.trim().length() > 0) {
            books = bookDao.findByQuery(query);
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Book> getBooks() {
        return books;
    }

}
