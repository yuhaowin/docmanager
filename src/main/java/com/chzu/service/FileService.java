package com.chzu.service;

import com.chzu.dao.FileDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileService {

    @Resource
    private FileDao fileDao;
}
