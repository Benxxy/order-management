package com.project.order.management.controller;

import com.project.order.management.data.ProductsDTO;
import com.project.order.management.data.entity.Products;
import com.project.order.management.mappers.ProductMapper;
import com.project.order.management.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsRepository productsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductsDTO>> getAllProducts() {
        List<Products> products = productsRepository.findAll();
        return new ResponseEntity<List<ProductsDTO>>(ProductMapper.INSTANCE.productsListToProductsDTOList(products), HttpStatus.OK);
    }
}
