# 架构说明

```text
Vue 3 管理端 / 响应式 H5
          │ HTTP / JSON
Spring Security → Controller → Service → Spring Data JPA → MySQL 8
                                  │
                     中心启动与运营风险规则
```

当前版本以单体分层架构保证易运行与易理解。`SiteActivationService` 聚合中心启动必备条件，`OperationsService` 负责试验运营统计和风险计算，`WorkItem` 承载中心、访视与质量事项。真实临床环境还需按适用法规补充电子签名、稽查轨迹、验证、盲态控制、数据留存和隐私保护。
