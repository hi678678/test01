package org.example;

import org.example.domain.Stock01;
import org.example.mapper.StockMapper01;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

@SpringBootTest(classes = JsoupApp.class)
@RunWith(SpringRunner.class)

public class Test02 {
    @Autowired //依赖注入mapper层
    private StockMapper01 stockMapper01;
    @Scheduled(initialDelay = 2000,fixedDelay = 3000) //实时刷新

    @Test
    public void contextLoads() throws IOException {
        String url="http://data.10jqka.com.cn/financial/yjyg/";
        Document document = Jsoup.parse(new URL(url), 3000);//解析URL获取文档对象
        Element jajax = document.getElementById("J-ajax-main");//通过J-ajax-main获取信息传入jajax中
        Elements elements = jajax.getElementsByTag("tbody");//从jajax获取tbody标签

//循环将数据存入数据库
        int id=0;
        for (Element el:elements) {
            Elements tag = el.getElementsByTag("tr");

            for (Element el2:tag) {
                Stock01 stock01 = new Stock01();
                String sid = el2.getElementsByTag("td").eq(1).text(); //股票代码
                String sname = el2.getElementsByTag("td").eq(2).text();//股票简称
                String kinds = el2.getElementsByTag("td").eq(3).text();//业绩预告类型
                String brief = el2.getElementsByTag("td").eq(4).text();//业绩预告摘要

                id++;
                if (id>23) break;
                //把值设置到stock01对象里边
                stock01.setId(id);
                stock01.setSid(sid);
                stock01.setSname(sname);
                stock01.setKinds(kinds);
                stock01.setBrief(brief);

                //stockMapper01.insert(stock01);//调用接口新增方法插入数据
                stockMapper01.updateById(stock01);//实更新数据
                System.out.println(stock01);//在控制台打印


            }
        }
        System.out.println("批量插入");

    }


}
