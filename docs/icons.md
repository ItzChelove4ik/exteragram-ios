# Реестр иконок ExteraGram → iOS

Сгенерировано: 2026-10-25. Источник: APK exteraGram 12.10.6 (20261004).

- Векторных иконок (VectorDrawable → SVG): **1516**
- Растровых imageset (PNG/WebP → @1x/@2x/@3x): **1330**
- Всего в реестре: **2846**
- Альтернативные иконки приложения (mipmap adaptive): 33 + основная — см. раздел 3

Правило порта: используются ТОЛЬКО оригинальные иконки ExteraGram. Замена на системные iOS-иконки или иконки стокового Telegram запрещена. Template-иконки (монохромные) помечены в xcassets как template-rendering-intent=template — tint-поведение как в оригинале; тёмная тема через appearances=dark для night-вариантов.

## Icon packs (IconPackType: DEFAULT / SOLAR / REMIX)

ExteraGram содержит встроенные наборы иконок Solar и Remix — по 470 SVG-вариантов. На iOS: все варианты включены в `assets/VectorIcons/`, переключение набора — через рантайм-маппинг в Swift-модуле IconManager (порт `BaseIconPacks.kt` и `IconManager.kt`).

## 1. Иконки, используемые в коде (usage найден)

| Android resource | iOS asset | Тип | Использование | Статус |
|---|---|---|---|---|
| `_menu_stream_comments_off_24` | `_menu_stream_comments_off_24` | vector | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `account_check` | `account_check` | raster 1x, 2x, 3x | org\telegram\ui\Cells\AccountSelectCell.java, org\telegram\ui\Cells\UserCell.java | ⬜ |
| `action_share` | `action_share` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `ai_chat` | `ai_chat` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\chats\ChatsPreferencesActivity.java, org\telegram\ui\ChatActivity.java (+1) | ⬜ |
| `ai_chat_remix` | `ai_chat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ai_chat_solar` | `ai_chat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `album_shadow` | `album_shadow` | raster 1x, 2x, 3x | org\telegram\ui\Cells\PhotoPickerAlbumsCell.java | ⬜ |
| `animationpin` | `animationpin` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `animationpinleft` | `animationpinleft` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `animationpinright` | `animationpinright` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `arrow_more` | `arrow_more` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\RichMessageLayout.java, org\telegram\ui\BoostsActivity.java (+22) | ⬜ |
| `arrow_more_remix` | `arrow_more_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `arrow_more_solar` | `arrow_more_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `arrow_newchat` | `arrow_newchat` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\DialogsAdapter.java, org\telegram\ui\Cells\DialogsHintCell.java, org\telegram\ui\Components\EditCoverButton.java (+2) | ⬜ |
| `arrows_select` | `arrows_select` | vector | org\telegram\ui\Adapters\DialogsSearchAdapter.java, org\telegram\ui\Components\AlertsCreator.java, org\telegram\ui\Components\TranslateAlert3.java (+6) | ⬜ |
| `attach_arrow_left` | `attach_arrow_left` | raster 2x, 3x | org\telegram\ui\Components\ChatAttachAlert.java, org\telegram\ui\TopicsFragment.java | ⬜ |
| `attach_arrow_right` | `attach_arrow_right` | raster 2x, 3x | org\telegram\ui\Cells\UnconfirmedAuthHintCell.java, org\telegram\ui\ChannelAdminLogActivity.java, org\telegram\ui\Components\AlertsCreator.java (+8) | ⬜ |
| `attach_send` | `attach_send` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\PhotoAlbumPickerActivity.java (+1) | ⬜ |
| `attach_send_remix` | `attach_send_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `attach_send_solar` | `attach_send_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `authorize` | `authorize` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `background_selected` | `background_selected` | raster 1x, 2x, 3x | org\telegram\ui\Cells\WallpaperCell.java | ⬜ |
| `bg_rotate_large` | `bg_rotate_large` | raster 1x, 2x, 3x | org\telegram\ui\ThemePreviewActivity.java | ⬜ |
| `block_remix` | `block_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `block_solar` | `block_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `book_bot` | `book_bot` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java, org\telegram\messenger\NotificationsController.java | ⬜ |
| `book_channel` | `book_channel` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java | ⬜ |
| `book_group` | `book_group` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java, org\telegram\messenger\NotificationsController.java | ⬜ |
| `book_logo` | `book_logo` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java | ⬜ |
| `book_user` | `book_user` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java, org\telegram\messenger\NotificationsController.java | ⬜ |
| `boosts` | `boosts` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\TopicsFragment.java | ⬜ |
| `boosts_remix` | `boosts_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `boosts_solar` | `boosts_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `boosty_icon` | `boosty_icon` | vector | com\exteragram\messenger\components\BoostyBottomSheet.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java | ⬜ |
| `bot_card` | `bot_card` | raster 1x, 2x, 3x | org\telegram\messenger\RichMessageLayout.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_file` | `bot_file` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_invite` | `bot_invite` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_lines` | `bot_lines` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_link` | `bot_link` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_location` | `bot_location` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_location_remix` | `bot_location_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `bot_location_solar` | `bot_location_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `bot_music` | `bot_music` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `bot_webview` | `bot_webview` | raster 1x, 2x, 3x | org\telegram\messenger\RichMessageLayout.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\bots\BotKeyboardView.java | ⬜ |
| `burn_remix` | `burn_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `burn_solar` | `burn_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `buy_with_googlepay_button_content` | `buy_with_googlepay_button_content` | vector | org\telegram\ui\PaymentFormActivity.java | ⬜ |
| `call` | `call` | vector | org\telegram\messenger\voip\VoIPGroupNotification.java, org\telegram\messenger\voip\VoIPPreNotificationService.java, org\telegram\messenger\voip\VoIPService.java (+2) | ⬜ |
| `call_remix` | `call_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `call_solar` | `call_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_bluetooth` | `calls_bluetooth` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\GroupCallPipAlertView.java, org\telegram\ui\GroupCallActivity.java (+1) | ⬜ |
| `calls_camera_mini` | `calls_camera_mini` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\VoIPFragment.java | ⬜ |
| `calls_camera_mini_remix` | `calls_camera_mini_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_camera_mini_solar` | `calls_camera_mini_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_decline` | `calls_decline` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\GroupCallPipAlertView.java, org\telegram\ui\Components\voip\AcceptDeclineView.java (+4) | ⬜ |
| `calls_decline_remix` | `calls_decline_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_decline_solar` | `calls_decline_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_headphones` | `calls_headphones` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\GroupCallPipAlertView.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `calls_menu_bluetooth_remix` | `calls_menu_bluetooth_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_bluetooth_solar` | `calls_menu_bluetooth_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_headset` | `calls_menu_headset` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\voip\VoIPService.java, org\telegram\ui\VoIPFragment.java | ⬜ |
| `calls_menu_headset_remix` | `calls_menu_headset_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_headset_solar` | `calls_menu_headset_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_phone` | `calls_menu_phone` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\VoIPFragment.java | ⬜ |
| `calls_menu_phone_remix` | `calls_menu_phone_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_phone_solar` | `calls_menu_phone_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_speaker_remix` | `calls_menu_speaker_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_menu_speaker_solar` | `calls_menu_speaker_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_mute_mini` | `calls_mute_mini` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\voip\VoIPFloatingLayout.java, org\telegram\ui\VoIPFragment.java | ⬜ |
| `calls_mute_remix` | `calls_mute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_mute_solar` | `calls_mute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `calls_speaker` | `calls_speaker` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\GroupCallPipAlertView.java, org\telegram\ui\GroupCallActivity.java (+1) | ⬜ |
| `calls_unmute` | `calls_unmute` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\GroupCallPipAlertView.java | ⬜ |
| `calls_video` | `calls_video` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\voip\AcceptDeclineView.java, org\telegram\ui\Components\voip\GroupCallMiniTextureView.java (+1) | ⬜ |
| `camera` | `camera` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlert.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `camera_btn` | `camera_btn` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShutterButton.java | ⬜ |
| `camera_remix` | `camera_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `camera_revert1` | `camera_revert1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `camera_revert1_remix` | `camera_revert1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `camera_revert1_solar` | `camera_revert1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `camera_revert2` | `camera_revert2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `camera_revert2_remix` | `camera_revert2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `camera_revert2_solar` | `camera_revert2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `camera_solar` | `camera_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `cancel_big` | `cancel_big` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `cast_ic_notification_on` | `cast_ic_notification_on` | raster 1x, 2x, 3x | com\google\android\gms\cast\CastRemoteDisplayLocalService.java | ⬜ |
| `channel` | `channel` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `channel_remix` | `channel_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `channel_solar` | `channel_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chat_calls_incoming` | `chat_calls_incoming` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `chat_calls_outgoing` | `chat_calls_outgoing` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `chat_calls_video` | `chat_calls_video` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `chat_calls_voice` | `chat_calls_voice` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `chats_archive` | `chats_archive` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\ActionBarMenuItem.java, org\telegram\ui\Adapters\FiltersView.java (+1) | ⬜ |
| `chats_archive_remix` | `chats_archive_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_archive_solar` | `chats_archive_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_pin` | `chats_pin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `chats_replies` | `chats_replies` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `chats_replies_remix` | `chats_replies_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_replies_solar` | `chats_replies_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_saved` | `chats_saved` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\ActionBarMenuItem.java, org\telegram\ui\ActionBar\Theme.java (+1) | ⬜ |
| `chats_saved_remix` | `chats_saved_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_saved_solar` | `chats_saved_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `chats_undo` | `chats_undo` | raster 1x, 2x, 3x | org\telegram\ui\Components\Bulletin.java, org\telegram\ui\Components\UndoView.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `chats_unpin` | `chats_unpin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `checkbig` | `checkbig` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\BottomSheet.java, org\telegram\ui\GroupCreateFinalActivity.java | ⬜ |
| `circle` | `circle` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `circle_big` | `circle_big` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `cocoon_text` | `cocoon_text` | vector | org\telegram\ui\Components\TranslateButton.java | ⬜ |
| `community_cards` | `community_cards` | vector | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `community_requests_outline_24` | `community_requests_outline_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\community\CommunityEditActivity.java | ⬜ |
| `compose_panel_shadow` | `compose_panel_shadow` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `delete` | `delete` | raster 1x, 2x, 3x | org\telegram\ui\Components\GroupCreateSpan.java | ⬜ |
| `device_desktop_osx` | `device_desktop_osx` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_desktop_other` | `device_desktop_other` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_desktop_win` | `device_desktop_win` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_phone_android` | `device_phone_android` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java, org\telegram\ui\Components\FloatingDebug\FloatingDebugView.java | ⬜ |
| `device_phone_ios` | `device_phone_ios` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_tablet_android` | `device_tablet_android` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_tablet_ios` | `device_tablet_ios` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_web_chrome` | `device_web_chrome` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_web_edge` | `device_web_edge` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `device_web_firefox` | `device_web_firefox` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java, org\telegram\ui\SessionBottomSheet.java | ⬜ |
| `device_web_opera` | `device_web_opera` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java, org\telegram\ui\SessionBottomSheet.java | ⬜ |
| `device_web_other` | `device_web_other` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java, org\telegram\ui\SessionBottomSheet.java | ⬜ |
| `device_web_safari` | `device_web_safari` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `dialog_media_checklist_20` | `dialog_media_checklist_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_game_20` | `dialog_media_game_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_incoming_call_20` | `dialog_media_incoming_call_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_incoming_video_call_20` | `dialog_media_incoming_video_call_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_outgoing_call_20` | `dialog_media_outgoing_call_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_outgoing_video_call_20` | `dialog_media_outgoing_video_call_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `dialog_media_poll_20` | `dialog_media_poll_20` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `diamond` | `diamond` | vector | org\telegram\ui\StakedDiceSheet.java, org\telegram\ui\Stars\StarsIntroActivity.java | ⬜ |
| `dice` | `dice` | raster 1x, 2x, 3x | org\telegram\ui\Components\UndoView.java | ⬜ |
| `dice1` | `dice1` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `dice2` | `dice2` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `dice3` | `dice3` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `dice4` | `dice4` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `dice5` | `dice5` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `dice6` | `dice6` | vector | org\telegram\ui\StakedDiceSheet.java | ⬜ |
| `drawer_proxy_off` | `drawer_proxy_off` | vector | com\exteragram\messenger\drawer\DrawerHeaderView.java, com\exteragram\messenger\pillstack\ui\pills\system\ProxyPill.java | ⬜ |
| `drawer_proxy_on` | `drawer_proxy_on` | vector | com\exteragram\messenger\drawer\DrawerHeaderView.java, com\exteragram\messenger\pillstack\core\PillRegistry.java, com\exteragram\messenger\pillstack\ui\pills\system\ProxyPill.java | ⬜ |
| `emoji_love` | `emoji_love` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `emoji_tabs_faves` | `emoji_tabs_faves` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java, org\telegram\ui\Stories\recorder\EmojiBottomSheet.java | ⬜ |
| `emoji_tabs_faves_remix` | `emoji_tabs_faves_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_faves_solar` | `emoji_tabs_faves_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new1` | `emoji_tabs_new1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `emoji_tabs_new1_remix` | `emoji_tabs_new1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new1_solar` | `emoji_tabs_new1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new2` | `emoji_tabs_new2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `emoji_tabs_new2_remix` | `emoji_tabs_new2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new2_solar` | `emoji_tabs_new2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new3` | `emoji_tabs_new3` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java, org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `emoji_tabs_new3_remix` | `emoji_tabs_new3_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `emoji_tabs_new3_solar` | `emoji_tabs_new3_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `extera_heart_large` | `extera_heart_large` | vector | com\exteragram\messenger\components\SupporterBottomSheet.java | ⬜ |
| `extera_outline` | `extera_outline` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\utils\SettingsRegistry.java, org\telegram\ui\ContentPreviewViewer.java (+1) | ⬜ |
| `extera_outline_remix` | `extera_outline_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `extera_outline_solar` | `extera_outline_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `fast_scroll_shadow` | `fast_scroll_shadow` | raster 1x, 2x, 3x | org\telegram\ui\Components\RecyclerListView.java | ⬜ |
| `files_folder` | `files_folder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java | ⬜ |
| `files_folder_remix` | `files_folder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_folder_solar` | `files_folder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_gallery` | `files_gallery` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\SharedDocumentCell.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java | ⬜ |
| `files_gallery_remix` | `files_gallery_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_gallery_solar` | `files_gallery_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_internal` | `files_internal` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\SharedDocumentCell.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java | ⬜ |
| `files_internal_remix` | `files_internal_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_internal_solar` | `files_internal_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_music` | `files_music` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SharedDocumentCell.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java, org\telegram\ui\Components\ProfileMusicView.java | ⬜ |
| `files_storage` | `files_storage` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\SharedDocumentCell.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java | ⬜ |
| `files_storage_remix` | `files_storage_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `files_storage_solar` | `files_storage_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_access_fingerprint` | `filled_access_fingerprint` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_access_location` | `filled_access_location` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_access_sleeping` | `filled_access_sleeping` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_add_album` | `filled_add_album` | vector | org\telegram\ui\DialogsActivity.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java, org\telegram\ui\ProfileActivity.java (+2) | ⬜ |
| `filled_add_photo` | `filled_add_photo` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\CaptionPhotoViewer.java | ⬜ |
| `filled_add_photo_remix` | `filled_add_photo_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_add_photo_solar` | `filled_add_photo_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_add_sticker` | `filled_add_sticker` | raster 1x, 2x, 3x | org\telegram\ui\Components\StickersAlert.java, org\telegram\ui\Stories\recorder\EmojiBottomSheet.java | ⬜ |
| `filled_affiliate` | `filled_affiliate` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_birthday` | `filled_birthday` | vector | org\telegram\ui\UserInfoActivity.java | ⬜ |
| `filled_boost_plus` | `filled_boost_plus` | raster 1x, 2x, 3x | org\telegram\ui\BoostsActivity.java | ⬜ |
| `filled_bot_approve_24` | `filled_bot_approve_24` | vector | org\telegram\messenger\BotInlineKeyboard.java | ⬜ |
| `filled_bot_decline_24` | `filled_bot_decline_24` | vector | org\telegram\messenger\BotInlineKeyboard.java | ⬜ |
| `filled_bot_suggest_24` | `filled_bot_suggest_24` | vector | org\telegram\messenger\BotInlineKeyboard.java | ⬜ |
| `filled_button_reply` | `filled_button_reply` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_button_share` | `filled_button_share` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_button_share_remix` | `filled_button_share_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_button_share_solar` | `filled_button_share_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_calls_bluetooth_s` | `filled_calls_bluetooth_s` | vector | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `filled_calls_plus` | `filled_calls_plus` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java | ⬜ |
| `filled_calls_plus_remix` | `filled_calls_plus_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_calls_plus_solar` | `filled_calls_plus_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_calls_users` | `filled_calls_users` | vector | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\CreateGroupCallSheet.java, org\telegram\ui\GroupCallSheet.java | ⬜ |
| `filled_chatlink_large` | `filled_chatlink_large` | raster 1x, 2x, 3x | org\telegram\ui\Business\BusinessLinksEmptyView.java | ⬜ |
| `filled_chatlist2` | `filled_chatlist2` | vector | org\telegram\ui\Components\ChatGreetingsView.java | ⬜ |
| `filled_chatlist_mention` | `filled_chatlist_mention` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `filled_chatlist_mention_solar` | `filled_chatlist_mention_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_chatlist_poll` | `filled_chatlist_poll` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_chatlist_poll_remix` | `filled_chatlist_poll_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_chatlist_poll_solar` | `filled_chatlist_poll_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_chatlist_reaction` | `filled_chatlist_reaction` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_clear` | `filled_clear` | raster 1x, 2x, 3x | org\telegram\ui\Components\PasscodeView.java | ⬜ |
| `filled_crown_off` | `filled_crown_off` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_crown_on` | `filled_crown_on` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_directions` | `filled_directions` | raster 1x, 2x, 3x | org\telegram\ui\Cells\LocationDirectionCell.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `filled_earn_stars` | `filled_earn_stars` | raster 1x, 2x, 3x | org\telegram\ui\ChannelMonetizationLayout.java, org\telegram\ui\Stars\BotStarsActivity.java, org\telegram\ui\Stars\StarsIntroActivity.java (+1) | ⬜ |
| `filled_extend_location` | `filled_extend_location` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `filled_fab_compose_32` | `filled_fab_compose_32` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\components\FabShapeCell.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `filled_fab_compose_remix` | `filled_fab_compose_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_fab_compose_solar` | `filled_fab_compose_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_fire` | `filled_fire` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_folder_existing` | `filled_folder_existing` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_folder_new` | `filled_folder_new` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_forge` | `filled_forge` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_forward` | `filled_forward` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ShareTopView.java | ⬜ |
| `filled_forward_remix` | `filled_forward_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_forward_settings` | `filled_forward_settings` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `filled_forward_solar` | `filled_forward_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_gift_pause_24` | `filled_gift_pause_24` | vector | org\telegram\ui\Stars\StarGiftPreviewSheet.java | ⬜ |
| `filled_gift_play_24` | `filled_gift_play_24` | vector | org\telegram\ui\Stars\StarGiftPreviewSheet.java | ⬜ |
| `filled_gift_premium` | `filled_gift_premium` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\BoostsActivity.java | ⬜ |
| `filled_gift_sell_24` | `filled_gift_sell_24` | vector | org\telegram\ui\Cells\ActiveGiftAuctionsHintCell.java, org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Gifts\ActiveAuctionsSheet.java (+2) | ⬜ |
| `filled_gift_sell_off` | `filled_gift_sell_off` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_gift_sell_on` | `filled_gift_sell_on` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_gift_simple` | `filled_gift_simple` | vector | org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `filled_gift_transfer` | `filled_gift_transfer` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_giveaway_premium` | `filled_giveaway_premium` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_giveaway_stars` | `filled_giveaway_stars` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\Premium\boosts\cells\msg\GiveawayMessageCell.java, org\telegram\ui\Components\Premium\boosts\cells\msg\GiveawayResultsMessageCell.java | ⬜ |
| `filled_info` | `filled_info` | raster 1x, 2x, 3x | org\telegram\ui\BoostsActivity.java, org\telegram\ui\Cells\UserInfoCell.java | ⬜ |
| `filled_limit_boost` | `filled_limit_boost` | raster 1x, 2x, 3x | org\telegram\ui\BoostsActivity.java, org\telegram\ui\ChannelBoostLayout.java, org\telegram\ui\ChatActivity.java (+1) | ⬜ |
| `filled_link` | `filled_link` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Stories\recorder\StoryLinkSheet.java | ⬜ |
| `filled_link_remix` | `filled_link_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_link_solar` | `filled_link_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_location` | `filled_location` | raster 1x, 2x, 3x | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java, com\exteragram\messenger\pillstack\ui\pills\weather\WeatherSettingsActivity.java, org\telegram\ui\Components\ChatAttachAlertPollLayout.java (+3) | ⬜ |
| `filled_location_forever` | `filled_location_forever` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\SharingLiveLocationCell.java | ⬜ |
| `filled_messages_paid` | `filled_messages_paid` | vector | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_new_contact_24` | `filled_new_contact_24` | vector | org\telegram\ui\Components\ContactsEmptyView.java | ⬜ |
| `filled_open_message` | `filled_open_message` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_open_message_remix` | `filled_open_message_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_open_message_solar` | `filled_open_message_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_paid_broadcast` | `filled_paid_broadcast` | vector | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `filled_paid_suggest_24` | `filled_paid_suggest_24` | vector | org\telegram\ui\ChatActivity.java | ⬜ |
| `filled_poll_add_24` | `filled_poll_add_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_correct_24` | `filled_poll_correct_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_deadline_24` | `filled_poll_deadline_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_multiple_24` | `filled_poll_multiple_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_revote_24` | `filled_poll_revote_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_shuffle_24` | `filled_poll_shuffle_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_poll_view_24` | `filled_poll_view_24` | vector | org\telegram\ui\Components\ChatAttachAlertPollLayout.java | ⬜ |
| `filled_premium_away` | `filled_premium_away` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_premium_bots` | `filled_premium_bots` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_premium_business` | `filled_premium_business` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_premium_camera` | `filled_premium_camera` | raster 1x, 2x, 3x | org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\PremiumPreviewFragment.java, org\telegram\ui\ProfileActivity.java (+2) | ⬜ |
| `filled_premium_chatlink` | `filled_premium_chatlink` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_premium_hours` | `filled_premium_hours` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `filled_premium_intro` | `filled_premium_intro` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_premium_status2` | `filled_premium_status2` | raster 1x, 2x, 3x | org\telegram\ui\PremiumFeatureCell.java, org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_profile_call_24` | `filled_profile_call_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_edit_24` | `filled_profile_edit_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AIEditorAlert.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_member_24` | `filled_profile_member_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_message_24` | `filled_profile_message_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_mute_24` | `filled_profile_mute_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_photo` | `filled_profile_photo` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_photo_20` | `filled_profile_photo_20` | vector | org\telegram\ui\DialogsActivity.java | ⬜ |
| `filled_profile_settings` | `filled_profile_settings` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java (+2) | ⬜ |
| `filled_profile_settings_remix` | `filled_profile_settings_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_profile_settings_solar` | `filled_profile_settings_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_profile_stop_24` | `filled_profile_stop_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_story` | `filled_profile_story` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_unmute_24` | `filled_profile_unmute_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_profile_video_24` | `filled_profile_video_24` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `filled_rating_crown` | `filled_rating_crown` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_reactions` | `filled_reactions` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_reply_quote` | `filled_reply_quote` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `filled_reply_quote_remix` | `filled_reply_quote_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_reply_quote_solar` | `filled_reply_quote_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_reply_settings` | `filled_reply_settings` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `filled_reply_settings_remix` | `filled_reply_settings_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_reply_settings_solar` | `filled_reply_settings_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filled_requests_24` | `filled_requests_24` | vector | org\telegram\ui\DialogsActivity.java, org\telegram\ui\community\CommunitySheet.java | ⬜ |
| `filled_sensitive` | `filled_sensitive` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\SharedPhotoVideoCell2.java | ⬜ |
| `filled_share` | `filled_share` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_sharing_off2_24` | `filled_sharing_off2_24` | vector | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `filled_sound_on` | `filled_sound_on` | vector | org\telegram\ui\Components\MuteDrawable.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `filled_star_plus` | `filled_star_plus` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `filled_stop_location` | `filled_stop_location` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `filled_stream_crown` | `filled_stream_crown` | vector | org\telegram\ui\Stars\StarsReactionsSheet.java, org\telegram\ui\Stories\LiveCommentsView.java | ⬜ |
| `filled_stream_crown_outline` | `filled_stream_crown_outline` | vector | org\telegram\ui\Stars\StarsReactionsSheet.java | ⬜ |
| `filled_suggest_chat_avatar` | `filled_suggest_chat_avatar` | vector | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_topic_new_24` | `filled_topic_new_24` | vector | org\telegram\ui\Cells\BotAskCellDrawable.java | ⬜ |
| `filled_track_add` | `filled_track_add` | vector | org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `filled_unclaimed` | `filled_unclaimed` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_understood` | `filled_understood` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `filled_unknown` | `filled_unknown` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `filled_username` | `filled_username` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `filled_verify_age` | `filled_verify_age` | vector | org\telegram\ui\ThemeActivity.java | ⬜ |
| `filled_video_group` | `filled_video_group` | vector | org\telegram\ui\Cells\ChatMessageCell.java | ⬜ |
| `filled_views` | `filled_views` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SharedPhotoVideoCell2.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `filled_voice_comment_32` | `filled_voice_comment_32` | vector | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `filled_widget_music` | `filled_widget_music` | raster 1x, 2x, 3x | com\exteragram\messenger\components\BlendedReplyFileText.java, org\telegram\messenger\car\HomeScreen.java, org\telegram\ui\Stories\StoryCaptionView.java (+2) | ⬜ |
| `filter_airplane` | `filter_airplane` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_airplane_remix` | `filter_airplane_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_airplane_solar` | `filter_airplane_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_all` | `filter_all` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_all_remix` | `filter_all_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_all_solar` | `filter_all_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_book` | `filter_book` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_book_remix` | `filter_book_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_book_solar` | `filter_book_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_bots` | `filter_bots` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_bots_remix` | `filter_bots_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_bots_solar` | `filter_bots_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_cat` | `filter_cat` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_cat_remix` | `filter_cat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_cat_solar` | `filter_cat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_channels` | `filter_channels` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_channels_remix` | `filter_channels_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_channels_solar` | `filter_channels_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_crown` | `filter_crown` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_crown_remix` | `filter_crown_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_crown_solar` | `filter_crown_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_custom` | `filter_custom` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_custom_remix` | `filter_custom_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_custom_solar` | `filter_custom_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_favorite` | `filter_favorite` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_favorite_remix` | `filter_favorite_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_favorite_solar` | `filter_favorite_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_flower` | `filter_flower` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_flower_remix` | `filter_flower_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_flower_solar` | `filter_flower_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_game` | `filter_game` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_game_remix` | `filter_game_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_game_solar` | `filter_game_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_group` | `filter_group` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_group_remix` | `filter_group_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_group_solar` | `filter_group_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_home` | `filter_home` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_home_remix` | `filter_home_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_home_solar` | `filter_home_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_light` | `filter_light` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_light_remix` | `filter_light_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_light_solar` | `filter_light_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_like` | `filter_like` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_like_remix` | `filter_like_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_like_solar` | `filter_like_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_love` | `filter_love` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_love_remix` | `filter_love_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_love_solar` | `filter_love_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_mask` | `filter_mask` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_mask_remix` | `filter_mask_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_mask_solar` | `filter_mask_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_money` | `filter_money` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_money_remix` | `filter_money_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_money_solar` | `filter_money_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_note` | `filter_note` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_note_remix` | `filter_note_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_note_solar` | `filter_note_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_palette` | `filter_palette` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_palette_remix` | `filter_palette_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_palette_solar` | `filter_palette_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_party` | `filter_party` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_party_remix` | `filter_party_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_party_solar` | `filter_party_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_private` | `filter_private` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_private_remix` | `filter_private_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_private_solar` | `filter_private_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_setup` | `filter_setup` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_setup_remix` | `filter_setup_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_setup_solar` | `filter_setup_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_sport` | `filter_sport` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_sport_remix` | `filter_sport_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_sport_solar` | `filter_sport_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_study` | `filter_study` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_study_remix` | `filter_study_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_study_solar` | `filter_study_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_trade` | `filter_trade` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_trade_remix` | `filter_trade_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_trade_solar` | `filter_trade_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_travel` | `filter_travel` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_travel_remix` | `filter_travel_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_travel_solar` | `filter_travel_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_unmuted` | `filter_unmuted` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_unmuted_remix` | `filter_unmuted_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_unmuted_solar` | `filter_unmuted_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_unread` | `filter_unread` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_unread_remix` | `filter_unread_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_unread_solar` | `filter_unread_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_work` | `filter_work` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\FolderIcons.java | ⬜ |
| `filter_work_remix` | `filter_work_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `filter_work_solar` | `filter_work_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `fingerprint` | `fingerprint` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PasscodeView.java | ⬜ |
| `fingerprint_remix` | `fingerprint_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `fingerprint_solar` | `fingerprint_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_auto` | `flash_auto` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `flash_auto_remix` | `flash_auto_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_auto_solar` | `flash_auto_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_off` | `flash_off` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `flash_off_remix` | `flash_off_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_off_solar` | `flash_off_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_on` | `flash_on` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `flash_on_remix` | `flash_on_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `flash_on_solar` | `flash_on_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `floating_check` | `floating_check` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatActionCell.java, org\telegram\ui\Cells\StickerSetCell.java, org\telegram\ui\Components\InviteMembersBottomSheet.java (+5) | ⬜ |
| `floating_pencil` | `floating_pencil` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java, org\telegram\ui\ThemePreviewActivity.java | ⬜ |
| `floating_shadow` | `floating_shadow` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\ui\picker\IconPickerView.java, org\telegram\ui\Components\FloatingDebug\FloatingDebugView.java | ⬜ |
| `floating_shadow_profile` | `floating_shadow_profile` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `formatting_bold` | `formatting_bold` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `formatting_italic` | `formatting_italic` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `formatting_marked` | `formatting_marked` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `formatting_spoiler` | `formatting_spoiler` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `formatting_strikethrough` | `formatting_strikethrough` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `formatting_underline` | `formatting_underline` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `forward_to_stories` | `forward_to_stories` | vector | org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `foursquare` | `foursquare` | raster 1x, 2x, 3x | org\telegram\ui\Cells\LocationPoweredCell.java | ⬜ |
| `fragment` | `fragment` | vector | org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `ft_avd_toarrow` | `ft_avd_toarrow` | vector | org\telegram\ui\ActionBar\FloatingToolbar.java | ⬜ |
| `ft_avd_tooverflow` | `ft_avd_tooverflow` | vector | org\telegram\ui\ActionBar\FloatingToolbar.java | ⬜ |
| `ghost` | `ghost` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `ghost_remix` | `ghost_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ghost_solar` | `ghost_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `gif_empty` | `gif_empty` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiView.java | ⬜ |
| `gift` | `gift` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\PremiumFeatureBottomSheet.java (+1) | ⬜ |
| `gift_message_bubble_24` | `gift_message_bubble_24` | vector | org\telegram\ui\Gifts\GiftMessageDrawable.java | ⬜ |
| `gift_message_bubble_border_24` | `gift_message_bubble_border_24` | vector | org\telegram\ui\Gifts\GiftMessageDrawable.java | ⬜ |
| `gift_remix` | `gift_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `gift_solar` | `gift_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `gift_unpack` | `gift_unpack` | vector | org\telegram\ui\Cells\ChatActionCell.java | ⬜ |
| `group_edit` | `group_edit` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `group_edit_profile` | `group_edit_profile` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `group_edit_profile_remix` | `group_edit_profile_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `group_edit_profile_solar` | `group_edit_profile_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `groups_create_remix` | `groups_create_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `groups_create_solar` | `groups_create_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `groups_limit1` | `groups_limit1` | raster 1x, 2x, 3x | org\telegram\ui\Cells\TooManyCommunitiesHintCell.java | ⬜ |
| `groups_overview_check` | `groups_overview_check` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatBigEmptyView.java | ⬜ |
| `header_qr_24` | `header_qr_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\NewContactBottomSheet.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `header_shadow` | `header_shadow` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\plugins\ui\components\PluginsMenuWrapper.java, org\telegram\ui\ActionBar\AlertDialog.java (+7) | ⬜ |
| `header_shadow_reverse` | `header_shadow_reverse` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\AlertDialog.java, org\telegram\ui\Components\SharingLocationsAlert.java, org\telegram\ui\PassportActivity.java (+2) | ⬜ |
| `heart_confetti` | `heart_confetti` | raster 1x, 2x, 3x | org\telegram\ui\Components\FireworksOverlay.java | ⬜ |
| `ic_ab_back` | `ic_ab_back` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\appicons\ui\AppIconsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java (+125) | ⬜ |
| `ic_ab_back_remix` | `ic_ab_back_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_ab_back_solar` | `ic_ab_back_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_ab_close` | `ic_ab_close` | vector | org\telegram\ui\DataSettingsActivity.java, org\telegram\ui\FiltersSetupActivity.java, org\telegram\ui\LanguageSelectActivity.java (+10) | ⬜ |
| `ic_ab_done` | `ic_ab_done` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\EditServiceActivity.java, com\exteragram\messenger\icons\ui\IconPacksEditorActivity.java, com\exteragram\messenger\icons\ui\picker\IconPickerView.java (+24) | ⬜ |
| `ic_ab_other` | `ic_ab_other` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\ui\IconPacksEditorActivity.java (+45) | ⬜ |
| `ic_ab_reply` | `ic_ab_reply` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `ic_ab_search_remix` | `ic_ab_search_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_ab_search_solar` | `ic_ab_search_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_action_next` | `ic_action_next` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `ic_action_pause` | `ic_action_pause` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `ic_action_play` | `ic_action_play` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `ic_action_previous` | `ic_action_previous` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `ic_again` | `ic_again` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_againinline` | `ic_againinline` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_arrow_back_black_24` | `ic_arrow_back_black_24` | vector | com\google\android\material\search\SearchView.java | ⬜ |
| `ic_arrow_drop_down` | `ic_arrow_drop_down` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java (+3) | ⬜ |
| `ic_arrow_drop_down_remix` | `ic_arrow_drop_down_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_arrow_drop_down_solar` | `ic_arrow_drop_down_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_call` | `ic_call` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPGroupNotification.java, org\telegram\messenger\voip\VoIPPreNotificationService.java | ⬜ |
| `ic_call_end_white_24dp` | `ic_call_end_white_24dp` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPGroupNotification.java, org\telegram\messenger\voip\VoIPPreNotificationService.java, org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `ic_chatlist_add_2` | `ic_chatlist_add_2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\TopicsFragment.java | ⬜ |
| `ic_chatlist_add_2_remix` | `ic_chatlist_add_2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_chatlist_add_2_solar` | `ic_chatlist_add_2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_chevron_right_black_18dp` | `ic_chevron_right_black_18dp` | raster 1x, 2x, 3x | org\telegram\ui\Charts\view_data\LegendSignatureView.java | ⬜ |
| `ic_close_white` | `ic_close_white` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\GenerateFromMessageBottomSheet.java, com\exteragram\messenger\components\ReverseImageSearchSheet.java, org\telegram\ui\ActionBar\ActionBarMenuItem.java (+17) | ⬜ |
| `ic_colorpicker_remix` | `ic_colorpicker_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_colorpicker_solar` | `ic_colorpicker_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_feed` | `ic_feed` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\preferences\utils\SettingsRegistry.java (+2) | ⬜ |
| `ic_feed_filled` | `ic_feed_filled` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `ic_feed_filled_remix` | `ic_feed_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_feed_filled_solar` | `ic_feed_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_feed_remix` | `ic_feed_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_feed_solar` | `ic_feed_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_foreground_solid` | `ic_foreground_solid` | vector | com\exteragram\messenger\preferences\components\HeaderSettingsCell.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `ic_gallery_background` | `ic_gallery_background` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\WallpaperCell.java | ⬜ |
| `ic_gallery_background_remix` | `ic_gallery_background_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_gallery_background_solar` | `ic_gallery_background_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_gofullscreen` | `ic_gofullscreen` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_goinline` | `ic_goinline` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmbedBottomSheet.java, org\telegram\ui\Components\WebPlayerView.java (+1) | ⬜ |
| `ic_goinline_remix` | `ic_goinline_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_goinline_solar` | `ic_goinline_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_launcher_dr` | `ic_launcher_dr` | raster 1x, 2x, 3x | org\telegram\messenger\NotificationsController.java, org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `ic_layer_close` | `ic_layer_close` | raster 1x, 2x, 3x | org\telegram\ui\Components\DownloadsInfoBottomSheet.java, org\telegram\ui\Components\JoinGroupAlert.java, org\telegram\ui\Components\PermanentLinkBottomSheet.java | ⬜ |
| `ic_lock_header` | `ic_lock_header` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `ic_lock_white` | `ic_lock_white` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatBigEmptyView.java, org\telegram\ui\PopupNotificationActivity.java | ⬜ |
| `ic_masks_msk1` | `ic_masks_msk1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_masks_msk1_remix` | `ic_masks_msk1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_masks_msk1_solar` | `ic_masks_msk1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_monochrome` | `ic_monochrome` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_candy` | `ic_monochrome_candy` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_cyberpunk` | `ic_monochrome_cyberpunk` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_google` | `ic_monochrome_google` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_nasa` | `ic_monochrome_nasa` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_orbit` | `ic_monochrome_orbit` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_monochrome_space` | `ic_monochrome_space` | vector | com\exteragram\messenger\appicons\GeneratedAppIcons.java | ⬜ |
| `ic_mtrl_arrow_circle` | `ic_mtrl_arrow_circle` | vector | com\google\android\material\loadingindicator\LoadingIndicatorDrawable.java, com\google\android\material\progressindicator\IndeterminateDrawable.java | ⬜ |
| `ic_outfullscreen` | `ic_outfullscreen` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_outinline` | `ic_outinline` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_outinline_remix` | `ic_outinline_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_outinline_solar` | `ic_outinline_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_pause` | `ic_pause` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_pauseinline` | `ic_pauseinline` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_play` | `ic_play` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_playinline` | `ic_playinline` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_rating_star` | `ic_rating_star` | raster 1x, 2x, 3x | org\telegram\ui\Components\BetterRatingView.java | ⬜ |
| `ic_rating_star_filled` | `ic_rating_star_filled` | raster 1x, 2x, 3x | org\telegram\ui\Components\BetterRatingView.java | ⬜ |
| `ic_reply_icon` | `ic_reply_icon` | raster 1x, 2x | org\telegram\messenger\NotificationsController.java | ⬜ |
| `ic_search_black_24` | `ic_search_black_24` | vector | com\google\android\material\search\SearchBar.java | ⬜ |
| `ic_send` | `ic_send` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `ic_send_remix` | `ic_send_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_send_solar` | `ic_send_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `ic_share_video` | `ic_share_video` | raster 1x, 2x, 3x | org\telegram\ui\Components\WebPlayerView.java | ⬜ |
| `ic_video` | `ic_video` | raster 1x, 2x, 3x | org\telegram\ui\Cells\PhotoPickerPhotoCell.java | ⬜ |
| `input_ai` | `input_ai` | vector | org\telegram\ui\Components\AiButtonDrawable.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `input_ai_star` | `input_ai_star` | vector | org\telegram\ui\Components\AiButtonDrawable.java | ⬜ |
| `input_attach` | `input_attach` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\AttachBotIntroTopView.java (+1) | ⬜ |
| `input_attach_remix` | `input_attach_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_attach_solar` | `input_attach_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_bot1` | `input_bot1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_bot1_remix` | `input_bot1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_bot1_solar` | `input_bot1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_bot2` | `input_bot2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_bot2_remix` | `input_bot2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_bot2_solar` | `input_bot2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_calendar1` | `input_calendar1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_calendar1_remix` | `input_calendar1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_calendar1_solar` | `input_calendar1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_calendar2` | `input_calendar2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_calendar2_remix` | `input_calendar2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_calendar2_solar` | `input_calendar2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_clear` | `input_clear` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\ShareTopView.java, org\telegram\ui\Stories\recorder\StoryLinkSheet.java | ⬜ |
| `input_done` | `input_done` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Stories\recorder\CaptionContainerView.java, org\telegram\ui\iv\RichEditor.java (+1) | ⬜ |
| `input_forward` | `input_forward` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\chat\layouts\ChatActivityActionsButtonsLayout.java | ⬜ |
| `input_forward_remix` | `input_forward_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_forward_solar` | `input_forward_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_gift_s` | `input_gift_s` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java, org\telegram\ui\Components\chat\layouts\ChatActivityChannelButtonsLayout.java | ⬜ |
| `input_keyboard` | `input_keyboard` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EditTextEmoji.java (+3) | ⬜ |
| `input_keyboard_remix` | `input_keyboard_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_keyboard_solar` | `input_keyboard_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_message` | `input_message` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\chat\layouts\ChatActivityChannelButtonsLayout.java | ⬜ |
| `input_mic` | `input_mic` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_mic_pressed` | `input_mic_pressed` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_mic_pressed_remix` | `input_mic_pressed_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_mic_pressed_solar` | `input_mic_pressed_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_mic_remix` | `input_mic_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_mic_solar` | `input_mic_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_notify_off` | `input_notify_off` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AIEditorAlert.java, org\telegram\ui\Components\ChatActivityEnterView.java (+9) | ⬜ |
| `input_notify_on` | `input_notify_on` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_reply` | `input_reply` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\chat\layouts\ChatActivityActionsButtonsLayout.java | ⬜ |
| `input_reply_remix` | `input_reply_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_reply_solar` | `input_reply_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_schedule` | `input_schedule` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `input_schedule_remix` | `input_schedule_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_schedule_solar` | `input_schedule_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_smile` | `input_smile` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EditTextEmoji.java (+3) | ⬜ |
| `input_smile_remix` | `input_smile_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_smile_solar` | `input_smile_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_suggest_paid_24` | `input_suggest_paid_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\ChatAttachAlert.java (+1) | ⬜ |
| `input_suggest_paid_remix` | `input_suggest_paid_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_suggest_paid_solar` | `input_suggest_paid_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_video` | `input_video` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `input_video_pressed` | `input_video_pressed` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java (+1) | ⬜ |
| `input_video_pressed_remix` | `input_video_pressed_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_video_pressed_solar` | `input_video_pressed_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_video_remix` | `input_video_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_video_solar` | `input_video_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `input_video_story` | `input_video_story` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\CaptionStory.java | ⬜ |
| `input_video_story_remove` | `input_video_story_remove` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\CaptionStory.java | ⬜ |
| `intro_etg_arrow` | `intro_etg_arrow` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_fast_arrow` | `intro_fast_arrow` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_fast_arrow_shadow` | `intro_fast_arrow_shadow` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_fast_body` | `intro_fast_body` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_fast_spiral` | `intro_fast_spiral` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_bubble` | `intro_ic_bubble` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_bubble_dot` | `intro_ic_bubble_dot` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_cam` | `intro_ic_cam` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_cam_lens` | `intro_ic_cam_lens` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_pencil` | `intro_ic_pencil` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_pin` | `intro_ic_pin` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_smile` | `intro_ic_smile` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_smile_eye` | `intro_ic_smile_eye` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_ic_videocam` | `intro_ic_videocam` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_knot_down` | `intro_knot_down` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_knot_up` | `intro_knot_up` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_powerful_infinity` | `intro_powerful_infinity` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_powerful_infinity_white` | `intro_powerful_infinity_white` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_powerful_mask` | `intro_powerful_mask` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_powerful_star` | `intro_powerful_star` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_private_door` | `intro_private_door` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `intro_private_screw` | `intro_private_screw` | raster 1x, 2x, 3x | org\telegram\ui\IntroActivity.java | ⬜ |
| `iv_align_horiz_left` | `iv_align_horiz_left` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_align_horiz_middle` | `iv_align_horiz_middle` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_align_horiz_right` | `iv_align_horiz_right` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_align_vert_bottom` | `iv_align_vert_bottom` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_align_vert_middle` | `iv_align_vert_middle` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_align_vert_top` | `iv_align_vert_top` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_audio` | `iv_audio` | vector | org\telegram\ui\iv\RichCommand.java | ⬜ |
| `iv_audio_preview` | `iv_audio_preview` | vector | org\telegram\messenger\MessageObject.java | ⬜ |
| `iv_button` | `iv_button` | vector | org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_checkbox_off` | `iv_checkbox_off` | vector | org\telegram\messenger\MessageObject.java | ⬜ |
| `iv_checkbox_on` | `iv_checkbox_on` | vector | org\telegram\messenger\MessageObject.java | ⬜ |
| `iv_code` | `iv_code` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java (+1) | ⬜ |
| `iv_details` | `iv_details` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_divider` | `iv_divider` | vector | org\telegram\ui\iv\RichCommand.java | ⬜ |
| `iv_footer` | `iv_footer` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_formula` | `iv_formula` | vector | org\telegram\messenger\MessageObject.java | ⬜ |
| `iv_fullscreen` | `iv_fullscreen` | vector | org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `iv_h` | `iv_h` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h1` | `iv_h1` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h2` | `iv_h2` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h3` | `iv_h3` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h4` | `iv_h4` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h5` | `iv_h5` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_h6` | `iv_h6` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_list` | `iv_list` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_list_tab` | `iv_list_tab` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_list_untab` | `iv_list_untab` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_lists` | `iv_lists` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_location` | `iv_location` | vector | org\telegram\ui\iv\RichCommand.java | ⬜ |
| `iv_math` | `iv_math` | vector | org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_media` | `iv_media` | vector | org\telegram\ui\iv\RichCommand.java | ⬜ |
| `iv_media_add` | `iv_media_add` | vector | org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `iv_media_collage` | `iv_media_collage` | vector | org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `iv_media_dots` | `iv_media_dots` | vector | org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `iv_media_slideshow` | `iv_media_slideshow` | vector | org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `iv_ordered_list` | `iv_ordered_list` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_preview_table` | `iv_preview_table` | vector | org\telegram\messenger\MessageObject.java | ⬜ |
| `iv_prompt` | `iv_prompt` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `iv_pullquote` | `iv_pullquote` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_quote` | `iv_quote` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java (+1) | ⬜ |
| `iv_redo` | `iv_redo` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_sub` | `iv_sub` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_super` | `iv_super` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_table` | `iv_table` | vector | org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_table_highlight` | `iv_table_highlight` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_highlight_remove` | `iv_table_highlight_remove` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_insert_bottom` | `iv_table_insert_bottom` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_insert_left` | `iv_table_insert_left` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_insert_right` | `iv_table_insert_right` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_insert_top` | `iv_table_insert_top` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_merge` | `iv_table_merge` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_remove` | `iv_table_remove` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_table_unmerge` | `iv_table_unmerge` | vector | org\telegram\ui\iv\RichEditorListView.java | ⬜ |
| `iv_text` | `iv_text` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `iv_text2` | `iv_text2` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_todo` | `iv_todo` | vector | org\telegram\ui\iv\ChatAttachAlertRichLayout.java, org\telegram\ui\iv\RichCommand.java, org\telegram\ui\iv\RichEditor.java | ⬜ |
| `iv_undo` | `iv_undo` | vector | org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `join_remix` | `join_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `join_solar` | `join_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `large_ads_info` | `large_ads_info` | raster 1x, 2x, 3x | org\telegram\ui\RevenueSharingAdsInfoBottomSheet.java, org\telegram\ui\SearchAdsInfoBottomSheet.java | ⬜ |
| `large_archive` | `large_archive` | raster 1x, 2x, 3x | org\telegram\ui\Components\ArchiveHelp.java | ⬜ |
| `large_article` | `large_article` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `large_away` | `large_away` | raster 1x, 2x, 3x | org\telegram\ui\Business\QuickRepliesEmptyView.java | ⬜ |
| `large_boosts` | `large_boosts` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `large_forge` | `large_forge` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `large_greeting` | `large_greeting` | raster 1x, 2x, 3x | org\telegram\ui\Business\QuickRepliesEmptyView.java | ⬜ |
| `large_hashtags` | `large_hashtags` | raster 1x, 2x, 3x | org\telegram\ui\Components\HashtagHistoryView.java | ⬜ |
| `large_hidden` | `large_hidden` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\SessionCell.java | ⬜ |
| `large_income` | `large_income` | raster 1x, 2x, 3x | org\telegram\ui\Components\InviteLinkBottomSheet.java, org\telegram\ui\ManageLinksActivity.java | ⬜ |
| `large_locked_post` | `large_locked_post` | raster 1x, 2x, 3x | org\telegram\ui\Stars\StarsIntroActivity.java | ⬜ |
| `large_log_actions` | `large_log_actions` | raster 1x, 2x, 3x | org\telegram\ui\ChannelAdminLogActivity.java | ⬜ |
| `large_monetize` | `large_monetize` | raster 1x, 2x, 3x | org\telegram\ui\ChannelMonetizationLayout.java | ⬜ |
| `large_notes` | `large_notes` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `large_quickreplies` | `large_quickreplies` | raster 1x, 2x, 3x | org\telegram\ui\Business\QuickRepliesEmptyView.java | ⬜ |
| `large_repost_story` | `large_repost_story` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\ShareDialogCell.java | ⬜ |
| `large_stealth` | `large_stealth` | raster 1x, 2x, 3x | org\telegram\ui\Stories\StealthModeAlert.java | ⬜ |
| `large_unsupported` | `large_unsupported` | vector | org\telegram\ui\Components\UnsupportedBlockDrawable.java | ⬜ |
| `large_user_tag` | `large_user_tag` | vector | org\telegram\ui\Components\TagEditCell.java | ⬜ |
| `leave` | `leave` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `leave_remix` | `leave_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `leave_solar` | `leave_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `left_status_profile` | `left_status_profile` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java (+2) | ⬜ |
| `list_check` | `list_check` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ArticleViewer.java, org\telegram\ui\Cells\RequestPeerRequirementsCell.java | ⬜ |
| `list_circle` | `list_circle` | raster 1x, 2x, 3x | org\telegram\messenger\SendMessagesHelper.java, org\telegram\ui\Cells\RequestPeerRequirementsCell.java, org\telegram\ui\ChannelMonetizationLayout.java (+6) | ⬜ |
| `list_halfcheck` | `list_halfcheck` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `list_mute` | `list_mute` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `list_mute_remix` | `list_mute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_mute_solar` | `list_mute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_pin` | `list_pin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `list_reorder` | `list_reorder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\IconPackCell.java, com\exteragram\messenger\pillstack\ui\PillStackPreferencesActivity.java (+9) | ⬜ |
| `list_reorder_remix` | `list_reorder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_reorder_solar` | `list_reorder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_secret` | `list_secret` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `list_secret_remix` | `list_secret_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_secret_solar` | `list_secret_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `list_unmute` | `list_unmute` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `list_warning_sign` | `list_warning_sign` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `live_loc` | `live_loc` | raster 1x, 2x, 3x | org\telegram\messenger\LocationSharingService.java | ⬜ |
| `live_stream` | `live_stream` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `live_stream_remix` | `live_stream_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `live_stream_solar` | `live_stream_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `livepin` | `livepin` | raster 1x, 2x, 3x | org\telegram\messenger\ChatMessageSharedResources.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `load_big` | `load_big` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `location_empty` | `location_empty` | raster 1x, 2x, 3x | org\telegram\ui\Cells\LocationLoadingCell.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `login_arrow1` | `login_arrow1` | raster 1x, 2x, 3x | org\telegram\ui\LoginActivity.java | ⬜ |
| `login_phone1` | `login_phone1` | raster 1x, 2x, 3x | org\telegram\ui\LoginActivity.java | ⬜ |
| `logo_middle` | `logo_middle` | raster 1x, 2x, 3x | org\telegram\ui\Components\TermsOfServiceView.java | ⬜ |
| `managed_bot` | `managed_bot` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `map_pin` | `map_pin` | raster 1x, 2x, 3x | org\telegram\messenger\ChatMessageSharedResources.java, org\telegram\messenger\RichMessageLayout.java, org\telegram\ui\ArticleViewer.java (+3) | ⬜ |
| `map_pin2` | `map_pin2` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatAttachAlertLocationLayout.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `map_pin3` | `map_pin3` | vector | org\telegram\ui\Components\Paint\Views\LocationMarker.java, org\telegram\ui\Stories\recorder\EmojiBottomSheet.java | ⬜ |
| `map_pin_circle` | `map_pin_circle` | raster 1x, 2x, 3x | org\telegram\ui\LocationActivity.java | ⬜ |
| `map_pin_cone2` | `map_pin_cone2` | raster 1x, 2x, 3x | org\telegram\ui\LocationActivity.java | ⬜ |
| `map_pin_photo` | `map_pin_photo` | raster 1x, 2x, 3x | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherSettingsActivity.java, org\telegram\ui\Business\LocationActivity.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `mastercard_icon` | `mastercard_icon` | vector | com\exteragram\messenger\preferences\OtherPreferencesActivity.java | ⬜ |
| `material_ic_calendar_black_24dp` | `material_ic_calendar_black_24dp` | vector | com\google\android\material\datepicker\MaterialDatePicker.java | ⬜ |
| `material_ic_edit_black_24dp` | `material_ic_edit_black_24dp` | vector | com\google\android\material\datepicker\MaterialDatePicker.java | ⬜ |
| `media_button_erase` | `media_button_erase` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\StickerCutOutBtn.java | ⬜ |
| `media_button_restore` | `media_button_restore` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\StickerCutOutBtn.java, org\telegram\ui\Components\PhotoViewerPollAttachButtons.java | ⬜ |
| `media_crop` | `media_crop` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\Stories\recorder\PreviewButtons.java | ⬜ |
| `media_doc_blue` | `media_doc_blue` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_blue_b` | `media_doc_blue_b` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_green` | `media_doc_green` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_green_b` | `media_doc_green_b` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_red` | `media_doc_red` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_red_b` | `media_doc_red_b` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_yellow` | `media_doc_yellow` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_doc_yellow_b` | `media_doc_yellow_b` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java | ⬜ |
| `media_download` | `media_download` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\DownloadButton.java | ⬜ |
| `media_draw` | `media_draw` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\Stories\recorder\PreviewButtons.java | ⬜ |
| `media_dual_camera2` | `media_dual_camera2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_dual_camera2_remix` | `media_dual_camera2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_dual_camera2_shadow` | `media_dual_camera2_shadow` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_dual_camera2_shadow_remix` | `media_dual_camera2_shadow_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_dual_camera2_shadow_solar` | `media_dual_camera2_shadow_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_dual_camera2_solar` | `media_dual_camera2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_flip` | `media_flip` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `media_like` | `media_like` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `media_like_active` | `media_like_active` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\ReactedUserHolderView.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `media_like_active_remix` | `media_like_active_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_like_active_solar` | `media_like_active_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_link_24` | `media_link_24` | vector | org\telegram\ui\Components\poll\attached\PollAttachedMediaLink.java, org\telegram\ui\Components\poll\buttons\PollButtonDrawable.java, org\telegram\ui\iv\RichEditor.java (+2) | ⬜ |
| `media_live_off` | `media_live_off` | vector | org\telegram\ui\Cells\PhotoAttachPhotoCell.java | ⬜ |
| `media_live_on` | `media_live_on` | vector | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\PhotoAttachPhotoCell.java, org\telegram\ui\Components\LivePhotoButton.java | ⬜ |
| `media_magic_cut` | `media_magic_cut` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\StickerCutOutBtn.java | ⬜ |
| `media_more` | `media_more` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\Stars\StarGiftSheet.java (+1) | ⬜ |
| `media_mute` | `media_mute` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_photo_flash_auto2` | `media_photo_flash_auto2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_photo_flash_auto2_remix` | `media_photo_flash_auto2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_photo_flash_auto2_solar` | `media_photo_flash_auto2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_photo_flash_off2` | `media_photo_flash_off2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_photo_flash_off2_remix` | `media_photo_flash_off2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_photo_flash_off2_solar` | `media_photo_flash_off2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_photo_flash_on2` | `media_photo_flash_on2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `media_photo_flash_on2_remix` | `media_photo_flash_on2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_photo_flash_on2_solar` | `media_photo_flash_on2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `media_repost` | `media_repost` | raster 1x, 2x, 3x | org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `media_settings` | `media_settings` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `media_share` | `media_share` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `media_sticker_stroke` | `media_sticker_stroke` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\StickerCutOutBtn.java | ⬜ |
| `media_unmute` | `media_unmute` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `mentionbutton` | `mentionbutton` | raster 1x, 2x, 3x | org\telegram\ui\Components\chat\layouts\ChatActivitySideControlsButtonsLayout.java | ⬜ |
| `menu_2sv` | `menu_2sv` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `menu_2sv_on` | `menu_2sv_on` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `menu_2sv_remix` | `menu_2sv_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_2sv_solar` | `menu_2sv_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_add_stories` | `menu_add_stories` | vector | org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `menu_add_tab_24` | `menu_add_tab_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java, org\telegram\ui\ContactsActivity.java (+1) | ⬜ |
| `menu_add_tab_remix` | `menu_add_tab_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_add_tab_solar` | `menu_add_tab_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_album_add` | `menu_album_add` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ItemOptions.java, org\telegram\ui\Components\SharedMediaLayout.java (+1) | ⬜ |
| `menu_bid_refund_24` | `menu_bid_refund_24` | vector | org\telegram\ui\Gifts\AuctionJoinSheet.java | ⬜ |
| `menu_birthday` | `menu_birthday` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\boosts\UserSelectorBottomSheet.java, org\telegram\ui\ContactAddActivity.java | ⬜ |
| `menu_birthday_remix` | `menu_birthday_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_browser_arrowup` | `menu_browser_arrowup` | raster 1x, 2x, 3x | org\telegram\ui\web\AddressBarList.java | ⬜ |
| `menu_browser_bookmarks` | `menu_browser_bookmarks` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\web\WebActionBar.java | ⬜ |
| `menu_call_create` | `menu_call_create` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java, org\telegram\ui\MainTabsActivity.java | ⬜ |
| `menu_call_create_2_24` | `menu_call_create_2_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java | ⬜ |
| `menu_call_create_remix` | `menu_call_create_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_call_create_solar` | `menu_call_create_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_camera_retake` | `menu_camera_retake` | raster 1x, 2x, 3x | org\telegram\ui\Stories\PeerStoriesView.java, org\telegram\ui\Stories\recorder\CollageLayoutView2.java | ⬜ |
| `menu_carryover_24` | `menu_carryover_24` | vector | org\telegram\ui\Gifts\AuctionJoinSheet.java | ⬜ |
| `menu_cashtag` | `menu_cashtag` | raster 1x, 2x, 3x | org\telegram\ui\Components\HashtagHistoryView.java | ⬜ |
| `menu_clear_cache` | `menu_clear_cache` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\web\WebBrowserSettings.java | ⬜ |
| `menu_clear_cache_remix` | `menu_clear_cache_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_clear_cache_solar` | `menu_clear_cache_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_clear_cookies` | `menu_clear_cookies` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\web\WebBrowserSettings.java | ⬜ |
| `menu_clear_recent` | `menu_clear_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\web\AddressBarList.java, org\telegram\ui\web\WebBrowserSettings.java | ⬜ |
| `menu_comments` | `menu_comments` | vector | org\telegram\ui\Stories\CommentButton.java | ⬜ |
| `menu_comments_arrow` | `menu_comments_arrow` | vector | org\telegram\ui\Stories\CommentButton.java | ⬜ |
| `menu_contact_existing` | `menu_contact_existing` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_copy_s` | `menu_copy_s` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Components\Premium\boosts\cells\LinkCell.java | ⬜ |
| `menu_cover_stories` | `menu_cover_stories` | raster 1x, 2x, 3x | org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_day_mode_24` | `menu_day_mode_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\MainTabsActivity.java | ⬜ |
| `menu_day_mode_remix` | `menu_day_mode_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_day_mode_solar` | `menu_day_mode_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_delete_old` | `menu_delete_old` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\TagEditCell.java, org\telegram\ui\PhotoViewer.java (+2) | ⬜ |
| `menu_delete_paid` | `menu_delete_paid` | vector | org\telegram\ui\ChatActivity.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_devices` | `menu_devices` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SessionBottomSheet.java | ⬜ |
| `menu_download_off_24` | `menu_download_off_24` | vector | org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `menu_download_round` | `menu_download_round` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\AudioPlayerAlert.java (+2) | ⬜ |
| `menu_edit_appearance` | `menu_edit_appearance` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppearancePreferencesActivity.java, org\telegram\ui\PeerColorActivity.java | ⬜ |
| `menu_edit_appearance_remix` | `menu_edit_appearance_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_edit_appearance_solar` | `menu_edit_appearance_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_edit_price` | `menu_edit_price` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_edit_price_remix` | `menu_edit_price_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_edit_price_solar` | `menu_edit_price_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_edited_stamp` | `menu_edited_stamp` | raster 1x, 2x, 3x | org\telegram\ui\Components\MessagePrivateSeenView.java | ⬜ |
| `menu_face` | `menu_face` | vector | org\telegram\ui\PasskeysActivity.java | ⬜ |
| `menu_factcheck` | `menu_factcheck` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java | ⬜ |
| `menu_feature_affect` | `menu_feature_affect` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_feature_code` | `menu_feature_code` | vector | org\telegram\ui\LoginActivity.java | ⬜ |
| `menu_feature_color_name` | `menu_feature_color_name` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_color_profile` | `menu_feature_color_profile` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_cover` | `menu_feature_cover` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_feature_cover_24` | `menu_feature_cover_24` | vector | org\telegram\ui\Gifts\AuctionWearingSheet.java | ⬜ |
| `menu_feature_custombg` | `menu_feature_custombg` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_hands` | `menu_feature_hands` | vector | org\telegram\ui\LoginActivity.java | ⬜ |
| `menu_feature_hourglass` | `menu_feature_hourglass` | vector | org\telegram\ui\AccountFrozenAlert.java | ⬜ |
| `menu_feature_intro` | `menu_feature_intro` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_feature_links` | `menu_feature_links` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\Gifts\AuctionJoinSheet.java | ⬜ |
| `menu_feature_links2` | `menu_feature_links2` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\bots\AffiliateProgramFragment.java | ⬜ |
| `menu_feature_noads` | `menu_feature_noads` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\RevenueSharingAdsInfoBottomSheet.java, org\telegram\ui\SearchAdsInfoBottomSheet.java | ⬜ |
| `menu_feature_pack` | `menu_feature_pack` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_paid` | `menu_feature_paid` | raster 1x, 2x, 3x | com\exteragram\messenger\components\SupporterBottomSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java (+4) | ⬜ |
| `menu_feature_paid_remix` | `menu_feature_paid_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_feature_paid_solar` | `menu_feature_paid_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_feature_premium` | `menu_feature_premium` | raster 1x, 2x, 3x | org\telegram\ui\ChatEditActivity.java, org\telegram\ui\bots\AffiliateProgramFragment.java | ⬜ |
| `menu_feature_reactions` | `menu_feature_reactions` | raster 1x, 2x, 3x | com\exteragram\messenger\components\SupporterBottomSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_reactions_remix` | `menu_feature_reactions_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_feature_reactions_solar` | `menu_feature_reactions_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_feature_reliable` | `menu_feature_reliable` | raster 1x, 2x, 3x | org\telegram\ui\bots\ChannelAffiliateProgramsFragment.java | ⬜ |
| `menu_feature_simple` | `menu_feature_simple` | raster 1x, 2x, 3x | org\telegram\ui\bots\ChannelAffiliateProgramsFragment.java | ⬜ |
| `menu_feature_split` | `menu_feature_split` | raster 1x, 2x, 3x | org\telegram\ui\ChannelMonetizationLayout.java, org\telegram\ui\RevenueSharingAdsInfoBottomSheet.java | ⬜ |
| `menu_feature_status` | `menu_feature_status` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java, org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_stories` | `menu_feature_stories` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_tradable` | `menu_feature_tradable` | vector | org\telegram\ui\Components\Premium\PremiumFeatureBottomSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_feature_transfer` | `menu_feature_transfer` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_feature_translate` | `menu_feature_translate` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_transparent` | `menu_feature_transparent` | raster 1x, 2x, 3x | org\telegram\ui\bots\ChannelAffiliateProgramsFragment.java | ⬜ |
| `menu_feature_unique` | `menu_feature_unique` | vector | org\telegram\ui\Components\Premium\PremiumFeatureBottomSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_feature_voice` | `menu_feature_voice` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_wallpaper` | `menu_feature_wallpaper` | raster 1x, 2x, 3x | com\exteragram\messenger\components\SupporterBottomSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `menu_feature_withdrawals` | `menu_feature_withdrawals` | raster 1x, 2x, 3x | org\telegram\ui\ChannelMonetizationLayout.java | ⬜ |
| `menu_folder_add` | `menu_folder_add` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `menu_folder_add_remix` | `menu_folder_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_folder_add_solar` | `menu_folder_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_forward_stamp` | `menu_forward_stamp` | raster 1x, 2x, 3x | org\telegram\ui\Components\MessagePrivateSeenView.java | ⬜ |
| `menu_gift` | `menu_gift` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\TranslateButton.java (+1) | ⬜ |
| `menu_gift_add` | `menu_gift_add` | vector | org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `menu_hashtag` | `menu_hashtag` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\MentionsAdapter.java, org\telegram\ui\Components\HashtagHistoryView.java | ⬜ |
| `menu_hide_gift` | `menu_hide_gift` | vector | com\exteragram\messenger\feed\FeedChannelActions.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `menu_high_price` | `menu_high_price` | vector | org\telegram\ui\LoginActivity.java | ⬜ |
| `menu_instant_view` | `menu_instant_view` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\web\WebActionBar.java | ⬜ |
| `menu_instant_view_remix` | `menu_instant_view_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_instant_view_solar` | `menu_instant_view_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_intro` | `menu_intro` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java, org\telegram\ui\bots\BotWebViewSheet.java (+1) | ⬜ |
| `menu_intro_remix` | `menu_intro_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_intro_solar` | `menu_intro_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_invit_telegram` | `menu_invit_telegram` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_invite_telegram_remix` | `menu_invite_telegram_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_invite_telegram_solar` | `menu_invite_telegram_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_lightbulb` | `menu_lightbulb` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\CollageLayoutView2.java | ⬜ |
| `menu_link_above` | `menu_link_above` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\CaptionPhotoViewer.java, org\telegram\ui\Components\ChatAttachAlert.java | ⬜ |
| `menu_link_above_remix` | `menu_link_above_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_link_above_solar` | `menu_link_above_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_link_below` | `menu_link_below` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\CaptionPhotoViewer.java, org\telegram\ui\Components\ChatAttachAlert.java | ⬜ |
| `menu_link_below_remix` | `menu_link_below_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_link_below_solar` | `menu_link_below_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_link_create` | `menu_link_create` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Business\BusinessLinksActivity.java | ⬜ |
| `menu_link_create2` | `menu_link_create2` | vector | org\telegram\ui\GroupCreateActivity.java | ⬜ |
| `menu_link_revoke` | `menu_link_revoke` | vector | org\telegram\ui\CallLogActivity.java | ⬜ |
| `menu_night_mode_24` | `menu_night_mode_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\MainTabsActivity.java (+1) | ⬜ |
| `menu_night_mode_24_remix` | `menu_night_mode_24_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_night_mode_24_solar` | `menu_night_mode_24_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_paid_off` | `menu_paid_off` | vector | org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_passkey_add` | `menu_passkey_add` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PasskeysActivity.java | ⬜ |
| `menu_passkey_add_remix` | `menu_passkey_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_passkey_add_solar` | `menu_passkey_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_phone` | `menu_phone` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\boosts\cells\selector\SelectorUserCell.java, org\telegram\ui\LoginActivity.java | ⬜ |
| `menu_photo_off_24` | `menu_photo_off_24` | vector | org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `menu_poll_notify` | `menu_poll_notify` | vector | org\telegram\ui\Components\chat\layouts\ChatActivitySideControlsButtonsLayout.java | ⬜ |
| `menu_poll_order_24` | `menu_poll_order_24` | vector | org\telegram\ui\Cells\PollEditTextCell.java | ⬜ |
| `menu_premium_away` | `menu_premium_away` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_premium_chatbot` | `menu_premium_chatbot` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_premium_chatlink` | `menu_premium_chatlink` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_premium_clock` | `menu_premium_clock` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_premium_clock_add` | `menu_premium_clock_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Business\OpeningHoursDayActivity.java | ⬜ |
| `menu_premium_clock_add_remix` | `menu_premium_clock_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_premium_clock_add_solar` | `menu_premium_clock_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_premium_clock_remix` | `menu_premium_clock_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_premium_clock_solar` | `menu_premium_clock_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_premium_effects` | `menu_premium_effects` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `menu_premium_location` | `menu_premium_location` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_premium_main` | `menu_premium_main` | raster 1x, 2x, 3x | org\telegram\ui\ChatEditActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_premium_seen` | `menu_premium_seen` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `menu_premium_tools` | `menu_premium_tools` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `menu_privacy` | `menu_privacy` | raster 1x, 2x, 3x | org\telegram\ui\AccountFrozenAlert.java, org\telegram\ui\Components\TranslateButton.java, org\telegram\ui\PasskeysActivity.java (+2) | ⬜ |
| `menu_privacy_policy` | `menu_privacy_policy` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_profile_colors` | `menu_profile_colors` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_profile_colors_locked` | `menu_profile_colors_locked` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_profile_colors_remix` | `menu_profile_colors_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_profile_colors_solar` | `menu_profile_colors_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_proofread` | `menu_proofread` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `menu_quality_hd` | `menu_quality_hd` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java, org\telegram\ui\Components\Premium\FeaturesPageView.java (+1) | ⬜ |
| `menu_quality_hd_filled` | `menu_quality_hd_filled` | vector | org\telegram\ui\PhotoViewer.java | ⬜ |
| `menu_quality_hd_remix` | `menu_quality_hd_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quality_hd_solar` | `menu_quality_hd_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quality_sd` | `menu_quality_sd` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_quality_sd_filled` | `menu_quality_sd_filled` | vector | org\telegram\ui\PhotoViewer.java | ⬜ |
| `menu_quality_sd_remix` | `menu_quality_sd_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quality_sd_solar` | `menu_quality_sd_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quickreply` | `menu_quickreply` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `menu_quote_delete` | `menu_quote_delete` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MessagePreviewView.java | ⬜ |
| `menu_quote_delete_remix` | `menu_quote_delete_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quote_delete_solar` | `menu_quote_delete_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quote_remix` | `menu_quote_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quote_solar` | `menu_quote_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_quote_specific` | `menu_quote_specific` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MessagePreviewView.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `menu_random` | `menu_random` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\boosts\SelectorBottomSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_reaction_delete_24` | `menu_reaction_delete_24` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_refund` | `menu_refund` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_reply` | `menu_reply` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\utils\SettingsRegistry.java, com\exteragram\messenger\utils\chats\DoubleTapUtils.java (+5) | ⬜ |
| `menu_rewrite` | `menu_rewrite` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `menu_select_quote` | `menu_select_quote` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MessagePreviewView.java | ⬜ |
| `menu_select_quote_remix` | `menu_select_quote_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_select_quote_solar` | `menu_select_quote_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_share_off_24` | `menu_share_off_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_share_off_remix` | `menu_share_off_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_share_off_solar` | `menu_share_off_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_share_on_24` | `menu_share_on_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_shop` | `menu_shop` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_shop_remix` | `menu_shop_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_shop_solar` | `menu_shop_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_sidebar_bottom` | `menu_sidebar_bottom` | vector | org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `menu_sidebar_left` | `menu_sidebar_left` | vector | org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `menu_sidebar_top` | `menu_sidebar_top` | vector | org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `menu_smile_add` | `menu_smile_add` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `menu_sort_date` | `menu_sort_date` | vector | org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\Gifts\ResaleGiftsFragment.java, org\telegram\ui\Gifts\SelectGiftsBottomSheet.java | ⬜ |
| `menu_sort_number` | `menu_sort_number` | vector | org\telegram\ui\Gifts\ResaleGiftsFragment.java | ⬜ |
| `menu_sort_value` | `menu_sort_value` | vector | org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\Gifts\ResaleGiftsFragment.java, org\telegram\ui\Gifts\SelectGiftsBottomSheet.java | ⬜ |
| `menu_stars_gift` | `menu_stars_gift` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java, org\telegram\ui\Stars\StarsIntroActivity.java | ⬜ |
| `menu_sticker_add` | `menu_sticker_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java, org\telegram\ui\Components\StickersDialogs.java (+1) | ⬜ |
| `menu_sticker_add_remix` | `menu_sticker_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_sticker_add_solar` | `menu_sticker_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_sticker_select` | `menu_sticker_select` | raster 1x, 2x, 3x | org\telegram\ui\Components\StickersDialogs.java | ⬜ |
| `menu_storage_path` | `menu_storage_path` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_storage_path_remix` | `menu_storage_path_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_storage_path_solar` | `menu_storage_path_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_stream_comments_24` | `menu_stream_comments_24` | vector | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `menu_stream_pip` | `menu_stream_pip` | vector | org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_tag_delete` | `menu_tag_delete` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_tag_delete_remix` | `menu_tag_delete_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_delete_solar` | `menu_tag_delete_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_edit` | `menu_tag_edit` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_tag_edit_remix` | `menu_tag_edit_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_edit_solar` | `menu_tag_edit_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_filter` | `menu_tag_filter` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_tag_filter_remix` | `menu_tag_filter_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_filter_solar` | `menu_tag_filter_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_plus` | `menu_tag_plus` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_tag_plus_remix` | `menu_tag_plus_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_plus_solar` | `menu_tag_plus_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_rename` | `menu_tag_rename` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\SearchTagsList.java | ⬜ |
| `menu_tag_rename_remix` | `menu_tag_rename_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_tag_rename_solar` | `menu_tag_rename_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_takeoff` | `menu_takeoff` | vector | org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `menu_top_bidders_24` | `menu_top_bidders_24` | vector | org\telegram\ui\Gifts\AuctionJoinSheet.java | ⬜ |
| `menu_topic_add` | `menu_topic_add` | vector | org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `menu_topic_add_30` | `menu_topic_add_30` | vector | org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_transfer` | `menu_transfer` | vector | org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `menu_unlock` | `menu_unlock` | raster 1x, 2x, 3x | org\telegram\ui\Stars\ExplainStarsSheet.java | ⬜ |
| `menu_unsave_story` | `menu_unsave_story` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_unsave_story_remix` | `menu_unsave_story_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_unsave_story_solar` | `menu_unsave_story_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_username_change` | `menu_username_change` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_username_remix` | `menu_username_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_username_set` | `menu_username_set` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `menu_username_set_remix` | `menu_username_set_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_username_set_solar` | `menu_username_set_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_username_solar` | `menu_username_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_verification` | `menu_verification` | vector | org\telegram\ui\Gifts\AuctionWearingSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_video_chromecast` | `menu_video_chromecast` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `menu_video_loop` | `menu_video_loop` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `menu_video_pip` | `menu_video_pip` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `menu_videocall` | `menu_videocall` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java, org\telegram\ui\Components\Premium\boosts\cells\selector\SelectorUserCell.java | ⬜ |
| `menu_views_reactions` | `menu_views_reactions` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_reactions2` | `menu_views_reactions2` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_reactions3` | `menu_views_reactions3` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_recent` | `menu_views_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\SelfStoryViewsPage.java, org\telegram\ui\web\WebActionBar.java | ⬜ |
| `menu_views_recent2` | `menu_views_recent2` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_recent3` | `menu_views_recent3` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_reposts` | `menu_views_reposts` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_views_reposts3` | `menu_views_reposts3` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `menu_wallet` | `menu_wallet` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java, org\telegram\ui\Components\ItemOptions.java | ⬜ |
| `menu_wallet_remix` | `menu_wallet_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_wallet_solar` | `menu_wallet_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_wear` | `menu_wear` | vector | org\telegram\ui\Components\Premium\PremiumFeatureBottomSheet.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `menu_website` | `menu_website` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `menu_website_remix` | `menu_website_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_website_solar` | `menu_website_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_welcome_messages` | `menu_welcome_messages` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatEditActivity.java | ⬜ |
| `menu_welcome_messages_remix` | `menu_welcome_messages_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `menu_welcome_messages_solar` | `menu_welcome_messages_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `message_remix` | `message_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `message_solar` | `message_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `mini_attach` | `mini_attach` | vector | org\telegram\ui\ChatActivity.java | ⬜ |
| `mini_boost_badge` | `mini_boost_badge` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\boosts\cells\statistics\CounterDrawable.java, org\telegram\ui\Stars\StarsIntroActivity.java | ⬜ |
| `mini_boost_button` | `mini_boost_button` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\ButtonWithCounterView.java | ⬜ |
| `mini_boost_profile_badge` | `mini_boost_profile_badge` | raster 1x, 2x, 3x | org\telegram\ui\ChatUsersActivity.java, org\telegram\ui\Components\Premium\boosts\BoostCounterSpan.java | ⬜ |
| `mini_boost_profile_badge2` | `mini_boost_profile_badge2` | raster 1x, 2x, 3x | org\telegram\ui\ChatUsersActivity.java, org\telegram\ui\Components\Premium\boosts\BoostCounterSpan.java | ⬜ |
| `mini_boost_remove` | `mini_boost_remove` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\boosts\ReassignBoostBottomSheet.java | ⬜ |
| `mini_call_in_16` | `mini_call_in_16` | vector | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\CallLogActivity.java | ⬜ |
| `mini_call_join` | `mini_call_join` | vector | org\telegram\ui\Cells\ChatMessageCell.java | ⬜ |
| `mini_call_out_16` | `mini_call_out_16` | vector | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\CallLogActivity.java | ⬜ |
| `mini_casting_fill` | `mini_casting_fill` | vector | org\telegram\ui\ChooseQualityLayout$QualityIcon.java | ⬜ |
| `mini_checklist_add` | `mini_checklist_add` | vector | org\telegram\ui\Cells\ChatActionCell.java | ⬜ |
| `mini_checklist_done` | `mini_checklist_done` | vector | org\telegram\ui\Cells\ChatActionCell.java | ⬜ |
| `mini_checklist_done_outline` | `mini_checklist_done_outline` | vector | org\telegram\ui\MessageSeenView.java | ⬜ |
| `mini_checklist_undone` | `mini_checklist_undone` | vector | org\telegram\ui\Cells\ChatActionCell.java | ⬜ |
| `mini_collage` | `mini_collage` | vector | org\telegram\ui\Stories\recorder\GalleryListView.java | ⬜ |
| `mini_ephemeral_hidden_14` | `mini_ephemeral_hidden_14` | vector | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\MentionCell.java, org\telegram\ui\ChatActivity.java (+3) | ⬜ |
| `mini_ephemeral_hidden_16` | `mini_ephemeral_hidden_16` | vector | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `mini_external_link` | `mini_external_link` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java | ⬜ |
| `mini_forward_story` | `mini_forward_story` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ReactedUserHolderView.java | ⬜ |
| `mini_forwarded` | `mini_forwarded` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `mini_gift` | `mini_gift` | raster 1x, 2x, 3x | org\telegram\ui\Cells\DialogCell.java, org\telegram\ui\Components\Premium\boosts\cells\statistics\GiftedUserCell.java | ⬜ |
| `mini_gift_hidden` | `mini_gift_hidden` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `mini_gift_lock` | `mini_gift_lock` | vector | org\telegram\ui\Gifts\GiftSheet.java | ⬜ |
| `mini_gift_sorting_date` | `mini_gift_sorting_date` | vector | org\telegram\ui\Gifts\ResaleGiftsFragment.java | ⬜ |
| `mini_gift_sorting_num` | `mini_gift_sorting_num` | vector | org\telegram\ui\Gifts\ResaleGiftsFragment.java | ⬜ |
| `mini_gift_sorting_price` | `mini_gift_sorting_price` | vector | org\telegram\ui\Gifts\ResaleGiftsFragment.java | ⬜ |
| `mini_giveaway` | `mini_giveaway` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\boosts\cells\statistics\GiftedUserCell.java | ⬜ |
| `mini_gram_14` | `mini_gram_14` | vector | org\telegram\ui\Gifts\GiftSheet.java | ⬜ |
| `mini_gram_16` | `mini_gram_16` | vector | com\exteragram\messenger\components\SupporterBottomSheet.java, com\exteragram\messenger\pillstack\ui\pills\crypto\GramPill.java, org\telegram\ui\ChannelMonetizationLayout.java (+1) | ⬜ |
| `mini_gram_72` | `mini_gram_72` | vector | org\telegram\ui\ChannelMonetizationLayout.java, org\telegram\ui\Components\Premium\boosts\UserSelectorBottomSheet.java, org\telegram\ui\Stars\GiftOfferSheet.java (+4) | ⬜ |
| `mini_inline_arrow_16` | `mini_inline_arrow_16` | vector | org\telegram\messenger\RichMessageLayout.java | ⬜ |
| `mini_inline_copy_16` | `mini_inline_copy_16` | vector | org\telegram\messenger\RichMessageLayout.java | ⬜ |
| `mini_inline_profile_16` | `mini_inline_profile_16` | vector | org\telegram\messenger\RichMessageLayout.java | ⬜ |
| `mini_inline_switch_16` | `mini_inline_switch_16` | vector | org\telegram\messenger\RichMessageLayout.java | ⬜ |
| `mini_like_filled` | `mini_like_filled` | raster 1x, 2x, 3x | org\telegram\ui\Components\TopicsTabsView.java, org\telegram\ui\Stories\SelfStoriesPreviewView.java | ⬜ |
| `mini_mention_filled_16` | `mini_mention_filled_16` | vector | org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `mini_more_dots` | `mini_more_dots` | raster 1x, 2x, 3x | org\telegram\ui\Cells\StickerEmojiCell.java | ⬜ |
| `mini_quote` | `mini_quote` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\MessageObject.java, org\telegram\messenger\RichMessageLayout.java (+3) | ⬜ |
| `mini_quote_remix` | `mini_quote_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `mini_quote_solar` | `mini_quote_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `mini_replace2` | `mini_replace2` | vector | org\telegram\ui\Components\TranslateAlert3.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `mini_replace_16` | `mini_replace_16` | vector | org\telegram\ui\Components\ThemeSmallPreviewView.java | ⬜ |
| `mini_reply_poll_16` | `mini_reply_poll_16` | vector | org\telegram\ui\Cells\ChatMessageCell.java | ⬜ |
| `mini_reply_user` | `mini_reply_user` | raster 1x, 2x, 3x | org\telegram\messenger\MediaDataController.java, org\telegram\messenger\MessageObject.java, org\telegram\ui\Cells\ChannelRecommendationsCell.java (+1) | ⬜ |
| `mini_repost_story` | `mini_repost_story` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ReactedUserHolderView.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `mini_roll` | `mini_roll` | vector | org\telegram\ui\StakedDiceSheet.java, org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `mini_stats` | `mini_stats` | vector | org\telegram\ui\Stars\StarsIntroActivity.java, org\telegram\ui\TON\TONIntroActivity.java | ⬜ |
| `mini_stats_likes` | `mini_stats_likes` | raster 1x, 2x, 3x | org\telegram\ui\Cells\StatisticPostInfoCell.java | ⬜ |
| `mini_stats_shares` | `mini_stats_shares` | raster 1x, 2x, 3x | org\telegram\ui\Cells\StatisticPostInfoCell.java, org\telegram\ui\Components\ShareAlert.java | ⬜ |
| `mini_switch_lock` | `mini_switch_lock` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChannelRecommendationsCell.java, org\telegram\ui\ChannelColorActivity.java, org\telegram\ui\ChannelMonetizationLayout.java (+9) | ⬜ |
| `mini_topup` | `mini_topup` | vector | org\telegram\ui\Stars\StarsIntroActivity.java, org\telegram\ui\TON\TONIntroActivity.java | ⬜ |
| `mini_user_channels_10` | `mini_user_channels_10` | vector | org\telegram\ui\community\cells\CommunityPendingRequestCell.java | ⬜ |
| `mini_viewonce` | `mini_viewonce` | raster 1x, 2x, 3x | org\telegram\messenger\MessageObject.java | ⬜ |
| `mini_views_likes` | `mini_views_likes` | raster 1x, 2x, 3x | org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `minibubble_in` | `minibubble_in` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ThemesHorizontalListCell.java | ⬜ |
| `minibubble_out` | `minibubble_out` | raster 1x, 2x | org\telegram\ui\Cells\ThemesHorizontalListCell.java | ⬜ |
| `miniplayer_close` | `miniplayer_close` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\FragmentContextView.java (+3) | ⬜ |
| `minithumb_play` | `minithumb_play` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg2_animations` | `msg2_animations` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ThemeActivity.java | ⬜ |
| `msg2_archived_stickers` | `msg2_archived_stickers` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\StickersActivity.java | ⬜ |
| `msg2_ask_question` | `msg2_ask_question` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_autodelete` | `msg2_autodelete` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `msg2_battery` | `msg2_battery` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_battery_remix` | `msg2_battery_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg2_battery_solar` | `msg2_battery_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg2_block2` | `msg2_block2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `msg2_call_earpiece` | `msg2_call_earpiece` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java | ⬜ |
| `msg2_chats_add` | `msg2_chats_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Business\AwayMessagesActivity.java, org\telegram\ui\Business\GreetMessagesActivity.java (+1) | ⬜ |
| `msg2_data` | `msg2_data` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\ui\pills\system\CachePill.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_devices` | `msg2_devices` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\OAuthSheet.java, org\telegram\ui\PrivacySettingsActivity.java (+1) | ⬜ |
| `msg2_discussion` | `msg2_discussion` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_email` | `msg2_email` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `msg2_folder` | `msg2_folder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\ReplaceIconBottomSheet.java, org\telegram\ui\ProfileActivity.java (+1) | ⬜ |
| `msg2_gif` | `msg2_gif` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java | ⬜ |
| `msg2_help` | `msg2_help` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_language` | `msg2_language` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java (+3) | ⬜ |
| `msg2_link2` | `msg2_link2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Premium\boosts\UserSelectorBottomSheet.java, org\telegram\ui\FilterCreateActivity.java | ⬜ |
| `msg2_notifications` | `msg2_notifications` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_permissions` | `msg2_permissions` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PasskeysActivity.java, org\telegram\ui\PrivacySettingsActivity.java | ⬜ |
| `msg2_policy` | `msg2_policy` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_reactions2` | `msg2_reactions2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\StickersActivity.java | ⬜ |
| `msg2_secret` | `msg2_secret` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PrivacySettingsActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg2_smile_status` | `msg2_smile_status` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java, org\telegram\ui\StickersActivity.java | ⬜ |
| `msg2_sticker` | `msg2_sticker` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java, org\telegram\ui\ProfileActivity.java (+1) | ⬜ |
| `msg2_trending` | `msg2_trending` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\StickersActivity.java | ⬜ |
| `msg2_videocall` | `msg2_videocall` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LiteModeSettingsActivity.java | ⬜ |
| `msg_actions` | `msg_actions` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\StickerSetCell.java, org\telegram\ui\FiltersSetupActivity.java | ⬜ |
| `msg_add` | `msg_add` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\RolesActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\IconPacksActivity.java (+8) | ⬜ |
| `msg_add_remix` | `msg_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_add_solar` | `msg_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addbio` | `msg_addbio` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_addbio_remix` | `msg_addbio_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addbio_solar` | `msg_addbio_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addbot` | `msg_addbot` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\AccountsUiHelper.java, org\telegram\ui\ChatActivity.java (+4) | ⬜ |
| `msg_addbot_remix` | `msg_addbot_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addbot_solar` | `msg_addbot_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addcontact` | `msg_addcontact` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChannelAdminLogActivity.java (+4) | ⬜ |
| `msg_addfolder` | `msg_addfolder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java (+1) | ⬜ |
| `msg_addfolder_remix` | `msg_addfolder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addfolder_solar` | `msg_addfolder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addphoto` | `msg_addphoto` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatEditActivity.java, org\telegram\ui\ContactAddActivity.java (+3) | ⬜ |
| `msg_addphoto_remix` | `msg_addphoto_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_addphoto_solar` | `msg_addphoto_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_admin_add` | `msg_admin_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatUsersActivity.java | ⬜ |
| `msg_admin_add_remix` | `msg_admin_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_admin_add_solar` | `msg_admin_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_admins` | `msg_admins` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java (+4) | ⬜ |
| `msg_admins_remix` | `msg_admins_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_admins_solar` | `msg_admins_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_allowspeak` | `msg_allowspeak` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_allowspeak_remix` | `msg_allowspeak_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_allowspeak_solar` | `msg_allowspeak_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_archive` | `msg_archive` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+7) | ⬜ |
| `msg_archive_archive` | `msg_archive_archive` | raster 1x, 2x, 3x | org\telegram\ui\Components\ArchiveHelp.java | ⬜ |
| `msg_archive_hide` | `msg_archive_hide` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java, org\telegram\ui\Components\ArchiveHelp.java (+5) | ⬜ |
| `msg_archive_hide_remix` | `msg_archive_hide_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_archive_hide_solar` | `msg_archive_hide_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_archive_remix` | `msg_archive_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_archive_show` | `msg_archive_show` | raster 1x, 2x, 3x | org\telegram\ui\TopicsFragment.java | ⬜ |
| `msg_archive_solar` | `msg_archive_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_archive_stories` | `msg_archive_stories` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ArchiveHelp.java | ⬜ |
| `msg_arrow_avatar` | `msg_arrow_avatar` | raster 1x, 2x, 3x | org\telegram\ui\Components\AlertsCreator.java, org\telegram\ui\Components\GuardBotReplaceSheet.java, org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java (+3) | ⬜ |
| `msg_arrow_back` | `msg_arrow_back` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChooseSubtitlesLayout.java, com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\components\SearchPhotoPopupWrapper.java (+17) | ⬜ |
| `msg_arrow_forward` | `msg_arrow_forward` | raster 1x, 2x, 3x | org\telegram\ui\Cells\GroupCreateUserCell.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\web\WebActionBar.java | ⬜ |
| `msg_arrowright` | `msg_arrowright` | raster 1x, 2x, 3x | com\exteragram\messenger\components\TranslateBeforeSendWrapper.java, com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java (+17) | ⬜ |
| `msg_ask_question_remix` | `msg_ask_question_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_ask_question_solar` | `msg_ask_question_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete` | `msg_autodelete` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ContentPreviewViewer.java (+1) | ⬜ |
| `msg_autodelete_1d` | `msg_autodelete_1d` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AutoDeletePopupWrapper.java, org\telegram\ui\KeepMediaPopupView.java | ⬜ |
| `msg_autodelete_1d_remix` | `msg_autodelete_1d_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_1d_solar` | `msg_autodelete_1d_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_1m` | `msg_autodelete_1m` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AutoDeletePopupWrapper.java, org\telegram\ui\KeepMediaPopupView.java | ⬜ |
| `msg_autodelete_1m_remix` | `msg_autodelete_1m_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_1m_solar` | `msg_autodelete_1m_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_1w` | `msg_autodelete_1w` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AutoDeletePopupWrapper.java, org\telegram\ui\KeepMediaPopupView.java | ⬜ |
| `msg_autodelete_1w_remix` | `msg_autodelete_1w_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_1w_solar` | `msg_autodelete_1w_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_2d` | `msg_autodelete_2d` | raster 1x, 2x, 3x | org\telegram\ui\KeepMediaPopupView.java | ⬜ |
| `msg_autodelete_badge2` | `msg_autodelete_badge2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\TimerDrawable.java | ⬜ |
| `msg_autodelete_badge2_remix` | `msg_autodelete_badge2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_badge2_solar` | `msg_autodelete_badge2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_remix` | `msg_autodelete_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_autodelete_solar` | `msg_autodelete_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_background` | `msg_background` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ThemeActivity.java (+1) | ⬜ |
| `msg_background_remix` | `msg_background_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_background_solar` | `msg_background_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_bell_mute_remix` | `msg_bell_mute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_bell_mute_solar` | `msg_bell_mute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_block` | `msg_block` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\utils\PluginsWatchdog.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+5) | ⬜ |
| `msg_block2` | `msg_block2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\video\VideoAds.java, org\telegram\ui\AccountFrozenAlert.java (+9) | ⬜ |
| `msg_block2_remix` | `msg_block2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_block2_solar` | `msg_block2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_block_remix` | `msg_block_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_block_solar` | `msg_block_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_blur_linear` | `msg_blur_linear` | raster 1x, 2x, 3x | org\telegram\ui\Components\PhotoFilterView.java | ⬜ |
| `msg_blur_off` | `msg_blur_off` | raster 1x, 2x, 3x | org\telegram\ui\Components\PhotoFilterView.java | ⬜ |
| `msg_blur_radial` | `msg_blur_radial` | raster 1x, 2x, 3x | org\telegram\ui\Components\PhotoFilterView.java | ⬜ |
| `msg_bot` | `msg_bot` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\preferences\utils\SettingsRegistry.java (+7) | ⬜ |
| `msg_bots` | `msg_bots` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\FiltersListBottomSheet.java, org\telegram\ui\MainTabsActivity.java | ⬜ |
| `msg_bots_remix` | `msg_bots_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_bots_solar` | `msg_bots_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_brightness_high` | `msg_brightness_high` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\BrightnessControlCell.java | ⬜ |
| `msg_brightness_high_remix` | `msg_brightness_high_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_brightness_high_solar` | `msg_brightness_high_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_brightness_low` | `msg_brightness_low` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\BrightnessControlCell.java | ⬜ |
| `msg_brightness_low_remix` | `msg_brightness_low_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_brightness_low_solar` | `msg_brightness_low_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calendar` | `msg_calendar` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChannelAdminLogActivity.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_calendar2` | `msg_calendar2` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java (+15) | ⬜ |
| `msg_calendar2_remix` | `msg_calendar2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calendar2_solar` | `msg_calendar2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calendar_remix` | `msg_calendar_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calendar_solar` | `msg_calendar_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_call_bluetooth` | `msg_call_bluetooth` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `msg_call_earpiece` | `msg_call_earpiece` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `msg_call_minimize_shadow` | `msg_call_minimize_shadow` | raster 1x, 2x, 3x | org\telegram\ui\VoIPFragment.java | ⬜ |
| `msg_call_speaker` | `msg_call_speaker` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `msg_callback` | `msg_callback` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_calls` | `msg_calls` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+7) | ⬜ |
| `msg_calls_minimize` | `msg_calls_minimize` | raster 1x, 2x, 3x | org\telegram\ui\Components\voip\GroupCallRenderersContainer.java | ⬜ |
| `msg_calls_regular` | `msg_calls_regular` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_calls_regular_remix` | `msg_calls_regular_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calls_regular_solar` | `msg_calls_regular_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calls_remix` | `msg_calls_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_calls_solar` | `msg_calls_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_camera` | `msg_camera` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ImageUpdater.java, org\telegram\ui\Components\WallpaperUpdater.java | ⬜ |
| `msg_camera_remix` | `msg_camera_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_camera_solar` | `msg_camera_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_cancel` | `msg_cancel` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChooseSubtitlesLayout.java, com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java (+12) | ⬜ |
| `msg_cancel_remix` | `msg_cancel_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_cancel_solar` | `msg_cancel_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel` | `msg_channel` | raster 1x, 2x, 3x | com\exteragram\messenger\feed\FeedChannelActions.java, com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java (+21) | ⬜ |
| `msg_channel_14` | `msg_channel_14` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_14_remix` | `msg_channel_14_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_14_solar` | `msg_channel_14_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_create` | `msg_channel_create` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\DialogsAdapter.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `msg_channel_filled` | `msg_channel_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_channel_hw` | `msg_channel_hw` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_hw_remix` | `msg_channel_hw_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_hw_solar` | `msg_channel_hw_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_ny` | `msg_channel_ny` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_ny_remix` | `msg_channel_ny_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_ny_solar` | `msg_channel_ny_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_remix` | `msg_channel_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_channel_solar` | `msg_channel_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_chats_add_remix` | `msg_chats_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_chats_add_solar` | `msg_chats_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_chats_remove` | `msg_chats_remove` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatEditActivity.java | ⬜ |
| `msg_chats_remove_remix` | `msg_chats_remove_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_chats_remove_solar` | `msg_chats_remove_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_check_s` | `msg_check_s` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ChannelColorActivity.java | ⬜ |
| `msg_clear` | `msg_clear` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CachedMediaLayout.java, org\telegram\ui\ChatActivity.java (+2) | ⬜ |
| `msg_clear_input` | `msg_clear_input` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\CustomPhoneKeyboardView.java | ⬜ |
| `msg_clear_recent` | `msg_clear_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\HashtagHistoryView.java, org\telegram\ui\PhotoPickerActivity.java (+1) | ⬜ |
| `msg_clear_recent_remix` | `msg_clear_recent_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_clear_recent_solar` | `msg_clear_recent_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_clear_remix` | `msg_clear_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_clear_solar` | `msg_clear_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_clearcache` | `msg_clearcache` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java, org\telegram\ui\Components\AlertsCreator.java (+2) | ⬜ |
| `msg_close` | `msg_close` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\DialogsHintCell.java, org\telegram\ui\Cells\StickerSetCell.java (+9) | ⬜ |
| `msg_colors` | `msg_colors` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stars\StarGiftSheet.java, org\telegram\ui\ThemeActivity.java | ⬜ |
| `msg_colors_remix` | `msg_colors_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_colors_solar` | `msg_colors_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contact` | `msg_contact` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_contact_add` | `msg_contact_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CacheChatsExceptionsFragment.java, org\telegram\ui\ChatActivity.java (+9) | ⬜ |
| `msg_contact_add_remix` | `msg_contact_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contact_add_solar` | `msg_contact_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts` | `msg_contacts` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java (+3) | ⬜ |
| `msg_contacts_name` | `msg_contacts_name` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java, org\telegram\ui\ContactsActivity.java | ⬜ |
| `msg_contacts_name_remix` | `msg_contacts_name_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts_name_solar` | `msg_contacts_name_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts_remix` | `msg_contacts_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts_solar` | `msg_contacts_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts_time` | `msg_contacts_time` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertDocumentLayout.java, org\telegram\ui\ContactsActivity.java | ⬜ |
| `msg_contacts_time_remix` | `msg_contacts_time_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_contacts_time_solar` | `msg_contacts_time_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy` | `msg_copy` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\ai\ui\activities\AiHistoryActivity.java, com\exteragram\messenger\ai\ui\activities\EditServiceActivity.java (+30) | ⬜ |
| `msg_copy_filled` | `msg_copy_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\components\QRCodeSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java (+4) | ⬜ |
| `msg_copy_filled_remix` | `msg_copy_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy_filled_solar` | `msg_copy_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy_photo` | `msg_copy_photo` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_copy_photo_remix` | `msg_copy_photo_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy_photo_solar` | `msg_copy_photo_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy_remix` | `msg_copy_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_copy_solar` | `msg_copy_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_current_location` | `msg_current_location` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `msg_current_location_remix` | `msg_current_location_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_current_location_solar` | `msg_current_location_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_customize` | `msg_customize` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AutoDeletePopupWrapper.java, org\telegram\ui\Components\ChatAttachAlertPollLayout.java (+4) | ⬜ |
| `msg_customize_s` | `msg_customize_s` | raster 1x, 2x, 3x | org\telegram\ui\NotificationPermissionDialog.java | ⬜ |
| `msg_data` | `msg_data` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java | ⬜ |
| `msg_data_remix` | `msg_data_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_data_solar` | `msg_data_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete` | `msg_delete` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\AiHistoryActivity.java, com\exteragram\messenger\ai\ui\activities\EditServiceActivity.java, com\exteragram\messenger\ai\ui\activities\RolesActivity.java (+59) | ⬜ |
| `msg_delete_auto` | `msg_delete_auto` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_delete_auto_remix` | `msg_delete_auto_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete_auto_solar` | `msg_delete_auto_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete_filled` | `msg_delete_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\LinkActionView.java | ⬜ |
| `msg_delete_filled_remix` | `msg_delete_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete_filled_solar` | `msg_delete_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete_remix` | `msg_delete_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_delete_solar` | `msg_delete_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_devices_remix` | `msg_devices_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_devices_solar` | `msg_devices_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_disable` | `msg_disable` | raster 1x, 2x, 3x | org\telegram\ui\Components\AutoDeletePopupWrapper.java | ⬜ |
| `msg_discuss` | `msg_discuss` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\ai\ui\activities\AiPreferencesActivity.java, com\exteragram\messenger\ai\ui\activities\ServicesActivity.java (+3) | ⬜ |
| `msg_discuss_remix` | `msg_discuss_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_discuss_solar` | `msg_discuss_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_discussion` | `msg_discussion` | raster 1x, 2x, 3x | com\exteragram\messenger\feed\FeedChannelActions.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\MainPreferencesActivity.java (+8) | ⬜ |
| `msg_discussion_remix` | `msg_discussion_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_discussion_solar` | `msg_discussion_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_download` | `msg_download` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java (+2) | ⬜ |
| `msg_download_remix` | `msg_download_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_download_settings` | `msg_download_settings` | raster 1x, 2x, 3x | org\telegram\ui\DataUsage2Activity.java, org\telegram\ui\community\CommunitySheet.java | ⬜ |
| `msg_download_solar` | `msg_download_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_edit` | `msg_edit` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\ai\ui\activities\RolesActivity.java, com\exteragram\messenger\ai\ui\activities\ServicesActivity.java (+26) | ⬜ |
| `msg_edit_remix` | `msg_edit_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_edit_solar` | `msg_edit_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_email_remix` | `msg_email_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_email_solar` | `msg_email_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_activities` | `msg_emoji_activities` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_activities_remix` | `msg_emoji_activities_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_activities_solar` | `msg_emoji_activities_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_cat` | `msg_emoji_cat` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_cat_remix` | `msg_emoji_cat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_cat_solar` | `msg_emoji_cat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_flags` | `msg_emoji_flags` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_flags_remix` | `msg_emoji_flags_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_flags_solar` | `msg_emoji_flags_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_food` | `msg_emoji_food` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_food_remix` | `msg_emoji_food_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_food_solar` | `msg_emoji_food_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_gem` | `msg_emoji_gem` | vector | org\telegram\ui\Components\EmojiTabsStrip.java, org\telegram\ui\Gifts\AuctionWearingSheet.java | ⬜ |
| `msg_emoji_objects` | `msg_emoji_objects` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_objects_remix` | `msg_emoji_objects_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_objects_solar` | `msg_emoji_objects_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_other` | `msg_emoji_other` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_other_remix` | `msg_emoji_other_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_other_solar` | `msg_emoji_other_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_question` | `msg_emoji_question` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\SessionCell.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `msg_emoji_recent` | `msg_emoji_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\SharedPhotoVideoCell2.java, org\telegram\ui\Components\EmojiTabsStrip.java (+2) | ⬜ |
| `msg_emoji_recent_remix` | `msg_emoji_recent_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_recent_solar` | `msg_emoji_recent_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_smiles` | `msg_emoji_smiles` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java, org\telegram\ui\SelectAnimatedEmojiDialog.java | ⬜ |
| `msg_emoji_stickers` | `msg_emoji_stickers` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java, org\telegram\ui\SelectAnimatedEmojiDialog.java | ⬜ |
| `msg_emoji_travel` | `msg_emoji_travel` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_emoji_travel_remix` | `msg_emoji_travel_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_emoji_travel_solar` | `msg_emoji_travel_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_endcall` | `msg_endcall` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_endcall_remix` | `msg_endcall_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_endcall_solar` | `msg_endcall_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_expand` | `msg_expand` | raster 1x, 2x, 3x | com\exteragram\messenger\drawer\DrawerHeaderView.java, org\telegram\ui\NotificationsCustomSettingsActivity.java | ⬜ |
| `msg_fave` | `msg_fave` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\MainPreferencesActivity.java (+3) | ⬜ |
| `msg_fave_remix` | `msg_fave_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_fave_solar` | `msg_fave_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filehq` | `msg_filehq` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlert.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java (+1) | ⬜ |
| `msg_filehq_remix` | `msg_filehq_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filehq_solar` | `msg_filehq_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_blocked` | `msg_filled_blocked` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SelectAnimatedEmojiDialog.java | ⬜ |
| `msg_filled_blocked_remix` | `msg_filled_blocked_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_blocked_solar` | `msg_filled_blocked_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_calls` | `msg_filled_data_calls` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_calls_remix` | `msg_filled_data_calls_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_calls_solar` | `msg_filled_data_calls_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_files` | `msg_filled_data_files` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\MessageObject.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_files_remix` | `msg_filled_data_files_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_files_solar` | `msg_filled_data_files_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_messages` | `msg_filled_data_messages` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_messages_remix` | `msg_filled_data_messages_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_messages_solar` | `msg_filled_data_messages_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_music` | `msg_filled_data_music` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `msg_filled_data_music_remix` | `msg_filled_data_music_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_music_solar` | `msg_filled_data_music_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_photos` | `msg_filled_data_photos` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java, org\telegram\ui\SaveToGallerySettingsActivity.java | ⬜ |
| `msg_filled_data_photos_remix` | `msg_filled_data_photos_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_photos_solar` | `msg_filled_data_photos_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_received` | `msg_filled_data_received` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_received_remix` | `msg_filled_data_received_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_received_solar` | `msg_filled_data_received_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_sent` | `msg_filled_data_sent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_sent_remix` | `msg_filled_data_sent_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_sent_solar` | `msg_filled_data_sent_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_videos` | `msg_filled_data_videos` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java, org\telegram\ui\SaveToGallerySettingsActivity.java | ⬜ |
| `msg_filled_data_videos_remix` | `msg_filled_data_videos_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_videos_solar` | `msg_filled_data_videos_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_voice` | `msg_filled_data_voice` | raster 1x, 2x, 3x | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_filled_data_voice_remix` | `msg_filled_data_voice_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_data_voice_solar` | `msg_filled_data_voice_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_datausage` | `msg_filled_datausage` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataSettingsActivity.java | ⬜ |
| `msg_filled_datausage_remix` | `msg_filled_datausage_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_datausage_solar` | `msg_filled_datausage_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_general` | `msg_filled_general` | raster 1x, 2x, 3x | org\telegram\ui\Components\Forum\ForumUtilities.java, org\telegram\ui\Components\PullForegroundDrawable.java, org\telegram\ui\Components\TopicsTabsView.java (+1) | ⬜ |
| `msg_filled_general_large` | `msg_filled_general_large` | raster 1x, 2x, 3x | org\telegram\ui\Components\Forum\ForumUtilities.java | ⬜ |
| `msg_filled_lockedrecord` | `msg_filled_lockedrecord` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\RecordControl.java | ⬜ |
| `msg_filled_menu_channels` | `msg_filled_menu_channels` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CacheControlActivity.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `msg_filled_menu_channels_remix` | `msg_filled_menu_channels_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_menu_channels_solar` | `msg_filled_menu_channels_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_menu_groups` | `msg_filled_menu_groups` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CacheControlActivity.java, org\telegram\ui\Components\CommunityAvatarDrawable.java | ⬜ |
| `msg_filled_menu_groups_remix` | `msg_filled_menu_groups_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_menu_groups_solar` | `msg_filled_menu_groups_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_menu_users` | `msg_filled_menu_users` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CacheControlActivity.java | ⬜ |
| `msg_filled_menu_users_remix` | `msg_filled_menu_users_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_menu_users_solar` | `msg_filled_menu_users_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_plus` | `msg_filled_plus` | raster 1x, 2x, 3x | org\telegram\ui\Components\AvatarConstructorFragment.java, org\telegram\ui\Stories\bots\BotPreviewsEditContainer.java | ⬜ |
| `msg_filled_reply_files` | `msg_filled_reply_files` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_reply_files_remix` | `msg_filled_reply_files_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_reply_files_solar` | `msg_filled_reply_files_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_reply_image` | `msg_filled_reply_image` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_reply_image_remix` | `msg_filled_reply_image_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_reply_image_solar` | `msg_filled_reply_image_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_sdcard` | `msg_filled_sdcard` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DataSettingsActivity.java | ⬜ |
| `msg_filled_sdcard_remix` | `msg_filled_sdcard_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_sdcard_solar` | `msg_filled_sdcard_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_shareout` | `msg_filled_shareout` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\browser\Browser.java | ⬜ |
| `msg_filled_shareout_remix` | `msg_filled_shareout_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_shareout_solar` | `msg_filled_shareout_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_storageusage` | `msg_filled_storageusage` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\core\PillRegistry.java, org\telegram\ui\DataSettingsActivity.java | ⬜ |
| `msg_filled_storageusage_remix` | `msg_filled_storageusage_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_storageusage_solar` | `msg_filled_storageusage_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_filled_stories` | `msg_filled_stories` | raster 1x, 2x, 3x | org\telegram\ui\CacheControlActivity.java, org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_filled_unlockedrecord` | `msg_filled_unlockedrecord` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\RecordControl.java | ⬜ |
| `msg_folder_remix` | `msg_folder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folder_solar` | `msg_folder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders` | `msg_folders` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChooseSubtitlesLayout.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\UnknownSourcesBottomSheet.java (+6) | ⬜ |
| `msg_folders_archive` | `msg_folders_archive` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_folders_archive_remix` | `msg_folders_archive_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_archive_solar` | `msg_folders_archive_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_bots` | `msg_folders_bots` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\BotHelpCell.java | ⬜ |
| `msg_folders_bots_remix` | `msg_folders_bots_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_bots_solar` | `msg_folders_bots_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_channels` | `msg_folders_channels` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\MessageObject.java, org\telegram\ui\ActionBar\Theme.java (+1) | ⬜ |
| `msg_folders_channels_remix` | `msg_folders_channels_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_channels_solar` | `msg_folders_channels_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_groups` | `msg_folders_groups` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\MessageObject.java, org\telegram\ui\ActionBar\Theme.java (+4) | ⬜ |
| `msg_folders_groups_remix` | `msg_folders_groups_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_groups_solar` | `msg_folders_groups_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_muted` | `msg_folders_muted` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_folders_muted_remix` | `msg_folders_muted_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_muted_solar` | `msg_folders_muted_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_private` | `msg_folders_private` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\SharedPhotoVideoCell2.java (+1) | ⬜ |
| `msg_folders_private_remix` | `msg_folders_private_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_private_solar` | `msg_folders_private_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_read` | `msg_folders_read` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_folders_read_remix` | `msg_folders_read_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_read_solar` | `msg_folders_read_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_requests` | `msg_folders_requests` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_folders_requests_remix` | `msg_folders_requests_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_folders_requests_solar` | `msg_folders_requests_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_forward` | `msg_forward` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\chats\DoubleTapUtils.java (+8) | ⬜ |
| `msg_forward_replace` | `msg_forward_replace` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MessagePreviewView.java | ⬜ |
| `msg_forward_replace_remix` | `msg_forward_replace_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_forward_replace_solar` | `msg_forward_replace_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gallery` | `msg_gallery` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChannelAdminLogActivity.java (+5) | ⬜ |
| `msg_gallery_locked2` | `msg_gallery_locked2` | raster 1x, 2x, 3x | org\telegram\ui\DialogsActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_gallery_remix` | `msg_gallery_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gallery_solar` | `msg_gallery_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gif` | `msg_gif` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java (+3) | ⬜ |
| `msg_gif_add` | `msg_gif_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ContentPreviewViewer.java | ⬜ |
| `msg_gif_add_remix` | `msg_gif_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gif_add_solar` | `msg_gif_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gif_remix` | `msg_gif_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gif_solar` | `msg_gif_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gift_premium` | `msg_gift_premium` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\BoostsActivity.java, org\telegram\ui\ChannelBoostLayout.java (+2) | ⬜ |
| `msg_gift_premium_remix` | `msg_gift_premium_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_gift_premium_solar` | `msg_gift_premium_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_go_down` | `msg_go_down` | raster 1x, 2x, 3x | org\telegram\ui\ArticleViewer.java | ⬜ |
| `msg_go_up` | `msg_go_up` | raster 1x, 2x, 3x | org\telegram\ui\ArticleViewer.java | ⬜ |
| `msg_groups` | `msg_groups` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\MainPreferencesActivity.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+8) | ⬜ |
| `msg_groups_create` | `msg_groups_create` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Adapters\DialogsAdapter.java, org\telegram\ui\ProfileActivity.java (+2) | ⬜ |
| `msg_groups_remix` | `msg_groups_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_groups_solar` | `msg_groups_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_halfcheck` | `msg_halfcheck` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ChannelColorActivity.java | ⬜ |
| `msg_halfcheck_s` | `msg_halfcheck_s` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_handdown` | `msg_handdown` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_header_draw` | `msg_header_draw` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\ThemePreviewActivity.java | ⬜ |
| `msg_header_draw_remix` | `msg_header_draw_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_header_draw_solar` | `msg_header_draw_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_header_share` | `msg_header_share` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\ThemePreviewActivity.java | ⬜ |
| `msg_header_share_remix` | `msg_header_share_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_header_share_solar` | `msg_header_share_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_help` | `msg_help` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\chat\layouts\ChatActivityChannelButtonsLayout.java (+3) | ⬜ |
| `msg_home` | `msg_home` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\SharedMediaLayout.java (+3) | ⬜ |
| `msg_home_remix` | `msg_home_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_home_solar` | `msg_home_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_hybrid` | `msg_hybrid` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `msg_hybrid_remix` | `msg_hybrid_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_hybrid_solar` | `msg_hybrid_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_info` | `msg_info` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java (+10) | ⬜ |
| `msg_info_filled` | `msg_info_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_info_filled_remix` | `msg_info_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_info_filled_solar` | `msg_info_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_info_remix` | `msg_info_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_info_solar` | `msg_info_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_input_attach2` | `msg_input_attach2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\chat\layouts\ChatActivitySideControlsButtonsLayout.java | ⬜ |
| `msg_input_gift` | `msg_input_gift` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\PrivacyControlActivity.java (+1) | ⬜ |
| `msg_inputarrow` | `msg_inputarrow` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\LoginActivity.java, org\telegram\ui\community\cells\CommunityLinkView.java (+1) | ⬜ |
| `msg_instant` | `msg_instant` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\BottomSheetTabs.java, org\telegram\ui\ActionBar\Theme.java (+2) | ⬜ |
| `msg_instant_link` | `msg_instant_link` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ArticleViewer.java | ⬜ |
| `msg_instant_link_remix` | `msg_instant_link_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_instant_link_solar` | `msg_instant_link_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_instant_remix` | `msg_instant_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_instant_solar` | `msg_instant_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_invited` | `msg_invited` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\GroupCallInvitedCell.java, org\telegram\ui\Components\GroupVoipInviteAlert.java | ⬜ |
| `msg_invited_remix` | `msg_invited_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_invited_solar` | `msg_invited_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_jobtitle` | `msg_jobtitle` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PhonebookShareAlert.java | ⬜ |
| `msg_jobtitle_remix` | `msg_jobtitle_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_jobtitle_solar` | `msg_jobtitle_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_language` | `msg_language` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\AiPreferencesActivity.java, com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java (+7) | ⬜ |
| `msg_language_remix` | `msg_language_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_language_solar` | `msg_language_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_leave` | `msg_leave` | raster 1x, 2x, 3x | com\exteragram\messenger\feed\FeedChannelActions.java, com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java (+6) | ⬜ |
| `msg_leave_remix` | `msg_leave_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_leave_solar` | `msg_leave_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_limit_accounts` | `msg_limit_accounts` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `msg_limit_chats` | `msg_limit_chats` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `msg_limit_folder` | `msg_limit_folder` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `msg_limit_groups` | `msg_limit_groups` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `msg_limit_links` | `msg_limit_links` | raster 1x, 2x, 3x | org\telegram\ui\Business\BusinessLinksActivity.java, org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\FilterCreateActivity.java (+2) | ⬜ |
| `msg_limit_pin` | `msg_limit_pin` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java, org\telegram\ui\Components\Premium\PremiumLockIconView.java, org\telegram\ui\Components\TopicsTabsView.java (+1) | ⬜ |
| `msg_limit_stories` | `msg_limit_stories` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\LimitReachedBottomSheet.java | ⬜ |
| `msg_link` | `msg_link` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\EmojiPacksAlert.java (+11) | ⬜ |
| `msg_link2` | `msg_link2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\IconPackPreviewView.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java (+12) | ⬜ |
| `msg_link2_remix` | `msg_link2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link2_solar` | `msg_link2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link_1` | `msg_link_1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChangeUsernameActivity.java, org\telegram\ui\FilterCreateActivity.java (+2) | ⬜ |
| `msg_link_1_remix` | `msg_link_1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link_1_solar` | `msg_link_1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link_2` | `msg_link_2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChangeUsernameActivity.java, org\telegram\ui\FilterCreateActivity.java (+2) | ⬜ |
| `msg_link_2_remix` | `msg_link_2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link_2_solar` | `msg_link_2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_link_folder` | `msg_link_folder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\FiltersSetupActivity.java | ⬜ |
| `msg_list` | `msg_list` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\utils\SettingsRegistry.java, org\telegram\ui\PhotoPickerActivity.java | ⬜ |
| `msg_list_remix` | `msg_list_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_list_solar` | `msg_list_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_location` | `msg_location` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java (+4) | ⬜ |
| `msg_location_alert` | `msg_location_alert` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `msg_location_alert2` | `msg_location_alert2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `msg_location_alert_remix` | `msg_location_alert_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_location_alert_solar` | `msg_location_alert_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_location_remix` | `msg_location_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_location_solar` | `msg_location_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_log` | `msg_log` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java (+2) | ⬜ |
| `msg_log_remix` | `msg_log_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_log_solar` | `msg_log_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_map` | `msg_map` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java (+3) | ⬜ |
| `msg_map_remix` | `msg_map_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_map_solar` | `msg_map_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_map_type` | `msg_map_type` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java, org\telegram\ui\LocationActivity.java | ⬜ |
| `msg_map_type_remix` | `msg_map_type_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_map_type_solar` | `msg_map_type_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_markread` | `msg_markread` | raster 1x, 2x, 3x | com\exteragram\messenger\feed\ui\FeedActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\NotificationsController.java (+3) | ⬜ |
| `msg_markread_remix` | `msg_markread_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_markread_solar` | `msg_markread_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_markunread` | `msg_markunread` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java (+4) | ⬜ |
| `msg_markunread_remix` | `msg_markunread_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_markunread_solar` | `msg_markunread_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mask` | `msg_mask` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_mask_remix` | `msg_mask_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mask_solar` | `msg_mask_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_maxvideo` | `msg_maxvideo` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_media` | `msg_media` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\IconPackPreviewView.java (+8) | ⬜ |
| `msg_media_remix` | `msg_media_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_media_solar` | `msg_media_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_members_list2` | `msg_members_list2` | raster 1x, 2x, 3x | org\telegram\ui\NotificationPermissionDialog.java | ⬜ |
| `msg_mention` | `msg_mention` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\AvatarPreviewer.java, org\telegram\ui\ChatActivity.java (+2) | ⬜ |
| `msg_menu_stories` | `msg_menu_stories` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\NotificationsSettingsActivity.java, org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `msg_menu_stories_remix` | `msg_menu_stories_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_menu_stories_solar` | `msg_menu_stories_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_message` | `msg_message` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CalendarActivity.java, org\telegram\ui\ChatActivity.java (+9) | ⬜ |
| `msg_message_remix` | `msg_message_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_message_s` | `msg_message_s` | raster 1x, 2x, 3x | org\telegram\ui\NotificationPermissionDialog.java | ⬜ |
| `msg_message_solar` | `msg_message_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_addemoji` | `msg_mini_addemoji` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_mini_addstory` | `msg_mini_addstory` | raster 1x, 2x, 3x | org\telegram\ui\Stories\DialogStoriesCell.java | ⬜ |
| `msg_mini_arrow_mediabold` | `msg_mini_arrow_mediabold` | raster 1x, 2x, 3x | org\telegram\ui\ChooseDownloadQualityLayout.java, org\telegram\ui\FilteredSearchView.java | ⬜ |
| `msg_mini_arrow_mediathin` | `msg_mini_arrow_mediathin` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\icons\ui\components\InstallIconPackBottomSheet.java, com\exteragram\messenger\plugins\ui\components\InstallPluginBottomSheet.java (+1) | ⬜ |
| `msg_mini_autodelete` | `msg_mini_autodelete` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\TimerDrawable.java | ⬜ |
| `msg_mini_autodelete_empty` | `msg_mini_autodelete_empty` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\TimerDrawable.java | ⬜ |
| `msg_mini_autodelete_empty_remix` | `msg_mini_autodelete_empty_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_autodelete_empty_solar` | `msg_mini_autodelete_empty_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_autodelete_remix` | `msg_mini_autodelete_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_autodelete_solar` | `msg_mini_autodelete_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_autodelete_timer` | `msg_mini_autodelete_timer` | raster 1x, 2x, 3x | org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_mini_bomb` | `msg_mini_bomb` | raster 1x, 2x, 3x | org\telegram\ui\Stories\StoriesUtilities.java | ⬜ |
| `msg_mini_checks` | `msg_mini_checks` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ReactedUserHolderView.java, org\telegram\ui\MessageSeenView.java | ⬜ |
| `msg_mini_close_tooltip` | `msg_mini_close_tooltip` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\HintView.java, org\telegram\ui\Stories\recorder\HintView2.java | ⬜ |
| `msg_mini_customize` | `msg_mini_customize` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Business\BusinessBotButton.java, org\telegram\ui\Components\EmojiView.java (+1) | ⬜ |
| `msg_mini_customize_remix` | `msg_mini_customize_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_customize_solar` | `msg_mini_customize_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_download` | `msg_mini_download` | raster 1x, 2x, 3x | org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_mini_fireoff` | `msg_mini_fireoff` | raster 1x, 2x, 3x | org\telegram\ui\UsersSelectActivity.java | ⬜ |
| `msg_mini_fireon` | `msg_mini_fireon` | raster 1x, 2x, 3x | org\telegram\ui\UsersSelectActivity.java | ⬜ |
| `msg_mini_forumarrow` | `msg_mini_forumarrow` | raster 1x, 2x, 3x | org\telegram\messenger\AndroidUtilities.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\DialogCell.java (+2) | ⬜ |
| `msg_mini_lock2` | `msg_mini_lock2` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\DialogCell.java, org\telegram\ui\Cells\GroupCreateUserCell.java (+9) | ⬜ |
| `msg_mini_lock3` | `msg_mini_lock3` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\FloatingToolbar.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\ItemOptions.java (+6) | ⬜ |
| `msg_mini_lockedemoji` | `msg_mini_lockedemoji` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiTabsStrip.java | ⬜ |
| `msg_mini_premiumlock` | `msg_mini_premiumlock` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\PremiumLockIconView.java, org\telegram\ui\Components\SenderSelectPopup.java | ⬜ |
| `msg_mini_qr` | `msg_mini_qr` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SessionsActivity.java | ⬜ |
| `msg_mini_qr_remix` | `msg_mini_qr_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_qr_solar` | `msg_mini_qr_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_replystory` | `msg_mini_replystory` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\DialogCell.java | ⬜ |
| `msg_mini_replystory2` | `msg_mini_replystory2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\StoriesUtilities.java | ⬜ |
| `msg_mini_replystory_remix` | `msg_mini_replystory_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_replystory_solar` | `msg_mini_replystory_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mini_stickerstar` | `msg_mini_stickerstar` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\PremiumLockIconView.java | ⬜ |
| `msg_mini_topicarrow` | `msg_mini_topicarrow` | raster 1x, 2x, 3x | com\exteragram\messenger\components\QRCodeSheet.java | ⬜ |
| `msg_mini_upload` | `msg_mini_upload` | raster 1x, 2x, 3x | org\telegram\ui\DataUsage2Activity.java | ⬜ |
| `msg_minvideo` | `msg_minvideo` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_msgbubble` | `msg_msgbubble` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `msg_msgbubble2` | `msg_msgbubble2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `msg_msgbubble2_remix` | `msg_msgbubble2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_msgbubble2_solar` | `msg_msgbubble2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_msgbubble3` | `msg_msgbubble3` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Delegates\MemberRequestsDelegate.java, org\telegram\ui\GroupCallActivity.java (+2) | ⬜ |
| `msg_msgbubble3_remix` | `msg_msgbubble3_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_msgbubble3_solar` | `msg_msgbubble3_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute` | `msg_mute` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java (+6) | ⬜ |
| `msg_mute_1h` | `msg_mute_1h` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java | ⬜ |
| `msg_mute_1h_remix` | `msg_mute_1h_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute_1h_solar` | `msg_mute_1h_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute_period` | `msg_mute_period` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java | ⬜ |
| `msg_mute_period_remix` | `msg_mute_period_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute_period_solar` | `msg_mute_period_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute_remix` | `msg_mute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_mute_solar` | `msg_mute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_newphone` | `msg_newphone` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppearancePreferencesActivity.java, org\telegram\ui\LogoutActivity.java | ⬜ |
| `msg_newphone_remix` | `msg_newphone_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_newphone_solar` | `msg_newphone_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_noise_off` | `msg_noise_off` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_noise_off_remix` | `msg_noise_off_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_noise_off_solar` | `msg_noise_off_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_noise_on` | `msg_noise_on` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_noise_on_remix` | `msg_noise_on_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_noise_on_solar` | `msg_noise_on_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_notifications` | `msg_notifications` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\car\HomeScreen.java, org\telegram\ui\ChatActivity.java (+1) | ⬜ |
| `msg_notifications_remix` | `msg_notifications_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_notifications_solar` | `msg_notifications_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_notspam` | `msg_notspam` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChannelAdminLogActivity.java | ⬜ |
| `msg_notspam_remix` | `msg_notspam_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_notspam_solar` | `msg_notspam_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_online` | `msg_online` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\iv\ChatAttachAlertRichLayout.java (+1) | ⬜ |
| `msg_online_remix` | `msg_online_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_online_solar` | `msg_online_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_openin` | `msg_openin` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ReverseImageSearchSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\InstallPluginBottomSheet.java (+10) | ⬜ |
| `msg_openprofile` | `msg_openprofile` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\AiPreferencesActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\AvatarPreviewer.java (+15) | ⬜ |
| `msg_openprofile_remix` | `msg_openprofile_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_openprofile_solar` | `msg_openprofile_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_other_new_filled` | `msg_other_new_filled` | raster 1x, 2x, 3x | org\telegram\ui\FilterCreateActivity.java | ⬜ |
| `msg_other_new_filled_text` | `msg_other_new_filled_text` | raster 1x, 2x, 3x | org\telegram\ui\FilterCreateActivity.java | ⬜ |
| `msg_other_new_outline` | `msg_other_new_outline` | raster 1x, 2x, 3x | org\telegram\ui\FilterCreateActivity.java | ⬜ |
| `msg_palette` | `msg_palette` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\picker\IconPickerView.java, org\telegram\ui\ThemeActivity.java (+1) | ⬜ |
| `msg_panel_clear` | `msg_panel_clear` | raster 1x, 2x, 3x | org\telegram\ui\Cells\AdminedChannelCell.java | ⬜ |
| `msg_panel_reply_remix` | `msg_panel_reply_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_panel_reply_solar` | `msg_panel_reply_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_address` | `msg_payment_address` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PaymentFormActivity.java | ⬜ |
| `msg_payment_card` | `msg_payment_card` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java, org\telegram\ui\ChatActivity.java (+1) | ⬜ |
| `msg_payment_card_remix` | `msg_payment_card_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_card_solar` | `msg_payment_card_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_delivery` | `msg_payment_delivery` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PaymentFormActivity.java | ⬜ |
| `msg_payment_delivery_remix` | `msg_payment_delivery_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_delivery_solar` | `msg_payment_delivery_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_provider` | `msg_payment_provider` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PaymentFormActivity.java | ⬜ |
| `msg_payment_provider_remix` | `msg_payment_provider_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_payment_provider_solar` | `msg_payment_provider_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_permissions` | `msg_permissions` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\UnknownSourcesBottomSheet.java, org\telegram\ui\ChatActivity.java (+7) | ⬜ |
| `msg_permissions_remix` | `msg_permissions_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_permissions_solar` | `msg_permissions_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_back` | `msg_photo_back` | raster 1x, 2x, 3x | org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `msg_photo_blur` | `msg_photo_blur` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PhotoFilterView.java | ⬜ |
| `msg_photo_blur_remix` | `msg_photo_blur_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_blur_solar` | `msg_photo_blur_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_crop` | `msg_photo_crop` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_crop_remix` | `msg_photo_crop_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_crop_solar` | `msg_photo_crop_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_cropfix` | `msg_photo_cropfix` | raster 1x, 2x, 3x | org\telegram\ui\Components\Crop\CropRotationWheel.java | ⬜ |
| `msg_photo_curve` | `msg_photo_curve` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PhotoFilterView.java | ⬜ |
| `msg_photo_curve_remix` | `msg_photo_curve_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_curve_solar` | `msg_photo_curve_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_draw_remix` | `msg_photo_draw_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_draw_solar` | `msg_photo_draw_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_flip` | `msg_photo_flip` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Crop\CropRotationWheel.java | ⬜ |
| `msg_photo_flip_remix` | `msg_photo_flip_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_flip_solar` | `msg_photo_flip_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_rotate` | `msg_photo_rotate` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Crop\CropRotationWheel.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_photo_rotate_remix` | `msg_photo_rotate_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_rotate_solar` | `msg_photo_rotate_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_settings` | `msg_photo_settings` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PhotoFilterView.java, org\telegram\ui\Stories\recorder\PreviewButtons.java | ⬜ |
| `msg_photo_settings_remix` | `msg_photo_settings_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_settings_solar` | `msg_photo_settings_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_sticker` | `msg_photo_sticker` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\PreviewButtons.java | ⬜ |
| `msg_photo_switch2` | `msg_photo_switch2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\RecordControl.java | ⬜ |
| `msg_photo_text2` | `msg_photo_text2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\recorder\PreviewButtons.java | ⬜ |
| `msg_photo_text2_remix` | `msg_photo_text2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_text2_solar` | `msg_photo_text2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photo_text_framed` | `msg_photo_text_framed` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `msg_photo_text_framed2` | `msg_photo_text_framed2` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `msg_photo_text_framed3` | `msg_photo_text_framed3` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `msg_photo_text_regular` | `msg_photo_text_regular` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `msg_photoeditor` | `msg_photoeditor` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_photos` | `msg_photos` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\IconPackPreviewView.java, com\exteragram\messenger\icons\ui\components\ReplaceIconBottomSheet.java (+3) | ⬜ |
| `msg_photos_remix` | `msg_photos_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_photos_solar` | `msg_photos_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin` | `msg_pin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\components\IconPackPreviewView.java, com\exteragram\messenger\plugins\ui\components\PluginCell.java (+11) | ⬜ |
| `msg_pin_code` | `msg_pin_code` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PasscodeActivity.java | ⬜ |
| `msg_pin_code_remix` | `msg_pin_code_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin_code_solar` | `msg_pin_code_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin_filled` | `msg_pin_filled` | raster 1x, 2x, 3x | org\telegram\ui\Components\voip\GroupCallRenderersContainer.java | ⬜ |
| `msg_pin_mini` | `msg_pin_mini` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\SharedPhotoVideoCell2.java (+2) | ⬜ |
| `msg_pin_mini_remix` | `msg_pin_mini_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin_mini_solar` | `msg_pin_mini_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin_remix` | `msg_pin_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pin_solar` | `msg_pin_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pinnedlist` | `msg_pinnedlist` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_pinnedlist_remix` | `msg_pinnedlist_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pinnedlist_solar` | `msg_pinnedlist_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_played` | `msg_played` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CachedMediaLayout.java, org\telegram\ui\Components\MessagePrivateSeenView.java (+1) | ⬜ |
| `msg_played_remix` | `msg_played_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_played_solar` | `msg_played_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_plugins` | `msg_plugins` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\MainPreferencesActivity.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+4) | ⬜ |
| `msg_plugins_remix` | `msg_plugins_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_plugins_solar` | `msg_plugins_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_policy` | `msg_policy` | raster 1x, 2x, 3x | com\exteragram\messenger\adblock\ui\AdBlockMenuItem.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatUsersActivity.java | ⬜ |
| `msg_policy_remix` | `msg_policy_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_policy_solar` | `msg_policy_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pollstop` | `msg_pollstop` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `msg_pollstop_remix` | `msg_pollstop_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_pollstop_solar` | `msg_pollstop_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_premium_ads` | `msg_premium_ads` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_avatar` | `msg_premium_avatar` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_badge` | `msg_premium_badge` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_emoji` | `msg_premium_emoji` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_icons` | `msg_premium_icons` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_limits` | `msg_premium_limits` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_liststar` | `msg_premium_liststar` | raster 1x, 2x, 3x | com\exteragram\messenger\preferences\appearance\components\ChatListPreviewCell.java, org\telegram\ui\Cells\ChatMessageCell.java, org\telegram\ui\Cells\GroupCallUserCell.java (+11) | ⬜ |
| `msg_premium_lock2` | `msg_premium_lock2` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\PremiumLockIconView.java | ⬜ |
| `msg_premium_normal` | `msg_premium_normal` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\PremiumGradient.java, org\telegram\ui\UnlockPremiumView.java | ⬜ |
| `msg_premium_prolfilestar` | `msg_premium_prolfilestar` | raster 1x, 2x, 3x | org\telegram\ui\Components\SelectSendAsPremiumHintBulletinLayout.java, org\telegram\ui\PremiumFeatureCell.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_premium_reactions` | `msg_premium_reactions` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_speed` | `msg_premium_speed` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_stickers` | `msg_premium_stickers` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_translate` | `msg_premium_translate` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_uploads` | `msg_premium_uploads` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_premium_voice` | `msg_premium_voice` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `msg_psa` | `msg_psa` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_psa_remix` | `msg_psa_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_psa_solar` | `msg_psa_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_qr_mini` | `msg_qr_mini` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\QrActivity.java | ⬜ |
| `msg_qrcode` | `msg_qrcode` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\utils\chats\MainMenuHelper.java (+7) | ⬜ |
| `msg_qrcode_mini_remix` | `msg_qrcode_mini_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_qrcode_mini_solar` | `msg_qrcode_mini_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_qrcode_remix` | `msg_qrcode_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_qrcode_solar` | `msg_qrcode_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rate_down` | `msg_rate_down` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_rate_down_remix` | `msg_rate_down_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rate_down_solar` | `msg_rate_down_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rate_up` | `msg_rate_up` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_rate_up_remix` | `msg_rate_up_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rate_up_solar` | `msg_rate_up_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reactions` | `msg_reactions` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\ReactedUserHolderView.java, org\telegram\ui\Components\AudioPlayerAlert.java (+2) | ⬜ |
| `msg_reactions2` | `msg_reactions2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\chats\DoubleTapUtils.java, com\exteragram\messenger\utils\chats\SwipeAction.java (+1) | ⬜ |
| `msg_reactions_expand` | `msg_reactions_expand` | raster 1x, 2x, 3x | org\telegram\ui\Components\ReactionsContainerLayout.java | ⬜ |
| `msg_reactions_filled` | `msg_reactions_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ReactionTabHolderView.java | ⬜ |
| `msg_reactions_filled_remix` | `msg_reactions_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reactions_filled_solar` | `msg_reactions_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reactions_remix` | `msg_reactions_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reactions_solar` | `msg_reactions_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rear_camera` | `msg_rear_camera` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `msg_rear_camera_remix` | `msg_rear_camera_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_rear_camera_solar` | `msg_rear_camera_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_recent` | `msg_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\utils\PluginsWatchdog.java (+2) | ⬜ |
| `msg_recent_remix` | `msg_recent_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_recent_solar` | `msg_recent_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_remove` | `msg_remove` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatLinkActivity.java, org\telegram\ui\ChatUsersActivity.java (+5) | ⬜ |
| `msg_remove_remix` | `msg_remove_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_remove_solar` | `msg_remove_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_removefolder` | `msg_removefolder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SharedMediaLayout.java, org\telegram\ui\Gifts\ProfileGiftsContainer.java | ⬜ |
| `msg_removefolder_remix` | `msg_removefolder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_removefolder_solar` | `msg_removefolder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reorder` | `msg_reorder` | raster 1x, 2x, 3x | org\telegram\ui\StickersActivity.java | ⬜ |
| `msg_repeat` | `msg_repeat` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\chats\DoubleTapUtils.java, com\exteragram\messenger\utils\chats\SwipeAction.java (+1) | ⬜ |
| `msg_repeat_remix` | `msg_repeat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_repeat_solar` | `msg_repeat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_replace` | `msg_replace` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\ChatAttachAlertPollLayout.java (+3) | ⬜ |
| `msg_replace_remix` | `msg_replace_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_replace_solar` | `msg_replace_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reply_remix` | `msg_reply_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reply_small` | `msg_reply_small` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\utils\ui\ForwardsCountUiHelper.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_reply_small_remix` | `msg_reply_small_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reply_small_solar` | `msg_reply_small_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reply_solar` | `msg_reply_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report` | `msg_report` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\OtherPreferencesActivity.java, org\telegram\ui\ChatActivity.java (+7) | ⬜ |
| `msg_report_drugs` | `msg_report_drugs` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_drugs_remix` | `msg_report_drugs_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_drugs_solar` | `msg_report_drugs_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_fake` | `msg_report_fake` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_fake_remix` | `msg_report_fake_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_fake_solar` | `msg_report_fake_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_other` | `msg_report_other` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_other_remix` | `msg_report_other_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_other_solar` | `msg_report_other_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_personal` | `msg_report_personal` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_personal_remix` | `msg_report_personal_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_personal_solar` | `msg_report_personal_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_violence` | `msg_report_violence` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_violence_remix` | `msg_report_violence_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_violence_solar` | `msg_report_violence_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_xxx` | `msg_report_xxx` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `msg_report_xxx_remix` | `msg_report_xxx_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_report_xxx_solar` | `msg_report_xxx_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_requests` | `msg_requests` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatEditActivity.java, org\telegram\ui\Delegates\MemberRequestsDelegate.java (+1) | ⬜ |
| `msg_reset` | `msg_reset` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\ui\PillStackPreferencesActivity.java (+7) | ⬜ |
| `msg_reset_remix` | `msg_reset_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_reset_solar` | `msg_reset_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_retry` | `msg_retry` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\ui\pills\crypto\RatePill.java (+8) | ⬜ |
| `msg_retry_remix` | `msg_retry_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_retry_solar` | `msg_retry_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_round_cancel_m` | `msg_round_cancel_m` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_round_file_remix` | `msg_round_file_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_round_file_s` | `msg_round_file_s` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\bots\BotDownloads.java | ⬜ |
| `msg_round_file_solar` | `msg_round_file_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_round_gif_m` | `msg_round_gif_m` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_round_load_m` | `msg_round_load_m` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_round_pause_m` | `msg_round_pause_m` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Stories\recorder\RecordControl.java | ⬜ |
| `msg_round_play_m` | `msg_round_play_m` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_satellite` | `msg_satellite` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertLocationLayout.java (+1) | ⬜ |
| `msg_satellite_remix` | `msg_satellite_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_satellite_solar` | `msg_satellite_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_save_story` | `msg_save_story` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_saved` | `msg_saved` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java, com\exteragram\messenger\utils\chats\DoubleTapUtils.java (+7) | ⬜ |
| `msg_saved_remix` | `msg_saved_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_saved_solar` | `msg_saved_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_screencast` | `msg_screencast` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_screencast_off` | `msg_screencast_off` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_screencast_off_remix` | `msg_screencast_off_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_screencast_off_solar` | `msg_screencast_off_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_screencast_remix` | `msg_screencast_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_screencast_solar` | `msg_screencast_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_search` | `msg_search` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\AvatarPreviewer.java, org\telegram\ui\Cells\SessionCell.java (+8) | ⬜ |
| `msg_search_remix` | `msg_search_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_search_solar` | `msg_search_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret` | `msg_secret` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java, org\telegram\ui\ProfileActivity.java (+1) | ⬜ |
| `msg_secret_14` | `msg_secret_14` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_14_remix` | `msg_secret_14_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_14_solar` | `msg_secret_14_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_hw` | `msg_secret_hw` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_hw_remix` | `msg_secret_hw_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_hw_solar` | `msg_secret_hw_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_remix` | `msg_secret_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_secret_solar` | `msg_secret_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_seen` | `msg_seen` | raster 1x, 2x, 3x | org\telegram\ui\Components\MessagePrivateSeenView.java, org\telegram\ui\MessageSeenView.java | ⬜ |
| `msg_select` | `msg_select` | raster 1x, 2x, 3x | com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\IconPacksEditorActivity.java (+11) | ⬜ |
| `msg_select_between_remix` | `msg_select_between_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_select_between_solar` | `msg_select_between_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_select_remix` | `msg_select_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_select_solar` | `msg_select_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_send` | `msg_send` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\AiResponseAlert.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java (+4) | ⬜ |
| `msg_send_remix` | `msg_send_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_send_solar` | `msg_send_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_sendfile` | `msg_sendfile` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `msg_sendfile_remix` | `msg_sendfile_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_sendfile_solar` | `msg_sendfile_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_settings` | `msg_settings` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\feed\ui\FeedActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java (+15) | ⬜ |
| `msg_settings_old` | `msg_settings_old` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\ui\pills\weather\WeatherSettingsActivity.java, com\exteragram\messenger\preferences\appearance\AppNavigationPreferencesActivity.java (+3) | ⬜ |
| `msg_settings_premium` | `msg_settings_premium` | raster 1x, 2x, 3x | org\telegram\ui\ArchiveSettingsActivity.java, org\telegram\ui\Cells\GroupCreateUserCell.java, org\telegram\ui\Components\EmojiPacksAlert.java (+6) | ⬜ |
| `msg_settings_remix` | `msg_settings_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_settings_solar` | `msg_settings_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_share` | `msg_share` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\IconPacksActivity.java (+22) | ⬜ |
| `msg_share_filled` | `msg_share_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\components\QRCodeSheet.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CallLogActivity.java (+2) | ⬜ |
| `msg_share_filled_remix` | `msg_share_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_share_filled_solar` | `msg_share_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_share_remix` | `msg_share_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_share_solar` | `msg_share_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_shareout` | `msg_shareout` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java (+5) | ⬜ |
| `msg_shareout_remix` | `msg_shareout_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_shareout_solar` | `msg_shareout_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_silent` | `msg_silent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_silent_remix` | `msg_silent_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_silent_solar` | `msg_silent_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_smile_status` | `msg_smile_status` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ContentPreviewViewer.java | ⬜ |
| `msg_speed` | `msg_speed` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_speed_0_2` | `msg_speed_0_2` | raster 1x, 2x, 3x | org\telegram\ui\ChooseSpeedLayout.java, org\telegram\ui\SpeedButtonsLayout.java | ⬜ |
| `msg_speed_fast` | `msg_speed_fast` | raster 1x, 2x, 3x | org\telegram\ui\ChooseSpeedLayout.java, org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java (+1) | ⬜ |
| `msg_speed_medium` | `msg_speed_medium` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java | ⬜ |
| `msg_speed_normal` | `msg_speed_normal` | raster 1x, 2x, 3x | org\telegram\ui\ChooseSpeedLayout.java, org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java (+1) | ⬜ |
| `msg_speed_remix` | `msg_speed_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_speed_slow` | `msg_speed_slow` | raster 1x, 2x, 3x | org\telegram\ui\ChooseSpeedLayout.java, org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java (+1) | ⬜ |
| `msg_speed_solar` | `msg_speed_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_speed_superfast` | `msg_speed_superfast` | raster 1x, 2x, 3x | org\telegram\ui\ChooseSpeedLayout.java, org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java (+1) | ⬜ |
| `msg_speed_veryfast` | `msg_speed_veryfast` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\FragmentContextView.java | ⬜ |
| `msg_spoiler` | `msg_spoiler` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `msg_spoiler_off` | `msg_spoiler_off` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java, org\telegram\ui\PhotoViewer.java, org\telegram\ui\iv\RichMediaCell.java | ⬜ |
| `msg_stats` | `msg_stats` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java (+6) | ⬜ |
| `msg_stats_remix` | `msg_stats_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stats_solar` | `msg_stats_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stealth_25min` | `msg_stealth_25min` | raster 1x, 2x, 3x | org\telegram\ui\Stories\StealthModeAlert.java | ⬜ |
| `msg_stealth_5min` | `msg_stealth_5min` | raster 1x, 2x, 3x | org\telegram\ui\Stories\StealthModeAlert.java | ⬜ |
| `msg_stealth_locked` | `msg_stealth_locked` | raster 1x, 2x, 3x | org\telegram\ui\DialogsActivity.java | ⬜ |
| `msg_sticker` | `msg_sticker` | raster 1x, 2x, 3x | com\exteragram\messenger\components\ChatActivityEnterViewStaticIconView.java, com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\export\ui\ChatViewer.java (+7) | ⬜ |
| `msg_sticker_remix` | `msg_sticker_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_sticker_solar` | `msg_sticker_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_add` | `msg_stories_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `msg_stories_add_remix` | `msg_stories_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_add_solar` | `msg_stories_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_archive` | `msg_stories_archive` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `msg_stories_archive_remix` | `msg_stories_archive_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_archive_solar` | `msg_stories_archive_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_caption` | `msg_stories_caption` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_closefriends` | `msg_stories_closefriends` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\SharedPhotoVideoCell2.java (+1) | ⬜ |
| `msg_stories_closefriends_remix` | `msg_stories_closefriends_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_closefriends_solar` | `msg_stories_closefriends_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_link` | `msg_stories_link` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_myhide` | `msg_stories_myhide` | raster 1x, 2x, 3x | org\telegram\ui\Stories\SelfStoryViewsPage.java | ⬜ |
| `msg_stories_order` | `msg_stories_order` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_save` | `msg_stories_save` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AudioPlayerAlert.java, org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_saved` | `msg_stories_saved` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\NotificationsCustomSettingsActivity.java | ⬜ |
| `msg_stories_saved_remix` | `msg_stories_saved_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_saved_solar` | `msg_stories_saved_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_stealth` | `msg_stories_stealth` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_stealth2` | `msg_stories_stealth2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\Stories\PeerStoriesView.java (+1) | ⬜ |
| `msg_stories_stealth_remix` | `msg_stories_stealth_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_stealth_solar` | `msg_stories_stealth_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_stories_timer` | `msg_stories_timer` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_stories_views` | `msg_stories_views` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\FeaturesPageView.java | ⬜ |
| `msg_switch` | `msg_switch` | raster 1x, 2x, 3x | org\telegram\ui\Cells\UserCell.java | ⬜ |
| `msg_tabs_mic1` | `msg_tabs_mic1` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareAlert.java | ⬜ |
| `msg_tabs_mic2` | `msg_tabs_mic2` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareAlert.java | ⬜ |
| `msg_text_check` | `msg_text_check` | raster 1x, 2x, 3x | org\telegram\ui\Components\ItemOptions.java, org\telegram\ui\Components\Paint\Views\LPhotoPaintView.java, org\telegram\ui\NewContactBottomSheet.java (+1) | ⬜ |
| `msg_text_outlined` | `msg_text_outlined` | raster 1x, 2x, 3x | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `msg_theme` | `msg_theme` | raster 1x, 2x, 3x | com\exteragram\messenger\export\ui\ChatViewer.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\preferences\MainPreferencesActivity.java (+5) | ⬜ |
| `msg_theme_remix` | `msg_theme_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_theme_solar` | `msg_theme_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_to_beginning` | `msg_to_beginning` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_to_beginning_remix` | `msg_to_beginning_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_to_beginning_solar` | `msg_to_beginning_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_add` | `msg_tone_add` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_tone_add_remix` | `msg_tone_add_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_add_solar` | `msg_tone_add_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_off` | `msg_tone_off` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java | ⬜ |
| `msg_tone_off_remix` | `msg_tone_off_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_off_solar` | `msg_tone_off_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_on` | `msg_tone_on` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java | ⬜ |
| `msg_tone_on_remix` | `msg_tone_on_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_tone_on_solar` | `msg_tone_on_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_topic_close` | `msg_topic_close` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\TopicsTabsView.java (+1) | ⬜ |
| `msg_topic_create` | `msg_topic_create` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\TopicsFragment.java | ⬜ |
| `msg_topic_create_remix` | `msg_topic_create_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_topic_create_solar` | `msg_topic_create_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_topic_restart` | `msg_topic_restart` | raster 1x, 2x, 3x | org\telegram\ui\Components\TopicsTabsView.java, org\telegram\ui\TopicsFragment.java | ⬜ |
| `msg_topics` | `msg_topics` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\PluginsInfoActivity.java, org\telegram\ui\ChatActivity.java (+2) | ⬜ |
| `msg_topics_remix` | `msg_topics_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_topics_solar` | `msg_topics_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_translate` | `msg_translate` | raster 1x, 2x, 3x | com\exteragram\messenger\components\TranslateBeforeSendWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\PluginTranslateButton.java (+10) | ⬜ |
| `msg_translate_remix` | `msg_translate_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_translate_solar` | `msg_translate_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_trending_remix` | `msg_trending_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_trending_solar` | `msg_trending_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unarchive` | `msg_unarchive` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_unarchive_remix` | `msg_unarchive_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unarchive_solar` | `msg_unarchive_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unfave` | `msg_unfave` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ContentPreviewViewer.java | ⬜ |
| `msg_unfave_remix` | `msg_unfave_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unfave_solar` | `msg_unfave_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_ungroup` | `msg_ungroup` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java | ⬜ |
| `msg_ungroup_remix` | `msg_ungroup_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_ungroup_solar` | `msg_ungroup_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unmute` | `msg_unmute` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\ChatNotificationsPopupWrapper.java (+5) | ⬜ |
| `msg_unpin` | `msg_unpin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\PluginCell.java, org\telegram\ui\ChatActivity.java (+9) | ⬜ |
| `msg_unpin_remix` | `msg_unpin_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unpin_solar` | `msg_unpin_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unvote` | `msg_unvote` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\SharedMediaLayout.java (+2) | ⬜ |
| `msg_unvote_remix` | `msg_unvote_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_unvote_solar` | `msg_unvote_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_user_remove` | `msg_user_remove` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java (+5) | ⬜ |
| `msg_user_remove_remix` | `msg_user_remove_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_user_remove_solar` | `msg_user_remove_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_user_search` | `msg_user_search` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_user_search_remix` | `msg_user_search_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_user_search_solar` | `msg_user_search_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_usersearch` | `msg_usersearch` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_video` | `msg_video` | raster 1x, 2x, 3x | org\telegram\ui\Components\ImageUpdater.java | ⬜ |
| `msg_videocall` | `msg_videocall` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_videocall_remix` | `msg_videocall_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_videocall_solar` | `msg_videocall_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_view_file` | `msg_view_file` | raster 1x, 2x, 3x | com\exteragram\messenger\components\MessageDetailsPopupWrapper.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CachedMediaLayout.java (+7) | ⬜ |
| `msg_viewchats` | `msg_viewchats` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\BackButtonMenu.java | ⬜ |
| `msg_viewintopic` | `msg_viewintopic` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Business\QuickRepliesActivity.java, org\telegram\ui\CachedMediaLayout.java (+2) | ⬜ |
| `msg_viewintopic_remix` | `msg_viewintopic_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_viewintopic_solar` | `msg_viewintopic_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_viewreplies` | `msg_viewreplies` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `msg_viewreplies_remix` | `msg_viewreplies_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_viewreplies_solar` | `msg_viewreplies_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_views` | `msg_views` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Stories\SelfStoriesPreviewView.java | ⬜ |
| `msg_views_remix` | `msg_views_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_views_solar` | `msg_views_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_bluetooth` | `msg_voice_bluetooth` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voice_bluetooth_remix` | `msg_voice_bluetooth_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_bluetooth_solar` | `msg_voice_bluetooth_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_headphones` | `msg_voice_headphones` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voice_headphones_remix` | `msg_voice_headphones_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_headphones_solar` | `msg_voice_headphones_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_muted` | `msg_voice_muted` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java, org\telegram\ui\Stories\MuteButton.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_voice_phone` | `msg_voice_phone` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voice_phone_remix` | `msg_voice_phone_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_phone_solar` | `msg_voice_phone_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_pip` | `msg_voice_pip` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voice_pip_remix` | `msg_voice_pip_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_pip_solar` | `msg_voice_pip_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voice_speaker` | `msg_voice_speaker` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voice_unmuted` | `msg_voice_unmuted` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java, org\telegram\ui\Stories\MuteButton.java, org\telegram\ui\Stories\PeerStoriesView.java | ⬜ |
| `msg_voicechat` | `msg_voicechat` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_voicechat2` | `msg_voicechat2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `msg_voicechat2_remix` | `msg_voicechat2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voicechat2_solar` | `msg_voicechat2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voicechat_remix` | `msg_voicechat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voicechat_solar` | `msg_voicechat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_voiceclose` | `msg_voiceclose` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_voiceshare` | `msg_voiceshare` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `msg_warning` | `msg_warning` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `msg_work` | `msg_work` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\PhonebookShareAlert.java | ⬜ |
| `msg_work_remix` | `msg_work_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_work_solar` | `msg_work_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_zoomin` | `msg_zoomin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MediaActivity.java, org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `msg_zoomin_remix` | `msg_zoomin_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_zoomin_solar` | `msg_zoomin_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_zoomout` | `msg_zoomout` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\MediaActivity.java, org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `msg_zoomout_remix` | `msg_zoomout_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_zoomout_solar` | `msg_zoomout_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `msg_zoomout_stats` | `msg_zoomout_stats` | raster 1x, 2x, 3x | org\telegram\ui\Charts\view_data\ChartHeaderView.java | ⬜ |
| `mtrl_ic_cancel` | `mtrl_ic_cancel` | vector | com\google\android\material\textfield\ClearTextEndIconDelegate.java | ⬜ |
| `music_empty` | `music_empty` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `mute_remix` | `mute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `mute_solar` | `mute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `nav_edit_attach` | `nav_edit_attach` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `navbar_search_tag` | `navbar_search_tag` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `navbar_search_tag_remix` | `navbar_search_tag_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `navbar_search_tag_solar` | `navbar_search_tag_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `newyear` | `newyear` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `no_passport` | `no_passport` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `no_password` | `no_password` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `nocover` | `nocover` | vector | com\exteragram\messenger\nowplaying\ui\components\NowPlayingCard.java, org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `notification` | `notification` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\IconManager.java, com\exteragram\messenger\icons\IconPackProvider.java | ⬜ |
| `notifications_mute1h` | `notifications_mute1h` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `notifications_mute1h_remix` | `notifications_mute1h_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_mute1h_solar` | `notifications_mute1h_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_mute2d` | `notifications_mute2d` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `notifications_mute2d_remix` | `notifications_mute2d_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_mute2d_solar` | `notifications_mute2d_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_off` | `notifications_off` | raster 1x, 2x, 3x | org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `notifications_on` | `notifications_on` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `notifications_on_remix` | `notifications_on_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_on_solar` | `notifications_on_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `notifications_settings` | `notifications_settings` | raster 1x, 2x, 3x | org\telegram\ui\Components\AlertsCreator.java | ⬜ |
| `other_2x_large` | `other_2x_large` | raster 1x, 2x, 3x | org\telegram\ui\Components\Premium\DoubledLimitsBottomSheet$Adapter.java | ⬜ |
| `other_chats` | `other_chats` | vector | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `other_lockedfolders` | `other_lockedfolders` | raster 1x, 2x, 3x | org\telegram\ui\Components\FilterTabsView.java | ⬜ |
| `other_lockedfolders2` | `other_lockedfolders2` | raster 1x, 2x, 3x | org\telegram\ui\Cells\AvailableReactionCell.java, org\telegram\ui\FiltersSetupActivity.java | ⬜ |
| `outline_add_account` | `outline_add_account` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `outline_add_account_remix` | `outline_add_account_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_add_account_solar` | `outline_add_account_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_ai_translate2` | `outline_ai_translate2` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `outline_attach_camera_24` | `outline_attach_camera_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\GalleryEmptyView.java | ⬜ |
| `outline_caption_24` | `outline_caption_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ContentPreviewViewer.java | ⬜ |
| `outline_caption_remix` | `outline_caption_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_caption_solar` | `outline_caption_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_community_remove_24` | `outline_community_remove_24` | vector | org\telegram\ui\ChatEditActivity.java | ⬜ |
| `outline_craft` | `outline_craft` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `outline_fab_story_24` | `outline_fab_story_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `outline_gram_24` | `outline_gram_24` | vector | org\telegram\ui\ChatActivity.java, org\telegram\ui\ChatEditActivity.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `outline_groups_24` | `outline_groups_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_header_lock_24` | `outline_header_lock_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\DialogsActivity.java | ⬜ |
| `outline_header_lock_remix` | `outline_header_lock_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_header_lock_solar` | `outline_header_lock_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_header_qr_24` | `outline_header_qr_24` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `outline_header_search` | `outline_header_search` | vector | com\exteragram\messenger\feed\ui\FeedChannelsActivity.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\icons\ui\IconPacksEditorActivity.java (+38) | ⬜ |
| `outline_message_time_24` | `outline_message_time_24` | vector | org\telegram\ui\Components\MessagePrivateSeenView.java | ⬜ |
| `outline_poll_add_24` | `outline_poll_add_24` | vector | org\telegram\ui\Components\poll\buttons\PollAddButtonDrawable.java | ⬜ |
| `outline_poll_attach_24` | `outline_poll_attach_24` | vector | org\telegram\ui\Components\poll\PollAttachButton.java, org\telegram\ui\iv\RichEditor.java, org\telegram\ui\iv\RichEditorToolbar.java | ⬜ |
| `outline_poll_emoji_24` | `outline_poll_emoji_24` | vector | org\telegram\ui\Components\poll\PollAddOptionFieldLayout.java | ⬜ |
| `outline_profile_call_24` | `outline_profile_call_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_edit_24` | `outline_profile_edit_24` | vector | org\telegram\ui\Components\ProfileActionsView.java, org\telegram\ui\Components\TopicsTabsView.java | ⬜ |
| `outline_profile_member_24` | `outline_profile_member_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_message_24` | `outline_profile_message_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_mute_24` | `outline_profile_mute_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_photo` | `outline_profile_photo` | vector | org\telegram\ui\Components\ProfileActionsView.java, org\telegram\ui\community\CommunityEditActivity.java | ⬜ |
| `outline_profile_settings` | `outline_profile_settings` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProfileActionsView.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `outline_profile_stop_24` | `outline_profile_stop_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_story` | `outline_profile_story` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_unmute_24` | `outline_profile_unmute_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_profile_video_24` | `outline_profile_video_24` | vector | org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `outline_question_mark` | `outline_question_mark` | vector | org\telegram\ui\Stars\StarGiftSheet.java | ⬜ |
| `outline_revert_24` | `outline_revert_24` | vector | org\telegram\ui\ChatActivity.java | ⬜ |
| `outline_saved_24` | `outline_saved_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java | ⬜ |
| `outline_search_1_24` | `outline_search_1_24` | vector | org\telegram\ui\Components\FragmentSearchField.java, org\telegram\ui\InviteContactsActivity.java | ⬜ |
| `outline_shield_check` | `outline_shield_check` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProxyDrawable.java | ⬜ |
| `outline_shield_plain_24` | `outline_shield_plain_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ProxyDrawable.java | ⬜ |
| `outline_shield_plain_remix` | `outline_shield_plain_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `outline_shield_plain_solar` | `outline_shield_plain_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `pagedown` | `pagedown` | raster 1x, 2x, 3x | org\telegram\ui\Components\chat\layouts\ChatActivitySideControlsButtonsLayout.java | ⬜ |
| `passcode_check` | `passcode_check` | raster 1x, 2x, 3x | org\telegram\ui\Components\PasscodeView.java | ⬜ |
| `pencil` | `pencil` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `pencil_remix` | `pencil_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `pencil_solar` | `pencil_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permission_locked` | `permission_locked` | raster 1x, 2x, 3x | com\exteragram\messenger\preferences\GeneralPreferencesActivity.java, org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ArchiveSettingsActivity.java (+9) | ⬜ |
| `permissions_camera1` | `permissions_camera1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\PhotoAttachPermissionCell.java | ⬜ |
| `permissions_camera1_remix` | `permissions_camera1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_camera1_solar` | `permissions_camera1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_camera2` | `permissions_camera2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\PhotoAttachPermissionCell.java | ⬜ |
| `permissions_camera2_remix` | `permissions_camera2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_camera2_solar` | `permissions_camera2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_gallery1` | `permissions_gallery1` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\PhotoAttachPermissionCell.java | ⬜ |
| `permissions_gallery1_remix` | `permissions_gallery1_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_gallery1_solar` | `permissions_gallery1_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_gallery2` | `permissions_gallery2` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\PhotoAttachPermissionCell.java | ⬜ |
| `permissions_gallery2_remix` | `permissions_gallery2_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `permissions_gallery2_solar` | `permissions_gallery2_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `phone_activate` | `phone_activate` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `photo_arrowshape` | `photo_arrowshape` | vector | org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `photo_circle` | `photo_circle` | vector | org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `photo_circle_fill` | `photo_circle_fill` | vector | org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `photo_expand` | `photo_expand` | vector | org\telegram\ui\Components\Paint\Views\PaintTextOptionsView.java | ⬜ |
| `photo_paint_brush` | `photo_paint_brush` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_paint_brush_remix` | `photo_paint_brush_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_paint_brush_solar` | `photo_paint_brush_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_rectangle` | `photo_rectangle` | vector | org\telegram\ui\Components\Paint\Brush.java, org\telegram\ui\PhotoViewer.java | ⬜ |
| `photo_rectangle_fill` | `photo_rectangle_fill` | vector | org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `photo_star` | `photo_star` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Paint\Brush.java, org\telegram\ui\LiteModeSettingsActivity.java | ⬜ |
| `photo_star_fill` | `photo_star_fill` | vector | org\telegram\ui\Components\Paint\Brush.java | ⬜ |
| `photo_tooltip2` | `photo_tooltip2` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `photo_undo` | `photo_undo` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_undo2` | `photo_undo2` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\PluginTranslateButton.java, org\telegram\ui\Components\Paint\Views\LPhotoPaintView.java (+2) | ⬜ |
| `photo_undo_remix` | `photo_undo_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_undo_solar` | `photo_undo_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `photo_zoomout` | `photo_zoomout` | vector | org\telegram\ui\Components\Paint\Views\LPhotoPaintView.java, org\telegram\ui\Stories\recorder\PaintView.java | ⬜ |
| `photos_arrow` | `photos_arrow` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatAttachAlertPhotoLayout.java, org\telegram\ui\Stories\StoryMediaAreasView.java | ⬜ |
| `photos_header_shadow` | `photos_header_shadow` | raster 1x, 2x, 3x | org\telegram\ui\Components\SharedMediaLayout.java | ⬜ |
| `picker` | `picker` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\Paint\ColorPickerBottomSheet.java | ⬜ |
| `pillstack_btc` | `pillstack_btc` | vector | com\exteragram\messenger\pillstack\ui\pills\crypto\BtcPill.java | ⬜ |
| `pillstack_btc_settings` | `pillstack_btc_settings` | vector | com\exteragram\messenger\pillstack\core\PillRegistry.java | ⬜ |
| `pillstack_usd` | `pillstack_usd` | vector | com\exteragram\messenger\pillstack\ui\pills\crypto\UsdPill.java | ⬜ |
| `pillstack_usd_settings` | `pillstack_usd_settings` | vector | com\exteragram\messenger\pillstack\core\PillRegistry.java | ⬜ |
| `pin` | `pin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Cells\LocationCell.java, org\telegram\ui\Cells\SendLocationCell.java (+1) | ⬜ |
| `pip_close` | `pip_close` | raster 1x, 2x, 3x | org\telegram\ui\Components\voip\VoIPPiPView.java | ⬜ |
| `pip_enlarge` | `pip_enlarge` | raster 1x, 2x, 3x | org\telegram\ui\Components\voip\VoIPPiPView.java | ⬜ |
| `pip_pause_large` | `pip_pause_large` | raster 1x, 2x, 3x | org\telegram\ui\Components\PipVideoOverlay.java | ⬜ |
| `pip_play_large` | `pip_play_large` | raster 1x, 2x, 3x | org\telegram\ui\Components\PipVideoOverlay.java | ⬜ |
| `pip_replay_large` | `pip_replay_large` | raster 1x, 2x, 3x | org\telegram\ui\Components\PipVideoOverlay.java | ⬜ |
| `pip_video_close` | `pip_video_close` | raster 1x, 2x, 3x | org\telegram\ui\Components\PipVideoOverlay.java, org\telegram\ui\Components\voip\RTMPStreamPipOverlay.java, org\telegram\ui\Stories\LiveStoryPipOverlay.java | ⬜ |
| `pip_video_expand` | `pip_video_expand` | raster 1x, 2x, 3x | org\telegram\ui\Components\PipVideoOverlay.java, org\telegram\ui\Components\voip\RTMPStreamPipOverlay.java, org\telegram\ui\Stories\LiveStoryPipOverlay.java | ⬜ |
| `play_mini_video` | `play_mini_video` | raster 1x, 2x, 3x | org\telegram\messenger\MessageObject.java, org\telegram\ui\Cells\PhotoAttachPhotoCell.java, org\telegram\ui\Cells\SharedPhotoVideoCell.java (+3) | ⬜ |
| `player_new_order` | `player_new_order` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_repeat_off` | `player_new_repeat_off` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `player_new_repeat_reverse` | `player_new_repeat_reverse` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_repeat_shuffle` | `player_new_repeat_shuffle` | raster 1x, 2x, 3x | org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_repeatall` | `player_new_repeatall` | vector | org\telegram\messenger\MusicPlayerService.java, org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_repeatone` | `player_new_repeatone` | vector | org\telegram\messenger\MusicPlayerService.java, org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_shuffle` | `player_new_shuffle` | vector | org\telegram\messenger\MusicPlayerService.java, org\telegram\ui\Components\AudioPlayerAlert.java | ⬜ |
| `player_new_shuffle_off` | `player_new_shuffle_off` | vector | org\telegram\messenger\MusicPlayerService.java | ⬜ |
| `plugins_filled` | `plugins_filled` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\plugins\ui\components\InstallPluginBottomSheet.java (+1) | ⬜ |
| `plugins_filled_remix` | `plugins_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `plugins_filled_solar` | `plugins_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `plugins_safe_mode` | `plugins_safe_mode` | vector | org\telegram\messenger\MediaDataController.java | ⬜ |
| `poll_add_circle` | `poll_add_circle` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\drawer\DrawerAccountPickerView.java, org\telegram\ui\Components\ChatAttachAlertPollLayout.java (+9) | ⬜ |
| `poll_add_plus` | `poll_add_plus` | raster 1x, 2x, 3x | com\exteragram\messenger\ai\ui\activities\ServicesActivity.java, com\exteragram\messenger\drawer\DrawerAccountPickerView.java, org\telegram\ui\Components\ChatAttachAlertPollLayout.java (+11) | ⬜ |
| `poll_remove` | `poll_remove` | raster 1x, 2x, 3x | org\telegram\ui\Cells\PollEditTextCell.java, org\telegram\ui\Components\Premium\boosts\cells\ChatCell.java | ⬜ |
| `poll_right` | `poll_right` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `poll_wrong` | `poll_wrong` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `premium_ai_editor` | `premium_ai_editor` | vector | org\telegram\ui\PremiumPreviewFragment.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `premium_colors` | `premium_colors` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `premium_rich_editor` | `premium_rich_editor` | vector | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `premium_status` | `premium_status` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `premium_tags` | `premium_tags` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `premium_wallpaper` | `premium_wallpaper` | raster 1x, 2x, 3x | org\telegram\ui\PremiumPreviewFragment.java | ⬜ |
| `preview_arrow` | `preview_arrow` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\ContentPreviewViewer.java | ⬜ |
| `preview_custom` | `preview_custom` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ThemesHorizontalListCell.java | ⬜ |
| `preview_dots` | `preview_dots` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java, org\telegram\ui\Cells\ThemesHorizontalListCell.java, org\telegram\ui\Components\ThemePreviewDrawable.java | ⬜ |
| `profile_discuss` | `profile_discuss` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `profile_discuss_remix` | `profile_discuss_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_discuss_solar` | `profile_discuss_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_level10_inner` | `profile_level10_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level10_outer` | `profile_level10_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level1_inner` | `profile_level1_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level1_outer` | `profile_level1_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level20_inner` | `profile_level20_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level20_outer` | `profile_level20_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level2_inner` | `profile_level2_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level2_outer` | `profile_level2_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level30_inner` | `profile_level30_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level30_outer` | `profile_level30_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level3_inner` | `profile_level3_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level3_outer` | `profile_level3_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level40_inner` | `profile_level40_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level40_outer` | `profile_level40_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level4_inner` | `profile_level4_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level4_outer` | `profile_level4_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level50_inner` | `profile_level50_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level50_outer` | `profile_level50_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level5_inner` | `profile_level5_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level5_outer` | `profile_level5_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level60_inner` | `profile_level60_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level60_outer` | `profile_level60_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level6_inner` | `profile_level6_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level6_outer` | `profile_level6_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level70_inner` | `profile_level70_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level70_outer` | `profile_level70_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level7_inner` | `profile_level7_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level7_outer` | `profile_level7_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level80_inner` | `profile_level80_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level80_outer` | `profile_level80_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level8_inner` | `profile_level8_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level8_outer` | `profile_level8_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level90_inner` | `profile_level90_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level90_outer` | `profile_level90_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level9_inner` | `profile_level9_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level9_outer` | `profile_level9_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level_minus_inner` | `profile_level_minus_inner` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_level_minus_outer` | `profile_level_minus_outer` | vector | org\telegram\ui\Components\BadgeLevelDrawable.java | ⬜ |
| `profile_newmsg_filled` | `profile_newmsg_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java | ⬜ |
| `profile_newmsg_filled_remix` | `profile_newmsg_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_newmsg_filled_solar` | `profile_newmsg_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_phone` | `profile_phone` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CreateGroupCallSheet.java, org\telegram\ui\GroupCreateActivity.java | ⬜ |
| `profile_phone_remix` | `profile_phone_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_phone_solar` | `profile_phone_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_qr_scan_24` | `profile_qr_scan_24` | vector | org\telegram\ui\QrActivity.java | ⬜ |
| `profile_video` | `profile_video` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CreateGroupCallSheet.java, org\telegram\ui\GroupCreateActivity.java (+1) | ⬜ |
| `profile_video_remix` | `profile_video_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `profile_video_solar` | `profile_video_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `proxy_check` | `proxy_check` | raster 1x, 2x, 3x | org\telegram\ui\ProxyListActivity.java | ⬜ |
| `qr_at_large` | `qr_at_large` | raster 1x, 2x, 3x | org\telegram\ui\QrActivity.java | ⬜ |
| `qr_at_medium` | `qr_at_medium` | raster 1x, 2x, 3x | org\telegram\ui\QrActivity.java | ⬜ |
| `qr_at_small` | `qr_at_small` | raster 1x, 2x, 3x | org\telegram\ui\QrActivity.java | ⬜ |
| `qr_flashlight` | `qr_flashlight` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\CameraScanActivity.java | ⬜ |
| `qr_flashlight_remix` | `qr_flashlight_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `qr_flashlight_solar` | `qr_flashlight_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `qr_gallery` | `qr_gallery` | raster 1x, 2x, 3x | org\telegram\ui\CameraScanActivity.java | ⬜ |
| `quantum_ic_keyboard_arrow_down_white_36` | `quantum_ic_keyboard_arrow_down_white_36` | raster 1x, 2x, 3x | com\google\android\gms\cast\framework\media\widget\ExpandedControllerActivity.java | ⬜ |
| `reactionbutton` | `reactionbutton` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\chat\layouts\ChatActivitySideControlsButtonsLayout.java | ⬜ |
| `redcircle` | `redcircle` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java | ⬜ |
| `report` | `report` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, com\exteragram\messenger\pillstack\ui\pills\weather\WeatherSettingsActivity.java, org\telegram\ui\Components\ProfileActionsView.java | ⬜ |
| `report_remix` | `report_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `report_solar` | `report_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `round_check2` | `round_check2` | raster 1x, 2x, 3x | org\telegram\ui\Cells\UserCell2.java, org\telegram\ui\Cells\WallpaperCell.java | ⬜ |
| `round_warn` | `round_warn` | vector | org\telegram\ui\ProfileActivity.java | ⬜ |
| `screencast_big` | `screencast_big` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\voip\GroupCallMiniTextureView.java, org\telegram\ui\Components\voip\PrivateVideoPreviewDialog.java (+2) | ⬜ |
| `screencast_big_remix` | `screencast_big_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `screencast_big_solar` | `screencast_big_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `search_arrow` | `search_arrow` | raster 1x, 2x, 3x | org\telegram\ui\Cells\TextCheckCell.java, org\telegram\ui\Components\TranslateAlert2.java | ⬜ |
| `search_date_filled` | `search_date_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java | ⬜ |
| `search_files_filled` | `search_files_filled` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Adapters\FiltersView.java, org\telegram\ui\bots\BotDownloads.java | ⬜ |
| `search_links_filled` | `search_links_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java | ⬜ |
| `search_media_filled` | `search_media_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java | ⬜ |
| `search_music_filled` | `search_music_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java | ⬜ |
| `search_users_filled` | `search_users_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java, org\telegram\ui\DialogsActivity.java, org\telegram\ui\community\CommunitySheet.java | ⬜ |
| `search_voice_filled` | `search_voice_filled` | raster 1x, 2x, 3x | org\telegram\ui\Adapters\FiltersView.java | ⬜ |
| `select_between` | `select_between` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\chat\layouts\ChatActivityActionsButtonsLayout.java | ⬜ |
| `selectphoto_large` | `selectphoto_large` | raster 1x, 2x, 3x | org\telegram\ui\PhotoViewer.java | ⬜ |
| `send_extera_24` | `send_extera_24` | vector | org\telegram\ui\Components\AIEditorAlert.java, org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\ChatAttachAlert.java (+5) | ⬜ |
| `send_outline` | `send_outline` | vector | org\telegram\ui\Components\ChatActivityEnterView.java | ⬜ |
| `send_plane_24` | `send_plane_24` | vector | org\telegram\ui\Gifts\GiftMessageBottomSheet.java | ⬜ |
| `send_plane_26` | `send_plane_26` | vector | org\telegram\ui\Components\UnsupportedBlockDrawable.java | ⬜ |
| `settings_account` | `settings_account` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_account_remix` | `settings_account_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_account_solar` | `settings_account_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_arrow` | `settings_arrow` | raster 1x, 2x, 3x | org\telegram\ui\Cells\SettingsSearchCell.java, org\telegram\ui\Stories\recorder\ScannedLinkPreview.java, org\telegram\ui\bots\ChannelAffiliateProgramsFragment.java (+1) | ⬜ |
| `settings_ask` | `settings_ask` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_ask_remix` | `settings_ask_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_ask_solar` | `settings_ask_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_business` | `settings_business` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_business_remix` | `settings_business_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_business_solar` | `settings_business_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_calls` | `settings_calls` | vector | org\telegram\ui\Adapters\ContactsAdapter.java, org\telegram\ui\UserInfoActivity.java | ⬜ |
| `settings_channel` | `settings_channel` | vector | org\telegram\ui\Adapters\ContactsAdapter.java | ⬜ |
| `settings_chat` | `settings_chat` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_chat_remix` | `settings_chat_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_chat_solar` | `settings_chat_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_data` | `settings_data` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_data_remix` | `settings_data_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_data_solar` | `settings_data_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_devices` | `settings_devices` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_devices_remix` | `settings_devices_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_devices_solar` | `settings_devices_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_faq` | `settings_faq` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_faq_remix` | `settings_faq_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_faq_solar` | `settings_faq_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_features` | `settings_features` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_features_remix` | `settings_features_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_features_solar` | `settings_features_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_folders` | `settings_folders` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_folders_remix` | `settings_folders_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_folders_solar` | `settings_folders_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_gift` | `settings_gift` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_gift_remix` | `settings_gift_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_gift_solar` | `settings_gift_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_gram_24` | `settings_gram_24` | vector | com\exteragram\messenger\pillstack\core\PillRegistry.java, org\telegram\ui\ProfileActivity.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_group` | `settings_group` | vector | org\telegram\ui\Adapters\ContactsAdapter.java | ⬜ |
| `settings_invite` | `settings_invite` | vector | org\telegram\ui\Adapters\ContactsAdapter.java | ⬜ |
| `settings_language` | `settings_language` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_language_remix` | `settings_language_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_language_solar` | `settings_language_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_policy` | `settings_policy` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_policy_remix` | `settings_policy_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_policy_solar` | `settings_policy_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_power` | `settings_power` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_power_remix` | `settings_power_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_power_solar` | `settings_power_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_premium` | `settings_premium` | vector | org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_privacy` | `settings_privacy` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_privacy_remix` | `settings_privacy_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_privacy_solar` | `settings_privacy_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_sounds` | `settings_sounds` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_sounds_remix` | `settings_sounds_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_sounds_solar` | `settings_sounds_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_stars` | `settings_stars` | vector | org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_wallet` | `settings_wallet` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\SettingsActivity.java | ⬜ |
| `settings_wallet_remix` | `settings_wallet_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `settings_wallet_solar` | `settings_wallet_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `shadowdown` | `shadowdown` | raster 1x, 2x, 3x | org\telegram\ui\Cells\GroupCreateSectionCell.java, org\telegram\ui\QrActivity.java | ⬜ |
| `shadowed_bubble1` | `shadowed_bubble1` | raster 3x | org\telegram\ui\SelectAnimatedEmojiDialog.java | ⬜ |
| `shadowed_bubble2_half` | `shadowed_bubble2_half` | raster 3x | org\telegram\ui\SelectAnimatedEmojiDialog.java | ⬜ |
| `share` | `share` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\WebviewActivity.java | ⬜ |
| `share_arrow` | `share_arrow` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `share_arrow_remix` | `share_arrow_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `share_arrow_solar` | `share_arrow_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `share_remix` | `share_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `share_solar` | `share_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smallanimationpin` | `smallanimationpin` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `smallanimationpin_remix` | `smallanimationpin_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smallanimationpin_solar` | `smallanimationpin_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smallanimationpinleft` | `smallanimationpinleft` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `smallanimationpinright` | `smallanimationpinright` | raster 1x, 2x, 3x | org\telegram\ui\Components\ShareLocationDrawable.java | ⬜ |
| `smiles_info` | `smiles_info` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiView.java | ⬜ |
| `smiles_inputsearch` | `smiles_inputsearch` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\SearchField.java, org\telegram\ui\Components\ThemeEditorView.java (+2) | ⬜ |
| `smiles_inputsearch_remix` | `smiles_inputsearch_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_inputsearch_solar` | `smiles_inputsearch_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_tab_clear` | `smiles_tab_clear` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java, org\telegram\ui\Components\Reactions\BackSpaceButtonView.java | ⬜ |
| `smiles_tab_clear_remix` | `smiles_tab_clear_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_tab_clear_solar` | `smiles_tab_clear_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_tab_gif` | `smiles_tab_gif` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `smiles_tab_search` | `smiles_tab_search` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiView.java, org\telegram\ui\Components\PostsSearchContainer.java | ⬜ |
| `smiles_tab_settings` | `smiles_tab_settings` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiTabsStrip.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `smiles_tab_settings_remix` | `smiles_tab_settings_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_tab_settings_solar` | `smiles_tab_settings_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `smiles_tab_smiles` | `smiles_tab_smiles` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EditTextEmoji.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `smiles_tab_stickers` | `smiles_tab_stickers` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `sms_bubble` | `sms_bubble` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `sms_code` | `sms_code` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `sms_devices` | `sms_devices` | raster 1x, 2x, 3x | org\telegram\ui\PassportActivity.java | ⬜ |
| `star` | `star` | vector | org\telegram\ui\Components\glass\GlassTabView.java, org\telegram\ui\Stars\StarGiftSheet.java, org\telegram\ui\Stories\PaidReactionButton.java | ⬜ |
| `star_premium` | `star_premium` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `star_premium_cutout` | `star_premium_cutout` | vector | org\telegram\ui\iv\RichEditor.java | ⬜ |
| `star_reaction` | `star_reaction` | vector | org\telegram\ui\Components\ReactionsContainerLayout.java, org\telegram\ui\Components\Reactions\ReactionsLayoutInBubble.java | ⬜ |
| `star_small_inner` | `star_small_inner` | vector | org\telegram\ui\Cells\DialogCell.java, org\telegram\ui\Cells\PhotoAttachPhotoCell.java, org\telegram\ui\ChannelMonetizationLayout.java (+13) | ⬜ |
| `star_small_outline` | `star_small_outline` | vector | org\telegram\ui\Cells\DialogCell.java, org\telegram\ui\Components\ChatAvatarContainer.java, org\telegram\ui\Components\InviteLinkBottomSheet.java (+3) | ⬜ |
| `sticker_added` | `sticker_added` | raster 1x, 2x, 3x | org\telegram\ui\Cells\ThemeTypeCell.java, org\telegram\ui\PassportActivity.java | ⬜ |
| `stickers_back_arrow` | `stickers_back_arrow` | raster 1x, 2x, 3x | org\telegram\ui\Components\EmojiColorPickerWindow.java | ⬜ |
| `stickers_empty` | `stickers_empty` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Adapters\StickersSearchAdapter.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `stickers_empty_remix` | `stickers_empty_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_empty_solar` | `stickers_empty_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_favorites` | `stickers_favorites` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_favorites_remix` | `stickers_favorites_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_favorites_solar` | `stickers_favorites_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_filled` | `stickers_filled` | vector | com\exteragram\messenger\components\BlendedReplyFileText.java, com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `stickers_filled_remix` | `stickers_filled_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_filled_solar` | `stickers_filled_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_gifs_trending` | `stickers_gifs_trending` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\EmojiView.java | ⬜ |
| `stickers_gifs_trending_remix` | `stickers_gifs_trending_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_gifs_trending_solar` | `stickers_gifs_trending_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stickers_recent` | `stickers_recent` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `story_link` | `story_link` | vector | org\telegram\ui\CallLogActivity.java, org\telegram\ui\Components\AlertsCreator.java, org\telegram\ui\Components\Paint\Views\LinkPreview.java | ⬜ |
| `story_remix` | `story_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `story_solar` | `story_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `stream_flip` | `stream_flip` | vector | org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `stream_settings` | `stream_settings` | vector | org\telegram\ui\Stories\recorder\StoryRecorder.java | ⬜ |
| `summary_arrow` | `summary_arrow` | vector | org\telegram\ui\Components\SummaryIcon.java | ⬜ |
| `summary_stars` | `summary_stars` | vector | org\telegram\ui\Components\SummaryIcon.java | ⬜ |
| `tabs_calls_24` | `tabs_calls_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_calls_active_24` | `tabs_calls_active_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_calls_active_remix` | `tabs_calls_active_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_calls_active_solar` | `tabs_calls_active_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_chats_24` | `tabs_chats_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_chats_active_24` | `tabs_chats_active_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_chats_active_remix` | `tabs_chats_active_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_chats_active_solar` | `tabs_chats_active_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_chats_remix` | `tabs_chats_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_chats_solar` | `tabs_chats_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_contact_active_24` | `tabs_contact_active_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_contacts_24` | `tabs_contacts_24` | vector | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_contacts_active_remix` | `tabs_contacts_active_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_contacts_active_solar` | `tabs_contacts_active_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_link_24` | `tabs_link_24` | vector | org\telegram\ui\Components\glass\GlassTabView.java | ⬜ |
| `tabs_reorder` | `tabs_reorder` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\AIEditorAlert.java (+5) | ⬜ |
| `tabs_reorder_remix` | `tabs_reorder_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `tabs_reorder_solar` | `tabs_reorder_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `telegram_logo_2` | `telegram_logo_2` | vector | org\telegram\ui\DialogsActivity.java | ⬜ |
| `theme_picker` | `theme_picker` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\Components\ThemeEditorView.java | ⬜ |
| `theme_picker_remix` | `theme_picker_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `theme_picker_solar` | `theme_picker_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `timeline` | `timeline` | vector | org\telegram\ui\Stories\recorder\TimelineView.java | ⬜ |
| `toast_pallete` | `toast_pallete` | raster 1x, 2x, 3x | org\telegram\ui\Components\UndoView.java | ⬜ |
| `ton_icon` | `ton_icon` | vector | com\exteragram\messenger\preferences\OtherPreferencesActivity.java | ⬜ |
| `ton_space_icon` | `ton_space_icon` | vector | com\exteragram\messenger\preferences\OtherPreferencesActivity.java | ⬜ |
| `tone_create` | `tone_create` | vector | org\telegram\ui\Components\AIEditorAlert.java | ⬜ |
| `tooltip_arrow` | `tooltip_arrow` | raster 1x, 2x, 3x | org\telegram\ui\Components\ChatActivityEnterView.java, org\telegram\ui\Components\ChecksHintView.java, org\telegram\ui\Components\HintView.java (+1) | ⬜ |
| `tooltip_arrow_up` | `tooltip_arrow_up` | raster 1x, 2x, 3x | org\telegram\ui\Components\HintView.java | ⬜ |
| `tooltip_sound` | `tooltip_sound` | raster 1x, 2x, 3x | org\telegram\ui\ChatActivity.java, org\telegram\ui\Components\HintView.java | ⬜ |
| `trusted_mini` | `trusted_mini` | vector | com\exteragram\messenger\plugins\ui\components\InstallPluginBottomSheet.java | ⬜ |
| `unknown_mini` | `unknown_mini` | vector | com\exteragram\messenger\plugins\ui\components\InstallPluginBottomSheet.java | ⬜ |
| `unmute_remix` | `unmute_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `unmute_solar` | `unmute_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_area` | `verified_area` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\BotFullscreenButtons.java, org\telegram\ui\ActionBar\Theme.java (+4) | ⬜ |
| `verified_area_remix` | `verified_area_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_area_solar` | `verified_area_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_check` | `verified_check` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\messenger\BotFullscreenButtons.java, org\telegram\ui\ActionBar\Theme.java (+4) | ⬜ |
| `verified_check_remix` | `verified_check_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_check_solar` | `verified_check_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_profile` | `verified_profile` | raster 1x, 2x, 3x | com\exteragram\messenger\icons\BaseIconPacks.java, org\telegram\ui\ProfileActivity.java, org\telegram\ui\Stories\PeerStoriesView.java (+1) | ⬜ |
| `verified_profile_remix` | `verified_profile_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `verified_profile_solar` | `verified_profile_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `video_actions` | `video_actions` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `video_mute` | `video_mute` | raster 1x, 2x, 3x | org\telegram\ui\Components\InstantCameraView.java, org\telegram\ui\Components\InstantCameraView2.java | ⬜ |
| `video_muted` | `video_muted` | raster 1x, 2x, 3x | org\telegram\ui\ActionBar\Theme.java | ⬜ |
| `video_remix` | `video_remix` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `video_settings` | `video_settings` | vector | org\telegram\ui\PhotoViewer.java | ⬜ |
| `video_solar` | `video_solar` | vector | com\exteragram\messenger\icons\BaseIconPacks.java | ⬜ |
| `voice_expand` | `voice_expand` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `voice_minimize` | `voice_minimize` | raster 1x, 2x, 3x | org\telegram\ui\GroupCallActivity.java | ⬜ |
| `voice_volume_mini` | `voice_volume_mini` | raster 1x, 2x, 3x | org\telegram\ui\Cells\GroupCallUserCell.java | ⬜ |
| `voicechat_active` | `voicechat_active` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `voicechat_muted` | `voicechat_muted` | raster 1x, 2x, 3x | org\telegram\messenger\voip\VoIPService.java | ⬜ |
| `voicechat_screencast` | `voicechat_screencast` | raster 1x, 2x, 3x | org\telegram\ui\Components\voip\GroupCallMiniTextureView.java | ⬜ |
| `warning_sign` | `warning_sign` | vector | org\telegram\ui\Components\Premium\LimitPreviewView.java, org\telegram\ui\web\WebActionBar.java | ⬜ |
| `weather_cloudy` | `weather_cloudy` | vector | com\exteragram\messenger\pillstack\core\PillRegistry.java, com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_foggy` | `weather_foggy` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_night` | `weather_night` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_partly_cloudy` | `weather_partly_cloudy` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_rainy` | `weather_rainy` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_snowy` | `weather_snowy` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_sunny` | `weather_sunny` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `weather_thunderstorm` | `weather_thunderstorm` | vector | com\exteragram\messenger\pillstack\ui\pills\weather\WeatherPill.java | ⬜ |
| `zoom_minus` | `zoom_minus` | raster 1x, 2x, 3x | org\telegram\ui\Components\ZoomControlView.java | ⬜ |
| `zoom_plus` | `zoom_plus` | raster 1x, 2x, 3x | org\telegram\ui\Components\ZoomControlView.java | ⬜ |
| `zoom_round` | `zoom_round` | raster 1x, 2x, 3x | org\telegram\ui\Components\ZoomControlView.java | ⬜ |
| `zoom_round_b` | `zoom_round_b` | raster 1x, 2x, 3x | org\telegram\ui\Components\ZoomControlView.java | ⬜ |

## 2. Иконки в ресурсах без прямых ссылок в коде

Используются через pack-маппинги (remix/solar), reflection и ресурсные ссылки. Все включены в ассеты — по контракту полного порта. Всего: **444**.

## 3. Альтернативные иконки приложения (mipmap)

ExteraGram включает 33 альтернативных launcher-иконок (amethyst, aurora, avatar, beta, candy, cyberpunk, echpochmak, editor, fish, flappy, frog, google, google26, guitar, hamster, human, ice_age, invincible, jellyfish, mole, monet, nasa, nyan_cat, orbit, osu, psychosis, space, sponge, sunset, sus, winter, xp, + round и фоновые/передние слои adaptive). На iOS — переносится весь набор как alternate app icons + UI выбора иконки приложения (порт `appicons/` пакета мода); применение через `setAlternateIconName`. Все фоновые/передние WebP-слои конвертированы в PNG в imageset-наборы.
