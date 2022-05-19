# 数据库

### 使用 Room

### 数据库   agencies_db
### 表名   Agency
```
  id:Int
  agency_name:String,               待办名
  agency_nowTime:String,            添加时间
  agency_futureTime:String,         预计时间
  agency_type:String,               待办类型
      游玩；学习；工作；
  agency_nowstate:String            待办是否完成
```
### 数据库  User_database
### 表名   User
```
  id:Int
  user_name:String
  user_password:String
```

### 数据库引擎 DBEngine
```
  1、 接收 AgencyDao,UserDao
  2、 配置 List<Agency>,List<User> 单例接收所有数据
  3、 再构造方法中 实例化 两个数据库
  4、 操作数据库 使用 AsyncTask 实现异步线程
```

// TODO 实现 两个表的关联