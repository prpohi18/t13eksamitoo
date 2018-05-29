<?php

require("functions.php");

$database = "if17_seppcasp";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";
$notice = "";

//Kas vajutati üleslaadimise nuppu
	if(isset($_POST["submit"])) {
		
		// Kontrollime kas väljad on täidetud
		if (isset ($_POST["playername"])){
			if (empty ($_POST["playername"])){
				echo "Palun sisesta mängijanimi!";
			} else {
				$playername = test_input($_POST["playername"]);
				addTransfer($_POST["playername"]);

			}
		}

		if (isset ($_POST["futureteam"])){
			if (empty ($_POST["futureteam"])){
				echo "Palun sisesta mängijatiim!";
			} else {
				$futureteam = test_input($_POST["futureteam"]);
				addTransfer($_POST["futureteam"]);
			}
		}

		if (isset ($_POST["futurenumber"])){
			if (empty ($_POST["futurenumber"])){
				echo "Palun sisesta mängijanumber!";
			} else {
				$futureteam = test_input($_POST["futurenumber"]);
				addTransfer($_POST["futurenumber"]);
			}
		}
		
		
		
	} // kas vajutati nuppu lõppeb

?>

<!DOCTYPE html>
<html>
<head>
	<title>Ülemineku soov</title>
	<link rel="stylesheet" href="style.css">
</head>
	<body>
	<center>
	<h1>Uue ülemineku soovi lisamine
	</h1>
		<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
			<input type="text" name="playername" placeholder="Lisa mängija nimi" style="color:#888;" /> 
			<br>
			<input type="text" name="futureteam" placeholder="Lisa mängija tiim" style="color:#888;" /> 
			<br>
			<input type="text" name="futurenumber" placeholder="Lisa mängija number" style="color:#888;" /> </br> </br>
			<input type="submit" value="Lae üles" name="submit">
		</form>
		<p><a href="addplayer.php">Lisa mängija</a></p>
		<p><a href="index.php">Tagasi pealehele</a></p>
		<?php echo listAllTransfers(); ?>
	<div class="footer">
		<br>
		<p>Caspar Sepp</p>
    </div>
		</center>
	</body>
</html>