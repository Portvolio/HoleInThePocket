package wtf.violet.portvolio.holeinthepocket;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.common.StartScenarioCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class HoleInThePocket extends JavaPlugin {

    private final Random random = new Random();

    @Override
    public void onEnable() 
    {
        new StartScenarioCommand(this, (task) ->
        {
            for (final Player player : getServer().getOnlinePlayers())
            {
                final ItemStack stack = findEligibleSlot(player);

                if (stack == null)
                {
                    continue;
                }

                stack.subtract(stack.getAmount());
            }
        }, 20 * 60, false).register();
    }

    /**
     * Find a random slot of the player's inventory with an item in it
     * @param player Player
     * @return The ItemStack, null if none are found
     */
    private ItemStack findEligibleSlot(final Player player)
    {
        final List<ItemStack> stacks = new ArrayList<>();

        for (final ItemStack stack : player.getInventory().getContents())
        {
            if (stack != null)
            {
                stacks.add(stack);
            }
        }

        final int size = stacks.size();

        if (size == 0)
        {
            return null;
        }

        return stacks.get(random.nextInt(size));
    }

}
