# План сборки iOS-версии ExteraGram

## Текущее состояние (этап 5, PR-1+)

Модуль `ios/ExteraGram/` компилируется автономно (проверка через CI:
`.github/workflows/ios-build.yml` — `swiftc -typecheck` на macOS-раннере, бесплатно
для публичного репозитория):

- `ExteraConfig.swift` — порт всех 143 настроек мода
- `ExteraLocaleController.swift` + `Resources/*.lproj/Extera.strings` — 34 локали
- `ExteraIconManager.swift` — маппинг icon packs (595 remix + 594 solar)

Ассеты: `assets/Icons.xcassets` (1330 imageset), `assets/VectorIcons/*.svg` (1516),
`assets/AppIcons.xcassets` (90 launcher-иконок).

## Полный билд приложения

Полная сборка telegram-ios требует:

1. **Xcode** с включённой поддержкой iOS SDK (публичные GitHub macOS-раннеры имеют Xcode).
2. **Подпись кода**: требуется Apple Developer аккаунт ($99/год). telegram-ios собирается
   с `DEVELOPMENT_TEAM`; для CI без секретов — `CODE_SIGNING_ALLOWED=NO` (simulator).
3. **Зависимости**: Bazel (telegram-ios использует BUILD.bazel / bazel-xcode-config).
   Для автономной проверки без Bazel наш модуль ExteraGram собирается отдельно (текущий CI).

### Шаги для полного билда (когда появятся секреты/локальный Mac)

1. `git submodule update --init ios/telegram-ios`
2. Применить патчи: `git apply ios/patches/*.patch` (патчи стоковых файлов — см. migration-map).
3. Подключить модуль ExteraGram: добавить в `Telegram-iOS.xcodeproj` (telegram-ios
   использует Bazel: добавить `ios/ExteraGram/BUILD.bazel` с deps на SwiftObjC… —
   см. `submodules/*/BUILD` паттерн).
4. Скопировать ассеты: `assets/Icons.xcassets` → `ios/ExteraGram/Resources/Icons.xcassets`.
5. Сборка: `bazel build //Telegram:Telegram-iOS` или Xcode: open `Telegram-iOS.xcodeproj`,
   scheme Telegram, team = ваш.

### CI без подписи

`CODE_SIGNING_ALLOWED=NO CODE_SIGNING_REQUIRED=NO` + destination simulator —
компиляция изменённых модулей возможна на CI (реализовано для нашего модуля;
расширение на полный билд — при наличии раннера с достаточным диском (~40GB)).

## Ограничения платформы (задокументировано честно)

- **Python-плагины (Chaquopy)**: на iOS невозможно — правила App Store запрещают
  JIT/download кода. Эквивалент: JavaScriptCore-движок (см. migration-map F7).
- **Смена иконки приложения**: iOS разрешает только через `setAlternateIconName`
  (все 33 альтернативные иконки включены; мгновенная смены как на Android
  технически невозможна на iOS — применяется системный API).
- **APK-updater**: App Store обновляет приложения сам; updater мода показывает
  баннер новой версии со ссылкой (TestFlight/сайт).
- **Google Crashlytics/Analytics**: в iOS-сборке нет Google-телеметрии; флаги
  перенесены в конфиг для сохранения настроек/экспорта.
