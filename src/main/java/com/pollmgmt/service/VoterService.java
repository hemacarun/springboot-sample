package com.pollmgmt.service;

import com.pollmgmt.dto.VoterDTO;
import com.pollmgmt.models.Voter;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VoterService {
    public VoterDTO saveVoter(VoterDTO voterDTO);
     List<VoterDTO> getAllVoters();

    VoterDTO updateVoter(VoterDTO voterDTO, Long voterId);
    VoterDTO updateVoterField(@RequestBody VoterDTO voterDTO, Long voterId);

    void deleteVoter(Long voterId);
}
