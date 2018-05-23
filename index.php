<?php
require("functions.php");

$database = "if17_niinsimo_3";
$serverHost = "localhost";
$serverUsername = "if17";
$serverPassword = "if17";

$mysqli = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);

//automarkide valik
$query1 = $mysqli->query("SELECT * FROM carmarks GROUP BY id");
while($array1[] = $query1->fetch_object());
array_pop($array1);

//varuosade valik
$query2 = $mysqli->query("SELECT * FROM spareparts");
while($array2[] = $query2->fetch_object());
array_pop($array2);

$carmark="";
$sparepart="";
$wish="";
$comment="";
$carmarkError = "";
$sparepartError = "";
$wishError = "";

//üleslaadimine
//Kas vajutati üleslaadimise nuppu
	if(isset($_POST["submit"])) {
		//Kontrollime kas väljad täidetud
		if (isset ($_POST["carmark"])){
			if (empty($_POST["carmark"])){
				$carmarkError ="NB! Väli on kohustuslik!";
			} else {
				$carmark =($_POST["carmark"]);
			}
		}
		
		if (isset ($_POST["sparepart"])){
			if (empty($_POST["sparepart"])){
				$sparepartError ="NB! Väli on kohustuslik!";
			} else {
				$sparepart =($_POST["sparepart"]);
			}
		}

		if (isset($_POST["wish"]) && !empty($_POST["wish"])){ //kui on määratud ja pole tühi
				$wish = ($_POST["wish"]);

			} else {
				$wishError = " (Palun vali sobiv!) Määramata!";
		}		
		

		if (isset ($_POST["comment"])){
			$comment =($_POST["comment"]);
		}else {
			$comment = NULL;
		}
		
		//andmebaasi salvestamine
		if (empty($carmarkError) and empty($sparepartError) and empty($wishError)){
			signPost($carmark, $sparepart, $wish, $comment);
		}	
		
	} // kas vajutati nuppu lõppeb
	
	//Kuvame sobivad kuulutused

	//Kas vajutatakse kustutamise nuppu
	if(isset($_POST['delete'])){
     $message= "Kustutatud";
	 //Kontrollime kas väljad täidetud
		if (isset ($_POST["carmark"])){
			if (empty($_POST["carmark"])){
				$carmarkError ="NB! Väli on kohustuslik!";
			} else {
				$carmark =($_POST["carmark"]);
			}
		}
		
		if (isset ($_POST["sparepart"])){
			if (empty($_POST["sparepart"])){
				$sparepartError ="NB! Väli on kohustuslik!";
			} else {
				$sparepart =($_POST["sparepart"]);
			}
		}

		if (isset($_POST["wish"]) && !empty($_POST["wish"])){ //kui on määratud ja pole tühi
				$wish = ($_POST["wish"]);

			} else {
				$wishError = " (Palun vali sobiv!) Määramata!";
		}		
		

		if (isset ($_POST["comment"])){
			$comment =($_POST["comment"]);
		}else {
			$comment = NULL;
		}
		signPost($carmark, $sparepart, $wish, $comment);
    }
	else {
		$message="";
	}
    

?>


<!DOCTYPE html>
<html>
<head>
	<title>Margi ja varuosa valimine</title>
</head>
	<body>
	<h3>Varuosade ost ja müük</h3>
	<form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
	
		<label>Automark</label>
		<select name="carmark">
		<?php foreach ($array1 as $option1): ?>
			<option value="<?php echo $option1->id; ?>"><?php echo $option1->carmark; ?></option>
		<?php endforeach; ?>
		</select>
		<span><?php echo $carmarkError; ?></span>
		<br><br>
		
		<label>Varuosa</label>
		<select name="sparepart">
		<?php foreach ($array2 as $option2): ?>
			<option value="<?php echo $option2->id; ?>"><?php echo $option2->sparepart; ?></option>
		<?php endforeach; ?>
		</select>
		<span><?php echo $sparepartError; ?></span>
		<br><br>
		
		<label>Tehinguliik</label>
		<input type="radio" name="wish" value="buy" /> Ost
		<input type="radio" name="wish" value="sell" /> Müük
		<span><?php echo $wishError; ?></span>
		<br><br>
		
		<label>Lisainfo</label>
		<input type="text" name="comment" placeholder="Vajadusel lisa kommentaar" /> 
		<br><br>
		
		<input type="submit" value="Lae üles" name="submit">
		<input type="submit" value="Kustuta" name="delete"><br><br>
		<?php echo $message;?>
	</form>
	
	
	<?php 
		if(isset($_POST["submit"])) {
			
			$con = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
			if($con->connect_error){
				echo 'Ühenduse viga: '.$con->connect_error;
			}else{
				$sql="SELECT spareparts.sparepart, userparts.comment, carmarks.carmark, userparts.wish 
				FROM spareparts 
				INNER JOIN userparts
				ON spareparts.id = userparts.sparepart
				INNER JOIN carmarks
				ON carmarks.id = userparts.carmark
				WHERE userparts.wish NOT LIKE '%$wish%' AND spareparts.id LIKE '%$sparepart%' AND  carmarks.id LIKE '%$carmark%'";

				$res=$con->query($sql);
			}
				$result="";
				if ($res->num_rows <1 ) {
					$result='Sobivad kuulutused puuduvad';
					echo $result;
				}
				else {
					$result='Sinule sobivad kuulutused';
					echo $result;
				}
				while($row=$res->fetch_assoc()){
					echo "<table><tr><th>Sisestatud kuulutus";
					echo "<tr><td>".'Varuosa:  '.$row["sparepart"]."</td><td>";
					echo "<tr><td>".'Mark:  '.$row["carmark"]."</td><td>";
					echo "<tr><td name='result' >".'Kommentaar:  '.$row["comment"]."</td><td>";
					echo "<tr><td>".'Soov:  '.$row["wish"]."</td><td>";
					echo "</table>";
				}

		}
		if(isset($_POST["delete"])) {
			$con = new mysqli($GLOBALS["serverHost"], $GLOBALS["serverUsername"], $GLOBALS["serverPassword"], $GLOBALS["database"]);
			if($con->connect_error){
				echo 'Ühenduse viga: '.$con->connect_error;
			}else{
				$sql="DELETE FROM userparts WHERE userparts.wish LIKE '%$wish%' AND userparts.comment LIKE '%$comment%'";

				$res=$con->query($sql);
			}
		}
	?>
	</body>
</html>