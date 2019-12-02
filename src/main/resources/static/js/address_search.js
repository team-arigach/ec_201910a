$(function(){
	$(".address-search").on("click",function(){
		AjaxZip3.zip2addr('destinationZipcode','','destinationAddress','destinationAddress');
	});
});