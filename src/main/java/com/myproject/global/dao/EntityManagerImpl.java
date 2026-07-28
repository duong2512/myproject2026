package com.myproject.global.dao;

import lombok.Generated;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ConditionalOnProperty(
        name = {"spring.datasource.url"}
)
@Repository
public class EntityManagerImpl {
    @PersistenceContext
    private EntityManager pcEntityManagerDefault;

    public EntityManagerImpl() {
    }

    @Generated
    public EntityManager getPcEntityManagerDefault() {
        return this.pcEntityManagerDefault;
    }
}
