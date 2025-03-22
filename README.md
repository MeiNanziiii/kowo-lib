![Icon](https://raw.githubusercontent.com/MeiNanziiii/kowo-lib/main/images/icon_256px.png)

Fancy™ kotlin syntax for [oωo-lib](https://modrinth.com/mod/owo-lib)

| Pros                | Cons                                    |
|---------------------|-----------------------------------------|
| Greater flexibility | Limited support for oωo-lib features    |
| Fancy™ syntax       | Updates arrive later than oωo-lib       |
| Uses kotlin         | Performance slightly lower than oωo-lib |

# Setup

In your `build.gradle` file, add these lines:

```gradle
repositories {
    maven { url "https://api.modrinth.com/maven" }
}

dependencies {
    modImplementation include("maven.modrinth:kowo-lib:0.12.20+1.21.4")
    // only if you plan to use owo-config
    // annotationProcessor "io.wispforest:owo-lib:0.12.20+1.21.4"
}
```

# Example usage

```kt
class ButtonTestScreen : BaseKowoScreen<FlowLayout>() {
    override fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
        return Containers.verticalFlow(horizontalSizing, verticalSizing)
    }

    override fun build() {
        // like rootComponent, but better
        root {
            // components can be stored in variables...
            val exampleLabel = label {
                text("Example screen: 0".literal())
            }

            // or directly added to the root
            button {
                message = "Example button".literal()

                // variables can be stored within components
                var value = 0
                onPress {
                    // components stored in variables can be modified
                    exampleLabel.text("Example screen: ${++value}".literal())
                }
            }
        }
    }
}
```

More examples on [GitHub](https://github.com/MeiNanziiii/kowo-lib/tree/main/src/test/kotlin/ua/mei/kuwu/client)
