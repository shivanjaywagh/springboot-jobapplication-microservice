package com.embarkx.firstjobapp.job;

import com.embarkx.firstjobapp.company.Company;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){

        return new ResponseEntity<List<Job>>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        Company c = job.getCompany();
        return "Job added sucessfully";
    }

    @GetMapping("/yolo/{num}")
    public ResponseEntity<String> temp(@PathVariable String num){
        return new ResponseEntity<String>("yolo " + num, HttpStatus.OK);
    }


    @GetMapping("/jobs/{num}")
    public ResponseEntity<Job> getJobById(@PathVariable Long num){
        List<Job> joblist = jobService.findAll();
        for(int i=0; i< joblist.size() ;i++){
            if (joblist.get(i).getId() == num){
                return new ResponseEntity<>(joblist.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){

        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated)
            return new ResponseEntity<>("job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

