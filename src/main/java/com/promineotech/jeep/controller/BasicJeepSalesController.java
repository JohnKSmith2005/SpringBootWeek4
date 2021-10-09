package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;


@RestController
// Tells Spring this is a controller class
@Slf4j
// must be in the implementing class, doesn't work in the interface.
// tells spring boot that this class is a REST
public class BasicJeepSalesController implements JeepSalesController {

@Autowired
private JeepSalesService jeepSalesService;

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
  //logging at "info" level
    //log.info("model={}, trim={}", model, trim);
    //logging at "debugger" level
    log.debug("model={}, trim={}", model, trim);
    //debug will not show a log line by default.
    
    //logging with Lombok
    // if info is enabled. Will cause logger to pass in model and trim.
    // example http://localhost:8080/jeeps?model=WRANGLER&trim=sport passes in WRANGLER as the model
    // sport as the trim.
    return jeepSalesService.fetchJeeps(model,trim);
  }
  // access SWAGGER
  // http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/basic-jeep-sales-controller/fetchJeeps
}
