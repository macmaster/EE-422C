:: Batch timer
:: plays a zelda ringtone
:: takes seconds as cl arg

@echo off
set t=%~1

:1
echo %t%
set /A t=%t%-1
if %t% lss 0 (
   start /min sound.vbs
   sleep 5
   start /min sound.vbs
   exit /B
)

sleep 1
goto 1