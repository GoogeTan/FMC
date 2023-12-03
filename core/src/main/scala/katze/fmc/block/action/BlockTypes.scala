package katze.fmc.block.action

import katze.fmc.block.state.BlockState
import katze.fmc.data.*

trait BlockTypes[F[_]]:
  def isAir(state : BlockState) : F[Boolean]
  
  def hasBlockEntity(block: BlockState): F[Boolean]
  
  /**
   * Some(Прочность блока) или None, если не разрушим
   */
  def strength(state : BlockState) : F[Option[Float]]
end BlockTypes

def isAir[F[_]](block: BlockState)(using reaction: BlockTypes[F]): F[Boolean] = 
  reaction.isAir(block)
end isAir

def hasBlockEntity[F[_]](state: BlockState)(using reaction: BlockTypes[F]): F[Boolean] = 
  reaction.hasBlockEntity(state)
end hasBlockEntity

def strength[F[_] : FMap](state: BlockState)(using reaction: BlockTypes[F]): F[Option[Float]] =
  reaction.strength(state)
end strength

def isUnbreakable[F[_] : FMap](state: BlockState)(using reaction: BlockTypes[F]): F[Boolean] = 
  fmap(reaction.strength(state))(_.isEmpty)
end isUnbreakable
