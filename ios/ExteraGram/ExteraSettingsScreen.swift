import Foundation
import UIKit
import Display
import TelegramPresentationData
import ItemListUI
import PresentationDataUtils
import SwiftSignalKit
import AccountContext

// MARK: - Экран настроек ExteraGram (порт General/Appearance/Chats/Other)

private final class ExteraSettingsControllerArguments {
    let toggle: (ReferenceWritableKeyPath<ExteraConfig, Bool>) -> Void
    let adjust: (ReferenceWritableKeyPath<ExteraConfig, Float>, Float) -> Void
    let resetAll: () -> Void

    init(toggle: @escaping (ReferenceWritableKeyPath<ExteraConfig, Bool>) -> Void,
         adjust: @escaping (ReferenceWritableKeyPath<ExteraConfig, Float>, Float) -> Void,
         resetAll: @escaping () -> Void) {
        self.toggle = toggle
        self.adjust = adjust
        self.resetAll = resetAll
    }
}

private enum ExteraSettingsSection: Int32 {
    case general
    case appearance
    case chats
    case other
    case actions
}

private struct ExteraToggleEntry: Equatable {
    let section: ExteraSettingsSection
    let title: String
    let key: String
    let value: Bool
    let keyPath: ReferenceWritableKeyPath<ExteraConfig, Bool>

    static func == (lhs: ExteraToggleEntry, rhs: ExteraToggleEntry) -> Bool {
        return lhs.key == rhs.key && lhs.value == rhs.value
    }

    var id: String { return self.key }
    var stableId: String { return self.key }
}

private struct ExteraAdjustEntry: Equatable {
    let section: ExteraSettingsSection
    let title: String
    let key: String
    let value: Float
    let keyPath: ReferenceWritableKeyPath<ExteraConfig, Float>

    static func == (lhs: ExteraAdjustEntry, rhs: ExteraAdjustEntry) -> Bool {
        return lhs.key == rhs.key && lhs.value == rhs.value
    }

    var id: String { return self.key }
    var stableId: String { return self.key }
}

private func exteraToggleEntries() -> [ExteraToggleEntry] {
    let c = ExteraConfig.shared
    var entries: [ExteraToggleEntry] = []
    // General (порт GeneralPreferencesActivity)
    entries.append(ExteraToggleEntry(section: .general, title: "Скрыть номер телефона", key: "hidePhoneNumber", value: c.hidePhoneNumber, keyPath: \.hidePhoneNumber))
    entries.append(ExteraToggleEntry(section: .general, title: "Отключить округление чисел", key: "disableNumberRounding", value: c.disableNumberRounding, keyPath: \.disableNumberRounding))
    entries.append(ExteraToggleEntry(section: .general, title: "Время с секундами", key: "formatTimeWithSeconds", value: c.formatTimeWithSeconds, keyPath: \.formatTimeWithSeconds))
    entries.append(ExteraToggleEntry(section: .general, title: "Относительное «был(а)»", key: "relativeLastSeen", value: c.relativeLastSeen, keyPath: \.relativeLastSeen))
    entries.append(ExteraToggleEntry(section: .general, title: "Фильтр zalgo-текста", key: "filterZalgo", value: c.filterZalgo, keyPath: \.filterZalgo))
    // Appearance
    entries.append(ExteraToggleEntry(section: .appearance, title: "Единый радиус углов", key: "singleCornerRadius", value: c.singleCornerRadius, keyPath: \.singleCornerRadius))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Центрировать заголовок", key: "centerTitle", value: c.centerTitle, keyPath: \.centerTitle))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Скрыть истории (Stories)", key: "hideStories", value: c.hideStories, keyPath: \.hideStories))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Скрыть поиск чатов", key: "hideDialogsSearchBar", value: c.hideDialogsSearchBar, keyPath: \.hideDialogsSearchBar))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Скрыть кнопку FAB", key: "hideFloatingButton", value: c.hideFloatingButton, keyPath: \.hideFloatingButton))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Квадратная FAB", key: "squareFab", value: c.squareFab, keyPath: \.squareFab))
    entries.append(ExteraToggleEntry(section: .appearance, title: "MD3 свитчи", key: "newSwitchStyle", value: c.newSwitchStyle, keyPath: \.newSwitchStyle))
    entries.append(ExteraToggleEntry(section: .appearance, title: "MD3 слайдеры", key: "newSliderStyle", value: c.newSliderStyle, keyPath: \.newSliderStyle))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Принудительный blur", key: "forceBlur", value: c.forceBlur, keyPath: \.forceBlur))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Стеклянное меню", key: "glassMessageMenu", value: c.glassMessageMenu, keyPath: \.glassMessageMenu))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Снежинки принудительно", key: "forceSnow", value: c.forceSnow, keyPath: \.forceSnow))
    entries.append(ExteraToggleEntry(section: .appearance, title: "Gooey-анимация аватаров", key: "gooeyAvatarAnimation", value: c.gooeyAvatarAnimation, keyPath: \.gooeyAvatarAnimation))
    // Chats
    entries.append(ExteraToggleEntry(section: .chats, title: "Инлайн-математика", key: "inlineMathResult", value: c.inlineMathResult, keyPath: \.inlineMathResult))
    entries.append(ExteraToggleEntry(section: .chats, title: "Отключить Markdown", key: "disableMarkdown", value: c.disableMarkdown, keyPath: \.disableMarkdown))
    entries.append(ExteraToggleEntry(section: .chats, title: "Запятая после упоминания", key: "addCommaAfterMention", value: c.addCommaAfterMention, keyPath: \.addCommaAfterMention))
    entries.append(ExteraToggleEntry(section: .chats, title: "Убрать хвост сообщений", key: "removeMessageTail", value: c.removeMessageTail, keyPath: \.removeMessageTail))
    entries.append(ExteraToggleEntry(section: .chats, title: "Иконка «изменено»", key: "replaceEditedWithIcon", value: c.replaceEditedWithIcon, keyPath: \.replaceEditedWithIcon))
    entries.append(ExteraToggleEntry(section: .chats, title: "Статус онлайн в чате", key: "showOnlineStatus", value: c.showOnlineStatus, keyPath: \.showOnlineStatus))
    entries.append(ExteraToggleEntry(section: .chats, title: "Кол-во пересылок", key: "showForwardsCount", value: c.showForwardsCount, keyPath: \.showForwardsCount))
    entries.append(ExteraToggleEntry(section: .chats, title: "Скрыть кнопку Share", key: "hideShareButton", value: c.hideShareButton, keyPath: \.hideShareButton))
    entries.append(ExteraToggleEntry(section: .chats, title: "Скрыть реакции (личные)", key: "hideReactionsInPrivateChats", value: c.hideReactionsInPrivateChats, keyPath: \.hideReactionsInPrivateChats))
    entries.append(ExteraToggleEntry(section: .chats, title: "Скрыть реакции (группы)", key: "hideReactionsInGroups", value: c.hideReactionsInGroups, keyPath: \.hideReactionsInGroups))
    entries.append(ExteraToggleEntry(section: .chats, title: "Скрыть реакции (каналы)", key: "hideReactionsInChannels", value: c.hideReactionsInChannels, keyPath: \.hideReactionsInChannels))
    entries.append(ExteraToggleEntry(section: .chats, title: "Клавиатура при скролле", key: "hideKeyboardOnScroll", value: c.hideKeyboardOnScroll, keyPath: \.hideKeyboardOnScroll))
    entries.append(ExteraToggleEntry(section: .chats, title: "Безлимитные стикеры", key: "unlimitedRecentStickers", value: c.unlimitedRecentStickers, keyPath: \.unlimitedRecentStickers))
    entries.append(ExteraToggleEntry(section: .chats, title: "Видео без сжатия", key: "preferOriginalQuality", value: c.preferOriginalQuality, keyPath: \.preferOriginalQuality))
    // Other
    entries.append(ExteraToggleEntry(section: .other, title: "Вибрация в приложении", key: "inAppVibration", value: c.inAppVibration, keyPath: \.inAppVibration))
    entries.append(ExteraToggleEntry(section: .other, title: "Блокировщик рекламы (web)", key: "enableAdBlock", value: c.enableAdBlock, keyPath: \.enableAdBlock))
    return entries
}

private func exteraAdjustEntries() -> [ExteraAdjustEntry] {
    let c = ExteraConfig.shared
    return [
        ExteraAdjustEntry(section: .appearance, title: "Скругление аватаров (%)", key: "avatarCorners", value: c.avatarCorners, keyPath: \.avatarCorners),
        ExteraAdjustEntry(section: .appearance, title: "Радиус секций (dp)", key: "sectionRadius", value: c.sectionRadius, keyPath: \.sectionRadius),
        ExteraAdjustEntry(section: .chats, title: "Размер стикеров", key: "stickerSize", value: c.stickerSize, keyPath: \.stickerSize),
    ]
}

private enum ExteraSettingsEntryItem: ItemListNodeEntry, Equatable {
    case header(String, ExteraSettingsSection)
    case toggle(ExteraToggleEntry)
    case adjust(ExteraAdjustEntry)
    case resetAction
    case version(String)

    var id: String {
        switch self {

        case let .header(_, section):
            return "header_\(Int(section.rawValue))"
        case let .toggle(entry):
            return "toggle_\(entry.key)"
        case let .adjust(entry):
            return "adjust_\(entry.key)"
        case .resetAction:
            return "resetAction"
        case .version:
            return "version"
        }
    }

    var stableId: String {
        switch self {

        case let .header(_, section):
            return "header_\(Int(section.rawValue))"
        case let .toggle(entry):
            return "toggle_\(entry.key)"
        case let .adjust(entry):
            return "adjust_\(entry.key)"
        case .resetAction:
            return "resetAction"
        case .version:
            return "version"
        }
    }

    static func == (lhs: ExteraSettingsEntryItem, rhs: ExteraSettingsEntryItem) -> Bool {
        switch (lhs, rhs) {
        case let (.header(a1, s1), .header(a2, s2)): return a1 == a2 && s1 == s2
        case let (.toggle(a), .toggle(b)): return a == b
        case let (.adjust(a), .adjust(b)): return a == b
        case (.resetAction, .resetAction): return true
        case let (.version(a), .version(b)): return a == b
        default: return false
        }
    }

    static func <(lhs: ExteraSettingsEntryItem, rhs: ExteraSettingsEntryItem) -> Bool {
        return lhs.index < rhs.index
    }

    private var index: Int {
        switch self {
        case .header(_, .general): return 0
        case .toggle(let e) where e.section == .general: return 100 + e.id.hashValue % 90
        case .header(_, .appearance): return 200
        case .toggle(let e) where e.section == .appearance: return 300 + e.id.hashValue % 90
        case .adjust(let e) where e.section == .appearance: return 390 + e.id.hashValue % 9
        case .header(_, .chats): return 400
        case .toggle(let e) where e.section == .chats: return 500 + e.id.hashValue % 90
        case .adjust(let e) where e.section == .chats: return 590 + e.id.hashValue % 9
        case .header(_, .other): return 600
        case .toggle(let e) where e.section == .other: return 700 + e.id.hashValue % 90
        case .resetAction: return 900
        case .version: return 950
        default: return 999
        }
    }

    var section: ItemListSectionId {
        switch self {
        case let .header(_, section): return section.rawValue
        case let .toggle(entry): return entry.section.rawValue
        case let .adjust(entry): return entry.section.rawValue
        case .resetAction, .version: return ExteraSettingsSection.actions.rawValue
        }
    }

    func item(presentationData: ItemListPresentationData, arguments: Any) -> ListViewItem {
        let args = arguments as! ExteraSettingsControllerArguments
        switch self {
        case let .header(title, section):
            return ItemListSectionHeaderItem(presentationData: presentationData, text: title, sectionId: section.rawValue)
        case let .toggle(entry):
            return ItemListSwitchItem(
                presentationData: presentationData,
                title: entry.title,
                value: entry.value,
                enableInteractiveChanges: true,
                sectionId: entry.section.rawValue,
                style: .blocks,
                updated: { value in
                    ExteraConfig.shared[keyPath: entry.keyPath] = value
                }
            )
        case let .adjust(entry):
            let formatted = String(format: "%.0f", entry.value)
            let step: Float
            if entry.key == "avatarCorners" { step = 10 } else { step = 5 }
            return ItemListDisclosureItem(
                presentationData: presentationData,
                title: entry.title,
                label: formatted,
                labelStyle: .text,
                sectionId: entry.section.rawValue,
                style: .blocks,
                disclosureStyle: .arrow,
                action: {
                    var next = entry.value + step
                    if entry.key == "avatarCorners", next > 100 { next = 0 }
                    if entry.key == "sectionRadius", next > 40 { next = 0 }
                    if entry.key == "stickerSize", next > 20 { next = 8 }
                    if next < 0 { next = 0 }
                    args.adjust(entry.keyPath, next)
                }
            )
        case .resetAction:
            return ItemListActionItem(
                presentationData: presentationData,
                title: "Сбросить все настройки exteraGram",
                kind: .destructive,
                alignment: .natural,
                sectionId: ExteraSettingsSection.actions.rawValue,
                style: .blocks,
                action: {
                    args.resetAll()
                }
            )
        case let .version(text):
            return ItemListInfoItem(presentationData: presentationData, title: text, text: .plain(""), style: .blocks, sectionId: ExteraSettingsSection.actions.rawValue, closeAction: nil)
        }
    }
}

public func exteraSettingsController(context: AccountContext) -> ViewController {
    let updateState = ValuePromise<Int>(0)
    let updateStateValue = Atomic<Int>(value: 0)

    let arguments = ExteraSettingsControllerArguments(
        toggle: { keyPath in
            let current = ExteraConfig.shared[keyPath: keyPath]
            ExteraConfig.shared[keyPath: keyPath] = !current
            let _ = updateStateValue.modify { value in
                var v = value
                v += 1
                return v
            }
            updateState.set(updateStateValue.with { $0 })
        },
        adjust: { keyPath, value in
            ExteraConfig.shared[keyPath: keyPath] = value
            let _ = updateStateValue.modify { state in
                var v = state
                v += 1
                return v
            }
            updateState.set(updateStateValue.with { $0 })
        },
        resetAll: {
            ExteraConfig.shared.resetAll()
            let _ = updateStateValue.modify { state in
                var v = state
                v += 1
                return v
            }
            updateState.set(updateStateValue.with { $0 })
        }
    )

    let state: Signal<(ItemListControllerState, (ItemListNodeState, ExteraSettingsControllerArguments)), NoError> = combineLatest(
        queue: .mainQueue(),
        context.sharedContext.presentationData
        |> map { ItemListPresentationData($0) },
        updateState.get()
    ) |> map { presentationData, _ in
        let toggles = exteraToggleEntries()
        let adjusts = exteraAdjustEntries()
        var items: [ExteraSettingsEntryItem] = []
        items.append(.header("Общие", .general))
        items.append(contentsOf: toggles.filter { $0.section == .general }.map { .toggle($0) })
        items.append(.header("Внешний вид", .appearance))
        items.append(contentsOf: toggles.filter { $0.section == .appearance }.map { .toggle($0) })
        items.append(contentsOf: adjusts.filter { $0.section == .appearance }.map { .adjust($0) })
        items.append(.header("Чаты", .chats))
        items.append(contentsOf: toggles.filter { $0.section == .chats }.map { .toggle($0) })
        items.append(contentsOf: adjusts.filter { $0.section == .chats }.map { .adjust($0) })
        items.append(.header("Другое", .other))
        items.append(contentsOf: toggles.filter { $0.section == .other }.map { .toggle($0) })
        items.append(.resetAction)
        items.append(.version("exteraGram iOS 1.0 (порт с Android)"))

        let nodeState = ItemListNodeState(
            presentationData: presentationData,
            entries: items,
            style: .blocks,
            animateChanges: false
        )
        let controllerState = ItemListControllerState(
            presentationData: presentationData,
            title: .text("exteraGram"),
            leftNavigationButton: nil,
            rightNavigationButton: nil,
            backNavigationButton: nil,
            animateChanges: false
        )
        return (controllerState, (nodeState, arguments))
    }

    let controller = ItemListController(context: context, state: state)
    controller.navigationPresentation = .modal
    return controller
}
