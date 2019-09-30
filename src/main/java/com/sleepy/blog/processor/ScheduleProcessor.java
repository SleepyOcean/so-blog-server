package com.sleepy.blog.processor;

import com.sleepy.blog.util.CommandUtil;

import java.io.IOException;

/**
 * 定时任务处理器
 *
 * @author gehoubao
 * @create 2019-09-30 14:11
 **/
public class ScheduleProcessor {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(CommandUtil.execute("mysqldump"));
    }

    /**
     * 备份文章数据
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void backupArticle() throws IOException, InterruptedException {
        String command = "mysqldump -h localhost -uroot -p123456 --databases test > backup.sql";
        String result = CommandUtil.execute(command);
    }
}