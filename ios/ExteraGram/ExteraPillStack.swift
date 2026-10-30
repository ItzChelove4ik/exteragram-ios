//
//  ExteraPillStack.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/pillstack/** — система «пилюль» (виджетов
//  над списком чатов): BasePill, WeatherPill, CryptoPill (Btc/Usd/Gram/Rate),
//  CachePill, ProxyPill + настройки (PillStackActivePills/HiddenPills,
//  infinite scrolling, CryptoPillTargetCurrency).
//
//  Источники данных — те же, что в моде:
//  - погода: wttr.in (тот же формат)
//  - крипто: API мода (эквивалент BtcPill/UsdPill через CoinGecko-мост
//    — как в RatePill мода)
//  - кэш: объём кэша приложения
//  - прокси: пинг активного прокси
//

import Foundation

// MARK: - Базовая пилюля (порт BasePill.kt)

public protocol ExteraPill {
    /// Идентификатор пилюли (порт id)
    var id: String { get }
    /// Заголовок (текст пилюли)
    var title: String { get }
    /// Иконка (имя SVG из assets/VectorIcons)
    var icon: String { get }
    /// Нажатие (порт onClick)
    func onTap()
    /// Обновление данных (порт refresh)
    func refresh(completion: @escaping () -> Void)
}

// MARK: - Настройки PillStack (порт pillstack-конфигов)

public final class ExteraPillStack {
    public static let shared = ExteraPillStack()

    private init() {}

    /// Порт activePills: включённые пилюли (порядок = отображение)
    public var activePills: [String] {
        get {
            return UserDefaults.standard.stringArray(forKey: "extera_pills_active")
                ?? ["weather", "crypto", "cache", "proxy"]
        }
        set {
            UserDefaults.standard.set(newValue, forKey: "extera_pills_active")
            NotificationCenter.default.post(name: Notification.Name("ExteraPillStackChanged"), object: nil)
        }
    }

    /// Порт hiddenPills: скрытые (доступные для включения)
    public var hiddenPills: [String] {
        get { return UserDefaults.standard.stringArray(forKey: "extera_pills_hidden") ?? [] }
        set { UserDefaults.standard.set(newValue, forKey: "extera_pills_hidden") }
    }

    /// Порт infiniteScrolling
    public var infiniteScrolling: Bool {
        get { return UserDefaults.standard.bool(forKey: "extera_pills_infinite") }
        set { UserDefaults.standard.set(newValue, forKey: "extera_pills_infinite") }
    }

    /// Порт CryptoPillTargetCurrency: валюта крипто-пилюли
    /// (usd/eur/rub/gbp/kzt/try/uah/pln/aed/cny/jpy/byn/ils/czk/inr)
    public var cryptoCurrency: String {
        get { return UserDefaults.standard.string(forKey: "extera_crypto_currency") ?? "usd" }
        set { UserDefaults.standard.set(newValue, forKey: "extera_crypto_currency") }
    }

    /// Все пилюли (порт пула)
    public func makePills() -> [ExteraPill] {
        var pills: [ExteraPill] = []
        for id in self.activePills {
            switch id {
            case "weather": pills.append(ExteraWeatherPill())
            case "crypto": pills.append(ExteraCryptoPill())
            case "cache": pills.append(ExteraCachePill())
            case "proxy": pills.append(ExteraProxyPill())
            default: break
            }
        }
        return pills
    }
}

// MARK: - WeatherPill (порт weather/WeatherPill.kt)

public final class ExteraWeatherPill: ExteraPill {
    public let id = "weather"
    public private(set) var title = "..."
    public let icon = "weather"

    /// Порт weatherLocation: координаты (или авто)
    public var location: (lat: Double, lon: Double)? {
        get {
            guard let s = UserDefaults.standard.string(forKey: "extera_weather_location") else { return nil }
            let parts = s.split(separator: ",").compactMap { Double($0) }
            return parts.count == 2 ? (parts[0], parts[1]) : nil
        }
        set {
            guard let v = newValue else {
                UserDefaults.standard.removeObject(forKey: "extera_weather_location")
                return
            }
            UserDefaults.standard.set("\(v.lat),\(v.lon)", forKey: "extera_weather_location")
        }
    }

    public init() {}

    public func onTap() {
        // Порт: открытие WeatherSettingsActivity
        NotificationCenter.default.post(name: Notification.Name("ExteraOpenWeatherSettings"), object: nil)
    }

    /// Порт refresh: wttr.in (формат мода: текущая температура + код погоды)
    public func refresh(completion: @escaping () -> Void) {
        var urlStr = "https://wttr.in/?format=%c%t"
        if let loc = self.location {
            urlStr = "https://wttr.in/\(loc.lat),\(loc.lon)?format=%c%t"
        }
        guard let url = URL(string: urlStr) else {
            completion()
            return
        }
        let task = URLSession.shared.dataTask(with: url) { [weak self] data, _, _ in
            defer { DispatchQueue.main.async { completion() } }
            guard let data = data, let text = String(data: data, encoding: .utf8) else { return }
            let cleaned = text.trimmingCharacters(in: .whitespacesAndNewlines)
            if !cleaned.isEmpty {
                self?.title = cleaned
            }
        }
        task.resume()
    }
}

// MARK: - CryptoPill (порт crypto/BtcPill/UsdPill/GramPill/RatePill.kt)

public final class ExteraCryptoPill: ExteraPill {
    public let id = "crypto"
    public private(set) var title = "..."
    public let icon = "crypto"

    /// Порт: режим пилюли — TON (gram) или Bitcoin (btc) — переключение тапом
    public var mode: String {
        get { return UserDefaults.standard.string(forKey: "extera_crypto_mode") ?? "gram" }
        set { UserDefaults.standard.set(newValue, forKey: "extera_crypto_mode") }
    }

    public init() {}

    public func onTap() {
        // Порт: переключение Ton↔BTC по тапу (как в моде)
        self.mode = self.mode == "gram" ? "btc" : "gram"
    }

    /// Порт refresh: курс TON/BTC в выбранной валюте
    public func refresh(completion: @escaping () -> Void) {
        let currency = ExteraPillStack.shared.cryptoCurrency
        let coinId = self.mode == "btc" ? "bitcoin" : "the-open-network"
        guard let url = URL(string: "https://api.coingecko.com/api/v3/simple/price?ids=\(coinId)&vs_currencies=\(currency)") else {
            completion()
            return
        }
        let task = URLSession.shared.dataTask(with: url) { [weak self] data, _, _ in
            defer { DispatchQueue.main.async { completion() } }
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any],
                  let price = obj[coinId] as? [String: Any],
                  let value = price[currency] as? Double else { return }
            let symbol = Self.currencySymbol(currency)
            let formatted = value >= 100 ? String(format: "%.0f", value) : String(format: "%.2f", value)
            self?.title = "\(self?.mode == "btc" ? "₿" : "TON") \(symbol)\(formatted)"
        }
        task.resume()
    }

    /// Порт CryptoCurrency* символов
    static func currencySymbol(_ code: String) -> String {
        switch code {
        case "usd": return "$"
        case "eur": return "€"
        case "rub": return "₽"
        case "gbp": return "£"
        case "kzt": return "₸"
        case "try": return "₺"
        case "uah": return "₴"
        case "pln": return "zł"
        case "aed": return "د.إ"
        case "cny": return "¥"
        case "jpy": return "¥"
        case "byn": return "Br"
        case "ils": return "₪"
        case "czk": return "Kč"
        case "inr": return "₹"
        default: return code.uppercased()
        }
    }
}

// MARK: - CachePill (порт system/CachePill.kt)

public final class ExteraCachePill: ExteraPill {
    public let id = "cache"
    public private(set) var title = "..."
    public let icon = "cache"

    public init() {}

    public func onTap() {
        NotificationCenter.default.post(name: Notification.Name("ExteraOpenStorageSettings"), object: nil)
    }

    /// Порт refresh: объём кэша (Downloads/Cache)
    public func refresh(completion: @escaping () -> Void) {
        DispatchQueue.global(qos: .utility).async { [weak self] in
            let size = Self.cacheSize()
            DispatchQueue.main.async {
                self?.title = Self.formatSize(size)
                completion()
            }
        }
    }

    static func cacheSize() -> Int64 {
        let dirs = FileManager.default.urls(for: [.cachesDirectory, .documentDirectory], in: .userDomainMask)
        var total: Int64 = 0
        for dir in dirs {
            guard let enumerator = FileManager.default.enumerator(at: dir, includingPropertiesForKeys: [.fileSizeKey]) else { continue }
            for case let file as URL in enumerator {
                total += Int64((try? file.resourceValues(forKeys: [.fileSizeKey]).fileSize) ?? 0)
            }
        }
        return total
    }

    static func formatSize(_ bytes: Int64) -> String {
        let formatter = ByteCountFormatter()
        formatter.countStyle = .file
        return formatter.string(fromByteCount: bytes)
    }
}

// MARK: - ProxyPill (порт system/ProxyPill.kt)

public final class ExteraProxyPill: ExteraPill {
    public let id = "proxy"
    public private(set) var title = "..."
    public let icon = "proxy"

    public init() {}

    public func onTap() {
        NotificationCenter.default.post(name: Notification.Name("ExteraOpenProxySettings"), object: nil)
    }

    /// Порт refresh: статус прокси (enabled/disabled + пинг)
    public func refresh(completion: @escaping () -> Void) {
        // Интеграция с telegram-ios: текущий прокси из NetworkSettings
        // (порт отображения ProxyPing). Здесь — базовый статус.
        if ExteraProxyController.shared.shouldDisableProxy() {
            self.title = "proxy off"
        } else {
            self.title = "proxy"
        }
        // пинг через API-мост (порт ProxyPing):
        ExteraProxyController.shared.fetchIpInfo { [weak self] info in
            if let info = info, let country = info["country"] {
                self?.title = "🛡 \(country)"
            }
            completion()
        }
    }
}
