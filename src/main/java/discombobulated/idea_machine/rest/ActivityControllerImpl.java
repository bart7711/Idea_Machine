package discombobulated.idea_machine.rest;

import discombobulated.idea_machine.dtos.ActivityDTO;
import discombobulated.idea_machine.dtos.converter.DTOConverter;
import discombobulated.idea_machine.services.ActivityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityControllerImpl implements ActivityControllerInterface {

    DTOConverter dtoConverter;
    ActivityServiceInterface activityService;

    @Autowired
    public ActivityControllerImpl( DTOConverter dtoConverter, ActivityServiceInterface activityService){
        this.dtoConverter=dtoConverter;
        this.activityService = activityService;
    }

    @Override
    public List<ActivityDTO> getAll() {
        return dtoConverter.convertToListOfProjectionDTO(activityService.getAll());
    }

    @Override
    public ActivityDTO getById(int id) {
        return dtoConverter.convertToActivityDTO(activityService.getById(id));
    }

    @Override
    public ActivityDTO getRandom() {
        return dtoConverter.convertToActivityDTO(activityService.getRandomActivity());
    }

    @Override
    public ActivityDTO create(ActivityDTO activityDTO) {
        return dtoConverter.convertToActivityDTO(activityService.createActivity(dtoConverter.convertToActivity(activityDTO)));
    }

    @Override
    public void deleteById(int id) {
        activityService.deleteActivity(id);
    }

    @Override
    public ActivityDTO update(int id, ActivityDTO activityDTO) {
        return dtoConverter.convertToActivityDTO(activityService.updateActivity(id, dtoConverter.convertToActivity(activityDTO)));
    }
}