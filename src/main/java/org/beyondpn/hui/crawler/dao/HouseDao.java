package org.beyondpn.hui.crawler.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.beyondpn.hui.crawler.domain.DistrictTradeInfo;
import org.beyondpn.hui.crawler.domain.TypeTradeInfo;

/**
 * Created by jianhua on 2016/10/10.
 */
@Mapper
public interface HouseDao {

    @Insert("insert into district_trade_info (`trade_date`,`district`,`number`,`area`,`category`)" +
            " values (#{tradeDate},#{district},#{number},#{area},#{category})")
    void insertDistrictTradeInfo(DistrictTradeInfo info);

    @Insert("insert into type_trade_info (`trade_date`,`city`,`type`,`number`,`area`,`category`)" +
            " values (#{tradeDate},#{city},#{type},#{number},#{area},#{category})")
    void insertTypeTradeInfo(TypeTradeInfo info);

}
