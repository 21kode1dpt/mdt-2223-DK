package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Todoitem;

public interface TodoItemRepository extends JpaRepository<Todoitem, String> {

}