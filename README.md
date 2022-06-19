# BST-Sound-MC
# A 1.19+ Fabric mod that attempts to implement the [BST sound transport algorithm](http://kunzhou.net/zjugaps/bst/bst.pdf) in Minecraft

Structurally, the code will (probably) have 4 main segments:
Some sort of hook to SoundSystem that matches sources with their IR data from the path tracer and uses the CONV_REVERB OpenAL effect to convolve the source audio with its IR

A setup to efficiently ingest chunk data from the ClientChunkManager and track the position (and maybe directionality in the future) of sound sources that converts the chunk block data into some sort of 3d binary array and passes it to the BST implementation.

An efficient ray-cast function based off of [this voxel raycast algorithm](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.42.3443&rep=rep1&type=pd) that takes full advantage of the voxelized nature of the MC world.

An implementation of the BST algorithm (I’ll probably implement the temporal cache/other optimizations after I finish the base algorithm) 

