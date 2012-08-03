package de.chkal.pf4demo.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

import de.chkal.pf4demo.model.Category;

@ApplicationScoped
public class CategoryDao extends AbstractDao<Category> {

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }
    
    public Category getBySeoKey(String key) {
        try {
            return entityManager.createQuery("SELECT c FROM Category c WHERE c.seoKey = :key", Category.class)
                    .setParameter("key", key)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
