function HideAreaPanel() {
    $("#source-area-select").hide();
    $("#dest-area-select").hide();
}
if (typeof EXPRESS == "undefined") {
    var EXPRESS = {};
}
function EXPRESS_Init()
{ }
$(document).ready(function () {
    EXPRESS_Init1();
})
function EXPRESS_Init1() {
    EXPRESS.indexService = {
        sourceSelect: $("#source-area-select"),
        destSelect: $("#dest-area-select"),
        sourceArea: $("#source-area"),
        destArea: $("#dest-area"),
        destMenuTip: $("<div class='menutip'>您可以使用键盘的<span class='arr'>&nbsp;&uarr;&nbsp;&darr;&nbsp;</span>键来选择</div>"),
        areaTip: $("<span class='tips'></span>"),
        placeHolderForArea: "",
        areaData: {},
        areaSelect: $(".area-select"),
        areaTxt: $(".area-t"),
        showGrade: 3, //控件需要展示的级数
        netAreaType: "net",
        init: function () {
            var self = this;
            this.map = {
                "source-area": [this.sourceSelect, this.destSelect, this.sourceArea, this.showGrade, "source"],
                "dest-area": [this.destSelect, this.sourceSelect, this.destArea, this.showGrade, "dest"]
            };
            $('html').bind("click", function (event) { self.pageClickHandler(event); });
            this.areaData = eval("(" + EXPRESS.areaData + ")");
            this.initAreaSelect();
            this.initAutoComplete();
            //设置默认发货地
            this.setSenderArea();
        },
        initAreaSelect: function () {
            var self = this, i, leng = this.areaSelect.length,
				temp, id, mapVal, curIndex;
            for (i = 0; i < leng; i++) {
                temp = this.areaSelect[i].id.split('-');
                temp.pop();
                id = temp.join("-");
                mapVal = self.map[id];
                $(this.areaSelect[i]).areaselect({
				    "source": EXPRESS.areaData,
				    "showIn": "#" + id,
				    "grade": mapVal[3],
				    "type": mapVal[4],
				    after: function (target) {
				        curIndex = target.find('li.s-tab-t.current').attr('tabIndex');
				        if (this.type == self.netAreaType) {
				            if (curIndex == self.showGrade.toString() || curIndex == 0) {
				                self.areaTip.hide();
				                target.areaselect("close");
				            }
				        } else {
				            self.areaTip.hide();
				            if (curIndex == self.showGrade.toString()) {
				                target.areaselect("close");
				            }
				        }
				    }
				}).hide();
            }
        },
        initAutoComplete: function () {
            var self = this;
            //绑定元素点击事件
            this.areaTxt.bind({"focus":function () {
			    if ($(this).val() === self.placeHolderForArea) {
			        $(this).val('');
			    }
			    $(this).css("color", "black");
			    this.select();
			    var id = this.id;
			    self.map[id][0].areaselect("open").css({
					    "top": self.map[id][2].position().top + 25,
					    "left": self.map[id][2].position().left
					});
			    if (self.map[id][1].areaselect){
			        self.map[id][1].areaselect("close");
			    }
			},
			    "blur":function () {
			        if ($(this).val() === '') {
			            $(this).val(self.placeHolderForArea).css("color", "gray");
			            $(this).attr("code","");//清除code
			        } else {
			            $(this).css("color", "black");
			        }
			    }
			}).autocomplete({
				"source": function (request, response) {
			        var id = this.id;
			        //[{ "value": "福建省-厦门市", "label": "厦门市", "exId": 0}]
			        var content = request.term.toUpperCase();
			        var Arr = new Array();
			        var areaData = eval("(" + EXPRESS.areaData + ")");
			        for (m in areaData) {
			            var obj = areaData[m];
			            if ("s" in obj) {
			                for (sub in areaData[m]) {
			                    if (sub != 'code' && sub != 'scope' && sub != 's') {
			                        if (areaData[m][sub].s[0].toUpperCase().indexOf(content) > -1 || areaData[m][sub].s[1].toUpperCase().indexOf(content) > -1 || sub.indexOf(content) > -1) {
			                            var Address = { value: "", label: "", exId: 0 };
			                            Address.value = m + "-" + sub;
			                            Address.label = sub;
			                            Arr.push(Address);
			                        }
			                    }
			                }
			            }
			        }
			        response(Arr);
			    },
			    "delay": 50,
			    "minLength": 1,
			    "position": {
			        my: "left top",
			        at: "left bottom",
			        offset: "0 28"
			    },
			    select: function (event, ui) {
			        var id = this.id;
			        var result = { isSuccess: true, errMsg: "", code: "" };
			        result = ValidateAddress(ui.item.value);
			        ui.item.code = result.code;
			        self.selectHandler(self.map[id][0], ui.item, self.map[id][3]);
			    },
			    open: function () {
			        var id = this.id;
			        $(this).after(self.destMenuTip.css({
			            "top": $(this).position().top + 26,
			            "left": $(this).position().left
			        }));
			        self.map[id][0].areaselect("close");
			    },
			    close: function (event) {
			        var id = this.id;
			        var result = { isSuccess: true, errMsg: "", code: "" };
			        result = ValidateAddress($("#" + id).val());
			        var obj = new Object();
			        obj.code = result.code;
			        obj.value = $("#" + id).val();
			        self.selectHandler(self.map[id][0], obj, self.map[id][3]);
			        self.destMenuTip.remove();
			    }
			});
        },
        pageClickHandler: function (event) {
            var target = $(event.target),
				parent = target.parents(".area-tab"),
				sId, isMenu = target.parent().attr('class') == 'ui-menu-item';
            //点击空白 关闭搜索框
            if (target.parent().attr('class') != "search-div1" && target.parent().attr('class') != "search-div2") {
                $("#source-area-select").hide();
                $("#dest-area-select").hide();
            }
        },
        selectHandler: function (target, item, grade) {//自动完成控件选择后的处理
            var codeList, nameList = item.value.split("-"), i, leng, address = {};
            if (item.code) {
                codeList = item.code.split("-");
            } else {
                codeList = [];
            }
            leng = codeList.length;
            this.areaTip.hide();
            if (leng == nameList.length) {
                target.areaselect("initAddress");
                for (i = 0; i < leng; i++) {
                    address[i + 1] = {
                        "name": nameList[i],
                        "code": codeList[i]
                    };
                }
                target.areaselect("initTab", address);
                if (leng == grade) {//选择了grade级区域后，areaselect组件隐藏，否则展示areaselect下一级tab
                    target.areaselect("close");
                }
                else {
                    target.areaselect("open");
                }
            }
        },
        setSenderArea: function () {
            var self = this;
            if(self.sourceArea.val() == "" || self.sourceArea.val() == null){
            	self.sourceArea.val(self.placeHolderForArea).css("color", "gray"); 
            }else {
                self.sourceArea.css("color", "black");
            }
            if(self.destArea.val() == "" || self.destArea.val() == null) { 
            	self.destArea.val(self.placeHolderForArea).css("color", "gray"); 
            }else {
                self.destArea.css("color", "black");
            }
        }
    };
    EXPRESS.indexService.init();
}

function ValidateAddress(name, culturename) {
    var result = { isSuccess: true, errMsg: "", code: "" };
    var areaData = eval("(" + EXPRESS.areaData + ")");
    var array = name.split('-');
    for (var i = 0; i < array.length; i++) {
        var Address = "";
        if (i == 0) {
            Address = areaData[array[i]];
            if (Address == undefined)
                result.errMsg = "不存在该地区";
            else
                result.code = Address.code;
        }
        else if (i == 1) {
            Address = areaData[array[0]][array[i]];
            if (Address == undefined)
                result.errMsg = "不存在该地区";
            else
                result.code += "-" + Address.code;
        }
        else if (i == 2) {
            Address = areaData[array[0]][array[1]][array[i]];
            if (Address == undefined)
                result.errMsg = "不存在该地区";
            else
                result.code += "-" + Address.code;
        }
        if (Address == undefined)
            result.errMsg = "不存在该地区";
    }
    if (result.errMsg != "")
        result.isSuccess = false;
    return result;
}