package cn.edu.njupt.zx120.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by wallance on 1/13/17.
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass =
                (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    @Autowired
    private SessionFactory sessionFactory =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void save(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
}
