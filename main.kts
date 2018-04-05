// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn (input : Any) : String {
    when(input) {
        "Hello" -> return ("world")
        "Howdy" -> return ("Say what?")
        "Bonjour" -> return ("Say what?")
        0 -> return ("zero")
        1 -> return ("one")
        in 2..10 -> return ("low number")
        !in 0..10 -> return ("I don't understand")
        else -> {
            return ("I don't understand")
        }
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add (firstNumber : Int, secondNumber : Int) : Int {
    return firstNumber + secondNumber
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub (firstNumber : Int, secondNumber : Int)  : Int {
    return firstNumber - secondNumber
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp (firstNumber : Int, secondNumber : Int, operation : (Int, Int) -> Int): Int {
     return operation(firstNumber, secondNumber)
}

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int) {
    val debugString : String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"

    fun equals(other : Person): Boolean {
        return other.debugString == debugString
    }

    override fun hashCode(): Int {
        return(firstName.hashCode() + lastName.hashCode() + age.hashCode())
    }
}

// write a class "Money"
class Money(var amount: Int, var currency: String){
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount can not be negative")
        }
        when (currency) {
            "USD", "EUR", "CAN","GBP" -> currency = currency
            else -> throw IllegalArgumentException("Currency is not recognized")
        }
    }

    operator fun plus(other : Money): Money{
        if(other.currency != currency){
            val otherConverted = other.convert(currency)
            return Money(amount + otherConverted.amount, currency)
        } else {
            return Money(amount + other.amount, currency)
        }
    }

    public fun convert(newCurrency: String): Money{
        var convertedAmount: Int = amount
        if (currency == "USD") {
            when(newCurrency){
                "CAN" -> convertedAmount = amount * 5 / 4
                "EUR" -> convertedAmount = amount * 3 / 2
                "GBP" -> convertedAmount = amount / 2
                "USD" -> convertedAmount = amount
                else -> throw IllegalArgumentException("Currency not recognized")
            }
        } else if (currency == "GBP") {
            val amountInUSD = amount * 2
            when(newCurrency){
                "CAN" -> convertedAmount = amountInUSD * 5 / 4
                "EUR" -> convertedAmount = amountInUSD * 3 / 2
                "GBP" -> convertedAmount = amount
                "USD" -> convertedAmount = amountInUSD 
                else -> throw IllegalArgumentException("Currency not recognized")
            }
        } else if (currency == "EUR") {
            val amountInUSD = amount * (2/3)
            when(newCurrency){
                "CAN" -> convertedAmount = amountInUSD * 5 / 4
                "EUR" -> convertedAmount = amount
                "GBP" -> convertedAmount = amountInUSD / 2
                "USD" -> convertedAmount = amountInUSD
                else -> throw IllegalArgumentException("Currency not recognized")
            }
        } else if (currency == "CAN") {
            val amountInUSD = amount * (4/5)
            when(newCurrency){
                "CAN" -> convertedAmount = amount
                "EUR" -> convertedAmount = amountInUSD * 3 / 2
                "GBP" -> convertedAmount = amountInUSD / 2
                "USD" -> convertedAmount = amountInUSD
                else -> throw IllegalArgumentException("Currency not recognized")
            }
        }
        var convertedMoney = Money(convertedAmount, newCurrency) 
        return convertedMoney
    }
}
// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
