package by.bsu.restaurant.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * Changing the encoding of the request and response to the encoding specified filter parameter
 * @see Filter
 */
public class EncodingFilter implements Filter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }



    @Override
    public void destroy() {
        code = null;
    }
}
