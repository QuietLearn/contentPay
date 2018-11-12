package com.stylefeng.guns.core.common.result;

/**
 * Created by hyj on 2018/11/12
 */
public class ReturnEx {
    public interface favoriteReturnEx{
        String successRetrun = "\"code\": 0,\n" +
                "\"data\":{\n" +
                "\"videoVoList\":[\n" +
                "{\n" +
                "    \"videoPublisharea\": \"内地\",\n" +
                "    \"videoDirector\": \"内详\",\n" +
                "    \"videoName\": \"村长趣哪鹅\",\n" +
                "    \"typeName\": \"综艺\",\n" +
                "    \"videoActor\": \"内详\",\n" +
                "    \"typeId\": 3,\n" +
                "    \"videoId\": 7001,\n" +
                "    \"videoPublishyear\": 0,\n" +
                "    \"videoNote\": \"2014-09-13 期\",\n" +
                "    \"videoPic\": \"uploads/allimg/171117/43cc2cebb28392eb.jpg\"\n" +
                "},\n" +
                "{\n" +
                "    \"videoPublisharea\": \"内地\",\n" +
                "    \"videoDirector\": \"内详\",\n" +
                "    \"videoName\": \"爱豆万万碎\",\n" +
                "    \"typeName\": \"综艺\",\n" +
                "    \"videoActor\": \"内详\",\n" +
                "    \"typeId\": 3,\n" +
                "    \"videoId\": 7002,\n" +
                "    \"videoPublishyear\": 0,\n" +
                "    \"videoNote\": \"2016-07-13 期\",\n" +
                "    \"videoPic\": \"uploads/allimg/171117/b6377146f622f600.jpg\"\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "\"msg\": \"\"";
        String failReturn = " \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"请重新登录\"";
    }

    public interface feedbackReturnEx{
        String listSuccessReturn ="\"code\": 0,\n" +
                "\"data\":[\n" +
                "{\n" +
                "\"gmtCreated\": \"2018-10-29 10:49:26\",\n" +
                "\"feedbackTypeName\": \"\",\n" +
                "\"info\": \"不顺畅\"\n" +
                "}\n" +
                "],\n" +
                "\"msg\": \"\"";
        String failReturn = " \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"请重新登录\"";
        String addSuccessReturn ="\"code\": 0,\n" +
                "                \"data\": \"\",\n" +
                "                \"msg\": \"反馈成功\"";
    }
    public interface feedbackTypeReturnEx{
        String addSuccessReturn = "\"code\": 0,\n" +
                "            \"data\":[\n" +
                "    {\n" +
                "        \"typeName\": \"播放问题\",\n" +
                "            \"typeCode\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"typeName\": \"改善建议\",\n" +
                "            \"typeCode\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"typeName\": \"求片找片\",\n" +
                "            \"typeCode\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"typeName\": \"其他问题\",\n" +
                "            \"typeCode\": \"4\"\n" +
                "    }\n" +
                "],\n" +
                "        \"msg\": \"\"";
    }
    public interface memberReturnEx{
        String registerSuccessReturn = "{\n" +
                "\"code\": 0,\n" +
                "\"data\":{\n" +
                "\"birthday\": \"\",\n" +
                "\"email\": \"\",\n" +
                "\"gender\": 1,\n" +
                "\"id\": 32,\n" +
                "\"memberTypeName\": \"普通会员\",\n" +
                "\"mobile\": \"13065708090\",\n" +
                "\"picAddress\": \"\",\n" +
                "\"points\": 0,\n" +
                "\"username\": \"0b4be0a0c7-mo\",\n" +
                "\"uuidToken\": \"4900694c9da742bf86dedc6dbbd1cda6\"\n" +
                "},\n" +
                "\"msg\": \"注册成功\"";
        String registerFailReturn = "  \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"该手机已经注册\"";

        String loginSuccessReturn = " \"code\": 0,\n" +
                "                 \"data\":{\n" +
                "            \"email\": \"13065708090@qq.com\",\n" +
                "                    \"gender\": 1,\n" +
                "                    \"id\": 2,\n" +
                "                    \"memberTypeName\": \"\",\n" +
                "                    \"mobile\": \"17764545854\",\n" +
                "                    \"nickname\": \"\",\n" +
                "                    \"picAddress\": \"\",\n" +
                "                    \"points\": 0,\n" +
                "                    \"username\": \"hyj11\",\n" +
                "                    \"uuidToken\": \"8bb4e149d9b94a4b833f1eabc033573d\"\n" +
                "        },\n" +
                "                \"msg\": \"登录成功\"";
        String loginFailReturn = "\"code\": 1,\n" +
                "\"data\": \"\",\n" +
                "\"msg\": \"密码错误\"";

        String checkVipSuccessReturn ="\"code\": 0,\n" +
                "\"data\": \"\",\n" +
                "\"msg\": \"欢迎观看\"";

        String checkVipFailReturn1 = "\"code\": 1,\n" +
                "\"data\":{\n" +
                "    \"prompt1\": \"请充值会员后观看\",\n" +
                "    \"prompt2\": \"可花费2金币观看\"\n" +
                "},\n" +
                "\"msg\": \"\"  ";

        String checkVipFailReturn2 = "\"code\": 10,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"如已是会员或购买该影片，请登录观看\"";


        String getMessageFailReturn = "  \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"手机号为空，请重新传值\"";

        String  getMessageSuccessReturn = "\"code\": 0,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"\"";
        String resetPassFailReturn = "\"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"验证码错误，重新输入\"";
        String resetPassSuccessReturn = " \"code\": 0,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"密码修改成功\"";

        String getUuidValidityFailReturn ="\"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"该uuid已过期\"";
        String getUuidValiditySuccessReturn = " \"code\": 0,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"uuid对应用户为2a7fa7b2ca-mo\"";

        String verifyMessageFailReturn ="\"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"验证码错误，重新输入\"";
        String verifyMessageSuccessReturn = "\"code\": 0,\n" +
                "    \"data\":\"a152d00-ce4fde4-79b8550-3e2e2ff9bd332\",（用于安全更新手机）\n" +
                "    \"msg\": \"验证通过\"";
        String deductedGoinFailReturn =" \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                    "    \"msg\": \"扣除失败，金币不足\"";
        String deductedGoinSuccessReturn1 =" \"code\": 0,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"扣除成功，欢迎观看\"";
        String deductedGoinSuccessReturn2 =" \"code\": 0,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"已付费此视频\"";

    }
    public interface notificationReturnEx{
        String getPushNotifySuccessReturn ="\"code\": 0,\n" +
                "\"data\":[\n" +
                "{\n" +
                "\"activityUrl\": \"\",\n" +
                "\"content\": \"asdad\",\n" +
                "\"title\": \"xxc\",\n" +
                "}\n" +
                "],\n" +
                "\"msg\": \"\"";
        String getPushNotifyFailReturn ="\"code\": 1,\n" +
                "\"data\": \"\",\n" +
                "\"msg\": \"没有新推送\"";

        String getAllNotifySuccessReturn ="\"code\": 0,\n" +
                "\"data\":[\n" +
                "{\n" +
                "\"activityUrl\": \"\",\n" +
                "\"content\": \"asdad\",\n" +
                "\"title\": \"xxc\",\n" +
                "},\n" +
                "{\n" +
                "\"activityUrl\": \"\",\n" +
                "\"content\": \"尊敬的用户，欢迎使用影视大全\",\n" +
                "\"title\": \"欢迎注册1\",\n" +
                "}\n" +
                "],\n" +
                "\"msg\": \"\"";

        String getAllNotifyFailReturn ="\\\"code\\\": 1,\\n\" +\n" +
                "                \"\\\"data\\\": \\\"\\\",\\n\" +\n" +
                "                \"\\\"msg\\\": \\\"没有新推送\\\"";
    }

    public interface playhistoryReturnEx {
        String addSuccessReturn = " \"code\": 0,\n" +
                "            \"data\":{\n" +
                "        \"videoVoList\":[\n" +
                "        {\n" +
                "            \"videoPublisharea\": \"内地\",\n" +
                "                \"videoDirector\": \"内详\",\n" +
                "                \"videoName\": \"村长趣哪鹅\",\n" +
                "                \"typeName\": \"综艺\",\n" +
                "                \"videoActor\": \"内详\",\n" +
                "                \"typeId\": 3,\n" +
                "                \"videoId\": 7001,\n" +
                "                \"videoPublishyear\": 0,\n" +
                "                \"videoNote\": \"2014-09-13 期\",\n" +
                "                \"videoPic\": \"uploads/allimg/171117/43cc2cebb28392eb.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"videoPublisharea\": \"内地\",\n" +
                "                \"videoDirector\": \"内详\",\n" +
                "                \"videoName\": \"爱豆万万碎\",\n" +
                "                \"typeName\": \"综艺\",\n" +
                "                \"videoActor\": \"内详\",\n" +
                "                \"typeId\": 3,\n" +
                "                \"videoId\": 7002,\n" +
                "                \"videoPublishyear\": 0,\n" +
                "                \"videoNote\": \"2016-07-13 期\",\n" +
                "                \"videoPic\": \"uploads/allimg/171117/b6377146f622f600.jpg\"\n" +
                "        }\n" +
                "]\n" +
                "    },\n" +
                "            \"msg\": \"\"";

        String addFailReturn = " \"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"请重新登录\"";
        String listSuccessReturn = "\"code\": 0,\n" +
                "\"data\":{\n" +
                "\"videoVoList\":[\n" +
                "{\n" +
                "    \"videoPublisharea\": \"内地\",\n" +
                "    \"videoDirector\": \"内详\",\n" +
                "    \"videoName\": \"村长趣哪鹅\",\n" +
                "    \"typeName\": \"综艺\",\n" +
                "    \"videoActor\": \"内详\",\n" +
                "    \"typeId\": 3,\n" +
                "    \"videoId\": 7001,\n" +
                "    \"videoPublishyear\": 0,\n" +
                "    \"videoNote\": \"2014-09-13 期\",\n" +
                "    \"videoPic\": \"uploads/allimg/171117/43cc2cebb28392eb.jpg\"\n" +
                "},\n" +
                "{\n" +
                "    \"videoPublisharea\": \"内地\",\n" +
                "    \"videoDirector\": \"内详\",\n" +
                "    \"videoName\": \"爱豆万万碎\",\n" +
                "    \"typeName\": \"综艺\",\n" +
                "    \"videoActor\": \"内详\",\n" +
                "    \"typeId\": 3,\n" +
                "    \"videoId\": 7002,\n" +
                "    \"videoPublishyear\": 0,\n" +
                "    \"videoNote\": \"2016-07-13 期\",\n" +
                "    \"videoPic\": \"uploads/allimg/171117/b6377146f622f600.jpg\"\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "\"msg\": \"\"";
        String listFailReturn = "\"code\": 1,\n" +
                "    \"data\": \"\",\n" +
                "    \"msg\": \"请重新登录\"";


    }


}
