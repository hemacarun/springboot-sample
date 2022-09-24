package com.pollmgmt.converter;

import com.pollmgmt.dto.VoterDTO;
import com.pollmgmt.models.Voter;
import org.springframework.stereotype.Component;

@Component
public class VoterConverter {

    public Voter covertDTOtoEntity(VoterDTO voterDTO){
        Voter vo= new Voter();
        vo.setName(voterDTO.getName());
        vo.setMobileno(voterDTO.getMobile());
        vo.setAge(voterDTO.getAge());
        vo.setGender(voterDTO.getGender());
        vo.setEducation(voterDTO.getEducation());
        vo.setCaste(voterDTO.getCaste());
        vo.setReligion(voterDTO.getReligion());
        vo.setProfession(voterDTO.getProfession());
        return vo;
    } // end of convert function

    public VoterDTO convertEntitytoDTO(Voter voter){
       VoterDTO voterDTO = new VoterDTO();
       voterDTO.setId(voter.getId());
       voterDTO.setName(voter.getName());
       voterDTO.setMobile(voter.getMobileno());
       voterDTO.setGender(voter.getGender());
       voterDTO.setEducation(voter.getEducation());
       voterDTO.setAge(voter.getAge());
       voterDTO.setReligion(voter.getReligion());
       voterDTO.setCaste(voter.getCaste());
       voterDTO.setProfession(voter.getProfession());


       return voterDTO;

    } // end of  convertEntitytoDTO

}
