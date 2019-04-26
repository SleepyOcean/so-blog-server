package com.sleepy.blog.util;

import java.io.*;

/**
 * 文件工具类
 *
 * @author gehoubao
 * @create 2019-04-26 10:16
 **/
public class FileUtil {

    /**
     * 文本写入文件操作类
     */
    static class StringWriter {
        File file;
        FileOutputStream fos;

        public StringWriter(String filePath) {
            file = new File(filePath);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void writeStringToFile(String content) {
            try {
                fos.write(content.getBytes());
                fos.write("\r\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void printContent() {
            try {
                InputStream input = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));
                String line = null;
                // 按行读取文本，直到末尾（一般都这么写）
                while ((line = reader.readLine()) != null) {
                    // 打印当前行字符串
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}