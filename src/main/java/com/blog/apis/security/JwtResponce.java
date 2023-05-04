package com.blog.apis.security;



import java.util.Date;

import lombok.Data;

@Data
public class JwtResponce {

    private String Token;
    private Date ValidTill;
}
