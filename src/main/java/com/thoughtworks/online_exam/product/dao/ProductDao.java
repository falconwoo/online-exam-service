package com.thoughtworks.online_exam.product.dao;

import com.thoughtworks.online_exam.common.jpa.BaseDaoWrapper;
import com.thoughtworks.online_exam.product.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductDao extends BaseDaoWrapper<ProductModel> {
    public ProductDao() {
        super(ProductModel.class);
    }
}
