//
//  ExteraAppIconSelectionController.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/appicons/ui/** (экран выбора иконки приложения
//  из Appearance → App Icon). UICollectionView-сетка с превью иконок, названием,
//  автором и seasonal-бейджем; выбор через ExteraAppIconController.setIcon.
//
//  Использует ТОЛЬКО оригинальные иконки ExteraGram (assets/AppIcons.xcassets).
//

import UIKit

public final class ExteraAppIconSelectionController: UIViewController {
    private var collectionView: UICollectionView!
    private let icons: [ExteraAppIcon]

    public init() {
        self.icons = ExteraAppIconController.shared.availableIcons
        super.init(nibName: nil, bundle: nil)
        self.title = ExteraLocaleController.shared.string("AppIcon")
    }

    required init?(coder: NSCoder) {
        self.icons = ExteraAppIconController.shared.availableIcons
        super.init(coder: coder)
    }

    public override func viewDidLoad() {
        super.viewDidLoad()

        self.view.backgroundColor = .systemBackground

        let layout = UICollectionViewFlowLayout()
        layout.minimumInteritemSpacing = 16
        layout.minimumLineSpacing = 24
        layout.sectionInset = UIEdgeInsets(top: 24, left: 24, bottom: 24, right: 24)
        let itemSide = (self.view.bounds.width - 24 * 2 - 16 * 2) / 3
        layout.itemSize = CGSize(width: itemSide, height: itemSide + 44)

        self.collectionView = UICollectionView(frame: self.view.bounds, collectionViewLayout: layout)
        self.collectionView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.collectionView.backgroundColor = .clear
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
        self.collectionView.register(ExteraAppIconCell.self, forCellWithReuseIdentifier: "icon")
        self.view.addSubview(self.collectionView)
    }

    // MARK: - DataSource

    public func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.icons.count
    }

    public func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "icon", for: indexPath) as! ExteraAppIconCell
        let icon = self.icons[indexPath.item]
        cell.configure(
            with: icon,
            image: ExteraAppIconController.shared.previewImage(for: icon),
            selected: ExteraAppIconController.shared.isEnabled(icon)
        )
        return cell
    }

    // MARK: - Delegate

    public func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let icon = self.icons[indexPath.item]
        ExteraAppIconController.shared.setIcon(icon) { [weak self] _ in
            self?.collectionView.reloadData()
        }
    }
}

/// Порт AppIconPreviewDrawable + ячейка списка (иконка, title, author)
final class ExteraAppIconCell: UICollectionViewCell {
    private let iconView = UIImageView()
    private let titleLabel = UILabel()
    private let authorLabel = UILabel()
    private let checkmark = UIImageView(image: UIImage(systemName: "checkmark.circle.fill"))

    override init(frame: CGRect) {
        super.init(frame: frame)
        self.iconView.contentMode = .scaleAspectFill
        self.iconView.layer.cornerRadius = frame.width / 5.2
        self.iconView.clipsToBounds = true
        self.titleLabel.font = .systemFont(ofSize: 12, weight: .medium)
        self.titleLabel.textAlignment = .center
        self.authorLabel.font = .systemFont(ofSize: 10)
        self.authorLabel.textColor = .secondaryLabel
        self.authorLabel.textAlignment = .center
        self.checkmark.tintColor = .systemBlue
        self.checkmark.isHidden = true

        let stack = UIStackView(arrangedSubviews: [self.iconView, self.titleLabel, self.authorLabel])
        stack.axis = .vertical
        stack.alignment = .center
        stack.spacing = 6
        stack.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(stack)
        NSLayoutConstraint.activate([
            stack.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 4),
            stack.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -4),
            stack.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor, constant: 4),
            stack.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -4),
            self.iconView.widthAnchor.constraint(equalTo: self.iconView.heightAnchor),
        ])
        self.checkmark.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(self.checkmark)
        NSLayoutConstraint.activate([
            self.checkmark.trailingAnchor.constraint(equalTo: self.iconView.trailingAnchor, constant: 4),
            self.checkmark.topAnchor.constraint(equalTo: self.iconView.topAnchor, constant: -4),
        ])
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func configure(with icon: ExteraAppIcon, image: UIImage?, selected: Bool) {
        self.iconView.image = image
        self.titleLabel.text = icon.title
        self.authorLabel.text = icon.author ?? ""
        self.checkmark.isHidden = !selected
    }
}
