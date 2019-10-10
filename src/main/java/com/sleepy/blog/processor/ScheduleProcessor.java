package com.sleepy.blog.processor;

import com.sleepy.blog.util.CommandUtil;
import com.sleepy.blog.util.DateUtil;
import com.sleepy.blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * 定时任务处理器
 *
 * @author gehoubao
 * @create 2019-09-30 14:11
 **/
@Component
public class ScheduleProcessor {

    @Value("${spring.datasource.username}")
    private String username = "root";
    @Value("${spring.datasource.password}")
    private String password = "123456";

    public static void main(String[] args) throws IOException, InterruptedException {
        new ScheduleProcessor().backupData("test", "so_project", "SoProject");
    }

    /**
     * 备份数据
     *
     * @param dbName         需要备份的数据库名称
     * @param tableName      需要备份的表名称
     * @param tableClassName 定义备份表对应的类名称
     * @throws IOException
     * @throws InterruptedException
     */
    private void backupData(String dbName, String tableName, String tableClassName) throws IOException, InterruptedException {
        String now = DateUtil.dateFormat(new Date(), DateUtil.DATE_PATTERN_POINT);
        String path = getStoreSqlPath() + "V" + now + "__" + tableClassName;
        String command = "mysqldump -h localhost -u" + username + " -p" + password + " --databases " + dbName + " --tables " + tableName + " -r " + path + ".sql";
        String result = CommandUtil.execute(command);
        System.out.println(result);
    }

    /**
     * 获取flyway的路径
     *
     * @return
     * @throws IOException
     */
    private String getStoreSqlPath() throws IOException {
        return FileUtil.getProjectPath() + "\\src\\main\\resources\\db\\migration\\";
    }
}