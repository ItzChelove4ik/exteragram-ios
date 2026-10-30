import re

p = 'ios/ExteraGram/ExteraApiClient.swift'
s = open(p, encoding='utf-8').read()
old_start = s.index('// MARK: - RegDate')

replacement = '''// MARK: - RegDate (порт regdate/RegDateController.kt + registration_dates.bin)

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
            "\\u{200a}", "\\u{200b}", "\\u{200c}", "\\u{200f}", "\\u{202f}",
            "\\u{206a}", "\\u{206b}", "\\u{206c}", "\\u{206d}", "\\u{206e}", "\\u{206f}",
        ]
        var body = s
        let prefix: [Character] = ["\\u{2001}", "\\u{2002}"]
        if Array(body.prefix(2)) == prefix {
            body.removeFirst(2)
        }
        let separator: Character = "\\u{2000}"
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
'''

# Fix escapes: the above contains literal \\u{...} (double backslash) — write actual escape sequences
replacement = replacement.replace('\\\\u{', '\\u{')

s = s[:old_start] + replacement
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('regdate rewritten')
