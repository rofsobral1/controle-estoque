package com.gestao.estoque_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioDetailsService usuarioDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Use getServletPath() para pegar o path correto mesmo com contexto
        String path = request.getServletPath();

        // ✅ IGNORA ROTAS PÚBLICAS E PRE-FLIGHT OPTIONS
        if (path.startsWith("/api/auth") ||
            path.startsWith("/api/recuperacao-senha") ||
            "OPTIONS".equalsIgnoreCase(request.getMethod())) {

            filterChain.doFilter(request, response);
            return;
        }

        // Captura o token do header Authorization
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            // Extrai email do token
            String email = jwtService.extractEmail(token);

            // Carrega usuário do banco
            var userDetails = usuarioDetailsService.loadUserByUsername(email);

            // Valida token
            if (jwtService.validateToken(token, userDetails.getUsername())) {
                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Seta a autenticação no contexto
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Erro ao validar token JWT", e);
            // Caso dê erro no token, não bloqueia aqui; só não autentica
        }

        // Continua o filtro
        filterChain.doFilter(request, response);
    }
}
