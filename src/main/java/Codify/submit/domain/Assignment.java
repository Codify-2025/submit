package Codify.submit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Entity
@Table(name = "Assignment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignmentId")
    private Long assignmentId;

    @Column(name = "userUuid", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userUuid;

    @Column(name = "subjectID", nullable = false)
    private Long subjectId;

    @Column(name = "assignmentName", nullable = false, length = 255)
    private String assignmentName;

    @Column(name = "startDate", nullable = false)
    private Timestamp startDate;

    @Column(name = "endDate", nullable = false)
    private Timestamp endDate;

    @Column(name = "week", nullable = false)
    private Long week;

    // 과제 생성 시 필수 필드만 주입
    @Builder
    public Assignment(UUID userUuid,
                      Long subjectId,
                      String assignmentName,
                      Timestamp startDate,
                      Timestamp endDate,
                      Long week) {
        this.userUuid = userUuid;
        this.subjectId = subjectId;
        this.assignmentName = assignmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.week = week;
    }
}