package com.hkc.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.hkc.cqrs.application.cartitem.command.create.CreateCartItemCommand;
import com.hkc.cqrs.application.cartitem.command.create.CreatedCartItemResponse;
import com.hkc.cqrs.core.web.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-items")
public class CartItemController extends BaseController {
    public CartItemController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCartItemResponse createCartItem(@RequestBody CreateCartItemCommand command) {
        return command.execute(pipeline);
    }
}
