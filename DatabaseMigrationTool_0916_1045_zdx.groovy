// 代码生成时间: 2025-09-16 10:45:11
import grails.util.Environment
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.transaction.annotation.Transactional
import liquibase.Liquibase
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.Contexts
import liquibase.LabelExpression
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class DatabaseMigrationTool {
    
    private final Logger logger = LoggerFactory.getLogger(DatabaseMigrationTool.class)
    
    // 定义Liquibase的changeLog文件路径
    private static final String CHANGELOG_FILE = "classpath:db/changelog/db.changelog-master.xml"
    
    // 执行数据库迁移
    void migrateDatabase(String url, String username, String password) {
        try {
            // 创建Liquibase实例
            Liquibase liquibase = createLiquibase(url, username, password)
            
            // 根据changeLog文件路径更新数据库
            liquibase.update(null)
            
            logger.info("Database migration completed successfully.")
        } catch (Exception e) {
            logger.error("Database migration failed.", e)
            throw new RuntimeException("Database migration failed.", e)
        }
    }
    
    // 创建Liquibase实例
    private Liquibase createLiquibase(String url, String username, String password) {
        Database database = DatabaseFactory.getInstance().open(url, username, password)
        ClassLoaderResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor()
        
        return new Liquibase(CHANGELOG_FILE, resourceAccessor, database)
    }
    
    // 用于测试的main方法
    static void main(String[] args) {
        if (Environment.current == Environment.DEVELOPMENT) {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")
            DatabaseMigrationTool migrationTool = ctx.getBean(DatabaseMigrationTool.class)
            
            // 提供数据库连接信息
            String url = "jdbc:mysql://localhost:3306/your_database"
            String username = "your_username"
            String password = "your_password"
            
            migrationTool.migrateDatabase(url, username, password)
            
            ctx.close()
        } else {
            println "Database migration is not allowed in this environment."
        }
    }
}
