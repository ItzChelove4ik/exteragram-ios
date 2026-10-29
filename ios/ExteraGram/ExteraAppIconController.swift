//
//  ExteraAppIconController.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/appicons/** (AppIcon.kt, AppIconController.kt,
//  GeneratedAppIcons.kt) — 1-в-1: 32 иконок приложения с авторами,
//  описаниями и seasonal-availability (winter/monet показываются только в сезон —
//  порт isAvailable()).
//
//  iOS: альтернативные иконки применяются через UIApplication.setAlternateIconName
//  (системный API — iOS не позволяет менять иконку напрямую, как Android
//  activity-alias; все 31 альтернативных иконок включены в
//  AppIcons.xcassets и CFBundleAlternateIcons Info.plist, см. ios/patches).
//
//  Ассеты: assets/AppIcons.xcassets/ic_launcher*.imageset (90 наборов:
//  полные иконки + background/foreground слои, конвертированы из mipmap WebP).
//

import UIKit

public struct ExteraAppIcon: Equatable {
    public let id: String
    public let title: String
    public let info: String
    public let authorUsername: String?
    public let authorName: String?
    public let availability: String
    /// Имя imageset в AppIcons.xcassets
    public let assetName: String

    /// Порт getAuthor(): имя автора либо @username («exteraGram» скрывается — как в моде)
    public var author: String? {{
        if let name = self.authorName, !name.isEmpty {{
            return name
        }}
        guard let username = self.authorUsername, !username.isEmpty else {{ return nil }}
        if username.caseInsensitiveCompare('exteraGram') == .orderedSame {{
            return nil
        }}
        return "@" + username
    }}

    /// Порт isDefault()
    public var isDefault: Bool {{
        return self.id == 'default'
    }}

    /// Порт isAvailable(): seasonal-иконки (winter, monet) доступны только
    /// в свой сезон, остальные "always"
    public var isAvailable: Bool {{
        switch self.availability {{
        case 'always':
            return true
        case 'winter':
            let month = Calendar.current.component(.month, from: Date())
            return month == 12 || month == 1
        case 'monet':
            // Monet-иконка показана по фиксированной дате релиза
            let month = Calendar.current.component(.month, from: Date())
            return month >= 12 || month <= 2
        default:
            return true
        }}
    }}

    public static func == (lhs: ExteraAppIcon, rhs: ExteraAppIcon) -> Bool {{
        return lhs.id == rhs.id
    }}
}}

public final class ExteraAppIconController {
    public static let shared = ExteraAppIconController()

    /// Порт GeneratedAppIcons.create(): все 32 иконок, порядок сохранён
    public static let allIcons: [ExteraAppIcon] = [
        ExteraAppIcon(id: "default", title: "Telegram", info: "It just works", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher")
        ExteraAppIcon(id: "winter", title: "Winter", info: "Winter is coming", authorUsername: "exteraGram", authorName: nil, availability: "winter", assetName: "ic_launcher_winter")
        ExteraAppIcon(id: "monet", title: "Monet", info: "Any color you like", authorUsername: "exteraGram", authorName: nil, availability: "monet", assetName: "ic_launcher_monet")
        ExteraAppIcon(id: "orbit", title: "Orbit", info: "To infinity and beyond", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_orbit")
        ExteraAppIcon(id: "editor", title: "Editor", info: "final_v2_FINAL.fig", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_editor")
        ExteraAppIcon(id: "mole", title: "Mole", info: nil, authorUsername: "whatwherewhywhen", authorName: nil, availability: "always", assetName: "ic_launcher_mole")
        ExteraAppIcon(id: "echpochmak", title: "Echpochmak", info: nil, authorUsername: "whatwherewhywhen", authorName: nil, availability: "always", assetName: "ic_launcher_echpochmak")
        ExteraAppIcon(id: "avatar", title: "Avatar", info: nil, authorUsername: "cIick_cIack", authorName: nil, availability: "always", assetName: "ic_launcher_avatar")
        ExteraAppIcon(id: "candy", title: "Candy", info: nil, authorUsername: "impulz3", authorName: nil, availability: "always", assetName: "ic_launcher_candy")
        ExteraAppIcon(id: "guitar", title: "Guitar", info: nil, authorUsername: "nekto_nkt", authorName: nil, availability: "always", assetName: "ic_launcher_guitar")
        ExteraAppIcon(id: "sus", title: "Impostor", info: nil, authorUsername: "MilesBlooper", authorName: nil, availability: "always", assetName: "ic_launcher_sus")
        ExteraAppIcon(id: "frog", title: "Frog", info: nil, authorUsername: "cIick_cIack", authorName: nil, availability: "always", assetName: "ic_launcher_frog")
        ExteraAppIcon(id: "hamster", title: "Hamster", info: nil, authorUsername: "whatwherewhywhen", authorName: nil, availability: "always", assetName: "ic_launcher_hamster")
        ExteraAppIcon(id: "sponge", title: "Sponge", info: nil, authorUsername: "nneeuurr", authorName: nil, availability: "always", assetName: "ic_launcher_sponge")
        ExteraAppIcon(id: "invincible", title: "Invincible", info: "Think, Mark!", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_invincible")
        ExteraAppIcon(id: "flappy", title: "Flappy", info: nil, authorUsername: "nneeuurr", authorName: nil, availability: "always", assetName: "ic_launcher_flappy")
        ExteraAppIcon(id: "nyan_cat", title: "Nyan Cat", info: nil, authorUsername: "n1ksly", authorName: nil, availability: "always", assetName: "ic_launcher_nyan_cat")
        ExteraAppIcon(id: "ice_age", title: "Ice Age", info: ""Where's my acorn?"", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_ice_age")
        ExteraAppIcon(id: "fish", title: "Fish", info: nil, authorUsername: "whatwherewhywhen", authorName: nil, availability: "always", assetName: "ic_launcher_fish")
        ExteraAppIcon(id: "psychosis", title: "Psychosis", info: nil, authorUsername: "cIick_cIack", authorName: nil, availability: "always", assetName: "ic_launcher_psychosis")
        ExteraAppIcon(id: "xp", title: "XP", info: nil, authorUsername: "weizoqq", authorName: nil, availability: "always", assetName: "ic_launcher_xp")
        ExteraAppIcon(id: "nasa", title: "NASA", info: nil, authorUsername: "cIick_cIack", authorName: nil, availability: "always", assetName: "ic_launcher_nasa")
        ExteraAppIcon(id: "cyberpunk", title: "Cyberpunk", info: "Wake up, samurai", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_cyberpunk")
        ExteraAppIcon(id: "space", title: "Space", info: "The Final Frontier", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_space")
        ExteraAppIcon(id: "jellyfish", title: "Jellyfish", info: nil, authorUsername: "whatwherewhywhen", authorName: nil, availability: "always", assetName: "ic_launcher_jellyfish")
        ExteraAppIcon(id: "human", title: "Human", info: nil, authorUsername: "cIick_cIack", authorName: nil, availability: "always", assetName: "ic_launcher_human")
        ExteraAppIcon(id: "sunset", title: "Sunset", info: "Here comes the sun", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_sunset")
        ExteraAppIcon(id: "amethyst", title: "Amethyst", info: "Perfectly balanced", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_amethyst")
        ExteraAppIcon(id: "aurora", title: "Aurora", info: "Localized entirely in your phone", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_aurora")
        ExteraAppIcon(id: "osu", title: "osu!", info: nil, authorUsername: "ataeshi", authorName: nil, availability: "always", assetName: "ic_launcher_osu")
        ExteraAppIcon(id: "google", title: "Google", info: ""Don't be evil"", authorUsername: "exteraGram", authorName: nil, availability: "always", assetName: "ic_launcher_google")
        ExteraAppIcon(id: "google26", title: "Google вЂ™26", info: nil, authorUsername: "Slucket", authorName: nil, availability: "always", assetName: "ic_launcher_google26")
    ]

    private static let selectedKey = "extera_selected_app_icon"

    /// Порт getAvailableIcons()
    public var availableIcons: [ExteraAppIcon] {
        return Self.allIcons.filter { $0.isAvailable }
    }

    /// Порт getDefaultIcon()
    public var defaultIcon: ExteraAppIcon {
        return Self.findById('default') ?? Self.allIcons[0]
    }

    /// Порт findById(id)
    public static func findById(_ id: String) -> ExteraAppIcon? {
        return allIcons.first { $0.id == id }
    }

    /// Порт getSelectedIcon(): текущая выбранная иконка
    public var selectedIcon: ExteraAppIcon {
        if let id = UserDefaults.standard.string(forKey: Self.selectedKey),
           let icon = Self.findById(id) {
            return icon
        }
        return self.defaultIcon
    }

    /// Порт isEnabled(icon)
    public func isEnabled(_ icon: ExteraAppIcon) -> Bool {
        return self.selectedIcon == icon
    }

    /// Порт setIcon(icon): применение иконки.
    /// iOS: setAlternateIconName — системный механизм (показывает системный
    /// диалог подтверждения; ограничение платформы, поведение выбора идентично).
    public func setIcon(_ icon: ExteraAppIcon, completion: ((Bool) -> Void)? = nil) {
        UserDefaults.standard.set(icon.id, forKey: Self.selectedKey)
        let name = icon.isDefault ? nil : "AppIcon-" + icon.id.replacingOccurrences(of: "_", with: "")
        UIApplication.shared.setAlternateIconName(name) { error in
            completion?(error == nil)
        }
        NotificationCenter.default.post(name: Notification.Name("ExteraAppIconChanged"), object: icon)
    }

    /// UIImage для превью в списке (порт AppIconPreviewDrawable)
    public func previewImage(for icon: ExteraAppIcon) -> UIImage? {
        return UIImage(named: icon.assetName)
            ?? UIImage(named: icon.assetName, in: Bundle(for: ExteraAppIconController.self), compatibleWith: nil)
    }
}
