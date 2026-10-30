//
//  ExteraMathEngine.swift
//  ExteraGram iOS port
//
//  Порт com/exteragram/messenger/math/** (MathExpression, MathOperators,
//  MathFunction, Infix/Prefix/PostfixOperator, MathResult, MathFormat) — 1-в-1.
//
//  Операторы (приоритеты как в Android-моде):
//    infix:  + (10)   - − (10)   * × (20)   / ÷ (20)   ^ (40, right-assoc)
//    prefix: - − (30)  + (30)  √ (40, right-assoc)
//    postfix: % (left-assoc)  ! (non-assoc)
//  Функции: sqrt cbrt abs sin round floor ceil fact min max log log2 ln exp
//           sin cos tan asin acos atan atan2 sinh cosh tanh (арности из IntRange)
//  Константы: pi/π = 3.141592653589793, e = 2.718281828459045
//
//  Использование: вычисление формул в поле ввода (inlineMathResult,
//  GhostTextLayout-превью) — порт D15.
//

import Foundation

// MARK: - Operators (порт InfixOperator/PrefixOperator/PostfixOperator)

public struct ExteraInfixOperator {
    public let symbols: [String]
    public let precedence: Int
    public let rightAssociative: Bool
    public let apply: (Double, Double) -> Double
}

public struct ExteraPrefixOperator {
    public let symbols: [String]
    public let precedence: Int
    public let rightAssociative: Bool
    public let apply: (Double) -> Double
}

public struct ExteraPostfixOperator {
    public let symbols: [String]
    public let leftAssociative: Bool
    public let apply: (Double) -> Double
}

public struct ExteraMathFunction {
    public let name: String
    public let arity: ClosedRange<Int>
    public let apply: ([Double]) -> Double
}

// MARK: - MathOperators (порт MathOperators.kt)

public enum ExteraMathOperators {
    public static let constants: [String: Double] = [
        "pi": 3.141592653589793,
        "π": 3.141592653589793,
        "e": 2.718281828459045,
    ]

    public static let infix: [ExteraInfixOperator] = [
        ExteraInfixOperator(symbols: ["+"], precedence: 10, rightAssociative: false) { $0 + $1 },
        ExteraInfixOperator(symbols: ["-", "−"], precedence: 10, rightAssociative: false) { $0 - $1 },
        ExteraInfixOperator(symbols: ["*", "×"], precedence: 20, rightAssociative: false) { $0 * $1 },
        ExteraInfixOperator(symbols: ["/", "÷"], precedence: 20, rightAssociative: false) { $0 / $1 },
        ExteraInfixOperator(symbols: ["^"], precedence: 40, rightAssociative: true) { pow($0, $1) },
    ]

    public static let prefix: [ExteraPrefixOperator] = [
        ExteraPrefixOperator(symbols: ["-", "−"], precedence: 30, rightAssociative: false) { -$0 },
        ExteraPrefixOperator(symbols: ["+"], precedence: 30, rightAssociative: false) { $0 },
        ExteraPrefixOperator(symbols: ["√"], precedence: 40, rightAssociative: true) { sqrt($0) },
    ]

    public static let postfix: [ExteraPostfixOperator] = [
        ExteraPostfixOperator(symbols: ["%"], leftAssociative: true) { $0 / 100.0 },
        ExteraPostfixOperator(symbols: ["!"], leftAssociative: false) { factorial($0) },
    ]

    public static let functions: [ExteraMathFunction] = [
        ExteraMathFunction(name: "sqrt", arity: 1...1) { sqrt($0[0]) },
        ExteraMathFunction(name: "cbrt", arity: 1...1) { cbrt($0[0]) },
        ExteraMathFunction(name: "abs", arity: 1...1) { abs($0[0]) },
        ExteraMathFunction(name: "sin", arity: 1...1) { sin($0[0]) },
        ExteraMathFunction(name: "round", arity: 1...1) { $0[0].rounded() },
        ExteraMathFunction(name: "floor", arity: 1...1) { floor($0[0]) },
        ExteraMathFunction(name: "ceil", arity: 1...1) { ceil($0[0]) },
        ExteraMathFunction(name: "fact", arity: 1...1) { factorial($0[0]) },
        ExteraMathFunction(name: "min", arity: 1...Int.max) { $0.min() ?? .nan },
        ExteraMathFunction(name: "max", arity: 1...Int.max) { $0.max() ?? .nan },
        ExteraMathFunction(name: "log", arity: 1...1) { log10($0[0]) },
        ExteraMathFunction(name: "log2", arity: 1...1) { log2($0[0]) },
        ExteraMathFunction(name: "ln", arity: 1...1) { Foundation.log($0[0]) },
        ExteraMathFunction(name: "exp", arity: 1...1) { exp($0[0]) },
        ExteraMathFunction(name: "cos", arity: 1...1) { cos($0[0]) },
        ExteraMathFunction(name: "tan", arity: 1...1) { tan($0[0]) },
        ExteraMathFunction(name: "asin", arity: 1...1) { asin($0[0]) },
        ExteraMathFunction(name: "acos", arity: 1...1) { acos($0[0]) },
        ExteraMathFunction(name: "atan", arity: 1...1) { atan($0[0]) },
        ExteraMathFunction(name: "atan2", arity: 2...2) { atan2($0[0], $0[1]) },
        ExteraMathFunction(name: "sinh", arity: 1...1) { sinh($0[0]) },
        ExteraMathFunction(name: "cosh", arity: 1...1) { cosh($0[0]) },
        ExteraMathFunction(name: "tanh", arity: 1...1) { tanh($0[0]) },
    ]

    /// Порт factorial (MathOperators.fact): гамма-функция для нецелых,
    /// точная для целых в разумных пределах
    public static func factorial(_ x: Double) -> Double {
        if x < 0 { return .nan }
        if x == 0 { return 1 }
        if x.truncatingRemainder(dividingBy: 1) == 0 && x < 171 {
            var result = 1.0
            var i = 2.0
            while i <= x {
                result *= i
                i += 1
            }
            return result
        }
        // Гамма-функция Ланцоша (для нецелых) — как fact() мода
        return gamma(x + 1)
    }

    /// Гамма-функция (Ланцоша, g=7, n=9)
    private static func gamma(_ x: Double) -> Double {
        if x < 0.5 {
            return .pi / sin(.pi * x) / gamma(1 - x)
        }
        let x = x - 1
        let g = 7.0
        let c: [Double] = [
            0.99999999999980993,
            676.5203621218851,
            -1259.1392167224028,
            771.32342877765313,
            -176.61502916214059,
            12.507343278686905,
            -0.13857109526572012,
            9.9843695780195716e-6,
            1.5056327351493116e-7,
        ]
        var a = c[0]
        let t = x + g + 0.5
        for i in 1..<c.count {
            a += c[i] / (x + Double(i))
        }
        return sqrt(2 * .pi) * pow(t, x + 0.5) * exp(-t) * a
    }
}

// MARK: - MathExpression (парсер и вычислитель, порт MathExpression.kt)

public enum ExteraMathError: Error, Equatable {
    case syntaxError
    case unknownFunction(String)
    case arityMismatch(String)
    case divideByZero
}

public final class ExteraMathExpression {
    // Токены
    private enum Token {
        case number(Double)
        case constant(String)
        case identifier(String)
        case op(String)
        case lparen
        case rparen
        case comma
    }

    private let tokens: [Token]

    public init(_ text: String) throws {
        self.tokens = try ExteraMathExpression.tokenize(text)
    }

    /// Порт MathExpression.evaluate(): значение выражения
    public func evaluate() throws -> Double {
        var pos = 0
        let value = try self.parseExpression(minPrecedence: 0, pos: &pos)
        guard pos == self.tokens.count else { throw ExteraMathError.syntaxError }
        return value
    }

    // MARK: Токенизация (порт лексера MathExpression)

    private static func tokenize(_ text: String) throws -> [Token] {
        var tokens: [Token] = []
        let chars = Array(text.unicodeScalars)
        var i = 0
        var allSymbols: [String] = ["(", ")", ","]
        for op in ExteraMathOperators.infix { allSymbols.append(contentsOf: op.symbols) }
        for op in ExteraMathOperators.prefix { allSymbols.append(contentsOf: op.symbols) }
        for op in ExteraMathOperators.postfix { allSymbols.append(contentsOf: op.symbols) }
        let sortedSymbols = allSymbols.sorted { $0.count > $1.count }

        while i < chars.count {
            let ch = chars[i]
            let s = String(ch)
            if ch.properties.isWhitespace {
                i += 1
                continue
            }
            // число
            if ch.properties.numericType != nil || (ch == "." && i + 1 < chars.count && chars[i+1].properties.numericType != nil) {
                var num = ""
                var dotSeen = false
                while i < chars.count {
                    let c = chars[i]
                    if c.properties.numericType != nil {
                        num.append(Character(c))
                        i += 1
                    } else if c == "." && !dotSeen {
                        dotSeen = true
                        num.append(Character(c))
                        i += 1
                    } else {
                        break
                    }
                }
                guard let v = Double(num) else { throw ExteraMathError.syntaxError }
                tokens.append(.number(v))
                continue
            }
            // идентификатор / функция / константа
            if ch.properties.isAlphabetic || ch == "_" {
                var id = ""
                while i < chars.count {
                    let c = chars[i]
                    if c.properties.isAlphabetic || c.properties.numericType != nil || c == "_" || c == "π" {
                        id.append(Character(c))
                        i += 1
                    } else {
                        break
                    }
                }
                if ExteraMathOperators.constants[id] != nil {
                    tokens.append(.constant(id))
                } else {
                    tokens.append(.identifier(id))
                }
                continue
            }
            // операторы/скобки — включая многосимвольные
            var matched = false
            for sym in sortedSymbols {
                let symScalars = Array(sym.unicodeScalars)
                if i + symScalars.count <= chars.count {
                    let slice = chars[i..<(i + symScalars.count)]
                    if Array(slice) == symScalars {
                        switch sym {
                        case "(": tokens.append(.lparen)
                        case ")": tokens.append(.rparen)
                        case ",": tokens.append(.comma)
                        default: tokens.append(.op(sym))
                        }
                        i += symScalars.count
                        matched = true
                        break
                    }
                }
            }
            if !matched {
                throw ExteraMathError.syntaxError
            }
        }
        return tokens
    }

    // MARK: Pratt-парсер (порт рекурсивного парсера MathExpression.kt)

    private func peek(_ pos: inout Int) -> Token? {
        return pos < tokens.count ? tokens[pos] : nil
    }

    private func parseExpression(minPrecedence: Int, pos: inout Int) throws -> Double {
        var left = try self.parsePrimary(pos: &pos)

        while true {
            guard case .op(let sym)? = peek(&pos),
                  let op = ExteraMathOperators.infix.first(where: { $0.symbols.contains(sym) }),
                  op.precedence >= minPrecedence else {
                break
            }
            pos += 1
            let nextMin = op.rightAssociative ? op.precedence : op.precedence + 1
            let right = try self.parseExpression(minPrecedence: nextMin, pos: &pos)
            left = op.apply(left, right)
        }
        return left
    }

    private func parsePrimary(pos: inout Int) throws -> Double {
        guard let tok = peek(&pos) else { throw ExteraMathError.syntaxError }

        switch tok {
        case .number(let v):
            pos += 1
            // postfix-операторы после числа
            return try self.applyPostfix(to: v, pos: &pos)

        case .constant(let name):
            pos += 1
            guard let v = ExteraMathOperators.constants[name] else { throw ExteraMathError.syntaxError }
            return try self.applyPostfix(to: v, pos: &pos)

        case .op(let sym):
            // prefix-оператор
            guard let op = ExteraMathOperators.prefix.first(where: { $0.symbols.contains(sym) }) else {
                throw ExteraMathError.syntaxError
            }
            pos += 1
            let operand = try self.parseExpression(minPrecedence: op.precedence, pos: &pos)
            let value = op.apply(operand)
            return try self.applyPostfix(to: value, pos: &pos)

        case .lparen:
            pos += 1
            let inner = try self.parseExpression(minPrecedence: 0, pos: &pos)
            guard case .rparen? = peek(&pos) else { throw ExteraMathError.syntaxError }
            pos += 1
            return try self.applyPostfix(to: inner, pos: &pos)

        case .identifier(let name):
            // функция: name(args...)
            pos += 1
            guard case .lparen? = peek(&pos) else { throw ExteraMathError.unknownFunction(name) }
            pos += 1
            var args: [Double] = []
            // первый аргумент (или пустой)
            if case .rparen? = peek(&pos) {
                pos += 1
                return try self.callFunction(name, args: args)
            }
            args.append(try self.parseExpression(minPrecedence: 0, pos: &pos))
            while case .comma? = peek(&pos) {
                pos += 1
                args.append(try self.parseExpression(minPrecedence: 0, pos: &pos))
            }
            guard case .rparen? = peek(&pos) else { throw ExteraMathError.syntaxError }
            pos += 1
            return try self.callFunction(name, args: args)

        case .rparen, .comma:
            throw ExteraMathError.syntaxError
        }
    }

    private func applyPostfix(to value: Double, pos: inout Int) throws -> Double {
        var v = value
        while case .op(let sym)? = peek(&pos),
              let op = ExteraMathOperators.postfix.first(where: { $0.symbols.contains(sym) }) {
            pos += 1
            v = op.apply(v)
            if !op.leftAssociative { break }
        }
        return v
    }

    private func callFunction(_ name: String, args: [Double]) throws -> Double {
        guard let fn = ExteraMathOperators.functions.first(where: { $0.name == name }) else {
            throw ExteraMathError.unknownFunction(name)
        }
        guard fn.arity.contains(args.count) else {
            throw ExteraMathError.arityMismatch(name)
        }
        return fn.apply(args)
    }
}

// MARK: - MathResult + MathFormat (порт форматирования результата)

public enum ExteraMathFormat {
    /// Формат результата: как MathFormat.kt — целые без .0, дробь до 6 знаков
    public static func format(_ value: Double) -> String {
        if value.isNaN || value.isInfinite { return "NaN" }
        if value == value.rounded() && abs(value) < 1e15 {
            // целое — без дробной части
            if abs(value) < 1e15 {
                let s = String(format: "%.0f", value)
                return s
            }
        }
        var s = String(format: "%.6f", value)
        // обрезать хвостовые нули
        while s.hasSuffix("0") { s.removeLast() }
        if s.hasSuffix(".") { s.removeLast() }
        return s
    }
}

// MARK: - Suggestion (порт MathSuggestion: подсказка формулы в поле ввода)

public enum ExteraMathSuggestion {
    /// Порт MathSuggestion.detect: последняя формула в тексте (для превью)
    /// Формула — завершённый токен-хвост, содержащий цифры/операторы
    public static func extractExpression(from text: String) -> String? {
        let chars = Array(text)
        var start: Int? = nil
        for i in stride(from: chars.count - 1, through: 0, by: -1) {
            let c = chars[i]
            let isMath = c.isNumber || "+-*/^%!".contains(c) || c == "." || c == "(" || c == ")"
                || "abcdegilmnorstux√π×÷−".contains(c)
            if isMath {
                if start == nil { start = i }
            } else {
                break
            }
        }
        guard let s = start, s < chars.count - 1 else { return nil }
        let candidate = String(chars[s...])
        // должна быть хотя бы одна цифра
        guard candidate.contains(where: { $0.isNumber }) else { return nil }
        return candidate
    }

    /// Вычислить формулу из текста (для GhostTextLayout-превью)
    public static func evaluate(from text: String) -> String? {
        guard ExteraConfig.shared.inlineMathResult else { return nil }
        guard let expr = extractExpression(from: text) else { return nil }
        guard let parsed = try? ExteraMathExpression(expr) else { return nil }
        guard let value = try? parsed.evaluate(), !value.isNaN, !value.isInfinite else { return nil }
        return ExteraMathFormat.format(value)
    }
}
