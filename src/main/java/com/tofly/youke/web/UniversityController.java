package com.tofly.youke.web;

import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.domain.KeyValueModel;
import com.tofly.youke.common.domain.RESPONSE_CODE;
import com.tofly.youke.common.exception.ServiceException;
import com.tofly.youke.domain.po.University;
import com.tofly.youke.service.UniversityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author lyrics
 * @date 2017-11-17
 */
@Controller
public class UniversityController {
    private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @Autowired
    private UniversityService universityService;

    @RequestMapping("/university")
    public String test() {
        University university = universityService.findUniversity("1");
        System.out.println(university.getUniversityName());
        return "hello";
    }

    @RequestMapping("/university/keyValue")
    @ResponseBody
    public void getUniversityKeyValue(@RequestBody University university) {
        BackResult<List<KeyValueModel>> result = new BackResult<>();
        result.setCode(RESPONSE_CODE.BACK_CODE_FAIL.value);
        try {
            university.setCountry("CHN");
            List<KeyValueModel> list = universityService.getUniversityKeyValue(university);
            result.setCode(RESPONSE_CODE.BACK_CODE_SUCCESS.value);
            result.setData(list);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/index")
    public String test2(Model model) {
        model.addAttribute("name", "jing");
        return "index";
    }

}
