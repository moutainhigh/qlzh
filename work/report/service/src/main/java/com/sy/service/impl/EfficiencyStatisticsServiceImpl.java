package com.sy.service.impl;

import com.google.common.collect.Lists;
import com.sy.dao.EfficiencyStatisticsDao;
import com.sy.dao.TaskDao;
import com.sy.entity.EfficiencyStatistics;
import com.sy.entity.Task;
import com.sy.service.EfficiencyStatisticsService;
import com.sy.vo.EfficiencyStatisticsVo;
import com.sy.vo.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;


@Service
public class EfficiencyStatisticsServiceImpl implements EfficiencyStatisticsService {

    @Autowired
    private EfficiencyStatisticsDao efficiencyStatisticsDao;

    @Autowired
    private TaskDao taskDao;


    @Override
    public List<EfficiencyStatisticsVo> getAllData(String taskName, Date beginTime, Date endTime) throws Exception {


        List<EfficiencyStatistics> list = getEfficiencyStatistics(taskName,beginTime, endTime);//所有的工程报表数据(所有数据都是焊工级的)

        //根据项目名称获取顶级上级项目
        Set<String> taskNames = new HashSet<>();
        Map<String,List<EfficiencyStatistics>> map = new HashMap<>();

        for (EfficiencyStatistics efficiencyStatistics : list) {
      //    String name = getFirstTaskName(efficiencyStatistics.getName());
            String name = getFirstTaskName1(efficiencyStatistics.getTaskId());//name为生产部级的projectName

            taskNames.add(name); //taskNames是所有生产部级的projectName，set内元素不重复
            if(map.get(name)==null){
                List<EfficiencyStatistics> tempList = new ArrayList<>();
                tempList.add(efficiencyStatistics);
                map.put(name,tempList);
            }else {
                map.get(name).add(efficiencyStatistics);
            }
        }

        if(!"".equals(taskName)&&taskName!=null){
            if(!taskNames.contains(taskName)){
                throw new Exception("查询的项目不存在/当日该项目并未施工");
            }

            Unit unit = calculateData(taskName, map);
    //      Unit unit = calculateData2(taskName, map);

            List<EfficiencyStatisticsVo> vos = new ArrayList<>();

            handleVo(unit, vos);

            return vos;
        }else {
            List<EfficiencyStatisticsVo> vos = new ArrayList<>();
            for (String name : taskNames) {
                Unit unit = calculateData(name, map);
      //        Unit unit = calculateData2(name, map);
                handleVo(unit, vos);
            }

            if(!vos.isEmpty()){
                return vos;
            }
            return null;
        }

    }

    public List<EfficiencyStatistics> getEfficiencyStatistics(String taskName,Date beginTime, Date endTime) {
        Specification querySpeci = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();

                if (beginTime!=null&&endTime!=null){
                    predicates.add(criteriaBuilder.between(root.get("date"),beginTime,endTime));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return efficiencyStatisticsDao.findAll(querySpeci);
    }

    private void handleVo(Unit unit, List<EfficiencyStatisticsVo> vos) {
        Set<String> set = new HashSet<>();
        Map<String,List<Unit>> map = new HashMap<>();
        for (Unit son : unit.getSonList()) {
            String name = son.getName();
            set.add(name); //焊工级别的project_name
            if(map.get(name)==null){
                List<Unit> list = new ArrayList<>();
                list.add(son);
                map.put(name,list);
            }else {
                map.get(name).add(son);
            }
        }

        for (String s : set) { //set是所有焊工级别的project_name
            int time = 0;
            int workTime = 0;
            BigDecimal ePower = new BigDecimal("0");
            for (Unit u : map.get(s)) {
                time += u.getTime();
                workTime += u.getWorkTime();
                ePower = ePower.add(new BigDecimal(u.getPower()));
            }
            EfficiencyStatisticsVo vo = new EfficiencyStatisticsVo();
            vo.setTime(unit.getTime());
            vo.setWorkTime(unit.getWorkTime());
            vo.setName(unit.getName());

            vo.setPower(unit.getPower());
            vo.setSonName(s);
            vo.setSonTime(time);
            vo.setSonWorkTime(workTime);
            vo.setSonPower(ePower.doubleValue());
            vo.setWorkNo(taskDao.getWorkNoByName(unit.getName()));
            vos.add(vo);
        }
    }

    //新写的方法
    private void handleVo2(Unit unit, List<EfficiencyStatisticsVo> vos) {
        Set<String> set = new HashSet<>();
        Map<String,List<Unit>> map = new HashMap<>();
        for (Unit son : unit.getSonList()) {
            String name = son.getName();
            set.add(name); //焊工级别的project_name
            if(map.get(name)==null){
                List<Unit> list = new ArrayList<>();
                list.add(son);
                map.put(name,list);
            }else {
                map.get(name).add(son);
            }
        }

        for (String s : set) { //set是所有焊工级别的project_name
            int time = 0;
            int workTime = 0;
            BigDecimal ePower = new BigDecimal("0");
            for (Unit u : map.get(s)) {
                time += u.getTime();
                workTime += u.getWorkTime();
                ePower = ePower.add(new BigDecimal(u.getPower()));
            }


            /*List<EfficiencyStatistics> lists =  efficiencyStatisticsDao.findAll();
            List<Integer> pids = new ArrayList<>();
            Map<Integer,EfficiencyStatistics> map2 = new HashMap<>();
            int time3=0; //班组级别的
            int workingtime3=0;

            for(EfficiencyStatistics efficiencyStatistics : lists){
                Integer taskId = efficiencyStatistics.getTaskId();
                Integer pid = taskDao.getPidById(taskId);
                pids.add(pid);
                if(pids.contains(pid)){
                    time3 += time3;
                    workingtime3 += workingtime3;
                }else{
                    time3 = unit.getTime();
                    workingtime3 = unit.getWorkTime();
                }
            }*/

            EfficiencyStatisticsVo vo = new EfficiencyStatisticsVo();
            vo.setTime(unit.getTime());
            vo.setWorkTime(unit.getWorkTime());
            vo.setName(unit.getName());
            vo.setPower(unit.getPower());

            vo.setSonName(s);
            vo.setSonTime(time);
            vo.setSonWorkTime(workTime);
            vo.setSonPower(ePower.doubleValue());
            vo.setWorkNo(taskDao.getWorkNoByName(unit.getName()));
            vos.add(vo);

        }
    }

    private Unit calculateData(String taskName, Map<String, List<EfficiencyStatistics>> map) {
        Unit unit = new Unit();
        unit.setName(taskName);
        int time = 0;
        int work_time = 0 ;
        BigDecimal ePower = new BigDecimal("0");
        List<Unit> sonList = new ArrayList<>();
        for (EfficiencyStatistics efficiencyStatistics : map.get(taskName)) {
            Unit sonUnit = new Unit();
            sonUnit.setName(efficiencyStatistics.getName());
            sonUnit.setPower(efficiencyStatistics.getPower());
            sonUnit.setWorkTime(efficiencyStatistics.getWorkingTime());
            sonUnit.setTime(efficiencyStatistics.getTime());
            sonList.add(sonUnit);

            time += efficiencyStatistics.getTime();
            work_time += efficiencyStatistics.getWorkingTime();
            ePower = ePower.add(new BigDecimal(efficiencyStatistics.getPower()));
        }
        unit.setSonList(sonList);
        unit.setTime(time);
        unit.setWorkTime(work_time);
        unit.setPower(ePower.doubleValue());
        return unit;
    }


    //新写的方法
    private Unit calculateData2(String taskName, Map<String, List<EfficiencyStatistics>> map) {
        Unit unit = new Unit();
        unit.setName(taskName);
        int time = 0;
        int work_time = 0 ;
        BigDecimal ePower = new BigDecimal("0");
        List<Unit> sonList = new ArrayList<>();
        int taskId = 0;
        List<Integer> ids = new ArrayList<>();
        int time3=0; //班组级别的
        int workingtime3=0;
        Integer id = null;
        Map<Integer,EfficiencyStatistics> map2 = new HashMap<>();
        for (EfficiencyStatistics efficiencyStatistics : map.get(taskName)) {
            if(ids.size() == 0){ //
                taskId = efficiencyStatistics.getTaskId();
                Integer pid = taskDao.getPidById(taskId);
                id = pid;
                ids.add(id);
                map2.put(id,efficiencyStatistics);
                time3 = efficiencyStatistics.getTime();
                workingtime3 = efficiencyStatistics.getWorkingTime();

            }else{
                taskId = efficiencyStatistics.getTaskId();
                Integer pid = taskDao.getPidById(taskId);
                id = pid;
                if(ids.contains(id)){
                    time3 += time3;
                    workingtime3 += workingtime3;
                }else{
                    time3 = unit.getTime();
                    workingtime3 = unit.getWorkTime();
                }
            }








            Unit sonUnit = new Unit();
            sonUnit.setName(efficiencyStatistics.getName());
            sonUnit.setPower(efficiencyStatistics.getPower());
            sonUnit.setWorkTime(efficiencyStatistics.getWorkingTime());
            sonUnit.setTime(efficiencyStatistics.getTime());
            sonList.add(sonUnit);

            time += efficiencyStatistics.getTime();
            work_time += efficiencyStatistics.getWorkingTime();
            ePower = ePower.add(new BigDecimal(efficiencyStatistics.getPower()));
        }
        unit.setSonList(sonList);
        unit.setTime(time);
        unit.setWorkTime(work_time);
        unit.setPower(ePower.doubleValue());
        return unit;
    }

    public String getFirstTaskName(String taskName) { //获取该任务最顶级的项目名称
        int pid = -1;
        String tempName = taskName;
        while (pid != 0){
            pid = taskDao.getPidByName(tempName);
            if(pid!=0){
                tempName = taskDao.getNameById(pid);
            }
        }
        return tempName;
    }

    //获取该任务最顶级的项目名称(新方法,解决项目名称不能重复问题)
    public String getFirstTaskName1(Integer taskId){
        Integer pid = -1;
        Integer id = taskId;
        String tempName = null;
        while (pid != 0){
            pid = taskDao.getPidById(id);
            if(pid!=0){
                id = pid;
            }
            tempName = taskDao.getNameById(id);
        }
        return tempName;
    }

}
