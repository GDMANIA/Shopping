/*主菜单动态效果*/
$(function(){
	$(".ni_categorys").hover(function(){
		$(".nic_block").show();
	},function(){
		$(".nic_block").hide();
	});
});