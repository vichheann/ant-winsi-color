#include <stdlib.h>
#include <windows.h>
#include "com_piaction_tools_ant_listener_WinColorConsole.h"


int originalColors;

BOOL APIENTRY DllMain( HANDLE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                      )
{
  return TRUE;
}

/*
    int hWrittenChars = 0;
      CONSOLE_SCREEN_BUFFER_INFO strConsoleInfo;
      COORD Home;

      GetConsoleScreenBufferInfo(hConsoleHandle, &strConsoleInfo);
      FillConsoleOutputCharacter(hConsoleHandle, EMPTY, strConsoleInfo.dwSize.x * strConsoleInfo.dwSize.y, Home, hWrittenChars);
      SetConsoleCursorPosition(hConsoleHandle, Home);
*/

JNIEXPORT void JNICALL Java_com_piaction_tools_ant_listener_WinColorConsole_cls(JNIEnv *env, jobject obj)
{

  HANDLE hConsole;
  unsigned long * hWrittenChars = 0;
  CONSOLE_SCREEN_BUFFER_INFO strConsoleInfo;
    COORD Home;
  static unsigned char EMPTY = 32;

  Home.X = 0;
  Home.Y = 0;
  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  GetConsoleScreenBufferInfo(hConsole, &strConsoleInfo);
  FillConsoleOutputCharacter(hConsole, EMPTY,
      strConsoleInfo.dwSize.X * strConsoleInfo.dwSize.X, Home, hWrittenChars);
  SetConsoleCursorPosition(hConsole, Home);
  // system("cls");  will do the same as the above!
}

JNIEXPORT void JNICALL Java_com_piaction_tools_ant_listener_WinColorConsole_setCursorPosition (JNIEnv *env, jobject obj, jshort x, jshort y)
{

  HANDLE hConsole;
  COORD coordScreen;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  coordScreen.X = x;
  coordScreen.Y = y;
  SetConsoleCursorPosition( hConsole, coordScreen );

}

JNIEXPORT void JNICALL Java_com_piaction_tools_ant_listener_WinColorConsole_setColor(JNIEnv *env, jobject obj, jshort color)
{
  HANDLE hConsole;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  SetConsoleTextAttribute(hConsole, color);
}

JNIEXPORT void JNICALL Java_com_piaction_tools_ant_listener_WinColorConsole_keepColors(JNIEnv *env, jobject obj)
{
  HANDLE hConsole;
  CONSOLE_SCREEN_BUFFER_INFO ConsoleInfo;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  GetConsoleScreenBufferInfo(hConsole, &ConsoleInfo);
  originalColors = ConsoleInfo.wAttributes;
}

JNIEXPORT void JNICALL Java_com_piaction_tools_ant_listener_WinColorConsole_restoreColors(JNIEnv *env, jobject obj)
{
  HANDLE hConsole;
  
  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  SetConsoleTextAttribute(hConsole, originalColors);
}
