package com.zyzh.MyFilter;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {
    /**
     * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中，
     * 如果不存在使用用户查询接口查询出用户对象并设置在Session中
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // _const_cas_assertion_是CAS中存放登录用户名的session标志
        //获取CAS服务端设置的用户信息
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Object object = httpRequest.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (object != null) {
            //获取用户信息
            Assertion assertion = (Assertion) object;
            //获取用户名
            String loginName = assertion.getPrincipal().getName();
            System.out.println(loginName + "==========");
            //业务系统可通过上面获取的用户名进行本系统的认证处理
        } else {
            System.out.println("null Name");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
