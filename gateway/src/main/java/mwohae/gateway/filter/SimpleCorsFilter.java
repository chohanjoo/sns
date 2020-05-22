package mwohae.gateway.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age","3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-auth-token, content-type");

        chain.doFilter(request,response);
    }

    public void init(FilterConfig filterConfig){}

    public void destroy() {}

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
