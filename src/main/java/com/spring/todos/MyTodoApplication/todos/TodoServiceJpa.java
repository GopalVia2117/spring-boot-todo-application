package com.spring.todos.MyTodoApplication.todos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;


@Service
public class TodoServiceJpa {
	TodoRepository todoRepository;
	public TodoServiceJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	
	public List<Todo> findByUsername(String username){
		return todoRepository.findByUsername(username);
	}
	
	public boolean addTodo(String username, String description, LocalDate targetDate, boolean done) {
		todoRepository.save(new Todo(0, username, description, targetDate, done));
		return true;
	}
	
	public Todo getTodo(int id) {
		return todoRepository.findById(id).get();
	}
	
	
	
	public boolean updateTodo(Todo todoPassed) {
		Todo found = getTodo(todoPassed.getId());
		
		if(found == null) return false;
		found.setDescription(todoPassed.getDescription());
		found.setTargetDate(todoPassed.getTargetDate());
		todoRepository.save(found);
		return true;
	}
	public void deleteTodo(int id) {	
		todoRepository.deleteById(id);
	}
}
