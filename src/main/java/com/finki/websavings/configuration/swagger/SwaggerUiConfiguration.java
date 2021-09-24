package com.finki.websavings.configuration.swagger;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Swagger configuration.
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerUiConfiguration {

  private static final String WEB_SAVINGS_GROUP = "web-savings";
  private static final String WEB_SAVINGS_V_1_YAML = "classpath:/api/web-savings.v1.yaml";

  private final ResourcePatternResolver resourcePatternResolver;

  /**
   * International business API Group.
   * @return the business model API group.
   */
  @Bean
  public GroupedOpenApi internationalBusinessApiGroup() {
    OpenApiCustomiser openApiCustomiser = new com.finki.websavings.configuration.swagger.OpenApiCustomizerImpl(
      resourcePatternResolver,
      WEB_SAVINGS_V_1_YAML);

    return GroupedOpenApi.builder()
      .group(WEB_SAVINGS_GROUP)
      .addOpenApiCustomiser(openApiCustomiser)
      .build();
  }
}
