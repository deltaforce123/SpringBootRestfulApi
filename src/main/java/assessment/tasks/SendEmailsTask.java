package assessment.tasks;

import assessment.repos.FileRepository;
import assessment.entity.Upload;
import assessment.repos.SchedulerRunRepository;
import assessment.entity.SchedulerRun;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@Configuration
@EnableScheduling
public class SendEmailsTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailsTask.class);

    public static final int SECONDS = 1 * 1000;

    public static final int MINUTES = 60 * SECONDS;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private SchedulerRunRepository schedulerRunRepository;

    @Scheduled(fixedRate = 30 * SECONDS)
    public void findNewUploadsAndSendEmail() {

        LOGGER.info("Scheduler started at {} ", LocalDateTime.now());

        final SchedulerRun run = new SchedulerRun();
        final List<SchedulerRun> recentRuns = schedulerRunRepository.findSchedulerRuns(new PageRequest(0, 1));
        schedulerRunRepository.save(run);

        final Integer runId = run.getId();
        LOGGER.info("Scheduler Run Id {} ", runId);


        Integer lastId = 0;

        SchedulerRun lastRun = recentRuns.size() > 0 ? recentRuns.get(0) : null;

        if (lastRun != null && lastRun.getEndId() != null) {lastId = lastRun.getEndId();}

        final List<Upload> uploadsInLastHour = fileRepository.findByIdGreaterThanEqual(lastId);
        int countOfNewUploads = uploadsInLastHour.size();

        this.sendEmails(uploadsInLastHour);
        final LocalDateTime completedAt = LocalDateTime.now();

        LOGGER.info("Scheduler run completed at  {}", completedAt);

        run.setNewUploadCount(countOfNewUploads);
        run.setCompletedAt(completedAt);

        schedulerRunRepository.save(run);

        LOGGER.info(String.format("Found # of new Uploads %d", countOfNewUploads));
    }

    public void sendEmails(List<Upload> lastHourUploads) {
        LOGGER.info("Started sending email related to new uploads in the last hour ");
        final String emailId = "john@google.com";
        LOGGER.info("Sending email to  {}", emailId);
    }

}