//
//  ExteraTranslation.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/translator/** (TranslationProviders.kt,
//  BaseTranslator, GoogleTranslator, YandexTranslator, MicrosoftTranslator,
//  TelegramTranslator, ChatTranslator, TranslatorUtils) — 1-в-1.
//
//  Провайдеры (порядок как в ALL мода):
//    0: Telegram (стоковый, через MTProto messages.translateText — Premium)
//    1: Google (translate.googleapis.com, бесплатный web-endpoint мода)
//    2: Yandex (translate.yandex.net/api/v1/tr.json/translate — web-API мода)
//    3: Microsoft (api.cognitive.microsofttranslator.com/translate v3.0)
//
//  ExteraConfig.translationProvider: -1 = авто (Google), 0..3 = выбранный.
//

import Foundation

// MARK: - Модель результата (порт BaseTranslator.TranslateResult)

public struct ExteraTranslationResult {
    public let text: String
    public let from: String
    public let to: String
}

// MARK: - Провайдеры (порт provider-классов)

public protocol ExteraTranslator {
    /// Имя провайдера (порт getTitle)
    var name: String { get }
    /// Поддерживаемые языки (порт getSupportedLanguages)
    var supportedLanguages: [String] { get }
    /// Перевод текста (порт translate)
    func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void)
}

extension ExteraTranslator {
    public func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void) {
        completion(nil)
    }

    /// Синхронный вариант (порт translateSync) — для фоновой очереди
    public func translateSync(text: String, from: String, to: String) -> ExteraTranslationResult? {
        let semaphore = DispatchSemaphore(value: 0)
        var result: ExteraTranslationResult?
        self.translate(text: text, from: from, to: to) { r in
            result = r
            semaphore.signal()
        }
        _ = semaphore.wait(timeout: .now() + 15)
        return result
    }
}

// MARK: - GoogleTranslator (порт GoogleTranslator.kt — translate.googleapis.com)

public final class ExteraGoogleTranslator: ExteraTranslator {
    public static let shared = ExteraGoogleTranslator()

    public let name = "Google"
    public let supportedLanguages = [
        "af", "sq", "am", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca",
        "ceb", "ny", "zh", "co", "hr", "cs", "da", "nl", "en", "eo", "et", "tl",
        "fi", "fr", "fy", "gl", "ka", "de", "el", "gu", "ht", "ha", "haw", "he",
        "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jv", "kn", "kk",
        "km", "rw", "ko", "ku", "ky", "lo", "la", "lv", "lt", "lb", "mk", "mg",
        "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "or", "ps", "fa",
        "pl", "pt", "pa", "ro", "ru", "sm", "gd", "sr", "st", "sn", "sd", "si",
        "sk", "sl", "so", "es", "su", "sw", "sv", "tg", "ta", "tt", "te", "th",
        "tr", "tk", "uk", "ur", "ug", "uz", "vi", "cy", "xh", "yi", "yo", "zu",
    ]

    public init() {}

    public func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void) {
        // Порт: GET https://translate.googleapis.com/translate_a/single?client=gtx&sl=<from>&tl=<to>&dt=t&q=<text>
        var components = URLComponents(string: "https://translate.googleapis.com/translate_a/single")
        components?.queryItems = [
            URLQueryItem(name: "client", value: "gtx"),
            URLQueryItem(name: "sl", value: from),
            URLQueryItem(name: "tl", value: to),
            URLQueryItem(name: "dt", value: "t"),
            URLQueryItem(name: "q", value: text),
        ]
        guard let url = components?.url else {
            completion(nil)
            return
        }
        let task = URLSession.shared.dataTask(with: url) { data, _, _ in
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [Any],
                  let sentences = obj.first as? [[Any]] else {
                completion(nil)
                return
            }
            var result = ""
            for sentence in sentences {
                if let s = sentence.first as? String {
                    result += s
                }
            }
            completion(ExteraTranslationResult(text: result, from: from, to: to))
        }
        task.resume()
    }
}

// MARK: - YandexTranslator (порт YandexTranslator.kt — web API мода)

public final class ExteraYandexTranslator: ExteraTranslator {
    public static let shared = ExteraYandexTranslator()

    public let name = "Yandex"
    public let supportedLanguages = [
        "af", "am", "ar", "az", "ba", "be", "bg", "bs", "ca", "ceb", "cs", "cy",
        "da", "de", "el", "en", "eo", "es", "et", "eu", "fa", "fi", "fr", "ga",
        "gd", "he", "hi", "hr", "ht", "hu", "hy", "id", "is", "it", "ja", "ka",
        "kk", "km", "kn", "ko", "ky", "la", "lb", "lo", "lt", "lv", "mg", "mhr",
        "mi", "mk", "ml", "mn", "mr", "ms", "mt", "my", "ne", "nl", "no", "pa",
        "pap", "pl", "pt", "ro", "ru", "sah", "si", "sk", "sl", "sq", "sr", "su",
        "sv", "sw", "ta", "te", "tg", "th", "tl", "tr", "tt", "udm", "uk", "ur",
        "uz", "vi", "xh", "yi", "zh",
    ]

    public init() {}

    public func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void) {
        // Порт: GET https://translate.yandex.net/api/v1/tr/json/translate?&srv=android&id=<uuid>&lang=<from>-<to>&text=<text>
        let id = UUID().uuidString.lowercased()
        var components = URLComponents(string: "https://translate.yandex.net/api/v1/tr.json/translate")
        components?.queryItems = [
            URLQueryItem(name: "srv", value: "android"),
            URLQueryItem(name: "id", value: id),
            URLQueryItem(name: "lang", value: "\(from)-\(to)"),
            URLQueryItem(name: "text", value: text),
        ]
        guard let url = components?.url else {
            completion(nil)
            return
        }
        let task = URLSession.shared.dataTask(with: url) { data, _, _ in
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any],
                  let translated = obj["text"] as? [String] else {
                completion(nil)
                return
            }
            completion(ExteraTranslationResult(text: translated.joined(), from: from, to: to))
        }
        task.resume()
    }
}

// MARK: - MicrosoftTranslator (порт MicrosoftTranslator.kt)

public final class ExteraMicrosoftTranslator: ExteraTranslator {
    public static let shared = ExteraMicrosoftTranslator()

    public let name = "Microsoft"
    public let supportedLanguages = [
        "af", "ar", "bg", "bn", "bs", "ca", "cs", "cy", "da", "de", "el", "en",
        "es", "et", "fa", "fi", "fr", "ga", "gu", "he", "hi", "hr", "ht", "hu",
        "id", "is", "it", "ja", "ka", "kk", "km", "kn", "ko", "lt", "lv", "mg",
        "mi", "ml", "mr", "ms", "mt", "my", "ne", "nl", "no", "pa", "pl", "pt",
        "ro", "ru", "sk", "sl", "sq", "sr", "sv", "sw", "ta", "te", "th", "tr",
        "uk", "ur", "uz", "vi", "zh", "zh-TW",
    ]

    public init() {}

    public func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void) {
        // Порт: POST https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=<from>&to=<to>
        var components = URLComponents(string: "https://api.cognitive.microsofttranslator.com/translate")
        components?.queryItems = [
            URLQueryItem(name: "api-version", value: "3.0"),
            URLQueryItem(name: "from", value: from),
            URLQueryItem(name: "to", value: to),
        ]
        guard let url = components?.url else {
            completion(nil)
            return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try? JSONSerialization.data(withJSONObject: [["Text": text]])
        let task = URLSession.shared.dataTask(with: request) { data, _, _ in
            guard let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [[String: Any]],
                  let first = obj.first,
                  let translations = first["translations"] as? [[String: Any]],
                  let translated = translations.first?["text"] as? String else {
                completion(nil)
                return
            }
            completion(ExteraTranslationResult(text: translated, from: from, to: to))
        }
        task.resume()
    }
}

// MARK: - TelegramTranslator (порт: стоковый MTProto messages.translateText)

public final class ExteraTelegramTranslator: ExteraTranslator {
    public static let shared = ExteraTelegramTranslator()

    public let name = "Telegram"
    public let supportedLanguages = [
        "af", "ar", "hy", "az", "be", "bs", "ca", "cs", "da", "de", "el", "en",
        "es", "et", "fa", "fi", "fr", "ga", "he", "hi", "hr", "hu", "id", "it",
        "ja", "ka", "kk", "ko", "ky", "lt", "lv", "mk", "ms", "my", "nb", "ne",
        "nl", "nn", "pa", "pl", "pt", "ro", "ru", "sd", "si", "sk", "sl", "sq",
        "sr", "sv", "sw", "ta", "tg", "th", "tk", "tr", "tt", "ug", "uk", "ur",
        "uz", "vi", "zh",
    ]

    public init() {}

    /// Стоковый переводчик: работает через TelegramCore (Premium-фича),
    /// сам запрос уходит в сеть Telegram — вызов интегрируется в telegram-ios
    /// (TranslateController), здесь — обёртка провайдера.
    public func translate(text: String, from: String, to: String, completion: @escaping (ExteraTranslationResult?) -> Void) {
        // Интеграция с telegram-ios: messages.translateText выполняется на уровне
        // TranslateController (см. migration-map B17). Здесь возвращаем nil —
        // вызывающий код должен использовать стоковый путь для этого провайдера.
        completion(nil)
    }
}

// MARK: - TranslationProviders (порт TranslationProviders.kt)

public final class ExteraTranslationProviders {
    public static let shared = ExteraTranslationProviders()

    /// Порт ALL: [Telegram, Google, Yandex, Microsoft]
    public static let all: [ExteraTranslator] = [
        ExteraTelegramTranslator.shared,
        ExteraGoogleTranslator.shared,
        ExteraYandexTranslator.shared,
        ExteraMicrosoftTranslator.shared,
    ]

    /// Порт current(): выбранный провайдер (translationProvider -1 = авто → Google)
    public static func current() -> ExteraTranslator {
        let index = Int(ExteraConfig.shared.translationProvider)
        if index >= 0 && index < all.count {
            return all[index]
        }
        return all[1] // Google (порт: авто-выбор мода)
    }

    /// Порт getLastIndex()
    public static var lastIndex: Int {
        return all.count - 1
    }

    /// Порт isAlternative(): не Telegram
    public static var isAlternative: Bool {
        return !isTelegram
    }

    /// Порт isTelegram()
    public static var isTelegram: Bool {
        return current().name == "Telegram"
    }

    /// Порт isChatTranslationUnlocked(account): альтернативные провайдеры
    /// разблокируют перевод чата без Premium (фича мода)
    public static func isChatTranslationUnlocked(isPremium: Bool) -> Bool {
        if isAlternative { return true }
        return isPremium
    }
}

// MARK: - TranslatorUtils (порт TranslatorUtils.kt: целевой язык)

public enum ExteraTranslatorUtils {
    /// Порт getTargetLanguage(): целевой язык из ExteraConfig.targetLang ("app" = язык системы)
    public static var targetLanguage: String {
        let saved = ExteraConfig.shared.targetLang
        if saved == "app" || saved.isEmpty {
            // язык системы (Common.ASSET_APP-семантика: follow app language)
            let preferred = Locale.preferredLanguages.first ?? "en"
            return String(preferred.prefix(2))
        }
        return saved
    }

    /// Порт getTargetLanguageName()
    public static var targetLanguageName: String {
        let code = targetLanguage
        let name = Locale.current.localizedString(forLanguageCode: code) ?? code
        return name
    }
}
