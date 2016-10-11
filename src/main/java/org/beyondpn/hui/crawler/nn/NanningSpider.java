package org.beyondpn.hui.crawler.nn;

import org.beyondpn.hui.crawler.domain.DistrictTradeInfo;
import org.beyondpn.hui.crawler.pipeline.SaveDistrictInfoPipeline;
import org.beyondpn.hui.crawler.pipeline.SaveTypeInfoPipeline;
import org.beyondpn.hui.crawler.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by jianhua on 2016/10/10.
 */
@Service
public class NanningSpider {

    private static final String MIN_NEW_HOUSE_DATE = "2011-01-01";
    private static final String MIN_OLD_HOUSE_DATE = "2011-01-04";

    @Autowired
    private SaveDistrictInfoPipeline saveDistrictInfoPipeline;
    @Autowired
    private SaveTypeInfoPipeline saveTypeInfoPipeline;

    @PostConstruct
    public void init(){
        LocalDate minNewHouseDate = ConvertUtil.parseLocalDate(MIN_NEW_HOUSE_DATE);
        LocalDate minOldHouseDate = ConvertUtil.parseLocalDate(MIN_OLD_HOUSE_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Spider districtSpider = Spider.create(new DistrictPageProcessor()).addPipeline(saveDistrictInfoPipeline);
        Spider typeSpider = Spider.create(new TypePageProcessor()).addPipeline(saveTypeInfoPipeline);

        LocalDate today = LocalDate.now();
        LocalDate tradeDay = today;
        while(tradeDay.isAfter(minNewHouseDate)){
            tradeDay = tradeDay.minusDays(1);
            String tradeDayStr = tradeDay.format(formatter);
            districtSpider.addUrl("http://www.nnfcj.gov.cn/tradedataDayDetail.jspx?houseCtgId=0&ctgId=0&dateString=" + tradeDayStr)
                    .addUrl("http://www.nnfcj.gov.cn/tradedataDayDetail.jspx?houseCtgId=1&ctgId=0&dateString=" + tradeDayStr);
        }

        tradeDay = today;
        while(tradeDay.isAfter(minOldHouseDate)){
            tradeDay = tradeDay.minusDays(1);
            String tradeDayStr = tradeDay.format(formatter);
            typeSpider.addUrl("http://www.nnfcj.gov.cn/tradedataDayDetail.jspx?houseCtgId=0&ctgId=1&dateString=" + tradeDayStr)
                    .addUrl("http://www.nnfcj.gov.cn/tradedataDayDetail.jspx?houseCtgId=1&ctgId=1&dateString=" + tradeDayStr);
        }

        districtSpider.thread(5).run();
        typeSpider.thread(5).run();
    }

}
