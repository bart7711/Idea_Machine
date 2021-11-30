package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;

import java.util.List;

public interface ActivityServiceInterface {
    List<Activity> getAll();
    Activity getById(int id);
    Activity getRandomActivity();
    Activity createActivity(Activity activity);
    void deleteActivity(int id);
    Activity updateActivity(int id, Activity activity);
}
