package jsp.springboot.controller;

import java.util.List;

import jsp.springboot.entity.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.springboot.dto.ServerResponse;
import jsp.springboot.service.TutorialService;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
	
	
	@Autowired
	private TutorialService tutorialService;

	@PostMapping
	public ResponseEntity<ServerResponse<Tutorial>> saveTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.saveTutorial(tutorial);
	}

	@GetMapping
	public ResponseEntity<ServerResponse<List<Tutorial>>> getAllTutorials() {
		return tutorialService.getAllTutorials();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ServerResponse<Tutorial>> fetchTutorialById(@PathVariable int id) {
		return tutorialService.getTutorialById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ServerResponse<Tutorial>> updateTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.updateTutorial(tutorial);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ServerResponse<Tutorial>> deleteTutorialById(@PathVariable int id) {
	    return tutorialService.deleteTutorialById(id);
	}
}

