package com.myproject.parser.util;


import com.myproject.parser.model.DateModel;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HTMLParser {

    public List<DateModel> parseOlxContent(String auto) {

        Document doc = null;
        try {
            doc = ConnectionUtil.getContext("https://www.olx.ua/d/uk/transport/legkovye-avtomobili/q-" + auto + "/?currency=USD");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Elements elements = doc.getElementsByAttributeValue("data-cy", "l-card");
        return elements.stream()
                .map(el -> new DateModel(
                        el.selectFirst("div h6").text(),
                        "https://www.olx.ua" + el.child(0).attr("href"),
                        el.getElementsByAttributeValueStarting("data-testid", "ad-price").text()))
                .toList();
    }

    public List<DateModel> parseRiaContent(String auto) {

        Document doc = null;
        String url = (auto.equals("aveo") || auto.equals("lachetti")) ?
                "https://auto.ria.com/uk/legkovie/chevrolet/" + auto :
                "https://auto.ria.com/uk/legkovie/" + auto;

        try {
            doc = ConnectionUtil.getContext(url);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Elements elements = doc.select("section.ticket-item");

        return elements.stream()
                .map(el -> new DateModel(
                        el.select("div.ticket-title a").attr("title"),
                        el.select("div.ticket-title a").attr("href"),
                        el.select("div.price-ticket").attr("data-currency", "USD").text())
                ).toList();
    }
}
