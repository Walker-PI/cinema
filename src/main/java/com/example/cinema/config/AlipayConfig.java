package com.example.cinema.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号

    public static String app_id = "2016093000631679";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzcwwKNqLGVbPWKMbfUQdZvPuKaQDldvAHtfoOCK6KstZpPEg68RaKFjq0wqgUgeU4LcXZklUivwl+ONfBHVs0A3O2BJx440WAsvbZvL2oRvnwJ6y5Rg7c1wftkBaleE6XyKdCiwpKsvdy88kj/AJ6i/+fzKcUz7JFqQYwEKnk7ywVmRBxKIu7YQjTkH4EAnm7ywKCfhvturK4Uj638kzZPd7pfRYBOSFC9783+W6WEM6iAZ3YnlEcQk/GRVlUWT9VzKhfAMnN06M43tzzeckNh6DOYP7Ipu0KfO7a/3Wa2DoPofVdwXEs7ikGvCQUaJ80sJf2GWJBmtlMaYzNhMtnAgMBAAECggEBALIRwuvLpaDcYJVuZ1Io7Pterdl+MxaA/NUZ/mW4tcMKjftlh2N941jpkB3ZX8Lh1xz1RvSM4hCVs4zeSTLnt54YotUHJGJNXKXGcXBFe4olzFVEeYBshckdVE+oAOD4Tj0OLvO4V2Ei0KvwQDOGGsuSavoesdnT9FptzKcbkguKoi/g1RiIJT6yqNlMWa9Xv7ygKZR7ed7GVSUigHLJiQYyunuCOx0BQZqUlvHFFPV6tozBXq2dVV8X97IHkPOCn7s6iijcOKGPRJGqO0M2H8KWECARPex23hBu5Uzcr9VhSWpXblrtbBQKMTdi+/dTK5cvC9n3SjFuZ/5shVyttqECgYEA8YuYmvwtklyqmPlMNA0Pq2k37k+zcSXU8CYEy8MfIx041/z+OFqvR51/Q9p65j1/pmZR2y3lRi5EMzQt8fGsU3at3K3xvPLAziKSBfhl9B+12xvF38SwnYYW3ffVsbvQICsIIdn/eP72laWgOyq673M6MPX9+HhKcp9o15Cl01cCgYEAvjAo6fiMPgXCwYLBJEw+9+Pfq3tlRDETNvaGuen00w/9QvxFJZ62GfwMZnoXso/0UAdpIHMBjmvtNpQyRzVgVykS1MuLtk48NUFSklC3tvY6eLQx4DvmbciRC5oFEA+qikPD9pxbhuPoyxP4JEgE6jsAknJ19YktyTzUyyrCTnECgYEAqJWi3EE32Vh95SjSS9312tAkLltIRad++GHApSq5V7u0ieNU16ZLgfNl+MjLUFkJ9+ygIxbkV9hA13AKxTvE57gEXuq0twjDEtxECJC3L+zV82ht8eEI19jXqvKoHlWUTG9cTKwnR57EIewoakNHl4pXygZWzQ3AAEJMXW/inR8CgYBDhU+ROUd44YyyLNyrKVKVqVYISGe8XPvoYH+WU4QyxpNwi6V05LLb6MryOthc8ZSYMPlWpt3flVuDK/vpof6REyj3WUhBsebYNVe+UF/Y2fQKVFb8t+doBHPSPZ0chaNTMRnKXNp1ukY0iuNm5hawnb/nPjG4uToDHtChTI3tUQKBgCJzdHOqiQZsTksb2IPeLi6/f6usCRV+20TV3chp3oMH8907feZTYns55czDuj7ckVcUHI6D46wMJ3Zs8yJnOP1aLy76thmkKa3g6xbDaUyv/9ybGQ3aZji61M8RbwExmfP1vgKxff+sa/4CfAyExFYu0dglBoq2XDA3NfHBqHKm";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwkyqPN9FeMF2CXGn18W+M8vS6PdgWRK94q/Xnq7DxKL5dISLInBG6FlvyAv03B7ln1M4qCrtNC2DFxTvV8FKaC/fIwq+D6M2xgH7OFJsV7NJ7xbfoIelnYEJBBU/wbxC+S65NSmx6l0kudoU0FcQ/iryi345R8Ogy4SXSe7/idKIm6ujjiEQp8OGYaRlsg3aYsCqAub2pF7jjBc9ju7jN+eNBKZMkym2PqsOefgxRrNliVNVKTMishSGIcEpYuYaVfyz6OLe/qVRODudbmSxxfY+Y9Cbl+AZwlQV5I/wXq6qpMGBq3P0Vm4gTA4v2GklTv7NDlyrMtWiwQrxWLIHOwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://120.77.169.189:80/notify/pay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://120.77.169.189:80/user/home";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关 //
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";// 测试


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
