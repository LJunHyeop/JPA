package com.green.greengram.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetApiAdminProvider {
    @JsonIgnore
    private long userId;


}
