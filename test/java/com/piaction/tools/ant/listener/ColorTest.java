package com.piaction.tools.ant.listener;

import org.junit.Test;
import static org.junit.Assert.*;

public class ColorTest
{
  @Test
  public void ansiCodeToWinCodeShouldGetColor()
  {
    assertEquals("white", Color.FOREGROUND_WHITE.winCode(), Color.ansiCodeToWinCode(null));
    assertEquals("white", Color.FOREGROUND_WHITE.winCode(), Color.ansiCodeToWinCode(""));
    assertEquals("red", Color.FOREGROUND_RED.winCode(), Color.ansiCodeToWinCode("2;31"));
    assertEquals("green", Color.FOREGROUND_GREEN.winCode(), Color.ansiCodeToWinCode("2;32"));
    assertEquals("blue", Color.FOREGROUND_BLUE.winCode(), Color.ansiCodeToWinCode("2;34"));
    assertEquals("magenta", Color.FOREGROUND_MAGENTA.winCode(), Color.ansiCodeToWinCode("2;35"));
    assertEquals("cyan", Color.FOREGROUND_CYAN.winCode(), Color.ansiCodeToWinCode("2;36"));

    assertEquals("bright cyan", Color.FOREGROUND_CYAN.brighter(), Color.ansiCodeToWinCode("1;36"));
    assertEquals("magenta background", Color.BACKGROUND_MAGENTA.winCode(), Color.ansiCodeToWinCode("2;45"));
    assertEquals("bright magenta background", Color.BACKGROUND_MAGENTA.brighter(), Color.ansiCodeToWinCode("1;45"));
  
    assertEquals("green+magenta", Color.FOREGROUND_GREEN.winCode()|Color.BACKGROUND_MAGENTA.winCode(), Color.ansiCodeToWinCode("2;32;2;45"));
  }
}
