package ru.kata.spring.boot_security.demo.repository;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User updated) {
        entityManager.merge(updated);
    }

    @Override
    public void deleteById(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByBankAccount(String bankAccount) {
        return entityManager.createQuery("SELECT user FROM User user join fetch  user.roles WHERE user.bankAccount =:bankAccount", User.class)
                .setParameter("bankAccount", bankAccount)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

}




