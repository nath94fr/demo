
package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication {
	static TODO TODO1 = new TODO("List my TODOs", "As a user I would like to list my current todos");
	static TODO TODO2 = new TODO("Change a TODO state", "As a user I would like to change a todo state by checking a \"box\"");
	static TODO TODO3 = new TODO("Detail a TODO", "As a user I would like to display one of my todo in a separate or dedicated view. This todo will contain its title and a description (which is a new information not shown in the previous view).");
	static TODO TODO4 = new TODO("Add a new TODO", "As a user I would like to add a new todo in my list");
	
	private static ArrayList<TODO> TODOs = new ArrayList<TODO>(){
		{
			add(TODO1);
			add(TODO2);
			add(TODO3);
			add(TODO4);
		}
	};

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/load")
	public String load() {
		//US1 -> Listing the TODOs
		//US2 -> Listing with Checkboxs and sorting TODOs by state
		String DisplayedList = "TODOs : <br><br>";

		int i = 0;
		for(TODO Todo : TODOs){

			if(Todo.getState() == 1){
				DisplayedList += "<form> <input type='checkbox' id='TODO" + i + "' name='TODO" + i + "' value='TODO" + i + "'>";
				DisplayedList += "<label for='TODO" + i + "'>" + Todo.getTitle() + "</label>";
				DisplayedList += "<br><a href='http://localhost:8080/detail?todoId=" + i + "'>More details here</a>";
				DisplayedList += "<script type='text/javascript'>";
				DisplayedList += "var checkbox" + i + " = document.querySelector('input[value=\"TODO" + i + "\"]');";
				DisplayedList += "checkbox" + i + ".onchange = function()";
				DisplayedList += "{if(checkbox" + i + "){window.location.href = 'http://localhost:8080/update?todoId=" + i + "';}}";
				DisplayedList += "</script>";
				DisplayedList += "</form>";
			}
			
			i++;
		}

		for(TODO Todo : TODOs){
			if(Todo.getState() == 2){
				DisplayedList += "<br><strike>" + Todo.getTitle() + "</strike>";
			}
		}

		DisplayedList += "<br><br>";
		DisplayedList += "<a href='http://localhost:8080/createform'>Ajouter un TODO</a>";

		return String.format(DisplayedList);
	}

	@GetMapping("/update")
	public String update(@RequestParam(value = "todoId") int todoId) {

		TODOs.get(todoId).setSateToDone();

		String returnToLoad = "<script type='text/javascript'>window.location.href = 'http://localhost:8080/load';</script>";

		return String.format(returnToLoad);
	}

	@GetMapping("/detail")
	public String detail(@RequestParam(value = "todoId") int todoId) {

		String DisplayedView = "";

		DisplayedView += "<label style='font-size:40px'>" + TODOs.get(todoId).getTitle() + "</label>";
		DisplayedView += "<br><br>";
		DisplayedView += "<label>" + TODOs.get(todoId).getDescription() + "</label>";
		DisplayedView += "<br><br>";
		DisplayedView += "<a href='http://localhost:8080/load'>Retour à la liste</a>";

		return String.format(DisplayedView);
	}

	@GetMapping("/createform")
	public String createform() {

		String createForm = "";

		createForm += "<label for='todoTitle'>Title : </label>";
		createForm += "<input type='text' id='todoTitle' name='todoTitle' required size='10'>";
		createForm += "<br> <br>";
		createForm += "<label for='todoDesc'>Description : </label>";
		createForm += "<input type='text' id='todoDesc' name='todoDesc' required size='50'>";
		createForm += "<br> <br>";
		createForm += "<input type='button' name='submit' value='Create the TODO'>";

		createForm += "<script type='text/javascript'>";
		createForm += "var button = document.querySelector('input[name=\"submit\"]');";
		createForm += "button.onclick = function() {";
		createForm += "var title = document.querySelector('input[name=\"todoTitle\"]').value;";
		createForm += "var description = document.querySelector('input[name=\"todoDesc\"]').value;";
		createForm += "window.location.href = 'http://localhost:8080/create";
		createForm += "?todoTitle=' + title";
		createForm += " + '&todoDesc=' + description";
		createForm += ";}";
		createForm += "</script>";

		return String.format(createForm);
	}

	@GetMapping("/create")
	public String create(@RequestParam(value = "todoTitle") String todoTitle, @RequestParam(value = "todoDesc", defaultValue = "None") String todoDesc) {

		TODO newTODO = new TODO(todoTitle, todoDesc);
		TODOs.add(0, newTODO);

		String createForm = "<script type='text/javascript'>window.location.href = 'http://localhost:8080/load';</script>";

		return String.format(createForm);
	}

}
            
