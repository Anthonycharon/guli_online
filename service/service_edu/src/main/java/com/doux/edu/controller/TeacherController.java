package com.doux.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doux.commonutils.R;
import com.doux.edu.POJO.Teacher;
import com.doux.edu.POJO.vo.TeacherQuery;
import com.doux.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 讲师 前端控制器
 *
 * @author ZT
 * @since 2023-02-07
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAll() {
        List<Teacher> teacherList = teacherService.list(null);
        return R.ok().data("items", teacherList);

    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("/delete/{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable Long current, @PathVariable Long limit) {
        Page<Teacher> teacherPage = new Page<>(current, limit);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<Teacher> pageRecords = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", pageRecords);
    }

    /**
     * 分页查询带条件
     *
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(value = "当前页")
                                  @PathVariable Long current, @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> teacherPage = new Page<>(current, limit);
        //构造条件
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();

        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (StringUtils.hasLength(name)) {
            teacherQueryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            teacherQueryWrapper.eq("level", level);
        }
        if (StringUtils.hasLength(begin)) {
            teacherQueryWrapper.ge("gmt_create", begin);
        }
        if (StringUtils.hasLength(end)) {
            teacherQueryWrapper.le("gmt_create", end);
        }
        teacherService.page(teacherPage, teacherQueryWrapper);
        long total = teacherPage.getTotal();
        List<Teacher> pageRecords = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", pageRecords);
    }

    /**
     * 添加讲师
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                        @RequestBody Teacher teacher) {
        if (teacherService.save(teacher)) {

            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师信息")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);

    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/modify")
    public R modifyTeacherById(@ApiParam(name = "teacher", value = "讲师", required = true)
                               @RequestBody Teacher teacher) {
        if (teacherService.updateById(teacher)) {

            return R.ok();
        } else {
            return R.error();
        }
    }
}
