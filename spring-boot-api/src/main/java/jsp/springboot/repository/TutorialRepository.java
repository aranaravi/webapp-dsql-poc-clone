package jsp.springboot.repository;

import jsp.springboot.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer>{

}
