package com.playground.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

@RestController
class CompanyController {

    private final CompanyRepository repository;

    CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/companies")
    Resources<Resource<Company>> all() {

        List<Resource<Company>> companies = repository.findAll().stream()
                .map(company -> new Resource<>(company,
                        linkTo(methodOn(CompanyController.class).one(company.getId())).withSelfRel(),
                        linkTo(methodOn(CompanyController.class).all()).withRel("companies")))
                .collect(Collectors.toList());

        return new Resources<>(companies, linkTo(methodOn(CompanyController.class).all()).withSelfRel());
    }

    @GetMapping("/companies/{id}")
    Resource<Company> one(@PathVariable Long id) {

        Company company = repository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));

//        return new Resource<>(company, linkTo(methodOn(CompanyController.class).one(id)).withSelfRel());
         return new Resource<>(company,
	         linkTo(methodOn(CompanyController.class).one(id)).withSelfRel(),
	         linkTo(methodOn(CompanyController.class).all()).withRel("companies"));
    }
}