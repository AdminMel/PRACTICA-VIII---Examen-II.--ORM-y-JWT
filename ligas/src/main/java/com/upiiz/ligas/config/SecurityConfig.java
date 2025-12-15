import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      // ✅ API stateless con JWT: CSRF off
      .csrf(csrf -> csrf.disable())

      // ✅ CORS en Spring Security (si no, bloquea el preflight)
      .cors(Customizer.withDefaults())

      // ✅ stateless
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

      // ✅ Reglas
      .authorizeHttpRequests(auth -> auth
        // Preflight
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        // Auth público
        .requestMatchers("/api/auth/**").permitAll()

        // Swagger público
        .requestMatchers(
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/swagger-ui.html"
        ).permitAll()

        // Todo lo demás protegido
        .anyRequest().authenticated()
      );

    // ✅ Si tienes filtro JWT, va ANTES del UsernamePasswordAuthenticationFilter
    // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
