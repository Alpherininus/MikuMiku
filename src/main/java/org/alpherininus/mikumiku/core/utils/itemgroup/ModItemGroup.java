package org.alpherininus.mikumiku.core.utils.itemgroup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.alpherininus.mikumiku.core.init.ItemInit;

public class ModItemGroup extends CreativeModeTab {

    public static final CreativeModeTab TAB_MIKU_MOD = new CreativeModeTab(13, "miku_mod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.MIKU_SWORD.get());
        }
    };

    public ModItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.MIKU_SWORD.get());
    }
}
