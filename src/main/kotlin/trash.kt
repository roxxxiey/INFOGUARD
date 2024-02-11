/*
    val finalVariants = Array<String>(finalText.size){""}

    for(i in 0 until finalText.size) {
        val point = finalText[i]
        val fin = point.replace(",", "").replace("[", "").replace("]", "").replace(" ", "")
        finalVariants[i] = fin
    }

    finalVariants.forEach { println(it) }
}*/


/*
for (i in 0 until final_keys.size) {
    time = final_keys[i].toCharArray().map { it.toString() }

    println("Start key: $time")

    time2 = shiftKeysRight(time)

    println("Shift key: $time2")
    println("First elemnt ${time2[0]}")

    finalText[i] = decoder(time2, input_text)
}
*/
/*
for (i in 0 until final_keys.size){
    time = final_keys[i].toCharArray().map { it.toString() }

    println("Start key: $time")

    preFinal[i] = decoder(time, second[i])
}*/
