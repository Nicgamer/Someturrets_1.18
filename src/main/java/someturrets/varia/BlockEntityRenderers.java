package someturrets.varia;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import someturrets.client.ScreenRenderer;
import someturrets.setup.Registration;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BlockEntityRenderers {
    private static final Map<BlockEntityType<?>, BlockEntityRendererProvider<?>> PROVIDERS = new java.util.concurrent.ConcurrentHashMap<>();

    public static <T extends BlockEntity> void register(BlockEntityType<? extends T> pType, BlockEntityRendererProvider<T> pRenderProvider) {
        PROVIDERS.put(pType, pRenderProvider);
    }

    public static Map<BlockEntityType<?>, BlockEntityRenderer<?>> createEntityRenderers(BlockEntityRendererProvider.Context pContext) {
        ImmutableMap.Builder<BlockEntityType<?>, BlockEntityRenderer<?>> builder = ImmutableMap.builder();
        PROVIDERS.forEach((p_173596_, p_173597_) -> {
            try {
                builder.put(p_173596_, p_173597_.create(pContext));
            } catch (Exception exception) {
                throw new IllegalStateException("Failed to create model for " + Registry.BLOCK_ENTITY_TYPE.getKey(p_173596_), exception);
            }
        });
        return builder.build();
    }

    static {
        register(Registration.SCREEN, ScreenRenderer::new);
    }
}
