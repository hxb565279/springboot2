package com.hxb.springboot2.controller;

import com.google.gson.Gson;
import com.hxb.springboot2.entity.*;
import com.hxb.springboot2.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取图像信息网站的内容
 */
public class GraphHandler {


    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";

    public static String getData() {
        return HttpClientUtil.doGet(urlStr);
    }

    public static List<GraphBean> getGraphData() {
        return getGraphData(getData());
    }

    public static List<GraphBean> getGraphData(String str) {
        List<GraphBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);

            String date = (String) tmp.get("date");
            double nowConfirm = (Double) tmp.get("nowConfirm");
            GraphBean graphBean = new GraphBean(date, (int) nowConfirm);
            result.add(graphBean);
        }

        return result;
    }

    public static List<GrapMapBean> getGraphData2(String str) {
        List<GrapMapBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);

            String date = (String) tmp.get("date");
            double nowConfirm = (Double) tmp.get("nowConfirm");
            double confirm = (Double) tmp.get("confirm");
            double dead = (Double) tmp.get("dead");
            double heal = (Double) tmp.get("heal");
            GrapMapBean graphBean = new GrapMapBean(date, (int) nowConfirm, (int) confirm, (int) dead, (int) heal);
            result.add(graphBean);
        }

        return result;
    }

    public static List<GraphAddBean> getGraphAddData() {
        return getGraphAddData(getData());
    }

    public static List<GraphAddBean> getGraphAddData(String str) {

        List<GraphAddBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayAddList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);
            String date = (String) tmp.get("date");
            double addConfirm = (Double) tmp.get("confirm");
            double addSuspect = (Double) tmp.get("suspect");

            GraphAddBean graphAddBean = new GraphAddBean(date,
                    (int) addConfirm, (int) addSuspect);
            result.add(graphAddBean);
        }

        return result;
    }

    public static List<GraphAddBean2> getGraphAddData2(String str) {

        List<GraphAddBean2> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayAddList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);
            String date = (String) tmp.get("date");
            double addConfirm = (Double) tmp.get("confirm");
            double addSuspect = (Double) tmp.get("suspect");
            double addHeal = (Double) tmp.get("heal");
            double addDead = (Double) tmp.get("dead");
            GraphAddBean2 graphAddBean = new GraphAddBean2(date,
                    (int) addConfirm, (int) addSuspect, (int) addHeal, (int) addDead);
            result.add(graphAddBean);
        }

        return result;
    }

    //
//    public static String urlStrAll2= "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
//    public static List<GraphColumnarBean> getGraphColumnarData2() {
//
//    }
//
//
    public static String urlStrAll = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static List<GraphColumnarBean> getGraphColumnarData() {
        List<GraphColumnarBean> result = new ArrayList<>();

        String respJson = HttpClientUtil.doGet(urlStrAll);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);


        ArrayList areaList = (ArrayList) subMap.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");


        for (int i = 0; i < childrenList.size(); i++) {

            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");

            ArrayList children = (ArrayList) tmp.get("children");
            for (int j = 0; j < children.size(); j++) {
                Map subTmp = (Map) children.get(j);
                if ("境外输入".equals((String) subTmp.get("name"))) {
                    Map total = (Map) subTmp.get("total");
                    double fromAbroad = (Double) total.get("confirm");

                    GraphColumnarBean bean = new GraphColumnarBean(name, (int) fromAbroad);
                    result.add(bean);
                }
            }
        }

        return result;

    }

    public static List<GraphPieBean> getGraphPieData() {
        return getGraphPieData(getData());
    }

    public static List<GraphPieBean> getGraphPieData(String str) {

        List<GraphPieBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        Map dataMap = (Map) subMap.get("nowConfirmStatis");

        for (Object o : dataMap.keySet()) {
            String name = (String) o;
            switch (name) {
                case "gat":
                    name = "港澳台病例";
                    break;
                case "import":
                    name = "境外输入病例";
                    break;
                case "province":
                    name = "31省本土病例";
                    break;
            }

            double value = (Double) dataMap.get(o);
            name += ":" + (int) value + "例";

            GraphPieBean bean = new GraphPieBean(name, (int) value);
            result.add(bean);
        }

        return result;
    }


}
