# Main Menu Panorama Background (overrides)

Place 6 square cubemap faces here to replace the vanilla main menu dynamic
background. The vanilla jar ships 1x1 placeholder textures
(`RGB(98,111,113)`), which is why the menu currently shows a flat dark color.

## Files

```
panorama_0.png  → right  (+X)
panorama_1.png  → left   (-X)
panorama_2.png  → up     (+Y)
panorama_3.png  → down   (-Y)
panorama_4.png  → front  (+Z)
panorama_5.png  → back   (-Z)
```

All 6 faces must be square PNGs of the same size (512x512 or 1024x1024 are
good defaults). If left/right or front/back look swapped, exchange
`panorama_0`/`panorama_1` or `panorama_4`/`panorama_5`.

## How it works

- The vanilla `GuiRenderer` loads these textures through `CubeMap`
  (`textures/gui/title/background/panorama_<n>.png`) and renders them as a
  cubemap skybox via `shaders/core/panorama.vsh/.fsh`, slowly rotating around
  the Y axis (spin speed is the `panoramaSpeed` option in vanilla settings).
- Because this is a resource override inside the mod jar (`assets/minecraft`),
  it applies automatically for all players; no resource pack needed.
- Rotation speed can be changed by the player in vanilla video settings.

Rebuild the client (`gradlew build`) for the new textures to take effect.
