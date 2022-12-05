package com.htpt.coordinator.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htpt.coordinator.Common.Const;
import com.htpt.coordinator.Models.LogModel;
import com.htpt.coordinator.Models.ResponseObject;
import com.htpt.coordinator.Repositories.LogRepository;
import com.htpt.coordinator.service.LogResponse;
import com.htpt.coordinator.service.Ping;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api")
public class api {
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Autowired LogRepository logRepository;
    @Autowired LogResponse logResponse;

    @GetMapping(value = "/find-site/{ip}")
    public ResponseEntity<ResponseObject> checkSite(@PathVariable("ip") String ip) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 1;
        int random = (int) randomDouble%2;

        Const constant = new Const();
        String host1 = constant.getHostServer1();
        String host2 = constant.getHostServer2();
        Ping ping = new Ping();
        
        if(random == 0) {
            if(ping.ping(host1)) {
                String ipServer = host1.split("//")[1].split(":")[0];
                logRepository.save(new LogModel(ip, ipServer, "Thành công", getCurrentTimeStamp()));

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Ping thành công", host1)  
                );
            } else {

                if(ping.ping(host2)) {
                    String ipServer = host2.split("//")[1].split(":")[0];
                    logRepository.save(new LogModel(ip, ipServer, "Thành công", getCurrentTimeStamp()));

                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Ping thành công", host2)  
                    );
                }
            }
        } else {
            if(ping.ping(host2)) {
                String ipServer = host2.split("//")[1].split(":")[0];
                logRepository.save(new LogModel(ip, ipServer, "Thành công", getCurrentTimeStamp()));

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Ping thành công", host2)  
                );
            } else {
                if(ping.ping(host1)) {
                    String ipServer = host1.split("//")[1].split(":")[0];
                    logRepository.save(new LogModel(ip, ipServer, "Thành công", getCurrentTimeStamp()));

                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Ping thành công", host1)  
                    );
                }
            }
        }
        System.out.println("Không tìm được server");
        logRepository.save(new LogModel(ip, "Không tìm được server", "Thất bại", getCurrentTimeStamp()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Ping thất bại", "")  
        );
    }

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LogModel> getLog() {
        return logResponse.findAll();
    }

    public static String getCurrentTimeStamp() {
        return dtf.format(LocalDateTime.now());
    }
}
