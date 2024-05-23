package ua.mei.kowo.mixin;

import io.wispforest.owo.ui.component.EntityComponent;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityComponent.class)
public interface KowoEntityComponent<E extends Entity> extends Component {
    @Accessor("entity")
    E getEntity();

    @Mutable
    @Accessor("entity")
    void setEntity(E entity);
}
