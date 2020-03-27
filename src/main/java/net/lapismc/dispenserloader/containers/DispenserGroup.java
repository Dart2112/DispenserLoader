package net.lapismc.dispenserloader.containers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a group of dispensers
 * The subjects contents are set to the contents of the master when the setContents method is run
 */
public class DispenserGroup {

    private JavaPlugin plugin;
    private GroupTimerTask task;
    private String name;
    private Dispenser master;
    private List<Dispenser> subjects = new ArrayList<>();

    /**
     * Initializes a Dispenser Group to facilitates the copying of contents to specific dispensers
     *
     * @param plugin   The main class of a plugin, required for timed executions
     * @param name     The name of the group, used in commands
     * @param master   The master dispenser, this is where the items will be copied from
     * @param subjects The subject dispensers, this is where the items will be copied to
     */
    public DispenserGroup(JavaPlugin plugin, String name, Dispenser master, Dispenser... subjects) {
        this.plugin = plugin;
        this.name = name;
        setMaster(master);
        addSubjects(subjects);
    }

    /**
     * Setup the automated running of the copy contents method
     *
     * @param period How many ticks between executions
     */
    public void setupTimerTask(long period) {
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
        task = new GroupTimerTask(0, period);
    }

    /**
     * Get the name of the group
     *
     * @return The name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * Set the master {@link Dispenser} for the group
     *
     * @param master The {@link Dispenser} to set as the master
     */
    public void setMaster(Dispenser master) {
        this.master = master;
    }

    /**
     * Add subjects to the group
     *
     * @param subjects The subjects you wish to add
     */
    public void addSubjects(Dispenser... subjects) {
        this.subjects.addAll(Arrays.asList(subjects));
    }

    /**
     * Copies the contents of the Master dispenser into the contents of subjects
     */
    public void copyContents() {
        List<ItemStack> contents = master.getContents();
        for (Dispenser subject : subjects) {
            subject.setContents(contents);
        }
    }

    class GroupTimerTask extends BukkitRunnable {

        public GroupTimerTask(long delay, long period) {
            this.runTaskTimer(plugin, delay, period);
        }

        @Override
        public void run() {
            copyContents();
        }

    }

}
