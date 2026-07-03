package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;
import jsp.springboot.entity.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.springboot.repository.TutorialRepository;

@Repository
public class TutorialDao {

	@Autowired
	private TutorialRepository tutorialRepository;

	public Tutorial saveTutorial(Tutorial tutorial) {
		return tutorialRepository.save(tutorial);
	}

	public List<Tutorial> getAllTutorials() {
		return tutorialRepository.findAll();
	}

	public Optional<Tutorial> getTutorialById(int id) {
		return tutorialRepository.findById(id);
	}

	public void deleteTutorialById(int id) {
		 tutorialRepository.deleteById(id);
	}

}
