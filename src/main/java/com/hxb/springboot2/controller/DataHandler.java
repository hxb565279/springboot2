package com.hxb.springboot2.controller;

import com.google.gson.Gson;

import com.hxb.springboot2.entity.DataBean;
import com.hxb.springboot2.service.DataService;
import com.hxb.springboot2.util.HttpURLConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

;


/**
 * 使用HttpURLConnection实时的从网站获取最新数据内容
 */

@Component
public class DataHandler {

    @Autowired
    private DataService dataService;
    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";


    public static String urlStr2 = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_foreign";
    public static String urlStr3 = "https://api.inews.qq.com/newsqa/v1/automation/modules/list?modules=FAutoCountryConfirmAdd,WomWorld,WomAboard";
    public static String urlStr4 = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=statisGradeCityDetail,diseaseh5Shelf";

    public void saveData() {
        List<DataBean> dataBeans = getData();
        // 先将数据清空  然后存储数据
        dataService.remove(null);
        dataService.saveBatch(dataBeans);

    }

    // 配置定时执行的注解  支持cron表达式
    @Scheduled(cron = "0 20 * * * ? ")
    public void updateData() {
        System.out.println("更新数据");

        // TODO 增加监听  提供用户订阅功能的   比如关注黑龙江省份 新增人数的变化

        saveData();
    }

    public static void main(String[] args) {
        System.out.println(getData());
    }
    //国内
    public static List<DataBean> getData() {

        /**
         * 分析json字符串对数据进行筛选和提取
         */
        // 实时获取数据
        String respJson = HttpURLConnectionUtil.doGet(urlStr);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        // 此时增加了一层处理  而且data对应的数据格式是string
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);


        ArrayList areaList = (ArrayList) subMap.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();

        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            DataBean dataBean = new DataBean(i + 1, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean);
        }
        return result;
    }


    //国外
    public static List<DataBean> getData3() {

        /**
         * 分析json字符串对数据进行筛选和提取
         */
        // 实时获取数据
        String respJson = HttpURLConnectionUtil.doGet(urlStr3);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);
        Map subMap = (Map) map.get("data");

        ArrayList areaList = (ArrayList) subMap.get("WomAboard");

        Map tmp2 = (Map) subMap.get("WomWorld");

        String name2 = (String) tmp2.get("name");
        double nowConfirm2 = (Double) tmp2.get("confirm");
        double confirm2 = (Double) tmp2.get("nowConfirm");
        double heal2 = (Double) tmp2.get("heal");
        double dead2 = (Double) tmp2.get("dead");
        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();
        DataBean dataBean = new DataBean(1, "总计/现有确诊", (int) nowConfirm2, (int) confirm2, (int) heal2, (int) dead2);
        result.add(dataBean);
        for (int i = 0; i < areaList.size(); i++) {
            Map tmp = (Map) areaList.get(i);

            String name = (String) tmp.get("name");
            double nowConfirm = (Double) tmp.get("confirm");
            double confirm = (Double) tmp.get("confirmAdd");
            double heal = (Double) tmp.get("heal");
            double dead = (Double) tmp.get("dead");
            DataBean dataBean2 = new DataBean(i + 2, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean2);

        }

        return result;
    }

    //国内城市加综合
    public static List<DataBean> getData6() {


        String respJson = HttpURLConnectionUtil.doGet(urlStr4);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        // 此时增加了一层处理  而且data对应的数据格式是string
        Map subMap = (Map) map.get("data");
//        Map subMap = gson.fromJson(subStr, Map.class);


        Map areaList = (Map) subMap.get("diseaseh5Shelf");
//        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) areaList.get("areaTree");
        Map childrenListTol = (Map) areaList.get("chinaTotal");
        Map childrenList2 = (Map) childrenList.get(0);
//        Map map1 = (Map) childrenListTol.get(0);
        ArrayList childrenList3 = (ArrayList) childrenList2.get("children");

        double nowConfirm2 = (Double) childrenListTol.get("nowConfirm");
        double confirm2 = (Double) childrenListTol.get("confirm");
        double heal2 = (Double) childrenListTol.get("heal");
        double dead2 = (Double) childrenListTol.get("dead");
        DataBean dataBean = new DataBean(1, "总计/现存", (int) nowConfirm2, (int) confirm2, (int) heal2, (int) dead2);

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();
        result.add(dataBean);
        for (int i = 0; i < childrenList3.size(); i++) {
            Map tmp = (Map) childrenList3.get(i);
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            DataBean dataBean2 = new DataBean(i + 2, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean2);

        }
        return result;
    }


    //国内全部城市
    public static List<DataBean> getData4() {


        String respJson = HttpURLConnectionUtil.doGet(urlStr4);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        // 此时增加了一层处理  而且data对应的数据格式是string
        Map subMap = (Map) map.get("data");
//        Map subMap = gson.fromJson(subStr, Map.class);


        Map areaList = (Map) subMap.get("diseaseh5Shelf");
//        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) areaList.get("areaTree");
        Map childrenListTol = (Map) areaList.get("chinaTotal");
        Map childrenList2 = (Map) childrenList.get(0);
//        Map map1 = (Map) childrenListTol.get(0);
        ArrayList childrenList3 = (ArrayList) childrenList2.get("children");

        double nowConfirm2 = (Double) childrenListTol.get("nowConfirm");
        double confirm2 = (Double) childrenListTol.get("confirm");
        double heal2 = (Double) childrenListTol.get("heal");
        double dead2 = (Double) childrenListTol.get("dead");
        DataBean dataBean = new DataBean(1, "总计/现存", (int) nowConfirm2, (int) confirm2, (int) heal2, (int) dead2);


        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();
        result.add(dataBean);
        for (int i = 0; i < childrenList3.size(); i++) {
            Map tmp = (Map) childrenList3.get(i);
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            List<DataBean> tmp2 = (ArrayList) tmp.get("children");


            DataBean dataBean2 = new DataBean(i + 2, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean2);
            for (int j = 0; j < tmp2.size(); j++) {
                Map tmp3 = (Map) tmp2.get(j);
                String name2 = (String) tmp3.get("name");
                Map totalMap2 = (Map) tmp3.get("total");
                double nowConfirm3 = (Double) totalMap2.get("nowConfirm");
                double confirm3 = (Double) totalMap2.get("confirm");
                double heal3 = (Double) totalMap2.get("heal");
                double dead3 = (Double) totalMap2.get("dead");
                DataBean dataBean3 = new DataBean(i + 2, name2, (int) nowConfirm3, (int) confirm3, (int) heal3, (int) dead3);
                result.add(dataBean3);
            }
        }
        return result;
    }

    //国内加国外
    public static List<DataBean> getData5() {


        String respJson = HttpURLConnectionUtil.doGet(urlStr);
        String respJson1 = HttpURLConnectionUtil.doGet(urlStr3);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        Gson gson1 = new Gson();
        Map map1 = gson1.fromJson(respJson1, Map.class);

        // 此时增加了一层处理  而且data对应的数据格式是string
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);


        ArrayList areaList = (ArrayList) subMap.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        Map subMap1 = (Map) map1.get("data");

        ArrayList areaList11 = (ArrayList) subMap1.get("WomAboard");

//        Map tmp21 = (Map) subMap1.get("WomWorld");
//
//        double nowConfirm2 = (Double) tmp21.get("confirm");
//        double confirm2 = (Double) tmp21.get("nowConfirm");
//        double heal2 = (Double) tmp21.get("heal");
//        double dead2 = (Double) tmp21.get("dead");
//        DataBean dataBean4 = new DataBean(1, "总计/现有确诊", (int) nowConfirm2, (int) confirm2, (int) heal2, (int) dead2);
//

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();

        for (int i = 0; i < areaList11.size(); i++) {
            Map tmp = (Map) areaList11.get(i);

            String name = (String) tmp.get("name");
            double nowConfirm = (Double) tmp.get("confirm");
            double confirm = (Double) tmp.get("confirmAdd");
            double heal = (Double) tmp.get("heal");
            double dead = (Double) tmp.get("dead");
            DataBean dataBean5 = new DataBean(i + 2, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean5);

        }


        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            DataBean dataBean = new DataBean(i + 1, name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean);
        }


        return result;
    }


}
