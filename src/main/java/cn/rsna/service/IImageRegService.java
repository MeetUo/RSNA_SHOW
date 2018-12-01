package cn.rsna.service;

import cn.rsna.entity.LocaResult;

import java.io.File;
import java.util.List;

public interface IImageRegService {
    public LocaResult getRes();
    public List<LocaResult> getRes(File file,String username,String path);
}
