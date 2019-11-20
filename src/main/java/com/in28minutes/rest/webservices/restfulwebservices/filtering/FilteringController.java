package com.in28minutes.rest.webservices.restfulwebservices.filtering;


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


    @GetMapping(path = "/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean bean = new SomeBean("value1", "value2", "value3");
        return buildFilters("SomeBeanFilter", bean, "field1", "field2");
    }

    @GetMapping(path = "filtering-list")
    public MappingJacksonValue retrieveSomeBeans(){
        List<SomeBean> beans =  Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
        return buildFilters("SomeBeanFilter", beans, "field2", "field3");
    }


    private MappingJacksonValue buildFilters(String filterName, Object bean, String... fields){
        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, propertyFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
