import 'package:flutter/material.dart';

class DevocionalPage extends StatelessWidget {
  const DevocionalPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Devocional')),
      body: const Center(
        child: Text('Conteúdo do devocional aqui'),
      ),
    );
  }
}
