package katze.fmc.forge.block

import katze.fmc.block.PlacementState
import katze.fmc.block.state.*
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import katze.fmc.forge.IO
import katze.fmc.forge.syntax.all.{ *, given }
import net.minecraft.world.level.block.state.{ BlockState, StateDefinition }
import katze.fmc.block.state.{ CaseProperty, Property as FmcProperty }
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

trait PropertiesProvider(
                          defaultProperties       : Properties,
                          stateForPlacement       : PlacementState[IO, Level],
                        ) extends Block:
  super.registerDefaultState(getStateDefinition.any().withProperties(defaultProperties))
  
  override def createBlockStateDefinition(builder : StateDefinition.Builder[Block, BlockState]): Unit =
    super.createBlockStateDefinition(builder)
    defaultProperties
      .mapP[VanilaProperty[?]]([T <: Comparable[T]] => (property : FmcProperty[T], _ : T) => property.asVanila)
      .foreach(p => builder.add(p))
  
  override def getStateForPlacement(context : BlockPlaceContext): BlockState =
    asVanilaState(stateForPlacement.stateForPlacement(asFmcBlock(this), context.getLevel, context.getClickedPos).runUnsafe)
  
  