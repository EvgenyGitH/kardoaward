//package com.kardoaward.competitions.service.impl;
//
//import com.kardoaward.competitions.model.ParticipationType;
//import com.kardoaward.competitions.repository.ParticipationTypeRepository;
//import com.kardoaward.competitions.service.ParticipationTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//public class ParticipationTypeServiceImpl implements ParticipationTypeService {
//    @Autowired
//    private ParticipationTypeRepository participationTypeRepository;
//
//    public List<ParticipationType> findAll() {
//        return participationTypeRepository.findAll();
//    }
//
//    public Optional<ParticipationType> findById(Long id) {
//        return participationTypeRepository.findById(id);
//    }
//
//    public ParticipationType save(ParticipationType participationType) {
//        return participationTypeRepository.save(participationType);
//    }
//
//    public void deleteById(Long id) {
//        participationTypeRepository.deleteById(id);
//    }
//}
