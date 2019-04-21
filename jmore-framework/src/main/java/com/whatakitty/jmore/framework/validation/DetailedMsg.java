package com.whatakitty.jmore.framework.validation;

import java.io.Serializable;

/**
 * detailed msg for describe more info about result.
 * such as the validation of some beans.
 */
public abstract class DetailedMsg implements Serializable {

    @Override
    public abstract String toString();

}