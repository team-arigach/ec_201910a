$(function() {
	calc_price();
	$(".size").on("change", function() {
		calc_price();
	});
	$(".checkbox").on("change", function() {
		calc_price();
	});
	$("#pizzanum").on("change", function() {
		calc_price();
	});
	
	//合計金額を計算する
	function calc_price() {
		var size = $(".size:checked").val();
		var topping_count = $("#topping input:checkbox:checked").length;
		var pizza_num = $("#pizzanum option:selected").val();
		if(size == "M"){
			var size_price = $("#price_m").text().replace("円", "");
			var topping_price = 200 * topping_count;
		}
		if(size == "L"){
			var size_price = $("#price_l").text().replace("円", "");
			var topping_price = 300 * topping_count;
		}
		var price = (parseInt(size_price) + topping_price) * pizza_num;
		$("#totalprice").text(price);
	};
});