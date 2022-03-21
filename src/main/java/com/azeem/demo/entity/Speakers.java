package com.azeem.demo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "speakers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Speakers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speaker_id")
    private int id;

    @Column(name = "speaker_name")
    private String speakerName;

    @Column(name = "speaker_designation")
    private String speakerDesignation;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "event_speaker",
            joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Events> eventsList;

}
