import java.util.Scanner;
import java.util.Random;
import java.util.Date;
import java.util.TimeZone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.time.DateTimeException;

public class A2_001_WarmUps_Overcash {
	static boolean run = true;
	static java.util.Scanner scanner = new Scanner(System.in);
	static java.util.Random random = new Random();


	public static void main(String[] args) {																																																																			 int[] a = new int[2147483642-2147483641];
		while (run) {
			// menu opts
			System.out.println("[-1] Exit");
			System.out.println("[ 1] Random excuse for users");
			System.out.println("[ 2] Average of 3 ints");
			System.out.println("[ 3] Basic ops of 2 floats");
			System.out.println("[ 4] Celcius->Farenheit");
			System.out.println("[ 5] Farenheit->Celcius");
			System.out.println("[ 6] Miles->Kilometres");
			System.out.println("[ 7] Human time->Epoch time");
			System.out.println("[ 8] Epoch time->Human time");
			System.out.println("[ 9] Distance between 2 points");
			System.out.println("[10] Radius->SA (sphere)");
			System.out.println("[11] Use Heron's formula to get a triangle's area");
			System.out.println("[12] MPG/Gas cost calculator");
			System.out.println("[13] Coin counter");
			System.out.println("[14] Phone number gen");

			// read input 
			System.out.print("\r\n>>> ");

			String chosenItemStr = scanner.nextLine();
			System.out.println();

			System.out.print("----------------------------------------\r\n");

			// try to get a number from it and run corresponding
			try {
				int chosenItem = Integer.parseInt(chosenItemStr);
				switch(chosenItem) {
					case -1:
						coolExit(); // exit!
						break;
					case 1:
						do1();
						break;
					case 2:
						do2();
						break;
					case 3:
						do3();
						break;
					case 4:
						do4();
						break;
					case 5:
						do5();
						break;
					case 6:
						do6();
						break;
					case 7:
						do7();
						break;
					case 8:
						do8();
						break;
					case 9:
						do9();
						break;
					case 10:
						do10();
						break;
					case 11:
						do11();
						break;
					case 12:
						do12();
						break;
					case 13:
						do13();
						break;
					case 14:
						do14();
						break;
					default: // if not on menu
						System.out.println("Invalid entry");
						System.out.println("<ENTER to continue>");
						scanner.nextLine();
						break;
				}
				System.out.print("----------------------------------------\r\n");
			} catch (Exception e) { // if NaN
				System.out.println("Invalid entry");
				System.out.println("<ENTER to continue>");
				scanner.nextLine();
			}
		}
	}

	public static void coolExit() {
		System.out.println("Goodbye!");
		run = false; // fallback if something wierd happens
		System.exit(0); // Exit successfully
	}

	public static void do1() { // Random excuse of 2 lines or more
		System.out.println("Printing a random BOFH excuse\r\n\r\n");

		String[] excuses = { // BOFH excuse list, from http://pages.cs.wisc.edu/~ballard/bofh/excuses
			"clock speed",
			"solar flares",
			"electromagnetic radiation from satellite debris",
			"static from nylon underwear",
			"static from plastic slide rules",
			"global warming",
			"poor power conditioning",
			"static buildup",
			"doppler effect",
			"hardware stress fractures",
			"magnetic interference from money/credit cards",
			"dry joints on cable plug",
			"we're waiting for [the phone company] to fix that line",
			"sounds like a Windows problem, try calling Microsoft support",
			"temporary routing anomaly",
			"somebody was calculating pi on the server",
			"fat electrons in the lines",
			"excess surge protection",
			"floating point processor overflow",
			"divide-by-zero error",
			"POSIX compliance problem",
			"monitor resolution too high",
			"improperly oriented keyboard",
			"network packets travelling uphill (use a carrier pigeon)",
			"Decreasing electron flux",
			"first Saturday after first full moon in Winter",
			"radiosity depletion",
			"CPU radiator broken",
			"It works the way the Wang did, what's the problem",
			"positron router malfunction",
			"cellular telephone interference",
			"techtonic stress",
			"piezo-electric interference",
			"(l)user error",
			"working as designed",
			"dynamic software linking table corrupted",
			"heavy gravity fluctuation, move computer to floor rapidly",
			"secretary plugged hairdryer into UPS",
			"terrorist activities",
			"not enough memory, go get system upgrade",
			"interrupt configuration error",
			"spaghetti cable cause packet failure",
			"boss forgot system password",
			"bank holiday - system operating credits  not recharged",
			"virus attack, luser responsible",
			"waste water tank overflowed onto computer",
			"Complete Transient Lockout",
			"bad ether in the cables",
			"Bogon emissions",
			"Change in Earth's rotational speed",
			"Cosmic ray particles crashed through the hard disk platter",
			"Smell from unhygienic janitorial staff wrecked the tape heads",
			"Little hamster in running wheel had coronary; waiting for replacement to be Fedexed from Wyoming",
			"Evil dogs hypnotised the night shift",
			"Plumber mistook routing panel for decorative wall fixture",
			"Electricians made popcorn in the power supply",
			"Groundskeepers stole the root password",
			"high pressure system failure",
			"failed trials, system needs redesigned",
			"system has been recalled",
			"not approved by the FCC",
			"need to wrap system in aluminum foil to fix problem",
			"not properly grounded, please bury computer",
			"CPU needs recalibration",
			"system needs to be rebooted",
			"bit bucket overflow",
			"descramble code needed from software company",
			"only available on a need to know basis",
			"knot in cables caused data stream to become twisted and kinked",
			"nesting roaches shorted out the ether cable",
			"The file system is full of it",
			"Satan did it",
			"Daemons did it",
			"You're out of memory",
			"There isn't any problem",
			"Unoptimized hard drive",
			"Typo in the code",
			"Yes, yes, its called a design limitation",
			"Look, buddy:  Windows 3.1 IS A General Protection Fault.",
			"That's a great computer you have there; have you considered how it would work as a BSD machine?",
			"Please excuse me, I have to circuit an AC line through my head to get this database working.",
			"Yeah, yo mama dresses you funny and you need a mouse to delete files.",
			"Support staff hung over, send aspirin and come back LATER.",
			"Someone is standing on the ethernet cable, causing a kink in the cable",
			"Windows 95 undocumented \"feature\"",
			"Runt packets",
			"Password is too complex to decrypt",
			"Boss' kid messed up the machine",
			"Electromagnetic energy loss",
			"Budget cuts",
			"Mouse chewed through power cable",
			"Stale file handle (next time use Tupperware(tm)!)",
			"Feature not yet implemented",
			"Internet outage",
			"Pentium FDIV bug",
			"Vendor no longer supports the product",
			"Small animal kamikaze attack on power supplies",
			"The vendor put the bug there.",
			"SIMM crosstalk.",
			"IRQ dropout",
			"Collapsed Backbone",
			"Power company testing new voltage spike (creation) equipment",
			"operators on strike due to broken coffee machine",
			"backup tape overwritten with copy of system manager's favourite CD",
			"UPS interrupted the server's power",
			"The electrician didn't know what the yellow cable was so he yanked the ethernet out.",
			"The keyboard isn't plugged in",
			"The air conditioning water supply pipe ruptured over the machine room",
			"The electricity substation in the car park blew up.",
			"The rolling stones concert down the road caused a brown out",
			"The salesman drove over the CPU board.",
			"The monitor is plugged into the serial port",
			"Root nameservers are out of sync",
			"electro-magnetic pulses from French above ground nuke testing.",
			"your keyboard's space bar is generating spurious keycodes.",
			"the real ttys became pseudo ttys and vice-versa.",
			"the printer thinks its a router.",
			"the router thinks its a printer.",
			"evil hackers from Serbia.",
			"we just switched to FDDI.",
			"halon system went off and killed the operators.",
			"because Bill Gates is a Jehovah's witness and so nothing can work on St. Swithin's day.",
			"user to computer ratio too high.",
			"user to computer ration too low.",
			"we just switched to Sprint.",
			"it has Intel Inside",
			"Sticky bits on disk.",
			"Power Company having EMP problems with their reactor",
			"The ring needs another token",
			"new management",
			"telnet: Unable to connect to remote host: Connection refused",
			"SCSI Chain overterminated",
			"It's not plugged in.",
			"because of network lag due to too many people playing deathmatch",
			"You put the disk in upside down.",
			"Daemons loose in system.",
			"User was distributing pornography on server; system seized by FBI.",
			"BNC (brain not connected)",
			"UBNC (user brain not connected)",
			"LBNC (luser brain not connected)",
			"disks spinning backwards - toggle the hemisphere jumper.",
			"new guy cross-connected phone lines with ac power bus.",
			"had to use hammer to free stuck disk drive heads.",
			"Too few computrons available.",
			"Flat tire on station wagon with tapes.  (\"Never underestimate the bandwidth of a station wagon full of tapes hurling down the highway\" Andrew S. Tannenbaum) ",
			"Communications satellite used by the military for star wars.",
			"Party-bug in the Aloha protocol.",
			"Insert coin for new game",
			"Dew on the telephone lines.",
			"Arcserve crashed the server again.",
			"Some one needed the powerstrip, so they pulled the switch plug.",
			"My pony-tail hit the on/off switch on the power strip.",
			"Big to little endian conversion error",
			"You can tune a file system, but you can't tune a fish (from most tunefs man pages)",
			"Dumb terminal",
			"Zombie processes haunting the computer",
			"Incorrect time synchronization",
			"Defunct processes",
			"Stubborn processes",
			"non-redundant fan failure ",
			"monitor VLF leakage",
			"bugs in the RAID",
			"no \"any\" key on keyboard",
			"root rot",
			"Backbone Scoliosis",
			"/pub/lunch",
			"excessive collisions & not enough packet ambulances",
			"le0: no carrier: transceiver cable problem?",
			"broadcast packets on wrong frequency",
			"popper unable to process jumbo kernel",
			"NOTICE: alloc: /dev/null: filesystem full",
			"pseudo-user on a pseudo-terminal",
			"Recursive traversal of loopback mount points",
			"Backbone adjustment",
			"OS swapped to disk",
			"vapors from evaporating sticky-note adhesives",
			"sticktion",
			"short leg on process table",
			"multicasts on broken packets",
			"ether leak",
			"Atilla the Hub",
			"endothermal recalibration",
			"filesystem not big enough for Jumbo Kernel Patch",
			"loop found in loop in redundant loopback",
			"system consumed all the paper for paging",
			"permission denied",
			"Reformatting Page. Wait...",
			"..disk or the processor is on fire.",
			"SCSI's too wide.",
			"Proprietary Information.",
			"Just type 'mv * /dev/null'.",
			"runaway cat on system.",
			"Did you pay the new Support Fee?",
			"We only support a 1200 bps connection.",
			"We only support a 28000 bps connection.",
			"Me no internet, only janitor, me just wax floors.",
			"I'm sorry a pentium won't do, you need an SGI to connect with us.",
			"Post-it Note Sludge leaked into the monitor.",
			"the curls in your keyboard cord are losing electricity.",
			"The monitor needs another box of pixels.",
			"RPC_PMAP_FAILURE",
			"kernel panic: write-only-memory (/dev/wom0) capacity exceeded.",
			"Write-only-memory subsystem too slow for this machine. Contact your local dealer.",
			"Just pick up the phone and give modem connect sounds. \"Well you said we should get more lines so we don't have voice lines.\"",
			"Quantum dynamics are affecting the transistors",
			"Police are examining all internet packets in the search for a narco-net-trafficker",
			"We are currently trying a new concept of using a live mouse.  Unfortunately, one has yet to survive being hooked up to the computer.....please bear with us.",
			"Your mail is being routed through Germany ... and they're censoring us.",
			"Only people with names beginning with 'A' are getting mail this week (a la Microsoft)",
			"We didn't pay the Internet bill and it's been cut off.",
			"Lightning strikes.",
			"Of course it doesn't work. We've performed a software upgrade.",
			"Change your language to Finnish.",
			"Fluorescent lights are generating negative ions. If turning them off doesn't work, take them out and put tin foil on the ends.",
			"High nuclear activity in your area.",
			"What office are you in? Oh, that one.  Did you know that your building was built over the universities first nuclear research site? And wow, aren't you the lucky one, your office is right over where the core is buried!",
			"The MGs ran out of gas.",
			"The UPS doesn't have a battery backup.",
			"Recursivity.  Call back if it happens again.",
			"Someone thought The Big Red Button was a light switch.",
			"The mainframe needs to rest.  It's getting old, you know.",
			"I'm not sure.  Try calling the Internet's head office -- it's in the book.",
			"The lines are all busy (busied out, that is -- why let them in to begin with?).",
			"Jan  9 16:41:27 huber su: 'su root' succeeded for .... on /dev/pts/1",
			"It's those computer people in X {city of world}.  They keep stuffing things up.",
			"A star wars satellite accidently blew up the WAN.",
			"Fatal error right in front of screen",
			"That function is not currently supported, but Bill Gates assures us it will be featured in the next upgrade.",
			"wrong polarity of neutron flow",
			"Lusers learning curve appears to be fractal",
			"We had to turn off that service to comply with the CDA Bill.",
			"Ionization from the air-conditioning",
			"TCP/IP UDP alarm threshold is set too low.",
			"Someone is broadcasting pygmy packets and the router doesn't know how to deal with them.",
			"The new frame relay network hasn't bedded down the software loop transmitter yet. ",
			"Fanout dropping voltage too much, try cutting some of those little traces",
			"Plate voltage too low on demodulator tube",
			"You did wha... oh _dear_....",
			"CPU needs bearings repacked",
			"Too many little pins on CPU confusing it, bend back and forth until 10-20% are neatly removed. Do _not_ leave metal bits visible!",
			"_Rosin_ core solder? But...",
			"Software uses US measurements, but the OS is in metric...",
			"The computer fleetly, mouse and all.",
			"Your cat tried to eat the mouse.",
			"The Borg tried to assimilate your system. Resistance is futile.",
			"It must have been the lightning storm we had (yesterday) (last week) (last month)",
			"Due to Federal Budget problems we have been forced to cut back on the number of users able to access the system at one time. (namely none allowed....)",
			"Too much radiation coming from the soil.",
			"Unfortunately we have run out of bits/bytes/whatever. Don't worry, the next supply will be coming next week.",
			"Program load too heavy for processor to lift.",
			"Processes running slowly due to weak power supply",
			"Our ISP is having {switching,routing,SMDS,frame relay} problems",
			"We've run out of licenses",
			"Interference from lunar radiation",
			"Standing room only on the bus.",
			"You need to install an RTFM interface.",
			"That would be because the software doesn't work.",
			"That's easy to fix, but I can't be bothered.",
			"Someone's tie is caught in the printer, and if anything else gets printed, he'll be in it too.",
			"We're upgrading /dev/null",
			"The Usenet news is out of date",
			"Our POP server was kidnapped by a weasel.",
			"It's stuck in the Web.",
			"Your modem doesn't speak English.",
			"The mouse escaped.",
			"All of the packets are empty.",
			"The UPS is on strike.",
			"Neutrino overload on the nameserver",
			"Melting hard drives",
			"Someone has messed up the kernel pointers",
			"The kernel license has expired",
			"Netscape has crashed",
			"The cord jumped over and hit the power switch.",
			"It was OK before you touched it.",
			"Bit rot",
			"U.S. Postal Service",
			"Your Flux Capacitor has gone bad.",
			"The Dilithium Crystals need to be rotated.",
			"The static electricity routing is acting up...",
			"Traceroute says that there is a routing problem in the backbone.  It's not our problem.",
			"The co-locator cannot verify the frame-relay gateway to the ISDN server.",
			"High altitude condensation from U.S.A.F prototype aircraft has contaminated the primary subnet mask. Turn off your computer for 9 days to avoid damaging it.",
			"Lawn mower blade in your fan need sharpening",
			"Electrons on a bender",
			"Telecommunications is upgrading. ",
			"Telecommunications is downgrading.",
			"Telecommunications is downshifting.",
			"Hard drive sleeping. Let it wake up on it's own...",
			"Interference between the keyboard and the chair.",
			"The CPU has shifted, and become decentralized.",
			"Due to the CDA, we no longer have a root account.",
			"We ran out of dial tone and we're and waiting for the phone company to deliver another bottle.",
			"You must've hit the wrong any key.",
			"PCMCIA slave driver",
			"The Token fell out of the ring. Call us when you find it.",
			"The hardware bus needs a new token.",
			"Too many interrupts",
			"Not enough interrupts",
			"The data on your hard drive is out of balance.",
			"Digital Manipulator exceeding velocity parameters",
			"appears to be a Slow/Narrow SCSI-0 Interface problem",
			"microelectronic Riemannian curved-space fault in write-only file system",
			"fractal radiation jamming the backbone",
			"routing problems on the neural net",
			"IRQ-problems with the Un-Interruptible-Power-Supply",
			"CPU-angle has to be adjusted because of vibrations coming from the nearby road",
			"emissions from GSM-phones",
			"CD-ROM server needs recalibration",
			"firewall needs cooling",
			"asynchronous inode failure",
			"transient bus protocol violation",
			"incompatible bit-registration operators",
			"your process is not ISO 9000 compliant",
			"You need to upgrade your VESA local bus to a MasterCard local bus.",
			"The recent proliferation of Nuclear Testing",
			"Elves on strike. (Why do they call EMAG Elf Magic)",
			"Internet exceeded Luser level, please wait until a luser logs off before attempting to log back on.",
			"Your EMAIL is now being delivered by the USPS.",
			"Your computer hasn't been returning all the bits it gets from the Internet.",
			"You've been infected by the Telescoping Hubble virus.",
			"Scheduled global CPU outage",
			"Your Pentium has a heating problem - try cooling it with ice cold water.(Do not turn off your computer, you do not want to cool down the Pentium Chip while he isn't working, do you?)",
			"Your processor has processed too many instructions.  Turn it off immediately, do not type any commands!!",
			"Your packets were eaten by the terminator",
			"Your processor does not develop enough heat.",
			"We need a licensed electrician to replace the light bulbs in the computer room.",
			"The POP server is out of Coke",
			"Fiber optics caused gas main leak",
			"Server depressed, needs Prozac",
			"quantum decoherence",
			"those damn raccoons!",
			"suboptimal routing experience",
			"A plumber is needed, the network drain is clogged",
			"50% of the manual is in .pdf readme files",
			"the AA battery in the wallclock sends magnetic interference",
			"the xy axis in the trackball is coordinated with the summer solstice",
			"the butane lighter causes the pincushioning",
			"old inkjet cartridges emanate barium-based fumes",
			"manager in the cable duct",
			"We'll fix that in the next (upgrade, update, patch release, service pack).",
			"HTTPD Error 666 : BOFH was here",
			"HTTPD Error 4004 : very old Intel cpu - insufficient processing power",
			"The ATM board has run out of 10 pound notes.  We are having a whip round to refill it, care to contribute ?",
			"Network failure -  call NBC",
			"Having to manually track the satellite.",
			"Your/our computer(s) had suffered a memory leak, and we are waiting for them to be topped up.",
			"The rubber band broke",
			"We're on Token Ring, and it looks like the token got loose.",
			"Stray Alpha Particles from memory packaging caused Hard Memory Error on Server.",
			"paradigm shift...without a clutch",
			"PEBKAC (Problem Exists Between Keyboard And Chair)",
			"The cables are not the same length.",
			"Second-system effect.",
			"Chewing gum on /dev/sd3c",
			"Boredom in the Kernel.",
			"the daemons! the daemons! the terrible daemons!",
			"I'd love to help you -- it's just that the Boss won't let me near the computer. ",
			"struck by the Good Times virus",
			"YOU HAVE AN I/O ERROR -> Incompetent Operator error",
			"Your parity check is overdrawn and you're out of cache.",
			"Communist revolutionaries taking over the server room and demanding all the computers in the building or they shoot the sysadmin. Poor misguided fools.",
			"Plasma conduit breach",
			"Out of cards on drive D:",
			"Sand fleas eating the Internet cables",
			"parallel processors running perpendicular today",
			"ATM cell has no roaming feature turned on, notebooks can't connect",
			"Webmasters kidnapped by evil cult.",
			"Failure to adjust for daylight savings time.",
			"Virus transmitted from computer to sysadmins.",
			"Virus due to computers having unsafe sex.",
			"Incorrectly configured static routes on the corerouters.",
			"Forced to support NT servers; sysadmins quit.",
			"Suspicious pointer corrupted virtual machine",
			"It's the InterNIC's fault.",
			"Root name servers corrupted.",
			"Budget cuts forced us to sell all the power cords for the servers.",
			"Someone hooked the twisted pair wires into the answering machine.",
			"Operators killed by year 2000 bug bite.",
			"We've picked COBOL as the language of choice.",
			"Operators killed when huge stack of backup tapes fell over.",
			"Robotic tape changer mistook operator's tie for a backup tape.",
			"Someone was smoking in the computer room and set off the halon systems.",
			"Your processor has taken a ride to Heaven's Gate on the UFO behind Hale-Bopp's comet.",
			"it's an ID-10-T error",
			"Dyslexics retyping hosts file on servers",
			"The Internet is being scanned for viruses.",
			"Your computer's union contract is set to expire at midnight.",
			"Bad user karma.",
			"/dev/clue was linked to /dev/null",
			"Increased sunspot activity.",
			"We already sent around a notice about that.",
			"It's union rules. There's nothing we can do about it. Sorry.",
			"Interference from the Van Allen Belt.",
			"Jupiter is aligned with Mars.",
			"Redundant ACLs. ",
			"Mail server hit by UniSpammer.",
			"T-1's congested due to porn traffic to the news server.",
			"Data for intranet got routed through the extranet and landed on the internet.",
			"We are a 100% Microsoft Shop.",
			"We are Microsoft.  What you are experiencing is not a problem; it is an undocumented feature.",
			"Sales staff sold a product we don't offer.",
			"Secretary sent chain letter to all 5000 employees.",
			"Sysadmin didn't hear pager go off due to loud music from bar-room speakers.",
			"Sysadmin accidentally destroyed pager with a large hammer.",
			"Sysadmins unavailable because they are in a meeting talking about why they are unavailable so much.",
			"Bad cafeteria food landed all the sysadmins in the hospital.",
			"Route flapping at the NAP.",
			"Computers under water due to SYN flooding.",
			"The vulcan-death-grip ping has been applied.",
			"Electrical conduits in machine room are melting.",
			"Traffic jam on the Information Superhighway.",
			"Radial Telemetry Infiltration",
			"Cow-tippers tipped a cow onto the server.",
			"tachyon emissions overloading the system",
			"Maintenance window broken",
			"We're out of slots on the server",
			"Computer room being moved.  Our systems are down for the weekend.",
			"Sysadmins busy fighting SPAM.",
			"Repeated reboots of the system failed to solve problem",
			"Feature was not beta tested",
			"Domain controller not responding",
			"Someone else stole your IP address, call the Internet detectives!",
			"It's not RFC-822 compliant.",
			"operation failed because: there is no message for this error (#1014)",
			"stop bit received",
			"internet is needed to catch the etherbunny",
			"network down, IP packets delivered via UPS",
			"Firmware update in the coffee machine",
			"Temporal anomaly",
			"Mouse has out-of-cheese-error",
			"Borg implants are failing",
			"Borg nanites have infested the server",
			"error: one bad user found in front of screen",
			"Please state the nature of the technical emergency",
			"Internet shut down due to maintenance",
			"Daemon escaped from pentagram",
			"crop circles in the corn shell",
			"sticky bit has come loose",
			"Hot Java has gone cold",
			"Cache miss - please take better aim next time",
			"Hash table has woodworm",
			"Trojan horse ran out of hay",
			"Zombie processes detected, machine is haunted.",
			"overflow error in /dev/null",
			"Browser's cookie is corrupted -- someone's been nibbling on it.",
			"Mailer-daemon is busy burning your message in hell.",
			"According to Microsoft, it's by design",
			"vi needs to be upgraded to vii",
			"greenpeace free'd the mallocs",
			"Terrorists crashed an airplane into the server room, have to remove /bin/laden. (rm -rf /bin/laden)",
			"astropneumatic oscillations in the water-cooling",
			"Somebody ran the operating system through a spelling checker.",
			"Rhythmic variations in the voltage reaching the power supply.",
			"Keyboard Actuator Failure.  Order and Replace.",
			"Packet held up at customs.",
			"Propagation delay.",
			"High line impedance.",
			"Someone set us up the bomb.",
			"Power surges on the Underground.",
			"Don't worry; it's been deprecated. The new one is worse.",
			"Excess condensation in cloud network",
			"It is a layer 8 problem",
			"The math co-processor had an overflow error that leaked out and shorted the RAM",
			"Leap second overloaded RHEL6 servers",
			"DNS server drank too much and had a hiccup",
			"Your machine had the fuses in backwards."
		};

		String dayStr = new SimpleDateFormat("MMMM d, y").format(new Date());

		System.out.println("Excuse for "+dayStr+" is:\r\n"+excuses[random.nextInt(excuses.length)]);

		enterToCont();
	}

	public static void do2() { // avg of 3 INTS
		System.out.println("Average of 3 integers (no decimals allowed)\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs 
		int[] numsToAvg = new int[3];

		// get ints and try to MAKE THEM INTS
		System.out.print("\r\nFirst integer:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-int
			numsToAvg[0] = Integer.parseInt(rawInput); // add to arr
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nSecond integer:\r\n>>> ");
		rawInput = scanner.nextLine();

		try {
			numsToAvg[1] = Integer.parseInt(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nThird integer:\r\n>>> ");
		rawInput = scanner.nextLine();

		try {
			numsToAvg[2] = Integer.parseInt(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.println("\r\nYour average is:\r\nint\t"+((numsToAvg[0]+numsToAvg[1]+numsToAvg[2])/3)+"\r\ndouble\t"+(((double)(numsToAvg[0]+numsToAvg[1]+numsToAvg[2]))/3.0));

		enterToCont();
	}

	public static void do3() { // ops on 2 doubles
		System.out.println("Sum, difference, and product of 2 decimal numbers x and y\r\n");
		
		Scanner scanner = new Scanner(System.in); // scope bs
		double xDouble, yDouble;

		System.out.print("\r\nFirst double:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			xDouble = Double.parseDouble(rawInput); // add to arr
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nSecond double:\r\n>>> ");
		rawInput = scanner.nextLine();

		try {
			yDouble = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double sum = xDouble+yDouble;
		double subtract1 = xDouble-yDouble;
		double subtract2 = yDouble-xDouble;
		double product = xDouble*yDouble;

		System.out.println("\r\nx + y:\t"+sum+"\r\na - b:\t"+subtract1+"\r\nb - a:\t"+subtract2+"\r\na * b:\t"+product);

		enterToCont();
	}

	public static void do4() { // ºC to ºF
		System.out.println("Degrees Celcius to Farenheit\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		Double celcius;

		System.out.print("\r\nDegrees Celcius (int):\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			celcius = (double)Integer.parseInt(rawInput); // add to arr
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double farenheit = ((1.8)*celcius) + 32.0;
		System.out.println("\r\nºC ("+celcius+") -> ºF ("+farenheit+")");

		enterToCont();
	}

	public static void do5() { // ºF to ºC
		System.out.println("Degrees Farenheit to Celcius\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		Double farenheit;

		System.out.print("\r\nDegrees Farenheit (int):\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			farenheit = (double)Integer.parseInt(rawInput); // add to arr
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double celcius = (farenheit - 32.0) * (5.0/9.0);
		System.out.println("\r\nºF ("+farenheit+") -> ºC ("+celcius+")");

		enterToCont();
	}

	public static void do6() { // Miles to Kilometres
		System.out.println("Miles to Kilometres\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		Double miles;

		System.out.print("\r\nMiles:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			miles = Double.parseDouble(rawInput); // add to arr
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double kilometres = (miles * 1.6093440);
		System.out.println("\r\nMiles ("+miles+") -> Kilometres ("+kilometres+")");

		enterToCont();
	}

	public static void do7() { // HRT to unix time
		System.out.println("Human time to unix time\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm:ss");
		dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date dateObj = new Date();

		System.out.print("\r\nTime (hh:mm:ss) where hh is in military time:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-time
			dateObj = dateParser.parse(rawInput);
		} catch (ParseException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		int unixTime = (int)(dateObj.getTime()/1000);
		System.out.println("\r\n"+rawInput+" -> "+unixTime+" seconds");

		enterToCont();
	}

	public static void do8() { // unix time to HRT
		System.out.println("Unix time to human time\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm:ss");
		dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date dateObj = new Date();

		System.out.print("\r\nSeconds after midnight:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-time
			dateObj = new Date(Long.parseLong(rawInput)*1000);
		} catch (DateTimeException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.println("\r\n"+rawInput+" seconds -> "+dateParser.format(dateObj));

		enterToCont();
	}

	public static void do9() { // distance between points
		System.out.println("Distance between points\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		double[] point1 = new double[2];
		double[] point2 = new double[2];

		System.out.print("Point 1:\r\n>>> (             , y )\r>>> ( ");
		String rawInputX = scanner.nextLine();
		System.out.print("\r>>> ( "+rawInputX+" ,            )\r>>> ( "+rawInputX+" , ");
		String rawInputY = scanner.nextLine();

		try { // return pretty error on non-double
			point1[0] = Double.parseDouble(rawInputX);
			point1[1] = Double.parseDouble(rawInputY);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("Point 2:\r\n>>> (                       , y )\r>>> ( ");
		rawInputX = scanner.nextLine();
		System.out.print("\r>>> ( "+rawInputX+" ,                      )\r>>> ( "+rawInputX+" , ");
		rawInputY = scanner.nextLine();

		try { // return pretty error on non-double
			point2[0] = Double.parseDouble(rawInputX);
			point2[1] = Double.parseDouble(rawInputY);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double distance = Math.sqrt(Math.pow((point1[0]-point2[0]),2) + Math.pow((point1[1]-point2[1]),2));

		System.out.println("\r\n Distance from ("+point1[0]+", "+point1[1]+") to ("+point2[0]+", "+point2[1]+") = "+distance);

		enterToCont();
	}

	public static void do10() { // SA from r in sphere - 4πr^2
		System.out.println("Surface area of sphere using radius\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		double radius;

		System.out.print("\r\nRadius of sphere:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			radius = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double surfaceArea = 4*Math.PI*Math.pow(radius, 2);

		System.out.println("\r\n"+rawInput+" units radius -> "+surfaceArea+" units²");

		enterToCont();
	}

	public static void do11() { // Area of trlangle (heron)
		System.out.println("Area of triangle using sides\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		double side1, side2, side3;

		System.out.print("\r\nSide 1:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			side1 = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nSide 2:\r\n>>> ");
		rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			side2 = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nSide 3:\r\n>>> ");
		rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			side3 = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		double s = (side1+side2+side3)/2.0;
		System.out.println("\r\n"+Math.sqrt(s*(s-side1)*(s-side2)*(s-side3))+" units²");

		enterToCont();
	}

	public static void do12() { // odom/mpg
		System.out.println("MPG and gas cost calculator\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		double odomStart, odomEnd, gasG, gasC;

		System.out.print("\r\nOdometer at start of trip:\r\n>>> ");
		String rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			odomStart = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nOdometer at end of trip:\r\n>>> ");
		rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			odomEnd = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nGallons of gas used:\r\n>>> ");
		rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			gasG = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.print("\r\nCost of gas per gallon:\r\n>>> ");
		rawInput = scanner.nextLine();

		try { // return pretty error on non-double
			gasC = Double.parseDouble(rawInput);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid input; Returning to menu");
			return;
		}

		System.out.println("\r\nMPG: "+((odomEnd-odomStart)/gasG)+"\r\nCost: "+(gasG*gasC));

		enterToCont();
	}

	public static void do13() { // coin counter
		System.out.println("Counter of coins (vals as of 8/25/16)\r\n");

		Scanner scanner = new Scanner(System.in); // scope bs
		String[] coinPrompts = {"μBtc (bits)","Dogecoin (DOGE)","Litecoin (LTC)","Feathercoin (FTC)","Monero (XMR)","Ethereum (ETH)","US Dollar Coin","US Half Dollar","US Quarter","US Dime","US Nickel","US Penny","Canadian 2 dollars","Canadian 1 dollar","Canadian 50 cent","Canadian 25 cent","Canadian 10 cent","Canadian 5 cent","Canadian 1 cent","Great British Pound 2 Pounds","Great British Pound 1 Pound","Great British Pound 50 Pence","Great British Pound 20 Pence","Great British Pound 10 Pence","Great British Pound 5 Pence","Great British Pound 2 Pence","Great British Pound 1 Penny","Euro 2 Euros","Euro 1 Euro","Euro 50 cent","Euro 20 cent","Euro 10 cent","Euro 5 cent","Euro 2 cent","Euro 1 cent"};
		Double[] coinVals = {0.00057498,0.0002238,3.80466658,0.01188745,3.85127893,11.25359174,1.0,0.5,0.25,0.10,0.05,0.01,1.54674256,0.77337128,0.38668564,0.19334282,0.077337128,0.038668564,0.0077337128,2.6381,1.31905,0.659525,0.26381,0.131905,0.0659525,0.026381,0.0131905,2.25633,1.128165,0.5640825,0.225633,0.1128165,0.05640825,0.0225633,0.01128165};
		int[] numCoins = new int[coinPrompts.length]; // not actually used

		double totalUSD=0;

		System.out.println("Please specify how many of each you have (empty for none), must be rounded:\n");

		for (int i=0; i<coinPrompts.length; i++) {
			System.out.print(coinPrompts[i]+": ");
			String rawInput = scanner.nextLine();
			if (rawInput.isEmpty()) {
				numCoins[i] = 0;
			} else {
				try {
					numCoins[i] = Integer.parseInt(rawInput);
					totalUSD += coinVals[i]*numCoins[i];
				} catch (java.lang.NumberFormatException e) {
					System.out.println("Invalid entry, please retry");
					i--;
				}
			}
		}

		System.out.print("\nYou have:\n$"+totalUSD+" US Dollars\n฿"+(totalUSD*0.001741008)+" BTC\n$"+(totalUSD*1.29304)+" CAD\n£"+(totalUSD*0.75823635)+" GBP\n€"+(totalUSD*0.88613204)+" EUR");

		enterToCont();
	}

	public static void do14() { // Phone num generator
		System.out.println("Phone number gen\r\n");
		
		System.out.print("\n("+random.nextInt(7)+random.nextInt(7)+random.nextInt(7)+") "+random.nextInt(743)+"-"+random.nextInt(9999));

		enterToCont();
	}

	public static void enterToCont() {
		System.out.println("\n<ENTER to continue>");
		scanner.nextLine();
	}

}
