package com.thuannd.service.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thuannd.entity.User;
import com.thuannd.model.MyPrincipal;

public class AuditorAwareImpl implements AuditorAware<com.thuannd.entity.User> {

	@Override
	public Optional<com.thuannd.entity.User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.ofNullable(null);
		}
		MyPrincipal currentUser = (MyPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(new User(currentUser.getId()));
	}

}
