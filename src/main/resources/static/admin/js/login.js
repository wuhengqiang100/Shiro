layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    $ = layui.jquery;

    //登录按钮事件
    form.on("submit(login)", function (data) {
        //等待加载动画
        var loadIndex = layer.load(2, {shade: [0.3, '#333']});
        if ($('form').find('input[type="checkbox"]')[0].checked) {
            data.field.rememberMe = true;
        } else {
            data.field.rememberMe = false;
        }

        $.post(data.form.action, data.field, function (res) {
            //关闭加载动画
            layer.close(loadIndex);
            if (res.success) {
                location.href = "/" + res.url;
            } else {
                layer.msg(res.message);
                $("#randImage").click();
            }
        });
        return false;
    });

/*    $(".register").on("click", function () {
        layer.confirm('确定要跳转快捷登录么?', {icon: 3, title: '提示'}, function () {
            location.href = "/" + login.html;
        });
    });*/

    $(document).on('keydown', function() {
        if(event.keyCode == 13) {
            $(".login_btn").click();
        }
    });
  //页面加载完后执行
    $(document).ready(function () {

        if( localStorage.getItem("username")==="on"){
            $("#insertDom").before("<div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">用户名:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"username\" placeholder=\"请输入用户名\" lay-verify=\"required\" type=\"text\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }
        if( localStorage.getItem("password")==="on"){
            $("#insertDom").before("  <div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"password\" placeholder=\"密码\" lay-verify=\"required\" type=\"password\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }
        if( localStorage.getItem("tel")==="on"){
            $("#insertDom").before(" <div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">电&nbsp;&nbsp;&nbsp;&nbsp;话:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"tel\" placeholder=\"电话号码\" lay-verify=\"required\" type=\"number\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }
        if( localStorage.getItem("email")==="on"){
            $("#insertDom").before(" <div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"email\" placeholder=\"邮箱\" lay-verify=\"required\" type=\"text\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }
        if( localStorage.getItem("identity")==="on"){
            $("#insertDom").before(" <div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">身&nbsp;&nbsp;&nbsp;&nbsp;份:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"identity\" placeholder=\"身份\" lay-verify=\"required\" type=\"text\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }
        if( localStorage.getItem("sex")==="on"){
            $("#insertDom").before("<div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input class=\"layui-input\" name=\"sex\" placeholder=\"性别\" lay-verify=\"required\" type=\"text\"\n" +
                "                       autocomplete=\"off\">\n" +
                "            </div>\n" +
                "        </div>");
        }


    });
    //等路页面回车按钮功能
    /* $(document).on('keydown', function (data) {
         var data= console.log(data.field);
         if (event.keyCode == 13) {
             //等待加载动画
             var loadIndex = layer.load(2, {shade: [0.3, '#333']});
             if ($('form').find('input[type="checkbox"]')[0].checked) {
                 data.field.rememberMe = true;
             } else {
                 data.field.rememberMe = false;
             }
             $.post(data.form.action, data.field, function (res) {
                 //关闭加载动画
                 layer.close(loadIndex);
                 if (res.success) {
                     location.href = "/" + res.url;
                 } else {
                     layer.msg(res.message);
                     $("#randImage").click();
                 }
             });
             return false;
         }
     });*/

    $("#randImage").click(function () {
        this.src = "/getCaptcha?t=" + Math.random();
    });
});
