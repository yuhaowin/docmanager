package com.chzu.dao;

import com.chzu.entity.CourseCustom;
import com.chzu.entity.PagingVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void count() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void findByPaging() {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(1);
        pagingVO.setPageSize(1);
        List<CourseCustom> byPaging = courseDao.findByPaging(pagingVO);
        System.out.println(byPaging.size());
    }
}