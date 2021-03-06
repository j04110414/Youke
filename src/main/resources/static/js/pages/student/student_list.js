youke.studentList = (function (youke, window, $) {


    //dataTables 对象
    var table;

    //所有角色数组
    var allRoles;

    var readyFunc = function () {

        initNation();

        initTableData();

        initPageOperate();

        //initCheckAll();
    };

    /**
     * 初始化地区结构
     */
    function initNation() {
        $.ajax({
                   type: "POST",
                   url: baseContextPath + "ajax/nation/keyvalue",
                   data: '',
                   async: false,
                   success: function (data) {
                       debugger
                       if (data.code === "6000") {
                           var options = '';
                           var nation = data.data;
                           for (var i = 0; i < nation.length; i++) {
                               options +=
                                   '<option value="' + nation[i].key + '">' + nation[i].value
                                   + '</option>';
                           }
                           $("#area").append(options);
                       }
                   },
                   error: function (request) {
                       showAjaxErrorMsg();
                   }
               });
    }

    /**
     * 初始化table数据
     */
    function initTableData() {
        table = $('#tab-studentlist').DataTable({
                                                    ajax: {
                                                        //指定数据源
                                                        url: baseContextPath + "students",
                                                        type: "post",
                                                        dataSrc: "data",
                                                        //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
                                                        "deferRender": true,
                                                        data: function (d) {
                                                            var param = {};
                                                            param.draw = d.draw;
                                                            param.start = d.start;
                                                            param.length = d.length;
                                                            var formData = $("#queryForm")
                                                                .serializeArray();//把form里面的数据序列化成数组
                                                            formData.forEach(function (e) {
                                                                param[e.name] = e.value;
                                                            });
                                                            return param;//自定义需要传递的参数。
                                                        },
                                                    },
                                                    fnDrawCallback: function () {
                                                        var api = this.api();
                                                        var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
                                                        api.column(0).nodes()
                                                            .each(function (cell, i) {
                                                                cell.innerHTML = startIndex + i + 1;
                                                            });
                                                    },
                                                    columns: [
                                                        {
                                                            "data": "id"
                                                        }, {
                                                            "data": "studentNo"
                                                        }, {
                                                            "data": "studentName"
                                                        }, {
                                                            "data": "gender",
                                                            "render": function (data) {
                                                                debugger
                                                                if (data === '1') {
                                                                    return "男";
                                                                } else if (data === '0') {
                                                                    return "女";
                                                                } else {
                                                                    return "未知";
                                                                }
                                                            }
                                                        }, {
                                                            "data": "idCard"
                                                        }, {
                                                            "data": "email"
                                                        }, {
                                                            "data": "phone"
                                                        }, {
                                                            "data": "majorName"
                                                        }, {
                                                            "data": "instituteName"
                                                        }, {
                                                            "data": "universityName"
                                                        }, {
                                                            "data": null,
                                                            "render": function (data, type, row,
                                                                                meta) {
                                                                var source = $("#tpl").html();
                                                                var template = Handlebars.compile(
                                                                    source);

                                                                var context = {
                                                                    id: row.id,
                                                                    username: row.username,
                                                                    editUrl: baseContextPath
                                                                             + "student/form?id="
                                                                             + data.id
                                                                };
                                                                var html = template(context);

                                                                return html;
                                                            }
                                                        }],
                                                    columnDefs: [
                                                        {
                                                            "visible": false,
                                                            "targets": 0
                                                        }
                                                    ]

                                                });

    }

    /**
     * 初始化页面操作
     */
    function initPageOperate() {

        //查询按钮
        $("#btn-query").on("click", function () {
            table.draw();
        });

        //刷新
        $("#btn-re").on("click", function () {
            table.draw(false);//刷新保持分页状态
        });
    }

    /**
     * 设置全选
     */
    function initCheckAll() {

        table.on("change", ":checkbox", function () {

            if ($(this).is("#checkAll")) {
                //全选
                $("#tab-userlist_wrapper :checkbox[name=checkList]")
                    .prop("checked", $(this).prop("checked"));
            } else {
                //一般复选
                var checkbox = $("#tab-userlist_wrapper :checkbox[name=checkList]");
                $("#tab-userlist_wrapper #checkAll")
                    .prop('checked', checkbox.length == checkbox.filter(':checked').length);
            }
        })

    }

    /**
     * 设置默认头像
     * @param img
     */
    var imgOnerrorFunc = function (img) {
        img.src = baseContextPath + "/" + "img/user2-160x160.jpg";
        img.onerror = null;//控制不要一直跳动
    }

    /**
     * 删除
     * @param id
     */
    var delRowFunc = function (id) {

        swal({
                 title: "您确定要删除该条数据吗？",
                 type: "warning",
                 showCancelButton: true,
                 confirmButtonText: "确定",
                 cancelButtonText: "取消",
                 closeOnConfirm: false
             }, function () {

            $.ajax({
                       url: baseContextPath + 'student/delete/' + id,
                       type: 'POST',
                       success: function (msg) {
                           if (msg.code === "6000") {

                               swal({
                                        title: "删除成功！",
                                        type: "success",
                                        timer: 1000,
                                        showConfirmButton: false
                                    });

                               table.draw();

                           } else {
                               swal({
                                        title: "删除失败！",
                                        type: "error",
                                        timer: 2000,
                                        showConfirmButton: false
                                    });
                           }
                       },
                       error: function () {
                           showAjaxErrorMsg();
                       }
                   });
        });

    }

    var checkCollage = function () {
        $.ajax({
                   type: "POST",
                   url: baseContextPath + "ajax/nation/keyvalue",
                   data: '',
                   async: false,
                   success: function (data) {
                       debugger
                       if (data.code === "6000") {
                           var options = '';
                           var nation = data.data;
                           for (var i = 0; i < nation.length; i++) {
                               options +=
                                   '<option value="' + nation[i].key + '">' + nation[i].value
                                   + '</option>';
                           }
                           $("#area").append(options);
                       }
                   },
                   error: function (request) {
                       showAjaxErrorMsg();
                   }
               });
    }

    return {
        ready: readyFunc,
        imgOnerror: imgOnerrorFunc,
        delRow: delRowFunc
    };
})(youke, window, jQuery);

youke.studentList.ready();