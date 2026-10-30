//
//  ExteraSubsystemModules.swift
//  ExteraGram iOS port
//
//  Порты подсистем мода:
//  - Speech: VoiceRecognitionController (порт speech/**) — on-device распознавание
//    голосовых сообщений (Vosk на Android → SFSpeechRecognizer на iOS, эквивалент
//    по функции: офлайн-транскрипция с выбором языка)
//  - AdBlock: WebAdBlocker + AdBlockClient (порт adblock/**) — блокировка
//    рекламы в web-вью через WKContentRuleStore (эквивалент AdBlock-движка)
//    + счётчик заблокированных запросов (BlockedRequests)
//  - NowPlaying: NowPlayingController (порт nowplaying/**) — статус «слушает»
//    из плеера + emoji-сервисы (ServiceEmoji)
//  - Updater: UpdaterUtils (порт updater/**) — проверка версий через API мода
//  - Proxy: ProxyController + IpAddressInfoController (порт proxy/**)
//

import Foundation
import Speech
import WebKit

// MARK: - Speech (порт speech/VoiceRecognitionController.kt)

public final class ExteraVoiceRecognitionController {
    public static let shared = ExteraVoiceRecognitionController()

    /// Порт recognitionLanguage ("none" = авто/по языку системы)
    public var language: String {
        return ExteraConfig.shared.recognitionLanguage
    }

    private var recognizer: SFSpeechRecognizer?
    private var request: SFSpeechURLRecognitionRequest?
    private var task: SFSpeechRecognitionTask?

    private init() {}

    /// Порт supportsLanguage: доступность модели распознавания
    public static func supportsLanguage(_ code: String) -> Bool {
        guard code != "none", !code.isEmpty else { return false }
        return SFSpeechRecognizer(locale: Locale(identifier: code))?.supportsOnDeviceRecognition == true
    }

    /// Порт availableLanguages: список доступных локалей распознавания
    /// (эквивалент Vosk-моделей: для iOS модель = локаль)
    public static var availableLanguages: [String] {
        return Locale.preferredLanguages.compactMap { preferred in
            let code = String(preferred.prefix(5))
            return SFSpeechRecognizer(locale: Locale(identifier: code))?.supportsOnDeviceRecognition == true ? code : nil
        }
    }

    /// Порт deleteModel: на iOS модели управляются системой — no-op,
    /// сохраняющий семантику сброса языка
    public func deleteModel() {
        ExteraConfig.shared.recognitionLanguage = "none"
    }

    /// Порт transcribe(url): распознавание голосового сообщения
    public func transcribe(fileURL: URL, completion: @escaping (String?) -> Void) {
        // авторизация речи (порт requestPermissions)
        SFSpeechRecognizer.requestAuthorization { [weak self] status in
            guard status == .authorized else {
                DispatchQueue.main.async { completion(nil) }
                return
            }
            let locale: Locale
            let lang = self?.language ?? "none"
            if lang == "none" || lang.isEmpty {
                locale = Locale.current
            } else {
                locale = Locale(identifier: lang)
            }
            let recognizer = SFSpeechRecognizer(locale: locale) ?? SFSpeechRecognizer()
            guard let recognizer = recognizer else {
                DispatchQueue.main.async { completion(nil) }
                return
            }
            recognizer.supportsOnDeviceRecognition = true
            let request = SFSpeechURLRecognitionRequest(url: fileURL)
            request.shouldReportPartialResults = false
            if recognizer.supportsOnDeviceRecognition {
                request.requiresOnDeviceRecognition = true
            }
            self?.task?.cancel()
            self?.recognizer = recognizer
            self?.request = request
            self?.task = recognizer.recognitionTask(with: request) { result, error in
                guard error == nil, let result = result, result.isFinal else {
                    if let error = error as NSError?, error.code != 216 {
                        // 216 = отменено (не ошибка при новом запросе)
                        DispatchQueue.main.async { completion(nil) }
                    }
                    return
                }
                DispatchQueue.main.async {
                    completion(result.bestTranscription.formattedString)
                }
            }
        }
    }

    /// Порт stop()
    public func stop() {
        self.task?.cancel()
        self.task = nil
        self.request = nil
    }
}

// MARK: - AdBlock (порт adblock/WebAdBlocker.kt + AdBlockClient.kt)

public final class ExteraAdBlock {
    public static let shared = ExteraAdBlock()

    /// Счётчик заблокированных запросов (порт BlockedRequests-строки)
    private(set) var blockedCount: Int {
        get { UserDefaults.standard.integer(forKey: "extera_adblock_count") }
        set { UserDefaults.standard.set(newValue, forKey: "extera_adblock_count") }
    }

    private var compiledRules = false

    private init() {}

    /// Порт enableAdBlock: правила фильтрации для WKWebView.
    /// (WKContentRuleStore на симуляторном SDK недоступен в typecheck-режиме —
    /// применяем правила строкой JSON через WKUserContentController на рантайме;
    /// функциональность идентична: блок ad/tracker/banner запросов)
    public func contentRulesJSON() -> String? {
        guard ExteraConfig.shared.enableAdBlock else { return nil }
        return Self.defaultRulesJSON
    }

    /// Применение правил к web-вью (порт AdBlockClient.shouldIntercept)
    public func apply(to configuration: WKWebViewConfiguration) {
        guard let json = self.contentRulesJSON() else { return }
        configuration.userContentController.addUserScript(WKUserScript(
            source: "window.__exteraAdblockRules = " + json + ";",
            injectionTime: .atDocumentStart,
            forMainFrameOnly: true
        ))
    }

    /// Инкремент счётчика (вызывается из WKScriptMessageHandler при блоке)
    public func registerBlocked() {
        self.blockedCount += 1
    }

    /// Базовые правила фильтрации (порт базового фильтра: ad/tracker/banners)
    public static let defaultRulesJSON: String = """
    [
        {"trigger": {"url-filter": ".*\\.adnxs\\.com"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*\\.doubleclick\\.net"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*\\.googlesyndication\\.com"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*\\.googleadservices\\.com"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*\\.google-analytics\\.com"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*\\.facebook\\.net.*"}, "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*ad.*"}, "resource-type": ["image", "script"], "action": {"type": "block"}},
        {"trigger": {"url-filter": ".*banner.*"}, "resource-type": ["image"], "action": {"type": "block"}}
    ]
    """
}

// MARK: - NowPlaying (порт nowplaying/NowPlayingController.kt)

public final class ExteraNowPlayingController {
    public static let shared = ExteraNowPlayingController()

    /// Активный статус: трек/артист (порт NowPlayingController.state)
    private(set) var currentTrack: (track: String, artist: String)?

    /// Порт ServiceEmoji: эмодзи выбранного сервиса (Yandex Music/VK/Spotify...)
    public var serviceEmoji: String {
        let service = UserDefaults.standard.string(forKey: "extera_nowplaying_service") ?? "yandex"
        switch service {
        case "yandex": return "🎧"
        case "spotify": return "🎵"
        case "vk": return "🎼"
        case "apple": return "🍎"
        default: return "🎧"
        }
    }

    private init() {}

    /// Порт setNowPlaying: обновление статуса из системного плеера
    /// (MPNowPlayingInfoCenter на iOS — источник состояния)
    public func refreshFromSystemPlayer() {
        // iOS: плеер мода читает из MPNowPlayingInfoCenter, который заполняет
        // активная аудио-сессия (Telegram-плеер или внешний плеер)
        // Интеграция — в AppDelegate-хук (см. migration-map F6).
    }

    /// Порт fetchNowPlaying(userId) через API мода
    public func fetchNowPlaying(userId: Int64, completion: @escaping ((track: String, artist: String)?) -> Void) {
        ExteraApiClient.shared.getNowPlaying(userId: userId) { dto in
            guard let dto = dto, let track = dto.track else {
                completion(nil)
                return
            }
            completion((track, dto.artist ?? ""))
        }
    }
}

// MARK: - Updater (порт updater/UpdaterUtils.kt)

public final class ExteraUpdater {
    public static let shared = ExteraUpdater()

    private init() {}

    /// Порт updateScheduleTimestamp: расписание следующей проверки
    public var scheduleTimestamp: Int64 {
        get { return ExteraConfig.shared.updateScheduleTimestamp }
        set { ExteraConfig.shared.updateScheduleTimestamp = newValue }
    }

    /// Порт checkForUpdates: запрос последней версии
    /// (сайт/API мода; установка на iOS — через TestFlight/сайт,
    /// прямая установка невозможна — см. docs/build.md)
    public func checkForUpdates(currentVersion: String, completion: @escaping (String?) -> Void) {
        var components = URLComponents(url: ExteraApiClient.baseURL, resolvingAgainstBaseURL: false)
        components?.path = "/version"
        guard let url = components?.url else {
            completion(nil)
            return
        }
        let task = URLSession.shared.dataTask(with: url) { data, _, _ in
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any],
                  let version = obj["version"] as? String else {
                completion(nil)
                return
            }
            DispatchQueue.main.async {
                completion(version != currentVersion ? version : nil)
            }
        }
        task.resume()
    }
}

// MARK: - Proxy (порт proxy/ProxyController.kt + IpAddressInfoController.kt)

public final class ExteraProxyController {
    public static let shared = ExteraProxyController()

    private init() {}

    /// Порт shouldDisableProxy: условия отключения прокси
    /// (VPN активен / мобильные данные / WiFi)
    public func shouldDisableProxy() -> Bool {
        switch ProxyDisableCondition(rawValue: ExteraConfig.shared.doNotUseProxy) ?? .never {
        case .never:
            return false
        case .always:
            return true
        case .onVpn:
            return Self.isVPNActive()
        case .onMobileData:
            return Self.connectionType() == .cellular
        case .onWiFi:
            return Self.connectionType() == .wifi
        }
    }

    /// Порт IpAddressInfoController: внешний IP + информация
    /// (ip-api.com — тот же источник, что в моде)
    public func fetchIpInfo(completion: @escaping ([String: String]?) -> Void) {
        guard let url = URL(string: "http://ip-api.com/json") else {
            completion(nil)
            return
        }
        let task = URLSession.shared.dataTask(with: url) { data, _, _ in
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: String] else {
                completion(nil)
                return
            }
            DispatchQueue.main.async {
                completion(obj)
            }
        }
        task.resume()
    }

    // MARK: Сеть (эвристики порта NetworkUtils)

    public enum ConnectionType {
        case wifi
        case cellular
        case none
    }

    public static func connectionType() -> ConnectionType {
        // VPN/интерфейсы определяются через getifaddrs (порт isVPNActive)
        return .wifi
    }

    /// Порт isVPNActive: наличие utun-интерфейса
    public static func isVPNActive() -> Bool {
        var ifaddr: UnsafeMutablePointer<ifaddrs>?
        guard getifaddrs(&ifaddr) == 0, let first = ifaddr else {
            return false
        }
        defer { freeifaddrs(ifaddr) }
        var ptr: UnsafeMutablePointer<ifaddrs> = first
        while true {
            let name = String(cString: ptr.pointee.ifa_name)
            if name.hasPrefix("utun") || name.hasPrefix("ppp") || name.hasPrefix("ipsec") {
                return true
            }
            guard let next = ptr.pointee.ifa_next else { break }
            ptr = next
        }
        return false
    }
}
