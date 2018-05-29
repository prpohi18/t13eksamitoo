<?php
$database = "if17_seppcasp";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";


	// mangija lisamine
	function addPlayer($playername, $currentteam, $currentnumber){
		$playername = ($_POST["playername"]);     
		$currentteam = ($_POST["currentteam"]);
		$currentnumber = ($_POST["currentnumber"]);
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		//kontroll, et samat mangijat ei oleks 2 tükki
		$check=mysqli_query($mysqli,"SELECT * from exam_players WHERE playername='$playername'");
		$checkrows=mysqli_num_rows($check);
		if($checkrows>0) {
			echo "Selline mängija on juba sisestatud!";
		} else {  
		//salvestab andmebaasi
			$stmt = $mysqli->prepare("INSERT INTO exam_players (playername, currentteam, currentnumber) VALUES (?, ?, ?)");
		echo $mysqli->error;
		$stmt->bind_param("ssi", $playername, $currentteam, $currentnumber);
		
		if ($stmt->execute()){
			//$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}
	}

	//ülemineku soovi lisamise funktsioon
	function addTransfer($playername, $futureteam, $futurenumber){
		$playername = ($_POST["playername"]);     
		$futureteam = ($_POST["futureteam"]);
		$futurenumber = ($_POST["futurenumber"]);
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		//Et ei oleks topelt
		$check=mysqli_query($mysqli,"SELECT * FROM exam_transfers WHERE playername='$playername' AND futureteam='$futureteam'");
		$checkrows=mysqli_num_rows($check);
		if($checkrows>0) {
			echo "Selline ülemineku soov on juba sisestatud!";
		} else {  
		//salvestab andmebaasi
			$stmt = $mysqli->prepare("INSERT INTO exam_transfers (playername, futureteam, futurenumber) VALUES (?, ?, ?)");
		echo $mysqli->error;
		$stmt->bind_param("ssi", $playername, $futureteam, $futurenumber);
		
		if ($stmt->execute()){
			//$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}
	}
	
	//sisestatud mängijate kuvamine
	function listAllPlayers(){
		$conn = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$sql = "SELECT * FROM exam_players";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {
			echo "<table><tr><th>Sisestatud mängijad";

			while($row = $result->fetch_assoc()) {
				echo "<tr><td>" . $row['playername'] . "</td><td>" . $row['currentteam'] . "</td><td>" . $row['currentnumber'] . "</td></tr>"; 
			}
			echo "</table>";
		} else {
			echo "0 results";
		}
		
	}

	//sisestatud ülemineku soovide kuvamine
	function listAllTransfers(){
		$conn = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$sql = "SELECT * FROM exam_transfers";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {
			echo "<table><tr><th>Sisestatud ülemineku soovid";

			while($row = $result->fetch_assoc()) {
				echo "<tr><td>" . $row['playername'] . "</td><td>" . $row['futureteam'] . "</td><td>" . $row['futurenumber'] . "</td></tr>"; 
			}
			echo "</table>";
		} else {
			echo "0 results";
		}
		
	}
	
	//sisestatud mängija kustutamine
	function deletePlayer($playername, $currentteam, $currentnumber){
		$playername = ($_POST["playername"]);     
		$currentteam = ($_POST["currentteam"]);
		$currentnumber = ($_POST["currentnumber"]);   
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
			$stmt = $mysqli->prepare("DELETE FROM exam_players WHERE playername='$playername' AND currentteam='$currentteam' AND currentnumber='$currentnumber'");
		echo $mysqli->error;
		$stmt->bind_param("ssi", $playername, $currentteam, $currentnumber);
		
		if ($stmt->execute()){
			$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}
		
	//sisestatud ülemineku soovi kustutamine
	function deleteTransfer($playername, $futureteam, $futurenumber){
		$playername = ($_POST["playername"]);     
		$futureteam = ($_POST["futureteam"]);
		$futurenumber = ($_POST["futurenumber"]);   
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
			$stmt = $mysqli->prepare("DELETE FROM exam_transfers WHERE playername='$playername' AND futureteam='$futureteam' AND futurenumber='$futurenumber'");
		echo $mysqli->error;
		$stmt->bind_param("ssi", $playername, $futureteam, $futurenumber);
		
		if ($stmt->execute()){
			$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}		
	
	// sisestuse kontrollimine
	function test_input($data){
		$data = trim($data);//liigsed tühikud, TAB, reavahetuse jms
		$data = stripslashes($data);//eemaldab kaldkriipsud "\"
		$data = htmlspecialchars($data);
		return $data;
	}
	
	
?>