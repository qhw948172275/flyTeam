package com.cdyykj.system.commons;

public class SystemConstants {
	/**
	 * 默认密码
	 */
	public static final String defaultPassword="123456";
	/**
	 * 默认加密版密码
	 */
	public static final String defaultPassword_encode="$2a$10$oitzdtvtBCKdvdyRQAOz1OlgbThEkAWpsl7ifj7H7vwH6T2GPTNAm";
	/**
	 * 用户缓存key
	 */
	public static final String SYS_USER_KEY="sys_user_key_";
	/**
	 * 学生缓存key
	 */
	public static final String STUDENT_KEY="student_key_";
	/**
	 * 成员token缓存key
	 */
	public static final String MEMBER_TOKEN_KEY="member_token_key_";

	/**
	 * 成员刷新token缓存key
	 */
	public static final String MEMBER_REFRESH_TOKEN_KEY="member_refresh_token_key_";

	/**
	 * 异常代码
	 */
	public static final String ERROR_CODE_414="414";//用户名不存在
	public static final String ERROR_CODE_415="415";//密码错误
	public static final String ERROR_CODE_416="416";//Token失效
	public static final String ERROR_CODE_500="500";//程序错误
	public static final String ERROR_CODE_417="417";//无权访问
	public static final String ERROR_CODE_418="418";//自定义

	/**
	 * 学校基础管理角色ID
	 */
    public static final Integer SCHOOL_ADMIN_ID=38;

	/**
	 * 云存储上传目录
	 */
	public static final String UPLOAD_DIR = "/photos/";
	/**
	 * 云存储资源访问目录
	 */
	public static final String VISIT_DIR = "http://cdn.benecess.com/photos/";
	/**
	 * 平台oauth账号
	 */
	public static final String PLATFORM="user-platform";

	/**
	 * 新增成员
	 */
	public static final String CREATE_USER="create_user";
	/**
	 * 更新成员信息
	 */
	public static final String UPDATE_USER="update_user";
	/**
	 * 删除成员
	 */
	public static final String DALETE_USER="delete_user";
	/**
	 *新增部门
	 */
	public static final String CREATE_PARTY="create_party";
	/**
	 *更新部门
	 */
	public static final String UPDATE_PARTY="update_party";
	/**
	 *删除部门
	 */
	public static final String DALETE_PARTY="delete_party";

	/**
	 * 本系统对应企业微信的密钥
	 */
	public static final String SYSTEM_SECRET="misyfsY7sOWodWqDUcDy9pnXDJzKtvkiJM7Ae05mN9s";

	/**
	 * 应用ID
	 */
	public static final Integer AGRENT_ID=1000015;

	/**
	 * 推送文章详情路径
	 */
	public static final String ARTICLE_DETAIL_URL="http://test.xzhk.cdyingyun.com:9317/wechat/articleDetail?articleId=";

	/**
	 * 推送文章详情路径
	 */
	public static final String FLIGHT_REPORT_DETAIL_URL="http://test.xzhk.cdyingyun.com:9317/wechat/reportDetail?flightReportId=";



	/**
	 * 本地上传目录
	 */
	public static final String LOCAL_UPLOAD_DIR = "/usr/local/webserver/xzhk/photos/";
	/**
	 *
	 */
	public static final String prefix="http://test.xzhk.cdyingyun.com:9317/photos/";
}
