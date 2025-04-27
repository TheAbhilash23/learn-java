package com.example.demo.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public abstract class BaseController<T> {
    
    @Operation(summary = "Get all items", description = "Returns a list of all items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "404", description = "No items found")
    })
    public abstract ResponseEntity<?> list();

    @Operation(summary = "Get item by ID", description = "Returns a single item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved item"),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public abstract ResponseEntity<?> retrieve(String id);

    @Operation(summary = "Create new item", description = "Creates a new item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created item"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public abstract ResponseEntity<?> create(T newItem);

    @Operation(summary = "Update item", description = "Partially updates an existing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated item"),
        @ApiResponse(responseCode = "404", description = "Item not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public abstract ResponseEntity<?> partialUpdate(String id, T updateItem);

    @Operation(summary = "Delete item", description = "Deletes an existing item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted item"),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public abstract ResponseEntity<?> delete(String id);
} 