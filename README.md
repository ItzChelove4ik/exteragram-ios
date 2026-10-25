# ExteraGram iOS — полный 1-в-1 порт ExteraGram с Android на iOS

Полный 1-в-1 порт ExteraGram (модифицированного Android-клиента Telegram) на iOS.
**Все** функции мода и **все** его иконки переносятся полностью, без исключений,
пропусков и упрощений.

## Структура репозитория

| Путь | Назначение |
|---|---|
| `apk/` | Исходный APK ExteraGram (через git-lfs) |
| `decompiled/sources/` | Java/Kotlin исходники (jadx) |
| `decompiled/resources/` | Ресурсы APK: манифест, layout, strings, drawable, mipmap (apktool) |
| `decompiled/smali/` | Smali-код для участков, которые jadx не восстановил |
| `tools/` | Инструменты и скрипты (jadx, apktool, конвертеры иконок) |
| `assets/` | Готовые iOS-ассеты (преобразованные иконки ExteraGram) |
| `ios/` | Код порта поверх telegram-ios (submodule + наши модули) |
| `docs/` | Контракт порта: `features.md`, `icons.md`, `migration-map.md`, отчёты |

## Этапы

0. **GitHub** — репозиторий, push, CI.
1. **Декомпиляция APK** — jadx + apktool + smali, карта пакетов, полный diff
   относительно стокового Telegram (DrKLO/Telegram).
2. **Иконки** — извлечение всех drawable/mipmap, VectorDrawable → SVG → PDF
   для iOS, реестр `docs/icons.md`.
3. **Инвентаризация фич** — исчерпывающий список всех функций мода
   (`docs/features.md`) — контракт порта.
4. **Маппинг** — каждой фиче — точка интеграции в telegram-ios
   (`docs/migration-map.md`).
5. **Реализация** — перенос фич по очередности, малые проверяемые PR.
6. **Сверка** — аудит чеклистов, все фичи и иконки отмечены.

## CI

GitHub Actions: декомпиляция APK, конвертация иконок, компиляция изменённых
модулей telegram-ios на бесплатных macOS-раннерах (публичный репозиторий).

## База

- Мод: ExteraGram (APK в `apk/`)
- Стоковый Android-клиент для сравнения: https://github.com/DrKLO/Telegram
- iOS-база: https://github.com/telegrammessenger/telegram-ios
