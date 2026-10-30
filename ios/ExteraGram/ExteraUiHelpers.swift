//
//  ExteraUiHelpers.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/utils/ui/*UiHelper.kt — визуальные фичи
//  appearance-раздела (C), применяемые к UIKit-контролам:
//  - AvatarCornerHelper (avatarCorners + singleCornerRadius + forum/community)
//  - FabUiHelper (squareFab / newFabStyle)
//  - SwitchUiHelper (newSwitchStyle)
//  - MaterialSliderUiHelper (newSliderStyle)
//  - NumberRounding (disableNumberRounding)
//  - TimeFormatHelper (formatTimeWithSeconds)
//  - RelativeLastSeen (relativeLastSeen)
//  - DividerHelper (dividerStyle)
//  - ChatHeaderUiHelper (newChatHeaderStyle / centerTitle / hideActionBarStatus)
//  - SectionRadius (sectionRadius + sectionsSeparatedHeaders)
//

import Foundation
import UIKit

// MARK: - AvatarCornerHelper (порт getAvatarCorners / AvatarCornerType)

public enum ExteraAvatarCorners {
    /// Порт getAvatarCorners(px, forum, community): радиус углов аватара
    /// из ExteraConfig.avatarCorners (0..100%) с типом скругления.
    public static func radius(px: CGFloat, forum: Bool = false, community: Bool = false) -> CGFloat {
        let config = ExteraConfig.shared
        let base = px * config.avatarCorners / 100.0
        let type: AvatarCornerType = forum ? .forum : (community ? .community : .default)
        switch type {
        case .default:
            return min(px, base)
        case .forum:
            // hexagon-подобное: 62% от полного
            return min(px, px * 0.62)
        case .community:
            return min(px, px * 0.72)
        @unknown default:
            return min(px, base)
        }
    }

    /// Единый радиус (singleCornerRadius): все углы равны максимальному
    public static func unifiedRadius(px: CGFloat) -> CGFloat {
        let config = ExteraConfig.shared
        return config.singleCornerRadius ? min(px, px * config.avatarCorners / 100.0) : radius(px: px)
    }
}

// MARK: - FabUiHelper (порт FabShapeCell/squareFab)

public enum ExteraFabUiHelper {
    /// Кнопка FAB: квадратная (squareFab) или круглая
    public static func cornerRadius(side: CGFloat) -> CGFloat {
        let config = ExteraConfig.shared
        if config.hideFloatingButton { return 0 }
        if config.squareFab {
            // newFabStyle — squircle 38%
            return config.newFabStyle ? side * 0.38 : side * 0.28
        }
        return side / 2
    }

    /// Стиль тени FAB (newFabStyle)
    public static func applyStyle(to button: UIButton, side: CGFloat) {
        button.layer.cornerRadius = cornerRadius(side: side)
        if ExteraConfig.shared.newFabStyle {
            button.layer.shadowColor = UIColor.black.cgColor
            button.layer.shadowOpacity = 0.25
            button.layer.shadowRadius = 12
            button.layer.shadowOffset = CGSize(width: 0, height: 4)
        }
    }
}

// MARK: - SwitchUiHelper (порт newSwitchStyle: MD3-стиль переключателя)

public enum ExteraSwitchUiHelper {
    /// MD3-переключатель: применение к UISwitch
    public static func applyStyle(to sw: UISwitch) {
        guard ExteraConfig.shared.newSwitchStyle else { return }
        sw.onTintColor = nil
        sw.tintColor = .systemGray4
        sw.layer.cornerRadius = sw.frame.height / 2
        sw.clipsToBounds = true
        // MD3 thumb больше трека
        sw.subviews.forEach { $0.clipsToBounds = true }
    }

    /// Рисование MD3-трека для кастомного рендера
    public static func trackColor(on: Bool) -> UIColor {
        if ExteraConfig.shared.newSwitchStyle {
            return on ? .systemBlue.withAlphaComponent(0.35) : .systemGray5
        }
        return on ? .systemBlue : .systemGray4
    }
}

// MARK: - MaterialSliderUiHelper (порт newSliderStyle)

public enum ExteraMaterialSliderUiHelper {
    /// MD3-слайдер: высота трека, метки-стопы
    public static var trackHeight: CGFloat {
        return ExteraConfig.shared.newSliderStyle ? 16 : 4
    }

    public static func trackColor() -> UIColor {
        return .systemGray5
    }

    public static var hasStopDots: Bool {
        return ExteraConfig.shared.newSliderStyle
    }
}

// MARK: - NumberRounding (порт disableNumberRounding)

public enum ExteraNumberFormatter {
    /// Форматирование 5.2K/1.2M (сток) или полного числа (мод)
    public static func format(_ count: Int) -> String {
        if ExteraConfig.shared.disableNumberRounding {
            return String(count)
        }
        // стоковое поведение: сокращение
        let absCount = abs(count)
        if absCount >= 1_000_000 {
            let v = Double(count) / 1_000_000
            return trimmed(v) + "M"
        } else if absCount >= 1_000 {
            let v = Double(count) / 1_000
            return trimmed(v) + "K"
        }
        return String(count)
    }

    private static func trimmed(_ v: Double) -> String {
        let s = String(format: "%.1f", v)
        return s.hasSuffix(".0") ? String(s.dropLast(2)) : s
    }
}

// MARK: - TimeFormatHelper (порт formatTimeWithSeconds + formatterDay24HSec/12HSec)

public enum ExteraTimeFormat {
    /// Время с секундами (форматтеры мода: formatterDay24HSec / formatterDay12HSec)
    public static func formatTime(_ date: Date, use24h: Bool = true) -> String {
        let df = DateFormatter()
        if ExteraConfig.shared.formatTimeWithSeconds {
            df.dateFormat = use24h ? "HH:mm:ss" : "h:mm:ss a"
        } else {
            df.dateFormat = use24h ? "HH:mm" : "h:mm a"
        }
        return df.string(from: date)
    }
}

// MARK: - RelativeLastSeen (порт relativeLastSeen)

public enum ExteraRelativeLastSeen {
    /// «был(а) N минут назад» вместо точного статуса
    public static func format(_ lastSeen: Date, now: Date = Date()) -> String? {
        guard ExteraConfig.shared.relativeLastSeen else { return nil }
        let interval = now.timeIntervalSince(lastSeen)
        switch interval {
        case ..<60:
            return ExteraLocaleController.shared.string("LastSeenJustNow")
        case ..<3600:
            let m = Int(interval / 60)
            return ExteraLocaleController.shared.quantityString("LastSeenMinutesAgo", count: m)
        case ..<86400:
            let h = Int(interval / 3600)
            return ExteraLocaleController.shared.quantityString("LastSeenHoursAgo", count: h)
        case ..<2592000:
            let d = Int(interval / 86400)
            return ExteraLocaleController.shared.quantityString("LastSeenDaysAgo", count: d)
        default:
            return nil
        }
    }
}

// MARK: - DividerHelper (порт DividerStyle)

public enum ExteraDividerHelper {
    /// Стиль разделителя списков
    public static func apply(to layer: CALayer, width: CGFloat) {
        switch ExteraConfig.shared.dividerStyle {
        case .line:
            layer.backgroundColor = UIColor.separator.cgColor
            layer.height = 0.5
        case .gradient:
            layer.backgroundColor = UIColor.clear.cgColor
            layer.height = 0.5
            // градиентная линия — через CAGradientLayer на месте вызова
        case .none:
            layer.isHidden = true
        @unknown default:
            break
        }
    }

    public static func makeGradientDivider(width: CGFloat) -> CAGradientLayer? {
        guard ExteraConfig.shared.dividerStyle == .gradient else { return nil }
        let g = CAGradientLayer()
        g.frame = CGRect(x: 0, y: 0, width: width, height: 0.5)
        g.colors = [UIColor.clear.cgColor, UIColor.separator.cgColor, UIColor.clear.cgColor]
        g.startPoint = CGPoint(x: 0, y: 0.5)
        g.endPoint = CGPoint(x: 1, y: 0.5)
        return g
    }
}

// MARK: - SectionRadius (порт sectionRadius + sectionsSeparatedHeaders)

public enum ExteraSectionStyle {
    /// Радиус секций настроек/листов
    public static var cornerRadius: CGFloat {
        let r = CGFloat(ExteraConfig.shared.sectionRadius)
        return ExteraConfig.shared.sectionsSeparatedHeaders ? r : r / 2
    }

    /// Разделённые заголовки секций
    public static var separatedHeaders: Bool {
        return ExteraConfig.shared.sectionsSeparatedHeaders
    }

    /// Применение к контейнеру секции
    public static func apply(to view: UIView) {
        view.layer.cornerRadius = cornerRadius
        view.layer.cornerCurve = .continuous
        view.clipsToBounds = true
    }
}

// MARK: - ChatHeaderUiHelper (порт newChatHeaderStyle/centerTitle/hideActionBarStatus)

public enum ExteraChatHeaderUiHelper {
    /// Центрирование заголовка
    public static var shouldCenterTitle: Bool {
        return ExteraConfig.shared.centerTitle
    }

    /// Скрыть подзаголовок (статус) в шапке чата
    public static var shouldHideStatus: Bool {
        return ExteraConfig.shared.hideActionBarStatus
    }

    /// Заголовок: имя (0) или @username (1)
    public static var titleMode: Int {
        return ExteraConfig.shared.titleText
    }
}

// MARK: - GlassBlur (порт forceBlur / glassMessageMenu / GlassOutlineStyle)

public enum ExteraGlass {
    /// Принудительный blur на старых устройствах
    public static var forceBlur: Bool {
        return ExteraConfig.shared.forceBlur
    }

    /// Стеклянное меню сообщений
    public static var glassMessageMenu: Bool {
        return ExteraConfig.shared.glassMessageMenu
    }

    /// Создание blur-эффекта с outline-стилем мода
    public static func makeEffectView(style: UIBlurEffect.Style) -> UIVisualEffectView {
        let effect = UIBlurEffect(style: style)
        let view = UIVisualEffectView(effect: effect)
        if ExteraConfig.shared.glassOutlineStyle == .glare {
            // блик поверх (порт GlassOutlineStyle.GLARE)
            let glare = CAGradientLayer()
            glare.colors = [
                UIColor.white.withAlphaComponent(0.0).cgColor,
                UIColor.white.withAlphaComponent(0.35).cgColor,
                UIColor.white.withAlphaComponent(0.0).cgColor,
            ]
            glare.startPoint = CGPoint(x: 0.2, y: 0)
            glare.endPoint = CGPoint(x: 0.8, y: 1)
            glare.frame = view.bounds
            glare.autoresizingMask = [.flexibleWidth, .flexibleHeight]
            view.layer.addSublayer(glare)
        }
        return view
    }
}

// MARK: - StickerShape (порт stickerShape: none/small/medium/large)

public enum ExteraStickerShape {
    /// Радиус углов стикера (0=none, 1=small, 2=medium, 3=large)
    public static func cornerRadius(side: CGFloat) -> CGFloat {
        switch ExteraConfig.shared.stickerShape {
        case 0: return 0
        case 1: return side * 0.08
        case 2: return side * 0.15
        case 3: return side * 0.25
        default: return side * 0.08
        }
    }
}

// MARK: - StickerTime (порт StickerTimeMode)

public enum ExteraStickerTime {
    /// Показывать время у стикеров
    public static var shouldShow: Bool {
        switch ExteraConfig.shared.stickerTimeMode {
        case .always: return true
        case .never: return false
        case .default: return false // сток: не показывает
        @unknown default: return false
        }
    }
}
