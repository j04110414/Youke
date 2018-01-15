package com.tofly.youke.web;

import com.github.pagehelper.Page;
import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.domain.RESPONSE_CODE;
import com.tofly.youke.common.utils.DataTablesPager;
import com.tofly.youke.common.utils.PageUtils;
import com.tofly.youke.common.utils.VerifyAndHintUtil;
import com.tofly.youke.domain.po.Student;
import com.tofly.youke.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyrics
 * @date 2017-12-22.
 */
@Controller
public class StudentController {
<<<<<<< HEAD
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
=======
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
>>>>>>> 370c2b47f7e4ecb767396b42d418cfa889e3ebfd

    @Autowired
    private StudentService studentService;

    /**
     * 学生列表页
     */
    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public String studentListPage() {
        return "student/student_list";
    }

    /**
     * 学生编辑页
     */
    @RequestMapping(value = "/student/form", method = RequestMethod.GET)
    public String studentFormPage(Model model, HttpServletRequest request) {

        String studentId = request.getParameter("id");
        Student student = new Student();
        if (StringUtils.hasText(studentId)) {
            student = studentService.selectByPrimaryKey(studentId);
            model.addAttribute("isOperatorEdit", true);
        } else {
            model.addAttribute("isOperatorEdit", false);
        }
        model.addAttribute("student", student);
        return "student/student_form";
    }

    /**
     * 查询学生列表信息
     */
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesPager<List<Student>> list(HttpServletRequest request) {
        BackResult<List<Student>> result = new BackResult<>();
        result.setCode(RESPONSE_CODE.BACK_CODE_FAIL.value);

        String universityId = request.getParameter("universityId");
        String instituteId = request.getParameter("instituteId");
        Student student = new Student();
        if (StringUtils.hasText(universityId)) {
            student.setUniversityId(universityId);
        }
        if (StringUtils.hasText(universityId)) {
            student.setInstituteId(instituteId);
        }

        DataTablesPager dataTablesPager = null;

        student.setCountryCode("CHN");
        try {
            Page<Object> pagerParams = PageUtils.getPagerParams(request);

            Page<List<Student>> page = pagerParams.doSelectPage(() -> studentService.findByObjParams(student));

            dataTablesPager = PageUtils.pageHelperToDataTablesPager(page, request);

            result.setCode(RESPONSE_CODE.BACK_CODE_SUCCESS.value);
        } catch (Exception e) {
            LOGGER.error("查询学生列表信息失败");
        }

        return dataTablesPager;
    }

    /**
     * 新增_编辑学生信息
     */
    @RequestMapping(value = "/student/save", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<String> saveStudent(@Validated Student student) {
        BackResult<String> result = new BackResult<>();
        result.setCode(RESPONSE_CODE.BACK_CODE_FAIL.value);

        try {
            if (StringUtils.hasText(student.getId())) {
                studentService.updateByPrimaryKeySelective(student);
            } else {
                student.setCountryCode("CHN");
                student.setCountryName("中国");
                studentService.insert(student);
            }
            result.setCode(RESPONSE_CODE.BACK_CODE_SUCCESS.value);
        } catch (Exception e) {
            LOGGER.error("新增学生信息失败");
        }

        return result;
    }


    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<String> deleteStudent(@PathVariable("id") String id) {
        BackResult<String> result = new BackResult<>();
        result.setCode(RESPONSE_CODE.BACK_CODE_FAIL.value);

        try {
            VerifyAndHintUtil.checkNull(id, "学生id不能为空");
            studentService.updateStuDelFlag(id);
            result.setCode(RESPONSE_CODE.BACK_CODE_SUCCESS.value);
        } catch (Exception e) {
            LOGGER.error("删除学生信息失败");
        }

        return result;
    }

}
