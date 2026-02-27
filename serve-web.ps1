#!/usr/bin/env pwsh

Write-Host "Building Flutter Web..." -ForegroundColor Cyan
flutter build web --release

Write-Host "Web app built successfully!" -ForegroundColor Green
Write-Host ""

# Get local IP
$localIP = (Get-NetIPAddress -AddressFamily IPv4 | Where-Object { $_.InterfaceAlias -notlike '*Loopback*' } | Select-Object -First 1).IPAddress

Write-Host "Starting HTTP Server on port 8080..." -ForegroundColor Cyan
Write-Host ""
Write-Host "Access from this PC:    http://localhost:8080" -ForegroundColor Yellow
Write-Host "Access from other devices in your network:" -ForegroundColor Yellow
Write-Host "http://${localIP}:8080" -ForegroundColor Yellow
Write-Host ""

Set-Location build\web
python -m http.server 8080
