package com.coolgua.signup.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.coolgua.signup.domain.GalaxyGraph;

public class GraphUtil {
    
    public static List<GalaxyGraph> getGalaxyGraphs(int width, int height, int num, boolean openIdEmpty) {
        int radius = 0;
        int originX = width / 2;
        int originY = height / 2;
        if(num <= 200) {
            radius = 30;
        } else if(num <= 1000) {
            radius = 24;  // 0.8
        } else if(num <= 5000) {
            radius = 18;  // 0.6
        } else {
            radius = 10;
        }
        
        int radiusLite = (int) (radius * 0.8);
        int gridWidth = 2*radius + 4;
        List<GalaxyGraph> result = new ArrayList<GalaxyGraph>();
        List<String> points = new ArrayList<>();
        points.add("0_0");
        if(openIdEmpty) {
        	// 先插入前三个
        	if(num > 0) {
        		getGraph(0, 0, radius, gridWidth, result, originX, originY);
        	}
        	if(num > 1) {
        		getGraph(3, -1, radius, gridWidth, result, originX, originY);
        		points.add("3_-1");
        	}
        	if(num > 2) {
        		getGraph(-3, 1, radius, gridWidth, result, originX, originY);
        		points.add("-3_1");
        	}
        } else {
        	if(num > 0) {
        		getGraph(0, 0, radius, gridWidth, result, originX, originY);
        	}
        }
        
        if(result.size() < num) {
            // 计算出x, y方向所需的图形数量， 并留一点余量
            float ratio = (float)height/width;
            double factor = Math.sqrt(num/ratio);
            int xNum = (int) (factor) + 1;
            int yNum = (int) (ratio * factor) + 1;
            while(result.size() < num) {
            	int xRand = getRand(xNum);
            	int yRand = getRand(yNum);
            	String point = xRand + "_" + yRand;
            	if(!points.contains(point)) {
            		points.add(point);
            		getGraph(xRand, yRand, radiusLite, gridWidth, result, originX, originY);
            	}
            }
            /*
            List<Integer> xPoints = getPoints(xNum);
            List<Integer> yPoints = getPoints(yNum);
            
            addGraph(xPoints, yPoints, gridWidth, result, num, radiusLite, originX, originY);
            */
        }
        
//        String str = JSONArray.toJSONString(result);
//        System.out.println(str);
        System.out.println("size=" + result.size());
        return result;
    }

    private static void addGraph(List<Integer> xPoints, List<Integer> yPoints, int gridWidth
            , List<GalaxyGraph> result, int num, int radiusLite, int originX, int originY) {
        for (int i = 0; i < xPoints.size(); i++) {
            for (int j = 0; j < yPoints.size(); j++) {
                if (result.size() < num) {
                	int x = xPoints.get(i);
                	int y = yPoints.get(j);
                	if((x == 0 && y == 0) || (x == 3 && y == -1) || (x == -3 && y == 1)) {
                		// 如果是这三个点， 则忽略
                	} else {
                		getGraph(xPoints.get(i), yPoints.get(j), radiusLite, gridWidth, result, originX, originY);
                	}
                } else {
                    return;
                }
            }
        }
    }

    private static void getGraph(int xIndex, int yIndex, int radius, int gridWidth
            , List<GalaxyGraph> result, int originX, int originY) {
        GalaxyGraph graph = new GalaxyGraph();
        graph.setX(xIndex* gridWidth + originX);
        graph.setY(yIndex *  gridWidth + originY);
        graph.setDiameter(radius*2);
        result.add(graph);
    }
    
    private static List<Integer> getPoints(int num) {
        int min = - num/2;
        int max = num/2;
        List<Integer> result = new ArrayList<Integer>();
        while(result.size() < num) {
            int rand = min + (int)(Math.random() * ((max - min) + 1));
            if(!result.contains(rand)) {
                result.add(rand);
            }
        }
        return result;
    }
    
    private static int getRand(int num) {
        int min = - num/2;
        int max = num/2;
        int rand = min + (int)(Math.random() * ((max - min) + 1));
        return rand;
    }
    
    public static void main(String[] args) {
        getGalaxyGraphs(1024, 600, 960, true);
    }

}
