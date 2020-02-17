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
        window.onload = function () {
            var login = document.getElementById("login_form");
            login.onsubmit = function () {
                return checkInput();
            }
        }

        function checkInput() {
            return checkQq() && checkAge() && checkName() && checkEmail();
        }

        function checkQq(){
            var qq_input = document.getElementById("qq");
            var qq = qq_input.value;
            var qq_re = /[0-9]{9,12}/;
            var flag = qq_re.test(qq);
            if (!flag){
                alert("QQ格式有误")
            }
            return qq_re.test(qq);
        }

        function checkAge() {
            var age_input = document.getElementById("age");
            var age = parseInt(age_input.value);
            if (age > 0 && age < 120){
                return true;
            }
            alert("年龄设置有误")
            return false;

        }

        function checkName() {
            var name_input = document.getElementById("name");
            var name = name_input.value;
            if (name === null || name === "" || typeof name === "undefined"){
                alert("请输入姓名");
                return false;
            }
            return true;
        }

        function checkEmail() {
            var email_input = document.getElementById("email");
            var email = email_input.value;
            if (email === null || email === "" || typeof email === "undefined"){
                alert("请输入邮箱");
                return false;
            }
            var emailReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
            if (!emailReg.test(email)){
                alert("邮箱格式有误");
                return false;
            }
            return true;
        }

        // 利用AJAX动态载入籍贯
        $(function () {
            $.get("${pageContext.request.contextPath}/FindProvinceServlet",
                {},
                function (data) {
                    $(data).each(function (index,element) {
                       var option = document.createElement("option");
                       $(option).prop("id",$(element).id);
                       $(option).val(element.name);
                       $(option).html(element.name)
                       $(option).appendTo($("#jiguan"));
                    });
                },
                "json"
            );
        });
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/AddUserServlet" method="post" id="login_form">
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>
        <div class="form-group">
            <label>性别</label>
            <input type="radio" name="gender" value="男" checked="checked" />男
            <input type="radio" name="gender" value="女"  />女
        </div>
        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="number" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>
        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="jiguan">
                <option>--请选择籍贯--</option>
            </select>
        </div>
        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="number" id="qq" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" placeholder="请输入邮箱地址"/>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
</html>
