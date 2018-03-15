package assessment.controllers;

import assessment.JsonViews;
import assessment.entity.Upload;
import assessment.repos.FileRepository;
import assessment.services.UploadService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 30485760, maxRequestSize = 1024 * 1024 * 5 * 5)
@RequestMapping("/api")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final String COUNT = "/count";
    public static final String UPLOAD_API = "/upload";
    public static final String UPLOAD_ID_META = "/upload/{id}/meta";
    public static final String DOWNLOAD_UUID = "/download/{uuid}";

    @Value("${file.upload.folder}")
    private String uploadFolder;

    @Autowired private UploadService uploadService;

    @Autowired
    private FileRepository fileRepository;

    @Async
    @JsonView(JsonViews.MetaView.class)
    @PostMapping(value = UPLOAD_API)
    public Upload uploadFile(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile multipartFile)
            throws IOException {
        final Upload upload = uploadService.saveUpload(multipartFile);
        return upload;
    }

}
