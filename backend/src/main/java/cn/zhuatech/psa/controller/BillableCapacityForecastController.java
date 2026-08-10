/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.psa.controller;

import cn.zhuatech.psa.common.ApiResponse;
import cn.zhuatech.psa.service.BillableCapacityForecastService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psa/insights")
public class BillableCapacityForecastController {
    private final BillableCapacityForecastService service;
    public BillableCapacityForecastController(BillableCapacityForecastService service) { this.service = service; }

    @PostMapping("/billable-capacity-forecast")
    public ApiResponse<BillableCapacityForecastService.Result> forecast(
        @Valid @RequestBody BillableCapacityForecastService.Request request) {
        return ApiResponse.ok(service.forecast(request));
    }
}
