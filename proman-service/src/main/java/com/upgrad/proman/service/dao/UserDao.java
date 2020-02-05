package com.upgrad.proman.service.dao;

import com.upgrad.proman.service.entity.UserEntity;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UserDao {

    // Container Managed Entity Manager.
    // Two types of Entity Manager
    // 1. Container Managed
    // 2. Application Managed
    @PersistenceContext
    EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity){
      entityManager.persist(userEntity);
      return userEntity;
    }

    public UserEntity getUser(final String userUuid){
        // Select * from user where user.id = <user_id> and role.id = <role_id>
        // As query parameters /user?id=123&roleId=100
        // Save it in a String parameter
        return entityManager.createNamedQuery("userByUuid", UserEntity.class)
                .setParameter("uuid", userUuid)
                .getSingleResult();
    }
}
