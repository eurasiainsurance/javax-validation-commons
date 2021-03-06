package tech.lapsa.javax.validation.constraints;

import java.net.URI;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tech.lapsa.javax.validation.ValidURI;

public class ValidURIConstraintValidator implements ConstraintValidator<ValidURI, URI> {

    private boolean mustAbsolute;
    private String[] allowedSchemes;

    @Override
    public void initialize(final ValidURI constraintAnnotation) {
	mustAbsolute = constraintAnnotation.mustAbsolute();
	allowedSchemes = constraintAnnotation.allowedSchemes();
    }

    @Override
    public boolean isValid(final URI value, final ConstraintValidatorContext context) {
	if (value == null)
	    return true;
	if (mustAbsolute && !value.isAbsolute())
	    return false;
	if (allowedSchemes != null && allowedSchemes.length > 0
		&& Stream.of(allowedSchemes).noneMatch(x -> x.equals(value.getScheme())))
	    return false;
	return true;
    }
}