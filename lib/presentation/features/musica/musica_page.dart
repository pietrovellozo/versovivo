import 'package:flutter/material.dart';

class MusicaPage extends StatelessWidget {
  const MusicaPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Música')),
      body: const Center(
        child: Text('Conteúdo de música aqui'),
      ),
    );
  }
}
