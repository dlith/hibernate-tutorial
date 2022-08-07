package com.dzmitry.hb_05_many_to_many.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "comment")
    private String comment;

    public Review(String comment) {
        this.comment = comment;
    }
}
