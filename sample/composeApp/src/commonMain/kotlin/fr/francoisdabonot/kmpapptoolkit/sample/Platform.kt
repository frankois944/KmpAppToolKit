package fr.francoisdabonot.kmpapptoolkit.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
