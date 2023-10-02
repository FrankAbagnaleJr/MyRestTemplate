第三方远程调用

第一步：定义Bean

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }

第二步：远程调用
    
    restTemplate.exchange(url, HttpMethod.POST, null, String.class);

    远程调用此url
    url：是地址
    HttpMethod.POST：是请求方式   
    requestEntity：是请求参数
    String.class：是返回的类型


    
    
    
