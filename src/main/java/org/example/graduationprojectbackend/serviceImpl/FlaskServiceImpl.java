package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.service.FlaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlaskServiceImpl implements FlaskService {

    @Value("${flask.api.url}")
    private String flaskApiUrl;  // 设置Flask API的URL

    private final RestTemplate restTemplate;

    public FlaskServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> predict(MultipartFile file, String params) throws Exception {
        // 打印上传的文件信息
        System.out.println("File uploaded: " + file.getOriginalFilename());
        System.out.println("Params received: " + params);

        // 处理参数，将其转为 float 列表
        List<Float> paramList = convertParamsToFloatList(params);

        // 发送请求到 Flask 后端
        return forwardToFlask(file, paramList);
    }

    // 转换逗号分隔的字符串为 Float 列表
    private List<Float> convertParamsToFloatList(String params) {
        List<Float> paramList = new ArrayList<>();
        String[] paramArray = params.split(",");

        for (String param : paramArray) {
            try {
                paramList.add(Float.parseFloat(param));  // 将字符串转为 float 并添加到列表
            } catch (NumberFormatException e) {
                System.out.println("Invalid float value: " + param);  // 如果转换失败，打印错误信息
            }
        }

        return paramList;
    }

    // 将文件和参数发送到 Flask 接口
    private ResponseEntity<String> forwardToFlask(MultipartFile file, List<Float> paramList) throws Exception {
        System.out.println("paramList"+paramList.toString());

        // 创建RestTemplate实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置HTTP头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建MultiValueMap，用于存储multipart数据
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // 添加文件到请求体
        body.add("files", file.getResource());

        // 添加参数列表到请求体
        int index = 0;
        for (Float param : paramList) {
            body.add("param" + index++, param.toString());
        }

        // 将请求体和头信息封装为HttpEntity对象
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // 发送POST请求到Flask服务
        ResponseEntity<String> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, entity, String.class);

        return response;
    }
}
