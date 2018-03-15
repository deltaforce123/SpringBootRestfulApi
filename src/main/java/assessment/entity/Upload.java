package assessment.entity;

import assessment.JsonViews;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "uploads")
@Data
public class Upload implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.MetaView.class)
    private Integer id;

    @JsonView(JsonViews.CompleteView.class)
    private String uuid;

    @JsonView(JsonViews.MetaView.class)
    @JsonProperty("size")
    private Long fileSize;

    @JsonView(JsonViews.MetaView.class)
    private Integer downloads = Integer.valueOf(0);

    @JsonView(JsonViews.CompleteView.class)
    private String path;

    @JsonView(JsonViews.MetaView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime uploadedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView(JsonViews.CompleteView.class)
    private LocalDateTime lastAccessed;

    @JsonView(JsonViews.MetaView.class)
    @JsonProperty("name")
    private String originalFileName;

    @JsonView(JsonViews.MetaView.class)
    private String downloadLink;

    @JsonView(JsonViews.CompleteView.class)
    public String getUuid() {
        return uuid;
    }

}
