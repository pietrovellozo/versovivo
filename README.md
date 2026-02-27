# Verso Vivo

Aplicativo de devocionais e música gospel construído com Flutter e Clean Architecture.

## Estrutura

- `lib/core` → utilitários, temas, constantes
- `lib/domain` → entidades, repositórios, casos de uso
- `lib/data` → modelos, datasources, implementações de repositórios
- `lib/presentation` → UI: telas, widgets, estado

### Páginas disponíveis
- `HomeScreen` com botões para Devocional, Música e Usuário

**Este é um esqueleto inicial.** Popule com lógica, modelos e integrações.

## 🚀 Fluxo de Deploy

### Branch Workflow (GitHub Flow)

```
feature/nova-tela    →  [Tests via PR]  →  PR criado e aprovado  →  merge main
                            ↓
                      Staging deployment
                  https://pietrovellozo.github.io/versovivo/staging/feature-nova-tela

main                 →  [Auto Deploy]  →  GitHub Actions build  →  Web live
                            ↓
                      Production deployment
                  https://pietrovellozo.github.io/versovivo/
```

### Como trabalhar com features

1. **Crie uma branch da main**:
   ```bash
   git checkout -b feature/sua-feature
   ```

2. **Faça suas alterações e commits**:
   ```bash
   git add .
   git commit -m "descrição clara"
   ```

3. **Dê push — GitHub Actions testa e deploya em staging**:
   ```bash
   git push origin feature/sua-feature
   ```
   - Actions roda testes automaticamente
   - Se passar, web versão fica em: `https://pietrovellozo.github.io/versovivo/staging/feature-sua-feature`

4. **Crie um Pull Request (PR) no GitHub**:
   - Descreva o que foi mudado usando o template
   - Aguarde testes automáticos passarem
   - Peça revisão

5. **Após aprovação, faça merge**:
   - Merge para `main`
   - GitHub Actions deploya versão de produção
   - App fica visível em: `https://pietrovellozo.github.io/versovivo/`

### Status das Workflows

- ✅ **feature/\*** → Testa e deploya em staging
- ✅ **Pull Requests** → Executa testes automáticos
- ✅ **main** → Testa e deploya em produção (GitHub Pages)
