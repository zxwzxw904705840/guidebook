package com.groupname.demo.consts;

public class Consts {
    public static final String LOGIN_SUCCESS = "登录成功！";
    public static final String WRONG_USERNO_OR_PASSWORD = "用户名或密码错误";
    public static final String PASSWORD_IS_EMPTY = "密码不能为空！";
    public static final String USERNO_IS_EMPTY = "用户名不能为空！";

    public static final String REGISTER_WAITING_FOR_REVIEW = "注册成功，请等待审核！";
    public static final String REGISTER_SUCCESS = "注册成功！";
    public static final String USERNO_EXISTS = "用户已存在！";
    public static final String USERNO_IS_TOO_LONG = "用户名过长！";
    public static final String USERNAME_IS_EMPTY = "姓名不能为空！";
    public static final String PASSWORD_NOT_CONSISTENT = "两次密码不一致！";
    public static final String PASSWORD_TOO_LONG = "密码过长！";
    public static final String PASSWORD_TOO_SHORT = "密码过短！";
    public static final String ILLEGAL_TELEPHONE = "不合法的手机号！";
    public static final String PHONE_IS_EMPTY = "手机号不能为空";
    public static final String REGISTER_FAIL = "注册失败！";
    public static final String ACCOUNT_CAN_USE = "账户可以使用";

    public static final String ADD_BOOK_SUCCESS = "图书添加成功";

    public enum Status{
            NORMAL(0),REVIEWING(1),REVIEW_FAILED(2),DELETED(3);
        private Integer value;
        Status(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }
    public enum PurchaseStatus{
        PURCHASING(0),REVIEWING(1),REVIEW_FAILED(2),COMPLETED(3);
        private Integer value;
        PurchaseStatus(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }

    public enum ReviewType{
        USER_NO(0),CLASS_NO(1),FILE_NO(2),MESSAGE_NO(3),PURCHASE_NO(4);
        private Integer value;
        ReviewType(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }
    public enum BookMajor{
        MATH(0),CS(1),CHEMISTRY(2),PHYSICS(3),HUMANITIES(4),ART(5);
        private Integer value;
        BookMajor(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }
}
