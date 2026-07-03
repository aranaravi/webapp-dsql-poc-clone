package jsp.springboot.service;


import java.util.List;
import java.util.Optional;

import jsp.springboot.entity.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.dao.TutorialDao;
import jsp.springboot.dto.ServerResponse;
import jsp.springboot.exception.NoRecordFoundException;

@Service
public class TutorialService {

	@Autowired
	private TutorialDao tutorialDao;

	public ResponseEntity<ServerResponse<Tutorial>> saveTutorial(Tutorial tutorial) {
		ServerResponse<Tutorial> response = new ServerResponse<Tutorial>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Tutorial record saved");
		response.setData(tutorialDao.saveTutorial(tutorial));
		
		return new ResponseEntity<ServerResponse<Tutorial>>(response,HttpStatus.CREATED);
	}

	public ResponseEntity<ServerResponse<List<Tutorial>>> getAllTutorials() {
		ServerResponse<List<Tutorial>> response = new ServerResponse<List<Tutorial>>();
		List<Tutorial> tutorials = tutorialDao.getAllTutorials();
		if(tutorials.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Fetched all the Tutorials");
			response.setData(tutorials);
			
			return new ResponseEntity<ServerResponse<List<Tutorial>>> (response, HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No records found");
	}

	public ResponseEntity<ServerResponse<Tutorial>> getTutorialById(int id) {
		ServerResponse<Tutorial> response = new ServerResponse<Tutorial>();
		Optional<Tutorial> optional = tutorialDao.getTutorialById(id);
		
		if(optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found a Tutorial by id : "+id);
			response.setData(optional.get());
			
			return new ResponseEntity<ServerResponse<Tutorial>>(response, HttpStatus.OK);
		}
		else {
				throw new NoRecordFoundException("There is no record with the id " + id);
		}
	}

	public ResponseEntity<ServerResponse<Tutorial>> updateTutorial(Tutorial tutorial) {
		ServerResponse<Tutorial> response = new ServerResponse<Tutorial>();
		Optional<Tutorial> optional = tutorialDao.getTutorialById(tutorial.getId());
		
		if(optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Updated a Record Successfully!");
			response.setData(tutorialDao.saveTutorial(tutorial));
			
			return new ResponseEntity<ServerResponse<Tutorial>>(response,HttpStatus.OK);
		}
		else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Record updation failure!");
			
			return new ResponseEntity<ServerResponse<Tutorial>>(response,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ServerResponse<Tutorial>> deleteTutorialById(int id) {
	    ServerResponse<Tutorial> response = new ServerResponse<>();
	    Optional<Tutorial> optional = tutorialDao.getTutorialById(id);

	    if (optional.isPresent()) {
	        tutorialDao.deleteTutorialById(id);
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Deleted record with id " + id + " successfully");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordFoundException("There is no record with id " + id);
	    }
	}
}
