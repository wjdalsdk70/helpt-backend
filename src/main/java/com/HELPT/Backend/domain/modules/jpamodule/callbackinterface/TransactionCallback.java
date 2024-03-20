package com.HELPT.Backend.domain.modules.jpamodule.callbackinterface;

import jakarta.persistence.EntityManager;

public interface TransactionCallback<T> {
    T action(EntityManager em);
}
