
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
	
	private static final ArrayList<TODO> TODOs = new ArrayList<TODO>();

	public static void main(String[] args) {
		//US1 -> Hard coded TODOs
		//Création of a list of TODOs for demo based on demo user stories
		TODO TODO1 = new TODO("List my TODOs");
		TODOs.add(TODO1);
		TODO TODO2 = new TODO("Change a TODO state");
		TODOs.add(TODO2);
		TODO TODO3 = new TODO("Detail a TODO");
		TODOs.add(TODO3);
		TODO TODO4 = new TODO("Add a new TODO");
		TODOs.add(TODO4);

		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/start")
	public String start() {
		//US1 -> Listing the TODOs
		String DisplayedList = "";

		for(TODO Todo : TODOs){
			DisplayedList += Todo.getTitle() + "<br>";
		}

		return String.format(DisplayedList);
	}

}
            
