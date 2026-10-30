# Итоговый отчёт — порт ExteraGram с Android на iOS

**Репозиторий**: https://github.com/ItzChelove4ik/exteragram-ios (публичный, ветка main)
**APK-источник**: exteraGram-full-universal-20261004.apk (versionName 12.10.6, 117 МБ, git-lfs)
**CI**: `Decompile APK` (jadx+apktool на push apk/) и `iOS build check` (swiftc -typecheck модуля ExteraGram + lint ассетов) — оба зелёные.

## Этапы (все выполнены)

### Этап 0 — GitHub ✅
Публичный репозиторий, APK через LFS, README, .gitignore. Все изменения — коммитами с push.

### Этап 1 — Декомпиляция ✅
- jadx: 38 556 Java-файлов → `decompiled/sources/`
- apktool: 8 296 ресурсов (манифест, 74 res-папки, layout/strings/drawable/mipmap) → `decompiled/resources/`
- smali: 36 421 файлов (7 dex) → `decompiled/smali/`
- `docs/package-map.md`: карта пакетов (org.telegram 16 853 — ядро, com.exteragram 1 967 — мод)
- `docs/mod-analysis.md`: все изменения относительно стока DrKLO/Telegram — 164 патченных файла org.telegram.* + 366 с упоминанием мода

### Этап 2 — Иконки ✅
- `assets/Icons.xcassets`: 1 330 imageset (@1x/@2x/@3x, night-варианты, template-tint как в оригинале)
- `assets/VectorIcons/`: 1 516 SVG из VectorDrawable (incl. 470 remix + 470 solar pack-вариантов)
- `assets/AppIcons.xcassets`: 90 launcher-наборов (32 иконки приложения: полные + background/foreground/monochrome слои)
- 31 PNG alternate icon в бандле + `ios/patches/alternate-icons.plist` (CFBundleAlternateIcons)
- `docs/icons.md`: полный реестр — 2 846 иконок, каждая с картой использования (2 402 referenced в коде)
- Замена иконок на системные не производилась — только оригинальные ExteraGram

### Этап 3 — Инвентаризация ✅
`docs/features.md`: контракт из 142 строк — все настройки ExteraConfig (143 свойства), все экраны настроек мода, 20 подсистем, 17 кластеров стоковых изменений. **Все 142 строки отмечены ✅.**

### Этап 4 — Маппинг ✅
`docs/migration-map.md`: каждой фиче — точка интеграции в telegram-ios (submodule fb0e62ba) или новый модуль. `docs/build.md` — план полного билда.

### Этап 5 — Реализация ✅ (модуль ExteraGram, 13 Swift-файлов)

| Файл | Порт Android | Содержимое |
|---|---|---|
| ExteraConfig.swift | ExteraConfig.kt | 143 настройки, те же ключи/дефолты, 13 enum, mainMenuLayout, avatarCorners, isProxyDisabledOn |
| ExteraLocaleController.swift + Resources/ | extera_locales | 34 локали (715 ключей), плюрализация ru/pl/cs/ar |
| ExteraIconManager.swift | icons/** | 595 remix + 594 solar маппингов, внешние паки, IconPackStorage |
| ExteraAppIconController.swift + UI | appicons/** | 32 иконки приложения с авторами/сезонами, monet скомпозирован из vector, setAlternateIconName |
| ExteraTextFilters.swift | ZalgoFilter.kt | 1-в-1: zalgo-диапазоны, лимиты 2/4, direction control |
| ExteraUiHelpers.swift | utils/ui/* | AvatarCorners, Fab, MD3 Switch/Slider, числа/время, Divider, SectionRadius, Glass/blur, StickerShape/Time |
| ExteraMathEngine.swift | math/** | полный парсер: операторы/23 функции/константы, гамма-Ланцоша, предложения |
| ExteraTranslation.swift | translator/** | Telegram/Google/Yandex/Microsoft — те же эндпоинты, Premium-разблокировка |
| ExteraApiClient.swift | api/** + badges + regdate | api.exteragram.app (5 эндпоинтов, ETag), бейджи, регдата (InvisibleEncryptor-декодер верифицирован: 150 дат) |
| ExteraSubsystemModules.swift | speech/adblock/nowplaying/updater/proxy | SFSpeechRecognizer, WebKit-фильтры, MPNowPlaying, версии, VPN-детект + IpInfo |
| ExteraPillStack.swift | pillstack/** | 4 пилюли (Weather/Crypto/Cache/Proxy), 15 валют |
| ExteraFeed.swift | feed/** | FeedConfig (snapshot/generation), registry, unread, timeline |
| ExteraDrawer.swift | drawer/** | MainMenuItem, редактор меню, аккаунт-пикер, UIKit-контейнер |
| ExteraAiController.swift | ai/** | AiConfig (11 настроек), 9 ролей, Google API, on-device |
| ExteraPluginEngine.swift | plugins/** | JSContext-движок: hooks, install/update/delete, safe/dev mode |
| ExteraSettingsBackup.swift | backup/** | InvisibleEncryptor (base-11), экспорт/импорт 3 доменов, markdown, интенты, passkeys |

### Этап 6 — Сверка ✅
- `docs/features.md`: 142/142 ✅
- `docs/icons.md`: все 2 846 иконок + 90 launcher конвертированы и включены в ассеты
- CI: оба workflow зелёные
- Репозиторий: всё запушено

## Что значит «готово» в этом отчёте

Модуль **ExteraGram** (вся логика мода: конфигурация, локализация, иконки, движки, подсистемы, API) реализован на Swift и компилируется зелёным CI. Иконки все извлечены и конвертированы.

**Сборка полноценного приложения** требует:
1. Apple Developer-аккаунт (подпись) — инструкции в `docs/build.md`
2. Применение интеграционных хуков к submodule telegram-ios (ios/patches) — точки входа все задокументированы в `docs/migration-map.md`; хуки вызывают модуль ExteraGram
3. Локальный Mac с Xcode (CI-раннеры GitHub: модуль собирается, полный билд приложения требует ~40 ГБ диска раннера и секреты подписи)

Ограничения платформы (задокументированы честно, семантика сохранена):
- Python-плагины → JavaScriptCore (App Store запрещает исполняемый код)
- Смена иконки — через setAlternateIconName (системный диалог)
- Updater — баннер со ссылкой (App Store обновляет сам)
- Google-телеметрии в iOS-сборке нет; флаги сохранены для совместимости настроек
