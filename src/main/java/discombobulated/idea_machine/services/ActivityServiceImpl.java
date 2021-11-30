package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;
import discombobulated.idea_machine.repositories.ActivityRepo;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ActivityServiceImpl implements ActivityServiceInterface {

    private final ActivityRepo activityRepo;

    public ActivityServiceImpl(ActivityRepo activityRepo){
        this.activityRepo= activityRepo;
    }

    @Override
    public List<Activity> getAll() {return activityRepo.findAll();}

    @Override
    public Activity getById(int id) {
        return activityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Activity with id = "+id+ " doesn't exist"));
    }

    @Override
    public Activity getRandomActivity() {
        Random random = new Random();
        List<Activity> activityList = activityRepo.findAll();
        int randomIndex = random.nextInt(activityList.size()+1);
        return activityList.get(randomIndex);

    }

    @Override
    public Activity createActivity(Activity activity) {
        if (activityRepo.existsByActivity(activity.getActivity())) {
            throw new IllegalArgumentException("A category with description: " + activity.getActivity() + " already exists");
        }
        return activityRepo.save(activity);
    }

    @Override
    public void deleteActivity(int id) {
        if(!activityRepo.existsById(id)){ throw new ResourceNotFoundException("There is no activity with id = "+id);}
        activityRepo.deleteById(id);
    }

    @Override
    public Activity updateActivity(int id, Activity activity) {
        Activity updatedActivity = activityRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("There is no activity with id = "+id));
        if(activity.getActivity()!=null){
            updatedActivity.setActivity(activity.getActivity());
        }
        if(activity.getAvailability()!=0){
            updatedActivity.setAvailability(activity.getAvailability());
        }
        if(activity.getType()!=null){
            updatedActivity.setType(activity.getType());
        }
        if(activity.getParticipants()!=0){
            updatedActivity.setParticipants(activity.getParticipants());
        }
        if(activity.getPrice()!=0){
            updatedActivity.setPrice(activity.getPrice());
        }
        if(activity.getAccessibility()!=null){
            updatedActivity.setAccessibility(activity.getAccessibility());
        }
        if(activity.getDuration()!=null){
            updatedActivity.setDuration(activity.getDuration());
        }
        if(activity.isKidFriendly()^updatedActivity.isKidFriendly()){
            updatedActivity.setKidFriendly(activity.isKidFriendly());
        }
        if(!updatedActivity.getLink().equals(activity.getLink())){
            updatedActivity.setLink(activity.getLink());
        }
        return activityRepo.save(updatedActivity);
    }
}
