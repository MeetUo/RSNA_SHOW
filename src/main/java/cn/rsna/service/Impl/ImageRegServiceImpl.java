package cn.rsna.service.Impl;

import cn.rsna.dao.UserPicMapper;
import cn.rsna.entity.LocaResult;
import cn.rsna.entity.UserPic;
import cn.rsna.service.IImageRegService;
import cn.rsna.utils.ImageProcess;
import cn.rsna.utils.ImageToBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageRegServiceImpl implements IImageRegService {
    @Autowired
    private UserPicMapper userPicMapper;
    private LocaResult locaResult;
    private static ImageToBox imageToBox;
    public ImageRegServiceImpl(){
        String rootPath = this.getClass().getResource("/").getPath();
        rootPath = rootPath.replaceAll("%20", " ");//空格转换的问题，显示的问题。 
        String webInfoPath = new File(rootPath).getParent();// WEB-INF 目录的物理路径
        String filepath = new File(webInfoPath).getParent();// WebRoot 目录的物理路径
        filepath = filepath.replaceAll("\\\\", "/")+"/model";
        File file = new File(filepath);
        if (!file.exists()){
            file.mkdir();
        }
        try{
            imageToBox = new ImageToBox(filepath+"/model_best.pb");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void add( List<LocaResult> res,String username,String path){
        UserPic userPic = new UserPic();
        userPic.setPath(path);
        userPic.setUsername(username);
        String xmin = "";
        String ymin = "";
        String xmax = "";
        String ymax = "";
        for (LocaResult locaResult : res) {
            if (xmin.length()==0) xmin = xmin+locaResult.getLeft_x();
            else xmin = xmin+" "+locaResult.getLeft_x();
            if (ymin.length()==0) ymin = ymin+locaResult.getLeft_y();
            else ymin = ymin+" "+locaResult.getLeft_y();
            if (xmax.length()==0) xmax = xmax+locaResult.getRight_x();
            else xmax = xmax+" "+locaResult.getRight_x();
            if (ymax.length()==0) ymax = ymax+locaResult.getRight_y();
            else ymax = ymax+" "+locaResult.getRight_y();
        }
        userPic.setXmin(xmin);
        userPic.setYmin(ymin);
        userPic.setXmax(xmax);
        userPic.setYmax(ymax);
        userPicMapper.insert(userPic);
    }
    @Override
    public List<LocaResult> getRes(File file,String username,String path){
        List<LocaResult> res = new ArrayList<>();
        try{
            float[][][] pred = imageToBox.predict(ImageProcess.readImage(file));
            for (int j =0;j<200;j++)
                if (pred[0][j][1]>0.35){
                    LocaResult x = new LocaResult();
                    x.setConf(Double.valueOf(pred[0][j][1]));
                    x.setLeft_x(Double.valueOf(pred[0][j][2]));
                    x.setLeft_y(Double.valueOf(pred[0][j][3]));
                    x.setRight_x(Double.valueOf(pred[0][j][4]));
                    x.setRight_y(Double.valueOf(pred[0][j][5]));
                    res.add(x);
                }
        }catch (Exception e){
            System.out.println(e);
        }
        add(res,username,path);
        return res;
    }
    public LocaResult getRes(){
        locaResult = new LocaResult();
        locaResult.setLeft_x(20.0);
        locaResult.setLeft_y(50.0);
        locaResult.setRight_x(100.0);
        locaResult.setRight_y(100.0);
        return locaResult;
    }
}
