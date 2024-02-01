package katze.fmc.potion

final case class PotionEffectPrototype(affectPlayer: Player => Player,
                                       isGoodEffect: Boolean,
                                       canBeCuredByMilk: Boolean)
