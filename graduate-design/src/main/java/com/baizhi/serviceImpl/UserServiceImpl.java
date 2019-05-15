package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.conf.RandomSaltUtil;
import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.dto.SexDto;
import com.baizhi.entity.Consumer;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    UserMapper userMapper;

    public void obtain(String phone) {
        redisTemplate.opsForValue().set(phone, "111111", 5, TimeUnit.MINUTES);
    }

    /*public void obtain(String phone) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败

        //随机生成验证码
        String validate = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {

            int r = random.nextInt(10); //每次随机出一个数字（0-9）

            validate = validate + r;  //把每次随机出的数字拼在一起

        }
        request.setTemplateParam("{'code':'" + validate + "'}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功,短信已发送
            //将手机号和生成的随机数存入redis 并设置redis的过期时间
            redisTemplate.opsForValue().set(phone, validate, 5, TimeUnit.MINUTES);
        }

    }*/


    @Override
    public void regist(User user, String phone) {
        String salt = RandomSaltUtil.generetRandomSaltCode();
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        String pwd = md5Hash.toHex();
        user.setPassword(pwd);
        user.setSalt(salt);
        userMapper.insertSelective(user);
    }

    @Override
    public void login(String phone, String password,String vcode, HttpSession session) {
        String validateImage = (String) session.getAttribute("validateImage");

        if (!validateImage.equalsIgnoreCase(vcode)) {
            throw new RuntimeException("验证码错误...");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(phone, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            throw new RuntimeException("账号错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            throw new RuntimeException("密码错误");
        }
    }

    @Override
    public List<Province> groupByProvince() {
        List<Province> provinces = userMapper.groupByProvince();
        return provinces;
    }

    @Override
    public List<Integer> statisCount(String data1, String data2, String data3) {
        List<Integer> list = new ArrayList<Integer>();

        Integer integer1 = userMapper.staticsCount(data1);
        Integer integer2 = userMapper.staticsCount(data2);
        Integer integer3 = userMapper.staticsCount(data3);
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);

        return list;
    }

    @Override
    public User findByPhone(String phone,HttpSession session) {
        User user1=new User();
        user1.setPhone(phone);
        User user = userMapper.selectOne(user1);
        session.setAttribute("detailUser",user);
        if(user==null) return null;
        return user;
    }

    @Override
    public void update(User user){
       userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateUser(User user, String data1, String data2, String data3) {
        //用户状态发生修改  冻结/未冻结
        userMapper.updateByPrimaryKeySelective(user);
        //实现数据表格的实时更新
        List<Integer> list = new ArrayList<Integer>();
        Integer integer1 = userMapper.staticsCount(data1);
        Integer integer2 = userMapper.staticsCount(data2);
        Integer integer3 = userMapper.staticsCount(data3);
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);

        String s = JSONObject.toJSONString(list);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-866c45c541e54fb6a416e17428daedcd");
        goEasy.publish("cmfz", s);

        //用户分布图
        Map<String, List<SexDto>> map = new HashMap<String, List<SexDto>>();

        List<SexDto> listMale = userMapper.groupBySex("男");
        List<SexDto> listFemale = userMapper.groupBySex("女");
        map.put("male", listMale);
        map.put("female", listFemale);
        String s1 = JSONObject.toJSONString(map);

        goEasy.publish("userProfile", s1);
    }

    @Override
    public void exportUser(HttpServletResponse response, HttpServletRequest request) {
        List<User> users = userMapper.selectAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setHeadPic(request.getSession().getServletContext().getRealPath("/upload/") + users.get(i).getHeadPic());
        }

        Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("用户一览表", "用户表"), User.class, users);

        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("用户表.xls", "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageBeanDto<User> queryAllUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> users = userMapper.selectAll();
        if (users.isEmpty()) {
            throw new RuntimeException("用户列表不存在...");
        }
        PageBeanDto pb = new PageBeanDto();
        pb.setRows(users);
        pb.setTotal(userMapper.selectCount(null));
        return pb;
    }

    @Override
    public String changePassword(String oldPassword, String newPassword, String newPassword2) {
        if(!newPassword.equals(newPassword2)){
            return "两次新密码输入不一致";
        }
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        String salt = user1.getSalt();
        Md5Hash md5Hash = new Md5Hash(oldPassword, salt, 1024);
        String pwd = md5Hash.toHex();

        if(!pwd.equals(user1.getPassword())){
            return "旧密码错误";
        }

        Md5Hash md5 = new Md5Hash(newPassword, salt, 1024);
        String newPass = md5.toHex();

        User user2 = new User();
        user2.setPassword(newPass);
        user2.setId(user1.getId());
        userMapper.updateByPrimaryKeySelective(user2);
        return "ok";
    }

    @Override
    public String getUsername() {
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        return user1.getUsername();
    }

    @Override
    public User queryUser() {
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        return user1;
    }

    @Override
    public String changePersonalMessage(User user) {
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        User u = new User();
        u.setPhone(principal);
        User us = userMapper.selectOne(u);
        if(!us.getUsername().equals(user.getUsername())){
            User user1 = new User();
            user1.setUsername(user.getUsername());
            User user2 = userMapper.selectOne(user1);
            if(user2!=null) return "用户名已存在";
        }
        if(!us.getPhone().equals(user.getPhone())){
            User user3 = new User();
            user3.setPhone(user.getPhone());
            User user4 = userMapper.selectOne(user3);
            if(user4!=null) return "手机号已存在";
        }
        userMapper.updateByPrimaryKeySelective(user);
        return "ok";
    }

}
