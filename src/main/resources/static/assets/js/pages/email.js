$(document).ready(function() {
    
    "use strict";
    //回复框设置
    $('.summernote').summernote({
        height: 150,
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']]
        ],
        placeholder: '写下您的回复...'
	});

    //写站内信js
    //设置点击信息变色事件
    $(".noactive").click(function () {
        $(this).removeClass("noactive");
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        $(this).siblings().addClass("noactive");
    });

    //回复邮件(看护人员)
    $('#ceregiver_email_reply').on( 'click', function () {
        var msg = "您确定回复站内信吗？\n\n请确认！";
        if (confirm(msg)==true){
      //  alert("执行回复邮件操作");
        var relativesSerialnumber= $('#xinId').text(),//变为亲属编号，收信人
            caregiverEmpno= $('#myid').text(),//发信人编号
            chatlogSendcontent= $('#replytext').code(),//获取回复值操作
            chatlogSource= 32;
        //alert(chatlogSendcontent);
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/chatlog",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                relativesSerialnumber:relativesSerialnumber,
                caregiverEmpno:caregiverEmpno,
                chatlogSource:chatlogSource,
                chatlogSendcontent: chatlogSendcontent,
            },
            success: function(data) {
                if (data.msgCode == "2000") {
                    alert(data.msgCode+data.message);/*成功获取到数据*/
                    location.reload();
                }
            },
            error: function(data) {
                alert("添加失败");
                //location.reload()
            }
        }); }else{
            return false;
        }
    });
    //发送新邮件（看护人员）
    $('#ceregiver_email_insert').on( 'click', function () {
        var msg = "您确定回复站内信吗？\n\n请确认！";
        if (confirm(msg)==true){
            //  alert("执行回复邮件操作");
            var relativesSerialnumber= $('#relativesSerialnumber').val(),//变为亲属编号，收信人
                caregiverEmpno= $('#myid').text(),//发信人编号
                chatlogSendcontent= $('#chatlogSendcontent').val(),//获取回复值操作
                chatlogSource= 32;
            //alert(chatlogSendcontent);
            $.ajax({
                cache: false,//每次读取的是最新的数据。
                url: "/insert/chatlog",
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                clearForm: true,// 成功提交后，清除所有的表单元素的值.
                data: {
                    relativesSerialnumber:relativesSerialnumber,
                    caregiverEmpno:caregiverEmpno,
                    chatlogSource:chatlogSource,
                    chatlogSendcontent: chatlogSendcontent,
                },
                success: function(data) {
                    if (data.msgCode == "2000") {
                        alert(data.msgCode+data.message);/*成功获取到数据*/
                        location.reload();
                    }
                },
                error: function(data) {
                    alert("添加失败");
                    //location.reload()
                }
            }); }else{
            return false;
        }
    });
    //回复邮件（亲属）
    $('#relatives_email_reply').on( 'click', function () {
        var msg = "您确定回复站内信吗？\n\n请确认！";
        if (confirm(msg)==true){
            //  alert("执行回复邮件操作");
            var relativesSerialnumber=  $('#myid').text(),//
                caregiverEmpno=$('#xinId').text(),//
                chatlogSendcontent= $('#replytext').code(),//获取回复值操作
                chatlogSource=23;
            //alert(chatlogSendcontent);
            $.ajax({
                cache: false,//每次读取的是最新的数据。
                url: "/insert/chatlog",
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                clearForm: true,// 成功提交后，清除所有的表单元素的值.
                data: {
                    relativesSerialnumber:relativesSerialnumber,
                    caregiverEmpno:caregiverEmpno,
                    chatlogSource:chatlogSource,
                    chatlogSendcontent: chatlogSendcontent,
                },
                success: function(data) {
                    if (data.msgCode == "2000") {
                        alert(data.msgCode+data.message);/*成功获取到数据*/
                        location.reload();
                    }
                },
                error: function(data) {
                    alert("添加失败");
                    //location.reload()
                }
            }); }else{
            return false;
        }
    });
    //发送新邮件（亲属）
    $('#relatives_email_insert').on( 'click', function () {
        var msg = "您确定回复站内信吗？\n\n请确认！";
        if (confirm(msg)==true){
            //  alert("执行回复邮件操作");
            var relativesSerialnumber=  $('#myid').text(),//
                caregiverEmpno=$('#caregiverEmpno').val(),//
                chatlogSendcontent= $('#chatlogSendcontent').val(),//获取回复值操作
                chatlogSource=23;
            alert("发送新信息操作"+chatlogSendcontent);
            $.ajax({
                cache: false,//每次读取的是最新的数据。
                url: "/insert/chatlog",
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                clearForm: true,// 成功提交后，清除所有的表单元素的值.
                data: {
                    relativesSerialnumber:relativesSerialnumber,
                    caregiverEmpno:caregiverEmpno,
                    chatlogSource:chatlogSource,
                    chatlogSendcontent: chatlogSendcontent,
                },
                success: function(data) {
                    if (data.msgCode == "2000") {
                        alert(data.msgCode+data.message);/*成功获取到数据*/
                        location.reload();
                    }
                },
                error: function(data) {
                    alert("添加失败");
                    //location.reload()
                }
            }); }else{
            return false;
        }
    });
    //删除邮件（看护人员）
    $('#ceregiver_email_delete').on( 'click', function () {
        var msg = "您确定删除此条私信吗？\n\n请确认！";
        if (confirm(msg)==true){
             alert("执行删除私信操作");
            var chatlogId= $('#chatlogId').text(),
                chatlogState=2;
            alert(chatlogId);
            //alert(chatlogSendcontent);
            $.ajax({
                cache: false,//每次读取的是最新的数据。
                url: "/delete/chatlog/caregiver",
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                clearForm: true,// 成功提交后，清除所有的表单元素的值.
                data: {
                    chatlogId:chatlogId,
                    chatlogState:chatlogState,
                },
                success: function(data) {
                    if (data.msgCode == "2000") {
                        alert(data.msgCode+data.message);/*成功获取到数据*/
                        location.reload();
                    }
                },
                error: function(data) {
                    alert("操作失败");
                    //location.reload()
                }
            }); }else{
            return false;
        }
    });
    //删除邮件（亲属）
    $('#relatives_email_delete').on( 'click', function () {
        var msg = "您确定删除此条私信吗？\n\n请确认！";
        if (confirm(msg)==true){
            alert("执行删除私信操作");
            var chatlogId= $('#chatlogId').text(),
                chatlogState=3;
            alert(chatlogId);
            //alert(chatlogSendcontent);
            $.ajax({
                cache: false,//每次读取的是最新的数据。
                url: "/delete/chatlog/caregiver",
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                clearForm: true,// 成功提交后，清除所有的表单元素的值.
                data: {
                    chatlogId:chatlogId,
                    chatlogState:chatlogState,
                },
                success: function(data) {
                    if (data.msgCode == "2000") {
                        alert(data.msgCode+data.message);/*成功获取到数据*/
                        location.reload();
                    }
                },
                error: function(data) {
                    alert("操作失败");
                    //location.reload()
                }
            }); }else{
            return false;
        }
    });
});