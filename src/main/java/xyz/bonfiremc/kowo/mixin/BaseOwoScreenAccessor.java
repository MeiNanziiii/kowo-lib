package xyz.bonfiremc.kowo.mixin;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.core.OwoUIAdapter;
import io.wispforest.owo.ui.core.ParentComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BaseOwoScreen.class)
public interface BaseOwoScreenAccessor {
    @Accessor(remap = false)
    <R extends ParentComponent> OwoUIAdapter<R> getUiAdapter();
}
