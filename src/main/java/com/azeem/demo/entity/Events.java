package com.azeem.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_venue")
    private String eventVenue;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> usersList;

//    public Events(String eventName, String eventVenue, List<Users> usersList) {
//        this.eventName = eventName;
//        this.eventVenue = eventVenue;
//        this.usersList = usersList;
//    }

    public void addUser(Users tempUser){
        if(this.usersList == null){
            this.usersList = new ArrayList<>();
        }

        this.usersList.add(tempUser);
    }
}
