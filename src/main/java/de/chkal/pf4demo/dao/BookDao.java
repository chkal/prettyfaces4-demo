package de.chkal.pf4demo.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import de.chkal.pf4demo.model.Book;
import de.chkal.pf4demo.model.Category;

@ApplicationScoped
public class BookDao extends AbstractDao<Book> {

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }

    public List<Book> findByCategory(Category category) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.category = :category ORDER BY b.title", Book.class)
                .setParameter("category", category)
                .getResultList();
    }

    public Book getByIsbn(Long isbn) {
        try {
            return entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Book> findByQuery(String query) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :query OR LOWER(b.author) LIKE :query ORDER BY b.title", Book.class)
                .setParameter("query", "%" + query.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> findByYear(Integer year) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.year= :year ORDER BY b.title", Book.class)
                .setParameter("year", year)
                .getResultList();
    }

}
