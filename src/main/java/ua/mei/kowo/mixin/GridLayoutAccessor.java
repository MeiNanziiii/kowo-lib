package ua.mei.kowo.mixin;

import io.wispforest.owo.ui.container.GridLayout;
import io.wispforest.owo.ui.core.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GridLayout.class)
public interface GridLayoutAccessor {
    @Accessor
    int getColumns();

    @Accessor
    Component[] getChildren();
}
