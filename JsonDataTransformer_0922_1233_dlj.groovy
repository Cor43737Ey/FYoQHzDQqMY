// 代码生成时间: 2025-09-22 12:33:14
import grails.converters.JSON
import groovy.json.JsonSlurper

class JsonDataTransformer {

    // 将JSON字符串转换为Map对象
    def convertJsonToMap(String jsonString) {
        try {
            JsonSlurper slurper = new JsonSlurper()
            def result = slurper.parseText(jsonString)
            return result
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error converting JSON to Map: ${e.message}", e)
        }
    }

    // 将Map对象转换为JSON字符串
    def convertMapToJson(Map dataMap) {
        try {
            return dataMap as JSON
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error converting Map to JSON: ${e.message}", e)
        }
    }

    // 测试方法
    def test() {
        String jsonInput = '''{
            "key": "value"
        }'''
        println "Original JSON: ${jsonInput}"

        // 将JSON字符串转换为Map对象
        def mapResult = convertJsonToMap(jsonInput)
        println "Converted Map: ${mapResult}"

        // 将Map对象转换为JSON字符串
        def jsonResult = convertMapToJson(mapResult)
        println "Converted JSON: ${jsonResult}"
    }

}
