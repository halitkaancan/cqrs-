package com.hkc.cqrs.application.cart.service;

import com.hkc.cqrs.domain.entity.Cart;
import com.hkc.cqrs.domain.entity.Student;

public interface CartService {

    Cart createCartForStudent(Student student);
    Cart getOrCreateCartForStudent(Student student);

}
