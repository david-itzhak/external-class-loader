package com.example.externalclassloader.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Dmitry Itskov
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CarTuningOrder {

    @NotEmpty
    @NotNull
    @JsonProperty(value = "name", required = true)
    String name;

    // It should be a name of the class which implements TuneMaster interface.
    // This name should start from a lower case letter!
    @NotEmpty
    @NotNull
    @JsonProperty(value = "action", required = true)
    String action;

    // A path to a jar file.
    // It cay be any directory in the file system.
    @JsonProperty(value = "jarPath")
    String jarPath;

    // A full class name with a package.
    @JsonProperty(value = "className")
    String className;
}
