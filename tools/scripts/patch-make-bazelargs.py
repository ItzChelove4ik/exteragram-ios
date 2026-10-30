#!/usr/bin/env python3
"""Патч build-system/Make/Make.py: проброс --bazelArguments в bazel build.

В апстриме аргумент парсится, но не используется (dead code). Добавляем
использование: shlex.split + add_additional_args в build().
"""
import sys

p = 'ios/telegram-ios/build-system/Make/Make.py'
s = open(p, encoding='utf-8').read()

marker = 'def add_additional_args(self, additional_args):'
if 'ExteraGram bazelArguments patch' in s:
    print('Make.py already patched')
    sys.exit(0)

# 1. get_additional_build_arguments: использовать self.additional_args
old = '''    def get_additional_build_arguments(self):
        combined_arguments = []'''
new = '''    def get_additional_build_arguments(self):
        combined_arguments = []
        # ExteraGram bazelArguments patch: проброс пользовательских флагов
        if self.additional_args is not None:
            combined_arguments += self.additional_args'''
if old in s:
    s = s.replace(old, new, 1)
else:
    print('pattern 1 not found')
    sys.exit(1)

# 2. build(): parse arguments.bazelArguments (shlex) в add_additional_args
old2 = '''    bazel_command_line.set_split_swiftmodules(arguments.enableParallelSwiftmoduleGeneration)

    bazel_command_line.invoke_build()'''
new2 = '''    bazel_command_line.set_split_swiftmodules(arguments.enableParallelSwiftmoduleGeneration)

    # ExteraGram bazelArguments patch: применить пользовательские флаги bazel
    if getattr(arguments, 'bazelArguments', None):
        import shlex as _shlex
        bazel_command_line.add_additional_args(_shlex.split(arguments.bazelArguments))

    bazel_command_line.invoke_build()'''
if old2 in s:
    s = s.replace(old2, new2, 1)
else:
    print('pattern 2 not found')
    sys.exit(1)

open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('Make.py patched: bazelArguments now forwarded to bazel')
