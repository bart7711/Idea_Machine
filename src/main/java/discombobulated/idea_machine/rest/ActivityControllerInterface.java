package discombobulated.idea_machine.rest;

import discombobulated.idea_machine.dtos.ActivityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ActivityControllerInterface {

    @GetMapping("/all")
    List<ActivityDTO> getAll();

    @GetMapping("/{id}")
    ActivityDTO getById(@PathVariable int id);

    @GetMapping
    ActivityDTO getRandom();

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ActivityDTO create(@RequestBody ActivityDTO activityDTO);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ActivityDTO update(@PathVariable("id") int id, @RequestBody ActivityDTO activityDTO);

    @GetMapping("/filter")
    ActivityDTO getRandomWithFilter(@RequestParam(required = false) String type,@RequestParam(required = false) Integer partMin, @RequestParam(required = false) Integer partMax,@RequestParam(required = false) String priceMin,@RequestParam(required = false) String priceMax, @RequestParam(required = false) String accessibility,@RequestParam(required = false) String duration  );

}
