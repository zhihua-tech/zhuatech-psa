/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.psa.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillableCapacityForecastService {
    public Result forecast(Request request) {
        double grossCapacity = request.consultants() * request.workDays() * request.hoursPerDay();
        double netCapacity = Math.max(1, grossCapacity - request.nonBillableHours());
        double weightedDemand = request.scheduledBillableHours() + request.confirmedPipelineHours() * .6;
        double utilization = weightedDemand / netCapacity;
        String decision = utilization > 1.05 ? "ADD_CAPACITY"
            : utilization < request.targetUtilization() - .10 ? "REBALANCE" : "BALANCED";
        List<String> actions = new ArrayList<>();
        if ("ADD_CAPACITY".equals(decision)) actions.add("增加外部顾问或调整项目启动时间");
        if ("REBALANCE".equals(decision)) actions.add("将空闲顾问匹配至已确认商机或内部产品任务");
        if ("BALANCED".equals(decision)) actions.add("保持当前人员计划并每周滚动更新商机权重");
        return new Result(request.teamCode(), round(netCapacity), round(weightedDemand),
            round(utilization), decision, actions);
    }

    private double round(double value) { return Math.round(value * 10_000D) / 10_000D; }

    public record Request(@NotBlank String teamCode, @Min(1) int consultants,
                          @Min(1) int workDays, @DecimalMin("0.1") double hoursPerDay,
                          @DecimalMin("0") double scheduledBillableHours,
                          @DecimalMin("0") double confirmedPipelineHours,
                          @DecimalMin("0") double nonBillableHours,
                          @DecimalMin("0.1") @DecimalMax("1") double targetUtilization) {}
    public record Result(String teamCode, double netCapacityHours, double weightedDemandHours,
                         double forecastUtilization, String decision, List<String> actions) {}
}
