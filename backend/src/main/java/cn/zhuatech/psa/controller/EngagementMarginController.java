/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.psa.controller;
import cn.zhuatech.psa.common.ApiResponse; import cn.zhuatech.psa.service.EngagementMarginService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin/engagement-margin") public class EngagementMarginController {
    private final EngagementMarginService service; /**
                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                    */
public EngagementMarginController(EngagementMarginService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping ApiResponse<EngagementMarginService.MarginForecast> forecast(@Valid @RequestBody EngagementMarginService.MarginRequest request){return ApiResponse.ok(service.forecast(request));}
}
