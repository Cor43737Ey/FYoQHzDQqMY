// 代码生成时间: 2025-10-05 02:38:28
class TestCase {
    String name
    String description
    Date createdDate
    Date lastUpdated
    
    static constraints = {
        name(blank: false, unique: true)
        description(blank: false)
        createdDate(nullable: true)
        lastUpdated(nullable: true)
    }
    
    /***
     * 验证测试用例信息是否完整
     * @return 验证结果
     */
    boolean validate() {
        if (name) {
            return true
        }
        
        false
    }
}
