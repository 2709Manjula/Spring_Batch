package com.example.SpringBatch.batchConfig;

import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importProductJob;
    @GetMapping("/import")
    public String importCSVtoDBJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("startAt", new Date())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(importProductJob, jobParameters);

            // Log exit status and step details
            execution.getStepExecutions().forEach(step -> {
                System.out.println("Step name: " + step.getStepName());
                System.out.println("Read count: " + step.getReadCount());
                System.out.println("Write count: " + step.getWriteCount());
                System.out.println("Exit status: " + step.getExitStatus());
                System.out.println("Failure exceptions: " + step.getFailureExceptions());
            });

            return "Batch job has been invoked: Status = " + execution.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return "Job failed: " + e.getMessage();
        }
    }

}

