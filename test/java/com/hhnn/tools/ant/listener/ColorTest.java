/*
  Copyright 2008 Vichheann Saing

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.hhnn.tools.ant.listener;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
