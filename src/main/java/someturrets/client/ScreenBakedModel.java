package someturrets.client;
/*
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import org.jetbrains.annotations.NotNull;
import someturrets.varia.ClientTools;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class ScreenBakedModel implements IDynamicBakedModel {

    private final ModelState modelState;
    private final Function<Material, TextureAtlasSprite> spriteGetter;
    private final Map<ModelKey, List<BakedQuad>> quadCache = new HashMap<>();
    private final ItemOverrides overrides;
    private final ItemTransforms itemTransforms;

    /**
     * @param modelState represents the transformation (orientation) of our model. This is generated from the FACING property that our blockstate uses
     * @param spriteGetter gives a way to convert materials to actual sprites on the main atlas
     * @param overrides this is used for using this baked model when it is rendered in an inventory (as an item)
     * @param itemTransforms these represent the transforms to use for the item model
     */
/*
    public ScreenBakedModel(ModelState modelState, Function<Material, TextureAtlasSprite> spriteGetter,
                               ItemOverrides overrides, ItemTransforms itemTransforms) {
        this.modelState = modelState;
        this.spriteGetter = spriteGetter;
        this.overrides = overrides;
        this.itemTransforms = itemTransforms;
        generateQuadCache();
    }

    /**
     * Whenever a chunk where our block is in needs to be rerendered this method is called to return the quads (polygons)
     * for our model. Typically this will be called seven times: one time for every direction and one time in general.
     * If you have a block that is solid at one of the six sides it can be a good idea to render that face only for that
     * direction. That way Minecraft knows that it can get rid of that face when another solid block is adjacent to that.
     * All faces or quads that are generated for side == null are not going to be culled away like that
     * @param state the blockstate for our block
     * @param side the six directions or null for quads that are not at a specific direction
     * @param rand random generator that you can use to add variations to your model (usually for textures)
     * @param extraData this represents the data that is given to use from our block entity
     * @return a list of quads
     */
/*
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        // Are we on the solid render type and are we rendering for side == null
        RenderType layer = MinecraftForgeClient.getRenderType();
        if (side != null || (layer != null && !layer.equals(RenderType.solid()))) {
            return Collections.emptyList();
        }

        var quads = getQuads(state, rand, extraData, layer);

        // ModelKey represents a unique configuration. We can use this to get our cached quads
        ModelKey key = new ModelKey(modelState);
        quads.addAll(quadCache.get(key));

        return quads;
    }

    private void generateQuadCache() {
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
        quadCache.put(new ModelKey(modelState), generateQuads());
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    /**
     * Generate the quads for a given configuration. This is done in the constructor in order to populate
     * our quad cache.
     */
/*
    @NotNull
    private List<BakedQuad> generateQuads() {
        var quads = new ArrayList<BakedQuad>();
        float l = 0;
        float r = 1;
        float p = 13f / 16f; // Relative position of panel

        float h = .5f;       // Half

        Transformation rotation = modelState.getRotation();

        TextureAtlasSprite textureSide = spriteGetter.apply(ScreenModelLoader.MATERIAL_SIDE);
        TextureAtlasSprite textureFront = spriteGetter.apply(ScreenModelLoader.MATERIAL_FRONT);

        // The base
        quads.add(ClientTools.createQuad(v(r, p, r), v(r, p, l), v(l, p, l), v(l, p, r), rotation, textureFront));
        quads.add(ClientTools.createQuad(v(l, l, l), v(r, l, l), v(r, l, r), v(l, l, r), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(r, p, r), v(r, l, r), v(r, l, l), v(r, p, l), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(l, p, l), v(l, l, l), v(l, l, r), v(l, p, r), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(r, p, l), v(r, l, l), v(l, l, l), v(l, p, l), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(l, p, r), v(l, l, r), v(r, l, r), v(r, p, r), rotation, textureSide));
        return quads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return spriteGetter.apply(ScreenModelLoader.MATERIAL_SIDE);
    }

    @Override
    public ItemOverrides getOverrides() {
        return overrides;
    }

    @Override
    public ItemTransforms getTransforms() {
        return itemTransforms;
    }
}
*/