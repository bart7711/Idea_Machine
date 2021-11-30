package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;

public interface ActivityServiceInterface {
    Activity getRandomActivity();
    Activity createActivity(Activity activity);
    void deleteActivity(int id);
    Activity updateActivity(int id, Activity activity);
}
