package cn.rsna.service.Impl;

import cn.rsna.entity.LocaResult;
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
    @Override
    public List<LocaResult> getRes(File file){
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
