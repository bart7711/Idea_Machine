package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;

import java.io.IOException;
import java.util.List;

public interface ActivityServiceInterface {
    List<Activity> getAll();
    Activity getById(int id);
    Activity getRandomActivity();
    Activity createActivity(Activity activity);
    void deleteActivity(int id);
    Activity updateActivity(int id, Activity activity);
    String getImage(Activity activity);
    Activity getRandomWithFilter(String type, Integer participantsMin, Integer participantsMax, Double priceMin, Double priceMax, String accessibility, String duration);
}
