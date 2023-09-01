package org.example.start;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job jobA;
    @Autowired
    Job sayHelloWorld;
    @Autowired
    Job evenOrOdd;
    @Autowired
    Job fixedLengthJob;
    @Autowired
    Job delimitedFileJob;
    @Autowired
    Job multipleFormatsFileJob;
    @Autowired
    Job multipleLineFileJob;
    @Autowired
    Job multipleFileJob;
    @Autowired
    Job cursorDataSourceJob;

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();

        //jobLauncher.run(jobA, jobParameters);
        //jobLauncher.run(sayHelloWorld, jobParameters);
        //jobLauncher.run(evenOrOdd, jobParameters);
        //jobLauncher.run(fixedLengthJob, jobParameters);
        //jobLauncher.run(delimitedFileJob, jobParameters);
        //jobLauncher.run(multipleFormatsFileJob, jobParameters);
        //jobLauncher.run(multipleLineFileJob, jobParameters);
        //jobLauncher.run(multipleFileJob, jobParameters);
        jobLauncher.run(cursorDataSourceJob, jobParameters);
        System.out.println("JOB Execution completed!");
    }
}
