//
//  ExteraLocaleController.swift
//  ExteraGram iOS port
//
//  Порт системы локализации мода (Android: assets/extera_locales, 34 языка).
//  Строки лежат в Extera.strings каждой lproj-папки (конвертация CI:
//  tools/scripts/convert-locales.py). Ключи идентичны Android.
//
//  Android-мод выбирает язык мода независимо от языка системы
//  (ExteraConfig.getCurrentLangName) — здесь так же: bundle для языка,
//  с фолбэком на Base.
//

import Foundation

public final class ExteraLocaleController {
    public static let shared = ExteraLocaleController()

    /// Порт getCurrentLangName(): текущий язык мода (код BCP-47 или "system")
    public var currentLangName: String {
        get {
            return UserDefaults.standard.string(forKey: "extera_current_lang") ?? "system"
        }
        set {
            UserDefaults.standard.set(newValue, forKey: "extera_current_lang")
            self.reload()
        }
    }

    private var bundle: Bundle

    private init() {
        let module = Bundle(for: ExteraLocaleController.self)
        self.bundle = Self.bundle(for: Self.preferredLanguageCode(module: module), module: module)
    }

    private func reload() {
        let module = Bundle(for: ExteraLocaleController.self)
        self.bundle = Self.bundle(for: Self.preferredLanguageCode(module: module), module: module)
    }

    private static func preferredLanguageCode(module: Bundle) -> String {
        let saved = UserDefaults.standard.string(forKey: "extera_current_lang") ?? "system"
        if saved != "system" {
            return saved
        }
        let system = Locale.preferredLanguages.first ?? "en"
        let code = String(system.prefix(2))
        // проверка, что локаль мода существует
        let path = module.path(forResource: code, ofType: "lproj")
        return path != nil ? code : "Base"
    }

    private static func bundle(for code: String, module: Bundle) -> Bundle {
        if let path = module.path(forResource: code, ofType: "lproj"),
           let b = Bundle(path: path) {
            return b
        }
        if let path = module.path(forResource: "Base", ofType: "lproj"),
           let b = Bundle(path: path) {
            return b
        }
        return module
    }

    /// Порт getString(R.string.<key>): строка локализации мода.
    public func string(_ key: String) -> String {
        return self.bundle.localizedString(forKey: key, value: key, table: "Extera")
    }

    /// Порт getQuantityString(R.plurals.<key>, n): плюрализация как в Android
    /// (one/few/many/other — портированные правила; для ru/uk/pl и пр.).
    public func quantityString(_ key: String, count: Int) -> String {
        let qty: String
        let lang = Self.preferredLanguageCode(module: Bundle(for: ExteraLocaleController.self))
        switch lang {
        case "ru", "uk", "sr", "hr", "bs":
            // славянские правила one/few/many
            let n = abs(count) % 100
            let n1 = n % 10
            if (11...14).contains(n) { qty = "many" }
            else if n1 == 1 { qty = "one" }
            else if (2...4).contains(n1) { qty = "few" }
            else { qty = "many" }
        case "pl":
            let n = abs(count) % 100
            let n1 = n % 10
            if (11...14).contains(n) { qty = "many" }
            else if n1 == 1 { qty = "one" }
            else if (2...4).contains(n1) { qty = "few" }
            else { qty = "many" }
        case "cs", "sk":
            let n1 = abs(count) % 10
            if count == 1 { qty = "one" }
            else if (2...4).contains(n1) { qty = "few" }
            else { qty = "other" }
        case "ar":
            let n = abs(count) % 100
            if count == 0 { qty = "zero" }
            else if count == 1 { qty = "one" }
            else if count == 2 { qty = "two" }
            else if (3...10).contains(n) { qty = "few" }
            else if (11...99).contains(n) { qty = "many" }
            else { qty = "other" }
        default:
            qty = count == 1 ? "one" : "other"
        }
        // порядок попыток: точная форма -> other -> Base -> key
        for candidate in [qty, "other"] {
            let k = "\(key)__\(candidate)"
            let s = self.bundle.localizedString(forKey: k, value: "", table: "Extera")
            if !s.isEmpty && s != k {
                return s.replacingOccurrences(of: "%d", with: String(count))
                    .replacingOccurrences(of: "%s", with: String(count))
            }
        }
        // Base-фолбэк
        if let basePath = Bundle(for: ExteraLocaleController.self).path(forResource: "Base", ofType: "lproj"),
           let base = Bundle(path: basePath) {
            for candidate in [qty, "other"] {
                let k = "\(key)__\(candidate)"
                let s = base.localizedString(forKey: k, value: "", table: "Extera")
                if !s.isEmpty && s != k {
                    return s.replacingOccurrences(of: "%d", with: String(count))
                        .replacingOccurrences(of: "%s", with: String(count))
                }
            }
        }
        return key
    }

    /// Список доступных языков мода (порт списка в настройках)
    public static var availableLanguages: [(code: String, name: String)] {
        let module = Bundle(for: ExteraLocaleController.self)
        var result: [(String, String)] = []
        let languages: [(code: String, name: String)] = [
            ("ar", "العربية"), ("az", "Azərbaycan"), ("be", "Беларуская"),
            ("ca", "Català"), ("cs", "Čeština"), ("de", "Deutsch"),
            ("es", "Español"), ("fa", "فارسی"), ("fi", "Suomi"),
            ("fr", "Français"), ("hr", "Hrvatski"), ("hu", "Magyar"),
            ("id", "Bahasa Indonesia"), ("it", "Italiano"), ("he", "עברית"),
            ("ja", "日本語"), ("kk", "Қазақша"), ("ko", "한국어"),
            ("ms", "Bahasa Melayu"), ("nb", "Norsk"), ("nl", "Nederlands"),
            ("pl", "Polski"), ("pt-BR", "Português (Brasil)"), ("ro", "Română"),
            ("ru", "Русский"), ("sk", "Slovenčina"), ("sr", "Српски"),
            ("sv", "Svenska"), ("tr", "Türkçe"), ("uk", "Українська"),
            ("uz", "O‘zbek"), ("zh-Hans", "简体中文"), ("zh-Hant", "繁體中文"),
        ]
        for entry in languages {
            if module.path(forResource: entry.code, ofType: "lproj") != nil {
                result.append((entry.code, entry.name))
            }
        }
        return result
    }
}
