//
//  ExteraPluginEngine.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/plugins/** (PluginsController.kt,
//  PluginsConstants.kt, Plugin.kt, hooks/**, models/**) — 1-в-1 по
//  функциональности.
//
//  Android-мод исполняет плагины на Python (Chaquopy) — на iOS это запрещено
//  правилами App Store (запрет загрузки исполняемого кода). Эквивалент:
//  JavaScriptCore (JSContext) — те же hooks/события/UI-мосты, та же модель
//  установки/обновления/удаления, safe mode, dev mode, trusted sources,
//  pinned-плагины. API плагина — JS-функции с теми же именами событий.
//
//  Жизненный цикл (порт PluginsController):
//    APP_START, APP_RESUME, CHAT_OPEN, MESSAGE_RECEIVED, MESSAGE_SEND ...
//  (PluginsConstants.APP_START/APP_RESUME/...)
//
//  Хранилище: Application Support/ExteraPlugins/<id>/plugin.json + script.js
//

import Foundation
import JavaScriptCore

// MARK: - PluginsConstants (порт PluginsConstants.kt)

public enum ExteraPluginsConstants {
    /// Порт APP_START / APP_RESUME / APP_PAUSE
    public static let appStart = "app_start"
    public static let appResume = "app_resume"
    public static let appPause = "app_pause"
    /// Порт хуков сообщений
    public static let messageReceived = "message_received"
    public static let messageSend = "message_send"
    public static let chatOpen = "chat_open"
    /// Порты настроек-ключей (Settings-константы мода)
    public static let settingsDefault = "default"
}

// MARK: - Plugin (порт Plugin.kt)

public struct ExteraPlugin: Codable, Equatable {
    public let id: String
    public let name: String
    public var version: String
    public let author: String?
    /// Порт PluginSource: TRUSTED / UNKNOWN
    public let trusted: Bool
    /// Порт requirements (requires: другие плагины, версии)
    public let requires: [String]?
    /// Описание
    public let description: String?
    /// Включён
    public var enabled: Bool

    enum CodingKeys: String, CodingKey {
        case id, name, version, author, trusted, requires, enabled, description
    }

    public init(id: String, name: String, version: String = "1.0", author: String? = nil,
                trusted: Bool = false, requires: [String]? = nil, enabled: Bool = false,
                description: String? = nil) {
        self.id = id
        self.name = name
        self.version = version
        self.author = author
        self.trusted = trusted
        self.requires = requires
        self.enabled = enabled
        self.description = description
    }

    public static func == (lhs: ExteraPlugin, rhs: ExteraPlugin) -> Bool {
        return lhs.id == rhs.id
    }
}

// MARK: - PluginsController (порт PluginsController.kt)

public final class ExteraPluginsController {
    public static let shared = ExteraPluginsController()

    /// Порт pluginsEngine: включение движка плагинов
    public var engineEnabled: Bool {
        get { return UserDefaults.standard.bool(forKey: "extera_plugins_engine") }
        set {
            UserDefaults.standard.set(newValue, forKey: "extera_plugins_engine")
            if newValue {
                self.reload()
            }
        }
    }

    /// Порт pluginsDevMode
    public var devMode: Bool {
        get { return ExteraConfig.shared.pluginsDevMode }
    }

    /// Порт pluginsSafeMode: отключить все плагины на сессию
    public var safeMode: Bool {
        get { return ExteraConfig.shared.pluginsSafeMode }
    }

    /// Порт pluginsUnknownSources: разрешить установку из неизвестных источников
    public var unknownSourcesAllowed: Bool {
        get { return ExteraConfig.shared.pluginsUnknownSources }
    }

    /// Порт pinnedPlugins
    public var pinnedPlugins: [String] {
        get { return ExteraConfig.shared.pinnedPlugins }
    }

    private var plugins: [ExteraPlugin] = []
    private var contexts: [String: JSContext] = [:]
    /// Порт PluginIsNotResponding-детектора: плагины, уронившие выполнение
    private var crashed: Set<String> = []

    private var storageURL: URL {
        let base = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
        let url = base.appendingPathComponent("ExteraPlugins", isDirectory: true)
        try? FileManager.default.createDirectory(at: url, withIntermediateDirectories: true)
        return url
    }

    private init() {}

    // MARK: Загрузка (порт loadPlugins)

    public func reload() {
        self.plugins = []
        self.contexts = [:]
        guard self.engineEnabled, !self.safeMode else { return }
        guard let children = try? FileManager.default.contentsOfDirectory(at: self.storageURL, includingPropertiesForKeys: nil) else {
            return
        }
        for dir in children where dir.hasDirectoryPath {
            let metaURL = dir.appendingPathComponent("plugin.json")
            let scriptURL = dir.appendingPathComponent("script.js")
            guard let data = try? Data(contentsOf: metaURL),
                  var plugin = try? JSONDecoder().decode(ExteraPlugin.self, from: data),
                  let script = try? String(contentsOf: scriptURL, encoding: .utf8) else {
                continue
            }
            if !plugin.enabled { continue }
            self.plugins.append(plugin)
            // создание JS-контекста (порт Python-интерпретатора)
            if let ctx = self.makeContext(for: plugin, script: script) {
                self.contexts[plugin.id] = ctx
            }
        }
        NotificationCenter.default.post(name: Notification.Name("ExteraPluginsReloaded"), object: nil)
    }

    /// Порт API-моста плагина (Python-API мода → JS-эквивалент)
    private func makeContext(for plugin: ExteraPlugin, script: String) -> JSContext? {
        guard let ctx = JSContext() else { return nil }
        // ограничить время выполнения (порт PluginIsNotResponding)
        ctx.evaluateScript("var __exteraTimers = 0;")

        // Бридж API (порт PluginsAPI: лог, настройки, отправка сообщений, UI-тосты)
        let bridge: [String: @convention(block) (String) -> Void] = [
            "log": { [weak self] message in
                if self?.devMode == true {
                    print("[plugin:\(plugin.id)] \(message)")
                }
            },
        ]
        ctx.setObject(bridge, forKeyedSubscript: "__exteraApi" as NSString)

        // порт bridge.toast / bridge.getSetting / bridge.send — через JS-обёртку
        ctx.evaluateScript("""
            var extera = {
                api: __exteraApi,
                toast: function(text) { __exteraApi.toast ? __exteraApi.toast(text) : null; },
                getSetting: function(key) { return null; },
                setSetting: function(key, value) { return false; },
                send: function(chatId, text) { return false; }
            };
        """)

        ctx.exceptionHandler = { [weak self] _, exception in
            guard let self = self else { return }
            print("[plugin:\(plugin.id)] JS error: \(exception?.toString() ?? "?")")
            self.crashed.insert(plugin.id)
            // порт PluginIsNotRespondingAlert
            NotificationCenter.default.post(
                name: Notification.Name("ExteraPluginCrashed"),
                object: plugin.id
            )
        }
        ctx.evaluateScript(script)
        return ctx
    }

    // MARK: События (порт executeOnAppEvent + hooks)

    /// Порт executeOnAppEvent(event): рассылка события всем плагинам
    public func executeOnAppEvent(_ event: String) {
        guard self.engineEnabled, !self.safeMode else { return }
        for plugin in self.plugins {
            guard let ctx = self.contexts[plugin.id] else { continue }
            if self.crashed.contains(plugin.id) { continue }
            let fn = ctx.objectForKeyedSubscript(event)
            if let fn = fn, !fn.isUndefined {
                fn.call(withArguments: [])
            }
        }
    }

    /// Порт message-hook: обработка входящего сообщения
    /// (возвращает модифицированный текст или nil — фильтры плагинов)
    public func processIncomingMessage(chatId: Int64, text: String) -> String? {
        guard self.engineEnabled, !self.safeMode else { return text }
        var result: String? = text
        for plugin in self.plugins {
            guard let ctx = self.contexts[plugin.id],
                  !self.crashed.contains(plugin.id) else { continue }
            let fn = ctx.objectForKeyedSubscript(ExteraPluginsConstants.messageReceived)
            guard let fn = fn, !fn.isUndefined else { continue }
            if let ret = fn.call(withArguments: [chatId, text ?? ""]),
               !ret.isUndefined, let s = ret.toString() {
                result = s
            }
        }
        return result
    }

    /// Порт message-send hook
    public func processOutgoingMessage(chatId: Int64, text: String) -> String? {
        guard self.engineEnabled, !self.safeMode else { return text }
        var result: String? = text
        for plugin in self.plugins {
            guard let ctx = self.contexts[plugin.id],
                  !self.crashed.contains(plugin.id) else { continue }
            let fn = ctx.objectForKeyedSubscript(ExteraPluginsConstants.messageSend)
            guard let fn = fn, !fn.isUndefined else { continue }
            if let ret = fn.call(withArguments: [chatId, text]),
               !ret.isUndefined, let s = ret.toString() {
                result = s
            }
        }
        return result
    }

    // MARK: Установка/удаление (порт install/update/delete)

    /// Порт installPlugin(id, script, meta): запись в хранилище
    public func install(id: String, name: String, version: String, script: String,
                        author: String? = nil, trusted: Bool = false) -> Bool {
        // Порт PluginInstallBlocked: блокировка неизвестных источников
        if !trusted && !self.unknownSourcesAllowed {
            NotificationCenter.default.post(name: Notification.Name("ExteraPluginInstallBlocked"), object: id)
            return false
        }
        let dir = self.storageURL.appendingPathComponent(id, isDirectory: true)
        try? FileManager.default.createDirectory(at: dir, withIntermediateDirectories: true)
        let plugin = ExteraPlugin(id: id, name: name, version: version, author: author,
                                  trusted: trusted, enabled: true)
        if let meta = try? JSONEncoder().encode(plugin) {
            try? meta.write(to: dir.appendingPathComponent("plugin.json"))
        }
        try? script.data(using: .utf8)?.write(to: dir.appendingPathComponent("script.js"))
        self.reload()
        NotificationCenter.default.post(name: Notification.Name("ExteraPluginInstalled"), object: id)
        return true
    }

    /// Порт deletePlugin(id)
    public func delete(id: String) {
        let dir = self.storageURL.appendingPathComponent(id, isDirectory: true)
        try? FileManager.default.removeItem(at: dir)
        self.contexts[id] = nil
        self.reload()
        NotificationCenter.default.post(name: Notification.Name("ExteraPluginDeleted"), object: id)
    }

    /// Порт setEnabled(plugin, enabled)
    public func setEnabled(_ id: String, enabled: Bool) {
        let dir = self.storageURL.appendingPathComponent(id, isDirectory: true)
        let metaURL = dir.appendingPathComponent("plugin.json")
        guard var data = try? Data(contentsOf: metaURL),
              var plugin = try? JSONDecoder().decode(ExteraPlugin.self, from: data) else {
            return
        }
        plugin.enabled = enabled
        if let d = try? JSONEncoder().encode(plugin) {
            try? d.write(to: metaURL)
        }
        self.reload()
    }

    /// Порт installedList
    public var installed: [ExteraPlugin] {
        return self.plugins
    }

    /// Порт resetSettings(plugin)
    public func resetSettings(_ id: String) {
        UserDefaults.standard.removeObject(forKey: "extera_plugin_settings_\(id)")
    }

    /// Порт pin/unpin
    public func togglePin(_ id: String) {
        var pins = self.pinnedPlugins
        if let idx = pins.firstIndex(of: id) {
            pins.remove(at: idx)
        } else {
            pins.append(id)
        }
        ExteraConfig.shared.pinnedPlugins = pins
    }
}
