package assessment.repos;

import assessment.entity.SchedulerRun;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SchedulerRunRepository extends PagingAndSortingRepository<SchedulerRun, Integer> {

    @Query("SELECT run FROM SchedulerRun run ORDER BY run.id DESC")
    List<SchedulerRun> findSchedulerRuns(Pageable pageRequest);
}
