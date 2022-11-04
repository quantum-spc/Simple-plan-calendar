package shop.qtmspc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.qtmspc.interceptor.LogInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 로그 인터셉터 세팅
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "*.ico", "/error"); //오류 페이지 경로
    }


}
