async function blinkAnimation(permutatedTitle) {

    document.title = permutatedTitle;

    for(let i = 0; i < 5; i++) {

	document.title = 'ERM.COOL';
	await delay(200);
	document.title = "erm.cool";
	await delay(200);
    }

    chaserAnimation();
   
}

async function chaserAnimation() {

    let title = "erm.cool";

    let titleArray = title.split("");
    let permutatedTitleArray = titleArray;
    
    for(let i = 0; i < titleArray.length; i++) {

	if(permutatedTitleArray[i] == ".") {

	    permutatedTitleArray[i] = "|"
	    document.title = permutatedTitleArray.join("");
	    await delay(250);
	    console.log("what2");
	    permutatedTitleArray[i] = ".";
	    document.title = permutatedTitleArray.join("");
	    
	    continue;

	}

	permutatedTitleArray[i] = titleArray[i].toUpperCase();
	document.title = permutatedTitleArray.join("");
	await delay(250);
	console.log("what");
	permutatedTitleArray[i] = titleArray[i].toLowerCase();
	document.title = permutatedTitleArray.join("");
	
    }


    titleArray = title.split("");
    permutatedTitleArray = titleArray;
   
    for(let i = titleArray.length -1; i > 0; i--) {

	if(permutatedTitleArray[i] == ".") {

	    permutatedTitleArray[i] = "|"
	    document.title = permutatedTitleArray.join("");
	    await delay(250);
	    console.log("what2");
	    permutatedTitleArray[i] = ".";
	    document.title = permutatedTitleArray.join("");
	    
	    continue;

	}
	
	permutatedTitleArray[i] = titleArray[i].toUpperCase();
	document.title = titleArray.join("");
	await delay(250);
	console.log("what");
	permutatedTitleArray[i] = titleArray[i].toLowerCase();
	document.title = titleArray.join("");

	
    }

    blinkAnimation("!!!!!!!!");
    
}

function getRandomFunnyCharacter() {

    let chooseChar = Math.floor(Math.random() * 8);

    const characters = ["!","@","#","$","%","^","&","*"];

    return characters[chooseChar];

}

function delay(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function urlfoolery() {

    await delay(1000);
    
    let title = "meow <3";

    let blankTitle = [];

    let renderTitle = "";
    
    for(let i = 0; i < title.length; i++) {

	blankTitle[i] = ' ';
		 
    }
    
    let titleArray = title.split("");

    for(let i = 0; i < titleArray.length; i++) {

	blankTitle[i] = titleArray[i];
	renderTitle = blankTitle.join("");
	document.title = renderTitle;
	
	await delay(500);
		 
    }

    await delay (1000);
    
    for(let i = 0; i< titleArray.length; i++) {


	blankTitle[i] = "X";
	renderTitle = blankTitle.join("");
	document.title = renderTitle;
	
	await delay(500);
			
    }

    chaserAnimation();

}
