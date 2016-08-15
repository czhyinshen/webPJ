package com.unicomWeb.controller;

import com.unicomWeb.model.FileMeta;
import com.unicomWeb.model.ResponseMeta;
import com.unicomWeb.model.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by York on 2016/8/3.
 */

@Controller
@RequestMapping("/controller")
public class FileUploadController {

	private FileMeta fileMeta = null;
    private ResponseMeta responseMeta = null;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMeta upload(MultipartHttpServletRequest request)
    		throws IllegalStateException, IOException {
        //通用多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iter = multiRequest.getFileNames();
            MultipartFile mpf = null;
            ResponseMeta responseMeta = new ResponseMeta();

            while (iter.hasNext()){
                mpf = multiRequest.getFile(iter.next());
                if (mpf!=null){
                    String nowFileName = mpf.getOriginalFilename();
                    if (nowFileName.trim() != ""){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        java.util.Date date=new java.util.Date();
                        String fileName = "Upload" + mpf.getOriginalFilename();
                        String path = "E:/tmp/files/"+ sdf.format(date) + fileName ;
                        File localFile = new File(path);

                        try {
                            mpf.transferTo(localFile);
                        }catch (IOException e){
                            responseMeta.setStatusCode(StatusCode.ERROR);
                        }
                        responseMeta.setStatusCode(StatusCode.OK);

                    }
                    FileMeta fileMeta = new FileMeta();
                    fileMeta.setFileName(mpf.getOriginalFilename());
                    fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
                    fileMeta.setFileType(mpf.getContentType());
//                files.add(fileMeta);
                }
            }
            //返回数据
            responseMeta.setData(fileMeta);
        }
        return responseMeta;
    }
}
