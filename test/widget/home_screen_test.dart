import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:versovivo_app/presentation/screens/home_screen.dart';

void main() {
  group('HomeScreen Widget Tests', () {
    testWidgets('HomeScreen renders with three buttons', (WidgetTester tester) async {
      await tester.pumpWidget(
        const MaterialApp(home: HomeScreen()),
      );

      expect(find.byType(ElevatedButton), findsWidgets);
      expect(find.text('Devocionais'), findsOneWidget);
      expect(find.text('Músicas'), findsOneWidget);
      expect(find.text('Usuário'), findsOneWidget);
    });

    testWidgets('Devocional button navigates to DevocionalPage', (WidgetTester tester) async {
      await tester.pumpWidget(
        const MaterialApp(home: HomeScreen()),
      );

      await tester.tap(find.text('Devocionais'));
      await tester.pumpAndSettle();

      expect(find.text('Devocional'), findsWidgets);
    });
  });
}
