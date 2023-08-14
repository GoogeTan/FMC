package fmc
package creative.tab.action

import creative.tab.CreativeModeTab

trait Tabs[F[_]]:
  def fromLocation(location: ResourceLocation) : F[Option[CreativeModeTab]]
end Tabs
