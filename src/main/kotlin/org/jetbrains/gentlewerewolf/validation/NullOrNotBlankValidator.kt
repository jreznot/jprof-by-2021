package org.jetbrains.gentlewerewolf.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NullOrNotBlankValidator : ConstraintValidator<NullOrNotBlank, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        return value.isNotBlank()
    }
}
