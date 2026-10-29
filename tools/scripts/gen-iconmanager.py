import json

m = json.load(open('docs/icon-pack-map.json'))
remix = sorted(set((a, b) for a, b in m['remix']))
solar = sorted(set((a, b) for a, b in m['solar']))


def sw(pairs):
    return '\n'.join(f'        "{a}": "{b}",' for a, b in pairs)


header = '''//
//  ExteraIconManager.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/icons/IconManager.kt + BaseIconPacks.kt (1-в-1).
//  Маппинг иконок DEFAULT<->SOLAR/REMIX перенесён полностью: REMIX_N remix и SOLAR_N solar замен.
//  SVG-файлы наборов: assets/VectorIcons/<name>.svg (1516 шт., incl. 470 remix + 470 solar).
//  Внешние icon packs (IconPackProvider/IconPackStorage) — порт ниже.
//

import Foundation

public final class ExteraIconManager {
    public static let shared = ExteraIconManager()

    // MARK: - Base icon packs (порт BaseIconPacks.kt)

    /// base.default — стоковые иконки Telegram (маппинг def: popup_fixed_alert → popup_fixed_alert4)
    public static let defaultOverrides: [String: String] = [
        "popup_fixed_alert": "popup_fixed_alert4",
        "popup_fixed_alert2": "popup_fixed_alert4",
        "popup_fixed_alert3": "popup_fixed_alert4",
    ]

    /// base.solar — Solar Icon Set (@Design480): SOLAR_N замен
    public static let solarMap: [String: String] = [
SOLAR_ENTRIES
    ]

    /// base.remix — Remix Icon (https://github.com/Remix-Design/RemixIcon): REMIX_N замен
    public static let remixMap: [String: String] = [
REMIX_ENTRIES
    ]

    public struct ExteraIconPack {
        public let id: String
        public let name: String
        public let author: String
        public let version: String
        public let icons: [String: String]
    }

    /// Порт getBasePack(id)
    public func getBasePack(_ id: String) -> ExteraIconPack? {
        switch id {
        case "base.default":
            return ExteraIconPack(id: "base.default", name: "Default", author: "Telegram", version: "1.0", icons: Self.defaultOverrides)
        case "base.solar":
            return ExteraIconPack(id: "base.solar", name: "Solar Icon Set", author: "@Design480", version: "1.0", icons: Self.solarMap)
        case "base.remix":
            return ExteraIconPack(id: "base.remix", name: "Remix Icon", author: "Remix-Design", version: "1.0", icons: Self.remixMap)
        default:
            return nil
        }
    }

    /// Порт текущего маппинга по ExteraConfig.iconPack
    public var activeOverrides: [String: String] {
        switch ExteraConfig.shared.iconPack {
        case .solar: return Self.solarMap
        case .remix: return Self.remixMap
        case .defaultPack: return Self.defaultOverrides
        }
    }

    /// Порт IconManager.getIconName(name): имя ресурса с учётом активного набора.
    /// Внешние паки (не base.*) перекрывают base-маппинг — как в Android.
    public func iconName(_ name: String) -> String {
        if let pack = self.externalPack, let replacement = pack.icons[name] {
            return replacement
        }
        return self.activeOverrides[name] ?? name
    }

    // MARK: - Внешние icon packs (порт IconPackProvider/IconPackStorage)

    private var externalPacks: [ExteraIconPack] = []
    public private(set) var externalPack: ExteraIconPack?

    private var storageURL: URL {
        let base = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
        let url = base.appendingPathComponent("ExteraIconPacks", isDirectory: true)
        try? FileManager.default.createDirectory(at: url, withIntermediateDirectories: true)
        return url
    }

    public func reloadPacks() {
        self.externalPacks = self.loadInstalledPacks()
    }

    /// Порт IconPackStorage.loadInstalled: читает pack.json каждого каталога
    private func loadInstalledPacks() -> [ExteraIconPack] {
        var packs: [ExteraIconPack] = []
        guard let children = try? FileManager.default.contentsOfDirectory(at: self.storageURL, includingPropertiesForKeys: nil) else {
            return packs
        }
        for dir in children where dir.hasDirectoryPath {
            let metaURL = dir.appendingPathComponent("pack.json")
            guard let data = try? Data(contentsOf: metaURL),
                  let obj = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any],
                  let id = obj["id"] as? String,
                  let name = obj["name"] as? String else { continue }
            let author = obj["author"] as? String ?? ""
            let version = obj["version"] as? String ?? "1.0"
            let icons = obj["icons"] as? [String: String] ?? [:]
            packs.append(ExteraIconPack(id: id, name: name, author: author, version: version, icons: icons))
        }
        return packs
    }

    public var installedPacks: [ExteraIconPack] {
        return self.externalPacks
    }

    /// Порт IconManager: выбор активного пака (id внешнего пака или IconPackType)
    public func setActivePack(id: String) {
        if id.hasPrefix("base.") {
            switch id {
            case "base.solar": ExteraConfig.shared.iconPack = .solar
            case "base.remix": ExteraConfig.shared.iconPack = .remix
            default: ExteraConfig.shared.iconPack = .defaultPack
            }
            self.externalPack = nil
        } else {
            self.externalPack = self.externalPacks.first { $0.id == id }
            // внешние паки активны поверх выбранного base
        }
        NotificationCenter.default.post(name: Notification.Name("ExteraIconPackChanged"), object: nil)
    }
}
'''

src = header.replace('SOLAR_ENTRIES', sw(solar)).replace('REMIX_ENTRIES', sw(remix))
src = src.replace('REMIX_N', str(len(remix))).replace('SOLAR_N', str(len(solar)))
open('ios/ExteraGram/ExteraIconManager.swift', 'w', encoding='utf-8', newline='\n').write(src)
print('written, remix:', len(remix), 'solar:', len(solar))
