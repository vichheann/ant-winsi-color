# Color Output for Ant on Windows

I use the `ant` build tool a lot and I find fun to enable the `ANSIColorLogger` on Unix or Linux system. But this does not work when using `ant` on Windows NT system. So I decide to make it run, et voilà !

## Install

You can just get the zip file from the Files tab section and pass the build section.
You may prefer to build from sources, so you will need:

* [JDK 1.5+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [ant 1.7.x](http://ant.apache.org) to build the java part
* [MinGW](http://www.mingw.org/) to build the JNI part

## Let's build ...

1. Have your `ant` install work properly (i.e. `java`, `%JAVA_HOME%`, ....).
2. Have your `MinGW` install running and put `gcc` in your `%PATH%`
3. Run ... `ant`

You should have a zip file containing:

* `com.2h2n.tools.ant.jar`
* `com.2h2n.tools.ant.jar.dll`
* a configuration file defining the mapping between `ant` log level and colors to display
* a `antrc_pre.bat` file to setup the environment for `ant`

## Let's try ...

Once you get all that,

1. Copy `antrc_pre.bat` in `%USERPROFILE%`
2. Copy `ansi.color` in `%USERPROFILE%/.ant`
3. Copy `com.2h2n.tools.ant.dll` in `%USERPROFILE%/.ant`
4. Copy `com.2h2n.tools.ant.jar` in `%USERPROFILE%/.ant/lib`
5. Open a new DOS Console and run `ant` from the project. You should see colors !!

You can install this file wherever you want. Read the .bat file and `ant` manual for configuration.

## Configuration

Just read the `ansi.color` file to define your colors. You can install this file wherever you want. Read the `antrc_pre.bat` file and `ant manual` for configuration.

Have fun building with ant !! (Or not)



