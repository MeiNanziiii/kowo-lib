package ua.mei.kowo.mixin;

import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(FlowLayout.class)
public interface FlowLayoutAccessor {
    @Accessor("children")
    List<Component> getChildren();

    @Accessor("children")
    void setChildren(List<Component> children);
}
