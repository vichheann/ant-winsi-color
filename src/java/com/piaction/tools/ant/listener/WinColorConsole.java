package com.piaction.tools.ant.listener;

public class WinColorConsole
{
  public native void cls();
  public native void setCursorPosition(short x, short y);
  public native void keepColors();
  public native void restoreColors();
  public native void setColor(short color);
  static
  {
    String OS = System.getProperty("os.name").toLowerCase();
    if (OS.indexOf("windows") > -1 || OS.indexOf("nt") > -1)
      System.loadLibrary("WinColorConsole");
  }
}
