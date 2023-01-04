package com.example.demo.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.demo.jpa.Todoitem;

@RestController
@RequestMapping("/todos/")
public class JpaController {

    @Autowired
    TodoItemRepository todoItemRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item has been created", content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public Todoitem createAndAddTodoItem(@PathVariable String name) {

        Todoitem item = new Todoitem(name);
        todoItemRepository.save(item);

        return item;
    }

    // TODO implement other CRUD methods and make API compliant to v1

    @GetMapping("/count")
    public long getAmountOfItems() {

        // todoItemRepository.
        return todoItemRepository.count();

    }

    @GetMapping("/id/{id}")
    public Optional<Todoitem> findById(@PathVariable String id) {

        return todoItemRepository.findById(id);

    }

    // List all elements in ArrayList
    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all items", content = @Content)
    })
    @ResponseStatus(HttpStatus.OK)
    public List<Todoitem> getTodoItems() {

        return todoItemRepository.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces = "application/json", path = "/{itemId}")
    Todoitem deleteTodoItem(@PathVariable String itemId) {

        todoItemRepository.deleteById(itemId);

        return null;
    }

}