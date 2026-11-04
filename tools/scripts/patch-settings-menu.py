#!/usr/bin/env python3
"""Патч: пункт «exteraGram» в настройках приложения + открытие экрана мода.

Правит 3 файла (в submodule telegram-ios, применяются CI):
1. PeerInfoSettingsItems.swift: секция .extera + пункт «exteraGram» в списке
2. PeerInfoScreen.swift: enum PeerInfoSettingsSection + case .exteraSettings
3. PeerInfoScreenSettingsActions.swift: handler открытия exteraSettingsController
4. PeerInfoScreen/BUILD: dep на //submodules/ExteraGram
"""
import sys

BASE = 'ios/telegram-ios/submodules/TelegramUI/Components/PeerInfo/PeerInfoScreen/Sources/'
NL = chr(10)

# 1. PeerInfoSettingsItems.swift — секция + пункт меню
p1 = BASE + 'PeerInfoSettingsItems.swift'
s = open(p1, encoding='utf-8').read()
if 'case extera' not in s:
    old = '''enum SettingsSection: Int, CaseIterable {
    case edit
    case phone
    case accounts
    case myProfile
    case wallet
    case proxy
    case apps
    case shortcuts
    case advanced'''
    new = '''enum SettingsSection: Int, CaseIterable {
    case edit
    case phone
    case accounts
    case myProfile
    case wallet
    case proxy
    case apps
    case shortcuts
    case extera
    case advanced'''
    assert old in s, 'sections enum not found'
    s = s.replace(old, new, 1)

anchor = '''    items[.advanced]!.append(PeerInfoScreenDisclosureItem(id: 0, label: notificationsWarning ? .badge("!", presentationData.theme.list.itemDestructiveColor) : .none, text: presentationData.strings.Settings_NotificationsAndSounds, icon: PresentationResourcesSettings.notifications, action: {'''
extera_item = '''    items[.extera]!.append(PeerInfoScreenDisclosureItem(id: 0, text: "exteraGram", icon: PresentationResourcesSettings.appearance, action: {
        interaction.openSettings(.exteraSettings)
    }))
'''
if 'items[.extera]' not in s:
    assert anchor in s, 'advanced anchor not found'
    s = s.replace(anchor, extera_item + anchor, 1)
with open(p1, 'w', encoding='utf-8', newline=NL) as f:
    f.write(s)
print('PeerInfoSettingsItems patched')

# 2. PeerInfoScreen.swift — enum секции
p2 = BASE + 'PeerInfoScreen.swift'
s2 = open(p2, encoding='utf-8').read()
if 'case exteraSettings' not in s2:
    old2 = '''enum PeerInfoSettingsSection {
    case avatar
    case edit
    case proxy'''
    new2 = '''enum PeerInfoSettingsSection {
    case exteraSettings
    case avatar
    case edit
    case proxy'''
    assert old2 in s2, 'section enum not found in PeerInfoScreen'
    s2 = s2.replace(old2, new2, 1)
    with open(p2, 'w', encoding='utf-8', newline=NL) as f:
        f.write(s2)
print('PeerInfoScreen enum patched')

# 3. PeerInfoScreenSettingsActions.swift — handler
p3 = BASE + 'PeerInfoScreenSettingsActions.swift'
s3 = open(p3, encoding='utf-8').read()
if 'import ExteraGram' not in s3:
    s3 = 'import ExteraGram' + NL + s3
old3 = '''        switch section {
        case .avatar:
            self.controller?.openAvatarForEditing()'''
new3 = '''        switch section {
        case .exteraSettings:
            push(exteraSettingsController(context: self.context))
        case .avatar:
            self.controller?.openAvatarForEditing()'''
assert old3 in s3, 'switch section not found'
if 'case .exteraSettings:' not in s3:
    s3 = s3.replace(old3, new3, 1)
with open(p3, 'w', encoding='utf-8', newline=NL) as f:
    f.write(s3)
print('PeerInfoScreenSettingsActions patched')

# 4. BUILD — dep модуля
p4 = 'ios/telegram-ios/submodules/TelegramUI/Components/PeerInfo/PeerInfoScreen/BUILD'
s4 = open(p4, encoding='utf-8').read()
if '//submodules/ExteraGram:ExteraGramUI' not in s4:
    s4 = s4.replace('        "//submodules/ItemListUI",',
                    '        "//submodules/ItemListUI",' + NL + '        "//submodules/ExteraGram:ExteraGramUI",', 1)
    with open(p4, 'w', encoding='utf-8', newline=NL) as f:
        f.write(s4)
print('PeerInfoScreen BUILD dep added')
