package com.promineotech.jeep.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.Constants;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


// Used by Swagger title = name, servers = server imports
@Validated
@RequestMapping("/jeeps") //any uri with "/jeeps" will be mapped to this class
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"),



    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})

public interface JeepSalesController {

  //OPEN API DOCUMENTATION
  //@formatter:off
  
  @Operation(
      summary = "Returns a list of Jeeps",
      description = "Returns a list of Jeeps given an optional model and/or trim",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Jeeps is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Jeep.class))), //OK status
          
          @ApiResponse(
               responseCode = "400",
               description = "The request parameters are invalid", 
               content = @Content(mediaType = "application/json")),
               //bad input/illegal request
  
  
          @ApiResponse(
              responseCode = "404", 
              description = "No Jeeps were found with the input criteria",
              content = @Content(mediaType = "application/json")),
              //not found
          
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
              //unplanned exception
      },
      parameters = {
          @Parameter(
              name = "model",
              allowEmptyValue = false,
              required = false,
              description = "The model name (i.e., 'WRANGLER')"),
          @Parameter(
              name = "trim",
              allowEmptyValue = false,
              required = false, description = "The trim (i.e., 'Sport')")
      }
  )
   
  @GetMapping
  //returns a list of jeeps
  @ResponseStatus(code = HttpStatus.OK)
  //Returns 'OK' if successful.
  List<Jeep> fetchJeeps(
      @RequestParam(required = false)
          JeepModel model,
          @Length(max = Constants.TRIM_MAX_LENGTH)
      //Generally want to validate length first.
      //This is preventive against DDOS attacks, to avoid having to validate excessively large 
      //strings.
          @Pattern(regexp = "[\\w\\s]*")
      //limits to a word character and spaces, no !@$% for example.
      @RequestParam(required = false) 
          String trim);
  //@formatter:on
}
