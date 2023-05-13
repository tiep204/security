package ra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class webSercurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // Tạo ra user trong bộ nhớ
        // lưu ý, chỉ sử dụng cách này để minh họa
        // Còn thực tế chúng ta sẽ kiểm tra user trong csdl
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withDefaultPasswordEncoder() // Sử dụng mã hóa password đơn giản
                        .username("loda")
                        .password("loda")
                        .roles("user") // phân quyền là người dùng.
                        .build()
        );
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("admin")
                        .build()
        );
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http

                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("user").hasRole("user")
                .antMatchers("admin").hasRole("admin")
                .anyRequest().authenticated()
                .and().formLogin();*/
 /*       http
                .cors().and()
                .csrf().disable()   .authorizeRequests()
                .antMatchers("/admin").access("hasRole('admin')");*/
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/user").hasRole("user")
                .antMatchers("/admin").hasRole("admin")// Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login

                .and()
                .logout() // Cho phép logout
                ;
    }
}
