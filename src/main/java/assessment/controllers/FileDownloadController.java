package assessment.controllers;

import assessment.entity.Upload;
import assessment.repos.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;

import static assessment.Constants.*;
import static assessment.controllers.FileUploadController.DOWNLOAD_UUID;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RestController
@EnableAutoConfiguration
@MultipartConfig(location = TMP_LOCATION, fileSizeThreshold = FILE_SIZE_THRESHOLD,
        maxFileSize = MAX_FILE_SIZE, maxRequestSize = MAX_REQUEST_SIZE)
@RequestMapping("/api")
public class FileDownloadController {

    @Autowired
    private FileRepository fileRepository;

    //API : File Download
    @Async
    @ResponseBody
    @GetMapping(value = DOWNLOAD_UUID, produces = APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource getFile(@PathVariable("uuid") String uuid) {
        final Upload upload = fileRepository.findByUuid(uuid);
        fileRepository.incrementDownloadCount(upload.getId());
        final FileSystemResource fileSystemResource = new FileSystemResource(upload.getPath());
        return fileSystemResource;
    }

}
