package com.HELPT.Backend.domain.library.jpamodule.callbackinterface;

import jakarta.persistence.EntityManager;

public interface TransactionCallback<T> {
    T action(EntityManager em);
}
