package katze.fmc.potion

final case class PotionEffectPrototype(affectPlayer: Nothing => Nothing, //todo: add player api
                                       isGoodEffect: Boolean,
                                       canBeCuredByMilk: Boolean)
