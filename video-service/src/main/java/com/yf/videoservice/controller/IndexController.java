package com.yf.videoservice.controller;

import com.yf.videoservice.config.MyConst;
import com.yf.videoservice.utils.FileUtil;
import com.yf.videoservice.utils.R;
import com.yf.videoservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Package com.yf.videoservice.controller
 * @Description: TODO
 * @author: jingh
 * @date 2019/3/15 22:50
 */
@RestController
public class IndexController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MyConst myConst;

    @GetMapping("/video")
    public R video(String path) {
//        获取所有视频文件
        if(path ==null){
            path = myConst.getDownloadpath();
        }
        List<File> list = new ArrayList<>();
        FileUtil.getVideoList(list,path);
        List<Map<String,String>> listMap = new ArrayList<>();
        for(File file:list){
            Map<String,String> map = new HashMap<>();
            map.put("name",file.getName());
            map.put("url",file.getPath().replace(myConst.getDownloadpath(),""));
            listMap.add(map);
        }
        return R.ok().put("file",listMap);
    }
    /**
     * @param: 获取文件夹
     * @return:
     * @auther: jingh
     * @date: 2019/3/20 15:32
     */
    @GetMapping("/folder")
    public R folder(@Param("path") String path){
        if(path ==null || path.equals("")){
            path = myConst.getDownloadpath();
        }
        File dir = new File(path);
        File[] files = dir.listFiles();
        List<Map<String,Object>> listMap = new ArrayList<>();
        for(File file:files){
            Map<String,Object> map = new HashMap<>();
            map.put("name",file.getName());
            map.put("isDir",!file.isDirectory());
            map.put("url",file.getPath());
            map.put("file",file.getPath().replace(myConst.getDownloadpath(),""));
            listMap.add(map);
        }
        return R.ok().put("file",listMap);
    }
    /**
     * 删除文件
     * @param:
     * @return:
     * @auther: jingh
     * @date: 2019/3/20 17:00
     */
    @GetMapping("/delFile")
    public R delFile(@Param("path") String path){
        if(path == null){
            R.error("删除失败");
        }
        return R.ok("删除成功").put("flag", FileUtil.delFile(path));
    }


}
