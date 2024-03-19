package com.HELPT.Backend.domain.callbackinterface;

import jakarta.persistence.EntityManager;

public interface TransactionCallback<T> {
    T action(EntityManager em);
}
