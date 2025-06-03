package com.example.leave_service.Services;

import com.example.leave_service.DTO.LeavesDTO;
import com.example.leave_service.DTO.UserDTO;
import com.example.leave_service.Entities.LeavesEntity;
import com.example.leave_service.Repositories.LeavesRepo;
import com.example.leave_service.openFeign.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LeavesServices {
    @Autowired
    UserClient userClient;
    @Autowired
    LeavesRepo leavesRepo;
    //type nomvariable
    //String x

    public LeavesEntity create (LeavesEntity leaves) {
        return leavesRepo.save(leaves);
    }
    public LeavesEntity update(LeavesEntity L){

        return leavesRepo.save(L);
    }
    public LeavesEntity details(Long id){
        return leavesRepo.findById(id).orElse(null);
    }
    public List<LeavesEntity> All(){
        return leavesRepo.findAll();
    }
    public void Delete (Long id){
        leavesRepo.deleteById(id);
    }
    public List<LeavesEntity> Allbyiduser(Long id){
        return leavesRepo.findByUserid(id);
    }

    public List<LeavesDTO> getAllLeavesByStatus(String status, String sortDirection) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Authorization token is missing!");
        }


        //sort by depositdate
        Sort sort = Sort.by("depositDate");
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }


        List<LeavesEntity> leaveEntities = leavesRepo.findByStatus(status, sort);
        //List<LeaveEntity> leaveEntities = leaveRepository.findByStatus(status);
        if (leaveEntities.isEmpty()) {
            throw new NoSuchElementException("No " + status + " leaves found in the database");
        }


        return leaveEntities.stream().map(leaveEntity -> {


            LeavesDTO leaveDTO = new LeavesDTO().ToDTO(leaveEntity);
            UserDTO userDTO = userClient.showdetails(leaveEntity.getIduser());
            leaveDTO.setUserDTO(userDTO);
            leaveDTO.setIduser(userDTO.getId());
            return leaveDTO;
        }).collect(Collectors.toList());
    }






}
