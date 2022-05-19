## 主页面  MainActivity
```
1、 通过 fragment 管理器 实现 fragment 跳转
2、 使用 DBEngine 数据库引擎 获取数据库数据<List<Agenct>>
3、 将 数据转化为 序列化数据<Serializable> 存入Bundle 通过fragment.setArguments(bundle) 以fragment管理器 发送数据到各个fragment 页面
4、 每次从数据库获取数据，由于操作在异步线程执行，为造成时间差，故通过休眠线程令主线程在异步线程获取了数据之后再执行
5、 刷新按钮，重新获取数据，且重新渲染 

```

####  背景 BlankFragment

####  待办页面 FutureFragment
```
1、 获取Main 传递的数据NevData并将数据转化为<List<Agency>>
        bundle = getArguments(),
        NevDataAll = List<Agency>bundle.getSerializable("NevData")

2、 将数据转化为 List<Map> ,通过 SimpleAdatater适配器 适配ListView
3、 每次从新处理数据都需要清理数据 clear()
4、 点击顶部导航 对数据进行筛选 重新适配
5、 点击每一项item 实现页面跳转，并发送 当前List<Map> item 对应得List<Agency> item
```

####  历史页面 HistoryFragment

####  数据页面 DataFragment
```
1、 添加页面入口
// TODO 用户登录，头像显示，数据表格
// TODO 表格显示数据
```

## 弹窗页面  DialogPage

```
通过线程实现 自动跳转
```

## 添加页面  AddAgency
```
1、 通过 DBEngine 实现数据库数据添加
```

## 更新页面  UpdateAgency
```
1、 通过 DBEngine 实现数据库数据更新
        修改 传递来得Agency对象，并更新到数据库
2、 通过 DBEngine 实现数据库数据删除
        删除 传递来得Agency对象

```