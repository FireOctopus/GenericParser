# GenericParser
Программа для разбора и вычислений выражений с тремя переменными x, y, z
## Структура проекта
1. **exceptions** - пакет с исключениями
2. **generic** - пакет с парсером
3. **modes** - пакет с режимами программы для разных типов:
* BigInteger
* Byte
* Double
* Float
* Integer
4. **operations** - пакет с операндами и арифмитическими операторами:
* константа (Const)
* переменная (Variable)
* сложение (Add)
* вычитание (Subtract)
* умножение (Multiply)
* деление (Divide)
* модуль числа (Abs)
* унарный минус (Negate)
* квадратный корень (Sqrt)

*В программе есть два варианта операций: с проверкой на переполение (в названии класса префикс "Checked") и без.*

***Примеры использования программы***

new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).evaluate(5)

Результат - 7 (эквивалентно выражению "2\*x-3")

new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).toString()

Результат - ((2 \* x) - 3)
