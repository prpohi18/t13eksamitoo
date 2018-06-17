<?php
require("functions.php");

$database = "if17_seppcasp";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";

$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);

//Kas vajutati esimest kustutamise nuppu
	if(isset($_POST["delete1"])) {
		
		// Kontrollime kas väljad on täidetud
		if (isset ($_POST["playername"])){
			if (empty ($_POST["playername"])){
				echo "Palun sisesta mängijanimi, kui soovid nimekirjast kustutada!";
			} else {
				$playername = test_input($_POST["playername"]);
				deletePlayer($_POST["playername"]);

			}
		}
		if (isset ($_POST["currentteam"])){
			if (empty ($_POST["currentteam"])){
				echo "Palun sisesta mängijatiim, kui soovid nimekirjast kustutada!";
			} else {
				$currentteam = test_input($_POST["currentteam"]);
				deletePlayer($_POST["currentteam"]);

			}
		}
		if (isset ($_POST["currentnumber"])){
			if (empty ($_POST["currentnumber"])){
				echo "Palun sisesta mängijanumber, kui soovid nimekirjast kustutada!";
			} else {
				$currentnumber = test_input($_POST["currentnumber"]);
				deletePlayer($_POST["currentnumber"]);

			}
		}		
    }

//Kas vajutati teist kustutamise nuppu
	if(isset($_POST["delete2"])) {
		
		// Kontrollime kas väljad on täidetud
		if (isset ($_POST["playername"])){
			if (empty ($_POST["playername"])){
				echo "Palun sisesta mängijanimi, kui soovid nimekirjast kustutada!";
			} else {
				$playername = test_input($_POST["playername"]);
				deleteTransfer($_POST["playername"]);

			}
		}
		if (isset ($_POST["futureteam"])){
			if (empty ($_POST["futureteam"])){
				echo "Palun sisesta mängijatiim, kui soovid nimekirjast kustutada!";
			} else {
				$futureteam = test_input($_POST["futureteam"]);
				deleteTransfer($_POST["futureteam"]);

			}
		}
		if (isset ($_POST["futurenumber"])){
			if (empty ($_POST["futurenumber"])){
				echo "Palun sisesta mängijanumber, kui soovid nimekirjast kustutada!";
			} else {
				$futurenumber = test_input($_POST["futurenumber"]);
				deleteTransfer($_POST["futurenumber"]);

			}
		}		
    }	
?>


<!DOCTYPE html>
<html>
<head>
	<title>Jalgpall</title>
	<link rel="stylesheet" href="style.css">

</head>
	<body>
	<center>
	<h2>Mängijate ning ülemineku soovide lisamine</h2>
	<p><a href="addplayer.php">Lisa mängija</a></p>
	<p><a href="addtransfer.php">Lisa ülemineku soov</a></p>
	
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
	<input type="text" name="playername" placeholder="Sisesta mängija nimi" style="color:#888;" /> 
	<input type="text" name="currentteam" placeholder="Sisesta mängija tiim" style="color:#888;" /> 
	<input type="text" name="currentnumber" placeholder="Sisesta mängija number" style="color:#888;" /> 
	<input type="submit" value="Kustuta" name="delete1">
	</form>
	<?php echo listAllPlayers(); ?>
	<?php echo listAllTransfers(); ?>
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
	<input type="text" name="playername" placeholder="Sisesta mängija nimi" style="color:#888;" /> 
	<input type="text" name="futureteam" placeholder="Sisesta mängija tiim" style="color:#888;" /> 
	<input type="text" name="futurenumber" placeholder="Sisesta mängija number" style="color:#888;" /> 
	<input type="submit" value="Kustuta" name="delete2">
	</form>
	<div class="footer">
		<br>
		<p>Caspar Sepp</p>
    </div>
	</center>	
	</body>
</html>