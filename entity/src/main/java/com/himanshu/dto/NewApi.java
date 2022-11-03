package com.himanshu.dto;

import com.himanshu.annotation.enumValidator.ValidateString;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewApi {

    @NotEmpty
    @ValidateString(acceptedValues = {"GET", "PUT", "POST", "DELETE", "PATCH"})
    private String method;

    @NotEmpty
    private String url;

    private Map<String, String> headers;

    private Map<String, String> body;

}
