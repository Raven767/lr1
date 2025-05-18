package ru.electronics.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.electronics.model.Store;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreController {
    private List<Store> stores = new ArrayList<>();

    @GetMapping("/index")
    public String index() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head>");
        html.append("<title>Магазин электрнонники</title>");
        html.append("<style>");

        html.append("body {");
        html.append("    font-family: Arial;");
        html.append("    background-color: #f9f9f9;");
        html.append("}");

        html.append("h1 {");
        html.append("    text-align: center;");
        html.append("}");

        html.append("h2 {");
        html.append("    text-align: center;");
        html.append("}");

        html.append("table {");
        html.append("    border-collapse: collapse;");
        html.append("    width: 100%;");
        html.append("    margin-top: 20px;");
        html.append("}");

        html.append("th, td {");
        html.append("    padding: 10px;");
        html.append("    text-align: left;");
        html.append("}");

        html.append("th {");
        html.append("    background-color: #e0e0e0;");
        html.append("}");

        html.append("form {");
        html.append("    margin-top: 20px;");
        html.append("}");

        html.append("input[type=text], input[type=number] {");
        html.append("    width: 25%;");
        html.append("    padding: 10px;");
        html.append("    margin-right: 30px;");
        html.append("}");

        html.append(
                "input[type=submit] {" +
                        "background-color: #909090;" +
                        "color: white;" +
                        "padding: 10px;" +
                        "border-radius: 4px;" +
                        "cursor: pointer;" +
                        "}"
        );

        html.append(
                "input[type=submit]:hover {" +
                        "background-color: #999999;" +
                        "}"
        );

        html.append("</style>");
        html.append("</head><body>");

        html.append("<h1>ЭльдоРадио</h1>");

        html.append("<form action=\"/index\" method=\"post\">");
        html.append("<h2>Добавить товар</h2>");

        html.append("Брэнд: <input type=\"text\" name=\"addBrand\">");
        html.append("Название: <input type=\"text\" name=\"addProductName\">");
        html.append("Цена: <input type=\"number\" name=\"addPrice\">");

        html.append("<input type=\"submit\" value=\"Добавить товар\">");

        html.append("</form>");


        html.append("<form action=\"/index\" method=\"post\">");
        html.append("<h2>Удалить товар</h2>");

        html.append("ID:<input type=\"number\" name=\"deleteId\">");

        html.append("<input type=\"submit\" value=\"Удалить товар\">");

        html.append("</form>");

        html.append("<table>");
        html.append("<tr><th>ID</th><th>Брэнд</th><th>Название</th><th>Цена</th></tr>");

        for (Store s : stores) {
            html.append("<tr>");
            html.append("<td>").append(s.getId()).append("</td>");
            html.append("<td>").append(s.getBrand()).append("</td>");
            html.append("<td>").append(s.getProductName()).append("</td>");
            html.append("<td>").append(s.getPrice()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("<a href=\"/products\" style=\"display:block; text-align:center; margin-top:30px; font-size:18px;\">Перейти к списку всех товаров</a>");
        html.append("</body></html>");

        return html.toString();
    }

    @PostMapping("/index")
    public RedirectView handleFormSubmission(@RequestParam(value = "addBrand", required = false) String addBrand,
                                             @RequestParam(value = "addProductName", required = false) String addProductName,
                                             @RequestParam(value = "addPrice", required = false) Double addPrice,
                                             @RequestParam(value = "deleteId", required = false) Integer deleteId) {
        try {
            if (addBrand != null && addProductName != null && addPrice != null) {
                    Store store = new Store();
                store.setBrand(addBrand);
                store.setProductName(addProductName);
                store.setPrice(addPrice);
                stores.add(store);
            } else if (deleteId != null) {
                stores.removeIf(s -> s.getId() == deleteId);
                for (int i = 0; i < stores.size(); i++) {
                    stores.get(i).setID(i + 1);
                }
            }
            return new RedirectView("/index", true);
        } catch (Exception e) {
            return new RedirectView("/error", true);
        }
    }

    @GetMapping("/products")
    public String productList() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Все товары</title>");
        html.append("<style>");
        html.append("body { font-family: Arial; background-color: #f0f0f0; padding: 20px; }");
        html.append("table { border-collapse: collapse; width: 100%; }");
        html.append("th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }");
        html.append("th { background-color: #e0e0e0; }");
        html.append("a { display: block; margin-top: 20px; }");
        html.append("</style></head><body>");

        html.append("<h1>Список всех товаров</h1>");
        html.append("<table>");
        html.append("<tr><th>ID</th><th>Брэнд</th><th>Название</th><th>Цена</th></tr>");

        for (Store s : stores) {
            html.append("<tr>");
            html.append("<td>").append(s.getId()).append("</td>");
            html.append("<td>").append(s.getBrand()).append("</td>");
            html.append("<td>").append(s.getProductName()).append("</td>");
            html.append("<td>").append(s.getPrice()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("<a href=\"/index\">Назад на главную</a>");
        html.append("</body></html>");

        return html.toString();
    }

}