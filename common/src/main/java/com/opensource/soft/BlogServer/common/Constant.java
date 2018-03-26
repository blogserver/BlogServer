package com.opensource.soft.BlogServer.common;

public class Constant {

	/**
	 * 博客状态 
	 */
    public static class BLOGSTATUS{
        public static final int SAVE = 0;                //保存
        public static final int START_RELEASE = 1;       //开始发布
        public static final int START_PROGRESS = 2;      //发布进行中
        public static final int RELEASE_SUCCESS = 3;     //发布成功
        public static final int RELEASE_ERROR = 4;       //发布失败
        public static final int START_AUDIT = 5;         //开始审核
        public static final int AUDIT_PROGRESS = 6;      //审核进行中
        public static final int AUDIT_ERROR = 7;         //审核失败
    }
}
