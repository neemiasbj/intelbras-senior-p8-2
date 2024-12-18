@ECHO OFF
java -d64 -version  >nul 2>&1
REM set java64bit=%ERRORLEVEL%
if %ERRORLEVEL%==0 (
  .\prunsrv64.exe //DS//HIKVISION
) else (
  .\prunsrv32.exe //DS//HIKVISION
)