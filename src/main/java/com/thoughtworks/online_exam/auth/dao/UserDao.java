package com.thoughtworks.online_exam.auth.dao;

import com.thoughtworks.online_exam.common.jpa.BaseDaoWrapper;
import com.thoughtworks.online_exam.product.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends BaseDaoWrapper<ProductModel> {
    public UserDao() {
        super(ProductModel.class);
    }
}
