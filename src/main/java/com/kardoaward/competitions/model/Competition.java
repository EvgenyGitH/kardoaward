package com.kardoaward.competitions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String competitionType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ElementCollection
    @CollectionTable(name = "competition_locations", joinColumns = @JoinColumn(name = "competition_id"))
    @Column(name = "location")
    private Set<String> locations = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "competition_directions",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id")
    )
    private Set<Direction> directions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "competition_participation_types",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "participation_type_id")
    )
    private Set<ParticipationType> participationTypes = new HashSet<>();

}
