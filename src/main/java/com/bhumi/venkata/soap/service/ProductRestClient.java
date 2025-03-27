package com.bhumi.venkata.soap.service;


import com.bhumi.venkata.product.types.DummyJsonResponse;
import com.bhumi.venkata.product.types.ProductType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductRestClient {

    private static final String PRODUCTS_REST_URL = "https://dummyjson.com/products";

    public ProductType getProduct(long productId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(PRODUCTS_REST_URL + "/" + productId, ProductType.class).getBody();
    }

    public DummyJsonResponse getAllProduct() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(PRODUCTS_REST_URL, DummyJsonResponse.class).getBody();
    }

}
