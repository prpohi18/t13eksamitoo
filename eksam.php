<?php

require "config.php";

$action = "post_list";	
if(isset($_GET['action']) && $_GET['action']) {
	$action = $_GET['action'];
}

session_start();
switch($action) {
    case "login":
        if(isset($_POST["username"]) && isset($_POST['password'])) {
            echo json_encode(login($_POST['username'], $_POST['password']));
        } else {
            echo json_encode(["success" => false]);

        }
        break;
}

function login($username, $password){
    $mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
    $stmt= $mysqli->prepare("SELECT id, username, password FROM users WHERE username = ?");
	$stmt->bind_param("s", $username);
	$stmt->bind_result($id, $username, $passwordDB);
	$stmt->execute();

    if($stmt->fetch()){
        if(hash("sha512", $password) == $passwordDB){	
            $_SESSION["loggedIn"] = true;
        }
	}
    $stmt->close();
    $mysqli->close();
    $_SESSION["loggedIn"] = true; //kuna kasutajat pole veel debug reasons
	logi();
    return ["success" => true];	//kuna kasutajat pole veel debug reasons
    if(isset ($_SESSION["loggedIn"])){
            return ['success' => true];			
        } else {
            return ['success' => false];
        }
}

function logi(){
	 $mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["if17_ojavgret"]);
	$stmt = $mysqli->prepare("INSERT INTO logi(id, aeg, vastus) VALUES(?,?)");
	$stmt->bind_param("is", $id, $_SESSION["loggedIn"]);
	$stmt->execute();   
    $stmt->close();
    $mysqli->close();

}
?>
