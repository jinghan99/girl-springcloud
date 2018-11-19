package com.yf.common.constant;

/**
 * @ClassName 常量值
 * @Description
 * @Date 2018/8/8 14:43
 * @Author jinghan
 * @Version 1.0
 */
public enum BizConstant {


    //网易云信 app server key
    NIM_AppKey("da4fd19ef9243ac2bab078b00d8a54ad", "应用app key"),
    NIM_AppSecret("2ce483646ee6", "NIM_AppSecret"),
    NIM_CreateUrl("https://api.netease.im/nimserver/user/create.action", "创建用户"),
    NIM_UpdateUrl("https://api.netease.im/nimserver/user/update.action", "修改信息"),
    NIM_RefreshTokenUrl("https://api.netease.im/nimserver/user/refreshToken.action", "更新并获取新的token"),
    NIM_FriendAdd("https://api.netease.im/nimserver/friend/add.action", "添加好友"),

//    jwt
    JWT_KEY_USER_ID("userId","JWT 存入 userId"),
    JWT_KEY_NAME("username","jwt 存入 username"),
    JWT_PASSWORD("password","jwt 存入 password"),
    ;

    private String value; //value
    private String name;  //内容

    BizConstant(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }


    public String getName() {
        return name;
    }

}
