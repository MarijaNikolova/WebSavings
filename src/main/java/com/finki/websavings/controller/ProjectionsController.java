package com.finki.websavings.controller;

import com.finki.websavings.model.Projection;
import com.finki.websavings.service.AnnualValuesApi;
import com.finki.websavings.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Implementation for the projections API.
 */
@AllArgsConstructor
@RestController
public class ProjectionsController implements AnnualValuesApi {

  private final ProjectionService projectionService;

  @Override
  public ResponseEntity<List<Projection>> getAnnualValues(Integer customerId) {
    return ResponseEntity.of(Optional.of(projectionService.getProjections(customerId)));
  }
}
