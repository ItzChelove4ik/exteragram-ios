# Маппинг порта: фича ExteraGram → точка интеграции в telegram-ios → план изменений

Сгенерировано: 2026-10-25. Submodule: `ios/telegram-ios` @ master fb0e62ba8a.
Правило: фич без маппинга нет — если аналога в iOS-коде нет, фича реализуется с нуля
в новом Swift-модуле `ExteraGram` (наш код живёт в `ios/ExteraGram/`, подключается
кабель-модулем через Bazel-депенденси к Telegram-iOS target).

## Архитектура порта

- **Настройки мода**: новый Swift-файл `ios/ExteraGram/ExteraConfig.swift` — порт
  `ExteraConfig.kt` 1-в-1: все 140+ ключей, те же дефолты, тот же ключ SharedPreferences→UserDefaults
  (`exteraconfig`), Codable-схема по паттерну `TranslationSettings.swift` (TelegramUIPreferences).
  Enum-типы: `IconPackType`, `TabIconsMode`, `TabCounterMode`, `DividerStyle`, `TransitionAnimation`,
  `GlassOutlineStyle`, `StickerTimeMode`, `AvatarCornerType`, `CameraType`, `VideoMessagesCamera`,
  `MainMenuItem`, `ProxyDisableCondition` — все как Swift-enum с rawValue, идентичные значения.
- **Иконки**: `assets/Icons.xcassets`, `assets/VectorIcons/*.svg`, `assets/AppIcons.xcassets`
  копируются в `ios/ExteraGram/Resources/`. SVG → рендер через SVGView (стоковый пакет
  `submodules/Svg` отсутствует — используем `DisplayMiniVideoNode`-независимый рендер
  CoreGraphics-парсером; конвертация в PDF на CI).
- **Экраны настроек мода**: `ios/ExteraGram/UI/ExteraSettingsController.swift` и подпапки
  General/Appearance/Chats/Other/Plugins — порт `preferences/**` на TelegramPresentationInterface
  (ASDK-контроллеры, паттерн `SettingsUI`).
- **UI-хуки в стоковом коде**: минимальные патчи файлов telegram-ios через
  `ios/patches/*.patch` (quilt-стайл, применяются CI; репо submodule не форкается напрямую —
  патчи версионируются в нашем репо, что даёт обновляемость базы).

## A. Экран настроек мода

| Фича | Точка в telegram-ios | План |
|---|---|---|
| A1 Пункт ExteraGram в настройках | `submodules/SettingsUI/Sources/SettingsController.swift` (секция root rows) | Patch: добавить row с иконкой `extera_outline`, открытие `ExteraSettingsController` |
| A2 Каркас настроек мода | — (нет аналога) | Новый модуль: `ExteraSettingsController` + `ExteraSectionRow` по паттерну `SettingsUI` + UniversalTextRow |
| A3 Deeplink на настройки | `Telegram-iOS/AppRootController` deep-link router | Patch: роут `tg://extera_settings?item=` |
| A4 Сброс настроек | `ExteraConfig.swift` | reset() + confirm-alert (порт OtherPreferencesActivity) |
| A5 Экспорт/импорт настроек | — | Новый: `ExteraSettingsBackup.swift` (JSON, порт PreferencesUtils + InvisibleEncryptor → CryptoKit AES-GCM) |
| A6 Экспорт данных | `submodules/.../export` (iOS имеет свой export в TelegramCore: `TelegramAccountDataExporter`) | Расширить до HTML/JSON форматов мода: `ExteraExportController.swift`, шаблоны из assets/extera/export |

## B. General

| Фича | Точка в telegram-ios | План |
|---|---|---|
| B1 hidePhoneNumber | `submodules/SettingsUI/Sources/privacy settings` + drawer `DrawerHeaderNode` (ChatListUI) | Patch: условие отображения |
| B2 showIdAndDc | `submodules/SettingsUI/Sources/PeerInfoScreen` + `ChatControllerTitlePanel` | Patch: подпись ID/DC под именем (данные из `EnginePeer.id`, cachedDC из AccountState) |
| B3 hideArchiveFolder | `submodules/ChatListUI/Sources/...ChatListTabItem.swift` / filterTabs | Patch: исключить archive tab |
| B4 archiveOnPull | `ChatListContainerNode` pull-down жест | Patch: свайп вниз → toggle archive |
| B5 disableUnarchiveSwipe | `ChatListSwipeNode` | Patch: флаг ExteraConfig |
| B6 proxy conditions + IP info | `submodules/ProxySettings`, `AccountNetworkSettings` | Patch: ProxyDisableCondition в `NetworkSettings` chooseProxy + `ExteraIpInfoController` (порт IpAddressInfoController, внешний API мода) |
| B7 customSavePath | iOS sandbox — нет прямого доступа к произвольным путям; Files.app папка | Новый: `ExteraSavePathController` — Documents/Inbox папка через UIDocumentPicker + закладка (security-scoped bookmark). Поведение адаптировано к iOS-ограничениям, функциональность та же: свои подпапки для загрузок |
| B8 doNotMarkAsNew | `ChatListUnreadBadge` / readState | Patch: suppress unread-подсветки для списка |
| B9 disableNumberRounding | `submodules/DisplayUtils` + `formatShortNumbers` | Patch: инт-форматирование |
| B10 formatTimeWithSeconds | `submodules/DateUtils` | Patch: форматтеры с секундами |
| B11 relativeLastSeen | `submodules/...UserPresence` strings | Patch: относительное время |
| B12 inAppVibration | `DeviceProximity/SoundPlayback` — haptic слой | Patch: обёртка `ExteraHaptics` |
| B13 disableNotificationDelay | `NotificationService`/`NotificationsController` | Patch: in-app задержка |
| B14 filterZalgo | `postbox` text processing | Patch в `ChatMessageBubbleContentText` sanitize |
| B15/B16 speed boosts | `Network.multiplex` `TelegramCore/Sources/Network` | Patch: количество коннектов/потоков загрузки |
| B17-B19 translator | `submodules/TelegramCore/Sources/...Translation` + `TranslationSettings` | Новый: `ExteraTranslationProviders.swift` (DeepL, Google, Libre, Lingva; порт TranslationProviders.kt), patch TranslationController на выбор провайдера |
| B20 accountNotifications | `submodules/AccountContext/AccountsList` + notification routing | Patch: per-account noise/бейджи |
| B21 new-features toast | `ExteraWhatsNew.swift` (порт newFeaturesShowedAt) | Новый экран What's New при апдейте |
| B22 язык мода | `submodules/TelegramUIStrings` | `ExteraLocaleController.swift`: 34 локали из assets/extera_locales (формат Android strings.xml → plist на CI-конвертере) |

## C. Appearance

| Фича | Точка | План |
|---|---|---|
| C1 avatarCorners | `AvatarNode` (submodules/AvatarUniversalNode…/ `AvatarNode.swift`) | Patch: cornerRadius = f(config, forum/community) — порт getAvatarCorners |
| C2 singleCornerRadius | там же | Patch |
| C3 forceSnow | `submodules/.../SnowflakesNode`? сток имеет holidays snow | Patch: включение вне сезона |
| C4 hideActionBarStatus | `ChatController/navigationBar` subtitle | Patch |
| C5 centerTitle | `Navigationabar` title layout | Patch: центрирование |
| C6 hideStories | `ChatListStoriesNode` + PeerInfo stories | Patch: скрытие |
| C7 hideFloatingButton | `ChatListFloatingButtonNode` | Patch |
| C8 hideDialogsSearchBar | `ChatListSearchBarNode` | Patch |
| C9 senderMiniAvatars | `ChatMessageAuthorNode`/avatar in bubble | Patch: мини-аватар у имени |
| C10 titleText (name/username) | `ChatController.titleView` | Patch |
| C11 Pill Stack | — нет аналога | Новый модуль `ExteraPillStack`: узел над ChatList (ASDK ASStackNode), пилюли: Weather (WeatherAPI мода), Crypto (BtcPill/UsdPill/GramPill/RatePill — API мода), Cache (usage), Proxy (ping). Все 4 порт + infinite scrolling + hidden/active списки |
| C12 tabIcons | `ChatListTabItem` (ChatListUI) | Patch: TabIconsMode enum |
| C13 tabCounterMode | `ChatListTabItem.badge` | Patch: unmuted/all |
| C14 hideAllChats | filterTabs | Patch |
| C15 app navigation | `ChatListController`+`AppRootController` | Patch: navigationDrawer (порт `drawer/**` — ExteraDrawerController: аккаунты, меню, прокси-пинг), bottom bar (BottomNavigationBar enum), transitionAnimation, predictiveBackIntensity (iOS: interactivePopGestureRecognizer intensity), immersiveDrawerAnimation, tabletMode |
| C16 app icons (33) | `Telegram-iOS Info.plist` alternate icons | Patch: CFBundleAlternateIcons 33 шт. из assets/AppIcons.xcassets + `ExteraAppIconController` (setAlternateIconName), порт appicons/** |
| C17 icon packs | — нет аналога | Новый: `ExteraIconManager.swift` (порт IconManager: BaseIconPacks map 470+470, IconPackProvider для внешних паков, storage, редактор). Использует assets/VectorIcons |
| C18 squareFab/fab style | `ChatListFloatingButtonNode` | Patch |
| C19 sectionRadius | все section-контейнеры (UniversalNode inset) | Patch: глобальный радиус |
| C20 sectionsSeparatedHeaders | `SettingsUI` headers | Patch |
| C21 dividerStyle | `UITableViewCell` separators/ASDK | Patch: DividerStyle enum |
| C22 MD3 styles | `SwitchNode`, `SliderNode`, loading | Patch: стили по new*Style флагам (порт MaterialSliderUiHelper, SwitchUiHelper) |
| C23 useSystemFonts | сток всегда системный шрифт на iOS; мод добавляет кастомные | `ExteraFontController` (порт FontUtils: подключение .ttf из Documents, если off — системный) |
| C24 systemEmoji | сток iOS уже системные эмодзи; мод на Android подменяет шрифтом | Флаг переносится как no-op с сохранением семантики (порт значения в конфиг для совместимости бэкапов) |
| C25 gooeyAvatarAnimation | AvatarNode morph | Patch: spring-анимация (порт gooey) |
| C26 customThemes (.attheme) | `PresentationTheme` + ThemeSettings | Новый: `ExteraThemeImporter.swift` — парсер .attheme (формат Telegram Android) → маппинг на PresentationTheme акцент/фон; attheme-файлы из assets |
| C27-C29 glass/blur | CALayer blur (UIVisualEffectView кастом) | Patch: GlassOutlineStyle в баблах/меню, glassMessageMenu в context menu (порт GlassMenuHelper, blur3) |
| C30 stickerShape | `ChatMessageStickerNode` corners | Patch |

## D. Chats

| Фича | Точка | План |
|---|---|---|
| D1 doubleTapAction | `ChatHistoryListNode` tap recognizer | Patch: DoubleTapUtils порт (reply/like/edit/translate, out-owner вариант) |
| D2 swipeActions | `ChatHistoryListItemNode` swipe (сток имеет swipe-to-reply) | Patch: ExteraSwipeController (порт SwipeActionsHelper: выбор действия на левый/правый, loop, reversed; UI как в моде) |
| D3 bottomButton | `ChatTextInputPanelNode` | Patch |
| D4 widePosts | `ChatMessageInteractiveMediaNode` width policy | Patch для каналов/feed |
| D5 telegramAi | `ChatController` AI-функции (сток: chatbot integration) | Patch: AiController — AI editor/summaries через бота мода (порт ai/**: AiConfig, AiController, TelegramAiReplacement) |
| D6 quickAdminShortcuts | `ChatController.contextMenu` | Patch: админ-кнопки в контекстном меню |
| D7 quickTransitions | `ChatController` push-анимации | Patch: без анимации для каналов/топиков |
| D8 stickerSize | `ChatMessageStickerNode` size | Patch (порт StickerTime/размер) |
| D9 stickerTimeMode | `ChatMessageStickerNode` timestamp | Patch |
| D10 reply elements | `ChatMessageReplyInfoNode` | Patch: BlendedReplyColorSpan/Emoji/Background (порт BlendedReply*) |
| D11 unlimitedRecentStickers | `MediaInputPanel` recents | Patch |
| D12 hideReactions | `ChatMessageContextMenuNode`+`ReactionNode` | Patch по типу чата |
| D13 hideKeyboardOnScroll | `ChatControllerNode` scroll | Patch |
| D14 addCommaAfterMention | `ChatTextInputNode` autocomplete | Patch |
| D15 inlineMath | `ChatTextInputPanelNode` | Новый: `ExteraMathEngine.swift` (полный порт math/**: MathExpression, операторы/функции, GhostTextLayout → text preview, MathRevealAnimation) |
| D16 disableMarkdown | `ChatTextInputNode.attributedText` markdown parse | Patch: MarkdownUtils |
| D17 hideSendAsPeer | input panel | Patch |
| D18 removeMessageTail | `ChatMessageBackground` bubble tail | Patch |
| D19 replaceEditedWithIcon | edited label | Patch |
| D20 showOnlineStatus | presence в подписи чата | Patch |
| D21 showForwardsCount | forward header | Patch |
| D22 hideShareButton | message share action | Patch |
| D23 showResultsBeforeVoting | poll rendering | Patch |
| D24 message menu buttons | `ChatController.contextMenu` | Patch: все 8 переключаемых кнопок + группировка (порт настроек меню) |
| D25 CopyPhoto/Frame | `ChatController.contextMenu` + PhotoViewer | Patch: копирование изображения/кадра в pasteboard |
| D26 Repeat | context menu | Patch: пересылка самому себе |
| D27 MessageHistory | сток iOS имеет history? (Android-мод включает) | Patch: если в iOS нет — `ExteraMessageHistoryController` через getMessagesByRef |
| D28 details popup | `ChatController.contextMenu` | Новый: `ExteraMessageDetailsController` (порт MessageDetailsPopupWrapper: SentFrom/Resolution/Bitrate/DC/Path…) |
| D29 speech recognition | — нет аналога | Новый: `ExteraSpeechRecognizer.swift` — on-device SFSpeechRecognizer (эквивалент Vosk по функции: офлайн-транскрипция голосовых, выбор языка, скачивание «модели» = проверка доступности локали) |
| D30 postprocessingWithAi | photo editor export | Patch: AI-обработка через бота мода |
| D31 camera settings | iOS камера = AVFoundation; Camera2/CameraX не существует | `ExteraCameraSettings.swift` (порт всех 13 настроек: FPS-расширение через activeVideoMinFrameDuration, стабилизация через preferredVideoStabilizationMode, mirror, videoMessagesCamera, rememberLastUsed, wide-angle (ultraWide), zoomSlider (ExteraCameraZoomSlider — порт CameraZoomSliderView), staticZoom, HD, hideTile, doubleTapSeek, originalQuality, CameraType) — cameraType (CAMERA_X/CAMERA2) на iOS становится выбором AVCaptureSession preset (семантика сохранена) |
| D32 pauseOnMinimize* | `PiP`/background audio session | Patch: AVAudioSession interruption/ниже background audio |
| D33 unmuteWithVolumeButtons | volume buttons observer | Patch |
| D34 swipeToPip | `ChatHistoryVideoNode` swipe | Patch |
| D35 reverse image search | `ChatController.contextMenu` | Patch: `ExteraReverseImageSearch` (порт ReverseImageSearchSheet: Yandex/Google/Lens) |
| D36 QR login/sheet | сток iOS имеет QR-login | Patch: QRCodeSheet-обёртка мода |
| D37 subtitles | `ChatMessageVideoNode` | Новый: `ExteraSubtitlesController` (порт VideoSubtitlesHelper: .srt/.vtt загрузка, ChooseSubtitles UI) |
| D38 video without compression | `MediaBox` send | Patch: original quality путь |
| D39 translate before send | `ChatTextInputPanelNode` | Patch: TranslateBeforeSendWrapper |
| D40 disableGreetingSticker | `GreetingStickerNode` | Patch |

## E. Other

| Фича | Точка | План |
|---|---|---|
| E1/E2 crashlytics/analytics | сток iOS не содержит Google Crashlytics/Analytics | Флаги переносим в конфиг (no-op телеметрии, сохраняя переключатели и их экспорт/импорт; телеметрии в iOS-сборке нет — функция = выключение несуществующей отправки, документировано) |
| E3 adblock | `WebKit` в web-view (сток: SafariView/in-app) | Новый: `ExteraAdBlock.swift` (порт adblock/**: WKContentRuleStore-фильтры, счётчик BlockedRequests, UI настроек фильтров) — эквивалент AdBlockClient по функции |
| E4 donate/boosty | — | `ExteraDonateController` (порт BoostyBottomSheet/SupporterBottomSheet) |
| E5 delete account | SettingsUI (сток имеет) | Patch: пункт в меню мода |
| E6 testBackend | `TelegramCore/AppLifeCycle` build config | Patch: тестовый backend по флагу (порт TestBackend) |
| E7 autolock instant | `AppLock` submodule | Patch |

## F. Подсистемы

| Фича | Точка | План |
|---|---|---|
| F1 Feed | — нет аналога | Новый крупный модуль `ExteraFeed`: FeedController (порт feed/**: registry каналов, backfill, store), вкладка в ChatList tabs, widePosts в фиде, unread badge. Реализация поверх `postbox` view-состояний |
| F2 Drawer | `ChatListController` (сток имеет drawer в iPad) | Patch/новый: `ExteraDrawerController` полный порт drawer/**: аккаунты, unread, меню, header |
| F3 main menu editor | `ChatListMenu` (сток burger menu) | Patch: `ExteraMainMenuHelper` — редактор порядка/видимости пунктов (порт MainMenuItem) |
| F4 translator core | TranslationSettings/TranslationController | Patch + новый `ExteraTranslationProviders` |
| F5 regdate | — | `ExteraRegDate.swift`: registration_dates.bin из assets (парсер бинарного формата мода) + отображение в профиле |
| F6 now playing | iOS: MPNowPlayingInfoCenter / scrobbling | `ExteraNowPlayingController` (порт NowPlayingController: статус в профиле, ServiceEmoji выбор, scrobbling через Last.fm API мода) |
| F7 plugins | Chaquopy Python — на iOS невозможен JI/Python в App Store (правила Apple) | Эквивалент: `ExteraPluginEngine` на JavaScriptCore (JS-API порт PluginsController: hooks, модели, dev-mode, safe-mode, trusted sources, pinned, install/update/delete, песочница JSContext). Полный порт UI плагинов (plugins/ui). Python-функциональность (pip) переносится как JS-модули + нативные API-мосты; ограничение платформы задокументировано в docs/port-notes.md — все хуки/события/UI идентичны |
| F8 updater | — App Store обновляет приложения | `ExteraUpdater`: проверка версии через API мода (порт UpdaterUtils/UpdateLayout/UpdateAppAlertDialog — показывает баннер «доступна новая версия» со ссылкой на TestFlight/сайт, т.к. прямая установка .apk/.ipa невозможна на iOS — задокументировано) |
| F9 extera API | — | `ExteraApiClient.swift` (порт api/**: OkHttp → URLSession, DTO, ApiController, бейджи/обновления/конфиг) |
| F10 badges | — | `ExteraBadgesController` (порт badges/**: бейджи у ника из кэша remote set) |
| F11 pillstack core | — | см. C11 |
| F12 weather | Stories Weather (сток имеет weather в stories) + `ExteraWeatherPill` | Patch/новый: API погоды мода, WeatherSettingsActivity порт |
| F13 math engine | — | см. D15 |
| F14 passkeys | iOS имеет нативные passkeys (ASAuthorization) | Patch: `ExteraPasskeysUtil` интеграция в логин |
| F15 extera intents | `AppRouter` | Patch: обработка tg://-стайл ссылок мода |
| F16 локализация мода | — | `ExteraLocaleController` + CI-конвертер strings.xml→plist (34 локали, 715 ключей) |
| F17 splash/брендинг | LaunchScreen storyboard | Patch: etg_splash интро (порт intro-слайдов) |
| F18 Boosty | — | см. E4 |
| F19 MainTabsActivity | `ChatListTabs` (сток) | Patch: bottom navigation режимы |
| F20 folder counters/cache pill | ChatListTabItem badge | Patch + C11 |

## G. Стоковые кластеры (все 164 файла из docs/mod-analysis.md §3)

Каждый файл-модификация Android сопоставлен iOS-файлу; полный перечень в
`docs/stock-files-map.md` (генерируется CI). Основные:

| Кластер Android | iOS-цель |
|---|---|
| ChatActivity | ChatController.swift + ChatControllerNode.swift + ChatHistoryListNode.swift |
| ChatMessageCell | ChatMessageBubbleContent* / ChatMessageItemNode |
| DialogsActivity/DialogCell | ChatListController / ChatListItemNode |
| ActionBar/Theme | NavigationBar / PresentationTheme |
| ProfileActivity | PeerInfoScreen (PeerInfoNode.swift) |
| Stories | Stories (StoryContainerNode) |
| PhotoViewer | GalleryController (сток iOS) — патчи по фичам |
| ChatActivityEnterView | ChatTextInputPanelNode |
| MessagesController/MediaDataController/SendMessagesHelper | TelegramCore (преимущественно без патчей — Android-мод патчит UI-слои) |
| web/SearchEngine | WebSearchSettings / InAppBrowser |
| FileLoader boosts | Network.multiplex |
| blur3/glass | нет прямого аналога | ExteraGlass (CVPixelBlur/CALayer) |
| RecyclerListView/Universal* | ASDKListNode эквиваленты |

## Последовательность PR (этап 5)

1. PR-1: ExteraConfig + каркас настроек (A1-A3, B-подмножество флагов-значений) + локализация мода
2. PR-2: иконки — IconManager + packs + ассеты в билд (C17, C16)
3. PR-3: appearance-патчи (C-раздел)
4. PR-4: chats-патчи (D-раздел)
5. PR-5: translator + speech + math
6. PR-6: feed + drawer + pillstack
7. PR-7: plugins engine + ai + adblock + badges + api
8. PR-8: экспорт, бэкап настроек, updater, donate, камера
9. PR-9: G-кластеры, завершение контракта
10. PR-10: финальный аудит, документация билда
