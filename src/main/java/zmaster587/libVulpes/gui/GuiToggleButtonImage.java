package zmaster587.libVulpes.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiToggleButtonImage extends GuiButton {
	
	boolean state = false;
	protected ResourceLocation[] buttonTexture;
	/**
	 * 
	 * @param id id of the button
	 * @param x x position of the button
	 * @param y y position of the button
	 * @param width width of the button in pixels
	 * @param height height of the button in pixels
	 * @param location index 0: enabled, index 1: disabled
	 */
	public GuiToggleButtonImage(int id, int x, int y, int width, int height, ResourceLocation[] location) {
		super(id, x, y, width, height,"");
		buttonTexture = location;
		//TODO: add exception
	}


	public void setState(boolean state) {
		this.state = state;
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int par2, int par3)
	{
		if (this.visible)
		{
			//
			this.hovered = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			
			/*if(mousePressed(minecraft, par2, par3) && buttonTexture[2] != null)
				minecraft.getTextureManager().bindTexture(buttonTexture[2]);*/
			if(state)
				minecraft.getTextureManager().bindTexture(buttonTexture[0]);
			else
				minecraft.getTextureManager().bindTexture(buttonTexture[1]);
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


			//Draw the button...each button should contain 3 images default state, hover, and pressed
			
			GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
           
			
	        Tessellator tessellator = Tessellator.getInstance();
	        VertexBuffer vertexbuffer = tessellator.getBuffer();
	        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
	        vertexbuffer.pos(xPosition, yPosition + height, (double)this.zLevel).tex(0, 1).endVertex();
	        vertexbuffer.pos(xPosition + width, yPosition + height, (double)this.zLevel).tex( 1, 1).endVertex();
	        vertexbuffer.pos(xPosition + width, yPosition, (double)this.zLevel).tex(1, 0).endVertex();
	        vertexbuffer.pos(xPosition, yPosition, (double)this.zLevel).tex(0, 0).endVertex();
	        tessellator.draw();
			
			this.mouseDragged(minecraft, par2, par3);
		}
	}
}
