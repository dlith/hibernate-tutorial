package com.dzmitry.hb_01_one_to_one_uni.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
@Getter
@Setter
@ToString
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Column(name = "hobby")
    private String hobby;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public InstructorDetail() {

    }
}
