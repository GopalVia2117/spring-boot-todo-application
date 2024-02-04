package com.spring.todos.MyTodoApplication.todos;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("username")
public class TodoController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@RequestMapping(value="list-todos", method = RequestMethod.GET)
	public String listAllTodos(ModelMap model) {
		String username = (String)model.get("username");
		List<Todo> todos = todoService.findByUsername(username);
		model.put("todos", todos);
		return "listTodos";
	}
	
	
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String addTodo(ModelMap model) {
		model.put("todo", new Todo(0, "", "", LocalDate.now(), false));
		return "addTodo";
	}
	
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username = (String) model.get("username");
		boolean isAdded = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		if(!isAdded) {
			model.put("error", "Unexpected error occurred");
			return "addTodo";
		}
		return "redirect:list-todos";	
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		boolean isDeleted = todoService.deleteTodo(id);
		if(!isDeleted) {
			model.put("error", "Todo not found");
		}
		return "redirect:list-todos";	
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo found = todoService.getTodo(id);
		logger.info(found.toString());
		model.put("todo", found);
		return "updateTodo";	
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "updateTodo";
		}
		String username = (String)model.get("username");
		todo.setUsername(username);
		boolean updated = todoService.updateTodo(todo);
		
		if(updated) {
			return "redirect:list-todos";
		}
		
		return "updateTodo";
	}
	
	
	
}
