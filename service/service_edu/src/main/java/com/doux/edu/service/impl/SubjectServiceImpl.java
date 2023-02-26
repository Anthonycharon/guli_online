package com.doux.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doux.edu.POJO.Subject;
import com.doux.edu.POJO.excel.SubjectData;
import com.doux.edu.POJO.subject.OneSubject;
import com.doux.edu.POJO.subject.TwoSubject;
import com.doux.edu.listener.SubjectExcelListener;
import com.doux.edu.mapper.SubjectMapper;
import com.doux.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZT
 * @since 2023-02-24
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OneSubject> getAllSubjects() {
        //查询一级课程
        QueryWrapper<Subject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parent_id", "0");
        List<Subject> oneSubjectList = baseMapper.selectList(queryWrapper1);

        //查询二级课程
        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", "0");
        List<Subject> twoSubjectList = baseMapper.selectList(queryWrapper2);

        List<OneSubject> subjectList = new ArrayList<>();

        //封装一级课程
        for (Subject subjectOne : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(subject.getId());
//            oneSubject.setTitle(subject.getTitle());
            BeanUtils.copyProperties(subjectOne, oneSubject);

            //封装二级课程
            List<TwoSubject> twoSubjects = new ArrayList<>();
            for (Subject subjectTwo : twoSubjectList) {
                if (subjectTwo.getParentId().equals(subjectOne.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subjectTwo, twoSubject);
                    twoSubjects.add(twoSubject);
                }

            }
            oneSubject.setChildren(twoSubjects);
            subjectList.add(oneSubject);
        }
        return subjectList;
    }
}
