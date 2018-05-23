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
		if(isset($_POST["carmark"]) and !empty($_POST["carmark"])){
			$carmark = test_input($_POST["carmark"]);
			addmark($_POST["carmark"]);
		} else {
			echo "Palun täidke väli!";
		}
		
		
		
	} // kas vajutati nuppu lõppeb

?>

<!DOCTYPE html>
<html>
<head>
	<title>Margi üleslaadimine</title>
</head>
	<body>
	<h1>Uue automargi lisamine
	</h1>
		<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
			<input type="text" name="carmark" placeholder="Lisa mark" style="color:#888;" /> </br> </br>
			<input type="submit" value="Lae üles" name="submit">
		</form>
		<p><a href="addspareparts.php">Lisa varuosasid</a></p>
	</body>
</html>