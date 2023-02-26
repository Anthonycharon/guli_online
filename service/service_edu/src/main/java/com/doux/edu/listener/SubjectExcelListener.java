package com.doux.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doux.baseservice.exception.MyException;
import com.doux.edu.POJO.Subject;
import com.doux.edu.POJO.excel.SubjectData;
import com.doux.edu.service.SubjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ZhangTao
 * @create 2023-02-24-21:55
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private SubjectService subjectService;

    //读取Excel,一行一行的
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new MyException(20001, "文件为空");
        }
        Subject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject == null) {
            oneSubject = new Subject();
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }
        String pid = oneSubject.getId();
        Subject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        if (twoSubject == null) {
            twoSubject = new Subject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }

    }

    //判断一级标题是否重复
    private Subject existOneSubject(SubjectService subjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级标题是否重复
    private Subject existTwoSubject(SubjectService subjectService, String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        Subject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
