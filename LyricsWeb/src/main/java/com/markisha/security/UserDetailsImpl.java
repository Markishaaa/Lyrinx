package com.markisha.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.Korisnik;

public record UserDetailsImpl(String username, @JsonIgnore String password, GrantedAuthority authority)
		implements UserDetails {

	public static UserDetailsImpl build(Korisnik user) {
		try {
			
			GrantedAuthority authority = new SimpleGrantedAuthority("USER_" + user.getUloga().toString());

			return new UserDetailsImpl(user.getUsername(), user.getPassword(), authority);
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl that = (UserDetailsImpl) o;
		return username.equals(that.username) && password.equals(that.password) && authority.equals(that.authority);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password, authority);
	}
}