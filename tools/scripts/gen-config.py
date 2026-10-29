import re
src = open('decompiled/sources/sources/com/exteragram/messenger/ExteraConfig.java', encoding='utf-8', errors='ignore').read()
pat = re.compile(r'(\w+)\$delegate = new (\w+)\(([^;]*?)\)\.provideDelegate')
decls = pat.findall(src)

enum_defaults = {
    'IconPackType.DEFAULT': 'IconPackType.defaultPack',
    'DividerStyle.LINE': 'DividerStyle.line',
    'TabIconsMode.TITLES_ONLY': 'TabIconsMode.titlesOnly',
    'TabCounterMode.ALL': 'TabCounterMode.all',
    'TransitionAnimation.SPRING': 'TransitionAnimation.spring',
    'GlassOutlineStyle.GLARE': 'GlassOutlineStyle.glare',
    'StickerTimeMode.DEFAULT': 'StickerTimeMode.default',
    'VideoMessagesCamera.FRONT': 'VideoMessagesCamera.front',
}

swift_fields = []
seen = set()
for name, kind, args in decls:
    if name in seen:
        continue
    seen.add(name)
    default = args.split(',')[0].strip()
    if kind == 'BooleanPref':
        d = 'true' if (default == 'true' and 'isBeta' not in default) else 'false'
        swift_fields.append((name, 'Bool', d))
    elif kind in ('IntegerPref', 'SanitizedIntegerPref'):
        swift_fields.append((name, 'Int', default or '0'))
    elif kind == 'FloatPref':
        swift_fields.append((name, 'Float', default.replace('f', '')))
    elif kind == 'LongPref':
        swift_fields.append((name, 'Int64', default or '0'))
    elif kind == 'StringPref':
        d = default.strip('"')
        swift_fields.append((name, 'String', '"%s"' % d))
    elif kind == 'NullableStringPref':
        swift_fields.append((name, 'String?', 'nil'))
    elif kind == 'EnumPref':
        if default in enum_defaults:
            d = enum_defaults[default]
            ty = default.split('.')[0]
            swift_fields.append((name, ty, d))
        else:
            swift_fields.append((name, 'CameraType', 'CAMERA_COND'))
    elif kind == 'StringSetPref':
        swift_fields.append((name, '[String]', '[]'))


def getter(ty, key, d):
    if ty == 'Bool':
        return f'self.defaults.object(forKey: "{key}") as? Bool ?? {d}'
    if ty == 'Int':
        return f'self.defaults.object(forKey: "{key}") as? Int ?? {d}'
    if ty == 'Int64':
        return f'self.defaults.object(forKey: "{key}") as? Int64 ?? {d}'
    if ty == 'Float':
        return f'self.defaults.object(forKey: "{key}") as? Float ?? {d}'
    if ty == 'String?':
        return f'self.defaults.string(forKey: "{key}")'
    if ty == 'String':
        return f'self.defaults.string(forKey: "{key}") ?? {d}'
    if ty == '[String]':
        return f'self.defaults.stringArray(forKey: "{key}") ?? []'
    return f'{ty}(rawValue: self.defaults.integer(forKey: "{key}")) ?? {d}'


def setter(ty, name):
    if ty == 'String?':
        return f'if let v = newValue {{ self.defaults.set(v, forKey: "{name}") }} else {{ self.defaults.removeObject(forKey: "{name}") }}'
    return f'self.defaults.set(newValue, forKey: "{name}")'


out = []
out.append('//')
out.append('//  ExteraConfig.swift')
out.append('//  ExteraGram iOS port')
out.append('//')
out.append('//  1-в-1 порт com/exteragram/messenger/ExteraConfig.kt (APK 12.10.6, 20261004).')
out.append('//  Все настройки мода с идентичными именами-ключами, дефолтами и семантикой.')
out.append('//  Хранение: UserDefaults(suiteName: "exteraconfig") — тот же ключ, что у мода.')
out.append('//')
out.append('')
out.append('import Foundation')
out.append('')
out.append('// MARK: - Enums (порт enum-классов мода)')
out.append('')
enums = [
    'public enum IconPackType: Int, Codable {\n    case defaultPack = 0, solar = 1, remix = 2\n}',
    'public enum DividerStyle: Int, Codable {\n    case line = 0, gradient = 1, none = 2\n}',
    'public enum TabIconsMode: Int, Codable {\n    case titlesOnly = 0, titlesWithIcons = 1, iconsOnly = 2\n}',
    'public enum TabCounterMode: Int, Codable {\n    case all = 0, unmuted = 1\n}',
    'public enum TransitionAnimation: Int, Codable {\n    case spring = 0, fade = 1, system = 2\n}',
    'public enum GlassOutlineStyle: Int, Codable {\n    case glare = 0, subtle = 1, none = 2\n}',
    'public enum StickerTimeMode: Int, Codable {\n    case `default` = 0, always = 1, never = 2\n}',
    'public enum CameraType: Int, Codable {\n    case cameraX = 0, camera2 = 1\n}',
    'public enum VideoMessagesCamera: Int, Codable {\n    case front = 0, back = 1, dual = 2\n}',
    'public enum AvatarCornerType: Int, Codable {\n    case `default` = 0, forum = 1, community = 2\n}',
    'public enum MainMenuItem: Int, Codable {\n    case divider = -1, contacts = 0, calls = 1, savedMessages = 2, settings = 3, inviteFriends = 4, telegramFeatures = 5, archivedChats = 6, nightMode = 7\n}',
    'public enum ProxyDisableCondition: Int, Codable {\n    case never = 0, always = 1, onVpn = 2, onMobileData = 3, onWiFi = 4\n}',
    'public enum BottomNavigationBar: Int, Codable {\n    case show = 0, hide = 1, floating = 2\n}',
]
for e in enums:
    out.append(e)
    out.append('')
out.append('// MARK: - ExteraConfig (порт ExteraConfig.kt)')
out.append('')
out.append('public final class ExteraConfig {')
out.append('    public static let shared = ExteraConfig()')
out.append('')
out.append('    /// SharedPreferences("exteraconfig") мода')
out.append('    private let defaults: UserDefaults')
out.append('')
out.append('    private init() {')
out.append('        self.defaults = UserDefaults(suiteName: "exteraconfig") ?? .standard')
out.append('    }')
out.append('')
out.append('    /// Полный сброс настроек мода')
out.append('    public func resetAll() {')
out.append('        let keys = self.defaults.dictionaryRepresentation().keys')
out.append('        for k in keys { self.defaults.removeObject(forKey: k) }')
out.append('    }')
out.append('')
out.append('    // MARK: Свойства — 1-в-1 с ExteraConfig.kt')
out.append('')

for name, ty, d in swift_fields:
    if d == 'CAMERA_COND':
        out.append(f'    /// {name} — Android default: CAMERA_X на high-performance классе, иначе CAMERA2. iOS: cameraX.')
        out.append(f'    public var {name}: CameraType {{')
        out.append(f'        get {{ return CameraType(rawValue: self.defaults.integer(forKey: "{name}")) ?? .cameraX }}')
        out.append(f'        set {{ self.defaults.set(newValue.rawValue, forKey: "{name}") }}')
        out.append('    }')
        out.append('')
        continue
    out.append(f'    /// {name} (Android default: {d})')
    out.append(f'    public var {name}: {ty} {{')
    out.append(f'        get {{ return {getter(ty, name, d)} }}')
    out.append(f'        set {{ {setter(ty, name)} }}')
    out.append('    }')
    out.append('')

out.append('    // MARK: Списочные настройки мода (сериализуются JSON, как Gson в Android)')
out.append('')
out.append('    /// doNotMarkAsNew: список chat id, не помечаемых «новыми»')
out.append('    public var doNotMarkAsNew: [Int64] {')
out.append('        get { return decodeArray("doNotMarkAsNew") }')
out.append('        set { encodeArray(newValue, "doNotMarkAsNew") }')
out.append('    }')
out.append('')
out.append('    /// newFeaturesShowedAt: feature -> timestamp')
out.append('    public var newFeaturesShowedAt: [String: Int64] {')
out.append('        get { return decodeDict("newFeaturesShowedAt") }')
out.append('        set { encodeDict(newValue, "newFeaturesShowedAt") }')
out.append('    }')
out.append('')
out.append('    /// iconPacksLayout / iconPacksHidden: порядок и скрытость внешних icon packs')
out.append('    public var iconPacksLayout: [String] {')
out.append('        get { return self.defaults.stringArray(forKey: "iconPacksLayout") ?? [] }')
out.append('        set { self.defaults.set(newValue, forKey: "iconPacksLayout") }')
out.append('    }')
out.append('')
out.append('    public var iconPacksHidden: [String] {')
out.append('        get { return self.defaults.stringArray(forKey: "iconPacksHidden") ?? [] }')
out.append('        set { self.defaults.set(newValue, forKey: "iconPacksHidden") }')
out.append('    }')
out.append('')
out.append('    /// mainMenuLayout / mainMenuHiddenItems: редактор главного меню')
out.append('    public var mainMenuLayout: [Int] {')
out.append('        get { return (self.defaults.array(forKey: "mainMenuLayout") as? [Int]) ?? Self.defaultMainMenuLayout }')
out.append('        set { self.defaults.set(newValue, forKey: "mainMenuLayout") }')
out.append('    }')
out.append('')
out.append('    public var mainMenuHiddenItems: [Int] {')
out.append('        get { return (self.defaults.array(forKey: "mainMenuHiddenItems") as? [Int]) ?? [] }')
out.append('        set { self.defaults.set(newValue, forKey: "mainMenuHiddenItems") }')
out.append('    }')
out.append('')
out.append('    /// Порт getDefaultMainMenuLayout()')
out.append('    public static let defaultMainMenuLayout: [Int] = [')
out.append('        MainMenuItem.contacts.rawValue,')
out.append('        MainMenuItem.calls.rawValue,')
out.append('        MainMenuItem.savedMessages.rawValue,')
out.append('        MainMenuItem.settings.rawValue,')
out.append('        MainMenuItem.inviteFriends.rawValue,')
out.append('        MainMenuItem.telegramFeatures.rawValue,')
out.append('        MainMenuItem.nightMode.rawValue,')
out.append('    ]')
out.append('')
out.append('    /// Порт getAvatarCorners(px, forum, community)')
out.append('    public static func avatarCorners(px: Float, forum: Bool, community: Bool, base: Float) -> Float {')
out.append('        if forum { return min(px, px * 0.62) }')
out.append('        if community { return min(px, px * 0.72) }')
out.append('        return min(px, base)')
out.append('    }')
out.append('')
out.append('    /// Порт isProxyDisabledOn(condition)')
out.append('    public func isProxyDisabledOn(_ condition: ProxyDisableCondition) -> Bool {')
out.append('        return ProxyDisableCondition(rawValue: self.doNotUseProxy) == condition')
out.append('    }')
out.append('')
out.append('    // MARK: - Helpers')
out.append('')
out.append('    private func decodeArray(_ key: String) -> [Int64] {')
out.append('        guard let data = self.defaults.data(forKey: key) else { return [] }')
out.append('        return (try? JSONDecoder().decode([Int64].self, from: data)) ?? []')
out.append('    }')
out.append('')
out.append('    private func encodeArray(_ value: [Int64], _ key: String) {')
out.append('        if let data = try? JSONEncoder().encode(value) {')
out.append('            self.defaults.set(data, forKey: key)')
out.append('        }')
out.append('    }')
out.append('')
out.append('    private func decodeDict(_ key: String) -> [String: Int64] {')
out.append('        guard let data = self.defaults.data(forKey: key) else { return [:] }')
out.append('        return (try? JSONDecoder().decode([String: Int64].self, from: data)) ?? [:]')
out.append('    }')
out.append('')
out.append('    private func encodeDict(_ value: [String: Int64], _ key: String) {')
out.append('        if let data = try? JSONEncoder().encode(value) {')
out.append('            self.defaults.set(data, forKey: key)')
out.append('        }')
out.append('    }')
out.append('}')

open('ios/ExteraGram/ExteraConfig.swift', 'w', encoding='utf-8', newline='\n').write('\n'.join(out) + '\n')
print('fields:', len(swift_fields))
