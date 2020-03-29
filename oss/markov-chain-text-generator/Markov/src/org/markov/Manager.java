package org.markov;

public class Manager {
	int numSentances = 10;
	Generator generator = new Generator();
	String[] strings = new String[numSentances];
	String text;

	public Manager()
	{
		text = this.setTest();
//		text = Load.load(path);
	}

	public void generateSentence(){
		for (int i = 0; i < numSentances; i++) {
			strings[i] = generator.generate(text);
		}
		manage();
	}

	public String getSentence(){
		return String.join("\n",strings);
	}
	public void manage()
	{
		for (int i = 0; i < strings.length; i++) {
			String string = strings[i];
			if(!(string.endsWith("the") || string.endsWith("and")
					|| string.endsWith("take") || string.endsWith("your")
					|| string.endsWith("*is*")|| string.endsWith("each")
					|| string.endsWith("they")|| string.endsWith("like")
					|| string.endsWith("in")|| string.endsWith("my")
					|| string.endsWith("his")|| string.endsWith("our")
					|| string.endsWith("a")|| string.endsWith("she's")
					|| string.endsWith("he")|| string.endsWith(" ")
					|| string.endsWith("she")|| string.endsWith("it")
					|| string.endsWith("but")|| string.endsWith("you're")
					|| string.endsWith("on")|| string.endsWith("or")
					|| string.endsWith("introduce")|| string.endsWith("you")
					|| string.endsWith("that's")|| string.endsWith("their")
					|| string.endsWith("ain't")|| string.endsWith("start")
					|| string.endsWith("a Go")|| string.endsWith("a go")
					|| string.endsWith("of")|| string.endsWith("just")
					|| string.endsWith("not")|| string.endsWith("if")
					|| string.endsWith("than")|| string.endsWith("are")
					|| string.endsWith("at")|| string.endsWith("to")
					|| string.endsWith("is")|| string.endsWith(",")
					|| string.endsWith(":")|| string.endsWith(";")
					|| string.endsWith("I'll get")|| string.endsWith("You'll get")
					|| string.endsWith("Don't be")|| string.endsWith("You're totally")
					|| string.matches("^\\w*[\\.\\?\\!]")) || string.matches("[A-Z]+[a-z']+[\\.\\?\\!]*$"))
			{
				if(!string.matches("[\\.\\?\\!\\,]$"))
				{
					string = string + ".";
				}
				if(string.startsWith(" "))string = string.substring(1);
				if(string.startsWith(" "))string = string.substring(1);
				if(string.startsWith(" "))string = string.substring(1);
				strings[i] = string.substring(0, 1).toUpperCase() + string.substring(1);
			}
			else
			{
				strings[i] = "\"" + string +"\"";
			}
		}
	}

	private String setTest(){
		return "I'll swing by and sign the contracts, alright? Just ignore the bodies!\n" +
				"Only because I'm living out here away from the Bean Machines, and the bankers?\n" +
				"I don't know what you're talking about. I don't agree with what you're saying. You're talking bullshit. And you're trying to wind me up. But I'm very, very angry, and I want this conversation to stop right away.\n" +
				"You're like every other asshole. You made a bit of money, and you became a turd.\n" +
				"I'm honest, alright? You're the hypocrite.\n" +
				"Start writing those names on tombstones, 'cause I'm on the way to your lab, and we're going to see how much of a family meth business you got when I'm done!\n" +
				"All! Of! You! Are! Going! To! Die!\n" +
				"This *is* the fuck, my soggy friend! You are out of business. The Lost MC are out of business. The guns and crank in this area go through Trevor Philips Enterprise, or they ain't going!\n" +
				"You can jerk me off if I get bored. I'm kidding! You can suck me off.\n" +
				"HE COULD NOT deal with people talking about him. It�s taken me some time since he died to get used to talking about him because I was under such strict instructions not to. But he fucked up something really major. He made a really dumb, bad decision. And it�s my right now to ignore all the other things that I thought were dumb, too. Maybe if I hadn�t felt I couldn�t talk about him to other people this wouldn�t have happened. I�m not going to let those preferences that led, in one way or another, to him killing himself guide my life anymore. I reject them.\n" +
				"Like any wide-spread activity, the selfie is not immune to tragedy. In pursuit of the ultimate profile pic, stick-yielding youths often go to extremes: They perch themselves on cliffs. They pose beside wild animals. They play chicken with oncoming trains. And sometimes, they don�t make it out alive.\n" +
				"The Chinese game is viewed as a much tougher challenge than chess for computers because there are many more ways a Go match can play out.\n" +
				"One independent expert called it a breakthrough for AI with potentially far-reaching consequences.\n" +
				"It now plays different versions of itself millions and millions of times, and each time it gets incrementally better. It learns from its mistakes.\n" +
				"The reasons it was quicker than people expected was the pace of the innovation going on with the underlying algorithms and also how much more potential you can get by combining different algorithms together\n" +
				"what was the big plan? You think I was gonna take the fake cash, save face, and walk away with my tail between my legs?\n" +
				"No. I just wanted to piss you off before I killed you.\n" +
				"Who does this guy think he is? Now I gotta dress like a chump as well as hang out with him? I like this shirt.\n" +
				"you have my personal assurance that I'm gonna get you your money back, and the drugs, and I'm gonna mail you the dicks of those responsible.\n" +
				"Just think of this as socialism in action.\n" +
				"That's my style because I seem to have this problem in my life with unreliable people. Don't be an unreliable person.\n" +
				"Oh she's on fire! Luckily I've never have been... I've done a lot of crazy things, I can tell you that... but I've never been on fire... at least not to my knowledge.\n" +
				"Our ancestors didn't eat chicken wings, they lived at one with nature and their eco-system. Existing on a diet of nuts, berries and leafy vegetables.\n" +
				"IT'S A MIRACLE!\n" +
				"Hey let's get this outta here. God knows what it is but he seems to want it badly enough so it must be worth something.\n" +
				"I've never killed no Yakuza!\n" +
				"Go and introduce a bat to his face. Then take his car, respray it. I want compensation for this insult.\n" +
				"Some Diablo scumbags has been pimping their scuzzy bitches in my backyard. Go take care of things for me.\n" +
				"When trouble looms, the fool turns his back, while the wise man faces it down.\n" +
				"You gonna die now, Homes!\n" +
				"I got a package for ya, special delivery! Hahaha!\n" +
				"I see pain in your future!\n" +
				"This is really going nowhere. Do you have anything else to say?\n" +
				"I'm going to kill you.\n" +
				"We're gonna KILL YOU!\n" +
				"Data mining, why it's better than pure statistics.\n" +
				"Anchors are a different breed. They do not match any character at all. Instead, they match a position before, after, or between characters.\n" +
				"Bits of perpetual unchangingness.\n" +
				"So long waves of matter, strained, twisted sharply.\n" +
				"So they are going to undulate across him and the species.\n" +
				"The barman reeled for every particle of Gold streaked through her eye.\n" +
				"We've met, haven't they? Look, said Ford never have good time you are merely a receipt.\n" +
				"The silence was delighted.\n" +
				"He's a good kid? A good kid? Why? Does he help the fucking poor? No. He sits on his ass all day, smoking dope and jerking off while he plays that fucking game. If that's our standard for goodness... then no wonder this country's screwed.\n" +
				"Why did I move here? I guess it was the weather. Or the... Ah, I don't know, that thing. That magic. You see it in the movies. I wanted to retire. From what I was doing, you know? From that, that... line of work. Be a good guy for once, a family man. So, I bought a big house. Came here, put my feet up, and thought I'd be a dad like all the other dads. My kids, would be like the kids on TV, we play ball and sit in the sun... But well, you know how it is.\n" +
				"You tell me exactly what you want, and I will very carefully explain to you why it cannot be.\n" +
				"Start writing those names on tombstones, 'cause I'm on the way to your lab, and we're going to see how much of a family meth business you got when I'm done!\n" +
				"What a shit show. I'll tell you what, you could take this desert, and stick it. My life may be a world of pain, but from here on out it's gonna be cool, comfortable, air-conditioned pain.\n" +
				"I mean, I admit I'm a bad piece of work. But that guy? That piece of shit! No boundaries. No sense of when to back off. No nothing! Twenty four seven insanity! Day in and day out! All the time! Never regretted nothing. Never cared for nothing. Well, fuck him. I mean... there's gotta be a limit, kid. You know? A point where even assholes like us say enough is e-fucking-nough. Human stew... that's my limit. I know that now.\n" +
				"My bad, homie. I picked C. Ain't that a bitch?\n" +
				"You're like every other asshole. You made a bit of money, and you became a turd.\n" +
				"Go fuck yourself. Are you some kind of pure, morally justifiable asshole? What, because you're... You're totally psychotic, somehow it's okay?\n" +
				"I'm honest, alright? You're the hypocrite.\n" +
				"This racist insulted me.\n" +
				"Arrgh!\n" +
				"Hey, what's the problem, dog?\n" +
				"Yeah, whatever, dog. It's either this or dealing dimebags. The bullets come crackin' at yo ass either way.\n" +
				"I'm no intelligent businessman like you. But the way I see it, there's two great evils that bedevil American capitalism of the kind you practice: Number one is outsourcing. You paid a private company to do your dirty work, and then you under paid that company because you thought you were big enough and bad enough that you didn't have to play by the rules. Oh, number two: off-shoring your profits.\n" +
				"You are alone, you pathetic psychopath!\n" +
				"I heard lot's of bangin', and screamin', but not the nice kind.\n" +
				"Mine ain't nothin' special, but this boy gets the job done.\n" +
				"I'm not gonna ask you again!\n" +
				"Did you kill him?\n" +
				"What kinda fucking animal do you take me for? No, I didn't kill him!\n" +
				"Man, I don't know y'all. We done.\n" +
				"Hey, we ain't done yet, homie. Not yet.\n" +
				"I'll cum in your ear when I get my hands on you.\n" +
				"Come in, come in.\n" +
				"Well that's perfect, then we can get back to the kind of capitalism we practice.\n" +
				"So, pal, what's the source of conflict this time?\n" +
				"Is that sarcasm?\n" +
				"What? Shit, I don't know what to say about that one.\n" +
				"I am inclined to diametrically opposite conclusions in constitution, more important, when used either at least tendency goes on the capacity of varieties as varieties. If we have failed to perfect fertility.\n" +
				"You have no idea. She panicked, went crazy and ran into it, but I saved the print so we still got our movie.\n" +
				"You think we can't do that? We can. It's in our guidelines.\n" +
				"We're gonna need a crew. I could round up some of the old guys.\n" +
				"Ugh, this is pointless. Everyone in here has done something illegal.\n" +
				"Shake what your daddies gave you.\n" +
				"Back from the dead, motherfucker!\n" +
				"You like that, huh?\n" +
				"Now we talkin'!\n" +
				"Let's be clear: only an idiot joins the cops.\n" +
				"Boy, you work hard for your living. Boil it all down for me, how much you think you make per senseless killing? A couple nickels?\n" +
				"The shit's about to hit the fan.\n" +
				"If you're gonna give me a sob story, I'm gonna rip your fucking throat out and shove a turd down the hole!\n" +
				"You're, like, finally using your powers of selfishness and rage for, like, good. Not an objective, universal good, but a subjective, what's-in-our-best-interest kind of good.\n" +
				"Here, can we just get a quick photo together?\n" +
				"I really think I can't treat you anymore. The fact is... I'm in love with you.\n" +
				"Okay, I'm just kidding, that's not true. See? You're learning.\n" +
				"I'm gonna be famous! Ha, think of the fucking tail. All those fucking bitches who called me pube head, they'll be lining up to suck it now.\n" +
				"Uh, best not to think too closely about what I just said. It's not like I'll be using real names. I-I'm really discreet. Bye now.\n" +
				"Next message left on the line was from this guy...\n" +
				"And when I was growing up, we had nine planets. Now it's eight. Where did the other one go? I mean, that is some shit! When you can be the government, you can make a planet disappear. Where did they put it? And who's living there?\n" +
				"You know, you're a, you're a resourceful guy getting past those trained killers. I need someone like you in my organization.\n" +
				"You're looking at it rationally - there are people who are useful to you and people who ain't, and the people who ain't got to go. Me, I'm not rational. I don't care if you're useful or not. I feel like taking you out, Devo, so that's what I'm doing.\n" +
				"This is a serious offer! Work for me, you'll have everything you've ever wanted.\n" +
				"All I've ever wanted is to watch you drift in and out of consciousness as you're slowly disemboweled.\n" +
				"Yes! Yes! That's the kind of creativity I need on my team. Come on, come on, let me out of here.\n" +
				"Oh yeah, a fellow entrepreneur. Let me buy a stake, give you money to grow.\n" +
				"I don't know if you heard, but I'm kind of gold rich right now. So you got precisely nothing to offer. Prepare for the end, my friend.\n" +
				"Next caller! Inseminate me! No, no, no. That's wrong. Speaketh to me.\n" +
				"And you, you're not even being fair.\n" +
				"Fair is you calling me a bitch?\n" +
				"That was once.\n" +
				"And just then, just when he hit rock bottom, he met a fat, silver-tongued troll under a bridge.\n" +
				"You definitely got hit up inside.\n" +
				"Motherfucker, I got respect for reality.\n" +
				"I'm sorry, man. You have been true with me. But the truth ain't what I'm interested in.\n" +
				"You fucking Judas! You're just like him!\n" +
				"Hey, I work two jobs, man. I'll take any break I can get.\n" +
				"Put your weapon down!\n" +
				"Oh, well, too bad. I don't know what you can do 'bout that.\n" +
				"I am serious about this. He will die!\n" +
				"Bitch-ass bullshit.\n" +
				"I'm not dead yet.\n" +
				"I think I'll take this back.\n" +
				"At the beginning of the 21st century, the Umbrella Corporation had become the largest commercial entity in the United States. Nine out of every ten homes contain its products. Its political and financial influence is felt everywhere. In public, it is the world's leading supplier of computer technology, medical products, and healthcare. Unknown, even to its own employees, its massive profits are generated by military technology, genetic experimentation, and viral weaponry.\n" +
				"All the people that were working here are dead.\n" +
				"Even in death the human body still is active. Hair and finger nails continue to grow, new cells are produced, and the brain itself holds a small electrical charge that takes months to dissipate. The T-virus provides a massive jolt, both to cellular growth, and to those trace electrical impulses. Put quite simply, it reanimates the body.\n" +
				"Not fully. The subjects have the simplest of motor functions. Perhaps a little memory, but virtually no intelligence. They're driven by the basest of impulses, the most basic needs.\n" +
				"I'm not sure I want to remember what went on down here.\n" +
				"No! Don't go!\n" +
				"I'll finish this once and for all. Say hello to my comrades you've killed.\n" +
				"Hey, you know I always keep my promises.\n" +
				"Man am I glad to see you guys!\n" +
				"Shooting wildly ain't gonna get us anywhere. Stick to the shadows.\n" +
				"And let me guess, you're the one that's gonna rule this 'new age' right?\n" +
				"Oh great, I feel more crazy talk coming on.\n" +
				"He never had it to begin with. We have to stop him.\n" +
				"I can't keep running away, I have to face the truth - accept responsibility. That's the only way I'll ever remember. The only way I'll get my life back.\n" +
				"If they're killing unarmed civilians, they're gonna really love us.\n" +
				"I guess the muscle memory saved me there.\n" +
				"I'm gonna need a hand here. Make your way back to me ASAP.\n" +
				"Each and every one of you may be ready to die for our cause, but It's my job to make sure we all get through this alive.\n" +
				"You wanna follow me around, fine! Just make sure you stay out of my way!\n" +
				"I think we overstayed our welcome.\n" +
				"Everybody prepare for landing. Stay on guard! We can't afford for any mistakes!\n" +
				"WHOOOOAAAAAA! This hall is DANGEROUS!\n" +
				"The most important thing for humanity to do right now is to invent true artificial intelligence (AI): machines or software that can think and act independently in a wide variety of situations. Once we have artificial intelligence, it can help us solve all manner of other problems.\n" +
				"But first, let us acknowledge that AI has gotten a lot of attention recently, in particular work on \"deep learning\" is being discussed in mainstream press as well as turned into startups that get bought by giant companies for bizarre amounts of money.\n" +
				"With few exceptions, the tasks that deep neural networks have excelled at are what are called pattern recognition problems.\n" +
				"You can see the general effect is to shift the curves to the right as the sample size increases. This implies that you get fewer matches with a larger sample.\n" +
				"A few notes on our methodology: We�ve defined �joke� pretty broadly here. Yes, a joke can be a one-liner built from a setup and a punch line, but it can also be an act of physical comedy. Pretending to stick a needle in your eye, or pooping in the street while wearing a wedding dress: both jokes. A joke, as defined by this list, is a discrete moment of comedy, whether from stand-up, a sketch, an album, a movie, or a TV show.\n" +
				"More powerful hardware is being made available to neuroscientists to do computation-intensive simulations. Neuropharmacologists design drugs with higher specificity, allowing researches to selectively target given receptor subtypes. Present scanning techniques are improved and new ones are under development. The list could be continued. All these innovations will give neuroscientists very powerful new tools that will facilitate their research.\n" +
				"Remember the first time you went to a show and saw your favorite band. You wore their shirt, and sang every word. You didn't know anything about scene politics, haircuts, or what was cool. All you knew was that this music made you feel different from anyone you shared a locker with. Someone finally understood you. This is what music is about.\n" +
				"Music should strike fire from the heart of man, and bring tears from the eyes of woman.\n" +
				"You can see the general effect is to shift the curves to the right as the sample size increases. This implies that you get fewer matches with a larger sample.\n" +
				"I'm building a pig from a kit.\n" +
				"I'm doing door-to-door collecting for static cling.\n" +
				"I have to go to the post office to see if I'm still wanted.\n" +
				"I'm trying to see how long I can go without saying yes.\n" +
				"my plot to take over the world is thickening.\n" +
				"the last time I went, I never came back.\n" +
				"I'm having all my plants neutered.\n" +
				"I changed the lock on my door and now I can't get out.\n" +
				"my mother would never let me hear the end of it.\n" +
				"I'm waiting to see if I'm already a winner.\n" +
				"I have an ulterior motive for writing this. I want to sharpen my technical writing skills. Any comments or criticisms about contents or style would be appreciated. Also, I will be happy to try to answer any questions you have about assembly language.\n" +
				"Prepare to die.\n" +
				"We�ve got company!\n" +
				"You have got to be kidding me.\n" +
				"You just don�t get it, do you?\n" +
				"I�ve gotta get me one of these!";
	}
	public static void main(String[] args)
	{

//		Manager manager = new Manager("res/text.txt");
		Manager manager = new Manager();
		manager.generateSentence();
		System.out.println(manager.getSentence());
	}
}
