//
//  ExteraApiClient.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/api/** (network/ApiClient.kt, ApiService.kt,
//  ApiController.kt, dto/**, worker/**) — 1-в-1.
//
//  BASE_URL = "https://api.exteragram.app/api/v1/" (тот же сервер мода).
//  Эндпоинты (Retrofit → URLSession):
//    GET profiles                      — все профили (бейджи)
//    GET profiles/updates?since=       — инкрементальные обновления (ETag/If-None-Match)
//    GET profiles/{userId}             — профиль пользователя (бейдж)
//    GET profiles/{userId}/now-playing — Now Playing пользователя
//    GET boosty-subscribers            — список Boosty-подписчиков
//
//  DTO: ProfileDTO (userId, badges, name, emoji), NowPlayingDTO (track, artist),
//  кэширование через ETag — как в моде.
//

import Foundation

// MARK: - DTO (порт api/dto)

public struct ExteraProfileDTO: Codable {
    public let userId: Int64
    public let name: String?
    public let username: String?
    /// Словарь бейджей: тип → значение (порт badges-структуры)
    public let badges: [String: String]?
    /// Эмодзи-статус
    public let emoji: String?

    enum CodingKeys: String, CodingKey {
        case userId = "user_id"
        case name
        case username
        case badges
        case emoji
    }
}

public struct ExteraNowPlayingDTO: Codable {
    public let track: String?
    public let artist: String?
    public let emoji: String?

    enum CodingKeys: String, CodingKey {
        case track
        case artist
        case emoji
    }
}

public struct ExteraBoostySubscriberDTO: Codable {
    public let userId: Int64?
    public let name: String?
    public let level: String?

    enum CodingKeys: String, CodingKey {
        case userId = "user_id"
        case name
        case level
    }
}

// MARK: - ApiClient (порт ApiClient.kt)

public final class ExteraApiClient {
    public static let shared = ExteraApiClient()

    /// Порт BASE_URL
    public static let baseURL = URL(string: "https://api.exteragram.app/api/v1/")!

    /// Порт requestsEnabled: сетевые запросы API мода можно выключить
    public var requestsEnabled: Bool {
        get {
            return UserDefaults.standard.object(forKey: "extera_api_enabled") as? Bool ?? true
        }
        set {
            UserDefaults.standard.set(newValue, forKey: "extera_api_enabled")
        }
    }

    private let session: URLSession
    /// ETag-кэш (порт If-None-Match-логики)
    private var etags: [String: String] = [:]

    private init() {
        let config = URLSessionConfiguration.ephemeral
        config.timeoutIntervalForRequest = 15
        self.session = URLSession(configuration: config)
    }

    // MARK: - Запросы (порт ApiService.kt)

    /// GET profiles — все профили с бейджами
    public func getProfiles(completion: @escaping ([ExteraProfileDTO]?) -> Void) {
        self.request(path: "profiles", etagKey: "profiles") { data in
            guard let data = data else {
                completion(nil)
                return
            }
            completion(try? JSONDecoder().decode([ExteraProfileDTO].self, from: data))
        }
    }

    /// GET profiles/updates?since= — инкрементальные обновления
    public func getUpdates(since: String, completion: @escaping ([ExteraProfileDTO]?) -> Void) {
        self.request(path: "profiles/updates", query: ["since": since], etagKey: "profiles/updates") { data in
            guard let data = data else {
                completion(nil)
                return
            }
            completion(try? JSONDecoder().decode([ExteraProfileDTO].self, from: data))
        }
    }

    /// GET profiles/{userId} — один профиль
    public func getProfile(userId: Int64, completion: @escaping (ExteraProfileDTO?) -> Void) {
        self.request(path: "profiles/\(userId)", etagKey: "profiles/\(userId)") { data in
            guard let data = data else {
                completion(nil)
                return
            }
            completion(try? JSONDecoder().decode(ExteraProfileDTO.self, from: data))
        }
    }

    /// GET profiles/{userId}/now-playing — статус Now Playing
    public func getNowPlaying(userId: Int64, completion: @escaping (ExteraNowPlayingDTO?) -> Void) {
        self.request(path: "profiles/\(userId)/now-playing", etagKey: nil) { data in
            guard let data = data else {
                completion(nil)
                return
            }
            completion(try? JSONDecoder().decode(ExteraNowPlayingDTO.self, from: data))
        }
    }

    /// GET boosty-subscribers — Boosty-подписчики
    public func getBoostySubscribers(completion: @escaping ([ExteraBoostySubscriberDTO]?) -> Void) {
        self.request(path: "boosty-subscribers", etagKey: "boosty-subscribers") { data in
            guard let data = data else {
                completion(nil)
                return
            }
            completion(try? JSONDecoder().decode([ExteraBoostySubscriberDTO].self, from: data))
        }
    }

    // MARK: - Базовый запрос (порт Retrofit-обвязки ApiClient)

    private func request(path: String, query: [String: String]? = nil, etagKey: String?, completion: @escaping (Data?) -> Void) {
        guard self.requestsEnabled else {
            completion(nil)
            return
        }
        var components = URLComponents(url: Self.baseURL.appendingPathComponent(path), resolvingAgainstBaseURL: false)
        if let query = query, !query.isEmpty {
            components?.queryItems = query.map { URLQueryItem(name: $0.key, value: $0.value) }
        }
        guard let url = components?.url else {
            completion(nil)
            return
        }
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        // ETag-кэширование (порт If-None-Match)
        if let etagKey = etagKey, let etag = self.etags[etagKey] {
            request.setValue(etag, forHTTPHeaderField: "If-None-Match")
        }
        let task = self.session.dataTask(with: request) { [weak self] data, response, _ in
            guard let http = response as? HTTPURLResponse else {
                completion(nil)
                return
            }
            // 304 Not Modified — контент не изменился (порт ETag-логики мода)
            if http.statusCode == 304 {
                completion(nil)
                return
            }
            guard http.statusCode == 200 else {
                completion(nil)
                return
            }
            if let etagKey = etagKey,
               let etag = http.value(forHTTPHeaderField: "ETag") {
                self?.etags[etagKey] = etag
            }
            completion(data)
        }
        task.resume()
    }
}

// MARK: - BadgesController (порт badges/BadgesController.kt)

public final class ExteraBadgesController {
    public static let shared = ExteraBadgesController()

    /// Кэш профилей: userId → бейджи (порт CachedRemoteSet)
    private var profiles: [Int64: ExteraProfileDTO] = [:]
    private var lastLoaded: Date?
    private let updateInterval: TimeInterval = 3600 // 1 час (порт периодичности)

    private init() {}

    /// Порт getBadge(userId): бейдж пользователя (badgeName + emoji)
    public func badge(for userId: Int64) -> (name: String, emoji: String?)? {
        guard let profile = self.profiles[userId],
              let badges = profile.badges,
              let first = badges.first else {
            return nil
        }
        return (first.key, profile.emoji)
    }

    /// Порт isSupporter(userId)
    public func isSupporter(_ userId: Int64) -> Bool {
        return self.profiles[userId]?.badges?.keys.contains("supporter") == true
    }

    /// Порт isDeveloper(userId)
    public func isDeveloper(_ userId: Int64) -> Bool {
        return self.profiles[userId]?.badges?.keys.contains("developer") == true
    }

    /// Порт update(): загрузка/обновление кэша бейджей
    public func update(force: Bool = false, completion: (() -> Void)? = nil) {
        let shouldUpdate = force
            || self.lastLoaded == nil
            || Date().timeIntervalSince(self.lastLoaded!) > self.updateInterval
        guard shouldUpdate else {
            completion?()
            return
        }
        ExteraApiClient.shared.getProfiles { [weak self] list in
            if let list = list {
                var map: [Int64: ExteraProfileDTO] = [:]
                for p in list {
                    map[p.userId] = p
                }
                self?.profiles = map
                self?.lastLoaded = Date()
            }
            DispatchQueue.main.async {
                completion?()
                NotificationCenter.default.post(name: Notification.Name("ExteraBadgesUpdated"), object: nil)
            }
        }
    }
}

// MARK: - RegDate (порт regdate/RegDateController.kt + registration_dates.bin)

public final class ExteraRegDateController {
    public static let shared = ExteraRegDateController()

    /// userId -> timestamp регистрации (порт regIds/regDates массивов)
    private var dates: [Int64: Int64] = [:]
    private var loaded = false

    private init() {}

    /// Порт loadDates(): невидимое шифрование InvisibleEncryptor:
    /// файл = prefix U+2001 U+2002 + байты JSON в base-11 (алфавит невидимых
    /// символов, разделитель U+2000). JSON: {userId: unixTimestamp}
    public func load(bundle: Bundle = Bundle(for: ExteraRegDateController.self)) {
        guard !self.loaded else { return }
        defer { self.loaded = true }
        guard let url = bundle.url(forResource: "registration_dates", withExtension: "bin"),
              let raw = try? String(contentsOf: url, encoding: .utf8) else {
            return
        }
        guard let json = Self.decodeInvisible(raw),
              let obj = (try? JSONSerialization.jsonObject(with: Data(json.utf8))) as? [String: Any] else {
            return
        }
        var result: [Int64: Int64] = [:]
        for (key, value) in obj {
            if let userId = Int64(key), let ts = (value as? NSNumber)?.int64Value {
                result[userId] = ts
            }
        }
        self.dates = result
    }

    /// InvisibleEncryptor.decode: strip prefix U+2001 U+2002, split U+2000,
    /// каждый токен — base-11 число по алфавиту невидимых символов
    private static func decodeInvisible(_ s: String) -> String? {
        let alphabet: [Character] = [
            "\u{200a}", "\u{200b}", "\u{200c}", "\u{200f}", "\u{202f}",
            "\u{206a}", "\u{206b}", "\u{206c}", "\u{206d}", "\u{206e}", "\u{206f}",
        ]
        var body = s
        let prefix: [Character] = ["\u{2001}", "\u{2002}"]
        if Array(body.prefix(2)) == prefix {
            body.removeFirst(2)
        }
        let separator: Character = "\u{2000}"
        let parts = body.split(separator: separator, omittingEmptySubsequences: false)
        var bytes: [UInt8] = []
        bytes.reserveCapacity(parts.count)
        for part in parts {
            var value = 0
            var mult = 1
            for ch in part.reversed() {
                guard let idx = alphabet.firstIndex(of: ch) else { return nil }
                value += idx * mult
                mult *= 11
            }
            guard value >= 0 && value < 256 else { return nil }
            bytes.append(UInt8(value))
        }
        return String(bytes: bytes, encoding: .utf8)
    }

    /// Порт getRegDate(userId): дата регистрации для профиля
    public func regDate(for userId: Int64) -> Date? {
        self.load()
        guard let ts = self.dates[userId] else { return nil }
        return Date(timeIntervalSince1970: TimeInterval(ts))
    }

    /// Порт форматирования CreationDateUser*: «May 2013»
    public func regDateString(for userId: Int64) -> String? {
        guard let date = self.regDate(for: userId) else { return nil }
        let df = DateFormatter()
        df.dateFormat = "MMMM yyyy"
        return df.string(from: date)
    }
}
