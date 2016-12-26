package zmaster587.libVulpes.tile.energy;

import zmaster587.libVulpes.Configuration;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;

public class TilePlugInputIC2 extends TileForgePowerOutput implements IEnergySink, ITickable {

	public TilePlugInputIC2() {
		super();
	}
	boolean tickedOnce = false;
	@Override
	public String getModularInventoryName() {
		return "tile.IC2Plug.name";
	}

	//I would use onLoad, however this causes an infinite loop with IC2, F*** you too IC2
	//One day I'll get around to shoving a patch on them for the new capability system...
	//One of the very few things I like about 1.7.10 -> 1.10.2

	@Override
	public void update() {
		super.update();
		if(!tickedOnce) {
			if(!worldObj.isRemote)
				MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			tickedOnce = true;
		}
	}

	@Override
	public void invalidate() {
		super.invalidate();
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return Math.min(getMaxEnergyStored() - getEnergyStored(), 128.0);
	}

	@Override
	public int getSinkTier() {
		return 2;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount,
			double voltage) {
		storage.acceptEnergy((int)(amount*Configuration.EUMult), false);
		return 0;
	}

	@Override
	public int acceptEnergy(int amt, boolean simulate) {
		return 0;
	}

	@Override
	public boolean canReceive() {
		return false;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

}
