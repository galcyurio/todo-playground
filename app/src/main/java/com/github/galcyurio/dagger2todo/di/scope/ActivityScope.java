package com.github.galcyurio.dagger2todo.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author galcyurio
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {}
