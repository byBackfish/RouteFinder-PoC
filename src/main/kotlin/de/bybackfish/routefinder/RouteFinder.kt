package de.bybackfish.routefinder

class RouteFinder {
    companion object {
        fun findRoutes(
            start: String,
            end: String,
            routes: Array<Route>,
            maxStops: Int = 4,
            currentStops: Int = 0,
            currentRoute: List<Route> = emptyList(),
            trips: MutableList<List<Route>> = mutableListOf()
        ): List<List<Route>> {
            if (currentStops > maxStops) {
                return trips
            }

            val lastStop = currentRoute.lastOrNull()?.destinationAirport ?: start
            val nextRoutes = routes.filter { it.sourceAirport == lastStop }

            for (route in nextRoutes) {
                val newRoute = currentRoute + route
                if (route.destinationAirport == end) {
                    trips.add(newRoute)
                } else {
                    findRoutes(start, end, routes, maxStops, currentStops + 1, newRoute, trips)
                }
            }

            return trips
        }
    }
}