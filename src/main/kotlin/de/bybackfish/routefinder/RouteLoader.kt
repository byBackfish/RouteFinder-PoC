package de.bybackfish.routefinder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

class RouteLoader {
    fun loadRoutes(): Array<Route> {
        val fileContent = File("routes.csv").readText()
        val routes = fileContent.lines().drop(1).map { it.split(",") }

        return routes
            .filter { it.size == 9 }
            .map { route ->
            Route(
                airline = route[0],
                airlineId = route[1],
                sourceAirport = route[2],
                sourceAirportId = route[3],
                destinationAirport = route[4],
                destinationAirportId = route[5],
                codeshare = route[6],
                stops = route[7],
                equipment = route[8]
            )
        }.toTypedArray()
    }

}

//Airline,Airline ID,Source Airport,Source Airport ID,Destination Airport,Destination Airport ID,Codeshare,Stops,Equipment
data class Route(
    @SerialName("Airline")
    val airline: String,
    @SerialName("Airline ID")
    val airlineId: String,
    @SerialName("Source Airport")
    val sourceAirport: String,
    @SerialName("Source Airport ID")
    val sourceAirportId: String,
    @SerialName("Destination Airport")
    val destinationAirport: String,
    @SerialName("Destination Airport ID")
    val destinationAirportId: String,
    @SerialName("Codeshare")
    val codeshare: String?,
    @SerialName("Stops")
    val stops: String,
    @SerialName("Equipment")
    val equipment: String
)