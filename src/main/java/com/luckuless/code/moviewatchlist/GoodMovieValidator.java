package com.luckuless.code.moviewatchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem>{

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		
        if (StringUtils.isBlank(value.getRating())) {
            return true;
        }

		return !(Double.valueOf(value.getRating()) >= 8 &&  "L".equals(value.getPriority()));
	}
}