package org.example.graduationprojectbackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlaskService {

    /**
     * 调用Flask API接口进行预测
     *
     * @param file  上传的文件
     * @param params  预测所需的参数
     * @return Flask API返回的预测结果
     */
    ResponseEntity<String> predict(MultipartFile file, String params) throws Exception;
}
