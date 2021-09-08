package de.lunoro.locker.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

import java.util.List;
import java.util.UUID;

@Getter
public class Lock {

    public UUID owner;
    public Location blockLocation;
    public List<UUID> trustedMembers;

    public Lock(UUID owner, Location blockLocation) {
        this.blockLocation = blockLocation;
        this.owner = owner;
    }

    public Lock(UUID owner, Chest chest) {
        this.blockLocation = chest.getLocation();
        this.owner = owner;
    }

    public Lock(UUID owner, Location blockLocation, List<UUID> trustedMembers) {
        this.blockLocation = blockLocation;
        this.owner = owner;
        this.trustedMembers = trustedMembers;
    }

    public void trust(Player player) {
        trustedMembers.add(player.getUniqueId());
    }

    public boolean isPlayerTrusted(Player player) {
        for (UUID trustedMember : trustedMembers) {
            if (trustedMember.equals(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    public BlockType getBlockTypeOfLock() {
        return blockLocation.getBlockType();
    }
}
