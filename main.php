<?php
	require("functions.php");
	$notice = "";
	$tanklad = createDataTable();
	$viimatiLisatud = readLastPart();
	
	$bensiin95Sort = updateTableBensiin95();
	$bensiin98Sort = updateTableBensiin98();
	$diiselSort = updateTableDiisel();
	
	//kui vajutati bensiin 95 järgi värksendamise nuppu
	if(isset($_POST["updateBtn95"])){
			$tanklad = $bensiin95Sort; 	
		}
	//kui vajutati bensiin 98 järgi värksendamise nuppu
	if(isset($_POST["updateBtn98"])){
			$tanklad = $bensiin98Sort; 	
		}	
	//kui vajutati diisli järgi värksendamise nuppu
	if(isset($_POST["updateBtnDiisel"])){
			$tanklad = $diiselSort; 	
		}
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css">
	<title>
		Tanklate andmebaas - kuvamine
	</title>
</head>
<body>
<div align="center" class="sansserif">
	<p><a href="lisamine.php">Tanklate lisamine</a></p>
	<p><a href="hinnad.php">Hindade muutmine</a></p>
	<p><a href="kalkulaator.html">Keskmise kütusekulu kalkulaator</a></p>
	
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
		<label>Millise kütusehinna järgi soovite tanklate tabelit kuvada?<br>
		(kuvab kõige odavamast hinnast kallimani) </label>
		<br>
		<input name="updateBtn95" class="buttonStyle" type="submit" value="Bensiin 95"><span><?php echo $notice; ?></span>
		<input name="updateBtn98" class="buttonStyle" type="submit" value="Bensiin 98"><span><?php echo $notice; ?></span>
		<input name="updateBtnDiisel" class="buttonStyle" type="submit" value="Diisel"><span><?php echo $notice; ?></span>
	</form>
	
	<h2 class="header1">Andmebaasis olevad tanklad ja nende kütusehinnad</h2>
	<div style="width: 40%">
		<?php echo $tanklad; ?>
	</div>
	
	<h2 class="header1">Viimati lisatud tankla andmebaasis on: <?php echo $viimatiLisatud; ?></h2>	
</div>

</body>
</html>