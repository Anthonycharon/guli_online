package com.doux.edu.controller;


import com.doux.commonutils.R;
import com.doux.edu.POJO.subject.OneSubject;
import com.doux.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZT
 * @since 2023-02-24
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    //获取上传的文件并读取文件
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    @GetMapping("/getAllSubject")
    public R getAllSubject() {
        List<OneSubject> subjectList = subjectService.getAllSubjects();
        return R.ok().data("list", subjectList);
    }
}

