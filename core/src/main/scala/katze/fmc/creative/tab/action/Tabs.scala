package katze.fmc.creative.tab.action

import katze.fmc.ResourceLocation
import katze.fmc.creative.tab.CreativeModeTab

trait Tabs[F[_]]:
  def fromLocation(location: ResourceLocation) : F[Option[CreativeModeTab]]
end Tabs
