### nginx config

```
upstream app1 {
        server 127.0.0.1:8080;
}
server {
        listen       80;
        server_name  app1.com;
        location / {
                proxy_pass         http://app1;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        }
        access_log /home/amen/logs/nginx/cors-app1-access.log;
        error_log  /home/amen/logs/nginx/cors-app1-error.log;
}


upstream app2 {
        server 127.0.0.1:8081;
}
server {
        listen       80;
        server_name  app2.com;
        location / {
                proxy_pass         http://app2;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        }
        access_log /home/amen/logs/nginx/cors-app2-access.log;
        error_log  /home/amen/logs/nginx/cors-app2-error.log;
}
```

### host config
```
127.0.0.1 app1.com
127.0.0.1 app2.com
```



###注意
```java
//如下配置当在拦截器中返回false时存在冲突，应尽量使用过滤器的方式解决
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/cors/**")
            .allowedHeaders(CorsConfiguration.ALL)
            .allowedMethods(CorsConfiguration.ALL)
            .allowCredentials(true);

        registry.addMapping("/api/**")
            .allowedOrigins("https://domain2.com")
            .allowedMethods("PUT", "DELETE")
            .allowedHeaders("header1", "header2", "header3")
            .exposedHeaders("header1", "header2")
            .allowCredentials(true).maxAge(3600);

    }

}

//推荐配置
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private RequiredLoginInterceptor requiredLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requiredLoginInterceptor);
    }

    @Bean
    public FilterRegistrationBean  corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    public MvcConfig(RequiredLoginInterceptor requiredLoginInterceptor) {
        this.requiredLoginInterceptor = requiredLoginInterceptor;
    }
}
```
