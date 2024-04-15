package com.tosin.notez.config;


import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.jibx.schema.codegen.extend.NameConverter;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class NamingStrategy extends DefaultGeneratorStrategy {

    private final NameConverter nameTools = new DefaultNameConverter();

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {

        final String javaClassName = super.getJavaClassName(definition, mode);
        if (mode == Mode.DEFAULT) return javaClassName;


        return nameTools.depluralize(javaClassName);
    }

}
