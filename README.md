![Icon](https://raw.githubusercontent.com/BonfireMC/kowo-lib/main/images/icon_256px.png)

Fancy™ kotlin syntax for [oωo-lib](https://modrinth.com/mod/owo-lib)

# Setup

In your `build.gradle` file, add these lines:

```gradle
repositories {
    maven { url "https://api.modrinth.com/maven" }
    maven { url "https://maven.wispforest.io/releases" }
}

dependencies {
    modImplementation include("maven.modrinth:kowo-lib:0.12.20+1.21.5")

    modImplementation "io.wispforest:owo-lib:0.12.20+1.21.5"
    // only if you plan to use owo-config
    // annotationProcessor "io.wispforest:owo-lib:0.12.20+1.21.5"
}
```

# Example usage

```kt
import ua.mei.kowo.dsl.*

class SizingTestKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            children {
                +stack(Sizing.content(), Sizing.content()).apply {
                    padding {
                        all(15)
                    }
                    horizontalAlignment = HorizontalAlignment.CENTER
                    surface = Surface.panelWithInset(6)

                    val animation: Animation<Sizing> = horizontalSizing().animate(500, Easing.CUBIC, 75.fill)

                    children {
                        +button("initialize sizenite".literal).apply {
                            horizontalSizing = 50.fill

                            onPress {
                                animation.reverse()
                            }
                        }
                    }
                }
                +label("bruh".literal.styled { it.withClickEvent(ClickEvent.OpenUrl(URI.create("https://wispforest.io"))) })
            }
        }
    }
}

```

More examples on [GitHub](https://github.com/BonfireMC/kowo-lib/tree/1.21.5/src/test/kotlin/ua/mei/kuwu/client/screen)

# TODO

- [ ] More examples
- [ ] Simplified way to add child to parent component
- [ ] Styling with MSS (Minecraft Style Sheets)
