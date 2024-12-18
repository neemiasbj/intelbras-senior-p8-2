@ECHO OFF
java -d64 -version  >nul 2>&1
REM set java64bit=%ERRORLEVEL%
if %ERRORLEVEL%==0 (
  .\prunsrv64.exe //IS//HIKVISION --Install="%CD%\prunsrv64.exe" --DisplayName="Intelbras Middleware SeniorP8" --Jvm=auto --StartPath="%CD%" --Classpath=intelbras.jar --StartMode=jvm --StartClass=br.com.thidi.middleware.ServiceManager --StartMethod=execute --StartParams=start --StopMode=jvm --StopClass=br.com.thidi.middleware.ServiceManager --StopMethod=execute --StopParams=stop --LogPath="%CD%\log" --StdOutput=auto --StdError=auto --Startup=auto --JvmOptions=-Xms512M ++JvmOptions=-Xmx512M
) else (
  .\prunsrv32.exe //IS//HIKVISION --Install="%CD%\prunsrv32.exe" --DisplayName="Intelbras Middleware SeniorP8" --Jvm=auto --StartPath="%CD%" --Classpath=intelbras.jar --StartMode=jvm --StartClass=br.com.thidi.middleware.ServiceManager --StartMethod=execute --StartParams=start --StopMode=jvm --StopClass=br.com.thidi.middleware.ServiceManager --StopMethod=execute --StopParams=stop --LogPath="%CD%\log" --StdOutput=auto --StdError=auto --Startup=auto --JvmOptions=-Xms512M ++JvmOptions=-Xmx512M
)