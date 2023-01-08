package com.ecommerce.agroproducts.utils.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Webproducts {
    private String title;
    private  String subtitle;
    private File image;
    private  String url;
}
