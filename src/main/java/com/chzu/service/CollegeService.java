package com.chzu.service;

import com.chzu.dao.CollegeDao;
import com.chzu.entity.College;
import com.chzu.entity.CollegeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vinci on 2017/6/30.
 */
@Service
public class CollegeService{

    @Autowired
    private CollegeDao collegeDao;

    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();

        criteria.andCollegeidIsNotNull();


        return collegeDao.selectByExample(collegeExample);
    }
}
