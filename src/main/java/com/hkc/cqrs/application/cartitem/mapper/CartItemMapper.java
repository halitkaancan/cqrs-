package com.hkc.cqrs.application.cartitem.mapper;

import com.hkc.cqrs.application.cartitem.command.create.CreateCartItemCommand;
import com.hkc.cqrs.application.cartitem.command.create.CreatedCartItemResponse;
import com.hkc.cqrs.domain.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {

    @Mapping(target = "book.id", source = "bookId")
    public abstract CartItem mapFromCreateCartItemCommand(CreateCartItemCommand createCartItemCommand);

    public abstract CreatedCartItemResponse mapCreatedResponseFromCartItem(CartItem cartItem);
}
