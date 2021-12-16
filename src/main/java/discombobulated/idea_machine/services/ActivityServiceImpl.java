package discombobulated.idea_machine.services;

import discombobulated.idea_machine.entities.Activity;
import discombobulated.idea_machine.repositories.ActivityRepo;
import net.minidev.json.JSONArray;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public String getImage(Activity activity) {
        String activityName = activity.getActivity();
        activityName = activityName.replace(" ", "%20"); // changed because the links dont work with spaces
        String apiUrl = "https://bing-image-search1.p.rapidapi.com/images/search?q="+activityName+"&count=1";
        String imageUrl = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("x-rapidapi-host", "bing-image-search1.p.rapidapi.com")
                .header("x-rapidapi-key", "08784da23emsh4597fae14723273p18077bjsn1c869a79330d")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String responseBody = response.body();
        int startingIndex = responseBody.indexOf("contentUrl")+14; //starting index of needed part of the string
        int endingIndex = responseBody.indexOf("hostPageUrl")-4; // ending index
        imageUrl = responseBody.substring(startingIndex, endingIndex);
        imageUrl = imageUrl.replace("\\", "");

        return imageUrl;
    }

    @Override
    public Activity getRandomWithFilter(String type, Integer participantsMin, Integer participantsMax, Double priceMin, Double priceMax, String accessibility, String duration) {
        List<Activity> activityList = activityRepo.findAll();

        if(type != null){
            activityList.removeIf(activity -> !activity.getType().equals(type));
        }
        if(participantsMax != null) {
            activityList.removeIf(activity -> !(activity.getParticipants() <= participantsMax));
        }
        if(participantsMin != null){
            activityList.removeIf(activity -> !(activity.getParticipants() >= participantsMin));
        }
        if(priceMax != null) {
            activityList.removeIf(activity -> !(activity.getPrice() <= priceMax));
        }
        if(priceMin != null){
            activityList.removeIf(activity -> !(activity.getPrice() >= priceMin));
        }
        if(accessibility != null){
            activityList.removeIf(activity -> !activity.getAccessibility().equals(accessibility));
        }
        if(duration != null){
            activityList.removeIf(activity -> !activity.getDuration().equals(duration));
        }

        Random random = new Random();
        int maxValue = activityList.size();
        int randomIndex = random.nextInt( maxValue );
        return activityList.get(randomIndex);

    }
}
