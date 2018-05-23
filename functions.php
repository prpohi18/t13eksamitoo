<?php

$database = "if17_niinsimo_3";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";
$wish = "";
$sparapart = "";
$carmark = "";
$comment = "";


	// Margi lisamise funktsioon
	function addMark($carmark){
		$carmark = ($_POST["carmark"]);
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		//Et ei oleks topelt
		$check=mysqli_query($mysqli,"SELECT * from carmarks WHERE carmark='$carmark'");
		$checkrows=mysqli_num_rows($check);
		if($checkrows>0) {
			echo "Selline mark on juba sisestatud!";
		} else {  
		//Salvestame andmebaasi
			$stmt = $mysqli->prepare("INSERT INTO carmarks (carmark) VALUES (?)");
		echo $mysqli->error;
		$stmt->bind_param("s", $carmark);
		
		if ($stmt->execute()){
			$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}
	}
	
	// Varuosa lisamise funktsioon
	function addSparePart($sparepart){
		$sparepart = ($_POST["sparepart"]);
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		//Et ei oleks topelt
		$check=mysqli_query($mysqli,"SELECT * from spareparts WHERE sparepart='$sparepart'");
		$checkrows=mysqli_num_rows($check);
		if($checkrows>0) {
			echo "Selline osa on juba sisestatud!";
		} else {  
		//Salvestame andmebaasi
			$stmt = $mysqli->prepare("INSERT INTO spareparts (sparepart) VALUES (?)");
		echo $mysqli->error;
		$stmt->bind_param("s", $sparepart);
		
		if ($stmt->execute()){
			//$GLOBALS["notice"] .= "Õnnestus";
		} else {
			$GLOBALS["notice"] .= "Ebaõnnestus";
		}
		$stmt->close();
		$mysqli->close();
		}
	}
	
	// Sisestuse kontrollimine
	function test_input($data){
		$data = trim($data);//liigsed tühikud, TAB, reavahetuse jms
		$data = stripslashes($data);//eemaldab kaldkriipsud "\"
		$data = htmlspecialchars($data);
		return $data;
	}
	
	//Sisestatud varuosade kuvamine
	function listAllParts(){
		$conn = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$sql = "SELECT sparepart FROM spareparts";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {
			echo "<table><tr><th>Sisestatud varuosad";

			while($row = $result->fetch_assoc()) {
				echo "<tr><td>".$row["sparepart"]."</td><td>";
			}
			echo "</table>";
		} else {
			echo "0 results";
		}
		
	}
	
	function allCarMarks() {
		$conn = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$sql = "SELECT carmark FROM carmarks";
		$result = $conn->query($sql);
		$resultData = [];
		if ($result->num_rows > 0) {
			// output data of each row
			while($row = $result->fetch_assoc()) {
				 $resultData = $row['carmark'];
			}
		}
	}
	
	function signPost($carmark, $sparepart, $wish, $comment){
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);

		$stmt = $mysqli->prepare("INSERT INTO userparts (carmark, sparepart, wish, comment) VALUES (?, ?, ?, ?)");
		echo $mysqli->error;
		$stmt->bind_param("iiss", $carmark, $sparepart, $wish, $comment);
		if ($stmt->execute()){
			//echo "\n Õnnestus!";
		} else {
			echo "\n Tekkis viga : " .$stmt->error;
		}
		$stmt->close();
		$mysqli->close();
	}
	

		

	
?>