package katze.fmc.forge.syntax

import katze.fmc.block.state.{ Properties, defaultStateOf, foldPC, withValue, BlockState as FmcBlockState, Property as FmcProperty }
import katze.fmc.forge.syntax.block.asFmcBlock
import katze.fmc.forge.syntax.property.{ asFmcProperty, asVanilaProperty }
import net.minecraft.world.level.block.state.BlockState as VanilaBlockState
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

import scala.jdk.CollectionConverters.{ *, given }

object blockstate:
  def asProperties(blockState: VanilaBlockState): Properties =
    val properties = blockState.getProperties.asScala.map(convert(_, blockState)).toMap
    new Properties(properties)
  end asProperties
  
  private def convert[T <: Comparable[T]](property : VanilaProperty[T], state : VanilaBlockState): (FmcProperty[T], T) =
    (asFmcProperty(property), state.getValue(property))
  end convert
  
  
  def asVanila(blockState: FmcBlockState): VanilaBlockState =
    foldPC[VanilaBlockState](blockState.properties, block.asVanilaBlock(blockState.block).defaultBlockState())(
      [T <: Comparable[T]] =>
        (state : VanilaBlockState, property : FmcProperty[T], value : T) =>
          state.setValue(asVanilaProperty(property), value)
    )
  end asVanila
  
  def asFmcBlockState(state: VanilaBlockState): FmcBlockState =
    val block = asFmcBlock(state.getBlock)
    state.getProperties.asScala.foldLeft(defaultStateOf(block))((res : FmcBlockState, x : VanilaProperty[?]) => withVanilaProperty(x, res, state))
    
  def withVanilaProperty[T <: Comparable[T]](property: VanilaProperty[T], blockState: FmcBlockState, oldState : VanilaBlockState) : FmcBlockState =
    val value : T = oldState.getValue(property)
    withValue(blockState, asFmcProperty(property), value).get
  end withVanilaProperty
end blockstate