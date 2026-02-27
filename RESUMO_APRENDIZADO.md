# 📚 Resumo de Aprendizado - Verso Vivo App
## Data: 27 de Fevereiro de 2026

---

## 🎯 Objetivo Geral
Construir um **aplicativo profissional de devocionais e música gospel** em Flutter com arquitetura escalável, testes desde o início e pipeline de CI/CD totalmente automatizado.

---

## 📖 O Que Aprendemos

### 1️⃣ **Plano de Arquitetura Profissional**

#### Conceitos estudados:
- **Clean Architecture (Robert C. Martin)**: Separação clara de responsabilidades em camadas
  - `Domain`: Entidades, repositórios abstratos, casos de uso
  - `Data`: Modelos, datasources, implementações de repositórios
  - `Presentation`: UI, widgets, gerenciamento de estado
  - `Core`: Utilitários compartilhados, temas, constantes

- **Escalabilidade por Microserviços** (Backend):
  - `Verse Service`: Busca e cache de versículos bíblicos
  - `Music Service`: Catálogo de músicas gospel
  - `Devotional Service`: Combina versículo + música + reflexão
  - `User Service`: Autenticação e preferências

- **Boas Práticas**:
  - Testes unitários, widget tests e e2e desde o início
  - Gerenciamento de estado com Riverpod/Bloc
  - Versionamento de API com OpenAPI/Swagger
  - Logging e monitoramento básico
  - Dependência inversa (inversão de controle)

---

### 2️⃣ **Setup do Projeto Flutter**

#### Passos executados:

1. **Verificação do ambiente**:
   ```bash
   flutter --version          # Verificar instalação
   flutter doctor             # Diagnóstico completo
   flutter doctor -v          # Diagnóstico detalhado
   ```

2. **Criação do projeto com nome válido**:
   ```bash
   flutter create --project-name=versovivo_app .
   # Importante: Nomes de pacotes Dart devem ser lowercase_underscore
   ```

3. **Investigação do cenário**:
   - Windows disponível ✅
   - Android Studio instalado ✅
   - iOS impossível (só em macOS)
   - Android emulator pronto
   - Web (Chrome) disponível

#### Tecnologias instaladas:
- Flutter 3.41.2
- Dart 3.11.0
- NDK (Native Development Kit)
- Android Build-Tools 35
- CMake 3.22.1
- Gradle (para compilação Android)

---

### 3️⃣ **Estrutura de Pastas (Clean Architecture)**

```
VersoVivo/
├── lib/
│   ├── core/                    # Compartilhado
│   │   ├── constants/           # app_constants.dart
│   │   ├── theme/               # app_theme.dart (cores, tipografia)
│   │   └── utils/               # logger.dart (logging)
│   ├── domain/                  # Lógica de negócio (independente)
│   │   ├── entities/            # Devocional, Musica, Usuario (POJO)
│   │   ├── repositories/        # Interfaces abstratas
│   │   └── usecases/            # Casos de uso (lógica isolada)
│   ├── data/                    # Camada de dados
│   │   ├── models/              # Conversores de JSON
│   │   ├── datasources/         # APIs, BD locais
│   │   └── repositories_impl/   # Implementações de repositórios
│   └── presentation/            # UI e estado
│       ├── features/
│       │   ├── devocional/      # Feature de devocionais
│       │   ├── musica/          # Feature de músicas
│       │   └── usuario/         # Feature de usuário
│       ├── screens/             # home_screen.dart
│       ├── widgets/             # Componentes reutilizáveis
│       └── state/               # devocional_provider.dart (Riverpod)
├── test/
│   ├── unit/                    # Testes de lógica pura
│   └── widget/                  # Testes de UI
├── integration_test/            # Testes e2e
├── android/                     # Código nativo Android
├── ios/                         # Código nativo iOS
├── web/                         # Versão web (Flutter web)
├── .github/workflows/           # GitHub Actions
├── pubspec.yaml                 # Dependências
└── README.md                    # Documentação

```

#### Por que essa estrutura?
- **Modularidade**: Cada feature é independente e testável
- **Escalabilidade**: Fácil adicionar novos serviços/features
- **Manutenibilidade**: Responsabilidades bem definidas
- **Testabilidade**: Inversão de dependência facilita testes
- **Reutilização**: Core compartilhado entre features

---

### 4️⃣ **Dependências Adicionadas**

No `pubspec.yaml`:
```yaml
dependencies:
  flutter_riverpod: ^2.3.0    # Gerenciamento de estado
  http: ^0.13.6              # Requisições HTTP
```

**Por que essas?**
- **Riverpod**: Alternativa moderna ao Provider, melhor composição
- **HTTP**: Para chamadas à API backend

---

### 5️⃣ **Estrutura de Testes (TDD)**

#### Criado:
- `test/unit/` → Testes de funções e classes isoladas
- `test/widget/` → Testes de widgets e UI
- `integration_test/` → Testes end-to-end (fluxo completo)
- `TEST_GUIDELINES.md` → Documentação de como testar

#### Exemplos implementados:

**Unit Test** (`test/unit/app_constants_test.dart`):
```dart
test('appName should be Verso Vivo', () {
  expect(AppConstants.appName, 'Verso Vivo');
});
```

**Widget Test** (`test/widget/home_screen_test.dart`):
```dart
testWidgets('HomeScreen renders with three buttons', (tester) async {
  expect(find.byType(ElevatedButton), findsWidgets);
});
```

**Integration Test** (`integration_test/app_test.dart`):
```dart
testWidgets('Full navigation flow', (tester) async {
  // Testa navegação entre todas as telas
});
```

#### Comandos aprendidos:
```bash
flutter test                  # Roda todos os testes
flutter test test/unit       # Testa apenas unitários
flutter test --coverage      # Gera relatório de cobertura
```

---

### 6️⃣ **Versionamento (Git & GitHub)**

#### Conceitos:

**Commits significativos feitos**:
1. "Initial Flutter project skeleton and backend structure" (142 arquivos)
2. "Add CI workflow and MIT license"
3. "Add test structure and testing guidelines"
4. "Add professional branching workflow"
5. "Rename GitHub Actions workflows to Portuguese"

#### Comandos executados:
```bash
git init                              # Inicializar repo local
git add .                             # Preparar arquivos
git commit -m "mensagem clara"        # Criar commit
git remote add origin https://...     # Adicionar remote
git branch -M main                    # Renomear branch
git push -u origin main               # Enviar para GitHub
```

#### Boas práticas aprendidas:
- Commits com mensagens **descritivas e claras**
- Uma feature = uma branch (`feature/...`)
- Pull Request com template padronizado
- Não fazer commit direto em `main` (fluxo colaborativo)

---

### 7️⃣ **CI/CD com GitHub Actions**

#### 4 Workflows criados:

**1. 🧪 Testes e Análise - Main** (`.github/workflows/flutter.yml`)
- Dispara: Quando há `push` em `main`
- Faz: `flutter pub get`, `flutter analyze`, `flutter test`
- Objetivo: Garantir qualidade antes de deploy

**2. 🚀 Deploy Web - Produção** (`.github/workflows/deploy-web.yml`)
- Dispara: Quando há `push` em `main`
- Faz: Compila `flutter build web`, faz deploy via GitHub Pages
- URL: `https://pietrovellozo.github.io/versovivo/`
- Objetivo: Versão de produção sempre atualizada

**3. 🔄 Deploy Staging - Feature Branches** (`.github/workflows/deploy-staging.yml`)
- Dispara: Quando há `push` em branches `feature/*`, `dev`, `develop`
- Faz: Compila web, faz deploy numa subpasta (`staging/feature-nome/`)
- URL: `https://pietrovellozo.github.io/versovivo/staging/feature-teste/`
- Objetivo: Testar features antes de merge

**4. ✅ Testes e Qualidade - PR** (`.github/workflows/pr-tests.yml`)
- Dispara: Quando há Pull Request para `main`
- Faz: Testes, análise, valida cobertura
- Objetivo: Bloquear merges com erro

#### Fluxo de trabalho profissional:

```
┌─────────────────────────────────────────────────────────────┐
│ 1. Cria feature/nova-tela                                   │
│    $ git checkout -b feature/nova-tela                      │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│ 2. Desenvolve e faz commit                                   │
│    $ git add .                                              │
│    $ git commit -m "feat: nova tela de devocionais"         │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│ 3. Faz push → 🔄 Deploy Staging roda                        │
│    $ git push origin feature/nova-tela                      │
│    ✅ Testes passam                                         │
│    ✅ Web building completo                                  │
│    ✅ Staging deploy em ./staging/feature-nova-tela/        │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│ 4. Cria Pull Request no GitHub                              │
│    → ✅ Testes e Qualidade roda                             │
│    → Valida código antes do merge                           │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│ 5. Aprova e faz merge para main                             │
│    → 🧪 Testes e Análise roda em main                       │
│    → 🚀 Deploy Web - Produção compila e faz deploy          │
│    → Produção atualizada em ./                              │
└─────────────────────────────────────────────────────────────┘
```

---

### 8️⃣ **Problemas Encontrados & Soluções**

| Problema | Causa | Solução | Lição |
|----------|-------|--------|-------|
| `flutter create` não funcionava | Nome "VersoVivo" inválido em Dart | Usar `--project-name=versovivo_app` | Nomes de pacotes devem ser lowercase_underscore |
| Windows desktop não compila | Visual Studio toolchain ausente | Instalar Visual Studio + C++ workload | Desktop targets precisam de compiladores nativos |
| Emulador Android não aparecia | Ambiente sem GUI | Android está instalado e pronto | Emuladores precisam de interface gráfica |
| GitHub Actions bloqueado | Repositório privado → público sem cartão | Adicionar método de pagamento (verificação) | GitHub exige pagamento validado mesmo pra público |
| Actions falhavam por permissões | OAuth token expirado após mudança | Fazer `git push` novamente (retrigger) | Workflows podem precisar de refresh após mudanças de config |

---

### 9️⃣ **Tecnologias Utilizadas**

| Camada | Tecnologia | Uso |
|-------|-----------|-----|
| **Frontend** | Flutter 3.41.2 | Cross-platform mobile/web |
| **Linguagem** | Dart 3.11.0 | Linguagem do Flutter |
| **Estado** | Riverpod 2.6.1 | Gerenciamento reativo |
| **HTTP** | http 0.13.6 | Chamadas REST API |
| **Versionamento** | Git | Controle de versão |
| **Repositório** | GitHub | Hospedagem + CI/CD |
| **CI/CD** | GitHub Actions | Automação de testes/deploy |
| **Hosting** | GitHub Pages | Deploy web automático |
| **Testes** | flutter_test | Unit + widget + integration |
| **Análise** | flutter analyze | Qualidade de código |
| **Build** | Gradle | Compilação Android |

---

### 🔟 **Comandos Mais Usados**

```bash
# Criar projeto
flutter create --project-name=versovivo_app .

# Gerenciar dependências
flutter pub get              # Baixar dependências
flutter pub outdated         # Verificar atualizações
flutter pub upgrade          # Atualizar tudo

# Compilar e rodar
flutter run                  # Roda na plataforma padrão
flutter run -d windows       # Roda no Windows
flutter run -d emulator-5554 # Roda no emulador Android
flutter run -d chrome        # Roda na web

# Testes
flutter test                 # Todos os testes
flutter test --coverage      # Com cobertura
flutter test test/unit       # Apenas unitários

# Análise
flutter analyze              # Lint do Dart
flutter doctor              # Diagnóstico do ambiente

# Git
git checkout -b feature/nome             # Criar branch
git add . && git commit -m "msg"         # Commit
git push origin feature/nome              # Push
git checkout main && git pull origin main # Atualizar main
```

---

### 1️⃣1️⃣ **Recursos Criados**

✅ **Arquivos principais**:
- `lib/main.dart` – Ponto de entrada da app
- `lib/core/` – Temas, constantes, utilitários
- `lib/domain/` – Entidades (Musica, Usuario)
- `lib/presentation/screens/` – HomeScreen, DevocionalPage, MusicaPage, UsuarioPage
- `test/` – Testes unit, widget e integration
- `pubspec.yaml` – Dependências e configuração
- `README.md` – Documentação do projeto
- `.github/workflows/` – 4 workflows profissionais
- `.github/pull_request_template.md` – Template para PRs

✅ **Backend (estrutura)**:
- `backend/verse_service/` – Serviço de versículos
- `backend/music_service/` – Serviço de músicas
- `backend/devotional_service/` – Serviço de devocionais
- `backend/user_service/` – Serviço de usuários

---

### 1️⃣2️⃣ **Links e Recursos Ativos**

- 📁 **Repositório**: https://github.com/pietrovellozo/versovivo
- 🌐 **Produção (main)**: https://pietrovellozo.github.io/versovivo/
- 🧪 **Staging (features)**: https://pietrovellozo.github.io/versovivo/staging/feature-nome/
- 📊 **GitHub Actions**: https://github.com/pietrovellozo/versovivo/actions

---

## 📝 Reflexões & Próximos Passos

### O que foi conquistado:
✅ Arquitetura profissional escalável
✅ Testes desde o dia 1
✅ CI/CD completamente automatizado
✅ Deploy em staging e produção
✅ Código versionado com Git
✅ Documentação e guidelines

### O que ainda precisa:
- [ ] Criar tela de splash com logo
- [ ] Integrar API de versículos
- [ ] Integrar catálogo de músicas
- [ ] Implementar autenticação
- [ ] Adicionar favoritos/histórico
- [ ] Multi-idioma (pt-BR, en-US, es-ES)
- [ ] Notificações de devocional diário
- [ ] Deploy no Play Store (Android)
- [ ] Deploy na App Store (iOS)
- [ ] Backend em Node.js ou Dart

---

## 🎓 Estrutura para Estudos Diários

**Cada dia estude e implemente uma coisa:**

1. **Dia 1 (hoje)**: Arquitetura, setup, CI/CD ✅
2. **Dia 2**: Tela de login/autenticação
3. **Dia 3**: Integrar API de versículos (Bible API)
4. **Dia 4**: Tela de devocional com versículo
5. **Dia 5**: Player de música
6. **Dia 6**: Favoritos e histórico
7. **Dia 7**: Testes para features anteriores
8. **Dia 8+**: Backend, multi-idioma, notificações, deploy stores

---

## 📚 Referências Estudadas

- Clean Architecture - Robert C. Martin
- Flutter Best Practices
- GitHub Actions Documentation
- Git Workflow (GitHub Flow)
- Test-Driven Development (TDD)
- Dart Programming Language
- Riverpod State Management

---

**Criado em:** 27 de Fevereiro de 2026  
**Projeto:** Verso Vivo - Aplicativo de Devocionais e Música Gospel  
**Status:** MVP com infraestrutura pronta ✅
