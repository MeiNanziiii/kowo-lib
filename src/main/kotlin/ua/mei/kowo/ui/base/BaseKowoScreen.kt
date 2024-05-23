package ua.mei.kowo.ui.base

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.core.OwoUIAdapter
import io.wispforest.owo.ui.core.ParentComponent
import io.wispforest.owo.ui.core.Sizing


abstract class BaseKowoScreen<R : ParentComponent> : BaseOwoScreen<R>() {
    fun root(init: R.() -> Unit) {
        this.uiAdapter.rootComponent.init()
    }

    abstract fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): R

    abstract fun build()

    override fun build(rootComponent: R) { build() }

    override fun createAdapter(): OwoUIAdapter<R> {
        return OwoUIAdapter.create(this) { h, v ->
            createRoot(h, v)
        }
    }
}