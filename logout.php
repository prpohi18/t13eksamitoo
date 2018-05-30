<?php
require("functions.php");
require("../../config.php");
$action = "logout";
//käivitab olemasolevat sessiooni
session_start();

//hävitab sessiooni koos muutujatega ja kõik kaasnevaga
logAction($action, $_SESSION["userID"]);
session_destroy();

//suuname avalehele tagasi
header("Location: main.php");




?>
