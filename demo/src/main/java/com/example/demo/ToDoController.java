package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/old/todos")
public class ToDoController {

    private ArrayList<ToDo> items = new ArrayList<ToDo>();

    @Operation(summary = "Creates a Todo Item with path variable name and default priority of 2")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item has been created", content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public ToDo createAndAddTodoItem(@PathVariable String name) {

        ToDo item = new ToDo(name);
        items.add(item);

        return item;
    }

    @Operation(summary = "Creates a Todo Item with a JSON object as request paramter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item has been created", content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ToDo addTodoItem(@RequestBody ToDo item) {

        items.add(item);
        return item;
    }

    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all items", content = @Content)
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ToDo> getTodoItems() {

        return items;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces = "application/json", path = "/{itemId}")
    ToDo deleteTodoItem(@PathVariable String itemId) {

        ToDo tempItem = new ToDo(itemId);
        Iterator<ToDo> iterator = items.iterator();

        while (iterator.hasNext()) {

            if (iterator.next().equals(tempItem))
                iterator.remove();

        }

        return null;
    }

}