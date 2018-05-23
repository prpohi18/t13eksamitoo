<?php
	require("vpconfig.php");
	$database = "if17_kerttamm_2";
	
	// Sessiooni alustamine
	session_start();

// Tankla salvestamise funktsioon (TÖÖTAB)
	function savePart($nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel){
		$notice = "";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("INSERT INTO tanklad (id, nimi, aadress, kohvik, bensiin95, bensiin98, diisel) VALUES(?, ?, ?, ?, ?, ?, ?)");
		echo $mysqli->error;
		$stmt->bind_param("isssddd", $_SESSION["id"], $nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel);
		if($stmt->execute()){
			$notice = " Tankla on salvestatud!";
		} else {
			$notice = " Salvestamisel tekkis tõrge: " .$stmt->error;
		}
		
		$stmt->close();
		$mysqli->close();
		return $notice;
	}
// Tankla hindade muutmise funktsioon (TÖÖTAB)
	
	function updatePrices($id, $bensiin95, $bensiin98, $diisel){
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("UPDATE tanklad SET bensiin95=?, bensiin98=?, diisel=? WHERE id=$id");
		$stmt->bind_param("ddd", $bensiin95, $bensiin98, $diisel);
		if($stmt->execute()){
			echo "Hindade muutmine õnnestus";
		} else {
			echo "Tekkis viga: " .$stmt->error;
		}
		$stmt->close();
		$mysqli->close();
	}	
	
// KÕIKIDE TANKLATE KUVAMINE (TÖÖTAB)
		function createDataTable(){
		$table = '<table border="1" style="border: 1px solid black; border-collapse: collapse">' ."\n";
		$table .= "<tr> \n <th>ID</th><th>nimi</th><th>aadress</th><th>kohvik</th><th>bensiin95</th><th>bensiin98</th><th>diisel</th> \n </tr> \n";

		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("SELECT id, nimi, aadress, kohvik, bensiin95, bensiin98, diisel FROM tanklad ORDER BY id DESC");
		$stmt->bind_result($id, $nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel);
		$stmt->execute();

		while($stmt->fetch()){
			$table .= "<tr> \n <td>" .$id ."</td><td>" .$nimi ."</td><td>" .$aadress ."</td><td>" .$kohvik ."</td><td>" .$bensiin95 ."</td><td>" .$bensiin98 ."</td><td>" .$diisel ."</td> \n </tr>";
		}

		$table .= "</table> \n";
		$stmt->close();
		$mysqli->close();
		return $table;
	}
// Bensiin 95 hinna järgi tabeli kuvamine (TÖÖTAB)
		function updateTableBensiin95(){
		$table = '<table border="1" style="border: 1px solid black; border-collapse: collapse">' ."\n";
		$table .= "<tr> \n <th>ID</th><th>nimi</th><th>aadress</th><th>kohvik</th><th>bensiin95</th><th>bensiin98</th><th>diisel</th> \n </tr> \n";

		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("SELECT id, nimi, aadress, kohvik, bensiin95, bensiin98, diisel FROM tanklad ORDER BY bensiin95 ASC");
		$stmt->bind_result($id, $nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel);
		$stmt->execute();

		while($stmt->fetch()){
			$table .= "<tr> \n <td>" .$id ."</td><td>" .$nimi ."</td><td>" .$aadress ."</td><td>" .$kohvik ."</td><td>" .$bensiin95 ."</td><td>" .$bensiin98 ."</td><td>" .$diisel ."</td> \n </tr>";
		}

		$table .= "</table> \n";
		$stmt->close();
		$mysqli->close();
		return $table;
	}
	
// Bensiin 98 hinna järgi tabeli kuvamine (TÖÖTAB)
		function updateTableBensiin98(){
		$table = '<table border="1" style="border: 1px solid black; border-collapse: collapse">' ."\n";
		$table .= "<tr> \n <th>ID</th><th>nimi</th><th>aadress</th><th>kohvik</th><th>bensiin95</th><th>bensiin98</th><th>diisel</th> \n </tr> \n";

		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("SELECT id, nimi, aadress, kohvik, bensiin95, bensiin98, diisel FROM tanklad ORDER BY bensiin98 ASC");
		$stmt->bind_result($id, $nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel);
		$stmt->execute();

		while($stmt->fetch()){
			$table .= "<tr> \n <td>" .$id ."</td><td>" .$nimi ."</td><td>" .$aadress ."</td><td>" .$kohvik ."</td><td>" .$bensiin95 ."</td><td>" .$bensiin98 ."</td><td>" .$diisel ."</td> \n </tr>";
		}

		$table .= "</table> \n";
		$stmt->close();
		$mysqli->close();
		return $table;
	}
	
// Diisli hinna järgi tabeli kuvamine (TÖÖTAB)
		function updateTableDiisel(){
		$table = '<table border="1" style="border: 1px solid black; border-collapse: collapse">' ."\n";
		$table .= "<tr> \n <th>ID</th><th>nimi</th><th>aadress</th><th>kohvik</th><th>bensiin95</th><th>bensiin98</th><th>diisel</th> \n </tr> \n";

		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("SELECT id, nimi, aadress, kohvik, bensiin95, bensiin98, diisel FROM tanklad ORDER BY diisel ASC");
		$stmt->bind_result($id, $nimi, $aadress, $kohvik, $bensiin95, $bensiin98, $diisel);
		$stmt->execute();

		while($stmt->fetch()){
			$table .= "<tr> \n <td>" .$id ."</td><td>" .$nimi ."</td><td>" .$aadress ."</td><td>" .$kohvik ."</td><td>" .$bensiin95 ."</td><td>" .$bensiin98 ."</td><td>" .$diisel ."</td> \n </tr>";
		}

		$table .= "</table> \n";
		$stmt->close();
		$mysqli->close();
		return $table;
	}
	
// Viimati lisatud tankla kuvamise funktsioon (TÖÖTAB)
	function readLastPart(){
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("SELECT nimi FROM tanklad WHERE id = (SELECT MAX(id) FROM tanklad)");
		$stmt->bind_result($nimi);
		$stmt->execute();
		$stmt->fetch();
		$stmt->close();
		$mysqli->close();
		return $nimi;
	}
	
// Sisestuse kontrollimise funktsioon (TÖÖTAB)
	function test_input($data){
		$data = trim($data);//liigsed tühikud, TAB, reavahetuse jms
		$data = stripslashes($data);//eemaldab kaldkriipsud "\"
		$data = htmlspecialchars($data);
		return $data;
	}
?>