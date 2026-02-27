# Testing Structure

Este projeto segue uma hierarquia clara de testes para garantir qualidade e confiabilidade.

## 📂 Organização de Testes

```
test/
├── unit/              # Testes de unidades (funções, classes, lógica pura)
│   └── app_constants_test.dart
│
└── widget/            # Testes de widgets e UI (componentes Flutter)
    └── home_screen_test.dart

integration_test/
└── app_test.dart      # Testes end-to-end (fluxo completo da app)
```

## ▶️ Como rodar os testes

### Testes unitários
```bash
flutter test test/unit
```

### Testes de widget
```bash
flutter test test/widget
```

### Testes de integração
```bash
flutter test integration_test/app_test.dart
```

### Todos os testes
```bash
flutter test
```

### Com cobertura
```bash
flutter test --coverage
```

## 📋 Convenções

- **Unit tests**: Testam lógica isolada (UseCases, Repositories, Models)
- **Widget tests**: Testam componentes UI e comportamento de tela
- **Integration tests**: Testam fluxos completos da aplicação (e2e)

Adicione novos testes em seus diretórios respectivos conforme o desenvolvimento.
