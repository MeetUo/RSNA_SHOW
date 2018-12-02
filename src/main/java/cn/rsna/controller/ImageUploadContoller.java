package cn.rsna.controller;


import cn.rsna.service.IImageRegService;
import cn.rsna.utils.LocaResult;
import cn.rsna.utils.LocalRequest;
import cn.rsna.utils.RSNAResult;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value="/image")
public class ImageUploadContoller {
    @Autowired
    IImageRegService imageRegService;
    @RequestMapping(value = "/imageUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public RSNAResult handleRequest(@RequestParam("myImage")MultipartFile Imagefile, HttpServletRequest request) throws Exception {
        String upLoadPath = request.getServletContext().getRealPath("/") +"ImageData/";
        String username =(String) request.getAttribute("username");
        if (username==null) return RSNAResult.build(123,"login first");
//        System.out.println(username+"image user");
        File dir = new File(upLoadPath);
        if (!dir.exists())
        {
            dir.mkdir();
        }
        //0，判断是否为空
        if(Imagefile!=null && !Imagefile.isEmpty())
        {
            /**
             * 对文件名进行操作防止文件重名
             */
            //1，获取原始文件名
            String originalFilename = Imagefile.getOriginalFilename();
            //2,截取源文件的文件名前缀,不带后缀
            String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
            //3,加工处理文件名，原文件加上时间戳
            String newFileNamePrefix  = fileNamePrefix + System.currentTimeMillis();
            //4,得到新文件名
            String newFileName = newFileNamePrefix + originalFilename.substring(originalFilename.lastIndexOf("."));
            //5,构建文件对象
            File file = new File(upLoadPath + newFileName);
            //6,执行上传操作
            try {
                Imagefile.transferTo(file);
                //上传成功，向jsp页面发送成功信息
                return imageRegService.getRes(file,username,file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return RSNAResult.ok();
    }
    @RequestMapping(value = "/imageUpdate.do",  method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RSNAResult imageUpdate(@RequestBody LocalRequest localRequest) throws Exception {
        if (imageRegService.addNewRes(localRequest)) return RSNAResult.ok();
        else return RSNAResult.build(123,"update fail");
    }
}
