<?php
	$database = "if17_valevale";
	require("../../../config.php");
	
	
	function login($id){
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("UPDATE userstime SET login = NOW() WHERE id=?");
		echo $mysqli->error;
		$stmt->bind_param("i", $id);
		$stmt->execute();
				
		$stmt->close();
		$mysqli->close();
	}
	
	function logout($id){
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
		$stmt = $mysqli->prepare("UPDATE userstime SET logout = NOW() WHERE id=?");
		echo $mysqli->error;
		$stmt->bind_param("i", $id);
		$stmt->execute();
		
		$stmt->close();
		$mysqli->close();
	}
?>