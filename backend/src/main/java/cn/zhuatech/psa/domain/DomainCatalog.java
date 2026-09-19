/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.psa.domain;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component public class DomainCatalog {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName(){return "知华 PSA 专业服务自动化平台";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String sceneName(){return "商机交接、项目交付、资源排期、工时、成本与收入确认";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<SeedItem> seedItems(){return List.of(
        new SeedItem("PSA-20260801-001","数据中台项目八月资源排期","处理中","交付管理组","高"),
        new SeedItem("PSA-20260801-002","客户新增范围成本评估","待处理","项目商务组","紧急"),
        new SeedItem("PSA-20260801-003","七月顾问工时完整性核验","已完成","运营分析组","中"),
        new SeedItem("PSA-20260801-004","项目里程碑收入确认准备","处理中","财务协同组","高"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<String> recommendedActions(){return List.of("优先处理资源冲突与关键岗位缺口","核查范围变更对成本和毛利的影响","提升工时提交与里程碑验收及时率");}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record SeedItem(String recordNo,String title,String status,String owner,String priority){}
}
