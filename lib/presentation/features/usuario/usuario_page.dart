import 'package:flutter/material.dart';

class UsuarioPage extends StatelessWidget {
  const UsuarioPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Usuário')),
      body: const Center(
        child: Text('Configurações de usuário aqui'),
      ),
    );
  }
}
