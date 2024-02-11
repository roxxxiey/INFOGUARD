//

package Lab_1

fun main(){
    var key = readln().split("").drop(1).dropLast(1)
    var input_text = readln().split("").drop(1).dropLast(1)

    val newKey = shiftKeyRight(key)
    println(newKey)
    var result_text: List<String> = decode(newKey, input_text)
    for (item in result_text) print(item)
    println()

    println(key)
    result_text = decode(key, result_text)

    for (item in result_text) print(item)
    println()
}

fun decode (key: List<String>, input_text: List<String>): List<String>{
    var flag = 0
    var modifiedKey = key
    val final_text = List<String>(input_text.size){""}.toMutableList()
    for(i in 0 until input_text.size){
        if (flag==key.size){
            modifiedKey = modifiedKey.map { (it.toInt() + modifiedKey.size).toString() }
            flag=0
        }
        final_text[i] = input_text[modifiedKey[flag].toInt() - 1]
        flag++
    }
    return final_text
}

fun shiftKeyRight(key: List<String>): List<String> {
    val length = key.size
    val tempArray = List<String>(length){""}.toMutableList()
    for (i in 0 until length) {
        tempArray[(i + 1) % length] = key[i]
    }
    return tempArray
}

