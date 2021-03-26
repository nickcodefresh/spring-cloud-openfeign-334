/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.example.matrixdemo.config;

import feign.Contract;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.MatrixVariable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


/**
 * As per https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html#spring-cloud-feign-overriding-defaults,
 * this class does not need to be annotated with {@link Configuration}; and in fact doing so would require ComponentScan
 * exclusions which it would be nicer to avoid.
 */
public class MatrixEncodingFeignConfiguration {

    @Bean
    public Encoder encoder(ObjectFactory<HttpMessageConverters> messageConverters) {

        return new MatrixVariableHandlingEncoder(messageConverters);
    }


    @Bean
    public Contract feignContract(ConversionService feignConversionService,
                                  List<AnnotatedParameterProcessor> parameterProcessors) {
        SpringMvcContract contract =  new SpringMvcContract(parameterProcessors, feignConversionService);

        try {
            Field annotatedArgumentProcessorsField = SpringMvcContract.class.
                    getDeclaredField("annotatedArgumentProcessors");
            annotatedArgumentProcessorsField.setAccessible(true);
            Map<Class<? extends Annotation>, AnnotatedParameterProcessor> annotatedArgumentProcessors =
                    (Map) annotatedArgumentProcessorsField.get(contract);

            annotatedArgumentProcessors.remove(MatrixVariable.class);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return contract;
    }

}
