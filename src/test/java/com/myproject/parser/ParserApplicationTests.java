package com.myproject.parser;

import com.myproject.parser.model.DateModel;
import com.myproject.parser.util.HTMLParser;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Setter
class ParserApplicationTests {

    @Autowired
    private HTMLParser parser;

    @Test
    void contextLoads() {

//        Document doc = ConnectionUtil.getContext("https://www.olx.ua/d/uk/transport/legkovye-avtomobili/q-aveo/?currency=USD");
//
//        Elements elements = doc.getElementsByAttributeValue("data-cy", "l-card");
//
//        List<DateModel> models = elements.stream()
//                .map(el -> new DateModel(
//                        el.selectFirst("div h6").text(),
//                        el.child(0).attr("href"),
//                        el.getElementsByAttributeValueStarting("data-testid", "ad-price").text())
//                )
//                .toList();

        //List<DateModel> models = parser.parseRiaContent("aveo");
        parser.parseRiaContent("aveo");

    }
}
