package com.hongchai.common.valid;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private Set<Integer>  set =new HashSet<>();
    @Override
    public void initialize(ListValue constraintAnnotation) {


        int vals[]= constraintAnnotation.vals();
        if(vals==null) return;
        for(int v:vals)
        {
            set.add(v);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
