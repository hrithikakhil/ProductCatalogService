package org.example.productcatalogservice.tableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
public class Instructor extends User{

    private String company;
}