# 架构说明

```text
Vue 3 管理端 / 响应式 H5
          │ HTTP / JSON
Spring Security → Controller → Service → Spring Data JPA → MySQL 8
                                  │
                     交付毛利与运营风险规则
```

当前版本使用清晰的单体分层结构。`EngagementMarginService` 负责按交付进度预测项目成本和毛利，`OperationsService` 形成资源与事项总览，`WorkItem` 可扩展为项目、里程碑、工时和审批聚合。生产化建议增加项目级数据权限、工时锁定、财务期间、计费规则、审计和 ERP 集成。
