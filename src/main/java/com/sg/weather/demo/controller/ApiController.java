package com.sg.weather.demo.controller;

import com.sg.weather.demo.exception.BadRequestException;
import com.sg.weather.demo.model.apikey.ApiKeySchemeRequest;
import com.sg.weather.demo.model.apikey.ApiKeySchemeResponse;
import com.sg.weather.demo.service.ApiKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sg.weather.demo.constant.AppConstants.API_KEY_REQUIRED;
import static com.sg.weather.demo.constant.AppConstants.API_NAME_REQUIRED;

@Slf4j
@Controller
public class ApiController {
    private final ApiKeyService apiKeyService;

    public ApiController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Operation(summary = "Get all api key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @GetMapping("api/v1/apikey")
    public ResponseEntity<List<ApiKeySchemeResponse>> getAll() {
        List<ApiKeySchemeResponse> apiKeySchemeResponses = apiKeyService.getAll();
        return ResponseEntity.ok(apiKeySchemeResponses);
    }

    @Operation(summary = "Get api key by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @GetMapping("api/v1/apikey/{id}")
    public ResponseEntity<ApiKeySchemeResponse> get(@PathVariable("id") Integer id) {
        ApiKeySchemeResponse apiKeySchemeResponse = apiKeyService.get(id);
        return ResponseEntity.ok(apiKeySchemeResponse);
    }

    @Operation(summary = "Create api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @PostMapping("api/v1/apikey")
    public ResponseEntity<ApiKeySchemeResponse> create(
            @RequestBody @NonNull ApiKeySchemeRequest apiKeySchemeRequest) {
        if (StringUtils.isEmpty(apiKeySchemeRequest.getApiKey())) {
            throw new BadRequestException(API_KEY_REQUIRED);
        }
        if (StringUtils.isEmpty(apiKeySchemeRequest.getApiName())) {
            throw new BadRequestException(API_NAME_REQUIRED);
        }

        ApiKeySchemeResponse apiKeySchemeResponse = apiKeyService.create(apiKeySchemeRequest);
        return ResponseEntity.ok(apiKeySchemeResponse);
    }

    @Operation(summary = "Update api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @PutMapping("api/v1/apikey/{id}")
    public ResponseEntity<ApiKeySchemeResponse> update(
            @RequestBody @NonNull ApiKeySchemeRequest apiKeySchemeRequest
            , @PathVariable("id") Integer id) {
        ApiKeySchemeResponse apiKeySchemeResponse = apiKeyService.update(apiKeySchemeRequest, id);
        return ResponseEntity.ok(apiKeySchemeResponse);
    }

    @Operation(summary = "Delete api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok, no response")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @DeleteMapping("api/v1/apikey/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        apiKeyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}