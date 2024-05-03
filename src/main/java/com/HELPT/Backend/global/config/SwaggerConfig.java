//package com.HELPT.Backend.global.config;
//
//import com.HELPT.Backend.global.common.ApiResponse;
//import io.swagger.v3.oas.models.Operation;
//import io.swagger.v3.oas.models.media.Content;
//import io.swagger.v3.oas.models.media.MediaType;
//import io.swagger.v3.oas.models.media.Schema;
//import io.swagger.v3.oas.models.responses.ApiResponses;
//import org.springdoc.core.customizers.OpenApiCustomizer;
//import org.springdoc.core.customizers.OperationCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.HandlerMethod;
//
//import java.util.Arrays;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OperationCustomizer operationCustomizer() {
//        return this::customizeOperation;
//    }
//
//    private Operation customizeOperation(Operation operation, HandlerMethod handlerMethod) {
//        addResponseBodyWrapperSchemaExample(operation, ApiResponse.class, "data");
//        return operation;
//    }
//
//    private void addResponseBodyWrapperSchemaExample(Operation operation, Class<?> type, String wrapFieldName) {
//        final Content content = operation.getResponses().get("200").getContent();
//        if (content != null) {
//            content.forEach((mediaTypeKey, mediaType) ->
//                    mediaType.setSchema(wrapSchema(mediaType.getSchema(), type, wrapFieldName))
//            );
//        }
//    }
//
//    private <T> Schema<T> wrapSchema(Schema<?> originalSchema, Class<T> type, String wrapFieldName) {
//        final Schema<T> wrapperSchema = new Schema<>();
//        try {
//            T instance = type.getDeclaredConstructor().newInstance();
//            Arrays.stream(type.getDeclaredFields())
//                    .forEach(field -> {
//                        field.setAccessible(true);
//                        try {
//                            wrapperSchema.addProperty(field.getName(), new Schema<>().example(field.get(instance)));
//                        } catch (IllegalAccessException e) {
//                            throw new RuntimeException("Failed to access field value", e);
//                        }
//                        field.setAccessible(false);
//                    });
//            wrapperSchema.addProperty(wrapFieldName, originalSchema);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to instantiate " + type.getName(), e);
//        }
//        return wrapperSchema;
//    }
//}