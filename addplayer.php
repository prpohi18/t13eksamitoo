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
				addPlayer($_POST["playername"]);

			}
		}

		if (isset ($_POST["currentteam"])){
			if (empty ($_POST["currentteam"])){
				echo "Palun sisesta mängijatiim!";
			} else {
				$currentteam = test_input($_POST["currentteam"]);
				addPlayer($_POST["currentteam"]);
			}
		}

		if (isset ($_POST["currentnumber"])){
			if (empty ($_POST["currentnumber"])){
				echo "Palun sisesta mängijanumber!";
			} else {
				$currentnumber = test_input($_POST["currentnumber"]);
				addPlayer($_POST["currentnumber"]);
			}
		}
		
		
		
	} // kas vajutati nuppu lõppeb

?>

<!DOCTYPE html>
<html>
<head>
	<title>Mängija lisamine</title>
	<link rel="stylesheet" href="style.css">	
</head>
	<body>
	<center>
	<h1>Uue mängija lisamine
	</h1>
		<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
			<input type="text" name="playername" placeholder="Lisa mängija nimi" style="color:#888;" /> 
			<br>
			<input type="text" name="currentteam" placeholder="Lisa mängija tiim" style="color:#888;" /> 
			<br>
			<input type="text" name="currentnumber" placeholder="Lisa mängija number" style="color:#888;" /> </br> </br>
			<input type="submit" value="Lae üles" name="submit">
		</form>
		<p><a href="addtransfer.php">Lisa ülemineku soov</a></p>
		<p><a href="index.php">Tagasi pealehele</a></p>
		<?php echo listAllPlayers(); ?>
	<div class="footer">
	<br>
		<p>Caspar Sepp</p>
    </div>
		</center>
	</body>
</html> 