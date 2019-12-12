package com.groupname.demo.consts;

public class Consts {
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String WRONG_USERNO_OR_PASSWORD = "用户名或密码错误";
    public static final String PASSWORD_IS_EMPTY = "密码不能为空";
    public static final String USERNO_IS_EMPTY = "用户名不能为空";

    public static final String REGISTER_WAITING_FOR_REVIEW = "注册成功，请等待审核";
    public static final String REGISTER_SUCCESS = "注册成功";
    public static final String USERNO_EXISTS = "用户已存在";
    public static final String USERNO_IS_TOO_LONG = "用户名过长";
    public static final String USERNAME_IS_EMPTY = "姓名不能为空";
    public static final String PASSWORD_NOT_CONSISTENT = "两次密码不一致";
    public static final String PASSWORD_TOO_LONG = "密码过长";
    public static final String PASSWORD_TOO_SHORT = "密码过短";
    public static final String ILLEGAL_TELEPHONE = "不合法的手机号";
    public static final String PHONE_IS_EMPTY = "手机号不能为空";
    public static final String REGISTER_FAIL = "注册失败";
    public static final String ACCOUNT_CAN_USE = "账户可以使用";
    public static final String USER_CHARACTER_ERROR = "用户角色错误";
    public static final String ILLEGAL_MAJOR = "非法的专业";
    public static final String UPDATE_USER_SUCCESS = "用户修改成功";

    public static final String PERMISSION_DENIED = "权限不足";
    public static final String USERNO_NOT_EXISTS = "用户不存在";
    public static final String INQUIRE_SUCCESS = "查询成功";
    public static final String INQUIRE_FAILED = "查询失败";

    public static final String ADD_BOOK_SUCCESS = "图书添加成功";
    public static final String ISBN_ERROR="ISBN错误";
    public static final String ISBN_IS_EMPTY="ISBN不能为空";
    public static final String BOOK_NAME_IS_EMPTY="书名不能为空";
    public static final String AUTHOR_IS_EMPTY="作者不能为空";
    public static final String AUTHOR_MAJOR_IS_EMPTY="专业领域不能为空";
    public static final String PUBLISHING_HOUSE_IS_EMPTY="出版社不能为空";
    public static final String PUBLISHING_TIME_IS_EMPTY="出版时间不能为空";
    public static final String ADD_BOOK_FAILED = "图书添加失败";
    public static final String AUTHOR_MAJOR_ERROR="专业领域错误";
    public static final String BOOK_EXISTS="图书已存在";
    public static final String BOOK_NOT_EXISTS="图书不存在";
    public static final String UPDATE_BOOK_FAILED = "图书修改失败";
    public static final String UPDATE_BOOK_SUCCESS = "图书修改成功";

    public static final String COURSE_EXISTS="课程已存在";
    public static final String ADD_COURSE_SUCCESS="课程添加成功";
    public static final String ADD_COURSE_FAILED="课程添加失败";
    public static final String UPDATE_COURSE_SUCCESS="课程修改成功";
    public static final String COURSE_NO_IS_EMPTY="课程号不能为空";
    public static final String COURSE_NAME_IS_EMPTY="课程名不能为空";
    public static final String TERM_IS_EMPTY="开课学期不能为空";
    public static final String TERM_ERROR="开课学期错误";
    public static final String GUIDEBOOK_ISBN_ERROR="教参ISBN错误";
    public static final String GUIDEBOOK_ISBN_IS_EMPTY="教参ISBN不能为空";
    public static final String GUIDEBOOK_NOT_EXISTS_ERROR="教参不存在";
    public static final String COURSE_TYPE_ERROR="课程类型错误";
    public static final String COURSE_TYPE_IS_EMPTY="课程类型不能为空";
    public static final String COURSE_MAJOR_NO_ERROR="可选专业号错误";


    public static final String ADD_CLASS_SUCCESS="开课成功";
    public static final String ADD_CLASS_FAILED="开课失败";
    public static final String COURSE_NOT_EXISTS="课程不存在";
    public static final String GUIDEBOOK_EXISTS="教参存在";//success=true

    public static final String MESSAGE_CONTENT_IS_EMPTY="留言内容不能为空";
    public static final String ADD_MESSAGE_SUCCESS="留言成功";
    public static final String ADD_MESSAGE_FAILED="留言失败";
    public static final String MESSAGE_NO_NOT_EXISTS="留言不存在";
    public static final String MESSAGE_DELETE_SUCCESS="留言删除成功";
    public static final String MESSAGE_STATUS_ERROR="留言状态错误";

    public static final String PURCHASE_NUMBER_ERROR="采购数量错误";
    public static final String PURCHASE_REQUEST_SUCCESS="采购申请成功，等待审核";
    public static final String PURCHASE_FAILED="采购申请失败";

    public static final String REVIEW_SUCCESS="审核成功";
    public static final String REVIEW_FAILED="审核失败";
    public static final String REVIEW_TYPE_ERROR="审核类型错误";
    public static final String STATUS_ERROR="状态错误";

    public enum Status{
            NORMAL(0),REVIEWING(1),REVIEW_FAILED(2),DELETED(3),DEFAULT(4);
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
    public enum UserType{
        STUDENT(0),TEACHER(1),MANAGER(2);
        private Integer value;
        UserType(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }
    public enum CourseType{
        PROFESSIONAL_OBLIGATORY_COURSE(0),PROFESSIONAL_ELECTIVE_COURSE(1),PUBLIC_OBLIGATORY_COURSE(2);
        private Integer value;
        CourseType(Integer value) {
            this.value=value;
        }
        public Integer getValue() {
            return value;
        }
    }

}
