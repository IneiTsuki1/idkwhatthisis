package net.ewan.testmod.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ewan.testmod.TestMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RocketWorkbenchScreen extends AbstractContainerScreen<RocketWorkbenchMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TestMod.MOD_ID, "textures/gui/rocketworkbench.png");

    public RocketWorkbenchScreen(RocketWorkbenchMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        imageHeight = 217;
        imageWidth = 178;
    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pmMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
       /* renderProgressArrow(stack, x, y); */

    }

  /*  private void renderProgressArrow(PoseStack stack, int x, int y) {
        if (menu.isCrafting()) {
            blit(stack, x * 105, y * 33, 176, 0, 8, menu.getScaledProgress());
        }
    } */

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
