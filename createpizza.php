<?php
	require("../../config.php");
	require("functions.php");
	if(!(isset ($_SESSION["userID"]))){
		header("Location: loginregister.php");
		exit();
	}
	//echo $_SESSION["userID"];
	if (isset ($_POST["pizzaCreate"])) {
		$toppings = $_POST['toppings'];
			/*if(empty($toppings)) {
				echo("Te ei valinud ühtegi toppingut");
			} else {
				$N = count($toppings);
				echo("Te valisite ".$N ." toppingut: ");
				for($i=0; $i < $N; $i++) {
					echo($toppings[$i] . " ");
				}
			}*/
			updatePizza($name,$toppings, $_SESSION["userID"]);
			header("location:createpizza.php");
	}
	$toppingIDs = loadComponents($_SESSION["userID"]);
	echo(loadPizza($_SESSION["userID"]));
	
	function loadComponents($userid) {
		$database = "if17_marek6";
		$notice = [];
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("SELECT pizza_id FROM pizzas WHERE user_id = ? ORDER BY created DESC Limit 1");
		$stmt->bind_param("i", $userid);
		$stmt->bind_result($pizzaID);
		$stmt->execute();
		if ($stmt->fetch()) {
			$stmt->close();
			$stmt = $mysqli->prepare("SELECT toppingonpizza.topping_id FROM pizzas INNER JOIN toppingonpizza on toppingonpizza.pizza_id = pizzas.pizza_id INNER JOIN toppings on toppingonpizza.topping_id = toppings.topping_id  WHERE pizzas.pizza_id = ?");
			$stmt->bind_param("i", $pizzaID);
			$stmt->bind_result($toppingID);
			$stmt->execute();
			while ($stmt->fetch()) {
				array_push($notice, $toppingID);
			}
		
	}
	return $notice;
	}
	function loadPizza($userid) {
		$database = "if17_marek6";
		$notice = "";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("SELECT pizza_id FROM pizzas WHERE user_id = ? ORDER BY created DESC Limit 1");
		$stmt->bind_param("i", $userid);
		$stmt->bind_result($pizzaID);
		$stmt->execute();
		if ($stmt->fetch()) {
			$mysqli->close();
			$stmt->close();
			$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
			$stmt = $mysqli->prepare("SELECT toppingonpizza.topping_id, toppings.topping_name, toppings.topping_price FROM pizzas INNER JOIN toppingonpizza on toppingonpizza.pizza_id = pizzas.pizza_id INNER JOIN toppings on toppingonpizza.topping_id = toppings.topping_id  WHERE pizzas.pizza_id = ?");
			$stmt->bind_param("i", $pizzaID);
			$stmt->bind_result($toppingID, $toppingName, $toppingPrice);
			$stmt->execute();
			while ($stmt->fetch()) {
				$notice .= $toppingName .' $' .$toppingPrice;
			}
			} else {
				createPizza($userid);
			}
			return $notice;
	}	// loadPizza lõpp
	
	function createPizza($userid) {
		$database = "if17_marek6";
		$notice = "";
		$action = "created Pizza";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("INSERT INTO pizzas (user_id) VALUES (?)");
		$stmt->bind_param("i", $userid);
		$stmt->execute();
		if ($stmt->execute()) {
			$notice = "Pizza edukalt loodud";
			logAction($action , $userid);
		} else {
			
		}
	}
	function updatePizza($pizzaname, $toppings, $userid) {
		$database = "if17_marek6";
		$notice = "";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("SELECT pizza_id FROM pizzas WHERE user_id = ? ORDER BY created DESC Limit 1");
		$stmt->bind_param("i", $userid);
		$stmt->bind_result($pizzaID);
		$stmt->execute();
		echo $stmt->fetch();
		if ($stmt->execute()) {
			echo $pizzaID;
			$stmt->close();
			foreach ($toppings as $key=>$value) {
				//$stmt = mysqli->prepare("SELECT topping_id ");
				$stmt = $mysqli->prepare("INSERT INTO toppingonpizza (topping_id, pizza_id) VALUES (?, ?)");
				$topping = $toppings[$key];
				$stmt->bind_param("ii", $topping, $pizzaID);
				$stmt->execute();
				echo $stmt->error;
				$stmt->close();
		}
		}
		
	}
	
	function laeVeged($toppings) {
		$database = "if17_marek6";
		$notice = "";
		$folder = "toppings/";
		$checked = "";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("SELECT topping_id, topping_name, topping_png FROM toppings WHERE topping_type = 2");
		$stmt->bind_result($topping_id, $toppingName, $toppingpng);
		$stmt->execute();
		while ($stmt->fetch()) {
			if (in_array($topping_id, $toppings)) {
				$checked = "checked";
			} else {
				$checked = "";
			}
			//<a href="toppings/". $toppingpng .><img src="Graafika/pizza_create_pics/meat/sausage.png" height="190" /></a>
			$notice .='<li><input type="checkbox" id="cb'.$topping_id.'" name="toppings[]" value="'.$topping_id.'" '.$checked.' /> <label for="cb'.$topping_id.'"><img src ="'.$folder.$toppingpng.'"/></label>';
		}
		$mysqli->close();
		$stmt->close();
		return $notice;
	} //laeVeged lõpp
		
	function laeLihad($toppings) {
		$database = "if17_marek6";
		$notice = "";
		$folder = "toppings/";
		$checked = "";
		$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"],$GLOBALS["serverPassword"], $database);
		$stmt = $mysqli->prepare("SELECT topping_id, topping_name, topping_png FROM toppings WHERE topping_type = 1");
		$stmt->bind_result($topping_id, $toppingName, $toppingpng);
		$stmt->execute();
		while ($stmt->fetch()) {
			if (in_array($topping_id, $toppings)) {
				$checked = "checked";
			} else {
				$checked = "";
			}
			//<a href="toppings/". $toppingpng .><img src="Graafika/pizza_create_pics/meat/sausage.png" height="190" /></a>
			$notice .='<li><input type="checkbox" id="cb'.$topping_id.'" name="toppings[]" value="'.$topping_id.'" '.$checked.' /> <label for="cb'.$topping_id.'"><img src ="'.$folder.$toppingpng.'"/></label>';
		}
		$mysqli->close();
		$stmt->close();
		return $notice;
	} //laeLihad lõpp
 ?>

<!DOCTYPE html>
<html lang="et">
<head>
	<link rel="stylesheet" type="text/css" href="radiotest.css">
	<meta charset="utf-8">
	<title>Valmista Pitsa</title>
</head>
<body>
<form method = "POST">
	<div class="toppings-outer">
		<div class="toppings-inner">
				<ul name="Lihad">
					<?php echo laeLihad($toppingIDs);?>
				</ul>
		</div><!-- end toppings-inner -->
	</div><!-- end toppings-outer -->
	<div class="pizza-outer">
		<div class="pizza-inner">
			<a href="toppings/bottom.png"><img src="toppings/bottom.png" height="550" /></a>
			<input class="pizzaName" name ="pizzaCreateName" type="text" placeholder="Nimeta Pizza">
			<input class="pizzabuttons" name ="pizzaCreate" type="submit" value="Loo Pizza">
		</div><!-- pizza-inner -->
	</div><!-- pizza-outer -->
<div class="bottoms-container">
<div class="bottoms-outer">
	<div class="bottoms-inner">
		<ul name="Veged">
			<?php echo laeVeged($toppingIDs);?>
		</ul>
	</div><!-- end bottoms-inner -->
</div><!-- end bottoms-outer -->
</div><!-- end bottoms-container -->
</form>
</body>
</html>