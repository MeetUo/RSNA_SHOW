package cn.rsna.controller;


import cn.rsna.service.IImageRegService;
import cn.rsna.utils.LocaResult;
import cn.rsna.utils.LocalRequest;
import cn.rsna.utils.RSNAResult;
import cn.rsna.utils.SaveImage;
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
        else return RSNAResult.build(123,"update fail");
    }
}
