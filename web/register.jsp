<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Title</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#user").blur(function () {
                //用户名校验
                var username = $(this).val();
                $.post("${pageContext.request.contextPath}/CheckUsernameServlet",
                    {r_username:username},
                    function (data) {
                        if (data.userExist){
                            $("#s_username").css("color","red");
                            $("#s_username").text(data.msg);
                        } else {
                            $("#s_username").css("color","green");
                            $("#s_username").text(data.msg);
                        }
                    },
                    "json");
            });
        });

        $(function () {
            //校验密码
            $("#confirm_password").blur(function () {
                if ($("#password").val() === $("#confirm_password").val()){
                    $("#s_password").css("color","green");
                    $("#s_password").text("密码格式正确");
                } else {
                    $("#s_password").css("color","red");
                    $("#s_password").text("两次密码不一致");
                }
            });
        });
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员注册</h3>
    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="r_username" class="form-control" id="user" placeholder="请输入用户名"/>
            <span id="s_username"></span>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="r_password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label for="password">确认密码：</label>
            <input type="password" name="confirm_password" class="form-control" id="confirm_password" placeholder="请确认密码"/>
            <span id="s_password"></span>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="点击注册">
        </div>

    </form>

</div>
</body>
</html>
