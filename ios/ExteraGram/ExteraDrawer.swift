//
//  ExteraDrawer.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/drawer/** (DrawerContainer, DrawerMenuView,
//  DrawerHeaderView, DrawerAccountPickerView, DrawerUnreadBadge,
//  DrawerAccountUnreadBadge, DrawerMenuItemView) + main menu editor
//  (MainMenuHelper + MainMenuItem).
//
//  Боковое меню: аккаунты (переключение), пункты главного меню
//  (перестраиваемые через ExteraConfig.mainMenuLayout), прокси-пинг в футере,
//  непрочитанные бейджи. Порядок/видимость пунктов редактируются (порт
//  MainMenuItems-настройки).
//

import Foundation
import UIKit

// MARK: - MainMenuItem (порт MainMenuItem enum + Companion)

public enum ExteraDrawerMenuItem: Int, CaseIterable {
    case divider = -1
    case contacts = 0
    case calls = 1
    case savedMessages = 2
    case settings = 3
    case inviteFriends = 4
    case telegramFeatures = 5
    case archivedChats = 6
    case nightMode = 7

    /// Порт titleRes
    public var title: String {
        switch self {
        case .divider: return ""
        case .contacts: return "Contacts"
        case .calls: return "Calls"
        case .savedMessages: return "Saved Messages"
        case .settings: return "Settings"
        case .inviteFriends: return "Invite Friends"
        case .telegramFeatures: return "Telegram Features"
        case .archivedChats: return "Archived Chats"
        case .nightMode: return "Night Mode"
        }
    }

    /// Порт iconRes (имя иконки из assets/VectorIcons)
    public var icon: String {
        switch self {
        case .divider: return ""
        case .contacts: return "menu_contacts"
        case .calls: return "calls_calls"
        case .savedMessages: return "menu_saved"
        case .settings: return "menu_settings_new"
        case .inviteFriends: return "menu_invite"
        case .telegramFeatures: return "menu_feature"
        case .archivedChats: return "chats_archive"
        case .nightMode: return "menu_night_mode"
        }
    }

    /// Порт Companion.getById(id)
    public static func byId(_ id: Int) -> ExteraDrawerMenuItem? {
        return ExteraDrawerMenuItem(rawValue: id)
    }
}

// MARK: - Модель пункта меню с бейджем (порт DrawerMenuItemView)

public final class ExteraDrawerItem {
    public let menu: ExteraDrawerMenuItem
    /// Бейдж непрочитанных (порт DrawerUnreadBadge)
    public let badge: Int?
    /// Ночной режим включён (переключатель)
    public var isSwitch: Bool { return menu == .nightMode }

    public init(menu: ExteraDrawerMenuItem, badge: Int? = nil) {
        self.menu = menu
        self.badge = badge
    }

    /// Формат бейджа (порт unread-форматтера мода)
    public var badgeText: String? {
        guard let badge = badge, badge > 0 else { return nil }
        return ExteraNumberFormatter.format(badge)
    }
}

// MARK: - DrawerModel (порт DrawerContainer + MainMenuHelper)

public final class ExteraDrawerModel {
    public static let shared = ExteraDrawerModel()

    private init() {}

    /// Порт buildMenu(): пункты по mainMenuLayout (порядок + видимость
    /// из ExteraConfig, скрытые пункты исключаются)
    public func buildMenu(
        contactsUnread: Int = 0,
        callsUnread: Int = 0,
        archivedUnread: Int = 0
    ) -> [ExteraDrawerItem] {
        let config = ExteraConfig.shared
        let layout = config.mainMenuLayout
        let hidden = Set(config.mainMenuHiddenItems)
        var items: [ExteraDrawerItem] = []
        for id in layout {
            guard let menu = ExteraDrawerMenuItem.byId(id) else { continue }
            if hidden.contains(id) { continue }
            var badge: Int? = nil
            switch menu {
            case .contacts: badge = contactsUnread
            case .calls: badge = callsUnread
            case .archivedChats: badge = archivedUnread
            default: break
            }
            items.append(ExteraDrawerItem(menu: menu, badge: badge))
        }
        // Дефолт, если layout пуст (порт sanitizeMenu)
        if items.isEmpty {
            for menu in ExteraDrawerMenuItem.allCases where menu != .divider {
                items.append(ExteraDrawerItem(menu: menu))
            }
        }
        return items
    }

    /// Порт moveMenuItem(from, to): перестановка в mainMenuLayout
    public func moveItem(from source: Int, to destination: Int) {
        var layout = ExteraConfig.shared.mainMenuLayout
        guard source < layout.count else { return }
        let id = layout.remove(at: source)
        let idx = min(destination, layout.count)
        layout.insert(id, at: idx)
        ExteraConfig.shared.mainMenuLayout = layout
        NotificationCenter.default.post(name: Notification.Name("ExteraMainMenuChanged"), object: nil)
    }

    /// Порт setHidden(itemId, hidden): скрытие/показ пункта меню
    public func setHidden(_ itemId: Int, hidden: Bool) {
        var hiddenList = ExteraConfig.shared.mainMenuHiddenItems
        var set = Set(hiddenList)
        if hidden {
            set.insert(itemId)
        } else {
            set.remove(itemId)
        }
        hiddenList = Array(set)
        ExteraConfig.shared.mainMenuHiddenItems = hiddenList
        NotificationCenter.default.post(name: Notification.Name("ExteraMainMenuChanged"), object: nil)
    }

    /// Порт resetMenu(): сброс layout к дефолту
    public func resetMenu() {
        ExteraConfig.shared.mainMenuLayout = ExteraConfig.defaultMainMenuLayout
        ExteraConfig.shared.mainMenuHiddenItems = []
        NotificationCenter.default.post(name: Notification.Name("ExteraMainMenuChanged"), object: nil)
    }
}

// MARK: - Модель аккаунта (порт DrawerAccountPickerView + DrawerHeaderView)

public struct ExteraDrawerAccount {
    public let id: Int32
    public let name: String
    public let initialsColor: UInt32
    /// Порт DrawerAccountUnreadBadge
    public let unreadCount: Int

    public init(id: Int32, name: String, initialsColor: UInt32 = 0xFF50A2E9, unreadCount: Int = 0) {
        self.id = id
        self.name = name
        self.initialsColor = initialsColor
        self.unreadCount = unreadCount
    }
}

// MARK: - Настройки drawer (порт navigationDrawer-параметров)

public enum ExteraDrawerSettings {
    /// Порт navigationDrawer: включён ли drawer-режим навигации
    public static var isEnabled: Bool {
        return ExteraConfig.shared.navigationDrawer
    }

    /// Порт immersiveDrawerAnimation
    public static var immersiveAnimation: Bool {
        return ExteraConfig.shared.immersiveDrawerAnimation
    }

    /// Порт hidePhoneNumber: скрытие номера в шапке drawer
    public static var hidePhoneNumber: Bool {
        return ExteraConfig.shared.hidePhoneNumber
    }
}

// MARK: - UIKit-представление drawer (порт DrawerContainer + view-классов)

public final class ExteraDrawerContainer: UIView {
    // Порты view-компонентов: header (аккаунты), menu (пункты), footer (прокси)

    private let accountPicker = ExteraDrawerAccountPickerView()
    private let menuTable = UITableView(frame: .zero, style: .plain)
    private var menuItems: [ExteraDrawerItem] = []
    private let proxyLabel = UILabel()

    public var onItemSelect: ((ExteraDrawerMenuItem) -> Void)?
    public var onAccountSwitch: ((Int32) -> Void)?

    public init(frame: CGRect, accounts: [ExteraDrawerAccount], currentAccount: Int32) {
        super.init(frame: frame)
        self.backgroundColor = .systemBackground

        self.accountPicker.configure(accounts: accounts, current: currentAccount)
        self.accountPicker.onSwitch = { [weak self] id in
            self?.onAccountSwitch?(id)
        }
        self.addSubview(self.accountPicker)

        self.menuTable.dataSource = self
        self.menuTable.delegate = self
        self.menuTable.register(ExteraDrawerMenuCell.self, forCellReuseIdentifier: "menu")
        self.menuTable.backgroundColor = .clear
        self.menuTable.separatorStyle = .none
        self.addSubview(self.menuTable)

        self.proxyLabel.font = .systemFont(ofSize: 12)
        self.proxyLabel.textColor = .secondaryLabel
        self.addSubview(self.proxyLabel)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    public override func layoutSubviews() {
        super.layoutSubviews()
        let headerH: CGFloat = 180
        self.accountPicker.frame = CGRect(x: 0, y: 0, width: self.bounds.width, height: headerH)
        self.proxyLabel.sizeToFit()
        self.proxyLabel.frame.origin = CGPoint(
            x: 16,
            y: self.bounds.height - 40
        )
        self.menuTable.frame = CGRect(
            x: 0,
            y: headerH,
            width: self.bounds.width,
            height: self.bounds.height - headerH - 48
        )
    }

    /// Порт reloadMenu()
    public func reloadMenu(contactsUnread: Int = 0, callsUnread: Int = 0, archivedUnread: Int = 0) {
        self.menuItems = ExteraDrawerModel.shared.buildMenu(
            contactsUnread: contactsUnread,
            callsUnread: callsUnread,
            archivedUnread: archivedUnread
        )
        self.menuTable.reloadData()
    }

    /// Порт прокси-футера (пинг)
    public func updateProxyFooter(text: String?) {
        self.proxyLabel.text = text
    }
}

extension ExteraDrawerContainer: UITableViewDataSource, UITableViewDelegate {
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.menuItems.count
    }

    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "menu", for: indexPath) as! ExteraDrawerMenuCell
        cell.configure(with: self.menuItems[indexPath.row])
        return cell
    }

    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = self.menuItems[indexPath.row]
        self.onItemSelect?(item.menu)
    }
}

/// Ячейка меню (порт DrawerMenuItemView: иконка + текст + бейдж)
final class ExteraDrawerMenuCell: UITableViewCell {
    private let iconView = UIImageView()
    private let titleLabel = UILabel()
    private let badgeLabel = UILabel()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.iconView.tintColor = .label
        self.titleLabel.font = .systemFont(ofSize: 15, weight: .medium)
        self.badgeLabel.font = .systemFont(ofSize: 12, weight: .semibold)
        self.badgeLabel.textColor = .white
        self.badgeLabel.backgroundColor = .systemBlue
        self.badgeLabel.layer.cornerRadius = 9
        self.badgeLabel.clipsToBounds = true
        self.badgeLabel.textAlignment = .center
        self.badgeLabel.isHidden = true

        self.contentView.addSubview(self.iconView)
        self.contentView.addSubview(self.titleLabel)
        self.contentView.addSubview(self.badgeLabel)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        let h = self.contentView.bounds.height
        self.iconView.frame = CGRect(x: 16, y: (h - 24) / 2, width: 24, height: 24)
        self.titleLabel.frame = CGRect(x: 56, y: (h - 20) / 2, width: self.contentView.bounds.width - 120, height: 20)
        if let text = self.badgeLabel.text {
            self.badgeLabel.sizeToFit()
            let w = max(18, self.badgeLabel.frame.width + 12)
            self.badgeLabel.frame = CGRect(x: self.contentView.bounds.width - w - 16, y: (h - 18) / 2, width: w, height: 18)
        }
    }

    func configure(with item: ExteraDrawerItem) {
        self.titleLabel.text = item.menu.title
        self.badgeLabel.text = item.badgeText
        self.badgeLabel.isHidden = item.badgeText == nil
        // Иконка из assets (порт IconManager-подстановки)
        let name = ExteraIconManager.shared.iconName(item.menu.icon)
        self.iconView.image = UIImage(named: name)
            ?? UIImage(systemName: "circle")
    }
}

/// Пикер аккаунтов (порт DrawerAccountPickerView: горизонтальный список
/// аватаров + шапка с текущим пользователем)
final class ExteraDrawerAccountPickerView: UIView {
    private var accounts: [ExteraDrawerAccount] = []
    private var current: Int32 = 0
    private let stack = UIStackView()
    var onSwitch: ((Int32) -> Void)?

    override init(frame: CGRect) {
        super.init(frame: frame)
        self.stack.axis = .vertical
        self.stack.spacing = 8
        self.addSubview(self.stack)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func configure(accounts: [ExteraDrawerAccount], current: Int32) {
        self.accounts = accounts
        self.current = current
        self.stack.arrangedSubviews.forEach { $0.removeFromSuperview() }
        for account in accounts {
            let row = UIButton(type: .system)
            var title = account.name
            if account.unreadCount > 0 {
                title += " (\(ExteraNumberFormatter.format(account.unreadCount)))"
            }
            row.setTitle(title, for: .normal)
            row.contentHorizontalAlignment = .left
            row.setTitleColor(account.id == current ? .systemBlue : .label, for: .normal)
            row.addTarget(self, action: #selector(self.pickAccount(_:)), for: .touchUpInside)
            row.tag = Int(account.id)
            self.stack.addArrangedSubview(row)
        }
        self.setNeedsLayout()
    }

    @objc private func pickAccount(_ sender: UIButton) {
        self.onSwitch?(Int32(sender.tag))
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        self.stack.frame = CGRect(x: 16, y: 60, width: self.bounds.width - 32, height: self.bounds.height - 60)
    }
}
