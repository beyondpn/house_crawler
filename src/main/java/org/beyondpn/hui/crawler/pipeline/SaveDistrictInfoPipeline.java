package org.beyondpn.hui.crawler.pipeline;

import org.beyondpn.hui.crawler.dao.HouseDao;
import org.beyondpn.hui.crawler.domain.DistrictTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by jianhua on 2016/10/10.
 */
@Service
public class SaveDistrictInfoPipeline implements Pipeline{

    @Autowired
    private HouseDao houseDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<DistrictTradeInfo> list = resultItems.get("info_list");
        list.forEach( info -> houseDao.insertDistrictTradeInfo(info));
    }
}
