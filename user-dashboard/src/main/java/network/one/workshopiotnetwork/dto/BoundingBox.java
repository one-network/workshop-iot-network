package network.one.workshopiotnetwork.dto;

import java.util.Objects;
import java.util.regex.Pattern;

public class BoundingBox {

    private final Location southWest;
    private final Location northEast;

    public static BoundingBox fromString(final String str) {
        if (str == null || str.isBlank()) {
            return null;
        }

        // southwest_lng,southwest_lat,northeast_lng,northeast_lat
        final double[] coords = Pattern.compile(",")
                .splitAsStream(str)
                .map(String::trim)
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .limit(4)
                .mapToDouble(Double::doubleValue)
                .toArray();

        if (coords.length != 4) {
            return null;
        }

        final Location southWest = new Location();
        southWest.setLongitude(coords[0]);
        southWest.setLatitude(coords[1]);

        final Location northEast = new Location();
        northEast.setLongitude(coords[2]);
        northEast.setLatitude(coords[3]);

        return new BoundingBox(southWest, northEast);
    }

    public BoundingBox(
            final Location southWest,
            final Location northEast) {
        this.southWest = southWest;
        this.northEast = northEast;
    }

    public Location getSouthWest() {
        return southWest;
    }

    public Location getNorthEast() {
        return northEast;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((northEast == null) ? 0 : northEast.hashCode());
        result = prime * result + ((southWest == null) ? 0 : southWest.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoundingBox other = (BoundingBox) obj;
        if (northEast == null) {
            if (other.northEast != null)
                return false;
        } else if (!northEast.equals(other.northEast))
            return false;
        if (southWest == null) {
            if (other.southWest != null)
                return false;
        } else if (!southWest.equals(other.southWest))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "%f,%f,%f,%f",
                southWest.getLongitude(),
                southWest.getLatitude(),
                northEast.getLongitude(),
                northEast.getLatitude());
    }

}
