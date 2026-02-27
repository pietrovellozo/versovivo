import 'package:flutter/material.dart';
import '../features/devocional/devocional_page.dart';
import '../features/musica/musica_page.dart';
import '../features/usuario/usuario_page.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Verso Vivo')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => const DevocionalPage(),
                    ));
              },
              child: const Text('Devocionais'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => const MusicaPage(),
                    ));
              },
              child: const Text('Músicas'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => const UsuarioPage(),
                    ));
              },
              child: const Text('Usuário'),
            ),
          ],
        ),
      ),
    );
  }
}
