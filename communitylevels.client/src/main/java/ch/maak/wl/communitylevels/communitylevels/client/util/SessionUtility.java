package ch.maak.wl.communitylevels.communitylevels.client.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.security.SimplePrincipal;

import ch.maak.wl.communitylevels.communitylevels.client.ClientSession;
import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;

public final class SessionUtility {

	public static Optional<WarzoneUserPrincipal> getWarzoneUserPrincipal() {
		return ClientSession.get()
				.getSubject()
				.getPrincipals()
				.stream()
				.filter(p -> p instanceof WarzoneUserPrincipal)
				.map(p -> (WarzoneUserPrincipal) p)
				.findFirst();
	}

	public static Subject getDefaultSubject() {
		final Subject subject = new Subject();
		subject.getPrincipals().add(new SimplePrincipal("access-check-user"));
		subject.setReadOnly();
		return subject;
	}

	public static String getRandomUserName() {
		List<String> usernames = Arrays.asList("Wolverine", "Yak", "Weasel", "Ferret", "Elephant", "Tuna", "Water Boa", "Sparrow", "Gecko", "Whippet", "Locust", "Squid", "Camel", "Pelican", "Duck",
				"Mole", "Bobcat", "Wombat", "Vulture", "Sailfish", "Raven", "Turkey", "Cheetah", "Deer", "Bug", "Bison", "Porpoise", "Grouse", "Gorilla", "Flea", "Shrew", "Butterfly", "Kite", "Sloth",
				"Orangutan", "Panther", "Porcupine", "Trout", "Dove", "Alpaca", "Penguin", "Cobra", "Fox", "Baboon", "Shark", "Bird", "Monkey", "Starfish", "Parrotfish", "Hedgehog", "Llama",
				"Reptile", "Swift", "Falcon", "Ostrich", "Walrus", "Piranha", "Fish", "Tapir", "Emu", "Red panda", "Spider", "Takin", "Ape", "Xerinae", "Orca", "Opossum", "Gopher", "Fly", "Crow",
				"Goldfish", "Louse", "Lark", "Moose", "Koala", "Crocodile", "Lobster", "Worm", "Tortoise", "Firefly", "Reindeer", "Slug", "Shrimp", "Finch", "Swordfish", "Possum", "Giraffe", "Stork",
				"Mouse", "Chipmunk", "Quokka", "Pinniped", "Turtle", "Peacock", "Pig", "Woodpecker", "Lemming", "Swan", "Planarian", "Octopus", "Whale", "Silverfish", "Bee", "Pheasant", "Tern",
				"Chicken", "Dragonfly", "Otter", "Pike", "Prawn", "Donkey", "Loon", "Gull", "Roundworm", "Hawk", "Bobolink", "Muli", "Beetle", "Iguana", "Crayfish", "Gibbon", "Skunk", "Frog",
				"Alligator", "Dog", "Python", "Puma", "Goat", "Buffalo", "Leopon", "Dingo", "Perch", "Jaguar", "Raccoon", "Cow", "Toad", "Flyingfish", "Impala", "Zebra", "Tarantula", "Toucan",
				"Termite", "Scorpion", "Cuckoo", "Sheep", "Salamander", "Quail", "Coyote", "Squirrel", "Lynx", "Partridge", "Tahr", "Tiglon", "Warbler", "Bonobo", "Wasp", "Wren", "Flamingo", "Goose",
				"Salmon", "Snail", "Cat", "Leopard", "Wolf", "Lemur", "Silkworm", "Dolphin", "Crawdad", "Peafowl", "Dinosaur", "Bovid", "Platypus", "Skink", "Leech", "Puffin", "Sawfish", "Ocelot",
				"Pony", "Horse", "Tarsier", "Owl", "Kangaroo", "Snake", "Swallow", "Jellyfish", "Crab", "Cricket", "Rhinoceros", "Hamster", "Wallaby", "Stingray", "Vole", "Swordtail", "Rat", "Rabbit",
				"Guanaco", "Roadrunner", "Lizard", "Thrush", "Scallop", "Seahorse", "Rooster", "Kiwi", "Limpet", "Parakeet", "Polar bear", "Sturgeon", "Mule", "Mongoose", "Guppy", "Hyena", "Eagle",
				"Mosquito", "Ladybug", "Clownfish", "Dragon", "Tiger", "Vicuna", "Quelea", "Viper", "Fowl", "Lion", "Parrot", "Cougar", "Chameleon", "Narwhal", "Pigeon", "Panda");
		Random rand = new Random();
		return "Anonymous " + usernames.get(rand.nextInt(usernames.size()));
	}
}
