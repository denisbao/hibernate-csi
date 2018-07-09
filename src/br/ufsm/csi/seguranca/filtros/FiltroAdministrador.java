package br.ufsm.csi.seguranca.filtros;

import br.ufsm.csi.seguranca.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.admin")
public class FiltroAdministrador implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Usuario a = (Usuario) request.getSession().getAttribute("userLoggedIn");

        if (a == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/spring-teste");
        }
        else if (!a.isAdmin()) {
            ((HttpServletResponse) servletResponse).sendRedirect("getAdminErro.priv");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

