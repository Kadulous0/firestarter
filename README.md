# Firestarter
![A cherry tree in minecraft burning at night with other cherry trees around it also burning.](https://cdn.modrinth.com/data/qrWBCdOA/images/7e076873a29c525df4a47336028a1c0f2b6bb90f.png)
## IMPORTANT
If you are getting an error on launch after updating to v1.0.0, try deleting the config file, the location of the file can be found below in the "Configuring Mechanics" section. If this doesn't fix it, create an issue on the Github repo.

## WARNING
This mod will likely destroy existing builds that have fire in them unless they are completely non-flammable. This mod will also likely cause large amounts of game lag when fires get larger due to the much more intensive amount of computations. Fire pools can also be dangerous and cause forrests to burn down, I plan on removing them in later releases. By downloading this mod you understand the full risks assoicated with re-launching into existing worlds.

## Description
Introduces more agressive fire spread behavior that does NOT slow down with time, (in more techinal terms), the mod disables the age property of fire from affecting spread probabilites.

## Configuring Mechanics
Make sure to launch the game to generate the config files. (YOU DO NOT HAVE TO LOAD INTO A WORLD). Go to your ".minecraft" directory or whatever profile you have the mod installed into and then go into the "config" folder. Find the "firespread.json5" file and open it with a text editor of your choice. All the properties listed have short descriptions of what they do and acceptable inputs. They also have the vanilla values listed if you prefer the vanilla mechanics but want to allow spreading to occur until there is no more fuel available. After you are done make sure to save the file. You must restart the game to make any changes.

I recommend deleting this "firespread.json5" file if you had the mod installed before the v1.0.0 update, it is safe to do it anyway if you are unsure. This will make sure the mechanics are balanced again since in the v1.0.0 update I changed things around to allow for compatibility with other mods who modify/use fire mechanics as well.

## WIP features
- ~~Configuration to allow users to change spread rules and mechanics easily.~~ (added in v0.1.0)
- ~~Disable fire pools from spawning to reduce number of natural wildfires.~~ (added in v2.0.0)
- Biome dependant spread rules
