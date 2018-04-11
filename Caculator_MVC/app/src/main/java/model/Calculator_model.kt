package model

/**
 * Created by Liang Zihong on 2018/3/3.
 */

object Calculator_model {

    fun caculate(allstring: String): Double {

        val nk = numstack()
        val ck = charstack()

        val input = allstring.split("[ ]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in input.indices) {
            val ch = input[i][0]
            if (Character.isDigit(ch)) {
                val temp = java.lang.Double.parseDouble(input[i])
                nk.push(temp)
            } else {
                val temp = input[i][0]
                if (ck.size() == 0)
                    ck.push(temp)
                else {
                    if (temp == '+' || temp == '-')
                    //新的符号是加或减，则可以直接运算前一个符号。
                    {
                        if (ck.top() == '+') {
                            deal(nk, ck.pop())
                        } else if (ck.top() == '-') {
                            deal(nk, ck.pop())
                        } else if (ck.top() == '*')
                            deal(nk, ck.pop())
                        else if (ck.top() == '/')
                            deal(nk, ck.pop())
                        ck.push(temp)
                    }
                    if (temp == '*' || temp == '/')
                    //新的符号是乘或除。如果top是同级就运算前一个符号，低级就不运算，直接塞
                    {
                        if (ck.top() == '+' || ck.top() == '-') {
                            ck.push(temp)
                        } else if (ck.top() == '*') {
                            deal(nk, ck.pop())
                            ck.push(temp)
                        } else if (ck.top() == '/') {
                            deal(nk, ck.pop())
                            ck.push(temp)
                        } else
                            ck.push(temp)
                    }

                    if (temp == '(')
                        ck.push(temp)
                    if (temp == ')') {
                        while (ck.top() != '(') {
                            val cktop = ck.pop()
                            deal(nk, cktop)
                        }
                        ck.pop()
                    }
                }
            }
        }

        while (ck.size() != 0) {
            deal(nk, ck.pop())
        }

        return nk.top()

    }


    private fun deal(nk: numstack, ch: Char) {
        val second = nk.pop()
        val first = nk.pop()
        when (ch) {
            '+' -> nk.push(first + second)
            '-' -> nk.push(first - second)
            '*' -> nk.push(first * second)
            '/' -> nk.push(first / second)
        }
    }
}


internal class charstack @JvmOverloads constructor(n: Int = 50) {
    var arr: CharArray
    var now: Int = 0

    init {
        arr = CharArray(n)
        now = 0
    }

    fun push(temp: Char) {
        arr[now++] = temp
    }

    fun pop(): Char {
        return arr[--now]
    }

    fun top(): Char {
        return arr[now - 1]
    }

    fun size(): Int {
        return now
    }
}

internal class numstack @JvmOverloads constructor(n: Int = 50) {
    var arr: DoubleArray
    var size: Int = 0
    var now: Int = 0

    init {
        arr = DoubleArray(n)
        now = 0
    }

    fun push(temp: Double) {
        arr[now++] = temp
    }

    fun pop(): Double {
        return arr[--now]
    }

    fun top(): Double {
        return arr[now - 1]
    }
}

































