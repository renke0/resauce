package com.renke.resauce.mapper;

import org.mapstruct.MapperConfig;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

@MapperConfig(
    unmappedTargetPolicy = IGNORE,
    injectionStrategy = CONSTRUCTOR,
    componentModel = "spring",
    implementationName = "Default<CLASS_NAME>",
    implementationPackage = "<PACKAGE_NAME>.generated")
interface DefaultConfiguration {}
