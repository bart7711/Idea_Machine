package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;
import discombobulated.idea_machine.repositories.ActivityRepo;
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
    public Activity getRandomActivity() {
        Random random = new Random();
        List<Activity> activityList = activityRepo.findAll();
        int randomIndex = random.nextInt(activityList.size()+1);
        return activityList.get(randomIndex);

    }
}
