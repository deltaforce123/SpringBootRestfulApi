package assessment.repos;

import assessment.entity.Upload;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@ComponentScan
public interface FileRepository extends CrudRepository<Upload, Integer> {
    
    Upload findByUuid(String fileName);

    List<Upload> findByOriginalFileNameContaining(String nameLike);

    List<Upload> findByIdGreaterThanEqual(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Upload upload SET upload.downloads = upload.downloads + 1 WHERE upload.id = :uploadId")
    void incrementDownloadCount(@Param("uploadId") int uploadId);

}
