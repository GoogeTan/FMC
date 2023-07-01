package me.zahara.fmc
package item

import text.ChatFormatting

enum Rarity(color : ChatFormatting):
  case COMMON extends Rarity(ChatFormatting.White)
  case UNCOMMON extends Rarity(ChatFormatting.Yellow)
  case RARE extends Rarity(ChatFormatting.Aqua)
  case EPIC extends Rarity(ChatFormatting.LightPurple)