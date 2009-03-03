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

package com.piaction.tools.ant.listener;

public enum Color
{
  FOREGROUND_BLACK(0x0, 30),
  FOREGROUND_BLUE(0x1, 34),
  FOREGROUND_GREEN(0x2, 32),
  FOREGROUND_RED(0x4, 31),
  FOREGROUND_WHITE(0x7, 37),
  FOREGROUND_CYAN(0x1|0x2, 36),
  FOREGROUND_MAGENTA(0x1|0x4, 35),
  FOREGROUND_YELLOW(0x2|0x4, 33),
  FOREGROUND_GRAY(0x1|0x2|0x4, 38),
  FOREGROUND_INTENSITY(0x8, 1),

  BACKGROUND_BLUE(0x10, 44),
  BACKGROUND_GREEN(0x20, 42),
  BACKGROUND_RED(0x40, 41),
  BACKGROUND_CYAN(0x10|0x20, 46),
  BACKGROUND_MAGENTA(0x10|0x40, 45),
  BACKGROUND_YELLOW(0x20|0x40, 43),
  BACKGROUND_INTENSITY(0x80, 1);

  private int winCode;
  private int ansiCode;

  Color(final int winCode, final int ansiCode)
  {
    this.winCode = winCode;
    this.ansiCode = ansiCode;
  }

  public int winCode()
  {
    return winCode;
  }

  public int ansiCode()
  {
    return ansiCode;
  }

  public String toString()
  {
    return "(winCode:"+ winCode + ",ansiCode:" + ansiCode +")";
  }

  public int brighter()
  {
    int result = winCode;
    if (result < BACKGROUND_BLUE.winCode())
    {
      result = result | FOREGROUND_INTENSITY.winCode();
    }
    else
    {
      result = result | BACKGROUND_INTENSITY.winCode();
    }
    return result;
  }

  public static int ansiCodeToWinCode(final String ansiCode) throws NumberFormatException
  {
    int result;
    String[] colors;

    if (ansiCode == null || ansiCode.length() == 0)
    {
      result = FOREGROUND_WHITE.winCode();
    }
    else
    {
      colors = ansiCode.split(";");

      result = toWinCode(Integer.parseInt(colors[1]), isBright(Integer.parseInt(colors[0])));

      if (colors.length == 4)
      {
        result = result | toWinCode(Integer.parseInt(colors[3]), isBright(Integer.parseInt(colors[2])));
      }
    }
    return result;
  }

  private static boolean isBright(final int value)
  {
    return value == 1;
  }

  private static int toWinCode(final int ansiColor, final boolean brightness) throws NumberFormatException
  {
    int result = FOREGROUND_WHITE.winCode();
    for (Color color : Color.values())
    {
      if (color.ansiCode() == ansiColor)
      {
        result = brightness?color.brighter():color.winCode();
      }
    }
    return result;
  }
}
