$.ajax({
	type:"post",
	url:"/shopping/user/getCatsAndKeys.do",
	success:function(data){
		var cats = eval("("+data+")");
		var _html = "";
		for (var i = 0; i < cats.length; i++) {
			//var keys = eval("("+cats[i].keys+")");
			//alert(keys[0].key_id);
			_html += "<li>"
				 + "<h6>"+ cats[i].cat_name +"</h6>"
				 + "<p>"
				 + "<a href='/shopping/user/gotoCatsAndKeys.do?cat_id="+cats[i].cat_id+"&key_id="+cats[i].keys[0].key_id+"'>"+cats[i].keys[0].key_name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "<a href='/shopping/user/gotoCatsAndKeys.do?cat_id="+cats[i].cat_id+"&key_id="+cats[i].keys[1].key_id+"'>"+cats[i].keys[1].key_name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "<a href='/shopping/user/gotoCatsAndKeys.do?cat_id="+cats[i].cat_id+"&key_id="+cats[i].keys[2].key_id+"'>"+cats[i].keys[2].key_name+"</a>"
				 + "</p>"
				 + "</li>";
		}
		$(".nic_block ul").html(_html);
	}
});