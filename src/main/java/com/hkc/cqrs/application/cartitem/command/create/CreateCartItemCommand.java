package com.hkc.cqrs.application.cartitem.command.create;

import an.awesome.pipelinr.Command;
import com.hkc.cqrs.application.cart.service.CartService;
import com.hkc.cqrs.application.cartitem.mapper.CartItemMapper;
import com.hkc.cqrs.application.student.service.StudentService;
import com.hkc.cqrs.domain.entity.Cart;
import com.hkc.cqrs.domain.entity.CartItem;
import com.hkc.cqrs.domain.entity.Student;
import com.hkc.cqrs.persistence.cartitem.CartItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
public class CreateCartItemCommand implements Command<CreatedCartItemResponse> {
    private UUID bookId;
    private  int quantity;
    private UUID studentId;//TODO JWT ile çalışmalı

    @Component
    @RequiredArgsConstructor
    public static class CreateCartItemCommandHandler implements Command.Handler<CreateCartItemCommand,CreatedCartItemResponse>{

        private final CartService cartService;
        private final StudentService studentService;
        private final CartItemRepository cartItemRepository;
        private final CartItemMapper cartItemMapper;

        @Override
        public CreatedCartItemResponse handle(CreateCartItemCommand createCartItemCommand) {
            //studentId ile, businessrule - student var mı bak.(StudentBusinessRules)
            Student student = studentService.findStudentById(createCartItemCommand.getStudentId());
            Cart cart = cartService.getOrCreateCartForStudent(student);
            // cart'ı al -> cartService
            //cartItemi oluştur(yoksa) db'e ekle. varsa quantitiyi arttır.

            CartItem cartItem = cart.getCartItems()
                    .stream()
                    .filter((c)->c.getBook().getId().equals(createCartItemCommand.getBookId()))
                    .findFirst().orElse(null);

            //Cartın içinde o kitaptan varsa miktarı arttır
            if(cartItem != null) {


                cartItem.setQuantity(cartItem.getQuantity() + createCartItemCommand.getQuantity());
                cartItemRepository.save(cartItem);

                //yoksa yeni ekle
            }else {

                 cartItem = cartItemMapper.mapFromCreateCartItemCommand(createCartItemCommand);
                 cartItem.setCart(cart);
                 cartItemRepository.save(cartItem);
            }
            return cartItemMapper.mapCreatedResponseFromCartItem(cartItem);
        }
    }

}
