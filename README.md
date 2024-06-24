# Joe.
_Just [an] Ordinary Engine._
# Why Joe Exists.

# Why You Should (or Shouldn't) use Joe

# Using Joe.

Full Wiki available eventually.
<!--Needs to be a section here about joePack and submodules.
recommend commands: ```git config --global alias.clone "clone --recurse-submodules" ; git config --global alias.pull "pull --recurse-submodules"```-->
# Compiling Joe.
# Modifying Joe.

# Versioning
Joe uses Semantic Versioning `2.0.0` for Version Numbers.
Check [the Specification](https://semver.org/) for more information.

There should be a unique branch for every minor version past `1.0.x`, which will recieve every security and bug patch from master, 
so long as the patches do not make any outward facing API changes.
These Branches will have a tag marking where the precompiled binaries were released, 
and should be checked for any important patches before downloading and using precompiled binaries.

Please also be aware that:
1. Minor Version branches will be depreciated with each major release.
2. Documentation Changes (such as to this README, or to JavaDoc within the Source)
**DO <u>NOT</u>** increment the Semantic Versioning Patch Number 

The Version of JOE that this readme is packaged with is 0.4.x
(We only version bump this with each minor update, consult git history for exact patch)

# Documentation
I have tried my best to provide decent documentation of as many public-facing classes as possible, 
if there is no documentation for a given class, function or property, take a peek at the code, and if you can, write some.

Here are some guidelines for writing JavaDoc for this project:
1. Any JavaDoc without an `@since` tag is likely from pre-1.0.0
2. The `@version` tag documents the version of the function/class, and **not** the version of the project the function/class was written in.
3. `@version` uses SemVer-like `MAJOR.MINOR`, and should only be updated in case of functionality changes.
4. JavaDoc Missing a `@version` tag can be presumed to be somewhere within the `0.x` to `1.x` range, and should be moved to `2.0` if any breaking changes arise.
5. Any JavaDoc without an `@author` tag should have one *or more* added by checking `git blame`.
6. The JavaDoc `@author` tag (_at least in this project_) declares the author of **the code**, and not the documentation. 
7. If you want credit for writing documentation, and do not already have an `@author` tag within the JavaDoc, add an additional `@author` tag and suffix it with `:docs` as below.
```java
/**
 * For Example...
 * @author Siri
 * @author John_Smith
 * @author JaneDoe2:docs
 */
```
8. Typically, `@apiNote` is used for forward-facing notes to users, and `@implNote` is used to annotate about the internal architecture.
9. HTML JavaDoc is generated to the `doc/` directory. This is manually generated and may not be up to
date. <!-- The generation is done within IDEA "Tools -> Generate JavaDoc... -> Module 'JOE' -> Generate" -->
10. Using GitHub Pages, the doc should be available on [my website](http://siri-chan.github.io/io.siri.joe/) once the JOE repository is public.

# Joe's Inspiration, Contributors, Testers and Friends.
## Inspiration
As a C/++/# Developer, I am used to having a lot of options when it comes to game engines.
Unity is obviously amazing and C libraries like Raylib, SDL, etc. are great 
for quickly writing 2D (or in SDL's case, also 3D) games.  
I've been trying to lean towards Java recently, to give me something different to do, 
and I found that there aren't really any engines (that follow this traditional structure) for Java.  
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
- https://www.delftstack.com/howto/java/play-sound-in-java/

and also lots and lots of JavaDoc.  
Thank you to the souls who put the effort into writing comprehensive documentation. 
It means a lot.
## Friend Projects

#Joe's Dependencies.
Built on OpenJDK 16, using `java.awt.*`, `java.awt.event.*`, `java.awt.image.*`, 
`java.text.*`, `java.util.*`, `java.io.*`,
`java.util.stream.*`, `java.nio.file.*`, `javax.sound.sampled.*` 
and `javax.swing.*`.  
Written Using **JetBrains IntelliJ IDEA**, though (hopefully) **Eclipse** and other IDEs should compile it anyway.


# Licensing Joe.
As of version 0.1.0, JOE is licensed under the Mozilla Public License (MPL), and Copyright 2022 Kira "Siri" K.
Breach of this license is illegal under international Copyright law.

# ToDos:
- [ ] Write JavaDoc for everything
- [x] Texturing for Objects
- [x] Rendering Layers
- [ ] Animation 
  _(realised I probably don't need this because the render() method is fully exposed)_
- [ ] More Useful Math Library Stuff
- [x] Fix the obviously incorrect Vector2 Clamp Function
  _(`x` and `y` should be clamped separately)_
- [x] File Serialisation and I/O
- [ ] Simpler Asset vs. SaveData Serialisation stuff
- [x] SFX
- [x] Music
- [ ] UI System
- [ ] Physics Engine
- [x] Collision Detection
- [ ] Particles
- [ ] Post Processing
- [ ] Maybe Networking Capabilities
- [ ] Maybe Experiment with 3D?
- [ ] Management for Locale Strings
  _(this should take a hashmap that has generic string ids that then return the relevant string for the locale)_
- [ ] Resolution Scaling without weird bounding issues
- [ ] Component Event Firing
- [x] Input System that doesn't use typing based system. 