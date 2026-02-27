import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:versovivo_app/main.dart';

void main() {
  group('App Integration Tests', () {
    testWidgets('App starts and shows HomeScreen', (WidgetTester tester) async {
      await tester.pumpWidget(const VersoVivoApp());
      await tester.pumpAndSettle();

      expect(find.text('Verso Vivo'), findsWidgets);
      expect(find.byType(ElevatedButton), findsWidgets);
    });

    testWidgets('Full navigation flow', (WidgetTester tester) async {
      await tester.pumpWidget(const VersoVivoApp());
      await tester.pumpAndSettle();

      // Navigate to Devocional
      await tester.tap(find.text('Devocionais'));
      await tester.pumpAndSettle();
      expect(find.text('Devocional'), findsWidgets);

      // Go back
      await tester.pageBack();
      await tester.pumpAndSettle();

      // Navigate to Música
      await tester.tap(find.text('Músicas'));
      await tester.pumpAndSettle();
      expect(find.text('Música'), findsWidgets);
    });
  });
}
