package fr.francoisdabonot.kmpapptoolkit.sample

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = "Hello, ${platform.name}!"
}
