package com.myproject.parser.controller;

import com.myproject.parser.model.DateModel;
import com.myproject.parser.util.HTMLParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class DataController {

    @Autowired
    private HTMLParser parser;

    private List<DateModel> olxDataModels = new ArrayList<>();
    private List<DateModel> riaDataModels = new ArrayList<>();

    @GetMapping("/")
    public String mainPage(Model model) {
        if (!olxDataModels.isEmpty()) {
            log.info("olxDataModel: {}, riaDtaModel: {}", olxDataModels, riaDataModels);
            model.addAttribute("olxContent", olxDataModels);
            model.addAttribute("riaContent", riaDataModels);
        }
        return "main";
    }

    @GetMapping("/find")
    public String showContent(@RequestParam String auto, @RequestParam String minPrice, @RequestParam String maxPrice) {
        log.info("Auto: {}, min price: {}, max price: {}", auto, minPrice, maxPrice);
        olxDataModels = parser.parseOlxContent(auto.toLowerCase());
        riaDataModels = parser.parseRiaContent(auto.toLowerCase());
        return "redirect:/";
    }
}
