package wtf.violet.portvolio.holeinthepocket;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.common.StartScenarioCommand;
import wtf.violet.portvolio.common.UtilPlayer;

import java.util.Random;

public final class HoleInThePocket extends JavaPlugin {

    private final Random random = new Random();

    @Override
    public void onEnable() 
    {
        saveDefaultConfig();

        final FileConfiguration config = getConfig();
        config.addDefault("delaySeconds", 60);

        new StartScenarioCommand(this, (task) ->
        {
            for (final Player player : getServer().getOnlinePlayers())
            {
                final ItemStack stack = UtilPlayer.findRandomFilledSlot(player);

                if (stack == null)
                {
                    continue;
                }

                stack.subtract(stack.getAmount());
            }
        }, 20 * config.getInt("delaySeconds"), false).register();
    }

}
