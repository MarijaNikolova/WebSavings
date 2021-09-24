package com.finki.websavings.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of {@link OpenApiCustomiser}.
 */
@RequiredArgsConstructor
public class OpenApiCustomizerImpl implements OpenApiCustomiser {

  private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiCustomizerImpl.class);

  private final ResourcePatternResolver resourcePatternResolver;
  private final String resourceLocation;

  @Override
  public void customise(OpenAPI openApi) {

    Resource resource = resourcePatternResolver.getResource(resourceLocation);
    OpenAPI resourceOpenApi = null;
    InputStream apiFileInputStream = null;
    try {
      apiFileInputStream = resource.getInputStream();
      SwaggerParseResult swaggerParseResult = new OpenAPIV3Parser()
        .readContents(IOUtils.toString(apiFileInputStream, StandardCharsets.UTF_8));
      resourceOpenApi = swaggerParseResult.getOpenAPI();
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    } finally {
      IOUtils.closeQuietly(apiFileInputStream);
    }

    if (!ObjectUtils.isEmpty(resourceOpenApi)) {
      openApi.setComponents(resourceOpenApi.getComponents());
      openApi.setExtensions(resourceOpenApi.getExtensions());
      openApi.setOpenapi(resourceOpenApi.getOpenapi());
      openApi.setExternalDocs(resourceOpenApi.getExternalDocs());
      openApi.setPaths(resourceOpenApi.getPaths());
      openApi.setInfo(resourceOpenApi.getInfo());
      openApi.setSecurity(resourceOpenApi.getSecurity());
      openApi.setTags(resourceOpenApi.getTags());
    }
  }
}
