package discombobulated.idea_machine.rest;

import discombobulated.idea_machine.dtos.ActivityDTO;
import discombobulated.idea_machine.dtos.converter.DTOConverter;
import discombobulated.idea_machine.entities.Activity;
import discombobulated.idea_machine.services.ActivityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin
    public List<ActivityDTO> getAll() {
        return dtoConverter.convertToListOfProjectionDTO(activityService.getAll());
    }

    @Override
    @CrossOrigin
    public ActivityDTO getById(int id) {
        return dtoConverter.convertToActivityDTO(activityService.getById(id));
    }

    @Override
    @CrossOrigin
    public ActivityDTO getRandom() {
        Activity activity = activityService.getRandomActivity();
        return dtoConverter.convertToActivityDTO(activityService.getRandomActivity());
    }

    @Override
    @CrossOrigin
    public ActivityDTO create(ActivityDTO activityDTO) {
        return dtoConverter.convertToActivityDTO(activityService.createActivity(dtoConverter.convertToActivity(activityDTO)));
    }

    @Override
    @CrossOrigin
    public void deleteById(int id) {
        activityService.deleteActivity(id);
    }

    @Override
    @CrossOrigin
    public ActivityDTO update(int id, ActivityDTO activityDTO) {
        return dtoConverter.convertToActivityDTO(activityService.updateActivity(id, dtoConverter.convertToActivity(activityDTO)));
    }

    @Override
    @CrossOrigin
    public ActivityDTO getRandomWithFilter(String type, Integer partMin, Integer partMax, String priceMin, String priceMax, String accessibility, String duration) {
        Double priceMinD = null;
        Double priceMaxD = null;
        if(priceMin != null) {
            priceMinD = Double.parseDouble(priceMin);
        }
        if(priceMax != null) {
            priceMaxD = Double.parseDouble(priceMax);
        }

        return dtoConverter.convertToActivityDTO(activityService.getRandomWithFilter(type,partMin,partMax,priceMinD,priceMaxD,accessibility,duration));
    }
}
