package com.yf.auth.config;

import com.yf.auth.security.CustomAccessDeniedHandler;
import com.yf.auth.security.JwtAuthenticationTokenFilter;
import com.yf.auth.security.MyAuthenticationFailureHandler;
import com.yf.auth.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Package com.yf.config
 * @Description: TODO
 * @author: jingh
 * @date 2018/9/21 10:13
 *
 * 通过@EnableWebSecurity注解开启Spring Security的功能
继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
configure(HttpSecurity http)方法
通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
例如以上代码指定了/和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证。
通过formLogin()定义当需要用户登录时候，转到的登录页面。
 */
@Configuration
@EnableWebSecurity  //启动web安全性
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启方法级的权限注解  性设置后控制器层的方法前的@PreAuthorize("hasRole('admin')") 注解才能起效
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     *  解决 无法直接注入 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()          // 定义当需要用户登录时候，转到的登录页面。
                    .loginPage("/auth/toLogin")      // 设置登录页面
                    .loginProcessingUrl("/auth/login")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)// 自定义的登录接口
                    .and()
                .authorizeRequests()
                    // 允许对于网站静态资源的无授权访问
                    .antMatchers(
                            HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    // 对于获取token的rest api要允许匿名访问
                    .antMatchers("/auth/**").permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest()
                    .access("@authorityRBACService.hasPermission(request,authentication)") ;// RBAC 动态 url 认证
        //security  之前 检测token
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 无权访问处理
        httpSecurity.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
