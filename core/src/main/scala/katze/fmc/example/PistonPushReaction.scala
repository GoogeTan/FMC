package katze.fmc.example

import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block.state.BlockState

trait BlockPistonPushReaction[F[_]]:
  def reaction(block : BlockRegistryEntry) : F[PistonPushReaction]
  
  def madeOfObsidian(block : BlockState) : F[Boolean]
  
  def isAir(block : BlockState) : F[Boolean]
  
  def hasBlockEntity(block : BlockState) : F[Boolean]
  
  def isUnbreakable(blockState: BlockState) : F[Boolean]
end BlockPistonPushReaction

enum PistonPushReaction:
  case Normal, Destroy, Block, Ignore, PushOnly
end PistonPushReaction

def blockPistonPushReaction[F[_]](block : BlockRegistryEntry)(using reaction: BlockPistonPushReaction[F]): F[PistonPushReaction] = reaction.reaction(block)

def isMadeOfObsidian[F[_]](block: BlockState)(using reaction: BlockPistonPushReaction[F]): F[Boolean] = reaction.madeOfObsidian(block)

def isAir[F[_]](block: BlockState)(using reaction: BlockPistonPushReaction[F]): F[Boolean] = reaction.isAir(block)

def hasBlockEntity[F[_]](block: BlockState)(using reaction: BlockPistonPushReaction[F]): F[Boolean] = reaction.hasBlockEntity(block)
def isUnbreakable[F[_]](block: BlockState)(using reaction: BlockPistonPushReaction[F]): F[Boolean] = reaction.isUnbreakable(block)