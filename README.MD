shell 放自己的一些shell命令
designpattern  设计模式
data-structures 数据结构与算法
trans-pro 为分布式事务 demo
  trans-pro1 注册用户
  trans-pro2 给用户增加积分
  中间用redis 做消息队列 也可以换其它消息中间件(activemq,kafka等等)
  独立的事务通过消息的重新发送保证所有流程全部走完,只有事务完成dead才等于1,幂等性是通过两个表来判断是否之前执行过,如果执行过则直接返回调用成功。（完成了功能，细节方面有些可以优化,比如redis有个获取列表为空的时候自动阻塞的:brpop )
