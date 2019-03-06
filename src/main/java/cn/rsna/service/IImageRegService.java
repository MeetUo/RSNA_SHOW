package cn.rsna.service;

import cn.rsna.utils.LocaResult;
import cn.rsna.utils.LocalRequest;
import cn.rsna.utils.RSNAResult;

import java.io.File;
import java.util.List;

public interface IImageRegService {
    public LocaResult getRes();
    public RSNAResult getRes(File file, String username, String path);
    public boolean addNewRes(LocalRequest localRequest);
    public List<String> getImageByUsername(String username);
}
