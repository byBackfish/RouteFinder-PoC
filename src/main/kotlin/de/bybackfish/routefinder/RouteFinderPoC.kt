package de.bybackfish.routefinder

fun main() {
    val preLoad = System.currentTimeMillis()
    val routeLoader = RouteLoader()
    val routes = routeLoader.loadRoutes()
    val postLoad = System.currentTimeMillis()


    test(0, routes, false)
    test(1, routes, false)
    test(2, routes, false)

    println("\n\nLoaded ${routes.size} routes in ${postLoad - preLoad}ms")
    println("Total time taken: ${System.currentTimeMillis() - preLoad}ms")
}

fun test(maxStops: Int, routes: Array<Route>, print: Boolean = true) {
    val now = System.currentTimeMillis()
    val trips = RouteFinder.findRoutes("AER", "KZN", routes, maxStops)
    if(print) trips.forEach {
        println(
            """
                
                 Found Route with: ${it.size} stops
                 Starting: ${it.first().sourceAirport} to ${it.last().destinationAirport}
                 Stops: ${it.map { it.sourceAirport }} to ${it.map { it.destinationAirport }}
                 
                 ------------------------------------------------------------
            """.trimIndent()
        )
    }

    println("Found ${trips.size} possible trips with max of $maxStops stops in ${System.currentTimeMillis() - now}ms")
}

