# VersoVivo Native

Aplicativo Android para aproximar a rotina espiritual do usuario por meio de Biblia,
versiculos, devocionais e musica. O projeto usa Kotlin, Jetpack Compose, Material 3,
MVVM, Coroutines e Retrofit.

## Visao do produto

O VersoVivo deve permitir que uma pessoa encontre um versiculo, reflita sobre ele,
encontre uma musica relacionada e organize tudo em um devocional pessoal. A experiencia
deve funcionar para visitantes, mas oferecer mais valor para usuarios autenticados,
com conteudo salvo, preferencias e recomendacoes personalizadas.

## Estado atual

### Ja existe

- Projeto Android nativo com Kotlin, Compose e Java/Kotlin 17.
- Navegacao para login, home, Biblia, musicas, perfil, configuracoes e devocionais.
- Login e entrada como convidado na interface, ainda sem autenticacao real.
- Integracao parcial com a API A Biblia Digital em `https://www.abibliadigital.com.br/api/`.
- Lista de livros, selecao de livro/capitulo, carregamento de versiculos e selecao de versiculos.
- Fluxo de criacao de devocional a partir dos versiculos selecionados.
- Cards de devocionais com titulo, tema, data, versiculos e lembrete representados em memoria.
- Tema claro/escuro na tela de configuracoes.

### Ainda e prototipo

- Os dados dos devocionais existem apenas no `ViewModel` e somem ao fechar o app.
- A tela de musicas exibe cards estaticos, sem catalogo, busca ou links externos.
- A home exibe versiculos, eventos e parceiros estaticos; nao existe versiculo diario real.
- O perfil mostra um usuario ficticio e nao possui preferencia de idioma.
- Criar, editar e excluir devocionais ainda nao esta conectado a persistencia.
- O editor de notas ainda nao e um editor rico.
- A arvore `com.versovivo.native` duplica parte da implementacao ativa `com.versovivo.app`.
  Antes de novas telas, a equipe deve definir uma unica arvore oficial e remover ou migrar a outra.

## Plano de features

Cada item abaixo deve ser tratado como uma feature independente, com seu proprio ciclo
Scrum: refinamento, historias, desenvolvimento, testes, revisao e aceite.

### F00 - Consolidacao tecnica e contratos

**Objetivo:** preparar uma base unica para as funcionalidades seguintes.

**Entregas:**

- Definir `com.versovivo.app` como namespace oficial e eliminar a duplicidade de pacotes.
- Separar claramente data, domain e presentation.
- Criar estados de carregamento, vazio, erro e sucesso para as telas de rede.
- Centralizar contratos de API, mapeamento DTO/domain e tratamento de erros.
- Definir ambiente de desenvolvimento, testes unitarios e testes de UI.

**Depende de:** nenhuma.

### F01 - Biblia: livros, capitulos e versiculos

**Objetivo:** oferecer uma navegacao confiavel por toda a Biblia.

**Entregas:**

- Catalogo de livros com nome, abreviacao e quantidade de capitulos.
- Navegacao por livro e capitulo.
- Leitura e selecao de versiculos.
- Busca por referencia e texto.
- Cache local do catalogo e dos capitulos acessados.
- Contrato de versao/traducao da Biblia, sem assumir NVI em codigo fixo.

**Aceite:** o usuario consegue abrir qualquer livro/capitulo disponivel, selecionar
versiculos e recuperar a referencia canonica no restante do app.

**Depende de:** F00.

### F02 - Conta, autenticacao e perfil

**Objetivo:** distinguir visitante de usuario autenticado e criar uma identidade persistente.

**Entregas:**

- Login Google real usando Credential Manager.
- Estado de sessao persistente, logout e tratamento de falha.
- Perfil com nome, foto, email e preferencias.
- Bloqueio de `Meus Devocionais` e `Criar Devocional` para visitantes, com convite claro para login.
- Modelo de usuario pronto para sincronizacao futura.

**Aceite:** um visitante pode ler conteudo publico, mas precisa autenticar para salvar
ou editar um devocional; ao reabrir o app, a sessao e restaurada corretamente.

**Depende de:** F00.

### F03 - Idioma e traducoes

**Objetivo:** permitir que o usuario escolha o idioma da interface e, quando suportado,
da Biblia e das recomendacoes.

**Entregas:**

- Preferencia de idioma no perfil/configuracoes.
- Interface preparada para internacionalizacao, sem textos fixos espalhados nas telas.
- Primeira entrega sugerida: portugues e ingles.
- Catalogo de idiomas suportados por cada provedor de Biblia.
- Persistencia local e sincronizacao da preferencia para usuarios autenticados.

**Aceite:** trocar o idioma atualiza a interface sem perder a navegacao ou os dados do usuario.

**Depende de:** F01 para traducoes biblicas e F02 para persistencia no perfil. Pode iniciar
a preparacao junto de F00.

### F04 - Persistencia de devocionais

**Objetivo:** transformar o devocional em um recurso pessoal confiavel.

**Entregas:**

- Banco local, preferencialmente Room, para devocionais, versiculos, notas e musicas.
- Repositorio com operacoes de criar, listar, atualizar e excluir.
- Associacao dos dados ao usuario autenticado.
- Migracoes de esquema e estados de sincronizacao.
- Recuperacao dos cards depois de fechar e reabrir o app.

**Aceite:** um usuario autenticado cria um devocional, fecha o app, volta e encontra o
card com os mesmos dados.

**Depende de:** F02 e F01.

### F05 - Home com conteudo diario

**Objetivo:** tornar a home util todos os dias.

**Entregas:**

- Um versiculo do dia vindo de fonte configuravel.
- Uma musica do dia com referencia externa.
- Estado de carregamento, erro e fallback em cache.
- Historico para evitar repeticao imediata.
- Acoes para abrir o versiculo na Biblia e a musica na tela de detalhes.

**Aceite:** em cada novo dia o usuario recebe conteudo identificavel pela data, mesmo
quando a rede falha depois de um cache bem-sucedido.

**Depende de:** F01 para versiculos e F06/F07 para musicas. F04 e recomendado para historico.

### F06 - Catalogo de musicas e links para plataformas

**Objetivo:** oferecer musicas gospel sem tentar reproduzir audio dentro do app.

**Entregas:**

- Modelo de musica com titulo, artista, capa, genero, referencias e links por plataforma.
- Busca e detalhes de musica.
- Integracoes permitidas pelos provedores, respeitando limites, autenticacao e termos de uso.
- Botoes intuitivos para Spotify, YouTube Music, Deezer e YouTube quando houver link.
- Deep links com fallback para navegador ou busca web.
- Indicacao clara de que a reproducao acontece no servico externo.

**Aceite:** ao tocar em uma plataforma instalada, o usuario e encaminhado para a musica
correta; sem o app instalado, recebe um fallback funcional.

**Depende de:** F00. F02 pode ser necessario para favoritos, mas nao para abrir links publicos.

### F07 - Relacao musica-versiculo

**Objetivo:** permitir descoberta nos dois sentidos: versiculo para musica e musica para versiculo.

**Entregas:**

- Extracao de palavras-chave e temas dos versiculos selecionados.
- Busca de musicas por tema, texto e metadados, com explicacao da relevancia.
- Sugestoes de versiculos relacionadas a uma musica selecionada.
- Acao para adicionar o resultado a um devocional.
- Filtros por idioma, plataforma, artista e disponibilidade.
- Camada de recomendacao substituivel, para comecar com regras e evoluir depois para busca semantica.

**Aceite:** com Genesis 1:1 selecionado, o usuario recebe resultados relacionados; ao
selecionar uma musica, recebe referencias biblicas relacionadas e pode adiciona-las.

**Depende de:** F01, F06 e F04. F03 melhora a relevancia por idioma.

### F08 - Editor de devocional

**Objetivo:** permitir que o usuario desenvolva o card depois de cria-lo.

**Entregas:**

- Abertura do card para uma tela de trabalho.
- Adicao, remocao e reordenacao de versiculos.
- Notas com edicao rica: texto, tamanho, cor e formatacao essencial.
- Adicao manual de musicas.
- Acesso a sugestoes de musica a partir dos versiculos.
- Acesso a sugestoes de versiculos a partir das musicas.
- Salvamento automatico e indicacao de alteracoes pendentes.
- Exclusao com confirmacao e recuperacao de erro.

**Aceite:** toda alteracao do devocional permanece depois de sair e voltar para o card,
inclusive notas, versiculos e musicas.

**Depende de:** F04, F06 e F07.

### F09 - Personalizacao da experiencia logada

**Objetivo:** entregar valor crescente para quem usa o app com uma conta.

**Entregas:**

- Home adaptada ao historico, temas e preferencias do usuario.
- Favoritos de versiculos e musicas.
- Historico de leitura e de musicas abertas.
- Recomendacoes baseadas em interacoes, sempre com controle do usuario.
- Exportacao/compartilhamento de um devocional como texto ou imagem.

**Depende de:** F02, F04, F05, F06 e F08.

### F10 - Lembretes e notificacoes

**Objetivo:** ajudar o usuario a manter uma rotina sem ser invasivo.

**Entregas:**

- Lembrete de devocional com horario configuravel.
- Notificacao do versiculo e da musica do dia.
- Preferencias de frequencia, silencio e fuso horario.
- Agendamento resiliente apos reinicio do dispositivo.

**Depende de:** F04 e F05. Requer consentimento e configuracao de notificacoes do Android.

## Ordem recomendada de execucao

```text
F00 Consolidacao tecnica
  -> F01 Biblia
  -> F02 Conta e perfil
	  -> F03 Idioma
	  -> F04 Persistencia de devocionais
		  -> F08 Editor de devocional
  -> F06 Musicas e plataformas
	  -> F05 Home diaria
	  -> F07 Recomendacoes bidirecionais
		  -> F09 Personalizacao
F04 + F05 -> F10 Notificacoes
```

### Incrementos Scrum sugeridos

1. **Incremento 1:** F00 e F01. Base unica e Biblia navegavel com estados de rede.
2. **Incremento 2:** F02 e F03. Login real, sessao, perfil e idiomas iniciais.
3. **Incremento 3:** F04. Devocionais persistentes e protegidos por autenticacao.
4. **Incremento 4:** F06 e F05. Catalogo de musicas, links externos e conteudo diario.
5. **Incremento 5:** F07. Sugestoes nos dois sentidos usando versiculos e musicas.
6. **Incremento 6:** F08. Editor completo do devocional.
7. **Incremento 7:** F09 e F10. Personalizacao, favoritos, historico e notificacoes.

## Backlog adicional recomendado

- **F11 Offline-first:** permitir leitura dos capitulos e devocionais salvos sem internet.
- **F12 Acessibilidade:** suporte a leitor de tela, contraste, tamanho de fonte e alvos de toque.
- **F13 Moderacao e seguranca:** validacao de links, protecao de dados, politica de privacidade e exclusao da conta.
- **F14 Observabilidade:** logs sem dados sensiveis, monitoramento de falhas e metricas de uso anonimizadas.
- **F15 Compartilhamento:** compartilhar versiculo, musica ou trecho do devocional com referencias corretas.

Essas features devem entrar depois que os fluxos principais estiverem estaveis. Offline,
acessibilidade e seguranca, entretanto, devem ser criterios transversais de aceite desde o
primeiro incremento, e nao apenas tarefas finais.

## Regra de trabalho por feature

Cada feature deve possuir uma especificacao propria antes do desenvolvimento, contendo:

- objetivo e problema do usuario;
- historias de usuario e criterios de aceite;
- dependencias e riscos externos;
- estados de loading, vazio, erro, sucesso e offline;
- contrato de dados e impacto no banco/API;
- tarefas tecnicas, testes e Definition of Done;
- evidencia de revisao e validacao no dispositivo/emulador.

Uma feature so deve ser considerada pronta quando o fluxo principal, os estados de erro,
persistencia necessaria, acessibilidade basica e testes definidos estiverem cobertos.

## Fluxo de branches e PRs

Cada feature deve ser desenvolvida em uma branch propria, criada a partir da `main`.
O nome deve seguir o padrao:

```text
feature/F00-consolidacao-tecnica
feature/F01-biblia-livros-capitulos-versiculos
```

O fluxo obrigatorio e:

1. Criar a branch da feature a partir da `main` atualizada.
2. Refinar a feature em historias menores e implementar somente o escopo aprovado.
3. Executar os testes locais e registrar qualquer limitacao conhecida.
4. Abrir um PR para `main` descrevendo comportamento, dependencias e evidencias.
5. Revisar o diff e os criterios de aceite.
6. Fazer merge somente depois de todos os checks obrigatorios passarem.
7. Excluir a branch da feature depois do merge.

Nao deve haver commit direto na `main`. Uma feature que dependa de outra deve declarar
essa dependencia no PR e aguardar o merge da base necessaria, salvo quando o trabalho
for explicitamente dividido em branches encadeadas.

## Criterios para aceitar um PR

Todo PR precisa comprovar:

- escopo limitado a uma feature ou a uma parte explicitamente aprovada;
- todos os criterios de aceite da feature atendidos;
- build de debug concluindo sem erro;
- testes unitarios das regras de negocio e dos mapeamentos adicionados ou alterados;
- testes de UI/integracao cobrindo o fluxo principal e os estados de loading, vazio e erro;
- comportamento offline ou fallback documentado quando houver chamada de rede;
- nenhuma credencial, arquivo local ou artefato de build versionado;
- nenhuma regressao nas telas e fluxos existentes;
- acessibilidade basica: textos legiveis, descricao de icones, contraste e alvos de toque;
- README ou documentacao da feature atualizada quando contrato, configuracao ou comportamento mudar.

O PR deve incluir uma secao `Como testar` com ambiente usado, passos reproduziveis e
resultado observado. Screenshots ou video devem ser anexados quando houver mudanca visual.

### Testes minimos por tipo de mudanca

| Mudanca | O teste deve contemplar |
| --- | --- |
| Regra de negocio ou ViewModel | sucesso, entrada vazia/invalida, erro e estado final |
| API ou mapeamento DTO | resposta valida, resposta incompleta, erro HTTP e erro de rede |
| Persistencia | criar, consultar, atualizar, excluir e reabrir os dados |
| Navegacao ou autenticacao | acesso permitido, bloqueio, retorno, logout e restauracao de sessao |
| Tela Compose | renderizacao, interacao principal, loading, vazio, erro e rotacao quando aplicavel |
| Link externo ou plataforma | app instalado, app ausente, URL invalida e fallback para navegador |

## F00 - Definition of Done

O PR da F00 so pode ser aceito quando:

- existir uma unica arvore de codigo ativa, com a duplicidade `com.versovivo.native`
	versus `com.versovivo.app` resolvida e documentada;
- o namespace, o package da aplicacao e a navegacao compilarem usando a arvore oficial;
- as camadas `data`, `domain` e `presentation` tiverem responsabilidades claras;
- chamadas de API usarem contratos e mapeadores centralizados;
- telas de rede apresentarem loading, sucesso, vazio e erro sem travar a aplicacao;
- houver testes para mapeamento, tratamento de erro e estados dos ViewModels alterados;
- o build de debug passar em uma maquina limpa ou com o ambiente documentado;
- nao houver regressao no login, home, Biblia, musicas, perfil, configuracoes e devocionais;
- o README registrar a decisao arquitetural e os comandos usados para validar o PR.

### Plano de testes da F00

1. Executar `./gradlew assembleDebug` no Linux/macOS ou `gradlew.bat assembleDebug` no Windows.
2. Executar os testes unitarios com `./gradlew test` ou `gradlew.bat test`.
3. Instalar o APK de debug em emulador/dispositivo e abrir a aplicacao.
4. Navegar pelo fluxo principal: login/convidado, home, Biblia, selecao de versiculo,
	 criacao/listagem de devocional, musicas, perfil e configuracoes.
5. Simular indisponibilidade de rede e confirmar mensagem de erro e possibilidade de recuperar.
6. Verificar que a orientacao/rotacao nao causa crash nos fluxos alterados.
7. Registrar no PR o resultado de cada comando e teste manual.

## Como executar

1. Abra `android_native` no Android Studio.
2. Use o JDK 17 ou superior compativel com o Gradle Wrapper.
3. Execute `gradlew.bat assembleDebug` e `gradlew.bat test` no Windows.
4. Execute `./gradlew assembleDebug` e `./gradlew test` no Linux/macOS.
5. Instale o APK de debug em um emulador ou dispositivo Android.
6. As configuracoes locais, como `local.properties`, nao devem ser commitadas.
