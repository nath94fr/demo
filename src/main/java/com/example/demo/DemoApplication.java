
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
	static TODO TODO1 = new TODO("List my TODOs");
	static TODO TODO2 = new TODO("Change a TODO state");
	static TODO TODO3 = new TODO("Detail a TODO");
	static TODO TODO4 = new TODO("Add a new TODO");
	
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
				DisplayedList += "<label for='TODO" + i + "' text-decoration='line-through'>" + Todo.getTitle() + "</label>";
				DisplayedList += "<script type='text/javascript'>";
				DisplayedList += "var checkbox" + i + " = document.querySelector('input[value=\"TODO" + i + "\"]');";
				DisplayedList += "checkbox" + i + ".onchange = function()";
				DisplayedList += "{if(checkbox" + i + "){window.location.href = 'http://localhost:8080/update?todoId=" + i + "';}}";
				DisplayedList += "</script>";
				DisplayedList += "</form>";
				i++;
			}
			
		}

		for(TODO Todo : TODOs){
			if(Todo.getState() == 2){
				DisplayedList += "<br><strike>" + Todo.getTitle() + "</strike>";
			}
		}

		return String.format(DisplayedList);
	}

	@GetMapping("/update")
	public String update(@RequestParam(value = "todoId") int todoId) {

		TODOs.get(todoId).setSateToDone();

		String returnToLoad = "<script type='text/javascript'>window.location.href = 'http://localhost:8080/load';</script>";

		return String.format(returnToLoad);
	}

}
            
