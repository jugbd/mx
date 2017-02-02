package com.bazlur.meetup.extended.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Entity
@Data
public class Submission implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private User speaker;

    @Enumerated
    private Track track;

    @Column
    private String title;

    @Column
    private SubmissionStatus status;

    @Column
    @Lob
    private String summary;

    public Submission() {
        this.status = SubmissionStatus.DRAFT;
    }
}
