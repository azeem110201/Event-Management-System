package com.azeem.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id;

    @Column(name = "event_name")
    @NotNull
    private String eventName;

    @Column(name = "event_venue")
    private String eventVenue;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> usersList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "event_speaker",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speakers> speakersList;

    public void addUser(Users tempUser){
        if(this.usersList == null){
            this.usersList = new ArrayList<>();
        }

        this.usersList.add(tempUser);
    }

    public void addSpeaker(Speakers speaker){
        if(this.speakersList == null){
            this.speakersList = new ArrayList<>();
        }

        this.speakersList.add(speaker);
    }
}
