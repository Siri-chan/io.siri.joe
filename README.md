# Joe.
*Just [an] Ordinary Engine, Java Operations Engine, whichever you prefer.*
#Why Joe Exists.

#Why You Should (or Shouldn't) use Joe

#Using Joe.

Full Wiki available eventually.
#Compiling Joe.
#Modifying Joe.

#Joe's Inspiration, Contributors, Testers and Friends.
##Inspiration
As a C/++/# Developer, I am used to having a lot of options when it comes to game engines.
Unity is obviously amazing and C libraries like Raylib, SDL, etc. are great 
for quickly writing 2D (or in SDL's case, also 3D) games.  
I've been trying to lean towards Java recently, to give me something different to do, 
and I found that there aren't really any engines for Java.  
So that's what this is.
##Contributors

##Testers and Special Thanks

##Resources
https://meakaakka.medium.com/a-beginners-guide-to-writing-a-kickass-readme-7ac01da88ab3
https://medium.com/geekculture/how-to-make-your-own-game-engine-and-why-ddf0acbc5f3
https://noobtuts.com/java/vector2-class
##Friend Projects

#Joe's Dependencies.
Built on OpenJDK 16, using `java.awt.*`, `java.awt.image.BufferStrategy`, 
`java.util.LinkedList`, `java.util.Arrays`, `java.util.LinkedList`, 
`java.util.stream.IntStream` and `javax.swing.*`.  
Written Using **JetBrains IntelliJ IDEA**, though (hopefully) **Eclipse** and other IDEs should compile it anyway.


#Licensing Joe.

#ToDos:
- [ ] Write JavaDoc for everything
- [ ] Texturing for Objects
- [ ] Rendering Layers
- [ ] Animation 
  *(realised I probably don't need this because the render() method is fully exposed)*
- [ ] More Useful Math Library Stuff
- [ ] Fix the obviously incorrect Vector2 Clamp Function
  *(`x` and `y` should be clamped separately)*
- [ ] File Serialisation and I/O
- [ ] Maybe Networking Capabilities
- [ ] Audio 
- [ ] UI System
  *(Might not need this either, because render() is exposed to gameobjects)*
- [ ] Physics Engine
- [ ] Collision Detection
- [ ] Particles
- [ ] Post Processing
- [ ] Maybe Experiment with 3D?