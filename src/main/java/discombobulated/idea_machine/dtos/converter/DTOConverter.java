package discombobulated.idea_machine.dtos.converter;

import discombobulated.idea_machine.dtos.ActivityDTO;
import discombobulated.idea_machine.entities.Activity;
import discombobulated.idea_machine.services.ActivityServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    ModelMapper modelMapper;
    ActivityServiceImpl activityService;

    @Autowired
    public DTOConverter(ModelMapper modelMapper, ActivityServiceImpl activityService) {
        this.modelMapper = modelMapper;
        this.activityService = activityService;
    }

    public ActivityDTO convertToActivityDTO(Activity activity){
        ActivityDTO activityDTO = modelMapper.map(activity, ActivityDTO.class);
        activityDTO.setImageUrl(activityService.getImage(activity));
        return  activityDTO;
    }
    public Activity convertToActivity(ActivityDTO activityDTO){return modelMapper.map(activityDTO, Activity.class);}

    public List<ActivityDTO> convertToListOfProjectionDTO(List<Activity> activities){
        return activities.stream()
                .map(this::convertToActivityDTO)
                .collect(Collectors.toList());
    }
}
