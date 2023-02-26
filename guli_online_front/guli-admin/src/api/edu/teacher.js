import request from '@/utils/request'
export default {
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/edu/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
        })
    }, deleteTeacherId(id) {
        return request({
            url: `/edu/teacher/delete/${id}`,
            method: 'delete',
        })
    },
    //添加讲师
    addTeacher(teacher) {
        return request({
            url: `/edu/teacher/addTeacher`,
            method: 'post',
            data: teacher
        })
    },
    //根据id查询讲师
    getTeacherInfo(id) {
        return request({
            url: `/edu/teacher/getTeacher/${id}`,
            method: 'get'
        })
    },
    //修改讲师
    updateTeacherInfo(teacher) {
        return request({
            url: `/edu/teacher/modify`,
            method: 'post',
            data: teacher
        })
    }
}
