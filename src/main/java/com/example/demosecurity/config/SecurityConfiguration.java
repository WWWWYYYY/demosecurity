package com.example.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/index").permitAll()//index路径免认证
//                .anyRequest().authenticated()
//                .and().formLogin()//httpbasic方式认证
//                .and().csrf().disable();//CSRF（Cross-site request forgery），中文名称：跨站请求伪造，也被称为：one click attack/session riding，缩写为：CSRF/XSRF。
//        super.configure(http);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).roles("USER","ADMIN")
//                .and()
//        .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
//    }


    /**
     * 配置编码器
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//配置权限
                .antMatchers("/order/**").hasRole("ADMIN")
                .anyRequest().authenticated()//任意请求需要登录
                .and()
                .formLogin()//开启formLogin默认配置
                .loginPage("/login")//请求时未登录跳转接口  spring security 提供了默认的登录页
                .failureUrl("/login?error=true")//用户密码错误跳转接口
//                .failureHandler(new FailureAuthenticationHandler()) //如果需要复杂的业务处理失败的情况，可配置failhandle
//                .defaultSuccessUrl("/index", true)//登录成功跳转接口
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");

                        RequestCache cache = new HttpSessionRequestCache();
                        SavedRequest savedRequest = cache.getRequest(request, response);
                        String url = savedRequest.getRedirectUrl();

                        response.sendRedirect(url);


                    }
                })

//                .loginProcessingUrl("/login")//post登录接口，登录验证由系统实现
//                .usernameParameter("username")    //要认证的用户参数名，自定义登录页面时参数名设置，默认username
//                .passwordParameter("password")    //要认证的密码参数名，自定义登录页面时参数名设置默认password
                .permitAll()
                .and()
                .logout()//配置注销
                .logoutUrl("/logout")//注销接口
                .logoutSuccessUrl("/login").permitAll()//注销成功跳转接口 注销后返回首页或者登录页
                .and()
                .csrf().disable()//禁用csrf
                .exceptionHandling().accessDeniedPage("/error403");//拒绝访问时页面跳转


                //session管理,失效后跳转
                http.sessionManagement().invalidSessionUrl("/login");
                //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
                //http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
                //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
                http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
                //退出时情况cookies
                http.logout().deleteCookies("JESSIONID");//根据实际进行定义
                //解决中文乱码问题
//                CharacterEncodingFilter filter = new CharacterEncodingFilter();
//                filter.setEncoding("UTF-8"); filter.setForceEncoding(true);
                //
//                http.addFilterBefore(filter,CsrfFilter.class);

        http.headers().frameOptions().disable(); //使用 springsecurity + h2database 必须要配置csrf().disable()和 http.headers().frameOptions().disable()
    }
}
