package com.tofly.youke.web;

import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.domain.KeyValueModel;
import com.tofly.youke.common.domain.RESPONSE_CODE;
import com.tofly.youke.common.exception.ServiceException;
import com.tofly.youke.domain.po.University;
import com.tofly.youke.service.UniversityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lyrics on 2017-11-17.
 */
@Controller
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @RequestMapping("/university")
    public String test() {
        University university = universityService.findUniversity("1");
        System.out.println(university.getUniversityName());
        return "hello";
    }

    /**
     * 高校key-value结构
     * @param university
     * @return
     */
    @RequestMapping(value = "/ajax/university/keyValue", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<List<KeyValueModel>> getUniversityKeyValue(@RequestBody University university) {
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

        return result;
    }

    @RequestMapping("/index")
    public String test2(Model model) {
        model.addAttribute("name", "jing");
        return "index";
    }

}
