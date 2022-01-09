package ru.gb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String cost;

    public ProductDto(Product product) {
        name = product.getName();
        cost = product.getCost().toString();
    }
}
