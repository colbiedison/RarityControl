#RarityControl
RarityControl is a Fabric mod that makes it easy to modify item rarities.<br>
Rarities control the colors of item names.<br>
You can override Vanilla rarities or create your own.

##Configuration
Config file: `.minecraft/config/raritycontrol.json`

The file must contain an array of objects called `rarities`.

Each rarity object has the following properties:

####name
- The internal name of the rarity. It doesn't matter very much.
        If you make it one of `COMMON`, `UNCOMMON`, `RARE`, or `EPIC`, then a new rarity will be created
        with the internal name of `"_"+rarity`, like `_COMMON`, then all items of that original rarity
        will be reassigned to the new one.

####color
- The hex code of the desired color.

####items  (optional)
- A list of items which should be reassigned to the rarity.
        When you override a default rarity, all items of the original rarity are automatically reassigned.


###Example configuration:
```json
{
  "rarities": [
//     recolor uncommon to green:
     {
       "name": "UNCOMMON",
       "color": "00FF00"
     },
//     recolor epic to blue and put netherite ingots into this rarity:
     {
       "name": "EPIC",
       "color": "0000FF",
       "items": [
         "minecraft:netherite_ingot"
       ]
     },
//     create a custom rarity with the color magenta, and put dirt and wooden swords into this rarity:
     {
       "name": "MYRARITY",
       "color": "FF00FF",
       "items": [
         "minecraft:dirt",
         "minecraft:wooden_sword"
       ]
     }

  ]
}
```

