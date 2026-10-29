//
//  ExteraConfig.swift
//  ExteraGram iOS port
//
//  1-в-1 порт com/exteragram/messenger/ExteraConfig.kt (APK 12.10.6, 20261004).
//  Все настройки мода с идентичными именами-ключами, дефолтами и семантикой.
//  Хранение: UserDefaults(suiteName: "exteraconfig") — тот же ключ, что у мода.
//

import Foundation

// MARK: - Enums (порт enum-классов мода)

public enum IconPackType: Int, Codable {
    case defaultPack = 0, solar = 1, remix = 2
}

public enum DividerStyle: Int, Codable {
    case line = 0, gradient = 1, none = 2
}

public enum TabIconsMode: Int, Codable {
    case titlesOnly = 0, titlesWithIcons = 1, iconsOnly = 2
}

public enum TabCounterMode: Int, Codable {
    case all = 0, unmuted = 1
}

public enum TransitionAnimation: Int, Codable {
    case spring = 0, fade = 1, system = 2
}

public enum GlassOutlineStyle: Int, Codable {
    case glare = 0, subtle = 1, none = 2
}

public enum StickerTimeMode: Int, Codable {
    case `default` = 0, always = 1, never = 2
}

public enum CameraType: Int, Codable {
    case cameraX = 0, camera2 = 1
}

public enum VideoMessagesCamera: Int, Codable {
    case front = 0, back = 1, dual = 2
}

public enum AvatarCornerType: Int, Codable {
    case `default` = 0, forum = 1, community = 2
}

public enum MainMenuItem: Int, Codable {
    case divider = -1, contacts = 0, calls = 1, savedMessages = 2, settings = 3, inviteFriends = 4, telegramFeatures = 5, archivedChats = 6, nightMode = 7
}

public enum ProxyDisableCondition: Int, Codable {
    case never = 0, always = 1, onVpn = 2, onMobileData = 3, onWiFi = 4
}

public enum BottomNavigationBar: Int, Codable {
    case show = 0, hide = 1, floating = 2
}

// MARK: - ExteraConfig (порт ExteraConfig.kt)

public final class ExteraConfig {
    public static let shared = ExteraConfig()

    /// SharedPreferences("exteraconfig") мода
    private let defaults: UserDefaults

    private init() {
        self.defaults = UserDefaults(suiteName: "exteraconfig") ?? .standard
    }

    /// Полный сброс настроек мода
    public func resetAll() {
        let keys = self.defaults.dictionaryRepresentation().keys
        for k in keys { self.defaults.removeObject(forKey: k) }
    }

    // MARK: Свойства — 1-в-1 с ExteraConfig.kt

    /// translationProvider (Android default: 0)
    public var translationProvider: Int {
        get { return self.defaults.object(forKey: "translationProvider") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "translationProvider") }
    }

    /// disableNumberRounding (Android default: false)
    public var disableNumberRounding: Bool {
        get { return self.defaults.object(forKey: "disableNumberRounding") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "disableNumberRounding") }
    }

    /// formatTimeWithSeconds (Android default: false)
    public var formatTimeWithSeconds: Bool {
        get { return self.defaults.object(forKey: "formatTimeWithSeconds") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "formatTimeWithSeconds") }
    }

    /// relativeLastSeen (Android default: false)
    public var relativeLastSeen: Bool {
        get { return self.defaults.object(forKey: "relativeLastSeen") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "relativeLastSeen") }
    }

    /// inAppVibration (Android default: true)
    public var inAppVibration: Bool {
        get { return self.defaults.object(forKey: "inAppVibration") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "inAppVibration") }
    }

    /// disableNotificationDelay (Android default: false)
    public var disableNotificationDelay: Bool {
        get { return self.defaults.object(forKey: "disableNotificationDelay") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "disableNotificationDelay") }
    }

    /// filterZalgo (Android default: true)
    public var filterZalgo: Bool {
        get { return self.defaults.object(forKey: "filterZalgo") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "filterZalgo") }
    }

    /// downloadSpeedBoost (Android default: 0)
    public var downloadSpeedBoost: Int {
        get { return self.defaults.object(forKey: "downloadSpeedBoost") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "downloadSpeedBoost") }
    }

    /// uploadSpeedBoost (Android default: false)
    public var uploadSpeedBoost: Bool {
        get { return self.defaults.object(forKey: "uploadSpeedBoost") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "uploadSpeedBoost") }
    }

    /// hidePhoneNumber (Android default: false)
    public var hidePhoneNumber: Bool {
        get { return self.defaults.object(forKey: "hidePhoneNumber") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hidePhoneNumber") }
    }

    /// showIdAndDc (Android default: 1)
    public var showIdAndDc: Int {
        get { return self.defaults.object(forKey: "showIdAndDc") as? Int ?? 1 }
        set { self.defaults.set(newValue, forKey: "showIdAndDc") }
    }

    /// hideArchiveFolder (Android default: false)
    public var hideArchiveFolder: Bool {
        get { return self.defaults.object(forKey: "hideArchiveFolder") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideArchiveFolder") }
    }

    /// archiveOnPull (Android default: false)
    public var archiveOnPull: Bool {
        get { return self.defaults.object(forKey: "archiveOnPull") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "archiveOnPull") }
    }

    /// disableUnarchiveSwipe (Android default: true)
    public var disableUnarchiveSwipe: Bool {
        get { return self.defaults.object(forKey: "disableUnarchiveSwipe") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "disableUnarchiveSwipe") }
    }

    /// doNotUseProxy (Android default: 0)
    public var doNotUseProxy: Int {
        get { return self.defaults.object(forKey: "doNotUseProxy") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "doNotUseProxy") }
    }

    /// customSavePath (Android default: "exteraGram")
    public var customSavePath: String {
        get { return self.defaults.string(forKey: "customSavePath") ?? "exteraGram" }
        set { self.defaults.set(newValue, forKey: "customSavePath") }
    }

    /// iconPack (Android default: IconPackType.defaultPack)
    public var iconPack: IconPackType {
        get { return IconPackType(rawValue: self.defaults.integer(forKey: "iconPack")) ?? IconPackType.defaultPack }
        set { self.defaults.set(newValue, forKey: "iconPack") }
    }

    /// editingIconPackId (Android default: nil)
    public var editingIconPackId: String? {
        get { return self.defaults.string(forKey: "editingIconPackId") }
        set { if let v = newValue { self.defaults.set(v, forKey: "editingIconPackId") } else { self.defaults.removeObject(forKey: "editingIconPackId") } }
    }

    /// avatarCorners (Android default: 28.0)
    public var avatarCorners: Float {
        get { return self.defaults.object(forKey: "avatarCorners") as? Float ?? 28.0 }
        set { self.defaults.set(newValue, forKey: "avatarCorners") }
    }

    /// singleCornerRadius (Android default: false)
    public var singleCornerRadius: Bool {
        get { return self.defaults.object(forKey: "singleCornerRadius") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "singleCornerRadius") }
    }

    /// dividerStyle (Android default: DividerStyle.line)
    public var dividerStyle: DividerStyle {
        get { return DividerStyle(rawValue: self.defaults.integer(forKey: "dividerStyle")) ?? DividerStyle.line }
        set { self.defaults.set(newValue, forKey: "dividerStyle") }
    }

    /// forceSnow (Android default: false)
    public var forceSnow: Bool {
        get { return self.defaults.object(forKey: "forceSnow") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "forceSnow") }
    }

    /// hideActionBarStatus (Android default: false)
    public var hideActionBarStatus: Bool {
        get { return self.defaults.object(forKey: "hideActionBarStatus") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideActionBarStatus") }
    }

    /// centerTitle (Android default: false)
    public var centerTitle: Bool {
        get { return self.defaults.object(forKey: "centerTitle") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "centerTitle") }
    }

    /// hideStories (Android default: false)
    public var hideStories: Bool {
        get { return self.defaults.object(forKey: "hideStories") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideStories") }
    }

    /// hideFloatingButton (Android default: false)
    public var hideFloatingButton: Bool {
        get { return self.defaults.object(forKey: "hideFloatingButton") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideFloatingButton") }
    }

    /// hideDialogsSearchBar (Android default: false)
    public var hideDialogsSearchBar: Bool {
        get { return self.defaults.object(forKey: "hideDialogsSearchBar") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideDialogsSearchBar") }
    }

    /// senderMiniAvatars (Android default: true)
    public var senderMiniAvatars: Bool {
        get { return self.defaults.object(forKey: "senderMiniAvatars") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "senderMiniAvatars") }
    }

    /// titleText (Android default: 0)
    public var titleText: Int {
        get { return self.defaults.object(forKey: "titleText") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "titleText") }
    }

    /// tabIcons (Android default: TabIconsMode.titlesOnly)
    public var tabIcons: TabIconsMode {
        get { return TabIconsMode(rawValue: self.defaults.integer(forKey: "tabIcons")) ?? TabIconsMode.titlesOnly }
        set { self.defaults.set(newValue, forKey: "tabIcons") }
    }

    /// tabCounterMode (Android default: TabCounterMode.all)
    public var tabCounterMode: TabCounterMode {
        get { return TabCounterMode(rawValue: self.defaults.integer(forKey: "tabCounterMode")) ?? TabCounterMode.all }
        set { self.defaults.set(newValue, forKey: "tabCounterMode") }
    }

    /// hideAllChats (Android default: false)
    public var hideAllChats: Bool {
        get { return self.defaults.object(forKey: "hideAllChats") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideAllChats") }
    }

    /// squareFab (Android default: true)
    public var squareFab: Bool {
        get { return self.defaults.object(forKey: "squareFab") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "squareFab") }
    }

    /// sectionRadius (Android default: 20.0)
    public var sectionRadius: Float {
        get { return self.defaults.object(forKey: "sectionRadius") as? Float ?? 20.0 }
        set { self.defaults.set(newValue, forKey: "sectionRadius") }
    }

    /// sectionsSeparatedHeadersPreference (Android default: true)
    public var sectionsSeparatedHeadersPreference: Bool {
        get { return self.defaults.object(forKey: "sectionsSeparatedHeadersPreference") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "sectionsSeparatedHeadersPreference") }
    }

    /// newLoadingStyle (Android default: true)
    public var newLoadingStyle: Bool {
        get { return self.defaults.object(forKey: "newLoadingStyle") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "newLoadingStyle") }
    }

    /// newSliderStyle (Android default: true)
    public var newSliderStyle: Bool {
        get { return self.defaults.object(forKey: "newSliderStyle") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "newSliderStyle") }
    }

    /// newSwitchStyle (Android default: true)
    public var newSwitchStyle: Bool {
        get { return self.defaults.object(forKey: "newSwitchStyle") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "newSwitchStyle") }
    }

    /// newChatHeaderStyle (Android default: false)
    public var newChatHeaderStyle: Bool {
        get { return self.defaults.object(forKey: "newChatHeaderStyle") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "newChatHeaderStyle") }
    }

    /// newNavigationBarStyle (Android default: false)
    public var newNavigationBarStyle: Bool {
        get { return self.defaults.object(forKey: "newNavigationBarStyle") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "newNavigationBarStyle") }
    }

    /// newFabStyle (Android default: false)
    public var newFabStyle: Bool {
        get { return self.defaults.object(forKey: "newFabStyle") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "newFabStyle") }
    }

    /// tabletMode (Android default: 0)
    public var tabletMode: Int {
        get { return self.defaults.object(forKey: "tabletMode") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "tabletMode") }
    }

    /// useSystemFonts (Android default: true)
    public var useSystemFonts: Bool {
        get { return self.defaults.object(forKey: "useSystemFonts") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "useSystemFonts") }
    }

    /// gooeyAvatarAnimation (Android default: true)
    public var gooeyAvatarAnimation: Bool {
        get { return self.defaults.object(forKey: "gooeyAvatarAnimation") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "gooeyAvatarAnimation") }
    }

    /// customThemes (Android default: true)
    public var customThemes: Bool {
        get { return self.defaults.object(forKey: "customThemes") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "customThemes") }
    }

    /// predictiveBackIntensity (Android default: 1.0)
    public var predictiveBackIntensity: Float {
        get { return self.defaults.object(forKey: "predictiveBackIntensity") as? Float ?? 1.0 }
        set { self.defaults.set(newValue, forKey: "predictiveBackIntensity") }
    }

    /// transitionAnimation (Android default: TransitionAnimation.spring)
    public var transitionAnimation: TransitionAnimation {
        get { return TransitionAnimation(rawValue: self.defaults.integer(forKey: "transitionAnimation")) ?? TransitionAnimation.spring }
        set { self.defaults.set(newValue, forKey: "transitionAnimation") }
    }

    /// glassOutlineStyle (Android default: GlassOutlineStyle.glare)
    public var glassOutlineStyle: GlassOutlineStyle {
        get { return GlassOutlineStyle(rawValue: self.defaults.integer(forKey: "glassOutlineStyle")) ?? GlassOutlineStyle.glare }
        set { self.defaults.set(newValue, forKey: "glassOutlineStyle") }
    }

    /// glassMessageMenu (Android default: true)
    public var glassMessageMenu: Bool {
        get { return self.defaults.object(forKey: "glassMessageMenu") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "glassMessageMenu") }
    }

    /// forceBlur (Android default: false)
    public var forceBlur: Bool {
        get { return self.defaults.object(forKey: "forceBlur") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "forceBlur") }
    }

    /// eventType (Android default: 0)
    public var eventType: Int {
        get { return self.defaults.object(forKey: "eventType") as? Int ?? 0 }
        set { self.defaults.set(newValue, forKey: "eventType") }
    }

    /// navigationDrawer (Android default: false)
    public var navigationDrawer: Bool {
        get { return self.defaults.object(forKey: "navigationDrawer") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "navigationDrawer") }
    }

    /// immersiveDrawerAnimation (Android default: false)
    public var immersiveDrawerAnimation: Bool {
        get { return self.defaults.object(forKey: "immersiveDrawerAnimation") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "immersiveDrawerAnimation") }
    }

    /// showFeedTab (Android default: false)
    public var showFeedTab: Bool {
        get { return self.defaults.object(forKey: "showFeedTab") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showFeedTab") }
    }

    /// showFeedUnreadCounter (Android default: true)
    public var showFeedUnreadCounter: Bool {
        get { return self.defaults.object(forKey: "showFeedUnreadCounter") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "showFeedUnreadCounter") }
    }

    /// stickerSize (Android default: 12.0)
    public var stickerSize: Float {
        get { return self.defaults.object(forKey: "stickerSize") as? Float ?? 12.0 }
        set { self.defaults.set(newValue, forKey: "stickerSize") }
    }

    /// stickerTimeMode (Android default: StickerTimeMode.default)
    public var stickerTimeMode: StickerTimeMode {
        get { return StickerTimeMode(rawValue: self.defaults.integer(forKey: "stickerTimeMode")) ?? StickerTimeMode.default }
        set { self.defaults.set(newValue, forKey: "stickerTimeMode") }
    }

    /// replyColors (Android default: true)
    public var replyColors: Bool {
        get { return self.defaults.object(forKey: "replyColors") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "replyColors") }
    }

    /// replyEmoji (Android default: true)
    public var replyEmoji: Bool {
        get { return self.defaults.object(forKey: "replyEmoji") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "replyEmoji") }
    }

    /// replyBackground (Android default: true)
    public var replyBackground: Bool {
        get { return self.defaults.object(forKey: "replyBackground") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "replyBackground") }
    }

    /// stickerShape (Android default: 1)
    public var stickerShape: Int {
        get { return self.defaults.object(forKey: "stickerShape") as? Int ?? 1 }
        set { self.defaults.set(newValue, forKey: "stickerShape") }
    }

    /// unlimitedRecentStickers (Android default: false)
    public var unlimitedRecentStickers: Bool {
        get { return self.defaults.object(forKey: "unlimitedRecentStickers") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "unlimitedRecentStickers") }
    }

    /// hideReactionsInPrivateChats (Android default: false)
    public var hideReactionsInPrivateChats: Bool {
        get { return self.defaults.object(forKey: "hideReactionsInPrivateChats") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideReactionsInPrivateChats") }
    }

    /// hideReactionsInChannels (Android default: false)
    public var hideReactionsInChannels: Bool {
        get { return self.defaults.object(forKey: "hideReactionsInChannels") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideReactionsInChannels") }
    }

    /// hideReactionsInGroups (Android default: false)
    public var hideReactionsInGroups: Bool {
        get { return self.defaults.object(forKey: "hideReactionsInGroups") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideReactionsInGroups") }
    }

    /// doubleTapAction (Android default: 1)
    public var doubleTapAction: Int {
        get { return self.defaults.object(forKey: "doubleTapAction") as? Int ?? 1 }
        set { self.defaults.set(newValue, forKey: "doubleTapAction") }
    }

    /// doubleTapActionOutOwner (Android default: 1)
    public var doubleTapActionOutOwner: Int {
        get { return self.defaults.object(forKey: "doubleTapActionOutOwner") as? Int ?? 1 }
        set { self.defaults.set(newValue, forKey: "doubleTapActionOutOwner") }
    }

    /// swipeActions (Android default: "2")
    public var swipeActions: String {
        get { return self.defaults.string(forKey: "swipeActions") ?? "2" }
        set { self.defaults.set(newValue, forKey: "swipeActions") }
    }

    /// swipeActionsLoop (Android default: false)
    public var swipeActionsLoop: Bool {
        get { return self.defaults.object(forKey: "swipeActionsLoop") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "swipeActionsLoop") }
    }

    /// swipeActionsReversed (Android default: false)
    public var swipeActionsReversed: Bool {
        get { return self.defaults.object(forKey: "swipeActionsReversed") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "swipeActionsReversed") }
    }

    /// bottomButton (Android default: 2)
    public var bottomButton: Int {
        get { return self.defaults.object(forKey: "bottomButton") as? Int ?? 2 }
        set { self.defaults.set(newValue, forKey: "bottomButton") }
    }

    /// widePostsInFeed (Android default: true)
    public var widePostsInFeed: Bool {
        get { return self.defaults.object(forKey: "widePostsInFeed") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "widePostsInFeed") }
    }

    /// widePostsInChannels (Android default: false)
    public var widePostsInChannels: Bool {
        get { return self.defaults.object(forKey: "widePostsInChannels") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "widePostsInChannels") }
    }

    /// telegramAiEditor (Android default: true)
    public var telegramAiEditor: Bool {
        get { return self.defaults.object(forKey: "telegramAiEditor") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "telegramAiEditor") }
    }

    /// telegramAiSummaries (Android default: true)
    public var telegramAiSummaries: Bool {
        get { return self.defaults.object(forKey: "telegramAiSummaries") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "telegramAiSummaries") }
    }

    /// telegramAiInstantViewSummaries (Android default: true)
    public var telegramAiInstantViewSummaries: Bool {
        get { return self.defaults.object(forKey: "telegramAiInstantViewSummaries") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "telegramAiInstantViewSummaries") }
    }

    /// quickAdminShortcuts (Android default: true)
    public var quickAdminShortcuts: Bool {
        get { return self.defaults.object(forKey: "quickAdminShortcuts") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "quickAdminShortcuts") }
    }

    /// quickTransitionForChannels (Android default: true)
    public var quickTransitionForChannels: Bool {
        get { return self.defaults.object(forKey: "quickTransitionForChannels") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "quickTransitionForChannels") }
    }

    /// quickTransitionForTopics (Android default: true)
    public var quickTransitionForTopics: Bool {
        get { return self.defaults.object(forKey: "quickTransitionForTopics") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "quickTransitionForTopics") }
    }

    /// disableGreetingSticker (Android default: false)
    public var disableGreetingSticker: Bool {
        get { return self.defaults.object(forKey: "disableGreetingSticker") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "disableGreetingSticker") }
    }

    /// hideKeyboardOnScroll (Android default: true)
    public var hideKeyboardOnScroll: Bool {
        get { return self.defaults.object(forKey: "hideKeyboardOnScroll") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "hideKeyboardOnScroll") }
    }

    /// addCommaAfterMention (Android default: true)
    public var addCommaAfterMention: Bool {
        get { return self.defaults.object(forKey: "addCommaAfterMention") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "addCommaAfterMention") }
    }

    /// inlineMathResult (Android default: true)
    public var inlineMathResult: Bool {
        get { return self.defaults.object(forKey: "inlineMathResult") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "inlineMathResult") }
    }

    /// disableMarkdown (Android default: false)
    public var disableMarkdown: Bool {
        get { return self.defaults.object(forKey: "disableMarkdown") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "disableMarkdown") }
    }

    /// hideSendAsPeer (Android default: false)
    public var hideSendAsPeer: Bool {
        get { return self.defaults.object(forKey: "hideSendAsPeer") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideSendAsPeer") }
    }

    /// removeMessageTail (Android default: true)
    public var removeMessageTail: Bool {
        get { return self.defaults.object(forKey: "removeMessageTail") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "removeMessageTail") }
    }

    /// replaceEditedWithIcon (Android default: true)
    public var replaceEditedWithIcon: Bool {
        get { return self.defaults.object(forKey: "replaceEditedWithIcon") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "replaceEditedWithIcon") }
    }

    /// showOnlineStatus (Android default: false)
    public var showOnlineStatus: Bool {
        get { return self.defaults.object(forKey: "showOnlineStatus") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showOnlineStatus") }
    }

    /// showForwardsCount (Android default: false)
    public var showForwardsCount: Bool {
        get { return self.defaults.object(forKey: "showForwardsCount") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showForwardsCount") }
    }

    /// hideShareButton (Android default: true)
    public var hideShareButton: Bool {
        get { return self.defaults.object(forKey: "hideShareButton") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "hideShareButton") }
    }

    /// showResultsBeforeVoting (Android default: false)
    public var showResultsBeforeVoting: Bool {
        get { return self.defaults.object(forKey: "showResultsBeforeVoting") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showResultsBeforeVoting") }
    }

    /// showCopyPhotoButton (Android default: true)
    public var showCopyPhotoButton: Bool {
        get { return self.defaults.object(forKey: "showCopyPhotoButton") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "showCopyPhotoButton") }
    }

    /// showSaveMessageButton (Android default: false)
    public var showSaveMessageButton: Bool {
        get { return self.defaults.object(forKey: "showSaveMessageButton") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showSaveMessageButton") }
    }

    /// showRepeatMessageButton (Android default: false)
    public var showRepeatMessageButton: Bool {
        get { return self.defaults.object(forKey: "showRepeatMessageButton") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showRepeatMessageButton") }
    }

    /// showClearButton (Android default: true)
    public var showClearButton: Bool {
        get { return self.defaults.object(forKey: "showClearButton") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "showClearButton") }
    }

    /// showHistoryButton (Android default: false)
    public var showHistoryButton: Bool {
        get { return self.defaults.object(forKey: "showHistoryButton") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showHistoryButton") }
    }

    /// showReportButton (Android default: true)
    public var showReportButton: Bool {
        get { return self.defaults.object(forKey: "showReportButton") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "showReportButton") }
    }

    /// showGenerateButton (Android default: true)
    public var showGenerateButton: Bool {
        get { return self.defaults.object(forKey: "showGenerateButton") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "showGenerateButton") }
    }

    /// showDetailsButton (Android default: false)
    public var showDetailsButton: Bool {
        get { return self.defaults.object(forKey: "showDetailsButton") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showDetailsButton") }
    }

    /// groupMessageMenu (Android default: true)
    public var groupMessageMenu: Bool {
        get { return self.defaults.object(forKey: "groupMessageMenu") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "groupMessageMenu") }
    }

    /// recognitionLanguage (Android default: "none")
    public var recognitionLanguage: String {
        get { return self.defaults.string(forKey: "recognitionLanguage") ?? "none" }
        set { self.defaults.set(newValue, forKey: "recognitionLanguage") }
    }

    /// postprocessingWithAi (Android default: false)
    public var postprocessingWithAi: Bool {
        get { return self.defaults.object(forKey: "postprocessingWithAi") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "postprocessingWithAi") }
    }

    /// cameraType — Android default: CAMERA_X на high-performance классе, иначе CAMERA2. iOS: cameraX.
    public var cameraType: CameraType {
        get { return CameraType(rawValue: self.defaults.integer(forKey: "cameraType")) ?? .cameraX }
        set { self.defaults.set(newValue.rawValue, forKey: "cameraType") }
    }

    /// extendedFramesPerSecond (Android default: false)
    public var extendedFramesPerSecond: Bool {
        get { return self.defaults.object(forKey: "extendedFramesPerSecond") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "extendedFramesPerSecond") }
    }

    /// cameraStabilization (Android default: false)
    public var cameraStabilization: Bool {
        get { return self.defaults.object(forKey: "cameraStabilization") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "cameraStabilization") }
    }

    /// cameraMirrorMode (Android default: true)
    public var cameraMirrorMode: Bool {
        get { return self.defaults.object(forKey: "cameraMirrorMode") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "cameraMirrorMode") }
    }

    /// videoMessagesCamera (Android default: VideoMessagesCamera.front)
    public var videoMessagesCamera: VideoMessagesCamera {
        get { return VideoMessagesCamera(rawValue: self.defaults.integer(forKey: "videoMessagesCamera")) ?? VideoMessagesCamera.front }
        set { self.defaults.set(newValue, forKey: "videoMessagesCamera") }
    }

    /// rememberLastUsedCamera (Android default: false)
    public var rememberLastUsedCamera: Bool {
        get { return self.defaults.object(forKey: "rememberLastUsedCamera") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "rememberLastUsedCamera") }
    }

    /// startWithWideAngleCamera (Android default: false)
    public var startWithWideAngleCamera: Bool {
        get { return self.defaults.object(forKey: "startWithWideAngleCamera") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "startWithWideAngleCamera") }
    }

    /// zoomSlider (Android default: true)
    public var zoomSlider: Bool {
        get { return self.defaults.object(forKey: "zoomSlider") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "zoomSlider") }
    }

    /// staticZoom (Android default: false)
    public var staticZoom: Bool {
        get { return self.defaults.object(forKey: "staticZoom") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "staticZoom") }
    }

    /// alwaysSendInHD (Android default: true)
    public var alwaysSendInHD: Bool {
        get { return self.defaults.object(forKey: "alwaysSendInHD") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "alwaysSendInHD") }
    }

    /// hideCameraTile (Android default: false)
    public var hideCameraTile: Bool {
        get { return self.defaults.object(forKey: "hideCameraTile") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "hideCameraTile") }
    }

    /// doubleTapSeekDuration (Android default: 1)
    public var doubleTapSeekDuration: Int {
        get { return self.defaults.object(forKey: "doubleTapSeekDuration") as? Int ?? 1 }
        set { self.defaults.set(newValue, forKey: "doubleTapSeekDuration") }
    }

    /// preferOriginalQuality (Android default: false)
    public var preferOriginalQuality: Bool {
        get { return self.defaults.object(forKey: "preferOriginalQuality") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "preferOriginalQuality") }
    }

    /// swipeToPip (Android default: false)
    public var swipeToPip: Bool {
        get { return self.defaults.object(forKey: "swipeToPip") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "swipeToPip") }
    }

    /// unmuteWithVolumeButtons (Android default: false)
    public var unmuteWithVolumeButtons: Bool {
        get { return self.defaults.object(forKey: "unmuteWithVolumeButtons") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "unmuteWithVolumeButtons") }
    }

    /// pauseOnMinimizeVideo (Android default: true)
    public var pauseOnMinimizeVideo: Bool {
        get { return self.defaults.object(forKey: "pauseOnMinimizeVideo") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "pauseOnMinimizeVideo") }
    }

    /// pauseOnMinimizeVoice (Android default: false)
    public var pauseOnMinimizeVoice: Bool {
        get { return self.defaults.object(forKey: "pauseOnMinimizeVoice") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pauseOnMinimizeVoice") }
    }

    /// pauseOnMinimizeRound (Android default: false)
    public var pauseOnMinimizeRound: Bool {
        get { return self.defaults.object(forKey: "pauseOnMinimizeRound") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pauseOnMinimizeRound") }
    }

    /// useGoogleCrashlytics (Android default: false)
    public var useGoogleCrashlytics: Bool {
        get { return self.defaults.object(forKey: "useGoogleCrashlytics") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "useGoogleCrashlytics") }
    }

    /// useGoogleAnalytics (Android default: false)
    public var useGoogleAnalytics: Bool {
        get { return self.defaults.object(forKey: "useGoogleAnalytics") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "useGoogleAnalytics") }
    }

    /// enableAdBlock (Android default: true)
    public var enableAdBlock: Bool {
        get { return self.defaults.object(forKey: "enableAdBlock") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "enableAdBlock") }
    }

    /// updateScheduleTimestamp (Android default: 0)
    public var updateScheduleTimestamp: Int64 {
        get { return self.defaults.object(forKey: "updateScheduleTimestamp") as? Int64 ?? 0 }
        set { self.defaults.set(newValue, forKey: "updateScheduleTimestamp") }
    }

    /// sdkUpdateScheduleTimestamp (Android default: 0)
    public var sdkUpdateScheduleTimestamp: Int64 {
        get { return self.defaults.object(forKey: "sdkUpdateScheduleTimestamp") as? Int64 ?? 0 }
        set { self.defaults.set(newValue, forKey: "sdkUpdateScheduleTimestamp") }
    }

    /// targetLang (Android default: "Common.ASSET_APP")
    public var targetLang: String {
        get { return self.defaults.string(forKey: "targetLang") ?? "Common.ASSET_APP" }
        set { self.defaults.set(newValue, forKey: "targetLang") }
    }

    /// flashWarmth (Android default: 0.5)
    public var flashWarmth: Float {
        get { return self.defaults.object(forKey: "flashWarmth") as? Float ?? 0.5 }
        set { self.defaults.set(newValue, forKey: "flashWarmth") }
    }

    /// flashIntensity (Android default: 1.0)
    public var flashIntensity: Float {
        get { return self.defaults.object(forKey: "flashIntensity") as? Float ?? 1.0 }
        set { self.defaults.set(newValue, forKey: "flashIntensity") }
    }

    /// pluginsDevMode (Android default: false)
    public var pluginsDevMode: Bool {
        get { return self.defaults.object(forKey: "pluginsDevMode") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsDevMode") }
    }

    /// pluginsSafeMode (Android default: false)
    public var pluginsSafeMode: Bool {
        get { return self.defaults.object(forKey: "pluginsSafeMode") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsSafeMode") }
    }

    /// pluginsCompactView (Android default: false)
    public var pluginsCompactView: Bool {
        get { return self.defaults.object(forKey: "pluginsCompactView") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsCompactView") }
    }

    /// pluginsPySdkAutoUpdate (Android default: false)
    public var pluginsPySdkAutoUpdate: Bool {
        get { return self.defaults.object(forKey: "pluginsPySdkAutoUpdate") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsPySdkAutoUpdate") }
    }

    /// pluginsPySdkBetaVersions (Android default: false)
    public var pluginsPySdkBetaVersions: Bool {
        get { return self.defaults.object(forKey: "pluginsPySdkBetaVersions") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsPySdkBetaVersions") }
    }

    /// pluginsDisableArtOpts (Android default: false)
    public var pluginsDisableArtOpts: Bool {
        get { return self.defaults.object(forKey: "pluginsDisableArtOpts") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsDisableArtOpts") }
    }

    /// pluginsUnknownSources (Android default: false)
    public var pluginsUnknownSources: Bool {
        get { return self.defaults.object(forKey: "pluginsUnknownSources") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "pluginsUnknownSources") }
    }

    /// pinnedPlugins (Android default: [])
    public var pinnedPlugins: [String] {
        get { return self.defaults.stringArray(forKey: "pinnedPlugins") ?? [] }
        set { self.defaults.set(newValue, forKey: "pinnedPlugins") }
    }

    /// useSystemIconShape (Android default: true)
    public var useSystemIconShape: Bool {
        get { return self.defaults.object(forKey: "useSystemIconShape") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "useSystemIconShape") }
    }

    // MARK: Списочные настройки мода (сериализуются JSON, как Gson в Android)

    /// doNotMarkAsNew: список chat id, не помечаемых «новыми»
    public var doNotMarkAsNew: [Int64] {
        get { return decodeArray("doNotMarkAsNew") }
        set { encodeArray(newValue, "doNotMarkAsNew") }
    }

    /// newFeaturesShowedAt: feature -> timestamp
    public var newFeaturesShowedAt: [String: Int64] {
        get { return decodeDict("newFeaturesShowedAt") }
        set { encodeDict(newValue, "newFeaturesShowedAt") }
    }

    /// iconPacksLayout / iconPacksHidden: порядок и скрытость внешних icon packs
    public var iconPacksLayout: [String] {
        get { return self.defaults.stringArray(forKey: "iconPacksLayout") ?? [] }
        set { self.defaults.set(newValue, forKey: "iconPacksLayout") }
    }

    public var iconPacksHidden: [String] {
        get { return self.defaults.stringArray(forKey: "iconPacksHidden") ?? [] }
        set { self.defaults.set(newValue, forKey: "iconPacksHidden") }
    }

    /// mainMenuLayout / mainMenuHiddenItems: редактор главного меню
    public var mainMenuLayout: [Int] {
        get { return (self.defaults.array(forKey: "mainMenuLayout") as? [Int]) ?? Self.defaultMainMenuLayout }
        set { self.defaults.set(newValue, forKey: "mainMenuLayout") }
    }

    public var mainMenuHiddenItems: [Int] {
        get { return (self.defaults.array(forKey: "mainMenuHiddenItems") as? [Int]) ?? [] }
        set { self.defaults.set(newValue, forKey: "mainMenuHiddenItems") }
    }

    /// Порт getDefaultMainMenuLayout()
    public static let defaultMainMenuLayout: [Int] = [
        MainMenuItem.contacts.rawValue,
        MainMenuItem.calls.rawValue,
        MainMenuItem.savedMessages.rawValue,
        MainMenuItem.settings.rawValue,
        MainMenuItem.inviteFriends.rawValue,
        MainMenuItem.telegramFeatures.rawValue,
        MainMenuItem.nightMode.rawValue,
    ]

    /// Порт getAvatarCorners(px, forum, community)
    public static func avatarCorners(px: Float, forum: Bool, community: Bool, base: Float) -> Float {
        if forum { return min(px, px * 0.62) }
        if community { return min(px, px * 0.72) }
        return min(px, base)
    }

    /// Порт isProxyDisabledOn(condition)
    public func isProxyDisabledOn(_ condition: ProxyDisableCondition) -> Bool {
        return ProxyDisableCondition(rawValue: self.doNotUseProxy) == condition
    }

    // MARK: - Helpers

    private func decodeArray(_ key: String) -> [Int64] {
        guard let data = self.defaults.data(forKey: key) else { return [] }
        return (try? JSONDecoder().decode([Int64].self, from: data)) ?? []
    }

    private func encodeArray(_ value: [Int64], _ key: String) {
        if let data = try? JSONEncoder().encode(value) {
            self.defaults.set(data, forKey: key)
        }
    }

    private func decodeDict(_ key: String) -> [String: Int64] {
        guard let data = self.defaults.data(forKey: key) else { return [:] }
        return (try? JSONDecoder().decode([String: Int64].self, from: data)) ?? [:]
    }

    private func encodeDict(_ value: [String: Int64], _ key: String) {
        if let data = try? JSONEncoder().encode(value) {
            self.defaults.set(data, forKey: key)
        }
    }
}
