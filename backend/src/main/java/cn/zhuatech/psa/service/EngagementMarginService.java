/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.psa.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.math.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EngagementMarginService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public MarginForecast forecast(MarginRequest request){
        BigDecimal baseCost=BigDecimal.valueOf(request.plannedHours()).multiply(request.costRate());
        int expectedDelivered=request.plannedHours()*request.completionPercent()/100;
        int overrun=Math.max(0,request.deliveredHours()-expectedDelivered);
        BigDecimal projectedCost=baseCost.add(BigDecimal.valueOf(overrun).multiply(request.costRate())).setScale(2,RoundingMode.HALF_UP);
        BigDecimal margin=request.contractRevenue().subtract(projectedCost);
        double marginRate=request.contractRevenue().signum()==0?0:margin.multiply(BigDecimal.valueOf(100)).divide(request.contractRevenue(),1,RoundingMode.HALF_UP).doubleValue();
        String status=margin.signum()<0?"LOSS":marginRate<25||request.scopeChangePending()?"WATCH":"HEALTHY";
        List<String> actions=new ArrayList<>(); if(overrun>0)actions.add("复核超出计划进度的交付工时"); if(request.scopeChangePending())actions.add("完成范围变更定价与客户确认"); if(marginRate<25)actions.add("调整资源结构并控制非计费投入"); if(actions.isEmpty())actions.add("保持交付节奏并持续跟踪毛利预测");
        return new MarginForecast(projectedCost,margin.setScale(2,RoundingMode.HALF_UP),marginRate,status,actions);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record MarginRequest(@NotNull @DecimalMin("0.01") BigDecimal contractRevenue,@NotNull @Min(0) Integer deliveredHours,@NotNull @Positive Integer plannedHours,@NotNull @DecimalMin("0.00") BigDecimal billableRate,@NotNull @DecimalMin("0.00") BigDecimal costRate,@NotNull @Min(0) @Max(100) Integer completionPercent,@NotNull Boolean scopeChangePending){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record MarginForecast(BigDecimal projectedCost,BigDecimal projectedMargin,double marginRate,String status,List<String> actions){}
}
