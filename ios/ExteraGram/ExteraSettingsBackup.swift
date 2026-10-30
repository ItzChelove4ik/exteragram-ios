//
//  ExteraSettingsBackup.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/backup/** (PreferencesUtils.kt,
//  BackupBottomSheet.kt, InvisibleEncryptor.kt) + OtherPreferencesActivity:
//  EXPORT_SETTINGS/RESET_SETTINGS.
//
//  Экспорт/импорт всех настроек мода в файл (порт getBackupKeys — все ключи
//  ExteraConfig + feedconfig + aiConfig), шифрование InvisibleEncryptor
//  (base-11 невидимые символы — тот же алгоритм, что в registration_dates.bin).
//

import Foundation

// MARK: - InvisibleEncryptor (порт 1-в-1: encode/decode/isEncrypted)

public enum ExteraInvisibleEncryptor {
    /// Алфавит base-11 (порт: " ​‌‏ ⁪⁫⁬⁭⁮⁯")
    private static let alphabet: [Character] = [
        "\u{200a}", "\u{200b}", "\u{200c}", "\u{200f}", "\u{202f}",
        "\u{206a}", "\u{206b}", "\u{206c}", "\u{206d}", "\u{206e}", "\u{206f}",
    ]
    /// Префикс (порт: "  ")
    private static let prefix: [Character] = ["\u{2001}", "\u{2002}"]
    /// Разделитель байтов (порт: " ")
    private static let separator: Character = "\u{2000}"

    /// Порт encode(text): байты UTF-8 → base-11 токены
    public static func encode(_ text: String) -> String {
        let bytes = Array(text.utf8)
        var parts: [String] = []
        parts.reserveCapacity(bytes.count)
        for byte in bytes {
            var value = Int(byte)
            var digits: [Character] = []
            if value == 0 {
                digits.append(alphabet[0])
            }
            while value > 0 {
                digits.append(alphabet[value % 11])
                value /= 11
            }
            parts.append(String(digits))
        }
        var result = String(prefix)
        result += parts.joined(separator: String(separator))
        return result
    }

    /// Порт decode(s)
    public static func decode(_ s: String) -> String? {
        var body = s
        if Array(body.prefix(2)) == prefix {
            body.removeFirst(2)
        }
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

    /// Порт isEncrypted(s)
    public static func isEncrypted(_ s: String) -> Bool {
        guard !s.isEmpty else { return false }
        if !Array(s.prefix(2)) == prefix { return false }
        let allowed = Set(alphabet) .union([separator])
        return s.dropFirst(2).allSatisfy { allowed.contains($0) }
    }
}

// MARK: - PreferencesUtils (порт backup/PreferencesUtils.kt)

public final class ExteraSettingsBackup {
    public static let shared = ExteraSettingsBackup()

    /// Порт getBackupKeys(): все backup-ключи (ExteraConfig + aiConfig + feedconfig)
    public static let backupDomains: [(suite: String, name: String)] = [
        ("exteraconfig", "ExteraConfig"),
        ("aiConfig", "AiConfig"),
        ("feedconfig", "FeedConfig"),
    ]

    private init() {}

    /// Порт export(): сбор всех настроек в JSON
    public func exportAll() -> String {
        var result: [String: [String: Any]] = [:]
        for (suite, name) in Self.backupDomains {
            let defaults = UserDefaults(suiteName: suite) ?? .standard
            let dict = defaults.dictionaryRepresentation()
            // отфильтровать системные ключи (порт фильтра PreferencesUtils)
            var filtered: [String: Any] = [:]
            for (key, value) in dict {
                if key.hasPrefix("NS") || key.hasPrefix("Apple") || key.hasPrefix("com.apple") {
                    continue
                }
                if let v = value as? Bool {
                    // Bool хранится как NSNumber — сохраняем тип
                    filtered[key] = v
                } else if let v = value as? Int {
                    filtered[key] = v
                } else if let v = value as? Double {
                    filtered[key] = v
                } else if let v = value as? String {
                    filtered[key] = v
                } else if let v = value as? [Any] {
                    filtered[key] = v
                }
            }
            result[name] = filtered
        }
        let data = (try? JSONSerialization.data(withJSONObject: result, options: [.prettyPrinted, .sortedKeys])) ?? Data()
        return String(data: data, encoding: .utf8) ?? "{}"
    }

    /// Порт exportEncrypted(): файл бэкапа с InvisibleEncryptor-шифрованием
    /// (формат BackupBottomSheet: невидимые символы в текстовом файле)
    public func exportEncrypted() -> String {
        return ExteraInvisibleEncryptor.encode(self.exportAll())
    }

    /// Порт import(json): восстановление настроек
    public func importAll(from json: String) -> Bool {
        guard let data = json.data(using: .utf8),
              let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: [String: Any]] else {
            return false
        }
        for (suite, name) in Self.backupDomains {
            guard let settings = obj[name] else { continue }
            let defaults = UserDefaults(suiteName: suite) ?? .standard
            for (key, value) in settings {
                defaults.set(value, forKey: key)
            }
        }
        NotificationCenter.default.post(name: Notification.Name("ExteraSettingsImported"), object: nil)
        return true
    }

    /// Порт importEncrypted(text): декодирование InvisibleEncryptor + импорт
    public func importEncrypted(from text: String) -> Bool {
        guard ExteraInvisibleEncryptor.isEncrypted(text) || text.hasPrefix("\u{2001}") else {
            // попытка прямого JSON (порт fallback)
            return self.importAll(from: text)
        }
        guard let json = ExteraInvisibleEncryptor.decode(text) else {
            return false
        }
        return self.importAll(from: json)
    }

    /// Порт RESET_SETTINGS: полный сброс
    public func resetAll() {
        for (suite, _) in Self.backupDomains {
            let defaults = UserDefaults(suiteName: suite) ?? .standard
            for key in defaults.dictionaryRepresentation().keys {
                defaults.removeObject(forKey: key)
            }
        }
        NotificationCenter.default.post(name: Notification.Name("ExteraSettingsReset"), object: nil)
    }

    /// Порт EXPORT_DATA: запись файла бэкапа
    public func writeBackupFile(to url: URL, encrypted: Bool = true) -> Bool {
        let content = encrypted ? self.exportEncrypted() : self.exportAll()
        do {
            try content.write(to: url, atomically: true, encoding: .utf8)
            return true
        } catch {
            return false
        }
    }

    /// Порт чтения файла бэкапа
    public func readBackupFile(from url: URL) -> Bool {
        guard let text = try? String(contentsOf: url, encoding: .utf8) else {
            return false
        }
        return self.importEncrypted(from: text)
    }
}

// MARK: - MarkdownUtils (порт utils/MarkdownUtils.kt: disableMarkdown)

public enum ExteraMarkdownUtils {
    /// Порт disableMarkdown: обработка текста перед отправкой.
    /// Мод: true = экранирование markdown-символов, чтобы сток Telegram
    /// не рендерил форматирование.
    public static func processOutgoing(_ text: String) -> String {
        guard ExteraConfig.shared.disableMarkdown else { return text }
        var result = ""
        for ch in text {
            switch ch {
            case "_", "*", "`", "~", "|", "[":
                result.append("\\")
                result.append(ch)
            default:
                result.append(ch)
            }
        }
        return result
    }

    /// Порт BoldItalic-фичи (BoldItalic строка мода): ***text*** →
    /// жирный+курсив в Telegram (Telegram-совместимая комбинация)
    public static func boldItalic(text: String) -> String {
        return "***" + text + "***"
    }
}

// MARK: - IntentsController (порт utils/IntentsController.kt)

public enum ExteraIntentsController {
    /// Обработка extera-ссылок (порт маршрутов мода)
    public enum Route {
        case settings(String?)      // tg://extera_settings?item=
        case iconPacks
        case plugins
        case weather
        case pillstack
    }

    /// Порт parseUri: разбор ссылки мода
    public static func route(for url: URL) -> Route? {
        let scheme = url.scheme ?? ""
        guard scheme == "tg" || scheme == "extera" else { return nil }
        let host = url.host ?? ""
        let query = url.query ?? ""
        switch host {
        case "extera_settings":
            var item: String? = nil
            for pair in query.split(separator: "&") {
                let kv = pair.split(separator: "=", maxSplits: 1)
                if kv.first == "item", kv.count > 1 {
                    item = String(kv[1])
                }
            }
            return .settings(item)
        case "extera_icons":
            return .iconPacks
        case "extera_plugins":
            return .plugins
        case "extera_weather":
            return .weather
        case "extera_pillstack":
            return .pillstack
        default:
            return nil
        }
    }
}

// MARK: - PasskeysUtil (порт utils/PasskeysUtil.kt)

public enum ExteraPasskeys {
    /// Порт isSupported: passkeys доступны (iOS 16+, ассоциированный домен
    /// настраивается в Info.plist — см. ios/patches)
    public static var isSupported: Bool {
        if #available(iOS 16.0, *) {
            return true
        }
        return false
    }
}
