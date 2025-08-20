package Codify.submit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "Subjects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectID")
    private Long subjectId;

    @Column(name = "userUuid", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userUuid;

    @Column(name = "subjectName", nullable = false, length = 255)
    private String subjectName;

    @Builder
    public Subjects(UUID userUuid, String subjectName) {
        this.userUuid = userUuid;
        this.subjectName = subjectName;
    }
}