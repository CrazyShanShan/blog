package org.example.amin.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @Description: "配置MybatisPlus"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Configuration
@MapperScan("org.example.admin.dao.mapper")
public class MybatisPlusConfig {

    // 分页插件
    /**
     * @Author: ZBZ
     * @Date: 2021/8/12
    
     * @return: com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
