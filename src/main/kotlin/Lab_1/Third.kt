import kotlin.concurrent.thread

fun main() {
    val key_lenght = readln().toInt()
    var signature = readln()
    val input_text = readln().split("").drop(1).dropLast(1)

    val probably_key = CharArray(key_lenght)

    val final_keys = mutableListOf<String>()

    for (i in 0 until key_lenght) {
        probably_key[i] = (i + 49).toChar()
    }

    permute(probably_key, 0, probably_key.size - 1, final_keys)

    val finalText = Array<MutableList<String>>(final_keys.size){mutableListOf()}

    println(final_keys)
    var time = List<String>(key_lenght) { "" }
    var time2 = time


    val thread1 = thread {
        for (i in 0 until final_keys.size/3) {
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            time2 = shiftKeysRight(time)

            println("Shift key: $time2")
            println("First elemnt ${time2[0]}")

            finalText[i] = decoder(time2, input_text)
        }

    }

    val thread2 = thread {
        Thread.sleep(10)
        for (i in final_keys.size/3 until 2 * (final_keys.size / 3)) {
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            time2 = shiftKeysRight(time)

            println("Shift key: $time2")
            println("First elemnt ${time2[0]}")

            finalText[i] = decoder(time2, input_text)
        }

    }

    val thread3 = thread {
        Thread.sleep(100)
        for (i in 2 * (final_keys.size / 3) until final_keys.size) {
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            time2 = shiftKeysRight(time)

            println("Shift key: $time2")
            println("First elemnt ${time2[0]}")

            finalText[i] = decoder(time2, input_text)
        }

    }

    thread1.join()
    thread2.join()
    thread3.join()

    println("First element in finalText ${finalText[0][0]}")

    for (item in finalText) println(item)
    println()

    var pusing = MutableList<String>(finalText.size){""}

    for (i in 0 until finalText.size){
        for (j in 0 until finalText[0].size){
            pusing[i] += (finalText[i][j])
        }
    }

    println(pusing)

    val second = Array<List<String>>(pusing.size){mutableListOf()}

    for(i in 0 until second.size){
        second[i] = pusing[i].split("").drop(1).dropLast(1)
    }
    println("first element secnond mass ${second[0]}")
    println("first element secnond mass ${second[0][0]}")

    val preFinal = Array<MutableList<String>>(second.size){ mutableListOf() }


    val thread12 = thread {

        for (i in 0 until final_keys.size/3){
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            preFinal[i] = decoder(time, second[i])
        }

    }
    val thread22 = thread {

        for (i in final_keys.size/3 until 2 * (final_keys.size / 3)){
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            preFinal[i] = decoder(time, second[i])
        }

    }
    val thread32 = thread {

        for (i in 2 * (final_keys.size / 3) until final_keys.size){
            time = final_keys[i].toCharArray().map { it.toString() }

            println("Start key: $time")

            preFinal[i] = decoder(time, second[i])
        }

    }

    thread12.join()
    thread22.join()
    thread32.join()

    println("First element in finalText ${preFinal[0][0]}")

    for (item in preFinal) println(item)
    println()

    var result = MutableList<String>(finalText.size){""}

    for (i in 0 until preFinal.size){
        for (j in 0 until preFinal[0].size){
            result[i] += (preFinal[i][j])
        }
    }

    println(result)
    println(result[0])

    while (result.size>1) {
        println("Введите следующую сигнатуру сигнатуру: ")
        signature = readln()

        checkAndRemoveSubstring(result, signature)

        println(result)
    }
}




fun permute(digits: CharArray, left: Int, right: Int, permutations: MutableList<String>) {
    if (left == right) {
        permutations.add(String(digits))
    } else {
        for (i in left..right) {
            digits.swap(left, i)
            permute(digits, left + 1, right, permutations)
            digits.swap(left, i)
        }
    }
}
fun CharArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}




fun decoder (key: List<String>, input_text: List<String>): MutableList<String> {
    var flag = 0
    var modifiedKey = key
    var space_text = input_text
    if(input_text.size%key.size!=0){
        repeat(key.size-(input_text.size%key.size)){
            space_text = input_text.plus(" ")
        }
    }
    val final_text = List<String>(space_text.size){""}.toMutableList()
    for(i in 0 until space_text.size){
        if (flag==key.size){
            modifiedKey = modifiedKey.map { (it.toInt() + modifiedKey.size).toString() }
            flag=0
        }
        final_text[i] = space_text[modifiedKey[flag].toInt() - 1]
        flag++
    }
    return final_text
}




fun shiftKeysRight(key: List<String>): List<String> {
    val length = key.size
    val tempArray = List<String>(length){""}.toMutableList()
    for (i in 0 until length) {
        tempArray[(i + 1) % length] = key[i]
    }
    return tempArray
}

fun checkAndRemoveSubstring(array: MutableList<String>, substring: String) {
    val iterator = array.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        if (!element.contains(substring)) {
            iterator.remove()
        }
    }
}

