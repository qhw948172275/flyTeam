企业微信设置接收事件服务器解密需要用到。由于美国对出口的软件做了限制，128位的AES解密，需要重新下载local_policy.jar和US_export.policy.jar两个包进行解密。
解决方法：
windows、linux和mac解决方法一样下：
将lib下的两个jar，拷贝到jdk安装目录下，“jre/lib/security”目录下面。

