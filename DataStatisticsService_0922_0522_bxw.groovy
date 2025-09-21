// 代码生成时间: 2025-09-22 05:22:53
package com.example

import groovy.json.JsonSlurper
import groovy.json.JsonOutput

// 数据统计分析器服务
class DataStatisticsService {

    // 构造函数
    DataStatisticsService() {
    }

    // 计算平均值
    double calculateAverage(List<Double> data) {
        try {
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            return data.sum() / data.size()
        } catch (IllegalArgumentException e) {
            // 处理数据为空或不合法的情况
            log.error("Error calculating average: \${e.message}")
            return Double.NaN
        }
    }

    // 计算中位数
    double calculateMedian(List<Double> data) {
        try {
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            List<Double> sortedData = data.sort()
            int middle = sortedData.size() / 2
            return sortedData.size() % 2 != 0 ? sortedData[middle] : (sortedData[middle - 1] + sortedData[middle]) / 2
        } catch (IllegalArgumentException e) {
            // 处理数据为空或不合法的情况
            log.error("Error calculating median: \${e.message}")
            return Double.NaN
        }
    }

    // 计算最大值
    Double calculateMax(List<Double> data) {
        try {
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            return Collections.max(data)
        } catch (IllegalArgumentException e) {
            // 处理数据为空或不合法的情况
            log.error("Error calculating max: \${e.message}")
            return null
        }
    }

    // 计算最小值
    Double calculateMin(List<Double> data) {
        try {
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            return Collections.min(data)
        }
        catch (IllegalArgumentException e) {
            // 处理数据为空或不合法的情况
            log.error("Error calculating min: \${e.message}")
            return null
        }
    }

    // 将数据转换为JSON格式
    String toJson(List<Double> data) {
        return JsonOutput.toJson(data)
    }

    // 从JSON字符串解析数据
    List<Double> fromJson(String jsonData) {
        try {
            new JsonSlurper().parseText(jsonData) as List<Double>
        } catch (Exception e) {
            log.error("Error parsing JSON data: \${e.message}")
            return []
        }
    }

    // 日志记录
    private def log = LoggerFactory.getLogger(DataStatisticsService)
}
