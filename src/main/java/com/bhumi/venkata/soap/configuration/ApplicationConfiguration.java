package com.bhumi.venkata.soap.configuration;

import com.bhumi.venkata.soap.service.ProductServiceEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private UTPasswordCallback utPasswordCallback;

    @Bean
    public Endpoint endpoint(ProductServiceEndpoint productServiceEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, productServiceEndpoint);
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "com.bhumi.venkata.soap.configuration.UTPasswordCallback");
        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        endpoint.getInInterceptors().add(wssIn);

        endpoint.publish("/service/product");
        return endpoint;
    }
}
