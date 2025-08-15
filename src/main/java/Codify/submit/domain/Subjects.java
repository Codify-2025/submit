package Codify.submit.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(name = "userUuid", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userUuid;

    @Column(name = "subjectName", nullable = false, length = 255)
    private String subjectName;

    protected Subjects() { }

    public Subjects(UUID userUuid, String subjectName) {
        this.userUuid = userUuid;
        this.subjectName = subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
    public String getSubjectName() {
        return subjectName;
    }
}