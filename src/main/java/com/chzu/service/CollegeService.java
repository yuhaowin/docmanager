package com.chzu.service;

import com.chzu.dao.CollegeDao;
import com.chzu.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    public List<College> finAll() {
        return collegeDao.selectByExample();
    }
}
