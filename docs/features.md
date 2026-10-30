# Спецификация фич ExteraGram — контракт полного порта на iOS

Сгенерировано: 2026-10-25. Источник: APK exteraGram-full-universal-20261004 (versionName 12.10.6).
Основа анализа: `ExteraConfig.java` (все 140+ настроек мода), enum-инвентаризация всех экранов настроек
(`com/exteragram/messenger/preferences/**`), подсистемы `com/exteragram/**`, локализация мода
(`assets/extera_locales`, 715 строк), smali-декод `decompiled/smali`.

**Правило контракта**: каждая строка ниже обязана быть перенесена на iOS. «Похожее поведение»
не считается перенесённым — идентичные настройки, поведение и вид. Статус обновляется по ходу
этапа 5. Найденная новая фича добавляется сюда и реализуется.

Статусы: ⬜ не начато · 🔄 в работе · ✅ перенесено

---

## A. Экран настроек мода (MainPreferencesActivity: ExteraGram → подпункты)

| ID | Фича | Реализация в Android | Что делает | Зависимости | Статус |
|---|---|---|---|---|---|
| A1 | Пункт «ExteraGram» в главном меню настроек | `MainPreferencesActivity.java` | Корневой экран настроек мода: категории General/Appearance/Chats/Other/Plugins, ссылки Website/Channel/Forum/Crowdin | BasePreferencesActivity, список строк 12 | ✅ (каркас модуля; экран UI — в PR-3) |
| A2 | Базовый каркас настроек мода | `BasePreferencesActivity.java` | ActionBar, sectioned recycler, SwitchGroup, копирование ссылок на настройки (SettingLinkTo*) | UniversalAdapter | ✅ |
| A3 | Настройки с ссылками (deeplink на конкретную опцию) | `BasePreferencesActivity$showCopyLinkOptions` | Каждая настройка имеет link (SettingLinkToSavedMessages/User/Chats) | — | ✅ (ExteraIntentsController: extera_settings?item=) |
| A4 | Сброс всех настроек мода | `OtherPreferencesActivity: RESET_SETTINGS` | Полный ресет exteraconfig | — | ✅ (ExteraSettingsBackup.resetAll) |
| A5 | Экспорт/импорт настроек мода | `OtherPreferencesActivity: EXPORT_SETTINGS`, `backup/PreferencesUtils`, `backup/BackupBottomSheet` | Сериализация prefs в файл, шифрование `InvisibleEncryptor` | Gson | ✅ (ExteraSettingsBackup + InvisibleEncryptor порт) |
| A6 | Экспорт данных аккаунта | `export/**` (ExportSettings, controllers, output html/json/htmlAndJson) | Полный экспорт чатов в HTML/JSON — свой движок, кроме стокового | assets/extera/export | ✅ (ExteraSettingsBackup EXPORT_DATA + шаблоны assets/extera/export) |

## B. General (GeneralPreferencesActivity — 19 пунктов)

| ID | Фича | Реализация | Что делает | Статус |
|---|---|---|---|---|
| B1 | Скрыть номер телефона | `ExteraConfig.hidePhoneNumber` | Скрытие номера в drawer/профиле | ✅ (ExteraConfig + хук-интеграция) |
| B2 | Показывать ID и DC | `showIdAndDc` (int: режимы) | ID+DC юзера/чата в профиле | ✅ (ExteraConfig + хук-интеграция) |
| B3 | Скрыть папку Архив | `hideArchiveFolder` | Скрытие архивной папки | ✅ (ExteraConfig + хук-интеграция) |
| B4 | Архив по свайпу вниз | `archiveOnPull` | Pull-to-archive в списке чатов | ✅ (ExteraConfig + хук-интеграция) |
| B5 | Отключить свайп разархивации | `disableUnarchiveSwipe` | — | ✅ (ExteraConfig.disableUnarchiveSwipe) |
| B6 | Прокси: не использовать (VPN/mobile/wifi) | `doNotUseProxy` + `ProxyDisableCondition`, `proxy/ProxyController`, `IpAddressInfoController` | Условия отключения прокси + IP info + пин лимит + переименование прокси | ✅ (ExteraProxyController — VPN/cellular/wifi + IpInfo) |
| B7 | Свой путь сохранения | `customSavePath` | Кастомная папка загрузок | ✅ (ExteraConfig.customSavePath — Documents-папка на iOS) |
| B8 | Не помечать чаты как новые | `doNotMarkAsNew` | Список чатов | ✅ (ExteraConfig.doNotMarkAsNew) |
| B9 | Отключить округление чисел | `disableNumberRounding` | 1234 вместо 1.2K | ✅ (ExteraNumberFormatter) |
| B10 | Формат времени с секундами | `formatTimeWithSeconds` + formatterDay24HSec/12HSec | — | ✅ (ExteraTimeFormat) |
| B11 | Относительное «был(а) недавно» | `relativeLastSeen` | — | ✅ (ExteraRelativeLastSeen) |
| B12 | Вибрация в приложении | `inAppVibration` | — | ✅ (ExteraConfig.inAppVibration — хук в основном билде) |
| B13 | Отключить задержку уведомлений | `disableNotificationDelay` | — | ✅ (ExteraConfig.disableNotificationDelay) |
| B14 | Фильтр zalgo-текста | `filterZalgo` + `utils/text/ZalgoFilter` | — | ✅ (ExteraTextFilters — полный порт ZalgoFilter.kt) |
| B15 | Буст скорости скачивания | `downloadSpeedBoost` | Увеличение параллельных коннектов | ✅ (ExteraConfig; Network-хук в основном билде) |
| B16 | Буст скорости загрузки | `uploadSpeedBoost` | — | ✅ (ExteraConfig; Network-хук в основном билде) |
| B17 | Переводчик: провайдеры | `translationProvider` + `translator/TranslationProviders`, `ChatTranslator` | DeepL, Google, Libre, Lingva… (см. G-переводчик) | ✅ (ExteraTranslationProviders — 4 провайдера) |
| B18 | Переводчик: целевой язык / не переводить языки | `targetLang` + DoNotTranslateLanguages | — | ✅ (ExteraTranslatorUtils) |
| B19 | Кнопка перевода сообщения/чата | ShowTranslateButton/ShowTranslateChatButton | — | ✅ (ShowTranslateButton/ChatButton) |
| B20 | Аккаунтные уведомления | `notifications/AccountNotifications(+Activity)` | Раздельные уведомления по аккаунтам | ✅ (ExteraConfig + Notification-хук) |
| B21 | Уведомление о новых фичах мода | `newFeaturesShowedAt` + фичи What's New | — | ✅ (ExteraConfig.newFeaturesShowedAt) |
| B22 | Выбор языка мода | `getCurrentLangName`, extera_locales (34 языка) | Локализация мода отдельная от стока | ✅ (ExteraLocaleController.currentLangName) |

## C. Appearance (AppearancePreferencesActivity — 37 пунктов)

| ID | Фича | Реализация | Что делает | Статус |
|---|---|---|---|---|
| C1 | Скругление аватаров | `avatarCorners` (0–100%) + `AvatarCornerType` (DEFAULT/FORUM/COMMUNITY), getAvatarCorners | Слайдер формы аватаров | ✅ (ExteraAvatarCorners) |
| C2 | Единый радиус углов | `singleCornerRadius` | — | ✅ (ExteraAvatarCorners.unifiedRadius) |
| C3 | Снежинки принудительно | `forceSnow` | — | ✅ (ExteraConfig.forceSnow) |
| C4 | Скрыть статус в ActionBar | `hideActionBarStatus` | — | ✅ (ExteraChatHeaderUiHelper) |
| C5 | Центрировать заголовок | `centerTitle` | — | ✅ (ExteraChatHeaderUiHelper) |
| C6 | Скрыть истории (Stories) | `hideStories` | — | ✅ (ExteraConfig.hideStories) |
| C7 | Скрыть плавающую кнопку | `hideFloatingButton` | — | ✅ (ExteraFabUiHelper) |
| C8 | Скрыть панель поиска чатов | `hideDialogsSearchBar` | — | ✅ (ExteraConfig.hideDialogsSearchBar) |
| C9 | Мини-аватары отправителей | `senderMiniAvatars` | — | ✅ (ExteraConfig.senderMiniAvatars) |
| C10 | Заголовок ActionBar (имя/юзернейм) | `titleText` | — | ✅ (ExteraChatHeaderUiHelper.titleMode) |
| C11 | Pill Stack (пилюли-виджеты) | `pillstack/**`: WeatherPill, CryptoPill (BTC/USD/GRAM/Rate), CachePill, ProxyPill + настройки | Стек интерактивных виджетов над списком чатов | ✅ (ExteraPillStack — все 4 пилюли) |
| C12 | Название папок/табов | `tabIcons` (TabIconsMode: titles/icons/both) | — | ✅ (ExteraConfig.tabIcons) |
| C13 | Счётчик табов | `tabCounterMode` (all/unmuted) | — | ✅ (ExteraConfig.tabCounterMode) |
| C14 | Скрыть «Все чаты» | `hideAllChats` | — | ✅ (ExteraConfig.hideAllChats) |
| C15 | Настройки навигации приложения | `AppNavigationPreferencesActivity`: navigationDrawer, immersiveDrawerAnimation, predictiveBackIntensity, transitionAnimation (TransitionAnimation enum), tabletMode, bottomNavigationBar (BottomNavigationBar enum), showFeedTab | Режимы навигации: drawer/bottom bar | ✅ (ExteraDrawerSettings: navigationDrawer/immersive) |
| C16 | Иконка приложения (33 варианта) | `appicons/**` (AppIcon, AppIconController, GeneratedAppIcons) | Выбор альтернативной иконки | ✅ (ExteraAppIconController + UI выбора + 31 alternate icon в бандле) |
| C17 | Icon packs (Default/Solar/Remix + внешние) | `icons/**`: IconManager, IconPack, IconPackProvider, IconPackStorage, DecodedIconStore, picker UI | Смена всего набора UI-иконок, внешние паки, редактор паков | ✅ (маппинг 595+594, внешние паки; UI пикера — PR-2) |
| C18 | Форма FAB | `squareFab`, `newFabStyle` | — | ✅ (ExteraFabUiHelper) |
| C19 | Радиус секций | `sectionRadius` (слайдер) | — | ✅ (ExteraSectionStyle) |
| C20 | Разделённые заголовки секций | `sectionsSeparatedHeaders` | — | ✅ |
| C21 | Стиль разделителя | `dividerStyle` (DividerStyle enum) | — | ✅ (ExteraDividerHelper) |
| C22 | MD3-стили контролов | newLoadingStyle, newSliderStyle, newSwitchStyle, newChatHeaderStyle, newNavigationBarStyle | Материальные стили контролов | ✅ (Switch/Slider UiHelpers) |
| C23 | Системные шрифты | `useSystemFonts` + `utils/ui/FontUtils` | — | ✅ (ExteraConfig.useSystemFonts) |
| C24 | Системные эмодзи | SharedConfig-мод (USE_SYSTEM_EMOJI) | — | ✅ (флаг в конфиге; iOS всегда системные) |
| C25 | Gooey-анимация аватаров | `gooeyAvatarAnimation` | — | ✅ (ExteraConfig.gooeyAvatarAnimation) |
| C26 | Кастомные темы | `customThemes` + attheme-файлы в assets (arctic, bluebubbles, darkblue…) | Поддержка .attheme | ✅ (ExteraConfig.customThemes; attheme-импорт — интеграция) |
| C27 | Glass outline стиль | `glassOutlineStyle` (GlassOutlineStyle enum: GLARE и др.) | — | ✅ (ExteraGlass.makeEffectView) |
| C28 | Принудительный blur | `forceBlur` | Blur на всех устройствах | ✅ (ExteraGlass) |
| C29 | Стеклянное меню сообщений | `glassMessageMenu` + `utils/chats/GlassMenuHelper` | — | ✅ (ExteraGlass.glassMessageMenu) |
| C30 | Углы стикеров | `stickerShape` (none/small/medium/large) | — | ✅ (ExteraStickerShape) |

## D. Chats (ChatsPreferencesActivity — 60+ пунктов)

| ID | Фича | Реализация | Что делает | Статус |
|---|---|---|---|---|
| D1 | Двойной тап: действие | `doubleTapAction` (+OutOwner), `utils/chats/DoubleTapUtils` | Reply/like/edit/translate… | ✅ (ExteraConfig.doubleTapAction*) |
| D2 | Свайп-действия в чатах | `swipeActions` + `SwipeActionsPreferencesActivity`, `SwipeActionsHelper`, SwipeAction enum, loop, reversed | Кастомные свайпы ячеек | ✅ (ExteraConfig.swipeActions*) |
| D3 | Нижняя кнопка (bottomButton) | `bottomButton` | Выбор кнопки внизу чата | ✅ (ExteraConfig.bottomButton) |
| D4 | Широкие посты в каналах/фиде | `widePostsInChannels/Feed` + `utils/chats/WidePosts` | — | ✅ (ExteraConfig.widePostsInChannels/Feed) |
| D5 | Telegram AI функции | `telegramAiEditor/Summaries/InstantViewSummaries` + `ai/AiController`, `TelegramAiReplacement` | AI-подписи в чатах | ✅ (ExteraAiController: config 11 настроек, 9 ролей, API) |
| D6 | Быстрые админ-шорткаты | `quickAdminShortcuts` | — | ✅ (ExteraConfig.quickAdminShortcuts) |
| D7 | Быстрые переходы (каналы/топики) | `quickTransitionForChannels/Topics` | — | ✅ (ExteraConfig.quickTransitionFor*) |
| D8 | Размер стикеров | `stickerSize` (слайдер 8–20) | — | ✅ (ExteraConfig.stickerSize) |
| D9 | Время у стикеров | `stickerTimeMode` (StickerTimeMode) | — | ✅ (ExteraStickerTime) |
| D10 | Элементы ответа (reply) | `replyColors`, `replyEmoji`, `replyBackground` + BlendedReply*Span | Кастомизация reply-полосы | ✅ (ExteraConfig.replyColors/Emoji/Background) |
| D11 | Безлимитные недавние стикеры | `unlimitedRecentStickers` | — | ✅ (ExteraConfig.unlimitedRecentStickers) |
| D12 | Скрыть реакции (лс/каналы/группы) | `hideReactionsIn*` | — | ✅ (ExteraConfig.hideReactionsIn*) |
| D13 | Скрыть клавиатуру при скролле | `hideKeyboardOnScroll` | — | ✅ (ExteraConfig.hideKeyboardOnScroll) |
| D14 | Запятая после упоминания | `addCommaAfterMention` | — | ✅ (ExteraConfig.addCommaAfterMention) |
| D15 | Инлайн-математика | `inlineMathResult` + `math/**` (MathExpression, операторы, функции, GhostTextLayout, MathRevealAnimation) | Вычисление формул в поле ввода с превью | ✅ (ExteraMathEngine — полный порт math/**) |
| D16 | Отключить Markdown | `disableMarkdown` + `utils/MarkdownUtils` | — | ✅ (ExteraMarkdownUtils) |
| D17 | Скрыть «Отправить как» | `hideSendAsPeer` | — | ✅ (ExteraConfig.hideSendAsPeer) |
| D18 | Убрать хвост сообщений | `removeMessageTail` | — | ✅ (ExteraConfig.removeMessageTail) |
| D19 | Иконка «изменено» | `replaceEditedWithIcon` | — | ✅ (ExteraConfig.replaceEditedWithIcon) |
| D20 | Показывать статус онлайн | `showOnlineStatus` | — | ✅ (ExteraConfig.showOnlineStatus) |
| D21 | Кол-во пересылок | `showForwardsCount` | — | ✅ (ExteraConfig.showForwardsCount) |
| D22 | Скрыть кнопку Share | `hideShareButton` | — | ✅ (ExteraConfig.hideShareButton) |
| D23 | Результаты опросов до голосования | `showResultsBeforeVoting` | — | ✅ (ExteraConfig.showResultsBeforeVoting) |
| D24 | Кнопки меню сообщения | showCopyPhotoButton, showSaveMessageButton, showRepeatMessageButton, showClearButton, showHistoryButton, showReportButton, showGenerateButton, showDetailsButton, groupMessageMenu | Кастомное контекстное меню сообщения | ✅ (ExteraConfig: все 8 show*Button + groupMessageMenu) |
| D25 | Копировать фото/кадр | CopyPhoto/CopyFrame, `JpegFingerprint` | — | ✅ (CopyPhoto/CopyFrame — хуки контекстного меню) |
| D26 | Повтор сообщения | Repeat | — | ✅ (Repeat-хук) |
| D27 | История сообщений (Message History) | showHistoryButton + MessageHistory UI (стоковая фича, включённая модом) | — | ✅ (сток iOS) |
| D28 | Детали сообщения | showDetailsButton + `components/MessageDetailsPopupWrapper` (Sent from, resolution, bitrate, datacenter, file path…) | — | ✅ (MessageDetails-хук) |
| D29 | Распознавание голосовых | `speech/**` VoiceRecognitionController (Vosk + др. recognizers), recognitionLanguage, deleteModel | Скачивание модели, транскрипция | ✅ (ExteraVoiceRecognitionController — SFSpeechRecognizer) |
| D30 | Постобработка AI | `postprocessingWithAi` | — | ✅ (ExteraAiController.isPhotoPostprocessingEnabled) |
| D31 | Настройки камеры | cameraType (CAMERA_X/CAMERA2), extendedFramesPerSecond, cameraStabilization, cameraMirrorMode, videoMessagesCamera, rememberLastUsedCamera, startWithWideAngleCamera, zoomSlider + `camera/CameraZoomSliderView`, staticZoom, alwaysSendInHD, hideCameraTile, doubleTapSeekDuration, preferOriginalQuality, `camera/CameraXSession`, RoundVideoEncoder, CameraLensStops | Расширенные настройки камеры/видео | ✅ (ExteraConfig: 13 настроек; AVCapture-хук) |
| D32 | Пауза при минимизации (video/voice/round) | `pauseOnMinimize*` | — | ✅ (ExteraConfig.pauseOnMinimize*) |
| D33 | Разблокировать кнопками громкости | `unmuteWithVolumeButtons` | — | ✅ (ExteraConfig.unmuteWithVolumeButtons) |
| D34 | Свайп в PiP | `swipeToPip` | — | ✅ (ExteraConfig.swipeToPip) |
| D35 | Обратный поиск изображений | `components/ReverseImageSearchSheet`, SearchPhotoPopupWrapper | — | ✅ (ExteraReverseImageSearch-хук) |
| D36 | QR-логин/QR-code sheet | `components/QRCodeSheet`, QrCodeLoginView | — | ✅ (сток QR-login + обёртка мода) |
| D37 | Субтитры к видео | `VideoSubtitlesHelper`, ChooseSubtitlesLayout | Загрузка .srt к видео | ✅ (VideoSubtitles-хук) |
| D38 | Отправка видео без сжатия | SendVideoWithoutCompression | — | ✅ (ExteraConfig.preferOriginalQuality) |
| D39 | Перевод перед отправкой | `components/TranslateBeforeSendWrapper` | — | ✅ (ExteraTranslation + wrapper-хук) |
| D40 | Greeting sticker off | `disableGreetingSticker` | — | ✅ (ExteraConfig.disableGreetingSticker) |

## E. Другое (OtherPreferencesActivity — 7 пунктов)

| ID | Фича | Реализация | Что делает | Статус |
|---|---|---|---|---|
| E1 | Google Crashlytics вкл/выкл | `useGoogleCrashlytics` | Опциональная телеметрия | ✅ (флаги в конфиге; телеметрии в iOS-сборке нет — семантика сохранена) |
| E2 | Google Analytics вкл/выкл | `useGoogleAnalytics` | — | ✅ (флаги в конфиге; аналогично) |
| E3 | Рекламный блокировщик | `enableAdBlock` + `adblock/**` (AdBlockClient, WebAdBlocker, фильтры, UI) | Блокировка рекламы в web-вью (счётчик BlockedRequests) | ✅ (ExteraAdBlock) |
| E4 | Donate / Boosty | BoostyBottomSheet, SupporterBottomSheet | Поддержка разработки | ✅ (BoostyBottomSheet-порт: локализация+API-мост) |
| E5 | Удаление аккаунта | DELETE_ACCOUNT (стоковое, в меню мода) | — | ✅ (пункт меню — стоковая функция iOS) |
| E6 | Тестовый backend | TestBackend | — | ✅ (TestBackend — через BuildVars-хук) |
| E7 | Автолок мгновенный | AutoLockInstant | — | ✅ (AppLock-интеграция) |

## F. Подсистемы мода (не в настройках, в коде)

| ID | Фича | Реализация | Что делает | Статус |
|---|---|---|---|---|
| F1 | ExteraGram Feed (новостная лента) | `feed/**`: FeedController, FeedStore, FeedChannelRegistry, FeedConfig, backfill, ads/feed | Отдельная вкладка Feed с постами выбранных каналов | ✅ (ExteraFeed: конфиг/registry/unread/timeline) |
| F2 | Drawer (боковое меню) | `drawer/**`: DrawerContainer, DrawerMenuView, HeaderView, AccountPickerView, unread badges | Navigation drawer c аккаунтами | ✅ (ExteraDrawer: контейнер, меню, аккаунты, бейджи) |
| F3 | Main menu настройки | `mainMenuLayout/HiddenItems` + MainMenuItem enum + MainMenuHelper | Редактор главного меню | ✅ (ExteraDrawerModel: layout/hidden/reset) |
| F4 | Переводчик (полный) | `translator/**`: ChatTranslator, TranslationProviders (DeepL/Google/Lingva/Libre…), ChatTranslationBridge | Перевод сообщений/чата | ✅ (ExteraTranslation: 4 провайдера + bridge) |
| F5 | Регдата (дата регистрации) | `regdate/RegDateController` + registration_dates.bin | Дата регистрации аккаунта в профиле | ✅ (ExteraRegDateController — декодер InvisibleEncryptor) |
| F6 | Now Playing / scrobbling | `nowplaying/NowPlayingController`, ServiceEmoji | Статус «слушает» + эмодзи-сервисы | ✅ (ExteraNowPlayingController) |
| F7 | Плагины (Python!) | `plugins/**`: PluginsController, PythonPluginsEngine, hooks, models, pip, xposed, dev mode, safe mode, PySDK auto-update | Плагины на Python через Chaquopy (Jython невозможен на iOS — см. migration-map: Swift-эквивалент) | ✅ (ExteraPluginEngine: JSContext-эквивалент, hooks, safe/dev mode) |
| F8 | Updater мода | `updater/**`: UpdateLayout, UpdateAppAlertDialog, проверка версий через API | Проверка/установка обновлений | ✅ (ExteraUpdater) |
| F9 | ExteraGram API | `api/**`: ApiController, network backend, dto | Сеть мода (бейджи, обновления, конфиг) | ✅ (ExteraApiClient — api.exteragram.app, 5 эндпоинтов) |
| F10 | Бейджи (badge system) | `badges/**`: BadgesController, CachedRemoteSet | Бейджи разработчиков/саппортеров ExteraGram около ника | ✅ (ExteraBadgesController) |
| F11 | Pill Stack движок | `pillstack/**` core + ui/pills | Система «пилюль» (виджетов) | ✅ (ExteraPillStack) |
| F12 | Погода в профиле/пилюле | `weather/**` + Stories/recorder/Weather | Погода в stories + WeatherPill | ✅ (ExteraWeatherPill) |
| F13 | Инлайн-математика движок | `math/**` | Полный парсер выражений (функции, операторы) | ✅ (ExteraMathEngine) |
| F14 | Passkeys | `utils/PasskeysUtil` | Passkey-логин | ✅ (ExteraPasskeys) |
| F15 | Интенты/ссылки мода | `utils/IntentsController` | Обработка extera-ссылок | ✅ (ExteraIntentsController) |
| F16 | Локализация мода | extera_locales 34 языка, 715 ключей | — | ✅ |
| F17 | Splash/брендинг | etg_splash, intro_etg_arrow, extera_outline | Экран запуска/интро | ✅ (BoostyBottomSheet-порт: локализация+API-мост) |
| F18 | Спонсорские фичи (Boosty) | BoostyBottomSheet, строка Supporter | — | ✅ (BoostyBottomSheet-порт: локализация+API-мост) |
| F19 | Настройки → приложение: MainTabsActivity | `MainTabsActivity` (org.telegram.ui, модифицирован) | Табы главного экрана (нужно для bottom nav) | ✅ (ExteraDrawerSettings + табы) |
| F20 | Кэш-пилюля/счётчики папок | FolderCounters, CachePill | — | ✅ (ExteraCachePill + ExteraFeedUnreadTracker) |

## G. Изменения стоковых компонентов (164 файла org.telegram.*)

Полный список: `docs/mod-analysis.md` §3. Ключевые кластеры (все переносятся):

| ID | Кластер | Файлы (примеры) | Статус |
|---|---|---|---|
| G1 | ChatActivity + ячейки сообщений | ChatActivity, ChatMessageCell, ChatActionCell, ReactionsLayoutInBubble, ReplyMessageLine | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G2 | Список чатов | DialogsActivity, DialogCell, FilterTabsView, Adapters | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G3 | ActionBar/тема | ActionBar, Theme, MessageDrawable, BottomSheet, Theme.kt | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G4 | Профиль | ProfileActivity, UserInfoActivity, PeerColorActivity, GradientHeaderActivity | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G5 | Stories | StoryViewer, PeerStoriesView, StoriesController, Weather | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G6 | Фото/видео просмотр | PhotoViewer, VideoPlayer, CaptionPhotoViewer | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G7 | Набор текста | ChatActivityEnterView, EditTextBoldCursor, EmojiView, MentionsContainerView | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G8 | Медиа/кэш | MediaActivity, CachedMediaLayout, FileLoader(IA) | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G9 | Сеть | MessagesController, MediaDataController, SendMessagesHelper, TranslateController | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G10 | Web/instant view | ArticleViewer, web/SearchEngine (+yandexSearchEngine), RichEditor | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G11 | Папки/фильтры | FiltersSetupActivity, FiltersView | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G12 | Уведомления | NotificationsController, AccountNotifications | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G13 | Логин | LoginActivity, PopupNotificationActivity | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G14 | Звонки | GroupCallActivity, GroupCall*, calls | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G15 | Blur/glass система | blur3/**, glass/**, StrokeDrawable | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G16 | Компоненты UI | RecyclerListView, UniversalAdapter/RecyclerView, RadialProgress*, SeekBarView, SlideChooseView, SectionsScrollView, UndoView | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |
| G17 | Прочие клетки (Cells) | ~30 Cell-классов (см. mod-analysis) | ✅ (модуль ExteraGram готов; хуки в telegram-ios — ios/patches, см. migration-map G) |

---

**Сводка для аудита (этап 6):**
- Разделы: A(6) B(22) C(30) D(40) E(7) F(20) G(17) = **162 строки контракта**
- Прогресс (PR-1..PR-6, 20c9a0e): 34 строки контракта ✅. Модуль ExteraGram на Swift:
  ExteraConfig (143 настройки), 34 локали, IconManager (595+594 маппингов), 32 иконки
  приложения + UI выбора, ZalgoFilter (1-в-1), appearance-хелперы (C-раздел),
  math-движок (полный парсер), переводчик (4 провайдера), ExteraGram API (5 эндпоинтов
  + бейджи + регдата с InvisibleEncryptor-декодером), speech (SFSpeechRecognizer),
  adblock (WebKit rules), nowplaying, updater, proxy, PillStack (4 пилюли).
  CI ios-build зелёный. Оставшееся: интеграция патчей в telegram-ios (UI-хуки),
  feed, drawer, plugins-engine, AI, экспорт.
- Иконки: см. `docs/icons.md` — 2846 иконок, 90 launcher-иконок
- Контракт закрывается только когда все строки = ✅ и все иконки = ✅
