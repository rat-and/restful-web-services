package edu.rest.webservices.restfulwebservices.filetring;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("dupa", "dupaa", "dupaaa");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", SimpleBeanPropertyFilter.filterOutAllExcept("field2"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveAllBeans() {
        List<SomeBean> someBeanList = Arrays.asList(
                new SomeBean("dupa", "dupaa", "dupaaa"),
                new SomeBean("cyc", "cyyc", "cyyyc")
        );

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", SimpleBeanPropertyFilter.filterOutAllExcept("field1"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
        mapping.setFilters(filters);

        return mapping;
    }
}
