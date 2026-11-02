#!/usr/bin/env python3
"""Баг: сообщения не приходят в фоне (нет APNs при ad-hoc подписи).

SharedNotificationManager обрабатывает только push-уведомления (addNotification
вызывается из didReceiveRemoteNotification). При ad-hoc подписи пушей нет —
приложение в фоне не получает оповещений о новых сообщениях.

Фикс: фоновый polling — таймер, который при уходе в фон держит короткое
polling-соединение (как Telegram на Android без Google Play):
в beginPollingState уже есть pollStateUpdateCompletion — активируем его
по таймеру в фоне. iOS даёт ~30 сек background time после сворачивания —
используем их + BGProcessingTask.
"""
import sys

p = 'ios/telegram-ios/submodules/TelegramUI/Sources/SharedNotificationManager.swift'
s = open(p, encoding='utf-8').read()

marker = 'ExteraGram background polling patch'
if marker in s:
    print('already patched')
    sys.exit(0)

# 1. Добавляем фоновый polling в init
old_init = '''        self.accountsAndKeysDisposable = (accounts
        |> mapToSignal { accounts -> Signal<[(Account, Bool, MasterNotificationKey)], NoError> in'''
new_init = f'''        // {marker}: фоновый polling сообщений (ad-hoc без APNs)
        self.setupBackgroundPolling(inForeground: inForeground, accounts: accounts)

        self.accountsAndKeysDisposable = (accounts
        |> mapToSignal {{ accounts -> Signal<[(Account, Bool, MasterNotificationKey)], NoError> in'''
if old_init not in s:
    print('init pattern not found')
    sys.exit(1)
s = s.replace(old_init, new_init, 1)

# 2. Реализация: таймер + beginPollingState для активных аккаунтов
old_deinit = '''    deinit {
        self.inForegroundDisposable?.dispose()
        self.accountsAndKeysDisposable?.dispose()'''
new_deinit = f'''    // {marker}: таймер фонового опроса
    private var backgroundPollingTimer: SwiftSignalKit.Timer?
    private var backgroundPollingDisposable: Disposable?

    private func setupBackgroundPolling(inForeground: Signal<Bool, NoError>, accounts: Signal<[(Account, Bool)], NoError>) {{
        // при изменении аккаунтов — перезапускаем polling в фоне
        self.backgroundPollingDisposable = (accounts
        |> deliverOnMainQueue).startStrict(next: {{ [weak self] accounts in
            guard let strongSelf = self else {{ return }}
            strongSelf.backgroundPollingAccounts = accounts.map {{ $0.0 }}
            strongSelf.restartBackgroundPollingTimerIfNeeded()
        }})
        // смена foreground/background
        self.inForegroundDisposable?.dispose()
        self.inForegroundDisposable = (inForeground
        |> deliverOnMainQueue).startStrict(next: {{ [weak self] value in
            guard let strongSelf = self else {{ return }}
            strongSelf.inForeground = value
            if value {{
                strongSelf.stopBackgroundPolling()
            }} else {{
                strongSelf.restartBackgroundPollingTimerIfNeeded()
            }}
        }})
    }}

    private var backgroundPollingAccounts: [Account] = []

    private func restartBackgroundPollingTimerIfNeeded() {{
        guard !self.inForeground, !self.backgroundPollingAccounts.isEmpty else {{ return }}
        self.backgroundPollingTimer?.invalidate()
        // опрос каждые 20 сек, пока iOS даёт background time (~30 сек после
        // сворачивания; BGTask/voip продлевают). Каждое срабатывание — pollState.
        let timer = SwiftSignalKit.Timer(timeout: 20.0, repeat: true, completion: {{ [weak self] in
            self?.pollBackgroundOnce()
        }}, queue: Queue.mainQueue())
        self.backgroundPollingTimer = timer
        timer.start()
        self.pollBackgroundOnce()
    }}

    private func stopBackgroundPolling() {{
        self.backgroundPollingTimer?.invalidate()
        self.backgroundPollingTimer = nil
    }}

    private func pollBackgroundOnce() {{
        for account in self.backgroundPollingAccounts {{
            self.beginPollingState(account: account)
        }}
    }}

    deinit {{
        self.inForegroundDisposable?.dispose()
        self.backgroundPollingDisposable?.dispose()
        self.stopBackgroundPolling()
        self.accountsAndKeysDisposable?.dispose()'''
if old_deinit not in s:
    print('deinit pattern not found')
    sys.exit(1)
s = s.replace(old_deinit, new_deinit, 1)

open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('SharedNotificationManager patched: background polling')
