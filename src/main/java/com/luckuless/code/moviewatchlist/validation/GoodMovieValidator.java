package com.luckuless.code.moviewatchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.luckuless.code.moviewatchlist.domain.WatchlistItem;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem>{

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		
        if (StringUtils.isBlank(value.getRating())) {
            return true;
        }

		return !(Double.valueOf(value.getRating()) >= 8 &&  "L".equals(value.getPriority()));
	}
}