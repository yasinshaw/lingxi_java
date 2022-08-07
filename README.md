## Ling Xi（灵希）管理系统简介
Ling Xi管理系统是一个基于Vue3 + Java开发的通用的后台管理系统，可复用此项目代码快速搭建一个后台管理系统。
同时会实现一个个人网站管理后台，包括：文章管理，微博管理，评论管理，订阅管理等基础功能。

该项目主要会维护两个分支版本。
- main: 最新分支，打通后端接口，功能最齐全，主要会实现一个个人网站的后台管理系统
- thin: 最小功能集合，其它项目可基于此分支快速搭建一个管理端的壳子

## 核心功能进展
### thin部分功能
- [x] SpringSecurity集成，基于JWT实现登录、注销、修改密码等基本功能
- [x] 用户信息修改，密码修改
- [x] 文件上传(七牛)
- [x] 权限管理功能 + 动态菜单 + 接口校验
- [x] swagger集成
### main部分功能
- [ ] 文章管理
- [ ] 微博管理
- [ ] 作品管理
- [ ] 订阅管理
- [ ] 留言管理
- [ ] 分类管理
- [ ] 标签管理
- [ ] 广告管理

## 启动命令
```bash
./mvnw bootRun
```
### swagger地址
swagger ui地址：http://localhost:8080/swagger-ui/index.html#

swagger json 地址：http://localhost:8080/v3/api-docs

## 配套前端代码
后端代码使用Vue3全家桶 https://github.com/yasinshaw/lingxi

## 相关截图

## 主要依赖
- SpringBoot 3.0 
- SpringSecurity
- SpringDataJpa
- MySql
- Jwt
- 七牛图床集成

## 其他
### 开源说明
因本人精力有限，所以写的代码难免有一些不完美之处，欢迎大家提交PR一起完善这个项目。
本项目基于MIT协议，可免费商用。但如果基于此项目二次开源，需要取得作者授权

### 微信群
微信添加作者本人微信：yasinshaw，备注 Ling Xi，通过后会拉你进群