package discombobulated.idea_machine.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDTO {
    private String activity;
    double availability;
    String type;
    int participants;
    double price;
    String accessibility;
    String duration;
    boolean kidFriendly;
    String link;
}
