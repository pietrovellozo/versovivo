import 'package:flutter_test/flutter_test.dart';
import 'package:versovivo_app/core/constants/app_constants.dart';

void main() {
  group('AppConstants', () {
    test('appName should be Verso Vivo', () {
      expect(AppConstants.appName, 'Verso Vivo');
    });
  });
}
