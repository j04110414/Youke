package com.tofly.youke.web;

import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.domain.po.Teacher;
import com.tofly.youke.service.TeacherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lyrics
 * @date 2017-12-22
 */
@Controller
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST)
    public BackResult<String> login(@PathVariable("name") String name, @PathVariable("password") String password) {
        BackResult<String> result = new BackResult<>();
        Teacher loginTeacher = new Teacher();

        return result;
    }
}
