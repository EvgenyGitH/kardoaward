//package com.kardoaward.competitions.service.impl;
//
//import com.kardoaward.competitions.model.Direction;
//import com.kardoaward.competitions.repository.DirectionRepository;
//import com.kardoaward.competitions.service.DirectionService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//public class DirectionServiceImpl implements DirectionService {
//    @Autowired
//    private DirectionRepository directionRepository;
//
//    public List<Direction> findAll() {
//        return directionRepository.findAll();
//    }
//
//    public Optional<Direction> findById(Long id) {
//        return directionRepository.findById(id);
//    }
//
//    public Direction save(Direction direction) {
//        return directionRepository.save(direction);
//    }
//
//    public void deleteById(Long id) {
//        directionRepository.deleteById(id);
//    }
//}
