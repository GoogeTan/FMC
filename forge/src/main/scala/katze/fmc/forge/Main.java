package katze.fmc.forge;

import katze.fmc.forge.subscribeable.Subscribeable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "fmc";
    public Main() {
        Subscribeable sub = Test.subscribeable();
        sub.subscribeEvent(MinecraftForge.EVENT_BUS);
        sub.subscribeInit(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
