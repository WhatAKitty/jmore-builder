# JMore WEB

## 约定

对前后端交互格式做约定化配置。

### API JSON 格式

后端通讯全部以`POST`形式传递

#### 成功消息体

```json
{
  "code": 0,
  "timestamp": 1524640724522,
  "msg": "成功",
  "data": ["label"]
}
```

```json
{
  "code": 0,
  "timestamp": 1524640724522,
  "msg": "成功",
  "data": {
    "name": "jmore"
  }
}
```

#### 失败消息体

```json
{
  "code": -1010,
  "timestamp": 1524640724522,
  "msg": "获取失败",
  "data": null
}
```
