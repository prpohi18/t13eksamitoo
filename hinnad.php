<?php
	require("functions.php");
	
	$notice = "";
	//$parts = "";
	
	//$bensiin95PriceUpdate = savePrice95();
	//$bensiinPrice98Update = savePrice98();
	//$diiselPriceUpdate = saveDiisel();	

	//kui vajutati hindade uuendamise nuppu
	if(isset($_POST["priceUpdateBtn"])){
		
		if(isset($_POST["id"]) and isset($_POST["bensiin95"]) and isset($_POST["bensiin98"]) and isset ($_POST["diisel"]) and !empty($_POST["id"]) and !empty($_POST["bensiin95"]) and !empty($_POST["bensiin98"]) and !empty($_POST["diisel"])){
			$notice = updatePrices(test_input($_POST["id"]), ($_POST["bensiin95"]), ($_POST["bensiin98"]), ($_POST["diisel"]));
		}
	}
	
	//$parts = readAllParts();
	$tanklad = createDataTable();	
	
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css">
	<title>
		Tanklate andmebaas - hindade muutmine
	</title>
</head>
<body>
<div align="center" class="sansserif">	
	<p><a href="main.php">Pealeht</a></p>
	<p><a href="lisamine.php">Tanklate lisamine</a></p>
	<p><a href="kalkulaator.html">Keskmise kütusekulu kalkulaator</a></p>
	<h2 class="header1">Tanklate kütusehindade muutmine</h2>
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
		<label>Sisesta tankla ID number: </label>
		<input name="id" placeholder="Kohustuslik lisada!" type="text">
		<br><br>
		<label>Hinnad palun sisestada koma asemel punktiga! </label>
		<br>
		<label>Kohustuslik on muuta korraga kõik hinnad! </label>
		<br><br>
		<label>bensiin 95 hind: </label>
		<input name="bensiin95" placeholder="sisesta bensiin 95 hind" type="text">
		<br><br>
		<label>bensiin 98 hind: </label>
		<input name="bensiin98" placeholder="sisesta bensiin 98 hind" type="text">
		<br><br>
		<label>diisli hind: </label>
		<input name="diisel" placeholder="sisesta diisli hind" type="text">
		<br><br>
		<input name="priceUpdateBtn" type="submit" value="Muuda hinnad"><span><?php echo $notice; ?></span>
	</form>
	<h2 class="header1">Andmebaasis olevad tanklad ja nende kütusehinnad</h2>
	<div style="width: 40%">
		<?php echo $tanklad; ?>
	</div>
</div>	
</body>
</html>