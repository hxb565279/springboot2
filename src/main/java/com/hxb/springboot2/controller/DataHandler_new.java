package com.hxb.springboot2.controller;


import com.google.gson.Gson;
import com.hxb.demo10.bean.GraphAddBean2;
import com.hxb.demo10.bean.GraphColumnarBean;
import com.hxb.demo10.bean.GraphPieBean;
import com.hxb.springboot2.entity.DataBean;
import com.hxb.springboot2.entity.GrapMapBean;
import com.hxb.springboot2.entity.MapBean;
import com.hxb.springboot2.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataHandler_new {
    @Autowired
    private DataService dataService;


    @GetMapping("/findAllData")
    public List<DataBean> findAllData() {
        List<DataBean> dataBeans = dataService.list();
        return dataBeans;
    }

    @GetMapping("/findall")
    public List<DataBean> findAllDataFor() {
        System.out.println(11);
        return dataService.list2();
    }

    @GetMapping("/findallMap")
    public List<DataBean> findAllDataMap() {
        return dataService.list2();
    }

    @GetMapping("/gnyqmap")
    public List<MapBean> gnyqmap(Model model) {
        List<DataBean> dataList = dataService.list5();
        List<MapBean> result = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            DataBean dataBean = dataList.get(i);
            MapBean mapBean = new MapBean(dataBean.getArea(), dataBean.getNowConfirm());
            result.add(mapBean);

        }
        model.addAttribute("mapData", new Gson().toJson(result));
        return result;
    }

    @GetMapping("/gnyq2")
    public List<ArrayList> gnyq2() {
        List<ArrayList> list2 = new ArrayList<>();
        String str = GraphHandler.getData();
        List<GrapMapBean> list = GraphHandler.getGraphData2(str);
        //  进一步改造数据格式
        //  因为前端需要的数据是  x轴所有数据的数组和y轴所有数据的数组

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> nowConfirmList = new ArrayList<>();
        ArrayList<Integer> ConfirmList = new ArrayList<>();
        ArrayList<Integer> deadList = new ArrayList<>();
        ArrayList<Integer> healList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GrapMapBean graphBean = list.get(i);
            dateList.add(graphBean.getDate());
            nowConfirmList.add(graphBean.getNowConfirm());
            ConfirmList.add(graphBean.getConfirm());
            deadList.add(graphBean.getDead());
            healList.add(graphBean.getHeal());
        }
        list2.add(dateList);
        list2.add(nowConfirmList);
        list2.add(ConfirmList);
        list2.add(deadList);
        list2.add(healList);
        return list2;
    }

    @GetMapping("/gnyq3")
    public List<ArrayList> gnyq3() {
        String str = GraphHandler.getData();
        List<ArrayList> list2 = new ArrayList<>();

        List<GraphAddBean2> addList = com.hxb.demo10.handller.GraphHandler.getGraphAddData2(str);
        ArrayList<String> addDateList = new ArrayList<>();
        ArrayList<Integer> addConfirmList = new ArrayList<>();
        ArrayList<Integer> addSuspectList = new ArrayList<>();
        ArrayList<Integer> addHealList = new ArrayList<>();
        ArrayList<Integer> addDeadList = new ArrayList<>();
        for (int i = 0; i < addList.size(); i++) {
            GraphAddBean2 graphAddBean = addList.get(i);
            addDateList.add(graphAddBean.getDate());
            addConfirmList.add(graphAddBean.getAddConfirm());
            addSuspectList.add(graphAddBean.getAddSuspect());
            addHealList.add(graphAddBean.getAddHeal());
            addDeadList.add(graphAddBean.getAddDead());
        }
        list2.add(addDateList);
        list2.add(addConfirmList);
        list2.add(addSuspectList);
        list2.add(addHealList);
        list2.add(addDeadList);
        return list2;

    }

    @GetMapping("/gnyq4")
    public List<GraphPieBean> gnyq4() {
        String str = GraphHandler.getData();
        List<GraphPieBean> pieList = com.hxb.demo10.handller.GraphHandler.getGraphPieData(str);
        Collections.sort(pieList);
        return pieList;
    }

    @GetMapping("/gnyq5")
    public List<DataBean> gnyq5() {
        List<DataBean> dataList2 = dataService.list5();
        dataList2.sort((x, y) -> Double.compare(y.getConfirm(), x.getConfirm()));
        return dataList2;
    }


    @GetMapping("/gyyqmap")
    public List<List> gyyqmap(Model model) {
        List<DataBean> dataList = this.dataService.list2();
        List<DataBean> dataList2 = this.dataService.list2();
        List<DataBean> dataList3 = dataService.list5();
        List<List> result = new ArrayList<>();
        result.add(dataList);
        result.add(dataList3);
        return result;
    }

    @GetMapping("/gyyq2")
    public List<ArrayList> gyyq2(Model model) {
        List<DataBean> dataList2 = this.dataService.list2();
        dataList2.sort((x, y) -> Double.compare(y.getNowConfirm(), x.getNowConfirm()));
        //境外排序
        List<ArrayList> list2 = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> nameList2 = new ArrayList<>();
        ArrayList<Integer> AbroadList = new ArrayList<>();
        ArrayList<Integer> ConfirmAddList = new ArrayList<>();
        ArrayList<Integer> DeadList = new ArrayList<>();
        for (int i = 2; i < 16; i++) {
            DataBean dataBean = dataList2.get(i);
            GraphColumnarBean bean = new GraphColumnarBean();
            bean.setArea(dataBean.getArea());
            bean.setFromAbroad((int) dataBean.getNowConfirm());
            nameList.add(bean.getArea());
            AbroadList.add(bean.getFromAbroad());
            ConfirmAddList.add(dataBean.getConfirm());
            DeadList.add(dataBean.getDead());
        }

        list2.add(nameList);
        list2.add(AbroadList);
        list2.add(DeadList);
        return list2;
    }

    @GetMapping("/gyyq3")
    public List<List> gyyq3(Model model) {
        List<DataBean> dataList = this.dataService.list2();
        List<DataBean> dataList2 = this.dataService.list2();
        List<DataBean> dataList3 = dataService.list5();
        List<List> result = new ArrayList<>();
        result.add(dataList);
        result.add(dataList3);
        return result;
    }

    @GetMapping("/gnyqtotal")
    public DataBean gnyqtotal(Model model) {
        List<DataBean> dataList = dataService.list5();
        model.addAttribute("data_total", dataList.get(0));
        return dataList.get(0);
    }

}
