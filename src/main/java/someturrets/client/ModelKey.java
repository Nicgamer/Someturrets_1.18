package someturrets.client;

import net.minecraft.client.resources.model.ModelState;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModelKey {

    /**
     * A key used to identify a set of baked quads for the baked model
     */
    @Nullable
    private final ModelState modelState;

    public ModelKey(@Nullable ModelState modelState) {
        this.modelState = modelState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelKey that = (ModelKey) o;
        return Objects.equals(modelState, that.modelState);
    }

    @Override
    public int hashCode() {return Objects.hash(modelState);}
}
