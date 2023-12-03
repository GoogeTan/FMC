package katze.fmc.example

import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block.state.BlockState

trait PistonBlockTypes[F[_]]:
  def pushReaction(block : BlockRegistryEntry) : F[PistonPushReaction]
  
  def madeOfObsidian(block : BlockState) : F[Boolean]
  
  def isPistonBlock(blockState: BlockState) : F[Boolean]
end PistonBlockTypes

enum PistonPushReaction:
  case Normal, Destroy, Block, Ignore, PushOnly
end PistonPushReaction

def blockPistonPushReaction[F[_]](block : BlockRegistryEntry)(using reaction: PistonBlockTypes[F]): F[PistonPushReaction] = reaction.pushReaction(block)

def isMadeOfObsidian[F[_]](block: BlockState)(using reaction: PistonBlockTypes[F]): F[Boolean] = reaction.madeOfObsidian(block)

def isPistonBlock[F[_]](block: BlockState)(using reaction: PistonBlockTypes[F]): F[Boolean] = reaction.isPistonBlock(block)