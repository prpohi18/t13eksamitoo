<?php
	require("functions.php");
	
	$notice = "";
	
//kui vajutati tankla salvestamise nuppu
	if(isset($_POST["partBtn"])){
		
		if(isset($_POST["nimi"]) and isset($_POST["aadress"]) and isset($_POST["kohvik"]) and isset ($_POST["bensiin95"]) and isset ($_POST["bensiin98"]) and isset ($_POST["diisel"]) and !empty($_POST["nimi"]) and !empty($_POST["aadress"]) and !empty($_POST["kohvik"]) and !empty($_POST["bensiin95"]) and !empty($_POST["bensiin98"]) and !empty($_POST["diisel"])){
			$notice = savePart(test_input($_POST["nimi"]), ($_POST["aadress"]), ($_POST["kohvik"]), ($_POST["bensiin95"]), ($_POST["bensiin98"]), ($_POST["diisel"]));
		}
	}

	$tanklad = createDataTable();	
	
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css">
	<title>
		Tanklate andmebaas - lisamine
	</title>
</head>
<body>
<div align="center" class="sansserif">
	
	<p><a href="main.php">Pealeht</a></p>
	<p><a href="hinnad.php">Hindade muutmine</a></p>
	<p><a href="kalkulaator.html">Keskmise kütusekulu kalkulaator</a></p>
	<h2 class="header1">Tankla lisamine andmebaasi</h2>
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
		<label>Tankla nimi: </label>
		<input name="nimi" type="text">
		<br><br>
		<label>Tankla aadress: </label>
		<input name="aadress" type="text">
		<br><br>
		<label>Kas tanklal kohvik on olemas? </label>
		<br>
		<input type="radio" name="kohvik" value="JAH" > Jah
		<br>
		<input type="radio" name="kohvik" value="EI"> Ei
		<br><br>
		<label>Hinnad palun sisestada koma asemel punktiga! </label>
		<br>
		<label>bensiin 95 hind: </label>
		<input name="bensiin95" placeholder="bensiin 95 hind" type="text">
		<br><br>
		<label>bensiin 98 hind: </label>
		<input name="bensiin98" placeholder="bensiin 98 hind" type="text">
		<br><br>
		<label>diisli hind: </label>
		<input name="diisel" placeholder="diisli hind" type="text">
		<br><br>
		<input name="partBtn" class="buttonStyle" type="submit" value="Salvesta tankla"><span><?php echo $notice; ?></span>
	</form>

	<h2 class="header1">Andmebaasis olevad tanklad ja nende kütusehinnad</h2>
	<div style="width: 40%">
		<?php echo $tanklad; ?>
	</div>
</div>	
</body>
</html>