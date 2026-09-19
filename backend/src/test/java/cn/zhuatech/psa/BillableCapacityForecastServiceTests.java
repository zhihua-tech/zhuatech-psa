/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.psa;

import cn.zhuatech.psa.service.BillableCapacityForecastService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class BillableCapacityForecastServiceTests {
    private final BillableCapacityForecastService service = new BillableCapacityForecastService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void requestsCapacityWhenWeightedDemandExceedsSupply() {
        var result = service.forecast(new BillableCapacityForecastService.Request(
            "DELIVERY-A", 10, 20, 8, 1300, 500, 200, .75));
        assertEquals("ADD_CAPACITY", result.decision());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void rebalancesUnderutilizedTeam() {
        var result = service.forecast(new BillableCapacityForecastService.Request(
            "DELIVERY-B", 10, 20, 8, 600, 100, 200, .75));
        assertEquals("REBALANCE", result.decision());
    }
}
