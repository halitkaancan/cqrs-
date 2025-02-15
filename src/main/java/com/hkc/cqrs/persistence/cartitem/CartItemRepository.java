package com.hkc.cqrs.persistence.cartitem;

import com.hkc.cqrs.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository  extends JpaRepository<CartItem, UUID> {
}
