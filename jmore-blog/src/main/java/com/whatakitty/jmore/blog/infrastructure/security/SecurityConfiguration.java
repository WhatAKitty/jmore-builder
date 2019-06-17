package com.whatakitty.jmore.blog.infrastructure.security;

import com.whatakitty.jmore.web.security.SecurityConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * security configuration
 *
 * @author WhatAKitty
 * @date 2019/06/17
 * @description
 **/
@Configuration
public class SecurityConfiguration extends SecurityConfig.CustomizedAuthenticationConfiguration {

    private RESTAuthenticationEntryPoint authenticationEntryPoint;

    protected SecurityConfiguration(AuthenticationProvider authenticationProvider) {
        super(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(restAuthenticationEntryPoint());

        final CustomizedFormLoginConfigurer configurer;
        CustomizedFormLoginConfigurer existingConfig = http.getConfigurer(CustomizedFormLoginConfigurer.class);
        if (existingConfig != null) {
            configurer = existingConfig;
        } else {
            configurer = new CustomizedFormLoginConfigurer();
        }

        configurer
            .loginProcessingUrl("/security/auth")
            .successHandler(restAuthenticationSuccessHandler())
            .failureHandler(restAuthenticationFailureHandler());

        http.apply(configurer);
    }

    @Bean
    public RESTAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RESTAuthenticationEntryPoint();
    }

    @Bean
    public RESTAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
        return new RESTAuthenticationSuccessHandler();
    }

    @Bean
    public RESTAuthenticationFailureHandler restAuthenticationFailureHandler() {
        return new RESTAuthenticationFailureHandler();
    }

    private static class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            clearAuthenticationAttributes(request);
        }
    }

    private class RESTAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {

            super.onAuthenticationFailure(request, response, exception);
        }
    }

    private static class CustomizedUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        public CustomizedUsernamePasswordAuthenticationFilter() {
            super();
            setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/security/auth", "POST"));
        }

        @Override
        protected String obtainPassword(HttpServletRequest request) {
            return super.obtainPassword(request);
        }

        @Override
        protected String obtainUsername(HttpServletRequest request) {
            return super.obtainUsername(request);
        }
    }

    private static class CustomizedFormLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, CustomizedFormLoginConfigurer<H>, CustomizedUsernamePasswordAuthenticationFilter> {

        /**
         * Creates a new instance
         *
         * @see HttpSecurity#formLogin()
         */
        public CustomizedFormLoginConfigurer() {
            super(new CustomizedUsernamePasswordAuthenticationFilter(), null);
            usernameParameter("username");
            passwordParameter("password");
        }

        /**
         * <p>
         * Specifies the URL to send users to if login is required. If used with
         * {@link WebSecurityConfigurerAdapter} a default login page will be generated when
         * this attribute is not specified.
         * </p>
         *
         * <p>
         * If a URL is specified or this is not being used in conjuction with
         * {@link WebSecurityConfigurerAdapter}, users are required to process the specified
         * URL to generate a login page. In general, the login page should create a form that
         * submits a request with the following requirements to work with
         * {@link CustomizedUsernamePasswordAuthenticationFilter}:
         * </p>
         *
         * <ul>
         * <li>It must be an HTTP POST</li>
         * <li>It must be submitted to {@link #loginProcessingUrl(String)}</li>
         * <li>It should include the username as an HTTP parameter by the name of
         * {@link #usernameParameter(String)}</li>
         * <li>It should include the password as an HTTP parameter by the name of
         * {@link #passwordParameter(String)}</li>
         * </ul>
         *
         * <h2>Example login.jsp</h2>
         *
         * Login pages can be rendered with any technology you choose so long as the rules
         * above are followed. Below is an example login.jsp that can be used as a quick start
         * when using JSP's or as a baseline to translate into another view technology.
         *
         * <pre>
         * <!-- loginProcessingUrl should correspond to FormLoginConfigurer#loginProcessingUrl. Don't forget to perform a POST -->
         * &lt;c:url value="/login" var="loginProcessingUrl"/&gt;
         * &lt;form action="${loginProcessingUrl}" method="post"&gt;
         *    &lt;fieldset&gt;
         *        &lt;legend&gt;Please Login&lt;/legend&gt;
         *        &lt;!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error --&gt;
         *        &lt;c:if test="${param.error != null}"&gt;
         *            &lt;div&gt;
         *                Failed to login.
         *                &lt;c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}"&gt;
         *                  Reason: &lt;c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /&gt;
         *                &lt;/c:if&gt;
         *            &lt;/div&gt;
         *        &lt;/c:if&gt;
         *        &lt;!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout --&gt;
         *        &lt;c:if test="${param.logout != null}"&gt;
         *            &lt;div&gt;
         *                You have been logged out.
         *            &lt;/div&gt;
         *        &lt;/c:if&gt;
         *        &lt;p&gt;
         *        &lt;label for="username"&gt;Username&lt;/label&gt;
         *        &lt;input type="text" id="username" name="username"/&gt;
         *        &lt;/p&gt;
         *        &lt;p&gt;
         *        &lt;label for="password"&gt;Password&lt;/label&gt;
         *        &lt;input type="password" id="password" name="password"/&gt;
         *        &lt;/p&gt;
         *        &lt;!-- if using RememberMeConfigurer make sure remember-me matches RememberMeConfigurer#rememberMeParameter --&gt;
         *        &lt;p&gt;
         *        &lt;label for="remember-me"&gt;Remember Me?&lt;/label&gt;
         *        &lt;input type="checkbox" id="remember-me" name="remember-me"/&gt;
         *        &lt;/p&gt;
         *        &lt;div&gt;
         *            &lt;button type="submit" class="btn"&gt;Log in&lt;/button&gt;
         *        &lt;/div&gt;
         *    &lt;/fieldset&gt;
         * &lt;/form&gt;
         * </pre>
         *
         * <h2>Impact on other defaults</h2>
         *
         * Updating this value, also impacts a number of other default values. For example,
         * the following are the default values when only formLogin() was specified.
         *
         * <ul>
         * <li>/login GET - the login form</li>
         * <li>/login POST - process the credentials and if valid authenticate the user</li>
         * <li>/login?error GET - redirect here for failed authentication attempts</li>
         * <li>/login?logout GET - redirect here after successfully logging out</li>
         * </ul>
         *
         * If "/authenticate" was passed to this method it update the defaults as shown below:
         *
         * <ul>
         * <li>/authenticate GET - the login form</li>
         * <li>/authenticate POST - process the credentials and if valid authenticate the user
         * </li>
         * <li>/authenticate?error GET - redirect here for failed authentication attempts</li>
         * <li>/authenticate?logout GET - redirect here after successfully logging out</li>
         * </ul>
         *
         * @param loginPage the login page to redirect to if authentication is required (i.e.
         *                  "/login")
         * @return the {@link CustomizedFormLoginConfigurer} for additional customization
         */
        @Override
        public CustomizedFormLoginConfigurer<H> loginPage(String loginPage) {
            return super.loginPage(loginPage);
        }

        /**
         * The HTTP parameter to look for the username when performing authentication. Default
         * is "username".
         *
         * @param usernameParameter the HTTP parameter to look for the username when
         *                          performing authentication
         * @return the {@link CustomizedFormLoginConfigurer} for additional customization
         */
        public CustomizedFormLoginConfigurer<H> usernameParameter(String usernameParameter) {
            getAuthenticationFilter().setUsernameParameter(usernameParameter);
            return this;
        }

        /**
         * The HTTP parameter to look for the password when performing authentication. Default
         * is "password".
         *
         * @param passwordParameter the HTTP parameter to look for the password when
         *                          performing authentication
         * @return the {@link CustomizedFormLoginConfigurer} for additional customization
         */
        public CustomizedFormLoginConfigurer<H> passwordParameter(String passwordParameter) {
            getAuthenticationFilter().setPasswordParameter(passwordParameter);
            return this;
        }

        /**
         * Forward Authentication Failure Handler
         *
         * @param forwardUrl the target URL in case of failure
         * @return the {@link CustomizedFormLoginConfigurer} for additional customization
         */
        public CustomizedFormLoginConfigurer<H> failureForwardUrl(String forwardUrl) {
            failureHandler(new ForwardAuthenticationFailureHandler(forwardUrl));
            return this;
        }

        /**
         * Forward Authentication Success Handler
         *
         * @param forwardUrl the target URL in case of success
         * @return the {@link CustomizedFormLoginConfigurer} for additional customization
         */
        public CustomizedFormLoginConfigurer<H> successForwardUrl(String forwardUrl) {
            successHandler(new ForwardAuthenticationSuccessHandler(forwardUrl));
            return this;
        }

        @Override
        public void init(H http) throws Exception {
            super.init(http);
            initDefaultLoginFilter(http);
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configurers.
         * AbstractAuthenticationFilterConfigurer
         * #createLoginProcessingUrlMatcher(java.lang.String)
         */
        @Override
        protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
            return new AntPathRequestMatcher(loginProcessingUrl, "POST");
        }

        /**
         * Gets the HTTP parameter that is used to submit the username.
         *
         * @return the HTTP parameter that is used to submit the username
         */
        private String getUsernameParameter() {
            return getAuthenticationFilter().getUsernameParameter();
        }

        /**
         * Gets the HTTP parameter that is used to submit the password.
         *
         * @return the HTTP parameter that is used to submit the password
         */
        private String getPasswordParameter() {
            return getAuthenticationFilter().getPasswordParameter();
        }

        /**
         * If available, initializes the {@link DefaultLoginPageGeneratingFilter} shared
         * object.
         *
         * @param http the {@link HttpSecurityBuilder} to use
         */
        private void initDefaultLoginFilter(H http) {
            DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http
                .getSharedObject(DefaultLoginPageGeneratingFilter.class);
            if (loginPageGeneratingFilter != null && !isCustomLoginPage()) {
                loginPageGeneratingFilter.setFormLoginEnabled(true);
                loginPageGeneratingFilter.setUsernameParameter(getUsernameParameter());
                loginPageGeneratingFilter.setPasswordParameter(getPasswordParameter());
                loginPageGeneratingFilter.setLoginPageUrl(getLoginPage());
                loginPageGeneratingFilter.setFailureUrl(getFailureUrl());
                loginPageGeneratingFilter.setAuthenticationUrl(getLoginProcessingUrl());
            }
        }

    }

}
