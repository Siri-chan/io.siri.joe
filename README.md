# Joe.
_Just [an] Ordinary Engine._
# Why Joe Exists.

# Why You Should (or Shouldn't) use Joe

# Using Joe.

Full Wiki available eventually.
# Compiling Joe.
# Modifying Joe.

# Joe's Inspiration, Contributors, Testers and Friends.
## Inspiration
As a C/++/# Developer, I am used to having a lot of options when it comes to game engines.
Unity is obviously amazing and C libraries like Raylib, SDL, etc. are great 
for quickly writing 2D (or in SDL's case, also 3D) games.  
I've been trying to lean towards Java recently, to give me something different to do, 
and I found that there aren't really any engines for Java.  
So that's what this is.
## Contributors
- **Kira "Siri" K** - Lead Developer, Head Maintainer; `@author` Tag: "**Siri**", 
also probably wrote the undocumented code you're looking at, check `git blame`.
## Testers and Special Thanks

## Resources
- https://meakaakka.medium.com/a-beginners-guide-to-writing-a-kickass-readme-7ac01da88ab3
- https://medium.com/geekculture/how-to-make-your-own-game-engine-and-why-ddf0acbc5f3
- https://noobtuts.com/java/vector2-class
- https://www.tutorialspoint.com/java/java_serialization.htm

and also lots and lots of JavaDoc.  
Thank you to the souls who put the effort into writing comprehensive documentation. 
It means a lot.
## Friend Projects

#Joe's Dependencies.
Built on OpenJDK 16, using `java.awt.*`, `java.awt.event.*`, `java.awt.image.*`, 
`java.text.*`, `java.util.*`, `java.io.*`,
`java.util.stream.*`, `java.nio.file.*` 
and `javax.swing.*`.  
Written Using **JetBrains IntelliJ IDEA**, though (hopefully) **Eclipse** and other IDEs should compile it anyway.


# Licensing Joe.

# ToDos:
- [ ] Write JavaDoc for everything
- [ ] Texturing for Objects
- [ ] Rendering Layers
- [ ] Animation 
  _(realised I probably don't need this because the render() method is fully exposed)_
- [ ] More Useful Math Library Stuff
- [x] Fix the obviously incorrect Vector2 Clamp Function
  _(`x` and `y` should be clamped separately)_
- [x] File Serialisation and I/O
- [ ] Audio 
- [ ] UI System
  _(Might not need this either, because render() is exposed to GameObjects)_
- [ ] Physics Engine
- [x] Collision Detection
- [ ] Particles
- [ ] Post Processing
- [ ] Maybe Networking Capabilities
- [ ] Maybe Experiment with 3D?