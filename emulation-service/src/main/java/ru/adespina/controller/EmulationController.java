package ru.adespina.controller;

import jdk.jfr.Description;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.adespina.emulation.EmulationServiceImpl;

@RestController
@RequestMapping("/emulation")
public class EmulationController {

	private EmulationServiceImpl emulationService;

	public EmulationController(EmulationServiceImpl emulationService) {
		this.emulationService = emulationService;
	}

	@PostMapping("/create")
	@Description("Создание заявки")
	@ResponseBody
	public String clientRequest() {
		return "Greetings from Spring Boot!";
	}

}