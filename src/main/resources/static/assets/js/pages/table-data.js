$(document).ready(function() {
    "use strict";
    
    
    // Datatables
    $('#example3').dataTable({
        "oLanguage" : {
            "sProcessing" : "处理中...",
            "sLengthMenu" : "显示 _MENU_ 项结果",
            "sZeroRecords" : "没有匹配结果",
            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix" : "",
            "sSearch" : "搜索:",
            "sUrl" : "",
            "sEmptyTable" : "表中数据为空",
            "sLoadingRecords" : "载入中...",
            "sInfoThousands" : ",",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上页",
                "sNext" : "下页",
                "sLast" : "末页"
            },}}
    );

/*
    $('#example2').dataTable({
        "oLanguage" : {
            "sProcessing" : "处理中...",
            "sLengthMenu" : "显示 _MENU_ 项结果",
            "sZeroRecords" : "没有匹配结果",
            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix" : "",
            "sSearch" : "搜索:",
            "sUrl" : "",
            "sEmptyTable" : "表中数据为空",
            "sLoadingRecords" : "载入中...",
            "sInfoThousands" : ",",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上页",
                "sNext" : "下页",
                "sLast" : "末页"
            },}}
    );
*/


    var t = $('#example3').DataTable();

    $.fn.isValid = function(){
        return this[0].checkValidity()
    }
    
    var table = $('#example2').DataTable({
        "columnDefs": [
            { "visible": false, "targets": 2 }
        ],
        "order": [[ 2, 'asc' ]],/*从第二行降序排序*/
        "displayLength": 25,
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;
 
            api.column(2, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="10">'+group+'</td></tr>'/*调节单独一行的长度 */
                    );
                    last = group;
                }
            } );
        },
        "oLanguage" : {
            "sProcessing" : "处理中...",
            "sLengthMenu" : "显示 _MENU_ 项结果",
            "sZeroRecords" : "没有匹配结果",
            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix" : "",
            "sSearch" : "搜索:",
            "sUrl" : "",
            "sEmptyTable" : "表中数据为空",
            "sLoadingRecords" : "载入中...",
            "sInfoThousands" : ",",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上页",
                "sNext" : "下页",
                "sLast" : "末页"
            },
        }
    } );
    // Order by the grouping
    $('#example2 tbody').on( 'click', 'tr.group', function () {
        var currentOrder = table.order()[0];
        if ( currentOrder[0] === 2 && currentOrder[1] === 'asc' ) {
            table.order( [ 2, 'desc' ] ).draw();
        }
        else {
            table.order( [ 2, 'asc' ] ).draw();
        }
    } );


    /**
     * 自己写的js
     */
    /*添加老人信息修改此处*/
    $('#add-row').on( 'click', function () {
       // alert("点击了添加按钮");
        var oldmanName = $('#oldmanName').val(),
            //oldmanSex = $("input[name='oldmanSex']:checked").val(),
            oldmanSex = $('#oldmanSex').val(),
            oldmanAge = $('#oldmanAge').val(),
            oldmanHeight = $('#oldmanHeight').val(),
            oldmanIdnumber = $('#oldmanIdnumber').val(),
            oldmanRoomnumber = $('#oldmanRoomnumber').val(),
            caregiverEmpno = $('#caregiverEmpno3').val(),
            oldmanCheckintime = $('#oldmanCheckintime').val();
           // alert(oldmanSex);
         $.ajax({
             cache: false,//每次读取的是最新的数据。
             url: "/insert/oldman",
             type: "POST",//方法类型
             dataType: "json",//预期服务器返回的数据类型
             clearForm: true,// 成功提交后，清除所有的表单元素的值.
             data: {
                 oldmanName: oldmanName,
                 oldmanSex: oldmanSex,
                 oldmanAge: oldmanAge,
                 oldmanHeight: oldmanHeight,
                 oldmanIdnumber: oldmanIdnumber,
                 oldmanRoomnumber: oldmanRoomnumber,
                 caregiverEmpno: caregiverEmpno,
                 oldmanCheckintime: oldmanCheckintime,
             },
             success: function(data) {
                 if (data.msgCode == "2000") {
                     alert("添加成功");
                     window.location.reload();
                 }
             },
             error: function(data) {
                 alert("添加失败");
                 //location.reload()
             }
          });
    });
    /*修改老人信息修改此处*/
    $('#update-row').on( 'click', function () {
        var oldmanSerialnumber = $('#oldmanSerialnumber2').val(),
            oldmanName = $('#oldmanName2').val(),
            //oldmanSex = $("input[name='oldmanSex2']:checked").val(),
            oldmanSex = $('#oldmanSex2').val(),
            oldmanAge = $('#oldmanAge2').val(),
            oldmanHeight = $('#oldmanHeight2').val(),
            oldmanIdnumber = $('#oldmanIdnumber2').val(),
            oldmanRoomnumber = $('#oldmanRoomnumber2').val(),
            caregiverEmpno = $('#caregiverEmpno2').val(),
            oldmanCheckintime = $('#oldmanCheckintime2').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/update/oldman",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                oldmanSerialnumber : oldmanSerialnumber,
                oldmanName: oldmanName,
                oldmanSex: oldmanSex,
                oldmanAge: oldmanAge,
                oldmanHeight: oldmanHeight,
                oldmanIdnumber: oldmanIdnumber,
                oldmanRoomnumber: oldmanRoomnumber,
                caregiverEmpno: caregiverEmpno,
                oldmanCheckintime: oldmanCheckintime,
            },
            success: function(data) {
                if (data.msgCode == "2000") {
                    alert("修改成功");
                    window.location.reload();
                    //window.location.href="index";
                }
            },
            error: function(data) {
                alert("修改失败");
                location.reload();
            }
        });
    });

    $('.date-picker').datepicker({
        orientation: "top auto",
        autoclose: true
    });

    /*添加看护人员信息修改此处*/
    $('#add-relatives').on( 'click', function () {
        var caregiverName = $('#caregiverName').val(),
            caregiverSex = $('#caregiverSex').val(),
            caregiverAge = $('#caregiverAge').val(),
            caregiverHeight = $('#caregiverHeight').val(),
            caregiverIdnumber = $('#caregiverIdnumber').val(),
            caregiverPhone = $('#caregiverPhone').val(),
            caregiverIntheyear = $('#caregiverIntheyear').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/caregiver",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                caregiverName: caregiverName,
                caregiverSex: caregiverSex,
                caregiverAge: caregiverAge,
                caregiverHeight: caregiverHeight,
                caregiverIdnumber: caregiverIdnumber,
                caregiverPhone: caregiverPhone,
                caregiverIntheyear: caregiverIntheyear,
            },
            success: function(data) {
                if (data.msgCode == "2000") {
                    alert("添加成功");
                    window.location.reload();
                }
            },
            error: function(data) {
                alert("添加失败");
                //location.reload()
            }
        });
    });

    /*修改看护人员信息修改此处*/
    $('#update-relatives').on( 'click', function () {
        var caregiverEmpno= $('#caregiverEmpno2').val(),
            caregiverName = $('#caregiverName2').val(),
            caregiverSex = $('#caregiverSex2').val(),
            caregiverAge = $('#caregiverAge2').val(),
            caregiverHeight = $('#caregiverHeight2').val(),
            caregiverIdnumber = $('#caregiverIdnumber2').val(),
            caregiverPhone = $('#caregiverPhone2').val(),
            caregiverIntheyear = $('#caregiverIntheyear2').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/update/caregiver",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                caregiverEmpno:caregiverEmpno,
                caregiverName: caregiverName,
                caregiverSex: caregiverSex,
                caregiverAge: caregiverAge,
                caregiverHeight: caregiverHeight,
                caregiverIdnumber: caregiverIdnumber,
                caregiverPhone: caregiverPhone,
                caregiverIntheyear: caregiverIntheyear,
            },
            success: function(data) {
                alert(data.msgCode+data.message);/*成功获取到数据*/
                location.reload();
            },
            error: function(data) {
                alert(data.msgCode+data.message);
                location.reload();
            }
        });
    });

    /*添加小组信息修改此处*/
    $('#add-groupinfo').on( 'click', function () {
        var groupId= $('#groupId').val(),
            groupName = $('#groupName').val(),
            caregiverEmpno = $('#caregiverEmpno').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/groupinfo",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                groupId:groupId,
                groupName: groupName,
                caregiverEmpno: caregiverEmpno,
            },
            success: function(data) {
                if (data.msgCode == "2000") {
                    alert("添加成功");
                    window.location.reload();
                }
            },
            error: function(data) {
                alert("添加失败");
                //location.reload()
            }
        });
    });

    /*修改分组信息修改此处*/
    $('#update-groupinfo').on( 'click', function () {
        var groupId = $('#groupId2').val(),
            groupName = $('#groupName2').val(),
            caregiverEmpno = $('#caregiverEmpno2').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/update/group",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                groupId: groupId,
                groupName: groupName,
                caregiverEmpno: caregiverEmpno,
            },
            success: function(data) {
                alert(data.msgCode+data.message);/*成功获取到数据*/
                location.reload();
            },
            error: function(data) {
                alert(data.msgCode+data.message);
                location.reload();
            }
        });
    });
    //添加看护人员分组
    $('#add-caregiverGroup').on( 'click', function () {
        var groupId= $('#groupId').val(),
            caregiverEmpno = $('#caregiverInfo').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/CaregiverGroup",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                groupId:groupId,
                caregiverEmpno: caregiverEmpno,
            },
            success: function(data) {
                if (data.msgCode == "2000") {
                    alert("添加成功");
                    window.location.reload();
                }
            },
            error: function(data) {
                alert("添加失败");
                //location.reload()
            }
        });
    });
    //修改看护人员进组信息
    $('#update-caregiverGroup').on( 'click', function () {
        var groupId = $('#groupId2').val(),
            caregiverEmpno = $('#caregiverInfo2').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/update/CaregiverGroup",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                groupId: groupId,
                caregiverEmpno: caregiverEmpno,
            },
            success: function(data) {
                alert(data.msgCode+data.message);/*成功获取到数据*/
                location.reload();
            },
            error: function(data) {
                alert(data.msgCode+data.message);
                location.reload();
            }
        });
    });
    //添加身体数据信息
    $('#add-ElderlyPhysicalData').on( 'click', function () {
        //alert("执行添加操作");
        var oldmanno= $('#oldmaninfo').val(),
            physicaldataBloodpressurelow= $('#physicaldataBloodpressurelow').val(),
            physicaldataBloodpressurehigh= $('#physicaldataBloodpressurehigh').val(),
            physicaldataWeight= $('#physicaldataWeight').val(),
            physicaldataDiet= $('#physicaldataDiet').val(),
            physicaldataDate= $('#physicaldataDate').val(),
            physicaldataRemark = $('#physicaldataRemark').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/info/ElderlyPhysicalData",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                oldmanno:oldmanno,
                physicaldataBloodpressurelow:physicaldataBloodpressurelow,
                physicaldataBloodpressurehigh:physicaldataBloodpressurehigh,
                physicaldataWeight:physicaldataWeight,
                physicaldataDiet:physicaldataDiet,
                physicaldataDate:physicaldataDate,
                physicaldataRemark: physicaldataRemark,
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
        });
    });
    //修改身体数据信息
    $('#update-ElderlyPhysicalData').on( 'click', function () {
        alert("执行修改操作");
        var physicaldataId= $('#physicaldataId2').val(),
            physicaldataBloodpressurelow= $('#physicaldataBloodpressurelow2').val(),
            physicaldataBloodpressurehigh= $('#physicaldataBloodpressurehigh2').val(),
            physicaldataWeight= $('#physicaldataWeight2').val(),
            physicaldataDiet= $('#physicaldataDiet2').val(),
            physicaldataDate= $('#physicaldataDate2').val(),
            physicaldataRemark = $('#physicaldataRemark2').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/update/ElderlyPhysicalData",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                physicaldataId:physicaldataId,
                physicaldataBloodpressurelow:physicaldataBloodpressurelow,
                physicaldataBloodpressurehigh:physicaldataBloodpressurehigh,
                physicaldataWeight:physicaldataWeight,
                physicaldataDiet:physicaldataDiet,
                physicaldataDate:physicaldataDate,
                physicaldataRemark: physicaldataRemark,
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
        });
    });
    //添加组长信息
    $('#add-caregiverleader').on( 'click', function () {
        var caregiverEmpno= $('#caregiverEmpno').val(),
            careLeaderInfoPassword = $('#caregiverleaderpwd').val();
        $.ajax({
            cache: false,//每次读取的是最新的数据。
            url: "/insert/caregiverleader",
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            clearForm: true,// 成功提交后，清除所有的表单元素的值.
            data: {
                caregiverEmpno:caregiverEmpno,
                careLeaderInfoPassword: careLeaderInfoPassword,
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
        });
    });
});