package com.blog.apis.Enitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Data
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private  String name;
}
