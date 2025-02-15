package com.hkc.cqrs.application.cart.service;

import com.hkc.cqrs.domain.entity.Cart;
import com.hkc.cqrs.domain.entity.Student;
import com.hkc.cqrs.persistence.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart createCartForStudent(Student student) {
        //todo business rules aynı student için 2. cart olmamalı

        Cart cart = new Cart();
        cart.setStudent(student);
        cartRepository.save(cart);
        return cart;
    }
}
