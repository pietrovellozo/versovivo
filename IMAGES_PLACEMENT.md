# 📍 Instruções de Colocação de Imagens

Coloque as suas imagens PNG nos seguintes locais:

## 🤖 Android Icons

```
android/app/src/main/res/
├── mipmap-mdpi/ic_launcher.png              ← 48.png
├── mipmap-hdpi/ic_launcher.png              ← 72.png
├── mipmap-xhdpi/ic_launcher.png             ← 96.png
├── mipmap-xxhdpi/ic_launcher.png            ← 144.png
└── mipmap-xxxhdpi/ic_launcher.png           ← 192.png
```

Comando para verificar (execute no terminal):
```bash
ls android/app/src/main/res/mipmap-*
```

---

## 🍎 iOS Icons

```
ios/Runner/Assets.xcassets/AppIcon.appiconset/
└── Icon-App-1024x1024@1x.png               ← 1024.png
```

---

## 🎨 Flutter Assets (App UI)

```
assets/images/
├── logo_300.png                             ← 300.png (splash/homepage)
└── logo_100.png                             ← 100.png (header)
```

**Já configurado no pubspec.yaml** ✅

---

## 🔧 Próximas etapas

1. ✅ Copia as imagens para os locais acima
2. ✅ Verifica os arquivos foram criados
3. ✅ Faz `git add` e `git commit`
4. ✅ Faz `git push`
5. ✅ GitHub Actions compila e faz staging deploy
6. ✅ Testa na web em: https://pietrovellozo.github.io/versovivo/staging/feature-add-images-and-splash/
