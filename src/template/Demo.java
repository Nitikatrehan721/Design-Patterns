package template;

// concrete classes will extend this class
abstract class CardGame {
	public Creature[] creatures;

	public CardGame(Creature[] creatures) {
		this.creatures = creatures;
	}

	public int combat(int creature1, int creature2) {
		Creature first = creatures[creature1];
		Creature second = creatures[creature2];
		hit(first, second);
		hit(second, first);

		if (first.health == 0 && second.health != 0) {
			return creature2;
		}
		if (second.health == 0 && first.health != 0) {
			return creature1;
		}
		// (both alive or both dead)
		return -1;
	}

	// attacker hits other creature
	protected abstract void hit(Creature attacker, Creature other);
}


class Creature {
	public int attack, health;

	public Creature(int attack, int health) {
		this.attack = attack;
		this.health = health;
	}
}

class TemporaryCardDamageGame extends CardGame {

	public TemporaryCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	public void hit(Creature attacker, Creature other) {
		if (other.health <= attacker.attack)
			other.health = 0;
	}
}

class PermanentCardDamageGame extends CardGame {
	public PermanentCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	public void hit(Creature attacker, Creature other) {
		other.health -= attacker.attack;
	}
}