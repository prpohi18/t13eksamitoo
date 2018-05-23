<?php

require("functions.php");

$database = "if17_niinsimo_3";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";
$notice = "";

//Kas vajutati üleslaadimise nuppu
	if(isset($_POST["submit"])) {
		
		// Kontrollime kas väli on täidetud
		if(isset($_POST["sparepart"]) and !empty($_POST["sparepart"])){
			$sparepart = test_input($_POST["sparepart"]);
			addSparePart($_POST["sparepart"]);
		} else {
			echo "Palun täidke väli!";
		}
		
		
	} // kas vajutati nuppu lõppeb

?>

<!DOCTYPE html>
<html>
<head>
	<title>Varuosa üleslaadimine</title>
</head>
	<body>
	<h1>Uue varuosa lisamine
	</h1>
		<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
			<input type="text" name="sparepart" placeholder="Lisa varuosa" style="color:#888;" /> </br> </br>
			<input type="submit" value="Lae üles" name="submit">
		</form>
		<p><a href="addmarks.php">Tagasi markide lisamise juurde</a></p>
		<?php echo listAllParts(); ?>

	</body>
</html>