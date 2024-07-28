# Firestarter 
![A cherry tree in minecraft burning at night with other cherry trees around it also burning.](https://cdn.modrinth.com/data/qrWBCdOA/images/7e076873a29c525df4a47336028a1c0f2b6bb90f.png)
## WARNING
This mod will likely destroy existing builds that have fire in them unless they are completely non-flammable. This mod will also likely cause large amounts of game lag when fires get larger due to the much more intensive amount of computations. Fire pools can also be dangerous and cause forrests to burn down, I plan on removing them in later releases. By downloading this mod you understand the full risks assoicated with re-launching into existing worlds.

## Description
Introduces more agressive fire spread behavior that does NOT slow down with time, (in more techinal terms), the mod disables the age property of fire from affecting spread probabilites and increases the range that fire can spread from a 3x3x6 area to a 5x5x8. Additionally, the mod modifies how fast fire spreads and makes it more rain resistant.

![On the left side of the images shows the mods default fire spread mechanics, indicating a 5x5x8 area of effect. On the left side of the image shows the vanilla fire spread mechanics, indicating a 3x3x6 area of effect.](https://cdn.modrinth.com/data/qrWBCdOA/images/fce7cbccdb6094eaf223eb12f972f389c65219e1.png)
## Configuring Mechanics
Make sure to launch the game to generate the config files. (YOU DO NOT HAVE TO LOAD INTO A WORLD). Go to your ".minecraft" directory or whatever profile you have the mod installed into and then go into the "config" folder. Find the "firespread.json5" file and open it with a text editor of your choice. All the properties listed have short descriptions of what they do and acceptable inputs. They also have the vanilla values listed if you prefer the vanilla mechanics but want to allow spreading to occur until there is no more fuel available. After you are done make sure to save the file. You must restart the game to make any changes.

## Known Conflicts
Currently, *due to my brute force coding*, the mod conflicts with the fire related features in Immersive Weathering. The rest of Immersive Weathering's features work just fine, just anything related to fire will not work (such as soot or charred logs, planks, etc). My apologies in advance.

## WIP features
- ~~Configuration to allow users to change spread rules and mechanics easily.~~ (added in v0.1.0)
- Biome dependant spread rules
- Disable fire pools from spawning to reduce number of natural wildfires.
