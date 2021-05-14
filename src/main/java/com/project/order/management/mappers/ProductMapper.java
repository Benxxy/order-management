package com.project.order.management.mappers;

import com.project.order.management.data.ProductsDTO;
import com.project.order.management.data.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductsDTO> productsListToProductsDTOList(List<Products> products);

    ProductsDTO productToProductDTO(Products products);
}
