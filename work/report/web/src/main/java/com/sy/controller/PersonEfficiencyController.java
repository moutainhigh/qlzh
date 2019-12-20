package com.sy.controller;


import com.sy.constant.HttpStatusConstant;
import com.sy.service.DeptService;
import com.sy.service.PersonEfficiencyService;
import com.sy.utils.DateUtils;
import com.sy.vo.PageJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personEfficiency")
public class PersonEfficiencyController {

    @Autowired
    private PersonEfficiencyService personEfficiencyService;

    @Autowired
    private DeptService deptService;


    @RequestMapping(value = "all",method = RequestMethod.GET)
    public PageJsonResult getAllData(String personName,int deptId,Integer page,Integer pageSize,String beginTime,String endTime){

        try {
            personEfficiencyService.calculateData(personName,deptId,beginTime =="" ?null:DateUtils.parseDate(beginTime),endTime =="" ?null:DateUtils.getNextDay(endTime));
        } catch (Exception e) {
            e.printStackTrace();
            return PageJsonResult.buildFailurePage(404,e.getMessage());
        }

        return PageJsonResult.buildSuccessPage(HttpStatusConstant.SUCCESS,personEfficiencyService.initAllData(page,pageSize));
    }

}
