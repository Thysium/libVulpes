package zmaster587.libVulpes.block;

import java.util.ArrayList;
import java.util.List;

import zmaster587.libVulpes.tile.TileSchematic;
import zmaster587.libVulpes.tile.multiblock.TilePlaceholder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPhantom extends Block {

	public BlockPhantom(Material mat) {
		super(mat);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos,
			IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}
	
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileSchematic();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target,
			World world, BlockPos pos, EntityPlayer player) {
		TileEntity tile = world.getTileEntity(pos);
		
		if(tile != null && tile instanceof TilePlaceholder && ((TilePlaceholder)tile).getReplacedState() != null) {
			Block block = ((TilePlaceholder)tile).getReplacedState().getBlock();
			ItemStack stack = ((TilePlaceholder)tile).getReplacedState().getBlock().getPickBlock(state, target, world, pos, player);
			
			
			stack.setItemDamage(block.damageDropped(block.getStateFromMeta(((TilePlaceholder)tile).getReplacedMeta())));
			return stack;
		}
		return super.getPickBlock(state, target, world, pos, player);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState,
			IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return super.isOpaqueCube(state);
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public net.minecraft.util.math.AxisAlignedBB getCollisionBoundingBox(
			IBlockState blockState, World worldIn, BlockPos pos) {
		return null;
	}
}
