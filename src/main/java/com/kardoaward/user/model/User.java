package com.kardoaward.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.validation.constraints.Email;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    private String password;
    private String nickname;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    private LocalDate birthday;
    private String country;
    private String region;
    private String city;
    private String phone;
    @Column(name = "photo_link")
    private String photoLink;
    @Column(name = "background_link")
    private String backgroundLink;
    @Column(name = "page_link")
    private String pageLink;
    @Column(name = "about_me")
    private String aboutMe;
    @Enumerated(EnumType.STRING)
    private Style style;  //Enum: [WORKOUT, HIPHOP, BREAKING, BMX, GRAFFITY, DJING и др.]
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;  //Enum: [ADMIN, USER]
    @Enumerated(EnumType.STRING)
    private State state;  //Enum: [ACTIVE, DELETED]

}
