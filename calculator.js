function dancePrice(){
	var price1pair = 30;
	var price2pair = 26;
	var price3pair = 20;
	var clubDiscount = 10;
	var partners = document.getElementById('partners').value;
	var hours = document.getElementById('hours').value;
	var myCheck = document.getElementById('myCheck').checked;
	if (partners == 1){
		if (myCheck == true) {
			var price = ((partners * hours * price1pair)*((100-clubDiscount)/100)).toFixed(2);
		} else {
			var price = (partners * hours * price1pair).toFixed(2);
		}
	} else if (partners == 2){
		if (myCheck == true) {
			var price = ((partners * hours * price2pair)*((100-clubDiscount)/100)).toFixed(2);
		} else {
			var price = (partners * hours * price2pair).toFixed(2);
		}
	} else if (partners == 3){
		if (myCheck == true) {
			var price = ((partners * hours * price3pair)*((100-clubDiscount)/100)).toFixed(2);
		} else {
			var price = (partners * hours * price3pair).toFixed(2);
		}
	} else {
		var price = "Kontrollige partnerite arvu!";
	}
	document.getElementById('price1').innerHTML = price;
}
