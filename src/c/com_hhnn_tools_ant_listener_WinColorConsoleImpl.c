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

#include <stdlib.h>
#include <windows.h>
#include "com_hhnn_tools_ant_listener_WinColorConsoleImpl.h"


int originalColors;

BOOL APIENTRY DllMain( HANDLE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                      )
{
  return TRUE;
}

JNIEXPORT void JNICALL Java_com_hhnn_tools_ant_listener_WinColorConsoleImpl_cls(JNIEnv *env, jobject obj)
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

JNIEXPORT void JNICALL Java_com_hhnn_tools_ant_listener_WinColorConsoleImpl_setCursorPosition (JNIEnv *env, jobject obj, jint xCoord, jint yCoord)
{

  HANDLE hConsole;
  COORD coordScreen;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  coordScreen.X = xCoord;
  coordScreen.Y = yCoord;
  SetConsoleCursorPosition( hConsole, coordScreen );

}

JNIEXPORT void JNICALL Java_com_hhnn_tools_ant_listener_WinColorConsoleImpl_setColor(JNIEnv *env, jobject obj, jint color)
{
  HANDLE hConsole;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  SetConsoleTextAttribute(hConsole, color);
}

JNIEXPORT void JNICALL Java_com_hhnn_tools_ant_listener_WinColorConsoleImpl_keepColors(JNIEnv *env, jobject obj)
{
  HANDLE hConsole;
  CONSOLE_SCREEN_BUFFER_INFO ConsoleInfo;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  GetConsoleScreenBufferInfo(hConsole, &ConsoleInfo);
  originalColors = ConsoleInfo.wAttributes;
}

JNIEXPORT void JNICALL Java_com_hhnn_tools_ant_listener_WinColorConsoleImpl_restoreColors(JNIEnv *env, jobject obj)
{
  HANDLE hConsole;

  hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
  SetConsoleTextAttribute(hConsole, originalColors);
}
