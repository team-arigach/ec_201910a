$(function(){
	$(".address-search2").on("click",function(){
		console.log("aaa");
		AjaxZip3.zip2addr('zipcode','','address','address');
	});
});