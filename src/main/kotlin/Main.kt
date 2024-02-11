import kotlin.concurrent.thread


fun main(){

    val text = mutableListOf("sda", "sdasd", "asd","asda","q","q")

    val result = mutableListOf<String>()

    println("Mass on start $result")

    val thread1 = thread {

        for (item in text.subList(0, text.size/3)) {
            result.add(item)
            println(item)
        }

    }

    val thread2 = thread {
        Thread.sleep(10)
        for (item in text.subList(text.size / 3, 2 * (text.size / 3))) {
            result.add(item)
            println(item)
        }
    }
    val thread3 = thread {
        Thread.sleep(100)
        for (item in text.subList(2 * (text.size / 3), text.size)) {
            result.add(item)
            println(item)
        }
    }

    thread1.join()
    thread2.join()
    thread3.join()

    println("Mass on end $result")
}


