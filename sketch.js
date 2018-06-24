var bird;
var pipes = [];
var a = 0;
var timesHit = 0;

function miss(){
	for (var i = pipes.length-1; i >= 0; i--) {
    pipes[i].show();
    pipes[i].update();
	
	if (pipes[i].offscreen() == true && pipes[i].hits(bird) == false){
		console.log("möödas");
	}
}
}

function setup() {
  createCanvas(640, 480);
  bird = new Bird();
  pipes.push(new Pipe());
}

function timerStart(){
  var time = 0;
  var timer;
  timer = setTimeout(update, 1000);
  console.log("taimer");
  
  function update () {
    if(time >= 0) {
      time += 1;
      document.getElementById("timer").innerHTML = time;
      timer = setTimeout(update, 1000);
    }
  }
}

function draw() {
  background(0);
	
  for (var i = pipes.length-1; i >= 0; i--) {
    pipes[i].show();
    pipes[i].update();

	
    if (pipes[i].hits(bird)) {
		timesHit++;
		document.getElementById("timesHit").innerHTML = timesHit;
		
	}
    if (pipes[i].offscreen()) {
        pipes.splice(i, 1);
		a++;
		document.getElementById("a").innerHTML = a;
    }
	
  }
	
  bird.update();
  bird.show();
  

  if (frameCount % 100 == 0) {
    pipes.push(new Pipe());
  }
}

function keyPressed() {
  if (key == ' ') {
    bird.up();
    //console.log("SPACE");
  }
}