package katze.fmc.forge.syntax

import katze.fmc.block.state.{ Properties, BlockState as FmcBlockState }
import net.minecraft.world.level.block.state.BlockState as VanilaBlockState
import scala.jdk.CollectionConverters.{*, given}
import property.*
object blockstate:
  def asProperties(blockState: VanilaBlockState) : Properties =
    val properties = blockState.getProperties.asScala.map(property => (asFmcProperty(property), blockState.getValue(property))).toMap
    new Properties(properties)
  end asProperties
  
  def asVanila(blockState: FmcBlockState) : VanilaBlockState = ???
  
  def asFmcBlockState(state : VanilaBlockState) : FmcBlockState = ???
end blockstate

