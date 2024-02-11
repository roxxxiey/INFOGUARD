//

fun main(){
    var key = readln().split("").drop(1).dropLast(1)
    var input_text = readln().split("").drop(1).dropLast(1)
    var flag = 0
    if(input_text.size%key.size!=0){
        repeat(key.size-(input_text.size%key.size)){
            input_text = input_text.plus(" ")
        }
    }
    var result_text: List<String> = encode(key, input_text)

    for (item in result_text) print(item)
    println()

    val newKey = shiftKeyRight(key)

    println(newKey)

    result_text = encode(newKey, result_text)

    for (item in result_text) print(item)
    println()
}



fun encode (key: List<String>, input_text: List<String>): List<String>{
    var flag = 0
    var modifiedKey = key
    val final_text = List<String>(input_text.size){""}.toMutableList()
    for(i in 0 until input_text.size){
        if (flag==key.size){
            modifiedKey = modifiedKey.map { (it.toInt() + modifiedKey.size).toString() }
            flag=0
        }
        final_text[modifiedKey[flag].toInt() - 1] = input_text[i]
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