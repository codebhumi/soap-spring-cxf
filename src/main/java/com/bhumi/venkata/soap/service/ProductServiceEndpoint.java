package com.bhumi.venkata.soap.service;

import com.bhumi.venkata.product.ProductWSPortType;
import com.bhumi.venkata.product.types.GetAllProductsRequest;
import com.bhumi.venkata.product.types.ProductType;
import com.bhumi.venkata.product.types.ProductsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.feature.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.math.BigInteger;

@Service
@Slf4j
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
public class ProductServiceEndpoint implements ProductWSPortType {

    @Autowired
    private ProductRestClient productRestClient;

    @Override
    public void getProductById(BigInteger productId, Holder<ProductType> product) {
        product.value = productRestClient.getProduct(productId.longValue());
    }

    @Override
    public ProductsResponse getAllProducts(GetAllProductsRequest parameters) {
        ProductsResponse response = new ProductsResponse();
        response.setDummyJsonResponse(productRestClient.getAllProduct());
        return response;
    }
}
