package com.doux.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doux.edu.POJO.Subject;
import com.doux.edu.POJO.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ZT
 * @since 2023-02-24
 */
public interface SubjectService extends IService<Subject> {
    void saveSubject(MultipartFile file, SubjectService subjectService);

    List<OneSubject> getAllSubjects();
}
