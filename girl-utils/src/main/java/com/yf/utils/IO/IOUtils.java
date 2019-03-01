package com.yf.utils.IO;

import java.io.*;

/**
 * @Package com.yf.utils.IO
 * @Description: IO 流操作
 * @author: jingh
 * @date 2019/2/28 10:23
 */
public class IOUtils {


    /**
     * @Description: 使用 ObjectOutputStream 对象实现序列化 至文件
     * @auther: jinghan
     * @date: 2019/2/28 10:25
     *
     * 这是二进制文件，计算机能读懂就行了
     */
    public static Boolean objToFile(String filePath,Object obj){
        try {
            OutputStream outputStream = new FileOutputStream(filePath);
            ObjectOutputStream ops = new ObjectOutputStream(outputStream);
            ops.writeObject(obj);
            ops.close();

        } catch (FileNotFoundException e) {
           return false;
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    /**
     * @Description: 使用ObjectInputStream 对象实现反序列化
     * @auther: jinghan
     * @date: 2019/2/28 10:28
     */

    public static Object fileToObj(String filePath){
        try {
            InputStream in = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(in);
            Object obj = (Object) os.readObject();
            os.close();
            return obj;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }
}
