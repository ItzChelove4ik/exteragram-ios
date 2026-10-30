//
//  ExteraTextFilters.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/utils/text/ZalgoFilter.kt — 1-в-1:
//  фильтрация zalgo-текста (combin-знаки сверх лимита заменяются на U+2060
//  WORD JOINER = 8288, direction-control символы вырезаются).
//
//  Логика портирована точно:
//  - последовательности combining marks (Unicode категории Mn=6, Mc=7):
//    zalgo-диапазоны (768-879, 6832-6911, 7616-7679, 8400-8447, 65056-65071)
//    допускают 2 знака подряд, прочие — 4; превышение — замена
//  - direction control (1564, 8206, 8207, 8234-8238, 8294-8297) — удаляются
//

import Foundation

public enum ExteraZalgoFilter {
    /// Порт isZalgoMarkRange
    private static func isZalgoMarkRange(_ cp: Int) -> Bool {
        return (cp >= 768 && cp <= 879)
            || (cp >= 6832 && cp <= 6911)
            || (cp >= 7616 && cp <= 7679)
            || (cp >= 8400 && cp <= 8447)
            || (cp >= 65056 && cp <= 65071)
    }

    /// Порт isDirectionControl
    private static func isDirectionControl(_ cp: Int) -> Bool {
        return cp == 1564
            || cp == 8206 || cp == 8207
            || (cp >= 8234 && cp <= 8238)
            || (cp >= 8294 && cp <= 8297)
    }

    /// Порт getAllowedMarksPerSequence: 2 для zalgo-диапазонов, 4 для прочих
    /// combining marks (Unicode.category Mn/Mc), 0 для остальных
    private static func allowedMarksPerSequence(_ scalar: Unicode.Scalar) -> Int {
        let cp = scalar.value
        guard Int(cp) >= 768 else { return 0 }
        // Character.getType == 6 (NON_SPACING_MARK) / 7 (COMBINING_SPACING_MARK)
        // в Swift: Unicode.Scalar.Properties.generalCategory == .nonspacingMark / .spacingMark
        let gc = scalar.properties.generalCategory
        guard gc == .nonspacingMark || gc == .spacingMark else { return 0 }
        return isZalgoMarkRange(Int(cp)) ? 2 : 4
    }

    /// Порт findReplacementRanges: диапазоны [start, end) для замены
    private static func findReplacementRanges(_ s: String) -> [(Int, Int)]? {
        let unicode = Array(s.unicodeScalars)
        let n = unicode.count
        var ranges: [(Int, Int)]? = nil
        var seqStart = -1
        var count = 0
        var limit = 4

        func addMarkSequenceRange(_ start: Int, _ end: Int, _ cnt: Int, _ lim: Int) {
            if start >= 0 && cnt > lim {
                if ranges == nil { ranges = [] }
                ranges!.append((start, end))
            }
        }

        func addRange(_ start: Int, _ end: Int) {
            if ranges == nil { ranges = [] }
            ranges!.append((start, end))
        }

        var i = 0
        while i < n {
            let scalar = unicode[i]
            let allowed = allowedMarksPerSequence(scalar)
            if allowed > 0 {
                if seqStart < 0 {
                    count = 0
                    seqStart = i
                }
                count += 1
                limit = allowed
            } else {
                addMarkSequenceRange(seqStart, i, count, limit)
                if isDirectionControl(Int(scalar.value)) {
                    addRange(i, i + 1)
                }
                seqStart = -1
                count = 0
                limit = 4
            }
            i += 1
        }
        addMarkSequenceRange(seqStart, n, count, limit)
        return ranges
    }

    /// Порт canFilter: включён ли фильтр и содержит ли текст заменяемые
    /// последовательности
    public static func canFilter(_ text: String?) -> Bool {
        guard ExteraConfig.shared.filterZalgo else { return false }
        guard let text = text, !text.isEmpty else { return false }
        return findReplacementRanges(text) != nil
    }

    /// Порт filter(String): zalgo-последовательности → WORD JOINER (U+2060)
    public static func filter(_ text: String?) -> String? {
        guard let text = text else { return nil }
        guard ExteraConfig.shared.filterZalgo else { return text }
        return filterEnabled(text)
    }

    private static func filterEnabled(_ s: String) -> String {
        guard let ranges = findReplacementRanges(s) else { return s }
        return assemble(s, ranges: ranges)
    }

    /// Сборка результата: до первого диапазона как есть, диапазоны → joiners,
    /// хвост — как есть
    private static func assemble(_ s: String, ranges: [(Int, Int)]) -> String {
        let scalars = Array(s.unicodeScalars)
        var result = String.UnicodeScalarView()
        var pos = 0
        for (start, end) in ranges {
            result.append(contentsOf: scalars[pos..<start])
            for _ in start..<end {
                result.append(Unicode.Scalar(8288)!)
            }
            pos = end
        }
        result.append(contentsOf: scalars[pos...])
        return String(result)
    }
}
