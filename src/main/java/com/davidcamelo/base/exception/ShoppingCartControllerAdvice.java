package com.davidcamelo.base.exception;

import com.davidcamelo.base.controller.ShoppingCartController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ShoppingCartController.class)
public class ShoppingCartControllerAdvice extends BaseRestControllerAdvice {
}
