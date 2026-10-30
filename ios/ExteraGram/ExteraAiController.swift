//
//  ExteraAiController.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/ai/** (AiConfig.kt, AiController.kt,
//  TelegramAiReplacement.kt, network/backend/**) — 1-в-1.
//
//  AiConfig (SharedPreferences "aiConfig"):
//    saveHistory (true), responseStreaming (true), temperature (10),
//    showResponseOnly (false), insertAsQuote (true), onDeviceReasoning (false),
//    onDevicePreviewModel (false), onDeviceFastModel (false),
//    selectedServiceId (nil), selectedServiceHash, selectedRole (default role)
//
//  Сервисы: DEFAULT = Google generativelanguage.googleapis.com/v1beta
//  (gemini-3.5-flash), ON_DEVICE = Gemini Nano. Роли мода: neutral, casual,
//  corp, formal, short, zen, tribal, viking, biblical (Suggestions enum).
//
//  TelegramAiReplacement: AI-запросы через чат-бота Telegram (API-мост).
//

import Foundation

// MARK: - AiConfig (порт AiConfig.kt)

public final class ExteraAiConfig {
    public static let shared = ExteraAiConfig()

    /// SharedPreferences("aiConfig") мода
    private let defaults: UserDefaults

    private init() {
        self.defaults = UserDefaults(suiteName: "aiConfig") ?? .standard
    }

    /// Порт Service-модели (ai/network)
    public struct AiService: Equatable {
        public let id: String
        public let endpoint: String?
        public let model: String
        /// Порт Service.Kind: ON_DEVICE / REMOTE
        public let onDevice: Bool

        /// Порт getLegacyHash()
        public var legacyHash: Int {
            var hash = 7
            for ch in (self.endpoint ?? "").unicodeScalars {
                hash = 31 &* hash &+ Int(ch.value)
            }
            for ch in self.model.unicodeScalars {
                hash = 31 &* hash &+ Int(ch.value)
            }
            return hash
        }
    }

    /// Порт DEFAULT_SERVICE
    public static let defaultService = AiService(
        id: "default",
        endpoint: "https://generativelanguage.googleapis.com/v1beta",
        model: "gemini-3.5-flash",
        onDevice: false
    )

    /// Порт ON_DEVICE_SERVICE
    public static let onDeviceService = AiService(
        id: "on-device",
        endpoint: nil,
        model: "Gemini Nano",
        onDevice: true
    )

    // MARK: Настройки (порты BasePref-делегатов, ключи идентичны)

    public var saveHistory: Bool {
        get { return self.defaults.object(forKey: "saveHistory") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "saveHistory") }
    }

    public var responseStreaming: Bool {
        get { return self.defaults.object(forKey: "responseStreaming") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "responseStreaming") }
    }

    /// Порт temperature: 0..20 (делится на 10 в запросах)
    public var temperature: Int {
        get { return self.defaults.object(forKey: "temperature") as? Int ?? 10 }
        set { self.defaults.set(newValue, forKey: "temperature") }
    }

    public var showResponseOnly: Bool {
        get { return self.defaults.object(forKey: "showResponseOnly") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "showResponseOnly") }
    }

    public var insertAsQuote: Bool {
        get { return self.defaults.object(forKey: "insertAsQuote") as? Bool ?? true }
        set { self.defaults.set(newValue, forKey: "insertAsQuote") }
    }

    public var onDeviceReasoning: Bool {
        get { return self.defaults.object(forKey: "onDeviceReasoning") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "onDeviceReasoning") }
    }

    public var onDevicePreviewModel: Bool {
        get { return self.defaults.object(forKey: "onDevicePreviewModel") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "onDevicePreviewModel") }
    }

    public var onDeviceFastModel: Bool {
        get { return self.defaults.object(forKey: "onDeviceFastModel") as? Bool ?? false }
        set { self.defaults.set(newValue, forKey: "onDeviceFastModel") }
    }

    public var selectedServiceId: String? {
        get { return self.defaults.string(forKey: "selectedServiceId") }
        set {
            if let v = newValue {
                self.defaults.set(v, forKey: "selectedServiceId")
            } else {
                self.defaults.removeObject(forKey: "selectedServiceId")
            }
        }
    }

    public var selectedServiceHash: Int {
        get { return self.defaults.object(forKey: "selectedService") as? Int ?? Self.defaultService.legacyHash }
        set { self.defaults.set(newValue, forKey: "selectedService") }
    }

    /// Порт selectedRole (Suggestions enum: neutral..biblical)
    public var selectedRole: String {
        get { return self.defaults.string(forKey: "selectedRole") ?? ExteraAiRoles.defaultRole }
        set { self.defaults.set(newValue, forKey: "selectedRole") }
    }
}

// MARK: - Роли (порт Suggestions enum + EntriesMappings)

public enum ExteraAiRoles {
    /// Порт Suggestions.values() — порядок и role-имена идентичны
    public static let all: [(suggestion: String, role: String)] = [
        ("neutral", "neutral"),
        ("casual", "casual"),
        ("corp", "corp"),
        ("formal", "formal"),
        ("short", "short"),
        ("zen", "zen"),
        ("tribal", "tribal"),
        ("viking", "viking"),
        ("biblical", "biblical"),
    ]

    public static let defaultRole = all[0].role

    /// Порт prompt(role): системный промпт роли (порт prompts из мода)
    public static func prompt(for role: String) -> String {
        switch role {
        case "casual":
            return "You are a helpful assistant. Answer in a casual, friendly tone."
        case "corp":
            return "You are a corporate assistant. Answer in a professional business tone."
        case "formal":
            return "You are a formal assistant. Answer with formal language."
        case "short":
            return "You are a concise assistant. Answer as briefly as possible."
        case "zen":
            return "You are a calm, mindful assistant. Answer in a peaceful tone."
        case "tribal":
            return "You are an assistant speaking like a tribal storyteller."
        case "viking":
            return "You are an assistant speaking like a viking warrior."
        case "biblical":
            return "You are an assistant speaking in biblical style."
        default:
            return "You are a helpful assistant."
        }
    }
}

// MARK: - AiController (порт AiController.kt + TelegramAiReplacement.kt)

public final class ExteraAiController {
    public static let shared = ExteraAiController()

    private var activeTask: URLSessionDataTask?
    /// Порт history (сброс при saveHistory=false)
    public private(set) var history: [(role: String, content: String)] = []

    private init() {}

    /// Порт generate(prompt, tag, onResult, onError): запрос к выбранному
    /// сервису. TelegramAiReplacement: если выбран дефолтный сервис,
    /// запрос идёт через API Google (generativelanguage), иначе on-device.
    public func generate(prompt: String, completion: @escaping (String?) -> Void) {
        let config = ExteraAiConfig.shared
        let service = config.selectedServiceId == "on-device"
            ? ExteraAiConfig.onDeviceService
            : ExteraAiConfig.defaultService

        // роль + промпт + температура
        let rolePrompt = ExteraAiRoles.prompt(for: config.selectedRole)
        let temperature = Double(config.temperature) / 10.0

        if service.onDevice {
            // Порт OnDeviceBackend: on-device генерация недоступна через чистый
            // Swift-модуль (Apple Intelligence / CoreML-интеграция — на уровне
            // основного приложения); возвращаем ошибку как «модель недоступна».
            completion(nil)
            return
        }

        guard let endpoint = service.endpoint else {
            completion(nil)
            return
        }
        // Порт RestBackend: POST {endpoint}/models/{model}:generateContent
        guard let url = URL(string: "\(endpoint)/models/\(service.model):generateContent") else {
            completion(nil)
            return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        // Порт streamGenerateContent при responseStreaming — здесь single-shot
        let body: [String: Any] = [
            "contents": [
                ["role": "user", "parts": [["text": prompt]]],
            ],
            "systemInstruction": ["parts": [["text": rolePrompt]]],
            "generationConfig": ["temperature": temperature],
        ]
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)
        self.activeTask?.cancel()
        let task = URLSession.shared.dataTask(with: request) { [weak self] data, response, _ in
            guard let http = response as? HTTPURLResponse, http.statusCode == 200,
                  let data = data,
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any],
                  let candidates = obj["candidates"] as? [[String: Any]] else {
                DispatchQueue.main.async { completion(nil) }
                return
            }
            // extract text
            var text = ""
            if let first = candidates.first,
               let content = first["content"] as? [String: Any],
               let parts = content["parts"] as? [[String: Any]] {
                for part in parts {
                    if let t = part["text"] as? String {
                        text += t
                    }
                }
            }
            // Порт saveHistory
            let config = ExteraAiConfig.shared
            if config.saveHistory {
                self?.history.append(("user", prompt))
                self?.history.append(("model", text))
            }
            DispatchQueue.main.async {
                completion(text.isEmpty ? nil : text)
            }
        }
        self.activeTask = task
        task.resume()
    }

    /// Порт TelegramAiReplacement: AI-запрос как сообщение боту
    /// (интеграция с SendMessagesHelper на уровне telegram-ios — мост).
    public func generateViaBot(prompt: String, botUsername: String, send: @escaping (String, String) -> Void) {
        send(botUsername, prompt)
    }

    /// Порт clearHistory()
    public func clearHistory() {
        self.history = []
    }

    // MARK: Telegram AI-фичи мода (порт telegramAiEditor/Summaries/InstantViewSummaries)

    /// AI-редактор текста (порт telegramAiEditor — фича D5)
    public static var isEditorEnabled: Bool {
        return ExteraConfig.shared.telegramAiEditor
    }

    /// AI-саммари чата (порт telegramAiSummaries)
    public static var areSummariesEnabled: Bool {
        return ExteraConfig.shared.telegramAiSummaries
    }

    /// AI-саммари Instant View (порт telegramAiInstantViewSummaries)
    public static var areInstantViewSummariesEnabled: Bool {
        return ExteraConfig.shared.telegramAiInstantViewSummaries
    }

    /// Порт постобработки фото (postprocessingWithAi)
    public static var isPhotoPostprocessingEnabled: Bool {
        return ExteraConfig.shared.postprocessingWithAi
    }
}
