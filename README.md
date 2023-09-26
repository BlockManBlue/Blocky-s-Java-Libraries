# Blocky-s-Java-Libraries
Collection of libraries you can use in your java projects

  ***   DOCUMENTATION   ***

In order to use any of these libraries in your project,
download the .jar release, and put it in 'lib' (at least for vscode)

Play my games!: https://blockmanblue.itch.io

> AnimImage - Use to draw a spritesheet as an animated image

> AnimKeyFrame - Part of the Animation system

> Animation - A single animation

> Animator - Can control one Position object with multiple animations

> Audio - Can play audio

> BSonBadFileException - Used to say when your BSon file sucks

> BSonObject - Holds a BSon variable's data

> BSonParser - Reads a .bson file

> BTools - Collection of various tools (tool documentation at bottom of page)

> CamShake - Can be used to make camera shake. You have to use the CamShake to render everything, or it'll be weird

> Camera - Renders entities based off its position

> Camera3D - Attempt at making a 3d engine. I wouldn't use this.

> CircleBar - Use to make a pie-chart-like progress bar

> Entity - An object in a game world. Handles its own rendering and logic. Meant to be extended off of

> Entity3D - Part of the 3d engine attempt

> ErrorHandler - Prints error into a file, for debugging after compiling the project into a jar or exe

> Event - Send event(int data) to run a piece of code you choose. Meant to be extended off of

> InputAdapter - Simplified inputs, extend to run specific code on key/mouse button press

> KeyManager - Keeps track of inputs

> Light - A light entity used with the LightManager

> LightManager - Simple lighting system

> ParticleEmitter - Particle System

> Player - PlayerController with a Camera

> Player3D - Again, just the weird 3D engine attempt

> PlayerController - Tracks its own inputs and moves itself accordingly

> Position - Two doubles, x and y

> ProgressBar - A horizontal bar to track a percentage

> Sequence - A system that is useful in making cutscenes

> Server - Runs a tick as fast as possible

> Smoke - An early particle system that just handles smoke

> TextBox - A better way to draw text

> TextDialog - Creates scrolling text (hasn't been tested much, might not work)

> TileManager - Creates a tilemap from a file and renders it with tilesets

> VerticalBar - A vertical ProgressBar

> WrongBSonTypeException - Says when you try to access the wrong information on a BSonObject, like trying to use getInt() on a String object


 ***  BTools  ***

> getDistance(Point p1, Point p2) or getDistance(Position p1, Position p2): get distance between p1 and p2

> getDirection(Point p1, Point p2): Worse way to get direction, DO NOT USE

> getDirectionTrig(Position p1, Position p2): Get vector direction with cool trigonomotry and stuff

> angleToVector(double radians): Translate an angle in radians to a vector with magnitude of 1

> getAngle(Position p1, Position p2): Get the angle in radians from p1 to p2

> vectorToAngle(Position vector): Translate a vector to an angle in radians

> randInt(int min, int max): Get a number between min(inclusive) and max(non-inclusive)

> randDouble(double min, double max): Get a double between min and max. DO NOT USE EVERY TICK. THIS IS INEFFICIENT

> collides(Rectangle r1, Rectangle r2): Returns whether or not r1 and r2 are colliding

> rectContains(Rectangle r, Point p): I don't know why you would use this instead of r.contains(p)

> clamp(int a, int min, int max) or with all doubles: Returns 'a' clamped between min and max

> keyToString(int key): Takes a keycode and translates it to english

> intersectsAt(Line line1, Line line2): checks where line1 and line2 intersect, or null if they don't intersect

> openWebsite(String url): Opens a website in the user's default browser

> flip(int i, int max) or with doubles: "-i + max"? I don't even know what this would be used for. Maybe it flips it around max? idek mannn

> getLastSubString(String s, int n) : Gets the last n characters of a string and puts it in a new string

> hasImage(ImageIcon i) : Checks if an ImageIcon actually has an Image attached to it 

