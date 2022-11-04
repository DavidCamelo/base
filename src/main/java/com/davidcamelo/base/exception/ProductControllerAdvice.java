package com.davidcamelo.base.exception;

import com.davidcamelo.base.controller.ProductController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ProductController.class)
public class ProductControllerAdvice extends BaseRestControllerAdvice {
}
