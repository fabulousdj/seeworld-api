package com.seeworld.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jidai on 9/10/17.
 * Sample API Controller
 */
@RestController
@RequestMapping("api")
public class SampleController {

    @RequestMapping("/sampleintegers")
    public List<Integer> getSampleIntegers() {
        List<Integer> sampleIntegers = new ArrayList<>();
        sampleIntegers.add(1);
        sampleIntegers.add(0);
        sampleIntegers.add(0);
        sampleIntegers.add(8);
        sampleIntegers.add(6);
        return sampleIntegers;
    }
}
