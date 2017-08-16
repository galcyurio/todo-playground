package com.github.galcyurio.dagger2todo.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author galcyurio
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {}
