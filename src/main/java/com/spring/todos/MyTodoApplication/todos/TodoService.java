package com.spring.todos.MyTodoApplication.todos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;


//@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 0;
	
	static {
		todos.add(new Todo(++todoCount, "in28minutes", "Learn AWS", LocalDate.now().plusMonths(6), false));
		todos.add(new Todo(++todoCount, "in28minutes", "Learn Devops", LocalDate.now().plusMonths(6), false));
		todos.add(new Todo(++todoCount, "in28minutes", "Learn FullStack", LocalDate.now().plusMonths(6), false));
		
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public boolean addTodo(String username, String description, LocalDate targetDate, boolean done) {
		return todos.add(new Todo(++todoCount, username, description, targetDate, done));
	}
	
	public Todo getTodo(int id) {
		return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
	}
	
	
	
	public boolean updateTodo(Todo todoPassed) {
		Todo found = todos.stream().filter(todo -> todo.getId() == todoPassed.getId()).findFirst().get();
		
		if(found == null) return false;
		found.setDescription(todoPassed.getDescription());
		found.setTargetDate(todoPassed.getTargetDate());
		return true;
	}
	public boolean deleteTodo(int id) {
//		Todo toRemove = null;
//		for(Todo todo: todos) {
//			if(todo.getId() == id) {
//				toRemove = todo;
//				break;
//			}
//		}
//		
//		if(toRemove != null) {
//			todos.remove(toRemove);
//			return true;
//		}
		
		return todos.removeIf(todo -> todo.getId() == id);
	}
}
