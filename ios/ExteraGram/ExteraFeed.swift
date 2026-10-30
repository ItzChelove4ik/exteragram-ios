//
//  ExteraFeed.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/feed/** (FeedConfig.kt, FeedChannelRegistry.kt,
//  FeedStore.kt, FeedController.kt, FeedUnreadTracker.kt, FeedChatIntegration.kt,
//  FeedChannelActions.kt) — 1-в-1.
//
//  Feed — отдельная вкладка «Лента»: посты из выбранных каналов, исключённые
//  каналы (excludedChannels + excludeAll), включение архивных, счётчик непрочитанных.
//
//  Хранение: UserDefaults("feedconfig") — тот же ключ, что у мода.
//  Лента постов строится поверх postbox-истории каналов telegram-ios
//  (см. migration-map F1: интеграция в ChatHistoryEntries).
//

import Foundation

// MARK: - FeedConfig (порт FeedConfig.kt)

public final class ExteraFeedConfig {
    public static let shared = ExteraFeedConfig()

    /// SharedPreferences("feedconfig") мода
    private let defaults: UserDefaults

    /// Порт excludedChannels: список id исключённых каналов
    public var excludedChannels: [Int64] {
        get {
            guard let data = self.defaults.data(forKey: "excludedChannels") else { return [] }
            return (try? JSONDecoder().decode([Int64].self, from: data)) ?? []
        }
        set {
            if let data = try? JSONEncoder().encode(newValue) {
                self.defaults.set(data, forKey: "excludedChannels")
            }
        }
    }

    /// Порт excludeAll: true = все каналы исключены (лента пустая, все скрыты)
    public var excludeAll: Bool {
        get { return self.defaults.bool(forKey: "excludeAll") }
        set { self.defaults.set(newValue, forKey: "excludeAll") }
    }

    /// Порт includeArchived: включать архивированные каналы в ленту
    public var includeArchived: Bool {
        get { return self.defaults.bool(forKey: "includeArchived") }
        set { self.defaults.set(newValue, forKey: "includeArchived") }
    }

    /// Порт showFeedTab: включение вкладки ленты
    public var showFeedTab: Bool {
        get { return ExteraConfig.shared.showFeedTab }
        set { ExteraConfig.shared.showFeedTab = newValue }
    }

    /// Порт showFeedUnreadCounter: счётчик непрочитанных ленты
    public var showUnreadCounter: Bool {
        get { return ExteraConfig.shared.showFeedUnreadCounter }
    }

    private init() {
        self.defaults = UserDefaults(suiteName: "feedconfig") ?? .standard
    }

    // MARK: Порт excluded-snapshot API (Snapshot-класс мода)

    /// Порт Snapshot: атомарный снимок конфигурации ленты
    public struct Snapshot {
        public let excludedChannels: Set<Int64>
        public let excludeAll: Bool
        public let includeArchived: Bool
        public let generation: Int

        /// Порт isExcluded(dialogId)
        public func isExcluded(_ dialogId: Int64) -> Bool {
            if self.excludeAll { return true }
            return self.excludedChannels.contains(dialogId)
        }
    }

    /// Порт getExcludedSnapshot()
    public func snapshot() -> Snapshot {
        return Snapshot(
            excludedChannels: Set(self.excludedChannels),
            excludeAll: self.excludeAll,
            includeArchived: self.includeArchived,
            generation: self.generation
        )
    }

    /// Порт generation: инкремент при каждом изменении (для инвалидации кэшей)
    public var generation: Int {
        get { return self.defaults.integer(forKey: "generation") }
    }

    /// Инкремент generation (внутренний)
    func bumpGeneration() {
        let next = self.generation + 1
        self.defaults.set(next, forKey: "generation")
    }

    // MARK: Изменения (порт applyExcluded/removeExcluded/setExcluded/clearExcluded)

    /// Порт setExcluded(dialogId, excluded)
    public func setExcluded(_ dialogId: Int64, _ excluded: Bool) {
        var list = self.excludedChannels
        if excluded {
            if !list.contains(dialogId) { list.append(dialogId) }
        } else {
            list.removeAll { $0 == dialogId }
        }
        self.excludedChannels = list
        self.bump()
    }

    /// Порт removeExcluded(dialogId)
    public func removeExcluded(_ dialogId: Int64) {
        self.setExcluded(dialogId, false)
    }

    /// Порт clearExcluded(): вернуть все каналы в ленту
    public func clearExcluded() {
        self.excludedChannels = []
        self.excludeAll = false
        self.bump()
    }

    private func bump() {
        self.bumpGeneration()
        NotificationCenter.default.post(name: Notification.Name("ExteraFeedChanged"), object: nil)
    }
}

// MARK: - FeedChannelRegistry (порт FeedChannelRegistry.kt)

public final class ExteraFeedChannelRegistry {
    public static let shared = ExteraFeedChannelRegistry()

    /// Каналы ленты: id -> метаданные (порт registry-кэша)
    private var channels: [Int64: ChannelInfo] = [:]

    public struct ChannelInfo {
        public let id: Int64
        public let title: String
        public let username: String?
        public let archived: Bool
        public let hasPosts: Bool
    }

    private init() {}

    /// Порт update(channels): обновление реестра из ChatList
    public func update(_ list: [ChannelInfo]) {
        var map: [Int64: ChannelInfo] = [:]
        for c in list {
            map[c.id] = c
        }
        self.channels = map
        NotificationCenter.default.post(name: Notification.Name("ExteraFeedRegistryUpdated"), object: nil)
    }

    /// Порт shownChannels: видимые в ленте (не исключённые, есть посты)
    public func shownChannels() -> [ChannelInfo] {
        let snap = ExteraFeedConfig.shared.snapshot()
        return self.channels.values
            .filter { !snap.isExcluded($0.id) && $0.hasPosts && (snap.includeArchived || !$0.archived) }
            .sorted { $0.title < $1.title }
    }

    /// Порт hiddenChannels: исключённые
    public func hiddenChannels() -> [ChannelInfo] {
        let snap = ExteraFeedConfig.shared.snapshot()
        return self.channels.values
            .filter { snap.isExcluded($0.id) && $0.hasPosts }
            .sorted { $0.title < $1.title }
    }

    /// Порт hideChannel / unhideChannel
    public func hideChannel(_ id: Int64) {
        ExteraFeedConfig.shared.setExcluded(id, true)
    }

    public func unhideChannel(_ id: Int64) {
        ExteraFeedConfig.shared.setExcluded(id, false)
    }
}

// MARK: - FeedUnreadTracker (порт FeedUnreadTracker.kt)

public final class ExteraFeedUnreadTracker {
    public static let shared = ExteraFeedUnreadTracker()

    /// Непрочитанные по каналам ленты: channelId -> count (порт тикера)
    private var unread: [Int64: Int] = [:]
    /// Позиция прочтения ленты (порт readState)
    private var lastReadMessageId: [Int64: Int64] = [:]

    private init() {}

    /// Порт setUnread(channelId, count)
    public func setUnread(_ channelId: Int64, count: Int) {
        self.unread[channelId] = count
    }

    /// Порт totalUnread: суммарный счётчик ленты
    public var totalUnread: Int {
        guard ExteraFeedConfig.shared.showUnreadCounter else { return 0 }
        let snap = ExteraFeedConfig.shared.snapshot()
        var total = 0
        for (channelId, count) in self.unread {
            if !snap.isExcluded(channelId) {
                total += count
            }
        }
        return total
    }

    /// Порт markAllRead(): пометить всю ленту прочитанной
    public func markAllRead() {
        self.unread = [:]
        NotificationCenter.default.post(name: Notification.Name("ExteraFeedRead"), object: nil)
    }

    /// Порт формата счётчика
    public var unreadBadgeText: String? {
        let n = self.totalUnread
        guard n > 0 else { return nil }
        return ExteraNumberFormatter.format(n)
    }
}

// MARK: - FeedController (порт FeedController.kt: сборка ленты постов)

public final class ExteraFeedController {
    public static let shared = ExteraFeedController()

    /// Элемент ленты: пост из канала (порт FeedMessageIdentityMap-записи)
    public struct FeedItem {
        public let channelId: Int64
        public let messageId: Int64
        public let timestamp: Int64
    }

    /// Порт maxFeedCapacity: буфер ленты
    public static let maxCapacity = 500

    private init() {}

    /// Порт loadTimeline: собрать ленту из последних постов каналов.
    /// Интеграция: истории каналов передаются из telegram-ios (postbox);
    /// здесь — сортировка/фильтрация (порт FeedTimelineLoader).
    public func buildTimeline(posts: [FeedItem]) -> [FeedItem] {
        let snap = ExteraFeedConfig.shared.snapshot()
        var registry: Set<Int64> = []
        for item in posts {
            registry.insert(item.channelId)
        }
        _ = registry
        let filtered = posts.filter { !snap.isExcluded($0.channelId) }
        let sorted = filtered.sorted { $0.timestamp > $1.timestamp }
        return Array(sorted.prefix(Self.maxCapacity))
    }

    /// Порт markChannelHidden (FeedChannelActions.hideChannel)
    public func hideChannel(_ id: Int64) {
        ExteraFeedChannelRegistry.shared.hideChannel(id)
    }
}

// MARK: - FeedChatIntegration (порт FeedChatIntegration.kt)

public enum ExteraFeedChatIntegration {
    /// Порт shouldShowInFeed(chat): канал-канал с постами попадает в ленту
    public static func isFeedEligible(isChannel: Bool, isBroadcast: Bool, archived: Bool) -> Bool {
        guard isChannel || isBroadcast else { return false }
        if archived && !ExteraFeedConfig.shared.includeArchived { return false }
        return true
    }

    /// Порт widePosts в ленте (widePostsInFeed)
    public static var widePostsInFeed: Bool {
        return ExteraConfig.shared.widePostsInFeed
    }
}
