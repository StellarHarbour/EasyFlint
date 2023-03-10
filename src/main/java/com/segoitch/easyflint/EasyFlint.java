package com.segoitch.easyflint;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EasyFlint extends JavaPlugin  implements Listener{
    ItemStack flint = new ItemStack(Material.FLINT,1 );
    double chance = .33;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onGravelRightClick(PlayerInteractEvent event) {
        // Check to fire event only one time (because basically it fires once for each hand)
        if(Objects.requireNonNull(event.getHand()).equals(EquipmentSlot.HAND)
           && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)
           && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                //event.getPlayer().sendMessage("Hand is free"); //Debug
                if (Objects.requireNonNull(event.getClickedBlock()).getType() == Material.GRAVEL) {
                    //event.getPlayer().sendMessage("Gravel found"); //Debug
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRAVEL_HIT, 0.10F, 1.0F);
                    if(Math.random() <= chance) {
                        //event.getPlayer().sendMessage("Chance proc"); //Debug
                        //event.getClickedBlock().breakNaturally(); //if you want drop block
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().getInventory().addItem(flint);
                }
           }
        }
    }
}