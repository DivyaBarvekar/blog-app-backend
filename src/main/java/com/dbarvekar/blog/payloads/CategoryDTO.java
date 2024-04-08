package com.dbarvekar.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private Long id;

    @NotEmpty
    @Size(min = 4 , message = "Title size should be greater then 4 ")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10 , message = "Description size should be greater then 10 ")
    private String categoryDescription;
}
