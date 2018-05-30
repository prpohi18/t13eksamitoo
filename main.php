<?php
	require("functions.php");
	$signupBirthMonth = null;
	
	if(!isset($_SESSION["userId"])){
		header("Location: login.php");
		exit();
	}
	
	//väljalogimine
	if(isset($_GET["logout"])){
		logout($_GET["id"]);
		session_destroy();
		header("Location: login.php");
		exit();
	}
	
?>

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Tantsumeka</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">

<div id="header">

</div>
<div id="logo">
	<h1><a href="http://greeny.cs.tlu.ee/~valevale/java/test/">Tagasi lehele</a></h1>
</div>

<hr />

<div id="page">

	<div id="content">
		<div class="post">
			<div class="entry">
			<p class="title"><strong><?php echo $_SESSION["firstname"] ." " .$_SESSION["lastname"]; ?></strong></p>

			<h3 class="title"><a href="?logout=1">Logi välja!</a></h3>
			<p><strong>Olete sisse logitud!</strong> </p>
			
</body>
</html>
