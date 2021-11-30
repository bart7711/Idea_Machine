package discombobulated.idea_machine.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "activity", length = 150, nullable = false)
    private String activity;
    @Column(name = "availability", length = 4, nullable = false)
    double availability;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "participants", nullable = false)
    int participants;
    @Column(name = "price", nullable = false)
    double price;
    @Column(name = "accessibility", length = 80, nullable = false)
    String accessibility;
    @Column(name = "duration", length = 30, nullable = false)
    String duration;
    @Column(name = "kidFriendly", nullable = false)
    boolean kidFriendly;
    @Column(name = "link",length = 120)
    String link;

    public Activity(String activity, double availability, String type, int participants, double price, String accessibility, String duration, boolean kidFriendly, String link){
        this.activity = activity;
        this.availability = availability;
        this.type = type;
        this.participants = participants;
        this.price=price;
        this.accessibility = accessibility;
        this.duration=duration;
        this.kidFriendly=kidFriendly;
        this.link = link;

    }

}
