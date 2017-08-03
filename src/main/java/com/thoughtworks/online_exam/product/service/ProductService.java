package com.thoughtworks.online_exam.product.service;

import com.thoughtworks.online_exam.common.Loggable;
import com.thoughtworks.online_exam.common.exception.NotFoundException;
import com.thoughtworks.online_exam.product.ProductMapper;
import com.thoughtworks.online_exam.product.entity.Product;
import com.thoughtworks.online_exam.product.model.ProductModel;
import com.thoughtworks.online_exam.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService implements Loggable {
    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(Long productId) {
        ProductModel productModel = productRepository.findOne(productId);
        if (productModel == null) {
            error("Product not found with id: " + productId);
            throw new NotFoundException();
        }

        return mapper.map(productModel, Product.class);
    }

    public List<Product> getProductsByName(String name) {
        List<ProductModel> productModels = productRepository.findByNameAndIsDeleted(name, false);
        return mapper.mapList(productModels, Product.class);
    }

    public Product create(Product product) {
        ProductModel model = mapper.map(product, ProductModel.class);
        model.setTimeCreated(new Date());
        return mapper.map(productRepository.saveAndFlush(model), Product.class);
    }

    public Product getLatestProductByName(String name) {
        ProductModel productModel = productRepository.findLatestProductByName(name);
        return productModel != null ? mapper.map(productModel, Product.class) : null;
    }
}
