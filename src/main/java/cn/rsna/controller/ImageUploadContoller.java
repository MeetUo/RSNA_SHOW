package cn.rsna.controller;


import cn.rsna.service.IImageRegService;
import cn.rsna.utils.LocaResult;
import cn.rsna.utils.LocalRequest;
import cn.rsna.utils.RSNAResult;
import cn.rsna.utils.SaveImage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/image")
public class ImageUploadContoller {
    @Autowired
    IImageRegService imageRegService;

    @RequestMapping(value = "/test.do", method = RequestMethod.POST)
    @ResponseBody
    public RSNAResult handleReques(@RequestParam(value = "myImage",required = false)MultipartFile file ,HttpServletRequest request) throws Exception {
        System.out.println(file);
        return RSNAResult.ok();
    }

    @RequestMapping(value = "/imageUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public RSNAResult handleRequest(@RequestParam(value = "myImage",required = false)MultipartFile Imagefile, HttpServletRequest request) throws Exception {
        String urlPrefix = "ImageData/";
        String upLoadPath = request.getServletContext().getRealPath("/") +urlPrefix;
        String username =(String) request.getAttribute("username");
        if (username==null) return RSNAResult.build(123,"login first");
        File file = SaveImage.getImageFile(Imagefile,upLoadPath);
        if(file!=null)
            return imageRegService.getRes(file,username,SaveImage.getUrl(file,urlPrefix));
        return RSNAResult.build(123,"image update fail");
    }
    @RequestMapping(value = "/imageUpdate.do",  method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RSNAResult imageUpdate(@RequestBody LocalRequest localRequest) throws Exception {
        if (imageRegService.addNewRes(localRequest)) return RSNAResult.ok();
        else return RSNAResult.build(123,"update fail 请确认该张图片是否已经由系统检测过肺炎");
    }

    @RequestMapping(value = "/imageHistory.do",  method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RSNAResult imageHistory(@RequestParam(value = "username")String username) throws Exception {
        List<String> data = imageRegService.getImageByUsername(username);
        if (data==null){
            return RSNAResult.build(123,"该用户暂无图片上传");
        }
        else return RSNAResult.ok(data);
    }
}
