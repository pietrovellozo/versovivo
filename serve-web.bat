@echo off
echo Building Flutter Web...
flutter build web --release

echo.
echo Web app built successfully!
echo.
echo Starting HTTP Server on port 8080...
echo.
cd build\web

echo To access from this PC: http://localhost:8080
echo To access from other devices in your network:
echo Find your PC IP using: ipconfig
echo Then use: http://YOUR_PC_IP:8080
echo.

python -m http.server 8080
