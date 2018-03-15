package assessment.controllers;

import assessment.Constants;
import assessment.JsonViews;
import assessment.entity.Upload;
import assessment.repos.FileRepository;
import assessment.services.UploadService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;

import java.util.List;

import static assessment.Constants.JsonKeys.COUNT;
import static assessment.controllers.FileUploadController.UPLOAD_ID_META;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@EnableAutoConfiguration
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 30485760, maxRequestSize = 1024 * 1024 * 5 * 5)
@RequestMapping("/api")
public class FileController {

    @Autowired private UploadService uploadService;

    @Autowired
    private FileRepository fileRepository;


    @GetMapping(value = COUNT, produces = APPLICATION_JSON_VALUE)
    public Object getFileCount() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(COUNT, fileRepository.count());
        return objectNode;
    }


    // API : Get Metadata
    @GetMapping(UPLOAD_ID_META)
    @JsonView(JsonViews.MetaView.class)
    public Upload getMetaData(@PathVariable("id") Integer id) {
        final Upload one = fileRepository.findOne(id);
        if (one == null) {
            return null;
        } else return one;
    }


    //  API : Search Files
    @ResponseBody
    @JsonView(JsonViews.MetaView.class)
    @GetMapping(value = "upload/search", produces = APPLICATION_JSON_VALUE)
    public List<Upload> searchFile(@RequestParam("name") String name) {
        return uploadService.find(name);
    }

}
