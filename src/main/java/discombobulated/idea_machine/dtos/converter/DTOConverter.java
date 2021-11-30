package discombobulated.idea_machine.dtos.converter;

import discombobulated.idea_machine.dtos.ActivityDTO;
import discombobulated.idea_machine.entities.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    ModelMapper modelMapper;

    @Autowired
    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ActivityDTO convertToActivityDTO(Activity activity){return modelMapper.map(activity, ActivityDTO.class);}
    public Activity convertToActivity(ActivityDTO activityDTO){return modelMapper.map(activityDTO, Activity.class);}

    public List<ActivityDTO> convertToListOfProjectionDTO(List<Activity> activities){
        return activities.stream()
                .map(this::convertToActivityDTO)
                .collect(Collectors.toList());
    }
}
