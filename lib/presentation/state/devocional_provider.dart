// exemplo de provedor Riverpod para devocionais
import 'package:flutter_riverpod/flutter_riverpod.dart';

final devocionalProvider = StateProvider<String>((ref) {
  return 'Versículo do dia';
});
