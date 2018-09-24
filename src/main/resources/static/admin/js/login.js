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

    form.on("submit(register)",function(){
        layer.alert("请求成功");
    });

    function register(){
        layer.alert("请求成功");
    }

    form.on("onclick(#register)",function(data){

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
