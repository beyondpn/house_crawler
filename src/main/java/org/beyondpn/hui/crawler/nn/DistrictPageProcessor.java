package org.beyondpn.hui.crawler.nn;

import org.beyondpn.hui.crawler.domain.DistrictTradeInfo;
import org.beyondpn.hui.crawler.util.ConvertUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jianhua on 2016/10/8.
 */
public class DistrictPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        String title = ConvertUtil.strip(html.xpath("//td[@colspan='3']/text()").toString());
        Date tradeDate = ConvertUtil.parseDate(title.substring(0,10));
        String category = title.substring(10,12);

        List<String> rows =  html.xpath("//tr[@height='25']").all();

        if(rows != null && rows.size() > 1)
        {
            List<DistrictTradeInfo> list = new ArrayList<>(rows.size() -1);
            //i=0是列名称，所以i从1开始
            for( int i = 1 ; i < rows.size(); i++)
            {
                Html tempHtml =  Html.create("<table>"+rows.get(i)+"</table>");
                String district = tempHtml.xpath("//td[1]/text()").toString();
                String number =  tempHtml.xpath("//td[2]/text()").toString();
                String area = tempHtml.xpath("//td[3]/text()").toString();

                DistrictTradeInfo info = new DistrictTradeInfo();
                info.setTradeDate(tradeDate);
                info.setCategory(category);
                info.setDistrict("南宁市" + ConvertUtil.strip(district));
                info.setNumber(ConvertUtil.toInt(number));
                info.setArea(ConvertUtil.toFloat(area));

                list.add(info);
            }
            page.putField("info_list",list);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
    }
}
