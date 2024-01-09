package com.stocklisting.favourite.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FavStockFilter extends GenericFilter {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${app.jwttoken.message}")
	private String message;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException

	{

		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponse httpres = (HttpServletResponse) response;

		httpres.setHeader("Access-Control-Allow-Origin", "*");
		httpres.setHeader("Access-Control-Allow-Headers", "*");
		httpres.setHeader("Access-Control-Allow-Credentials", "true");
		httpres.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");

		// to handle preflight request for the first time which is raised by webbrowser
		// ,when UI is based on script

		if (httpreq.getMethod().equals(HttpMethod.OPTIONS.name())) {
			chain.doFilter(httpreq, httpres);
		} else {

			String headerinfo = httpreq.getHeader("Authorization");

			System.out.println(headerinfo);

			if ((headerinfo == null) || (!headerinfo.startsWith("Bearer"))) {
				throw new ServletException("JWT Token is missing");
			}

			String mytoken = headerinfo.substring(7);

			try {
				//System.out.println(secret);
				//JwtParser jwtparser = Jwts.parser().setSigningKey("nenQ9D2agbLtig/HEzSxvM7VHrqzsdvSskTPcQIZkah7EcQk7UonJde1qVRTUbU2RwLcnifDqKMk+kf6rLPdmm7Jr6bA0oRrgqEja3h+PGl5XOdunSf0pC2yYErGm8iJA7ZHeua4jgUbdCruvqmRu4Vxao/b3o7iUTSa8tm3pDtDU7KGhrthuOsUolUDxi+LWkx+WEiK23Wl+qlpaCqlcDKHl7f1qH1W+iClH6p+oIGDnq342k01PZcFQr64BtBLBN8Vio82awHpgY0L/cF5AXPZcNa0bNJjcjQ1FQkda4owErhiCcejb4Hps1+LaWEVTqBVRZTTYhnxR/i7WlWjjcG91HAFhbpEKsc9S3YE5uU=");
				JwtParser jwtparser = Jwts.parser().setSigningKey("5A971D3D4754E2451EE4DC454F03FF917C85CD23E4C0F5C12F01DDF110165958");
				System.out.println(jwtparser);
				Jwt jwtobj = jwtparser.parse(mytoken);
				Claims claimobj = (Claims) jwtobj.getBody();

				System.out
						.println("User logged in " + claimobj.getSubject() + " his role is " + claimobj.getAudience());

				HttpSession sess = httpreq.getSession();
				sess.setAttribute("username", claimobj.getSubject());
			} catch (SignatureException e) {
				throw new ServletException("Invalid siganature / token ");
			} catch (MalformedJwtException e) {
				throw new ServletException("Someone illegally modified token");

			}

			chain.doFilter(httpreq, httpres);
		}

	}

}
