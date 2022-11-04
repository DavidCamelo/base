package com.davidcamelo.base.exception;

import com.davidcamelo.base.controller.CategoryController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CategoryController.class)
public class CategoryControllerAdvice extends BaseRestControllerAdvice {
}
