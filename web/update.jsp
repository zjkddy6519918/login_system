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
                        if (element.name == "${user_update.address}"){
                            $(option).prop("selected",true);
                        }
                        $(option).appendTo($("#jiguan"));
                    });
                },
                "json"
            );
        });
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
        <input type="hidden" name="id" value="${requestScope.user_update.id}"/>
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" value="${requestScope.user_update.name}" class="form-control" id="name" name="name"  readonly="readonly" placeholder="请输入姓名" />
        </div>
        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user_update.gender=='男'}">
                <input type="radio" name="gender" value="男" checked="checked" />男
                <input type="radio" name="gender" value="女"  />女
            </c:if>
            <c:if test="${user_update.gender=='女'}">
                <input type="radio" name="sex" value="男" />男
                <input type="radio" name="sex" value="女" checked="checked"  />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" value="${requestScope.user_update.age}" id="age"  name="age" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="jiguan">

            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" value="${requestScope.user_update.qq}" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" value="${requestScope.user_update.email}" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
