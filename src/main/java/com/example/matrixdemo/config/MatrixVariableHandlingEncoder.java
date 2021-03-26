/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.example.matrixdemo.config;

import feign.RequestTemplate;
import feign.form.spring.SpringFormEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * The Encoder implementation will use to add matrix params in request.
 */
public class MatrixVariableHandlingEncoder extends SpringEncoder {

    public MatrixVariableHandlingEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {

        super(messageConverters);

        System.out.println("OGL: CREATING ENCODER");
    }


    @Override
    @SuppressWarnings("unchecked")
    public void encode(Object object, Type type, RequestTemplate template) {

        System.out.println("OGL: running encode on " + object);
        if (object instanceof Map) {
            Map<String, List<String>> matrixParams = (Map) object;

            StringBuilder builder = new StringBuilder();
            if (!matrixParams.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : matrixParams.entrySet()) {
                    entry.getValue().forEach(value -> builder.append(";").append(entry.getKey()).append("=")
                            .append(value));
                }
            }
            System.out.println("OGL: running encode on " + builder.toString());
            template.uri(StringUtils.isNotEmpty(builder.toString()) ? builder.substring(0) : "", true);

        }
        else if (object instanceof MultipartFile) {
            new SpringFormEncoder().encode(object, type, template);
        }
        else {
            super.encode(object, type, template);
        }

    }

}
