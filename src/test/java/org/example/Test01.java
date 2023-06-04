package org.example;
import org.example.domain.Stock;
import org.example.mapper.StockMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

@SpringBootTest(classes = JsoupApp.class)
@RunWith(SpringRunner.class)
public class Test01 {
    @Autowired
    private StockMapper stockMapper;
    @Scheduled(initialDelay = 2000,fixedDelay = 3000)
    @Test
    public void contextLoads() throws IOException {
        //准备url
        String url="http://q.10jqka.com.cn/zs/";
        //解析url文档对象
        Document document = Jsoup.parse(new URL(url),3000);
        //通过maincont获取所有商品信息
        Element maincont = document.getElementById("maincont");
        //通过每一个tbody标签获取
        Elements elements = maincont.getElementsByTag("m-table m-pager-table");

        int id=-1;
        for (Element el :elements){ //把参数给el并循环打印处来
            Elements tag = el.getElementsByTag("tr");
            //循环打印tag
            for (Element el2:tag) { //把tag参数传给el2打印出来
                Stock stock = new Stock();
                String code =el2.getElementsByTag("td").eq(1).text();//股票代码
                String name = el2.getElementsByTag("td").eq(2).text();
                String  zxj = el2.getElementsByClass("c-rise").eq(0).text();
                String zde = el2.getElementsByClass("c-rise").eq(1).text();
                id++;
                if (id==0) continue;

                stock.setId(id);
                stock.setCode(code);
                stock.setName(name);
                stock.setZxj(zxj);
                stock.setZde(zde);

                //stockMapper.insert(stock);//插入数据
                stockMapper.updateById(stock);//实更新数据
                System.out.println(stock);
            }


        }
        System.out.println("批量插入");


    }
}
