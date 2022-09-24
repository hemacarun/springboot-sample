package com.pollmgmt.service.impl;

import com.pollmgmt.converter.VoterConverter;
import com.pollmgmt.dto.VoterDTO;
import com.pollmgmt.models.Voter;
import com.pollmgmt.repository.VoterRepository;
import com.pollmgmt.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//----- this is factory method pattern
//-----
@Service
public class VoterServiceImpl implements VoterService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoterConverter voterConverter;
    @Override
    public VoterDTO saveVoter(VoterDTO voterDTO) {

        Voter vo =voterConverter.covertDTOtoEntity(voterDTO);
          vo  =voterRepository.save(vo);
          VoterDTO vdto= voterConverter.convertEntitytoDTO(vo);
        return  vdto;
    }

    @Override
    public List<VoterDTO> getAllVoters() {
       List<Voter> listVoter= ( List<Voter>)voterRepository.findAll();
       List<VoterDTO>  vdtoList= new ArrayList<>();
       for(Voter ve:listVoter){
        VoterDTO    vdto =voterConverter.convertEntitytoDTO(ve);
        vdtoList.add(vdto);
       }
        return vdtoList;
    }

    @Override
    public VoterDTO updateVoter(VoterDTO voterDTO, Long voterId) {
       VoterDTO vdto= null;

       System.out.println(voterRepository.findById(voterId));

       Optional<Voter> opve=voterRepository.findById(voterId);
       if(opve.isPresent()){
           Voter ve= opve.get();
           ve.setName(voterDTO.getName());
           ve.setMobileno(voterDTO.getMobile());
           ve.setAge(voterDTO.getAge());
           ve.setGender(voterDTO.getGender());
           ve.setEducation(voterDTO.getEducation());
           ve.setCaste(voterDTO.getCaste());
           ve.setReligion(voterDTO.getReligion());
           ve.setProfession(voterDTO.getProfession());

              vdto=voterConverter.convertEntitytoDTO(ve);
              voterRepository.save(ve);
       }
        return vdto;
    }// end of updateVoter

    @Override
    public VoterDTO updateVoterField(VoterDTO voterDTO, Long voterId) {

        VoterDTO vdto= null;
        Optional<Voter> opve=voterRepository.findById(voterId);
        if(opve.isPresent()) {
            Voter ve= opve.get();
            ve.setName(voterDTO.getName());
            vdto=voterConverter.convertEntitytoDTO(ve);
            voterRepository.save(ve);
        }

        return vdto;
    }

    @Override
    public void deleteVoter(Long voterId) {
        voterRepository.deleteById(voterId);
    }


}
