package com.example.project.map;

import com.example.project.dto.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class gymDAOImpl implements gymDAO{
    private final gymRepository repository;
    @Override
    public List<Gym> gymlist(float x, float y) {
        List<Gym> list = repository.AreaGym(x, y);
        return list;
    }

    @Override
    public List<Gym> gymselectlist(String keyword,int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,15, Sort.by(Sort.Direction.ASC,"gymnumber"));
        Page<Gym> list = repository.findByGymaddrOrGymnameContaining(keyword,keyword, pageRequest);
        return list.getContent();
    }

    @Override
    public List<Gym> gymselectaddrlist(String keyword,int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,15, Sort.by(Sort.Direction.ASC,"gymnumber"));
        Page<Gym> list = repository.findByGymaddrContaining(keyword, pageRequest);
        return list.getContent();
    }

}
