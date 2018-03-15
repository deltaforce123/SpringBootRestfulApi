package assessment.entity;

import assessment.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "scheduler_runs")
public class SchedulerRun implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.MetaView.class)

    private Integer id;

    @Column(nullable = true)
    private Integer startId;

    @Column(nullable = true)
    private Integer endId;

    private Integer newUploadCount = 0;

    private LocalDateTime startedAt = LocalDateTime.now();

    private LocalDateTime created = startedAt;

    private LocalDateTime completedAt;

    private LocalDateTime updated;
}
