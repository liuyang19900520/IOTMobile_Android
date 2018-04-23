package com.liuyang19900520.iotmobile_android.model.bean;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liuya
 */
@Data
@AllArgsConstructor
public class TestApi {


    private String category;
    private List<Api> apis;

    public TestApi() {

    }


    @Data
    @AllArgsConstructor
    public static class Api {
        private String name;
        private String url;
        private Map<String, Object> params;
    }


}
