package org.fintech.paytech.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceAndUserFilter extends HttpFilter {

    private static final String TRACE_ID = "traceId";
    private static final String USER_ID = "userId";
    private static final String HEADER_TRACE_ID = "X-Trace-Id";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Get traceId from the request header, or generate a new one if missing
        String traceId = request.getHeader(HEADER_TRACE_ID);
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(TRACE_ID, traceId);

        // Retrieve userId from the security context
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : "ANONYMOUS";
        MDC.put(USER_ID, userId);*/

        try {
            chain.doFilter(request, response);  // Continue with the next filter in the chain
        } finally {
            // Remove traceId and userId from MDC after the request is processed
            MDC.remove(TRACE_ID);
            MDC.remove(USER_ID);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
