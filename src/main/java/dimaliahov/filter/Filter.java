package dimaliahov.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {
	FilterConfig filterConfig = null;

	@Override
	public void init (FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if (filterConfig.getInitParameter("access").equalsIgnoreCase("true")) {
			String servletPath = request.getServletPath();

			String s = (String)(session.getAttribute("role"));

			if (servletPath.equalsIgnoreCase("/login")
					|| servletPath.equalsIgnoreCase("/home")
					|| servletPath.equalsIgnoreCase("/shop")
					|| servletPath.equalsIgnoreCase("/singleproduct")
					|| servletPath.equalsIgnoreCase("/registration")
					|| servletPath.equalsIgnoreCase("/cart*")
					|| servletPath.equalsIgnoreCase("/source/*")) {
				filterChain.doFilter(request, response);

			} else if (s==null) {
				response.sendRedirect("/home");
			}else if (s.equals("admin")) {
				filterChain.doFilter(request, response);
			} else {
				response.sendRedirect("/home");
			}
		}
	}

	@Override
	public void destroy () {

	}
}
