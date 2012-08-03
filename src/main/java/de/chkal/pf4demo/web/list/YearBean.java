package de.chkal.pf4demo.web.list;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.chkal.pf4demo.dao.BookDao;
import de.chkal.pf4demo.model.Book;

@Named
@RequestScoped
public class YearBean {

    private Integer year;

    @Inject
    private BookDao bookDao;

    private List<Book> books;

    public void preRenderView() {
        books = bookDao.findByYear(year);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
