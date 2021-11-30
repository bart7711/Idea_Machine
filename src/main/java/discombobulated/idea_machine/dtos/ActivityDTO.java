package discombobulated.idea_machine.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
