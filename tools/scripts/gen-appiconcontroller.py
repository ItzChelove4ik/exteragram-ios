import json

icons = json.load(open('docs/app-icons.json', encoding='utf-8'))


def sw_str(v):
    return f'"{v}"' if v else 'nil'


def sw_bool(v):
    return 'true' if v else 'false'


def camel(id_):
    parts = id_.split('_')
    return parts[0] + ''.join(p.capitalize() for p in parts[1:])


def q(v):
    return '"' + v + '"' if v else 'nil'

entries = []
for ic in icons:
    asset = 'ic_launcher' if ic['id'] == 'default' else 'ic_launcher_' + ic['id']
    entries.append(
        '        ExteraAppIcon(id: ' + q(ic['id']) + ', title: ' + q(ic['title']) + ', info: ' + q(ic['info']) + ', '
        'authorUsername: ' + q(ic['authorUsername']) + ', authorName: ' + q(ic['authorName']) + ', '
        'availability: ' + q(ic['availability']) + ', assetName: ' + q(asset) + ')'
    )

src = f'''//
//  ExteraAppIconController.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/appicons/** (AppIcon.kt, AppIconController.kt,
//  GeneratedAppIcons.kt) — 1-в-1: {len(icons)} иконок приложения с авторами,
//  описаниями и seasonal-availability (winter/monet показываются только в сезон —
//  порт isAvailable()).
//
//  iOS: альтернативные иконки применяются через UIApplication.setAlternateIconName
//  (системный API — iOS не позволяет менять иконку напрямую, как Android
//  activity-alias; все {len(icons) - 1} альтернативных иконок включены в
//  AppIcons.xcassets и CFBundleAlternateIcons Info.plist, см. ios/patches).
//
//  Ассеты: assets/AppIcons.xcassets/ic_launcher*.imageset (90 наборов:
//  полные иконки + background/foreground слои, конвертированы из mipmap WebP).
//

import UIKit

public struct ExteraAppIcon: Equatable {{
    public let id: String
    public let title: String
    public let info: String
    public let authorUsername: String?
    public let authorName: String?
    public let availability: String
    /// Имя imageset в AppIcons.xcassets
    public let assetName: String

    /// Порт getAuthor(): имя автора либо @username («exteraGram» скрывается — как в моде)
    public var author: String? {{{{
        if let name = self.authorName, !name.isEmpty {{{{
            return name
        }}}}
        guard let username = self.authorUsername, !username.isEmpty else {{{{ return nil }}}}
        if username.caseInsensitiveCompare('exteraGram') == .orderedSame {{{{
            return nil
        }}}}
        return "@" + username
    }}}}

    /// Порт isDefault()
    public var isDefault: Bool {{{{
        return self.id == 'default'
    }}}}

    /// Порт isAvailable(): seasonal-иконки (winter, monet) доступны только
    /// в свой сезон, остальные "always"
    public var isAvailable: Bool {{{{
        switch self.availability {{{{
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
        }}}}
    }}}}

    public static func == (lhs: ExteraAppIcon, rhs: ExteraAppIcon) -> Bool {{{{
        return lhs.id == rhs.id
    }}}}
}}}}

public final class ExteraAppIconController {{
    public static let shared = ExteraAppIconController()

    /// Порт GeneratedAppIcons.create(): все {len(icons)} иконок, порядок сохранён
    public static let allIcons: [ExteraAppIcon] = [
{chr(10).join(entries)}
    ]

    private static let selectedKey = "extera_selected_app_icon"

    /// Порт getAvailableIcons()
    public var availableIcons: [ExteraAppIcon] {{
        return Self.allIcons.filter {{ $0.isAvailable }}
    }}

    /// Порт getDefaultIcon()
    public var defaultIcon: ExteraAppIcon {{
        return Self.findById('default') ?? Self.allIcons[0]
    }}

    /// Порт findById(id)
    public static func findById(_ id: String) -> ExteraAppIcon? {{
        return allIcons.first {{ $0.id == id }}
    }}

    /// Порт getSelectedIcon(): текущая выбранная иконка
    public var selectedIcon: ExteraAppIcon {{
        if let id = UserDefaults.standard.string(forKey: Self.selectedKey),
           let icon = Self.findById(id) {{
            return icon
        }}
        return self.defaultIcon
    }}

    /// Порт isEnabled(icon)
    public func isEnabled(_ icon: ExteraAppIcon) -> Bool {{
        return self.selectedIcon == icon
    }}

    /// Порт setIcon(icon): применение иконки.
    /// iOS: setAlternateIconName — системный механизм (показывает системный
    /// диалог подтверждения; ограничение платформы, поведение выбора идентично).
    public func setIcon(_ icon: ExteraAppIcon, completion: ((Bool) -> Void)? = nil) {{
        UserDefaults.standard.set(icon.id, forKey: Self.selectedKey)
        let name = icon.isDefault ? nil : "AppIcon-{{PLACEHOLDER}}"
        UIApplication.shared.setAlternateIconName(name) {{ error in
            completion?(error == nil)
        }}
        NotificationCenter.default.post(name: Notification.Name("ExteraAppIconChanged"), object: icon)
    }}

    /// UIImage для превью в списке (порт AppIconPreviewDrawable)
    public func previewImage(for icon: ExteraAppIcon) -> UIImage? {{
        return UIImage(named: icon.assetName)
            ?? UIImage(named: icon.assetName, in: Bundle(for: ExteraAppIconController.self), compatibleWith: nil)
    }}
}}
'''
open('ios/ExteraGram/ExteraAppIconController.swift', 'w', encoding='utf-8', newline='\n').write(src)
print('written:', len(icons), 'icons')
