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

  private short _winCode;
  private short _ansiCode;

  Color(int winCode, int ansiCode)
  {
    _winCode = (short) winCode;
    _ansiCode = (short) ansiCode;
  }

  public short winCode()
  {
    return _winCode;
  }

  public short ansiCode()
  {
    return _ansiCode;
  }

  public String toString()
  {
    return "(winCode:"+ _winCode + ",ansiCode:" + _ansiCode +")";
  }

  public short brighter()
  {
    short result = _winCode;
    if (result < BACKGROUND_BLUE.winCode())
    {
      result = (short) (result | FOREGROUND_INTENSITY.winCode());
    }
    else
    {
      result = (short) (result | BACKGROUND_INTENSITY.winCode());
    }
    return result;
  }

  public static short ansiCodeToWinCode(String ansiCode) throws NumberFormatException
  {
    short result = FOREGROUND_WHITE.winCode();
    if (ansiCode == null || ansiCode.length() == 0)
    {
      return result;
    }
    String[] colors = ansiCode.split(";");

    result = toWinCode(Integer.parseInt(colors[1]), isBright(Integer.parseInt(colors[0])));

    if (colors.length == 4)
    {
      result = (short) (result | toWinCode(Integer.parseInt(colors[3]), isBright(Integer.parseInt(colors[2]))));
    }

    return result;
  }

  private static boolean isBright(int value)
  {
    return value == 1;
  }

  private static short toWinCode(int ansiColor, boolean brightness) throws NumberFormatException
  {
    short result = FOREGROUND_WHITE.winCode();
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
