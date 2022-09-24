package com.pollmgmt.controllers;

import com.pollmgmt.dto.VoterDTO;
import com.pollmgmt.repository.VoterRepository;
import com.pollmgmt.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VoterController {

    @Autowired
    private VoterService voterService;


    @PostMapping("/voter")
    public  ResponseEntity<VoterDTO> saveVotersData(@RequestBody VoterDTO voterDTO){
        voterDTO= voterService.saveVoter(voterDTO);
        ResponseEntity<VoterDTO> responseEntity= new ResponseEntity<>(voterDTO, HttpStatus.CREATED);
        System.out.println(voterDTO);
        return responseEntity;

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            Exception exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @GetMapping("/voters")
    public  ResponseEntity<List<VoterDTO>> getAllVoters(){
        List<VoterDTO> voterList = voterService.getAllVoters();
        ResponseEntity<List<VoterDTO>> responseEntity= new ResponseEntity<>(voterList, HttpStatus.OK);
        return responseEntity;

    } // end of getAllvoters


    @PutMapping("/voter/{voterId}")
    public ResponseEntity<VoterDTO> updateVoter(@RequestBody VoterDTO voterDTO, @PathVariable Long voterId){

      VoterDTO  vdto = voterService.updateVoter(voterDTO, voterId);
      ResponseEntity<VoterDTO> responseEntity= new ResponseEntity<>(vdto, HttpStatus.OK);
       return responseEntity;
    }

    @PatchMapping("/voter/update-name/{voterId}")
    public ResponseEntity<VoterDTO> updateVoterField(@RequestBody VoterDTO voterDTO, @PathVariable Long voterId){
        VoterDTO  vdto =voterService.updateVoterField(voterDTO,voterId);
        ResponseEntity<VoterDTO> responseEntity= new ResponseEntity<>(vdto, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/voter/delete/{voterId}")
    public ResponseEntity deleteVoter(@PathVariable Long voterId){
        voterService.deleteVoter(voterId);
        ResponseEntity<Void> responseEntity= new ResponseEntity<>(null, HttpStatus.OK);
        return responseEntity;
    }





}
