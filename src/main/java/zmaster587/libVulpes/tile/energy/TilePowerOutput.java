package zmaster587.libVulpes.tile.energy;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import zmaster587.libVulpes.energy.IPower;

public class TilePowerOutput extends TilePlugBase implements IPower, ITickable {

	public TilePowerOutput() {
		super(1);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing arg0) {
		return true;
	}


	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void update() {
		for(EnumFacing dir : EnumFacing.values()) {
			
			TileEntity tile = worldObj.getTileEntity(this.pos.offset(dir));

			if(tile instanceof IPower) {
				IPower handle = (IPower)tile;
				storage.getEnergyStored();
				storage.extractEnergy(handle.receiveEnergy(dir.getOpposite(), storage.getEnergyStored(), false), false);
			}
		}
	}

	@Override
	public String getModularInventoryName() {
		return "tile.powerOutput.name";
	}

	@Override
	public int extractEnergy(EnumFacing dir, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing dir) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing arg0) {
		return storage.getMaxEnergyStored();
	}

	@Override
	public int receiveEnergy(EnumFacing dir, int amt, boolean simulate) {

		return 0;
	}

	@Override
	public int receiveEnergy(int amt, boolean simulate) {
		return storage.acceptEnergy(amt, simulate);
	}

	@Override
	public String getName() {
		return getModularInventoryName();
	}
}