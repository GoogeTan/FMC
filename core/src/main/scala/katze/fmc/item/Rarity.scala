package katze.fmc.item

import katze.fmc.text.ChatFormatting

enum Rarity(color : ChatFormatting):
  case COMMON extends Rarity(ChatFormatting.White)
  case UNCOMMON extends Rarity(ChatFormatting.Yellow)
  case RARE extends Rarity(ChatFormatting.Aqua)
  case EPIC extends Rarity(ChatFormatting.LightPurple)