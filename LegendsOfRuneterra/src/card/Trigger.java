package card;

public enum Trigger {

    PLAY, //Ao jogar ("Comprar") a carta.
    LAST_BREATH, //Quando a unidade morrer.
    DESTROY_OPPONENT, //Quando a unidade matar outra.
    ROUND_START, //No começo do round.
    STRIKE, //Quando a unidade golpear.
    ATTACK, //Quando a unidade fizer parte do ataque.
    ROUND_END, //No final do round.
    SEEN_ALLY_DIE, //Quando outro aliado da unidade morrer.
    ENLIGHTENED, //Quando você tiver 10 gemas de mana.
    NEXUS_STRIKE //Quando uma unidade golpear o nexus.
    
}
