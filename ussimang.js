var canvas;
var ctx;
var direction;
var score = 1;
var savedScore;
var keyPressed = "";
var refreshInterval;
var dead = false;
var bestScore;

//snake
var snakeRect = 20;
var snakeSpeed = 150;
var snakeSpeedMax = snakeSpeed;
var snakeHistoryX = [];
var snakeHistoryY = [];
var snakeTailX = [];
var snakeTailY = [];
var snakeX = 240;
var snakeY = 240;
var snakeColor = "white";

//food
var foodRect = 20;
var foodX;
var foodY;
var foodColor = "BLACK";

//audio
var eat = new Audio("eat.mp3");
var theme = new Audio("theme.wav");
var lose = new Audio("lose.mp3");

window.onload = function(){
	bestScore = localStorage.getItem('Score');
	canvas = document.getElementById("paber");
	ctx = canvas.getContext("2d");
	document.getElementById("startBtn").addEventListener("click", startGame);
}

function startGame(){
	document.getElementById("startBtn").innerHTML = "Mäng algas!";
	document.getElementById("startBtn").removeEventListener("click", startGame);
	console.log("Mäng algas!")
	window.addEventListener("keydown", whatKey);
	newFood();
	drawSnake();
	document.getElementById("instructions").innerHTML = "Liikumiseks kasuta nooli!";
	document.getElementById("startBtn").style = "";
	document.getElementById("paber").style.backgroundImage = "url('bg.jpg')";
	
	theme.play();
}

function whatKey(e){
	document.getElementById("instructions").innerHTML = "Parim tulemus: "+bestScore;
	document.getElementById("score").innerHTML = score-1;

	setTimeout(function(){                                                 
		if(e.keyCode == 38){
			if(keyPressed == "down" || keyPressed == "up"){} 
			else{
				clearInterval(refreshInterval);
				keyPressed = "up";
				moveUp();
			}
		}
		if(e.keyCode == 40){
			if(keyPressed == "up" || keyPressed == "down"){}
			else{
				clearInterval(refreshInterval);
				keyPressed = "down";
				moveDown();
			}
		}
		if(e.keyCode == 37){
			if(keyPressed == "right" || keyPressed == "left"){}
			else{
				clearInterval(refreshInterval);
				keyPressed = "left";
				moveLeft();
			}
		}
		if(e.keyCode == 39){
			if(keyPressed == "left" || keyPressed == "right"){}
			else{
				clearInterval(refreshInterval);
				keyPressed = "right";
				moveRight();
			}
		}
	}, snakeSpeed);
}

function drawSnake(){
	if(dead == false){
		//kontrollib kas uss on läinud üle canvase ääre
		if(snakeX<(canvas.width-canvas.width)){
			snakeX = canvas.width-snakeRect;
		}
		if(snakeX>canvas.width-snakeRect){
			snakeX = 0;
		}
		if(snakeY<(canvas.height-canvas.height)){
			snakeY = canvas.height-snakeRect;
		}
		if(snakeY>canvas.height-snakeRect){
			snakeY = 0;
		}
		
		snakeHistoryX.push(snakeX);
		snakeHistoryY.push(snakeY);
		
		snakeTailX.push(snakeX);
		snakeTailY.push(snakeY);
		//ussi saba kasvab
		if(score == 1 || score == snakeTailX.length){
			snakeTailX.shift();
			snakeTailY.shift();
			console.log(snakeTailX, snakeTailY);
		}
		
		//kontrollib surma
		checkDeath();
		
		//kustutab ussi viimase ruudu
		ctx.clearRect(snakeHistoryX[snakeHistoryX.length-1-score], snakeHistoryY[snakeHistoryY.length-1-score], snakeRect, snakeRect);
		
		//joonistab ussi esimese ruudu
		ctx.beginPath();
			ctx.rect(snakeX, snakeY, snakeRect, snakeRect);
			ctx.fillStyle = snakeColor;
			ctx.fill();
		ctx.closePath();
		//kontrollib, kas toit on söödud
		eatFood();
		//joonistab toidu
		drawFood();
	}
}

function drawFood(){
	ctx.beginPath();
		ctx.rect(foodX, foodY, foodRect, foodRect);
		ctx.fillStyle = foodColor;
		ctx.fill();
	ctx.closePath();
}

function eatFood(){
	if(foodX == snakeX && foodY == snakeY){
		eat.play();
		score++;
		snakeSpeed = snakeSpeedMax - score*2.5;
		document.getElementById("score").innerHTML = score-1;
		newFood();
	}
}

function newFood(){
	var canvasDivided = canvas.width/snakeRect-1;
	foodX = Math.round(Math.random()*canvasDivided)*snakeRect; //randomize uue toidu tekkimiseks
	foodY = Math.round(Math.random()*canvasDivided)*snakeRect;
	console.log(foodX, foodY, foodRect, foodRect);
}
//funktsioon kui uss ületab enda saba
function checkDeath(){
		var j = 1;
		for(var i = 0;i<snakeTailX.length;i++){
			console.log(i, j);
			for(j;j<snakeTailX.length;j++){
				if(snakeTailX[i] == snakeTailX[j] && snakeTailY[i] == snakeTailY[j]){ //kui x ületab y telje pmst, siis dead = true
					dead = true;
					console.log("Surnud!");
					lose.play();
					canvas.width = canvas.width;
					document.getElementById("paber").style.backgroundImage = "url('snek.jpg')";
					setTimeout(function(){
						if(score>bestScore || bestScore == null){    //PÄRAST SURMA + ALERT
							localStorage.clear();
							bestScore = score-1;
							localStorage.setItem('Score', bestScore);
						}
						alert("!!Mäng läbi!!\nSinu skooriks sai "+ (score-1));
						location.reload();}, 2500);
				}
			}
		
		}
}

function moveUp(){
	if(keyPressed == "up"){
		snakeY = snakeY - snakeRect;
		drawSnake();
		refreshInterval = setInterval(function(){
			snakeY = snakeY - snakeRect;
			drawSnake();}, snakeSpeed);
	}
}

function moveDown(){
	if(keyPressed == "down"){
		snakeY = snakeY + snakeRect;
		drawSnake();
		refreshInterval = setInterval(function(){
			snakeY = snakeY + snakeRect;
			drawSnake();}, snakeSpeed);
	}
}

function moveLeft(){
		snakeX = snakeX - snakeRect;
		drawSnake();
	if(keyPressed == "left"){
		refreshInterval = setInterval(function(){
			snakeX = snakeX - snakeRect;
			drawSnake();}, snakeSpeed);
	}
}

function moveRight(){
	if(keyPressed == "right"){
		snakeX = snakeX + snakeRect;
		drawSnake();
		refreshInterval = setInterval(function(){
			snakeX = snakeX + snakeRect;
			drawSnake();}, snakeSpeed);
	}
}

//kuidas check deathi saaks kiiremaks teha